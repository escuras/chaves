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
public class Software {
    private String nome;
    private String versao;
    private boolean atualizado;

    public Software(){
        nome = "";
        versao = "";
        atualizado = false;
    }
    
    public Software(String nome, String versao){
        this.nome = nome;
        this.versao = versao;
        atualizado = false;
    }
    
    public Software(String nome, String versao, boolean atualizado){
        this.nome = nome;
        this.versao = versao;
        this.atualizado = atualizado;
    }
    
    public Software(Software soft) {
        this.nome = soft.getName();
        this.versao = soft.getVersion();
        this.atualizado = soft.isUpdated();
    }
    
    
    /**
     * @return the nome
     */
    public String getName() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setName(String nome) {
        this.nome = nome;
    }

    /**
     * @return the versao
     */
    public String getVersion() {
        return versao;
    }

    /**
     * @param versao the versao to set
     */
    public void setVersion(String versao) {
        this.versao = versao;
    }

    /**
     * @return the atualizado
     */
    public boolean isUpdated() {
        return atualizado;
    }

    /**
     * @param atualizado the atualizado to set
     */
    public void setUpdated(boolean atualizado) {
        this.atualizado = atualizado;
    }
}
