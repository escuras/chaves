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
public class Mae {
    
    public static void main(String[] args){
        Familia troca = new Familia("Santos",20);
        troca.escreveFamilia();
        Familia santos = new Familia("Troca",6);
        santos.escreveFamilia();
        
        int h= 1;
      
        
    } 
    
}

class Familia {
    String nome;
    int elmentos;
    
    public Familia(String nome, int elementos){
        this.nome = nome;
        this.elmentos = elementos;
    }
    
    public int getElementos(){
        return elmentos;
    }
    
    public String getnome(){
        return this.nome;
    }
    
    public void escreveFamilia(){
        System.out.println("Familia "+this.nome + ":");
        System.out.println("tem "+ this.elmentos +" elementos.");
    }
    
}
