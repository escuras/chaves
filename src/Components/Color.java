/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;


/**
 *
 * @author toze
 */
public class Color extends java.awt.Color {

    private static final long serialVersionUID = 432L;
    public Color(int r, int g, int b) {
        super(r, g, b);
    }

    public Color(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public Color(Color cor) {
        super(cor.getRed(), cor.getGreen(), cor.getBlue(), cor.getAlpha());
    }

 
    

}
