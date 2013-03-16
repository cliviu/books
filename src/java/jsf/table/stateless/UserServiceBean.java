/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.table.stateless;

import java.util.Collection;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jsf.table.model.Book;
import jsf.table.model.User;

/**
 *
 * @author lcarausu
 */
@Startup
@Stateless
public class UserServiceBean implements UserService{

    @PersistenceContext(unitName="zntBooksPU")
    protected EntityManager em;
    
    @Override
    public void addUser(User user) 
    {
        em.persist(user);
    }

    @Override
    public void deleteUser(User user) 
    {
        User foundUser = em.find(User.class, user.getId());
        System.out.println("Em found user " + foundUser);
        em.remove(foundUser);
    }

    @Override
    public Collection<User> findAllUsers() {
        Query query = em.createQuery("SELECT u FROM User u");
        return (Collection<User>) query.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
