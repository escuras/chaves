/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;


/**
 *
 * @author toze
 */
public class Function {
    private String descricao;
    private int id;
    private int privilegio;
    
    public Function()
    {
        this.descricao = "";
        this.id = -1;
        this.privilegio = 0;
    }
    
    public Function( String nome){
        this.descricao = nome;
        this.id = -1;
        this.privilegio = -1;
    } 
    
     public Function( String nome, int privilegio){
        this.descricao = nome;
        this.id = -1;
        this.privilegio = privilegio;
    } 
    
    public Function(int id, String nome, int privilegio){
        this.descricao = nome;
        this.id = id;
        this.privilegio = privilegio;
    } 
    
    public Function(Function func)
    {
        this.descricao = func.getName();
        this.id = func.getId();
        this.privilegio = func.getPrivilege();
    }
    /**
     * @return the funcao
     */
    public String getName() {
        return descricao;
    }

    /**
     * @param nome the funcao to set
     */
    public void setName(String nome) {
        this.descricao = nome;
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



