/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Clavis.KeyQuest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toze
 */
public class EfectsOnDivisor extends Thread {

    boolean condition = true;
    javax.swing.JSplitPane jSplitPaneInicial;
    double valor = 0;

    public EfectsOnDivisor(javax.swing.JSplitPane jSplitPaneInicial, double valor) {
        this.jSplitPaneInicial = jSplitPaneInicial;
        this.valor = valor;
    }

    @Override
    public void run() {
        int val = jSplitPaneInicial.getDividerLocation();
        if (valor == 0.5) {
            valor = jSplitPaneInicial.getHeight() / 2;
            if (val <= valor) {
                while ((val < valor) && (condition)) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(KeyQuest.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    jSplitPaneInicial.setDividerLocation(val);
                    val = val + 1;
                }
            } else {
                while ((val > valor) && (condition)) {
                    val = val - 1;
                    jSplitPaneInicial.setDividerLocation(val);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(KeyQuest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (valor > 0) {
            while ((val < valor) && (condition)) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(KeyQuest.class.getName()).log(Level.SEVERE, null, ex);
                }

                jSplitPaneInicial.setDividerLocation(val);
                val = val + 1;
            }
        } else {
            while ((val > valor) && (condition)) {
                val = val - 1;
                jSplitPaneInicial.setDividerLocation(val);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(KeyQuest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

}
