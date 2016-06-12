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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
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
import javax.swing.JSplitPane;

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
    private String titulo_imagem;
    private JSplitPane tamanho_auxiliar;

    public PanelDetails(Langs.Locale lingua, Color color, String imagem, String titulo_imagem) {
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
        this.titulo_imagem = titulo_imagem;
        try {
            diagrama = svg.getDiagram(svg.loadSVG(is, imagem));
        } catch (IOException ex) {
        }
    }

    public PanelDetails(Color color, String titulo, String[] titulos, String[] resultados, Langs.Locale lingua, String imagem, String titulo_imagem, JSplitPane tamanho_auxiliar) {
        super();
        this.color = color;
        this.titleColor = DEFAULT_COLOR;
        this.textColor = DEFAULT_COLOR;
        this.subTitleColor = DEFAULT_COLOR;
        this.lingua = lingua;
        this.titulo = titulo;
        this.intervalo = 10;
        this.tamanho_auxiliar = tamanho_auxiliar;
        this.titulo_imagem = titulo_imagem;
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
        System.out.println(is.toString());
        try {
            diagrama = svg.getDiagram(svg.loadSVG(is, imagem));
        } catch (IOException ex) {
        }

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
            ltitulo.setHorizontalAlignment(javax.swing.JLabel.CENTER);
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
            AffineTransform affinetransform = new AffineTransform();     
            FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
            Font font = new Font("Cantarell", java.awt.Font.PLAIN, 13);
            int texto = 0;
            String auxiliar;
            while (i < titulos.length) {
                paineis[i] = new JLabel();
                paineis[i].setForeground(this.subTitleColor);
                paineis[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                paineis[i].setFont(new java.awt.Font("Cantarell", java.awt.Font.BOLD, 13));
                paineis[i].setText(lingua.translate(titulos[i]));
                paineis2[i] = new JLabel();
                paineis2[i].setForeground(this.textColor);
                paineis2[i].setFont(new java.awt.Font("Cantarell", java.awt.Font.PLAIN, 13));
                paineis2[i].setOpaque(true);
                paineis2[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                auxiliar = lingua.translate(resultados[i]);
                texto = (int)(font.getStringBounds(auxiliar, frc).getWidth());
                if (texto > tamanho_auxiliar.getWidth() -50)  {
                    while (texto >= tamanho_auxiliar.getWidth()-50) {
                        auxiliar = auxiliar.substring(0, auxiliar.length() -1);
                        System.out.println(texto);
                        texto = (int)(font.getStringBounds(auxiliar, frc).getWidth());
                    }
                    auxiliar = auxiliar+"...";
                }
                paineis2[i].setText(auxiliar);
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
            panel = new PanelDetails(lingua, color, nome_imagem, titulo_imagem) {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    this.setMinimumSize(new java.awt.Dimension(1, 1));
                    this.setPreferredSize(new java.awt.Dimension(240, 300));
                    this.setBackground(color);
                    Graphics2D g2 = (Graphics2D) g;
                    if (diagrama != null) {
                        try {
                            AffineTransform at = new AffineTransform();
                            at.setToScale(this.getWidth() / (diagrama.getWidth() + 10), this.getWidth() / (diagrama.getWidth() + 10));
                            g2.setColor(Color.BLACK);
                            g2.transform(at);
                            g2.translate(5, 6);
                            diagrama.setIgnoringClipHeuristic(true);
                            diagrama.render(g2);
                            g2.setFont(new Font("Cantarell", Font.BOLD, 8));
                            int largura = g2.getFontMetrics().stringWidth(getImageTitle());
                            int i = 8;
                            while (largura > diagrama.getWidth()) {
                                g2.setFont(new Font("Cantarell", Font.BOLD, i));
                                largura = g2.getFontMetrics().stringWidth(getImageTitle());
                                i--;
                            }
                            g2.drawString(getImageTitle(), diagrama.getWidth()/2-(largura/2), 40);
                        } catch (SVGException ex) {
                            Logger.getLogger(PanelDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
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
    public boolean isShowingImage(){
        return (this.diagrama != null);
    }

    /**
     * @return the titulo_imagem
     */
    public String getImageTitle() {
        return titulo_imagem;
    }

    /**
     * @param titulo_imagem the titulo_imagem to set
     */
    public void setImageTitle(String titulo_imagem) {
        this.titulo_imagem = titulo_imagem;
    }
}
