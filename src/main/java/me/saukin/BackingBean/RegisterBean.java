
package me.saukin.BackingBean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import me.saukin.entities.Clients;
import me.saukin.jpaControllers.ClientsJpaController;

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
    
    public String createClients() throws Exception {
        clientsJpaController.create(clients);
        return "login";
    }
    
    public String chooseBook() {
        System.out.println("Book is " + clients.getBook());
        return "registration";
    }
    
}
