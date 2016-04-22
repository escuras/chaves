/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import javax.swing.JTable;

/**
 *
 * @author toze
 */
public class TColors extends Thread implements Runnable{

    private JTable tabela;
    
    public TColors(JTable tabela){
        this.tabela = tabela;
    } 
    
 
    @Override
    public void run() {
        
    }
    
}
