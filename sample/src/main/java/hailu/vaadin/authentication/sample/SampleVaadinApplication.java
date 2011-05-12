package hailu.vaadin.authentication.sample;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import hailu.vaadin.authentication.core.VaadinApplication;
import org.springframework.security.core.GrantedAuthority;

/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
public class SampleVaadinApplication extends VaadinApplication {


    private UriFragmentUtility fragmentUtility;
    private VerticalLayout layout;

    @Override
    public void init() {
        final Window mainWindow = new Window("Vaadin Cas Sample");
        fragmentUtility = new UriFragmentUtility();
        addListener();
        mainWindow.setSizeFull();
        mainWindow.addComponent(fragmentUtility);
        layout = new VerticalLayout();
        mainWindow.addComponent(layout);
        setMainWindow(mainWindow);
        createHomeButton();
    }

    private void addListener() {
        fragmentUtility.addListener(new UriFragmentUtility.FragmentChangedListener() {
            @Override
            public void fragmentChanged(UriFragmentUtility.FragmentChangedEvent source) {
                final String fragment = source.getUriFragmentUtility().getFragment();
                if (fragment.equals("do-something")) {
                    homeView();
                } else if (fragment.equals("back")) {
                    createHomeButton();
                } else if (fragment.equals("/")) {
                    createHomeButton();
                } else {
                    throw new RuntimeException("Unknown fragment [" + fragment + "]");
                }
            }
        });
    }

    private void createHomeButton() {
        layout.removeAllComponents();
        layout.addComponent(new Label("Sample Vaadin press home button to enter secure"));
        final Button homeButton = new Button("Do something");
        homeButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                final String path = getURL().getPath();
                if (!hasAnyRole("ROLE_DEFAULT_ACCESS")) {
                    getMainWindow().open(new ExternalResource(path + "do-something/"));
                } else {
                    fragmentUtility.setFragment("do-something");
                }
            }
        });
        layout.addComponent(homeButton);
    }

    private void homeView() {
        layout.removeAllComponents();
        layout.addComponent(new Label("You have logged into a secure area following are the roles available for you"));
        layout.addComponent(new Label("Current Username : " + currentUserName()));
        for (GrantedAuthority grantedAuthority : availableAuthorities()) {
            layout.addComponent(new Label("role : " + grantedAuthority.getAuthority()));
        }
        if (hasAnyRole("ROLE_ADMIN")) {
            layout.addComponent(new Label("You have admin Access, Use it wisely"));
        } else if (hasAnyRole("ROLE_DEFAULT_ACCESS")) {
            layout.addComponent(new Label("You have normal user access"));
        } else {
            layout.addComponent(new Label("You have no access"));
        }
        backButton();
        logoutButton();
    }

    private void logoutButton() {
        final Button logout = new Button("Logout");
        logout.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getMainWindow().open(new ExternalResource(getURL().getPath() + "j_spring_cas_security_logout"));
            }
        });
        layout.addComponent(logout);
    }

    private void backButton() {
        final Button backButton = new Button("Back");
        backButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                fragmentUtility.setFragment("back");
            }
        });
        layout.addComponent(backButton);
    }
}
