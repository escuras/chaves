/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Keys;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author toze
 */
public class Material extends TypeOfMaterial implements Comparable<Material>{
    private int id;
    private String codigo;
    private String descricao;
    private boolean estado;
    private String imagem;
    private Set<Keys.Feature> caracteristicas;
    
    public Material(){
        super();
        this.id = -1;
        this.codigo = "";
        this.descricao = "";
        this.estado = false;
        this.imagem = "sem";
        caracteristicas = new HashSet<>();
    }
    
    public Material(TypeOfMaterial m, String codigo, String descricao,boolean estado){
        super(m);
        this.id = -1;
        this.codigo = codigo;
        this.descricao = descricao;
        this.estado = estado;
        this.imagem = "sem";
        caracteristicas = new HashSet<>();
    }
    
    public Material(int id, TypeOfMaterial m, String codigo, String descricao,boolean estado){
        super(m);
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.estado = estado;
        this.imagem = "sem";
        caracteristicas = new HashSet<>();
    }
    

    public Material(TypeOfMaterial m, String codigo, String descricao, String imagem, boolean estado){
        super(m);
        this.id = -1;
        this.codigo = codigo;
        this.descricao = descricao;
        this.estado = estado;
        this.imagem = imagem;
        caracteristicas = new HashSet<>();
    }
    
    public Material(int id, TypeOfMaterial m, String codigo, String descricao, String imagem, boolean estado){
        super(m);
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.estado = estado;
        this.imagem = imagem;
        caracteristicas = new HashSet<>();
    }
    
    public Material(int id, TypeOfMaterial m, String codigo, String descricao, BufferedImage imagem, String extensao, int largura, int altura, boolean estado){
        super(m);
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
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
            this.imagem = "sem";
        }
        caracteristicas = new HashSet<>();
    }
    
    public Material(TypeOfMaterial m, String codigo, String descricao, BufferedImage imagem, String extensao, int largura, int altura, boolean estado){
        super(m);
        this.id = -1;
        this.codigo = codigo;
        this.descricao = descricao;
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
            this.imagem = "sem";
        }
        caracteristicas = new HashSet<>();
    }
    
    public Material(Material m){
        super(m);
        this.id = m.getId();
        this.codigo = m.getCodeOfMaterial();
        this.descricao = m.getDescription();
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

    
    @Override
    public String toString(){
        return this.descricao;
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
    
    public void setMaterialImage(String imagem){
        this.imagem = imagem;
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
            this.imagem = "sem";
        }
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

    @Override
    public int compareTo(Material o) {
        if ((this.getDescription().matches("\\d+")) && (o.getDescription().matches("\\d+"))) {
            String texto = this.getDescription();
            String texto2 = o.getDescription();
            while (texto.charAt(0) == '0') {
                texto = texto.replaceFirst("0", "");
            }
            while (texto2.charAt(0) == '0') {
                texto2 = texto2.replaceFirst("0", "");
            }
            int val1 = Integer.parseInt(texto);
            int val2 = Integer.parseInt(texto2);
            return (val1 - val2);
        }
        int valor = this.getDescription().toLowerCase().compareTo(o.getDescription().toLowerCase());
        if (valor == 0) {
            valor = this.getCodeOfMaterial().compareTo(o.getCodeOfMaterial());
        }
        if (valor == 0) {
            valor = this.getId() - o.getId();
        }
        return valor;
    }

    /**
     * @return the caracteristicas
     */
    public Set<Keys.Feature> getFeatures() {
        return caracteristicas;
    }

    /**
     * @param caracteristicas the caracteristicas to set
     */
    public void setFeatures(Set<Keys.Feature> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    public void addFeature(Keys.Feature e){
        caracteristicas.add(e);
    }
    
    public void removeFeature(Keys.Feature e){
        caracteristicas.remove(e);
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
    
    
}
