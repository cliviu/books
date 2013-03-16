/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.controller;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import jsf.table.model.User;
import jsf.table.stateless.BookService;
import jsf.table.stateless.UserService;

/**
 *
 * @author lcarausu
 */
@ManagedBean
@ApplicationScoped
public class NewUserController 
{
    
    @EJB UserService userService;
    @EJB BookService bookService;
    
    private int persId;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private String department;
    
    public NewUserController()
    {        
    }

    public NewUserController(int persId, String password, String firstName, String lastName, String department) 
    {
        this.persId = persId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }
    
    public NewUserController(User user) 
    {
        this.persId = user.getPersId();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.department = user.getDepartment();
    }

    
    
    /**
     * @return the persId
     */
    public int getPersId() {
        return persId;
    }

    /**
     * @param persId the persId to set
     */
    public void setPersId(int persId) {
        this.persId = persId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        System.out.println("xxxx Set first name " + firstName);
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void valueChanged(ValueChangeEvent event) 
    {
        System.out.println("*** value changed " + event);
    }    
    
    public void persistUser()            
    {
       // Thread.dumpStack();
        System.out.println("Persist user called");
        userService.addUser(new User(firstName,lastName,persId,department));
    }
}
