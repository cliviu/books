/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.table.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author lcarausu
 */
@Entity
@Table(name="ZNT_USERS")
public class User {
    private static final long serialVersionUID = 1L;    
    
    @TableGenerator(name="UserGen")   
    @Id @GeneratedValue(generator="UserGen")
    private Long id;
    
    private int persId;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    @OneToMany(mappedBy="owner")
    private List<Book> books;
    
    public User()
    {
    }

    public User(String firstName, String lastName, int persId, String department) {
        this.persId = persId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }
    
    private String department;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jsf.table.model.User[ id=" + id + " ]";
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
    
}
