/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;


/**
 *
 * @author toze
 */
public class jasonteste {
    
    public static void main(String[] args){
       DataBase.DataBase db = new DataBase.DataBase("jdbc:mysql://localhost:3306/clavis?autoReconnect=true&useSSL=false&user=root&password=sobral");
       java.util.Set<String> st = db.getActivity("TEO");
       for (String g: st){
           System.out.println(g);
       }
    } 
    
}
