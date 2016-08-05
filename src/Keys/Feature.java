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
public class Feature {
    private String descricao;
    private String medida;
    private int quantidade;
    private java.util.ArrayList<TypeOfMaterial> mat;
    
    
    public Feature(){
        this.descricao = "";
        this.medida = "";
        this.quantidade = 0;
        this.mat = new java.util.ArrayList<>();
    }
    
    public Feature(String descricao){
        this.descricao = descricao;
        this.medida = "";
        this.quantidade = 0;
        this.mat = new java.util.ArrayList<>();
    }
    
    public Feature(String descricao, String medida, int quantidade){
        this.descricao = descricao;
        this.medida = medida;
        this.quantidade = quantidade;
        this.mat = new java.util.ArrayList<>();
    }
    
    public Feature(String descricao, String medida, int quantidade, TypeOfMaterial mat){
        this.descricao = descricao;
        this.medida = medida;
        this.quantidade = quantidade;
        this.mat = new java.util.ArrayList<>();
        this.mat.add(mat);
    }
    
    public Feature(String descricao, String medida, int quantidade, TypeOfMaterial[] mat){
        this.descricao = descricao;
        this.medida = medida;
        this.quantidade = quantidade;
        this.mat = new java.util.ArrayList<>();
        int i = 0;
        while (i < mat.length) {
            this.mat.add(mat[i]);
            i++;
        }
    }
    
    public Feature(Feature feature){
        this.descricao = feature.getDescription();
        this.medida = feature.getUnityMeasure();
        this.quantidade = feature.getNumber();
        this.mat = feature.getTypeOfMaterial();
    }
    
    public void addTypeOfMaterial(TypeOfMaterial mat){
        this.mat.add(mat);
    }
    
    public void removeTypeOfMaterial(TypeOfMaterial mat){
        this.mat.remove(mat);
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
    public java.util.ArrayList<TypeOfMaterial> getTypeOfMaterial() {
        return mat;
    }

   
    
}
