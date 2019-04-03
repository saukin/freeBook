
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
import org.primefaces.PrimeFaces;

/**
 *
 * @author saukin
 */

@Named
@SessionScoped
public class LoginBean implements Serializable{
    
    @Inject
    RegisterBean registerBean;
    
    @Inject
    private ClientsJpaController clientsJpaController;
    
    @Inject
    private Clients clients;
    
    private String email;
    private String password;
    private String loginMessage = "";

    
    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
    
    
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
    
    private FacesMessage message = null;
    private boolean loggedIn = false;
    
    public String login() {
        String s;
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
               getExternalContext().getSession(false);
        Clients cl = clientsJpaController.findClientByEmail(email);
        

        

        if (cl != null) {
            if (email != null && email.equals(cl.getEmail()) && password != null && password.equals(cl.getPassword())) {
                loggedIn = true;
                session.setAttribute("loggedIn", loggedIn);
                System.out.println("return dwnl");
                s = "download";
            } else {
                loggedIn = false;
                session.setAttribute("loggedIn", loggedIn);
                System.out.println("return null");
                loginMessage = "login error. please check email and password!";
                s = "welcome"; 
            }
        } else {
            loginMessage = "login error. please check email and password!";
            s = "welcome";
        }
        return s;
    }   
    
    
    
    
    

    
}
