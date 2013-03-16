/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.accpanel;

/**
 *
 * @author lcarausu
 */
public class CounterBean {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        count++;
        System.out.print("Count " + count);
    }
}