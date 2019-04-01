package me.saukin.jpaControllers;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import me.saukin.entities.Clients;

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

//    public void edit(Clients clients) throws NonexistentEntityException, RollbackFailureException, Exception {
//        try {
//            utx.begin();
//            clients = em.merge(clients);
//            utx.commit();
//        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
//            try {
//                utx.rollback();
//            } catch (IllegalStateException | SecurityException | SystemException re) {
//                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
//            }
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Integer id = clients.getId();
//                if (findClients(id) == null) {
//                    throw new NonexistentEntityException("The clients with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } 
//    }
//
//    
//
//    public List<Clients> findClientsEntities() {
//        return findClientsEntities(true, -1, -1);
//    }
//
//    public List<Clients> findClientsEntities(int maxResults, int firstResult) {
//        return findClientsEntities(false, maxResults, firstResult);
//    }
//
//    private List<Clients> findClientsEntities(boolean all, int maxResults, int firstResult) {
//        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(Clients.class));
//        Query q = em.createQuery(cq);
//        if (!all) {
//            q.setMaxResults(maxResults);
//            q.setFirstResult(firstResult);
//        }
//        return q.getResultList(); 
//    }
//
//    public Clients findClients(Integer id) {
//        return em.find(Clients.class, id);
//    }
//
//    public int getClientsCount() {
//        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<Clients> rt = cq.from(Clients.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//    } 
  
}
