/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author toze
 */
public class Function {
    private String nome;
    private int id;
    private int privilegio;
    
    public Function()
    {
        this.nome = "";
        this.id = 0;
        this.privilegio = 0;
    }
    
     public Function( String nome, int privilegio){
        this.nome = nome;
        this.id = -1;
        this.privilegio = privilegio;
    } 
    
    public Function(int id, String nome, int privilegio){
        this.nome = nome;
        this.id = id;
        this.privilegio = privilegio;
    } 
    
    public Function(Function func)
    {
        this.nome = func.getName();
        this.id = func.getId();
        this.privilegio = func.getPrivilege();
    }
    /**
     * @return the funcao
     */
    public String getName() {
        return nome;
    }

    /**
     * @param nome the funcao to set
     */
    public void setName(String nome) {
        this.nome = nome;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the privilegio
     */
    public int getPrivilege() {
        return privilegio;
    }

    /**
     * @param privilegio the privilegio to set
     */
    public void setPrivilege(int privilegio) {
        this.privilegio = privilegio;
    }
}



