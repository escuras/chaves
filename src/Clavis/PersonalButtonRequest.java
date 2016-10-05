/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import javax.swing.BorderFactory;

/**
 *
 * @author toze
 */

public class PersonalButtonRequest extends javax.swing.JButton implements Comparable<PersonalButtonRequest>, Cloneable {
    
    private static final long serialVersionUID = 123L;
    int valor;
    String designacao;

    public PersonalButtonRequest() {
        super();
        valor = -1;
        designacao = "";
        super.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }

    public PersonalButtonRequest(int val, String designacao) {
        super();
        valor = val;
        this.designacao = designacao;
        super.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }

    public PersonalButtonRequest(PersonalButtonRequest r) {
        super();
        valor = r.getValue();
        this.designacao = r.getDescription();
        super.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }

    public void setValue(int val) {
        valor = val;
    }

    public int getValue() {
        return valor;
    }

    public void setDescription(String designacao) {
        this.designacao = designacao;
    }

    public String getDescription() {
        return designacao;
    }

    @Override
    public int compareTo(PersonalButtonRequest o) {
        if ((this.getText().matches("\\d+")) && (o.getText().matches("\\d+"))) {
            String texto = this.getText();
            String texto2 = o.getText();
            while (texto.charAt(0) == '0') {
                texto = texto.replaceFirst("0", "");
            }
            while (texto2.charAt(0) == '0') {
                texto2 = texto2.replaceFirst("0", "");
            }
            int val1 = Integer.parseInt(texto);
            int val2 = Integer.parseInt(texto2);
            return (val1 - val2);
        }
        int val;
        val = this.getDescription().compareTo(o.getDescription());
        if (val == 0) {
            val = this.getText().compareTo(o.getText());
            if (val == 0) {
                val = this.getValue() - o.getValue();
            }
        }
        return val;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}