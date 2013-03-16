/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.accpanel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;


/**
 *
 * @author carausu
 */
@Named
@RequestScoped
public class AccPanelListener 
{
    private String test = "test";
    public void onChange(TabChangeEvent event)
    {
        System.out.println("Tab changed " + event);
        System.exit(0);
    }

    /**
     * @return the test
     */
    public String getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(String test) {
        this.test = test;
    }
}

