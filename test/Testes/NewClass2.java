/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import java.util.BitSet;

/**
 *
 * @author toze
 */
public class NewClass2 {
    public static void main(String[] args) {
        BitSet bt = new BitSet();
        bt.set(1, true);
        bt.set(1);
        System.out.println(bt.toString());
        System.out.println(bt.toByteArray()[0]);
        javax.swing.JToggleButton bto = new javax.swing.JToggleButton();
        bto.setText("ola");
        java.awt.Component[] com = bto.getComponents();
        DataBase.DataBase db = new DataBase.DataBase("jdbc:mysql://localhost:3306/clavis?autoReconnect=true&useSSL=false&user=root&password=sobral");
        Keys.TypeOfMaterial tp = db.getTypeOfMaterial(1);
        Keys.Material mat = new Keys.Material(tp, "awsed69", "94", false);
        Keys.Classroom cla = new Keys.Classroom(mat);
        db.insertMaterial(cla);
    }
}
