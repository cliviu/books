/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.table.stateless;

import java.util.Collection;
import jsf.table.model.User;

/**
 *
 * @author lcarausu
 */
public interface UserService 
{
    public void addUser(User user);
    public void deleteUser(User user);
    public Collection<User> findAllUsers();        
}
