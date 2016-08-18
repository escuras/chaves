/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Keys;

/**
 *
 * @author toze
 */
public class Feature implements Comparable<Feature>{
    private String descricao;
    private String medida;
    private int quantidade;
    TypeOfMaterial material;
    
    
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
        material = new TypeOfMaterial();
    }
    
    public Feature(String descricao, String medida, int quantidade, TypeOfMaterial mat){
        this.descricao = descricao;
        this.medida = medida;
        this.quantidade = quantidade;
        material = mat;
    }
   
    
    public Feature(Feature feature){
        this.descricao = feature.getDescription();
        this.medida = feature.getUnityMeasure();
        this.quantidade = feature.getNumber();
        this.material = feature.getTypeOfMaterial();
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

    /**
     * @return the mat
     */
    public TypeOfMaterial getTypeOfMaterial() {
        return material;
    }

    @Override
    public int compareTo(Feature o) {
        int val;
        if ((val = this.getDescription().compareTo(o.getDescription())) == 0) {
            if ((val = this.getUnityMeasure().compareTo(o.getUnityMeasure())) == 0) {
                return this.getNumber() - o.getNumber();
            }
        }
        return val;
    }

   
    
}
