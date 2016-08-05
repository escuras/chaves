/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.Component;
import java.awt.Container;

/**
 *
 * @author toze
 */
public class SplitElement {

    private final double factorX;
    private final double factorY;
    private final Component com;
    private final double largura;
    private final double altura;
    private final boolean sit;
    private final Component[] comp;

    public SplitElement() {
        this.com = null;
        this.factorX = 0.0;
        this.factorY = 0.0;
        this.largura = 0;
        this.altura = 0;
        this.sit = true;
        comp = new Component[]{};

    }

    public SplitElement(Component com, double factorX, double factorY, boolean sit) {
        this.com = com;
        this.factorX = factorX;
        this.factorY = factorY;
        this.largura = com.getWidth();
        this.altura = com.getHeight();
        this.sit = sit;
        if ((com instanceof Container)) {
            Container sub = (Container) com;
            this.comp = sub.getComponents();
        } else {
            this.comp = new Component[]{};
        }
    }

    public void putBounds(double largura_, double altura_) {
        if (comp.length > 0) {
            int i = 0;
            java.util.List<SplitElement> elementos = new java.util.ArrayList<>();
            while (i < comp.length) {
                elementos.add(new SplitElement(comp[i], factorX, factorY, true));
                i++;
            }
            i = 0;
            while (i < elementos.size()) {
                elementos.get(i).putBounds(largura_, altura_);
                i++;
            }
        }
        int x = com.getX();
        int y = com.getY();

        int afinal = (int) altura_ - (y + 116);
        if (sit) {
            this.com.setBounds(new java.awt.Rectangle(x, y, (int) largura, afinal));
        } else {
            int val = (int) largura_ - (x + 12);
            this.com.setBounds(new java.awt.Rectangle(x, y, val, afinal));
        }
    }
}
