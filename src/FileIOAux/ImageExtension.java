/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileIOAux;

import java.awt.image.BufferedImage;

/**
 *
 * @author toze
 */
public class ImageExtension {
    
    private String extensao;
    private BufferedImage imagem;
    
    public ImageExtension(BufferedImage imagem, String extensao) {
        this.imagem = imagem;
        this.extensao = extensao;
    }
    
    public ImageExtension(BufferedImage imagem) {
        this.imagem = imagem;
        this.extensao = "";
    }
    
    public String getExtension(){
        return extensao;
    }
    
    public void setExtension(String extensao){
        this.extensao = extensao;
    }
    
    public BufferedImage getImage(){
        return this.imagem;
    }
    
    public void setImage(BufferedImage bimage){
        this.imagem = bimage;
    }
    
}
