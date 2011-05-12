package hailu.vaadin.authentication.core;

import com.vaadin.Application;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletConfig;
import java.util.Collection;

/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
public abstract class VaadinApplication extends Application {

    private WebApplicationContext webApplicationContext;
    private ServletConfig servletConfig;

    public String currentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Collection<GrantedAuthority> availableAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public boolean hasAnyRole(String ... roles){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority authority: authorities){
			for(String role: roles){
				if(role.equals(authority.getAuthority())){
					return true;
				}
			}
		}
		return false;
	}

    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }
}
