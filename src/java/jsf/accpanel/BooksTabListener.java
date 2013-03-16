/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.accpanel;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;  
  

@Named
@RequestScoped
public class BooksTabListener {  
    
    private String text ="Text BooksTabListener";
    
    public BooksTabListener()
    {
    }
  
    public void onTabChange(TabChangeEvent event) {  
        System.out.println("Tab changed");        
        System.out.println("Tab changed" + "Active Tab:" + event.getTab().getId());        
    }  

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}  
  
