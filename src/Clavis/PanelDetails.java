/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author toze
 */
public class PanelDetails extends JPanel {

    private static final long serialVersionUID = 1L;
    private Color color;
    private Color subTitleColor;
    private Color textColor;
    private Color titleColor;
    private String[] titulos;
    private Langs.Locale lingua;
    private String[] resultados;
    private int intervalo;
    private SVGDiagram diagrama;
    private JPanel panel;
    private String nome_imagem;
    private String imagemrecurso;
    private String tiporecurso;
    private JPanel tamanho_auxiliar;

    public PanelDetails(Langs.Locale lingua, Color color, Color foreground, String imagem, String tiporecurso) {
        super();
        this.color = color;
        this.titleColor = foreground;
        this.textColor = foreground;
        this.subTitleColor = foreground;
        this.lingua = lingua;
        this.nome_imagem = imagem;
        this.intervalo = 10;
        this.titulos = new String[]{};
        this.resultados = new String[]{};
        SVGUniverse svg = new SVGUniverse();
        InputStream is;
        if (!imagem.equals("sem")) {
            is = this.getClass().getResourceAsStream("Images/Materiais/" + imagem + ".svg");
        } else {
            is = this.getClass().getResourceAsStream("Images/Materiais/sem.svg");
        }
        this.imagemrecurso = "";
        this.tiporecurso = tiporecurso;
        try {
            diagrama = svg.getDiagram(svg.loadSVG(is, imagem));
        } catch (IOException ex) {
        }

    }

    public PanelDetails(Color color, Color foreground, String[] titulos, String[] resultados, Langs.Locale lingua, String imagem, String imagemrecurso, String tiporecurso, JPanel tamanho_auxiliar) {
        super();
        this.color = color;
        this.titleColor = foreground;
        this.textColor = foreground;
        this.subTitleColor = foreground;
        this.lingua = lingua;
        this.intervalo = 10;
        this.tamanho_auxiliar = tamanho_auxiliar;
        this.imagemrecurso = imagemrecurso;
        this.tiporecurso = tiporecurso;
        this.nome_imagem = imagem;
        if (titulos.length == resultados.length) {
            this.titulos = titulos;
            this.resultados = resultados;
        } else {
            this.titulos = new String[]{};
            this.resultados = new String[]{};
        }
        SVGUniverse svg = new SVGUniverse();
        InputStream is;
        if (!imagem.equals("sem")) {
            is = this.getClass().getResourceAsStream("Images/Materiais/" + imagem + ".svg");
        } else {
            is = this.getClass().getResourceAsStream("Images/Materiais/sem.svg");
        }
        try {
            diagrama = svg.getDiagram(svg.loadSVG(is, imagem));
        } catch (IOException ex) {
        }

    }

    public void create() {
        if (resultados.length > 0) {
            this.setBackground(color);
            JLabel[] paineis = new JLabel[titulos.length + 1];
            JLabel[] paineis2 = new JLabel[titulos.length + 1];
            int i = 1;
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            Group grupo = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
            Group grupo2 = layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE));
            grupo2.addGap(11);
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
            Font font = new Font("Noto", java.awt.Font.PLAIN, 14);
            int texto;
            String auxiliar;
            paineis[0] = new JLabel();
           
            if (!this.imagemrecurso.equals("sem")) {
                paineis[0].setIcon(new ImageIcon(FileIOAux.ImageAux.resize(FileIOAux.ImageAux.transformFromBase64IntoImage(this.imagemrecurso), 100, 80)));
            } else {
                File file = new File(new File("").getAbsolutePath()
                        + System.getProperty("file.separator")
                        + "Resources" + System.getProperty("file.separator")
                        + "Images" + System.getProperty("file.separator")
                        + this.nome_imagem + ".png");
                try {
                    BufferedImage bt = FileIOAux.ImageAux.resize(ImageIO.read(file), 100, 80);
                    paineis[0].setIcon(new ImageIcon(bt));
                } catch (IOException ex) {
                    Logger.getLogger(PanelDetails.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            grupo2.addGap(1, 20, 30);
            paineis[0].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createLineBorder(new Color(254, 254, 254))));
            paineis[0].setHorizontalAlignment(javax.swing.JLabel.CENTER);
            grupo.addComponent(paineis[0], 100, 100, 100);
            grupo2.addComponent(paineis[0], javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE);
            grupo2.addGap(1, 30, 30);
            String[] multipla;
            while (i < titulos.length + 1) {
                paineis[i] = new JLabel();
                paineis[i].setForeground(this.subTitleColor);
                paineis[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                paineis[i].setFont(new java.awt.Font("Noto", java.awt.Font.CENTER_BASELINE, 14));
                paineis[i].setHorizontalAlignment(javax.swing.JLabel.CENTER);
                auxiliar = lingua.translate(titulos[i - 1]);
                texto = (int) (font.getStringBounds(auxiliar, frc).getWidth());
                if (texto > tamanho_auxiliar.getWidth() - 100) {
                    while (texto >= tamanho_auxiliar.getWidth() - 100) {
                        if (auxiliar.length() > 0) {
                            auxiliar = auxiliar.substring(0, auxiliar.length() - 1);
                            texto = (int) (font.getStringBounds(auxiliar, frc).getWidth());
                        }
                    }
                    auxiliar = auxiliar + "... ";
                }
                paineis[i].setText(auxiliar);
                paineis2[i] = new JLabel();
                paineis2[i].setForeground(new Color(50, 50, 50));
                paineis2[i].setBackground(this.color);
                paineis2[i].setHorizontalAlignment(javax.swing.JLabel.CENTER);
                paineis2[i].setFont(new java.awt.Font("Noto", java.awt.Font.PLAIN, 14));
                paineis2[i].setOpaque(true);
                paineis2[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                multipla = resultados[i - 1].split(":::");
                auxiliar = lingua.translate(multipla[0]);
                if (multipla.length > 1) {
                    Components.PopUpMenu pop = new Components.PopUpMenu(multipla, lingua);
                    pop.create();
                    paineis[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (!pop.isShowing()) {
                                pop.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            pop.show(e.getComponent(), e.getX(), e.getY());
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            pop.setVisible(false);
                        }
                    });
                    paineis2[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (!pop.isShowing()) {
                                pop.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            e.getComponent().setForeground(new Color(145, 145, 145));
                            pop.show(e.getComponent(), e.getX(), e.getY());
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            e.getComponent().setForeground(textColor);
                            pop.setVisible(false);
                        }
                    });
                }
                texto = (int) (font.getStringBounds(auxiliar, frc).getWidth());
                if (texto > tamanho_auxiliar.getWidth() - 100) {
                    while (texto >= tamanho_auxiliar.getWidth() - 100) {
                        auxiliar = auxiliar.substring(0, auxiliar.length() - 1);
                        texto = (int) (font.getStringBounds(auxiliar, frc).getWidth());
                    }
                    auxiliar = auxiliar + "... ";
                }
                paineis2[i].setText(auxiliar);
                grupo.addComponent(paineis[i], javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE);
                grupo.addComponent(paineis2[i], javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE);
                grupo2.addComponent(paineis[i], javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE);
                grupo2.addGap(1, 2, 3);
                grupo2.addComponent(paineis2[i], javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE);
                grupo2.addGap(24 + intervalo, 28 + intervalo, 32 + intervalo);
                i++;
            }
            layout.setVerticalGroup(grupo2);
            layout.setHorizontalGroup(grupo);
        } else {
            panel = new PanelDetails(lingua, color, textColor, nome_imagem, tiporecurso) {
                private static final long serialVersionUID = 1L;

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
                        } catch (SVGException ex) {
                            Logger.getLogger(PanelDetails.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            };
            panel.setBackground(color);
        }
        this.setPreferredSize(new Dimension(110, (int) this.getPreferredSize().getHeight()));
    }

    public void destroy() {
        this.panel = null;
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

    public boolean isShowingImage() {
        return (this.diagrama != null);
    }

}
