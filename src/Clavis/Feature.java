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
public class Feature {
    private String descricao;
    private String medida;
    private int quantidade;
    
    
    public Feature(){
        this.descricao = "";
        this.medida = "";
        this.quantidade = 0;
    }
    
    public Feature(String descricao){
        this.descricao = descricao;
        this.medida = "";
        this.quantidade = 0;
    }
    
    public Feature(String descricao, String medida, int quantidade){
        this.descricao = descricao;
        this.medida = medida;
        this.quantidade = quantidade;
    }
    
    public Feature(String descricao, String medida, int quantidade, int disponibilidade){
        this.descricao = descricao;
        this.medida = medida;
        this.quantidade = quantidade;
    }
    
    public Feature(Feature feature){
        this.descricao = feature.getDescription();
        this.medida = feature.getUnityMeasure();
        this.quantidade = feature.getNumber();
    }

    /**
     * @return the descricao
     */
    public String getDescription() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescription(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the medida
     */
    public String getUnityMeasure() {
        return medida;
    }

    /**
     * @param medida the medida to set
     */
    public void setUnityMeasure(String medida) {
        this.medida = medida;
    }

    /**
     * @return the quantidade
     */
    public int getNumber() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setNumber(int quantidade) {
        this.quantidade = quantidade;
    }

   
    
}
