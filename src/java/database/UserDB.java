package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import models.Users;

public class UserDB {

    public int insert(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            //Users user = Users.getUsername();
             //user.getNoteList().add(user);
            trans.begin();
            em.persist(user);
            //em.merge(user);
            trans.commit();
            
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
            return 1;
        }
    }

    public int update(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
           em.merge(user);
           trans.commit();
           
        } catch (Exception ex) {
            trans.rollback();
        } finally {
           em.close();
           return 1;
        }
        
       
    }

    public List<Users> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Users> user = em.createNamedQuery("Users.findAll", Users.class).getResultList();
             return user;
    
        } finally {
            em.close();
        }
        
    }

    /**
     * Get a single user by their username.
     *
     * @param username The unique username.
     * @return A User object if found, null otherwise.
     * @throws NotesDBException
     */
    public Users getUser(String username) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Users user = em.find(Users.class, username);
            return user;
        } finally {
            em.close();

        }
    }

    public int delete(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            
           // user.getNoteList().remove(note);
           trans.begin();
           em.remove(em.merge(user));
           //em.merge(user);
           trans.commit();
           
        } catch (Exception ex) {
         trans.rollback();
         
        } finally {
             em.close();
             return 1;
        } 
       
    }

}
