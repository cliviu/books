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
public class TabBean {  
  
    public TabBean()
    {
    }
    public void onChange(TabChangeEvent event) {  
        System.out.println("Tab changed");
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab:" + event.getTab().getId());  
        System.out.println("Tab changed" + "Active Tab:" + event.getTab().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}  
  