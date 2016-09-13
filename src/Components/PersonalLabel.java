/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import javax.swing.BorderFactory;

/**
 *
 * @author toze
 */
public class PersonalLabel {

    private int painelaltura;
    private int altura;
    private int painellargura;
    private int largura;
    private java.util.ArrayList<javax.swing.JLabel> labels;
    private int tamanho;
    private int X;
    private int Y;
    private javax.swing.JPanel panel;
    int resto;
    int andamento;
    private java.util.Set<Keys.Material> mlista;
    private java.util.ArrayList<Keys.Material> selecionados;
    boolean specialone;

    public PersonalLabel() {
        super();
    }

    public PersonalLabel(javax.swing.JPanel panel, int largura, int altura, java.util.Set<Keys.Material> mlista) {
        this.panel = panel;
        this.mlista = mlista;
        this.painellargura = (int) panel.getPreferredSize().getWidth();
        this.painelaltura = (int) panel.getPreferredSize().getHeight();
        this.selecionados = new java.util.ArrayList<>();
        labels = new java.util.ArrayList<>();
        if ((painelaltura / altura) > 2) {
            altura = (int) painelaltura / 2;
        }
        if (largura > (painellargura - 10)) {
            largura = painellargura - 10;
        }
        resto = 0;
        tamanho = 0;
        andamento = 0;
        specialone = false;
        this.altura = altura;
        this.largura = largura;
    }

    public void go(boolean cond, Keys.Material mat) {
        X = 10;
        Y = (int) Math.ceil(painelaltura / 2 - altura / 2) + 10;
        panel.removeAll();
        if (mat == null) {
            if (cond) {
                this.prepareSelectOnesForFirstTime();
            } else {
                this.prepareSelectOnes();
            }
        } else {
            this.selectTheSpecialOne(mat);
        }
        Keys.Material m;
        for (int i = 0; i < getDimension(); ++i) {
            if ((getX() + largura + 10) > panel.getPreferredSize().getWidth()) {
                resto = (int) Math.ceil((getX() + largura + 10) - painellargura);
            }
            m = selecionados.get(i);
            javax.swing.JLabel l = new javax.swing.JLabel();
            l.setSize(new java.awt.Dimension(largura, altura));
            l.setOpaque(true);
            l.setText(this.treatLongStrings(m.getDescription()));
            FileIOAux.ImageExtension bimage;
            if (!m.getMaterialImage().equals("sem")) {
                bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.transformFromBase64IntoImage(m.getMaterialImage()));
                l.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
            } else {
                String path = new File("").getAbsolutePath() + System.getProperty("file.separator") + "Resources" + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator") + m.getTypeOfMaterialImage() + ".png";
                bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.getImageFromFile(new File(path)));
                l.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
            }
            l.setBackground(java.awt.Color.WHITE);
            
            l.setVerticalTextPosition(javax.swing.JTextField.BOTTOM);
            l.setHorizontalTextPosition(javax.swing.JTextField.CENTER);
            l.setHorizontalAlignment(javax.swing.JTextField.CENTER);

            l.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, java.awt.Color.gray), BorderFactory.createLineBorder(java.awt.Color.BLACK, 1)));

            l.setPreferredSize(new java.awt.Dimension(largura, altura));
            l.setMinimumSize(new java.awt.Dimension(largura, altura));
            l.setBounds(getX(), Y, largura, altura);
            getLabels().add(l);
            panel.add(l);
            panel.revalidate();
            panel.repaint();
            X += largura + 10;
        }
        if (mat == null) {
            if ((getDimension() < andamento) && ((painellargura + resto) > painellargura)) {
                resto -= (int) Math.ceil(largura + 10) * (andamento - this.getDimension());
            } else if (getDimension() < andamento) {
                int res = selecionados.size() - (andamento - this.getDimension());
                for (int j = selecionados.size() - 1; j >= res; j--) {
                    System.out.println(j);
                    selecionados.remove(j);
                }
            }
        }
        panel.setPreferredSize(new java.awt.Dimension((painellargura + resto), painelaltura));
        andamento = getDimension();
    }

    private void prepareSelectOnes() {
        java.util.List<Keys.Material> m = new java.util.ArrayList<>(mlista);
        if (specialone) {
            selecionados = new java.util.ArrayList<>();
        } else {
            for (int h = 0; h < selecionados.size(); h++) {
                for (int k = 0; k < m.size(); k++) {
                    if (selecionados.get(h).compareTo(m.get(k)) == 0) {
                        m.remove(k);
                    }
                }
            }
        }
        int aleatorio;
        int tam = this.getDimension() - selecionados.size();
        for (int i = 0; i < tam; i++) {
            if (mlista.size() > 0) {
                aleatorio = new java.util.Random().nextInt(m.size());
                Keys.Material mat = m.get(aleatorio);
                selecionados.add(mat);
                m.remove(aleatorio);
            }
        }
        specialone = false;
    }
    
    
    public String treatLongStrings(String l) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        Font font = new Font("Cantarell", java.awt.Font.PLAIN, 12);
        int dimensao = (int) (font.getStringBounds(l, frc).getWidth());
        int tam = this.largura;
        if (dimensao > tam) {
            String [] palavras = l.split(" ");
            String texto = "<html><div style='text-align:center'>";
            int i = 0;
            while (i < palavras.length) {
                if (!palavras[i].equals("")) {
                    texto += palavras[i]+"<br/>";
                }
                i++;
            }
            texto += "</div></html>"; 
            return texto;   
        }
        return l;
    }

    private void prepareSelectOnesForFirstTime() {
        java.util.List<Keys.Material> m = new java.util.ArrayList<>(mlista);
        selecionados = new java.util.ArrayList<>();
        int aleatorio;
        int tam = this.getDimension();
        specialone = false;
        for (int i = 0; i < tam; i++) {
            if (mlista.size() > 0) {
                aleatorio = new java.util.Random().nextInt(m.size());
                Keys.Material mat = m.get(aleatorio);
                selecionados.add(mat);
                m.remove(aleatorio);
            }
        }
    }

    private void selectTheSpecialOne(Keys.Material mat) {
        selecionados = new java.util.ArrayList<>();
        mlista = new java.util.TreeSet<>();
        mlista.add(mat);
        selecionados.add(mat);
        specialone = true;
    }

    public void randomXY() {
        X = new java.util.Random().nextInt(painellargura - largura - 10) + 10;
        Y = new java.util.Random().nextInt((painelaltura / 2) - 10) + 10;
    }

    public javax.swing.JLabel getLabel(int i) {
        return getLabels().get(i);
    }

    /**
     * @return the labels
     */
    public java.util.ArrayList<javax.swing.JLabel> getLabels() {
        return labels;
    }

    /**
     * @param labels the labels to set
     */
    public void setLabels(java.util.ArrayList<javax.swing.JLabel> labels) {
        this.labels = labels;
    }

    /**
     * @return the tamanho
     */
    public int getDimension() {
        return tamanho;
    }

    /**
     * @param tamanho the tamanho to set
     */
    public void set(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * @return the X
     */
    public int getX() {
        return X;
    }

    public void setNewList(java.util.Set<Keys.Material> mlista) {
        this.mlista = mlista;
    }

    public java.util.List<Keys.Material> getSelectedOnes() {
        return selecionados;
    }

}
