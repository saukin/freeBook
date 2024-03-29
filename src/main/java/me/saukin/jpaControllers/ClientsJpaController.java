package me.saukin.jpaControllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import me.saukin.entities.Clients;
import me.saukin.jpaControllers.exceptions.NonexistentEntityException;

import me.saukin.jpaControllers.exceptions.RollbackFailureException;

/**
 *
 * @author saukin
 */

@Named
@RequestScoped
public class ClientsJpaController implements Serializable {

    @Resource
    private UserTransaction utx;
    
    @PersistenceContext(unitName = "FinalLabPU")
    private EntityManager em;

    public void create(Clients clients) throws RollbackFailureException, Exception {
        try {
            utx.begin();
            em.persist(clients);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
        } 
    }
    
    
    public List<Clients> findAllClients() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Clients> cq = cb.createQuery(Clients.class);
        Root<Clients> client = cq.from(Clients.class);
        cq.select(client);
        TypedQuery<Clients> query = em.createQuery(cq);

        // Using a named query
//        TypedQuery<Fish> query =  entityManager.createNamedQuery("Fish.findAll", Fish.class);

        // Execute the query
        List<Clients> clients = query.getResultList();

        return clients;
    }

   

    public Clients findClients(String email, String password) {
        TypedQuery<Clients> query = em.createNamedQuery("Clients.findByEmailAndPassword", Clients.class);
        query.setParameter(1, email);
        query.setParameter(2, password);
        List<Clients> clients = query.getResultList();
        if (!clients.isEmpty()) {
            return clients.get(0);
        }
        return null;
    }
    
    public Clients findClientByEmail(String email) {
        TypedQuery<Clients> query = em.createNamedQuery("Clients.findByEmail", Clients.class);
        query.setParameter("email", email);
        List<Clients> clients = query.getResultList();
        if (!clients.isEmpty()) {
            return clients.get(0);
        }
        return null;
    }

    public int getClientsCount() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clients> rt = cq.from(Clients.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
    } 
  
}
