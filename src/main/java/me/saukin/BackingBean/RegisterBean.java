
package me.saukin.BackingBean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import me.saukin.entities.Clients;
import me.saukin.jpaControllers.ClientsJpaController;
import me.saukin.util.MessageUtil;

/**
 *
 * @author saukin
 */
@Named("registerBean")
@SessionScoped
public class RegisterBean implements Serializable {
    
    @Inject
    private ClientsJpaController clientsJpaController;
    
    private Clients clients;
    
//    private String book = "";
//
//    public String getBook() {
//        return book;
//    }
//
//    public void setBook(String book) {
//        this.book = book;
//    }
//    
    
    
    public Clients getClients() {
        if (clients == null) {
            clients = new Clients();
        }
        return clients;
    }
    
//    public void setClients(Clients clients) {
//        this.clients = clients;
//    }
    
    public boolean isEmailValid() {
        boolean valid = false;
        String email = clients.getEmail();
        if (email != null) {
            if (clientsJpaController.findClientByEmail(email) == null) {
                valid = true;
            } else {
                FacesMessage message = MessageUtil.getMessage("me.saukin.bundles.messages", 
                        "emailExists", null);
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage("signupForm:email", message);
            }
        }
        return valid;
    }
    
    
    public String createClients() throws Exception {
        if (isEmailValid()) {
            clientsJpaController.create(clients);
        return "login";
        }
        return null;
    }
    
    public String chooseBook() {
        System.out.println("Book is " + clients.getBook());
        return "registration";
    }
    
}
