/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
/**
 *
 * @author toze
 */
public class ButtonListRequest{

    List<javax.swing.JButton> bLista;
    Set<Clavis.Material> mater;
    Dimension dim;
    Langs.Locale lingua;
    
    public ButtonListRequest(RequestList req, Langs.Locale lingua) {
        this.mater = new HashSet<>();
        this.lingua = lingua;
        dim = new Dimension(140,40);
        req.getRequests().stream().forEach((r) -> {
            Clavis.Material mat = new Clavis.Material(r.getMaterial());
            boolean aux;
            if (mater.isEmpty()) {
                mater.add(mat);
            } else {
                aux = false;
                for (Clavis.Material m : mater){
                    if (m.getCodeOfMaterial().equals(mat.getCodeOfMaterial())) aux = true;
                }
                if (!aux) mater.add(mat);
            }
        });
    }
    
    public String [] getListOfMaterialType(){
        String []nomes = new String[this.mater.size()];
        int i = 0;
        for (Clavis.Material m : this.mater){
            nomes[i] = lingua.translate(m.getDescription());
            i++;
        }
        return nomes;
    } 
    
    public  List<javax.swing.JButton> getButtons(){
        this.bLista = new ArrayList<>();
        if (!this.mater.isEmpty()) {
            for (Clavis.Material m : this.mater){
                javax.swing.JButton button = new javax.swing.JButton();
                button.setText(this.lingua.translate(m.getDescription()));
                button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setPreferredSize(dim);
                button.setMaximumSize(dim);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setBackground(new Color(254,254,254));
                //button.setPreferredSize(new Dimension(40,40));
                button.setSize(new Dimension(50,100));
                bLista.add(button);
            }
        }
        return bLista;
    }
    
    public javax.swing.JScrollPane getScrollPane(){
        javax.swing.JScrollPane aux = new javax.swing.JScrollPane();
        javax.swing.JPanel pane = new javax.swing.JPanel();
        aux.setPreferredSize(new Dimension(400,200));
        this.bLista = this.getButtons();
        if (!this.bLista.isEmpty()) {
            pane.setAutoscrolls(true);
            pane.setLayout(new BorderLayout());
            pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
            pane.setBorder(new EmptyBorder(0, 30, 20, 30));
            pane.setMinimumSize(new Dimension(0, 0));
            pane.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
            pane.setPreferredSize(new Dimension(100, 300));
            pane.setMaximumSize(new Dimension(120000,120000));
            pane.setBackground(Color.DARK_GRAY);
            aux.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            for (javax.swing.JButton bt : bLista){
                pane.add(bt);
            }
           
        }
        aux.setViewportView(pane);
        return aux;
    }
    
    
    
    
}
