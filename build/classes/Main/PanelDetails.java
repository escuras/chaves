/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author toze
 */
public class PanelDetails extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private Color color;
    private Color subTitleColor;
    private Color textColor;
    private Color titleColor;
    private String[] titulos;
    private String titulo;
    private Langs.Locale lingua;
    private String[] resultados;
    private int intervalo;
    private SVGDiagram diagrama;
    private JPanel panel;
    private String nome_imagem;

    public PanelDetails(Langs.Locale lingua, Color color, String imagem) {
        super();
        this.color = color;
        this.titleColor = DEFAULT_COLOR;
        this.textColor = DEFAULT_COLOR;
        this.subTitleColor = DEFAULT_COLOR;
        this.lingua = lingua;
        this.titulo = "";
        this.nome_imagem = imagem;
        this.intervalo = 10;
        this.titulos = new String[]{};
        this.resultados = new String[]{};
        SVGUniverse svg = new SVGUniverse();
        InputStream is = this.getClass().getResourceAsStream("Images/" + imagem + ".svg");
        try {
            diagrama = svg.getDiagram(svg.loadSVG(is, imagem));
        } catch (IOException ex) {}
    }

    public PanelDetails(Color color, String titulo, String[] titulos, String[] resultados, Langs.Locale lingua, String imagem) {
        super();
        this.color = color;
        this.titleColor = DEFAULT_COLOR;
        this.textColor = DEFAULT_COLOR;
        this.subTitleColor = DEFAULT_COLOR;
        this.lingua = lingua;
        this.titulo = titulo;
        this.intervalo = 10;
        this.nome_imagem = imagem;
        if (titulos.length == resultados.length) {
            this.titulos = titulos;
            this.resultados = resultados;
        } else {
            this.titulos = new String[]{};
            this.resultados = new String[]{};
        }
        SVGUniverse svg = new SVGUniverse();
        InputStream is = this.getClass().getResourceAsStream("Images/" + imagem + ".svg");
        try {
            diagrama = svg.getDiagram(svg.loadSVG(is, imagem));
        } catch (IOException ex) {}

    }

    public void create() {
        if (resultados.length > 0) {
            JLabel ltitulo = new JLabel();
            this.setMinimumSize(new java.awt.Dimension(1, 1));
            this.setPreferredSize(new java.awt.Dimension(240, 400));
            this.setBackground(color);
            ltitulo.setForeground(this.titleColor);
            ltitulo.setFont(new java.awt.Font("Cantarell", 1, 14));
            ltitulo.setText(lingua.translate(titulo));
            JLabel[] paineis = new JLabel[titulos.length];
            JLabel[] paineis2 = new JLabel[titulos.length];
            int i = 0;
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            Group grupo = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
            Group grupo2 = layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE));
            grupo.addComponent(ltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE);
            grupo2.addGap(11);
            grupo2.addComponent(ltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE);
            grupo2.addGap(11, 11, 11);
            while (i < titulos.length) {
                paineis[i] = new JLabel();
                paineis[i].setForeground(this.subTitleColor);
                paineis[i].setFont(new java.awt.Font("Cantarell", java.awt.Font.BOLD, 13));
                paineis[i].setText(lingua.translate(titulos[i]));
                paineis2[i] = new JLabel();
                paineis2[i].setForeground(this.textColor);
                paineis2[i].setFont(new java.awt.Font("Cantarell", java.awt.Font.PLAIN, 13));
                paineis2[i].setOpaque(true);
                paineis2[i].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                paineis2[i].setText(lingua.translate(resultados[i]));
                grupo.addComponent(paineis[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
                grupo.addComponent(paineis2[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
                grupo2.addComponent(paineis[i], javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE);
                grupo2.addGap(3, 5, 7);
                grupo2.addComponent(paineis2[i], javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE);
                grupo2.addGap(5 + intervalo, 7 + intervalo, 9 + intervalo);
                i++;
            }
            layout.setVerticalGroup(grupo2);
            layout.setHorizontalGroup(grupo);
        } else {
            panel = new PanelDetails(lingua, color, nome_imagem) {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    this.setMinimumSize(new java.awt.Dimension(1, 1));
                    this.setPreferredSize(new java.awt.Dimension(240, 400));
                    this.setBackground(color);
                    Graphics2D g2 = (Graphics2D) g;
                    try {
                        AffineTransform at = new AffineTransform();
                        at.setToScale(this.getWidth() / (diagrama.getWidth() + 10), this.getWidth() / (diagrama.getWidth() + 10));
                        g2.setColor(Color.BLACK);
                        g2.transform(at);
                        g2.translate(5, 6);
                        diagrama.setIgnoringClipHeuristic(true);
                        diagrama.render(g2);
                    } catch (SVGException ex) {
                        Logger.getLogger(PanelDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            panel.setBackground(color);
        }

    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the titulos
     */
    public String[] getSubTitles() {
        return titulos;
    }

    /**
     * @return the lingua
     */
    public Langs.Locale getLanguage() {
        return lingua;
    }

    /**
     * @return the interval
     */
    public int getInterval() {
        return intervalo;
    }

    /**
     * @param intervalo the interval to set
     */
    public void setInterval(int intervalo) {
        this.intervalo = intervalo;
    }

    /**
     * @return the resultados
     */
    public String[] getResults() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResults(String[] resultados) {
        this.resultados = resultados;
    }

    /**
     * @return the titulo
     */
    public String getTitle() {
        return titulo;
    }

    /**
     * @param titulo the title to set
     */
    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param titulos the subtitles to set
     */
    public void setSubTitles(String[] titulos) {
        this.titulos = titulos;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param lingua the lingua to set
     */
    public void setLanguage(Langs.Locale lingua) {
        this.lingua = lingua;
    }

    /**
     * @return the subTitleColor
     */
    public Color getSubTitleColor() {
        return subTitleColor;
    }

    /**
     * @param subTitleColor the subTitleColor to set
     */
    public void setSubTitleColor(Color subTitleColor) {
        this.subTitleColor = subTitleColor;
    }

    /**
     * @return the textColor
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * @param textColor the textColor to set
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * @return the titleColor
     */
    public Color getTitleColor() {
        return titleColor;
    }

    /**
     * @param titleColor the titleColor to set
     */
    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
    }

    public JPanel alternativePanel() {
        return this.panel;
    }
}
