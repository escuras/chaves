/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.Graphics2D;
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
public class Material extends TypeOfMaterial {
    private String codigo;
    private String descricao;
    private String caracteristicas;
    private boolean estado;
    private String imagem;
    
    public Material(){
        super();
        this.codigo = "";
        this.descricao = "";
        this.caracteristicas = "";
        this.estado = false;
        this.imagem = "";
    }
    
    public Material(TypeOfMaterial m, String codigo, String descricao,boolean estado){
        super(m);
        this.codigo = codigo;
        this.descricao = descricao;
        this.caracteristicas = "";
        this.estado = estado;
        this.imagem = "";
    }
    
    public Material(TypeOfMaterial m, String codigo, String descricao, String caracteristicas, boolean estado){
        super(m);
        this.codigo = codigo;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
        this.imagem = "";
    }
    
    public Material(TypeOfMaterial m, String codigo, String descricao, String caracteristicas, String imagem, boolean estado){
        super(m);
        this.codigo = codigo;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
        this.imagem = imagem;
    }
    
    public Material(TypeOfMaterial m, String codigo, String descricao, String caracteristicas, BufferedImage imagem, String extensao, int largura, int altura, boolean estado){
        super(m);
        this.codigo = codigo;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
        int l = imagem.getWidth();
        int a = imagem.getHeight();
        BufferedImage img = new BufferedImage(largura, altura, imagem.getType());
        Graphics2D g = img.createGraphics();
        g.drawImage(imagem, 0, 0, largura, altura, 0, 0, l, a, null);
        g.dispose();
        ByteArrayOutputStream bi = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, extensao, bi);
            this.imagem = Base64.getEncoder().encodeToString(bi.toByteArray());
        } catch (IOException ex) {
            this.imagem = "";
        }
    }
    
    public Material(Material m){
        super(m);
        this.codigo = m.getCodeOfMaterial();
        this.descricao = m.getDescription();
        this.caracteristicas = m.getFeatures();
        this.estado = m.isLoaned();
        this.imagem = m.getMaterialImage();
    }

    /**
     * @return the id
     */
    public String getCodeOfMaterial() {
        return codigo;
    }

    /**
     * @param codigo
     */
    public void setCodeOfMaterial(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricao
     */

    public String getDescription() {
        return descricao;
    }
    
     public TypeOfMaterial getTypeOfMaterial() {
        return this;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescription(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the caracteristicas
     */
    public String getFeatures() {
        return caracteristicas;
    }

    /**
     * @param caracteristicas the caracteristicas to set
     */
    public void setFeatures(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    @Override
    public String toString(){
        return this.descricao + "\n\n" + this.caracteristicas;
    }

    /**
     * @return the estado
     */
    public boolean isLoaned() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setLoaned(boolean estado) {
        this.estado = estado;
    }
    
     /**
     * @return the imagem
     */
    
    public String getMaterialImage() {
        return this.imagem;
    }

    /**
     * @param imagem the imagem to set
     * @param extensao
     */
    public void setMaterialImage(BufferedImage imagem, String extensao) {
        ByteArrayOutputStream bi = new ByteArrayOutputStream();
        try {
            ImageIO.write(imagem, extensao, bi);
            this.imagem = Base64.getEncoder().encodeToString(bi.toByteArray());
        } catch (IOException ex) {
            this.imagem = "";
        }
    }
    
    @Override
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
