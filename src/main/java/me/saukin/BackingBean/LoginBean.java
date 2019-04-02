
package me.saukin.BackingBean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import me.saukin.entities.Clients;
import me.saukin.jpaControllers.ClientsJpaController;
import me.saukin.util.MessageUtil;

/**
 *
 * @author saukin
 */

@Named
@SessionScoped
public class LoginBean implements Serializable{
    
    @Inject
    private ClientsJpaController clientsJpaController;
    
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public String login() {
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        
        FacesMessage message;
        
        boolean loggedIn;
        
        String nextPage = "";
        
        Clients clients = clientsJpaController.findClients(email, password);
        
        if (clients != null) {
            loggedIn = true;
            message = MessageUtil.getMessage(
                    "me.saukin.bundles.messages", "welcome", new Object[]{email});
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            nextPage = "download";
        } else {
            loggedIn = false;
            message = MessageUtil.getMessage("me.saukin.bundles.messages", "loginError", new Object[]{email});
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            nextPage = "login";
        }
        
        session.setAttribute("loggedIn", loggedIn);
        
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        return nextPage;
    }
    
}
