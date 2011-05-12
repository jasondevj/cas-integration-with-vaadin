package hailu.vaadin.authentication.core;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
public class VaadinAbstractApplicationServlet extends AbstractApplicationServlet {

    private static final String VAADIN_WINDOW = "vaadin-application";
    private String vaadinWindow;
    private WebApplicationContext webApplicationContext;
    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        super.init(servletConfig);
        vaadinWindow = servletConfig.getInitParameter(VAADIN_WINDOW);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        try {
            final VaadinApplication vaadinApplication = (VaadinApplication) Class.forName(vaadinWindow).newInstance();
            vaadinApplication.setServletConfig(servletConfig);
            vaadinApplication.setWebApplicationContext(webApplicationContext);
            return vaadinApplication;
        } catch (Exception e) {
            throw new RuntimeException("Could not initiate Vaadin application : ", e);
        }
    }

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return (Class<? extends Application>) Class.forName(vaadinWindow);
    }
}
