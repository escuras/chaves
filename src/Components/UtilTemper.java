/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author toze
 */
public class UtilTemper extends Timer {
     
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            
        }
    };
    
    public void start(int time){
        this.schedule(task, time);
    }
    
    public void start(Date time){
        this.schedule(task, time);
    }
    
}
