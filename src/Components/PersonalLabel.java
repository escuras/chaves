/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

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
    Langs.Locale lingua;
    boolean vazia;
    private String url;

    public PersonalLabel() {
        super();
    }

    public PersonalLabel(javax.swing.JPanel panel, int largura, int altura, java.util.Set<Keys.Material> mlista, String url) {
        this.panel = panel;
        this.mlista = mlista;
        this.url = url;
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
        lingua = Clavis.KeyQuest.getLanguage();
        if (lingua == null) {
            lingua = Langs.Locale.getLocale_pt_PT();
        }
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
        if ((mlista.size() > 0) && (getDimension() > 0)) {
            Keys.Material m;
            for (int i = 0; i < getDimension(); i++) {
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
                if (Clavis.Windows.WRequest.isMaterialInLateState(m, url)) {
                    l.setBackground(new Color(255, 204, 200));
                } else {
                    l.setBackground(java.awt.Color.WHITE);
                }
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
                this.addReplaceMaterialPossibility(l, i, m);
            }
            if ((mat == null) && (mlista.size() > 0)) {
                if ((getDimension() < andamento) && ((painellargura + resto) > painellargura)) {
                    resto -= (int) Math.ceil(largura + 10) * (andamento - this.getDimension());
                } else if (getDimension() < andamento) {
                    int res = selecionados.size() - (andamento - this.getDimension());
                    for (int j = selecionados.size() - 1; j >= res; j--) {
                        selecionados.remove(j);
                    }
                }
            }
            panel.setPreferredSize(new java.awt.Dimension((painellargura + resto), painelaltura));
            andamento = getDimension();
        }
        panel.repaint();
    }

    private void addReplaceMaterialPossibility(javax.swing.JLabel label, int situo, Keys.Material material) {
        javax.swing.JPanel panell = new javax.swing.JPanel(null);
        panell.setPreferredSize(new java.awt.Dimension(300, 350));
        lingua = Clavis.KeyQuest.getLanguage();
        if (lingua == null) {
            lingua = Langs.Locale.getLocale_pt_PT();
        }
        boolean esta_na_lista = false;
        javax.swing.JList lmater = new javax.swing.JList<>();
        if (mlista.isEmpty()) {
            vazia = true;
            DefaultListModel<String> modelo = new DefaultListModel<>();
            modelo.addElement(lingua.translate("A lista está vazia") + "!");
            lmater.setModel(modelo);
        } else {
            DefaultListModel<Keys.Material> modelo = new DefaultListModel<>();
            vazia = true;
            for (Keys.Material m : mlista) {
                for (Keys.Material s : selecionados) {
                    if ((s.getDescription().equals(m.getDescription())) && (s.getId() == m.getId()) && (s.getCodeOfMaterial().equals(m.getCodeOfMaterial())) && (s.getMaterialTypeID() == m.getMaterialTypeID())) {
                        esta_na_lista = true;
                    }
                }
                if (!esta_na_lista) {
                    m = new Keys.Material(m) {
                        @Override
                        public String toString() {
                            return this.getTypeOfMaterialName() + " " + this.getDescription();
                        }
                    };
                    vazia = false;
                    modelo.addElement(m);
                }
                esta_na_lista = false;
            }
            lmater.setModel(modelo);
        }
        lmater.setPreferredSize(new Dimension(280, 0));
        lmater.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lmater.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lmater.setCellRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 2L;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = (javax.swing.JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(150, 25));
                if (isSelected) {
                    label.setBackground(java.awt.Color.GRAY);
                    label.setForeground(java.awt.Color.WHITE);
                    label.setBorder(BorderFactory.createLineBorder(java.awt.Color.black, 1));
                }
                return label;
            }
        });
        int altu = 20;
        if (!vazia) {
            DefaultListModel<Keys.Material> modelo = (DefaultListModel) lmater.getModel();
            for (int i = 0; i < modelo.getSize(); i++) {
                Rectangle r = lmater.getCellBounds(i, i);
                altu += r.getHeight();
                lmater.setPreferredSize(new Dimension(280, altu));
            }
        }
        lmater.setBackground(new java.awt.Color(250, 250, 250));
        javax.swing.JScrollPane scrol = new javax.swing.JScrollPane(lmater);
        scrol.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(java.awt.Color.BLACK, 3, 0.5f, 6, false, true, true, true), BorderFactory.createLineBorder(java.awt.Color.BLACK)));
        scrol.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrol.setPreferredSize(new Dimension(300, 320));
        scrol.setBounds(0, 30, 300, 320);
        panell.add(scrol);
        Components.MessagePane me = new Components.MessagePane(label, Components.MessagePane.ACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Substituição de recurso"), 400, 500, panell, "", new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
        String[] titulos = {lingua.translate("substituir")};
        ActionListener[] act = new ActionListener[1];
        act[0] = (ActionEvent e) -> {
            if (!vazia) {
                me.refreshAction(new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
            } else {
                me.refreshAction(new String[]{lingua.translate("Voltar")});
            }
            int val = me.showMessage();
            if (!vazia) {
                if (val == 1) {
                    int sel = lmater.getSelectedIndex();
                    Keys.Material ma = (Keys.Material) lmater.getModel().getElementAt(sel);
                    selecionados.set(situo, ma);
                    label.setText(this.treatLongStrings(ma.getDescription()));
                    FileIOAux.ImageExtension bimage;
                    if (!ma.getMaterialImage().equals("sem")) {
                        bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.transformFromBase64IntoImage(ma.getMaterialImage()));
                        label.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
                    } else {
                        String path = new File("").getAbsolutePath() + System.getProperty("file.separator") + "Resources" + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator") + ma.getTypeOfMaterialImage() + ".png";
                        bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.getImageFromFile(new File(path)));
                        label.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
                    }
                    if (specialone) {
                        Clavis.Windows.WRequest.updateComboMaterialOnSpecialRequest(ma);
                    }
                }
            }
        };
        Components.PopUpMenu pop = new Components.PopUpMenu(titulos, act);
        pop.create();
        String envia;
        int tipo;
        if (Clavis.Windows.WRequest.isMaterialInLateState(material, url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            Keys.Request re = db.getCurrentRequest(material);
            db.close();
            envia = "<html><div style='text-align:center'>" + lingua.translate("O recurso") + " \"" + lingua.translate(material.getTypeOfMaterialName()).toLowerCase() + " " + lingua.translate(material.getDescription()) + "\"<br/>" + lingua.translate("continua emprestado") + " (" + lingua.translate("situação de atraso") + "). <br/>"
                    + lingua.translate("Data de devolução") + ": " + re.getEndDate().toString()+" ("+re.getTimeEnd().toString(0)+")</div></html>";
            tipo = Components.MessagePane.AVISO;
        } else {
            if (DataBase.DataBase.testConnection(url)) {
                DataBase.DataBase db = new DataBase.DataBase(url);
                Keys.Request re = db.getNextRequest(material);
                db.close();
                if (re.getId() > 0) {
                    envia = "<html><div style='text-align:center'>" + lingua.translate("O recurso") + " \"" + lingua.translate(material.getTypeOfMaterialName()).toLowerCase() + " " + lingua.translate(material.getDescription()) + "\" " + lingua.translate("está disponível") + " <br/>" + lingua.translate("até") + " " + re.getBeginDate().toString() + " (" + re.getTimeBegin().toString(0) + "). </div></html>";
                } else {
                    envia = "<html><div style='text-align:center'>" + lingua.translate("O recurso") + " \"" + lingua.translate(material.getTypeOfMaterialName()).toLowerCase() + " " + lingua.translate(material.getDescription()) + "\" " + lingua.translate("está disponível") + ".<br/> " + lingua.translate("Não tem requisições futuras") + ".</div></html>";
                }
            } else {
                envia = "<html><div style='text-align:center'>" + lingua.translate("O recurso") + " \"" + lingua.translate(material.getTypeOfMaterialName()).toLowerCase() + " " + lingua.translate(material.getDescription()) + "\" " + lingua.translate("está disponível") + ".</div></html>";
            }
            tipo = Components.MessagePane.INFORMACAO;
        }
        Components.MessagePane mensagem = new Components.MessagePane(null, tipo, Clavis.KeyQuest.getSystemColor(), lingua.translate("Nota"), 400, 200, envia, new String[]{lingua.translate("Voltar")});
        label.addMouseListener(new MouseAdapter() {
            java.awt.Color cor = label.getBackground();

            @Override
            public void mousePressed(MouseEvent e) {
                DefaultListModel<Keys.Material> modelo = new DefaultListModel<>();
                boolean esta_na_lista = false;
                vazia = true;
                for (Keys.Material m : mlista) {
                    for (Keys.Material s : selecionados) {
                        if ((s.getDescription().equals(m.getDescription())) && (s.getId() == m.getId()) && (s.getCodeOfMaterial().equals(m.getCodeOfMaterial())) && (s.getMaterialTypeID() == m.getMaterialTypeID())) {
                            esta_na_lista = true;
                        }
                    }
                    if (!esta_na_lista) {
                        m = new Keys.Material(m) {
                            @Override
                            public String toString() {
                                return this.getTypeOfMaterialName() + " " + this.getDescription();
                            }
                        };
                        modelo.addElement(m);
                        vazia = false;
                    }
                    esta_na_lista = false;
                }
                if (!vazia) {
                    lmater.setModel(modelo);
                    DefaultListModel<Keys.Material> model = (DefaultListModel) lmater.getModel();
                    int alt = 20;
                    for (int i = 0; i < model.getSize(); i++) {
                        Rectangle r = lmater.getCellBounds(i, i);
                        alt += r.getHeight();
                        lmater.setPreferredSize(new Dimension(280, alt));
                    }
                } else {
                    DefaultListModel<String> modelo2 = new DefaultListModel<>();
                    modelo2.addElement(lingua.translate("A lista está vazia") + "!");
                    lmater.removeAll();
                    lmater.setModel(modelo2);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    pop.show(e.getComponent(), e.getX(), e.getY());
                } else if (e.getClickCount() == 2) {
                    label.setBackground(cor);
                    mensagem.showMessage();
                } else {
                    label.setBackground(Color.LIGHT_GRAY);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                label.setBackground(cor);
            }
        });
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
            String[] palavras = l.split(" ");
            String texto = "<html><div style='text-align:center'>";
            int i = 0;
            while (i < palavras.length) {
                if (!palavras[i].equals("")) {
                    texto += palavras[i] + "<br/>";
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

    public boolean isSpecialOne() {
        return specialone;
    }

}
