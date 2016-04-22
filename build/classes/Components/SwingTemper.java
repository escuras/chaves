/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author toze
 */
public class SwingTemper extends Timer  {
    
    public SwingTemper(int delay, ActionListener listener) {
        super(delay, listener);
    }
    
}
 /* Timer java.util
        java.util.Timer tim = new java.util.Timer();
        tim.schedule(new java.util.TimerTask(){
            @Override
            public void run() {
                JOptionPane.showMessageDialog(rootPane, "Ola mundo");
                System.out.println(new java.util.Date().getTime());
            }
        
        },1000*10);
        */
        
        
        /* javax.swing.timer
        Timer t = new Timer(10000, (ActionEvent e) -> {
            JOptionPane.showMessageDialog(rootPane, "Olass mundo");
        });
        t.start();
        t.setRepeats(true);
        */
