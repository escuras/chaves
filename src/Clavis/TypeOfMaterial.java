/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author toze
 */
public class TypeOfMaterial {
    private int id;
    private String descricao;
    private int total;
    private int livres;
    private String imagem;
    
    public TypeOfMaterial(){
        this.id = -1;
        this.descricao = "";
        this.total = 0;
        this.livres = 0;
        this.imagem = "sem";
    }
    
    public TypeOfMaterial(String descricao){
        this.id = -1;
        this.descricao = descricao;
        this.total = 0;
        this.livres = 0;
        this.imagem = "sem";
    }
    
    public TypeOfMaterial(String descricao, int total){
        this.id = -1;
        this.descricao = descricao;
        this.total = total;
        this.livres = total;
        this.imagem = "sem";
    }
    
    public TypeOfMaterial(int id, String descricao, int total){
        this.id = id;
        this.descricao = descricao;
        this.total = total;
        this.livres = total;
        this.imagem = "sem";
    }
    
    public TypeOfMaterial(int id, String descricao, int total, int livres){
        this.id = id;
        this.descricao = descricao;
        this.total = total;
        this.livres = livres;
        this.imagem = "sem";
    }
    
    public TypeOfMaterial(int id, String descricao, int total, int livres, String imagem){
        this.id = id;
        this.descricao = descricao;
        this.total = total;
        this.livres = livres;
        this.imagem = imagem;
    }
    
    public TypeOfMaterial(int id, String descricao, int total, int livres, BufferedImage imagem, String extensao){
        this.id = id;
        this.descricao = descricao;
        this.total = total;
        this.livres = livres;
        ByteArrayOutputStream bi = new ByteArrayOutputStream();
        try {
            ImageIO.write(imagem, extensao, bi);
            this.imagem = Base64.getEncoder().encodeToString(bi.toByteArray());
        } catch (IOException ex) {
            this.imagem = "sem";
        }
    }
    
    public TypeOfMaterial(TypeOfMaterial material){
        this.id = material.getMaterialTypeID();
        this.descricao = material.getTypeOfMaterialName();
        this.total = material.getTotal();
        this.livres = material.getFree();
        this.imagem = material.getTypeOfMaterialImage();
    }
    
    @Override
    public String toString(){
        return this.descricao;
    }

    /**
     * @return the id
     */
    public int getMaterialTypeID() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setMaterialTypeID(int id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getTypeOfMaterialName() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setTypeOfMaterialName(String descricao) {
        this.descricao = descricao;
    }   

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the livres
     */
    public int getFree() {
        return livres;
    }

    /**
     * @param livres the livres to set
     */
    public void setFree(int livres) {
        this.livres = livres;
    }
    
    public void increaseFree(){
        if (this.livres <= this.total) this.livres = this.livres + 1;
    }
    
    public void increaseFree(int total){
        if (this.livres <= this.total) this.livres = this.livres + total;
    }
    
    public void decreaseFree(){
        if (this.livres >= 0) this.livres = this.livres - 1;
    }
    
    public void decreaseFree(int total){
        if (this.livres >= 0) this.livres = this.livres - total;
    }

    /**
     * @return the imagem
     */
    public String getTypeOfMaterialImage() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     * @param extensao
     */
    public void setTypeOfMaterialImage(BufferedImage imagem, String extensao) {
        ByteArrayOutputStream bi = new ByteArrayOutputStream();
        try {
            ImageIO.write(imagem, extensao, bi);
            this.imagem = Base64.getEncoder().encodeToString(bi.toByteArray());
        } catch (IOException ex) {
            this.imagem = "sem";
        }
    }
    
     public void setTypeOfMaterialImage(String imagem) {
        this.imagem = imagem;
    }
    
    public java.awt.Image transformIntoImage() {
        byte[] p = Base64.getDecoder().decode(this.imagem);
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(p);
        java.awt.Image image = null;
        try {
            image = ImageIO.read(in);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }   
}
