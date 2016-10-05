/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author toze
 */
public class WResources extends javax.swing.JFrame {

    private static final long serialVersionUID = 123L; 
    private Color corborda;
    private Color corfundo;
    private String url;
    private Langs.Locale lingua;
    private List<Keys.TypeOfMaterial> tiposdematerial;
    private Keys.TypeOfMaterial tiposelecionado;
    private Keys.TypeOfMaterial novo;
    private java.util.List<Keys.Feature> carsOriginal;
    private java.util.List<Keys.Feature> carsData;
    private java.util.List<Keys.Feature> carsFinal;
    private boolean novaimagem;
    private FileIOAux.ImageExtension bimage;
    private Components.PersonalTextField numero;
    private DefaultListModel<String> modeloOriginal;
    private DefaultListModel<String> modeloFinal;

    public WResources(Color corfundo, Color corborda, String url, Langs.Locale lingua) {
        this.corborda = corborda;
        this.corfundo = corfundo;
        this.url = url;
        this.lingua = lingua;
        tiposelecionado = null;
        tiposdematerial = new java.util.ArrayList<>();
        carsFinal = new java.util.ArrayList<>();
        carsOriginal = new java.util.ArrayList<>();
        carsData = new java.util.ArrayList<>();
        novaimagem = false;
    }

    private void makePanel() {
        Border b = BorderFactory.createLineBorder(corborda, 4);
        jPanelInicial.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createLineBorder(Color.BLACK)));
        jPanelInicial.setBackground(corfundo);
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) (s.getWidth() / 2 - this.getWidth() / 2), (int) (s.getHeight() / 2 - this.getHeight() / 2));
    }

    public void appear() {
        this.setVisible(true);
    }

    private void putOrignalIcon() {
        String path = new File("").getAbsolutePath() + System.getProperty("file.separator") + "Resources" + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator") + "sem.jpg";
        bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.getImageFromFile(new File(path)));
        jLabelImagem.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 112, 112)));
        novaimagem = false;
    }

    public void create() {
        initComponents();
        try {
            if (Clavis.KeyQuest.class.getResource("Images/lock.png") != null) {
                BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lock.png"));
                ImageIcon imo = new ImageIcon(im);

            } else {

            }
        } catch (IOException ex) {
        }
        makePanel();
        this.makeComboBoxTypeMaterial();
        this.makeJListOriginalFeatures();
        jLabelRegisto.requestFocus();
        addListsBeahviors();

    }

    private void makeComboBoxTypeMaterial() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            tiposdematerial = db.getTypesOfMaterial();
            for (int i = 0; i < tiposdematerial.size(); i++) {
                tiposdematerial.set(i, new Keys.TypeOfMaterial(tiposdematerial.get(i)) {
                    @Override
                    public String toString() {
                        return lingua.translateWithPlural(getTypeOfMaterialName());
                    }
                });
            }
            for (int i = 0; i < tiposdematerial.size(); i++) {
                if (tiposdematerial.get(i).getMaterialTypeID() == 1) {
                    Keys.TypeOfMaterial tip = tiposdematerial.get(0);
                    tiposdematerial.set(0, tiposdematerial.get(i));
                    tiposdematerial.set(i, tip);
                }
            }
            tiposdematerial.stream().forEach((t) -> {
                modelo.addElement(t.toString());
            });
            tiposelecionado = tiposdematerial.get(0);
        } else {
            modelo.addElement(lingua.translate("Não existe ligação"));
            tiposdematerial = null;
        }
        jComboBoxTipoMaterial.setModel(modelo);
        jComboBoxTipoMaterial.addActionListener((ActionEvent e) -> {
            if ((tiposdematerial != null) && (tiposdematerial.size() > 0)) {
                int val = jComboBoxTipoMaterial.getSelectedIndex();
                for (int i = 0; i < tiposdematerial.size(); i++) {
                    if (i == val) {
                        tiposelecionado = tiposdematerial.get(i);
                    }
                }
                this.makeJListOriginalFeatures();
                carsFinal = new java.util.ArrayList<>();
                modeloFinal.removeAllElements();
            }
        });
    }

    private void makeJListOriginalFeatures() {
        modeloOriginal = new DefaultListModel<>();
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            if (tiposelecionado != null) {
                carsData = db.getFeaturesByTypeOfMaterial(tiposelecionado);
                carsOriginal = new java.util.ArrayList<>(carsData);
                for (int i = 0; i < carsOriginal.size(); i++) {
                    carsOriginal.set(i, new Keys.Feature(carsOriginal.get(i)) {
                        @Override
                        public String toString() {
                            return lingua.translate(this.getDescription());
                        }
                    });
                    modeloOriginal.addElement(lingua.translate(carsOriginal.get(i).toString()));
                }
            } else {
                modeloOriginal.addElement(lingua.translate("Selecione um tipo de material"));
                carsOriginal = null;
            }
        } else {
            modeloOriginal.addElement(lingua.translate("Não existe ligação à base de dados") + ".");
            carsOriginal = null;
        }
        jListOriginal.setModel(modeloOriginal);
        jScrollPaneOriginal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Rectangle r;
        int altura = 20;
        jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), 0));
        for (int x = 0; x < modeloOriginal.getSize(); x++) {
            r = jListOriginal.getCellBounds(x, x);
            altura += (int) (r.getHeight());
            if (altura >= jListOriginal.getPreferredSize().getHeight()) {
                jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), altura));
            }
        }
    }

    private void addListsBeahviors() {
        jListOriginal.setDragEnabled(true);
        jListOriginal.setDropMode(DropMode.INSERT);
        jListOriginal.setTransferHandler(new TransferHandler() {
            java.util.List<Keys.Feature> feas;
            private static final long serialVersionUID = 123L;
            @Override
            public int getSourceActions(JComponent comp) {
                return TransferHandler.COPY;
            }

            @Override
            public Transferable createTransferable(JComponent comp) {
                feas = getSelectedOriginalFeatures();
                String sel = "";
                sel = feas.stream().map((f) -> f.toString()).reduce(sel, String::concat);
                return new StringSelection(sel);
            }

            @Override
            public void exportDone(JComponent comp, Transferable trans, int action) {
                boolean igual = false;
                if (action == COPY) {
                    for (Keys.Feature f : feas) {
                        for (int i = 0; i < carsFinal.size(); i++) {
                            if (f.getDescription().equals(carsFinal.get(i).getDescription())) {
                                igual = true;
                            }
                        }
                        if (!igual) {
                            carsFinal.add(f);
                            if (!f.getUnityMeasure().equals("")) {
                                int u = createMessageMeasure(f).showMessage();
                                if (u < 1) {
                                    for (int i = 0; i < carsFinal.size(); i++) {
                                        if (f.compareTo(carsFinal.get(i)) == 0) {
                                            modeloFinal.removeElementAt(i);
                                        }
                                    }
                                    carsFinal.remove(f);
                                } else {
                                    double val;
                                    String snumero = numero.getText().replace(",", ".");
                                    try {
                                        val = Double.parseDouble(snumero);
                                    } catch (NumberFormatException e) {
                                        val = -1;
                                    }
                                    if (val != -1) {
                                        String s = f.toString() + " (" + val + " " + f.getUnityMeasure() + ")";
                                        for (int i = 0; i < carsFinal.size(); i++) {
                                            if (f.compareTo(carsFinal.get(i)) == 0) {
                                                modeloFinal.set(i, s);
                                                carsFinal.get(i).setNumber(val);
                                            }
                                        }
                                    } else {
                                        for (int i = 0; i < carsFinal.size(); i++) {
                                            if (f.compareTo(carsFinal.get(i)) == 0) {
                                                modeloFinal.removeElementAt(i);
                                            }
                                        }
                                        carsFinal.remove(f);
                                    }
                                }
                            }
                        } else if (!f.getUnityMeasure().equals("")) {
                            int u = createMessageMeasure(f).showMessage();
                            if (u == 1) {
                                double val;
                                String snumero = numero.getText().replace(",", ".");
                                try {
                                    val = Double.parseDouble(snumero);
                                } catch (NumberFormatException e) {
                                    val = -1;
                                }
                                if (val != -1) {
                                    String s = f.toString() + " (" + val + " " + f.getUnityMeasure() + ")";
                                    for (int i = 0; i < carsFinal.size(); i++) {
                                        if (f.compareTo(carsFinal.get(i)) == 0) {
                                            modeloFinal.set(i, s);
                                            carsFinal.get(i).setNumber(val);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        jListFinal.setDragEnabled(true);
        jListFinal.setDropMode(DropMode.INSERT);
        modeloFinal = new DefaultListModel<>();
        jListFinal.setModel(modeloFinal);
        jListFinal.setTransferHandler(new TransferHandler() {
            int index;
            private static final long serialVersionUID = 123L;
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return false;
                }
                JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
                return dl.getIndex() != -1;
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }
                Transferable transferable = support.getTransferable();
                String data;
                try {
                    data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                } catch (UnsupportedFlavorException | IOException e) {
                    return false;
                }
                index = jListFinal.getModel().getSize();
                boolean nao_tem = true;
                for (int k = 0; k < modeloFinal.size(); k++) {
                    if (modeloFinal.get(k).split(" \\(")[0].trim().equals(data.split(" \\(")[0].trim())) {
                        nao_tem = false;
                    }
                }
                if (nao_tem) {
                    modeloFinal.add(index, data);
                    Rectangle r;
                    int altura = 20;
                    jListFinal.setPreferredSize(new Dimension((int) jListFinal.getPreferredSize().getWidth(), 0));
                    for (int x = 0; x < modeloFinal.size(); x++) {
                        r = jListFinal.getCellBounds(0, 0);
                        altura += (int) (r.getHeight());
                        if (altura >= jListFinal.getPreferredSize().getHeight()) {
                            jListFinal.setPreferredSize(new Dimension((int) jListFinal.getPreferredSize().getWidth(), altura));
                        }
                    }
                }
                return true;
            }
        });
        String[] remover = new String[]{lingua.translate("Remover")};
        ActionListener[] al = new ActionListener[1];
        al[0] = (ActionEvent e) -> {
            removeFromFinalList();
        };
        Components.PopUpMenu pop = new Components.PopUpMenu(remover, al);
        pop.create();
        jListFinal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (jListFinal.getSelectedIndices().length <= 1) {
                        int index = jListFinal.locationToIndex(new java.awt.Point(e.getX(), e.getY()));
                        if (index > -1) {
                            jListFinal.setSelectedIndex(index);
                        }
                    }
                    pop.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        jListOriginal.addMouseListener(new MouseAdapter() {
            ActionListener[] ala;
            String[] outra;
            Components.PopUpMenu pop2;

            @Override
            public void mouseReleased(MouseEvent e) {
                ala = new ActionListener[1];
                outra = new String[]{lingua.translate("Transferir")};
                if (e.getButton() == MouseEvent.BUTTON3) {
                    ala[0] = (ActionEvent ex) -> {
                        boolean igual = false;
                        java.util.List<Keys.Feature> feas = getSelectedOriginalFeatures();
                        for (Keys.Feature f : feas) {
                            for (int i = 0; i < carsFinal.size(); i++) {
                                if (f.getDescription().equals(carsFinal.get(i).getDescription())) {
                                    igual = true;
                                }
                            }

                            if (!igual) {
                                carsFinal.add(f);
                                if (!f.getUnityMeasure().equals("")) {
                                    int u = createMessageMeasure(f).showMessage();
                                    if (u < 1) {
                                        carsFinal.remove(f);
                                    } else {
                                        double val;
                                        String snumero = numero.getText().replace(",", ".");
                                        try {
                                            val = Double.parseDouble(snumero);
                                        } catch (NumberFormatException ek) {
                                            val = -1;
                                        }
                                        if (val != -1) {
                                            String s = f.toString() + " (" + val + " " + f.getUnityMeasure() + ")";
                                            for (int i = 0; i < carsFinal.size(); i++) {
                                                if (f.compareTo(carsFinal.get(i)) == 0) {
                                                    modeloFinal.addElement(s);
                                                    carsFinal.get(i).setNumber(val);
                                                }
                                            }
                                        } else {
                                            carsFinal.remove(f);
                                        }
                                    }
                                } else {
                                    modeloFinal.addElement(f.toString());
                                }
                            } else if (!f.getUnityMeasure().equals("")) {
                                int u = createMessageMeasure(f).showMessage();
                                if (u == 1) {
                                    DefaultListModel<String> modelo = (DefaultListModel<String>) jListFinal.getModel();
                                    double val;
                                    String snumero = numero.getText().replace(",", ".");
                                    try {
                                        val = Double.parseDouble(snumero);
                                    } catch (NumberFormatException el) {
                                        val = -1;
                                    }
                                    if (val != -1) {
                                        String s = f.toString() + " (" + val + " " + f.getUnityMeasure() + ")";
                                        for (int i = 0; i < modelo.size(); i++) {
                                            if (f.compareTo(carsFinal.get(i)) == 0) {
                                                modelo.set(i, s);
                                                carsFinal.get(i).setNumber(val);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Rectangle r;
                        int altura = 20;
                        jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), 0));
                        DefaultListModel<String> modelo = (DefaultListModel<String>) jListOriginal.getModel();
                        for (int x = 0; x < modelo.size(); x++) {
                            r = jListOriginal.getCellBounds(0, 0);
                            altura += (int) (r.getHeight());
                            if (altura >= jListOriginal.getPreferredSize().getHeight()) {
                                jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), altura));
                            }
                        }
                    };
                    int sel = jListOriginal.locationToIndex(e.getPoint());
                    jListOriginal.setSelectedIndex(sel);
                    if (sel >= 0) {
                        Keys.Feature f = carsOriginal.get(sel);
                        boolean existe = false;
                        for (Keys.Feature fo : carsData) {
                            if (fo.compareTo(f) == 0) {
                                existe = true;
                            }
                        }
                        if (!existe) {
                            ActionListener ro = ala[0];
                            ActionListener[] alas = new ActionListener[2];
                            alas[0] = ro;
                            alas[1] = (ActionEvent ex) -> {
                                int i = jListOriginal.getSelectedIndex();
                                Keys.Feature fan = carsOriginal.get(i);
                                if (!carsData.contains(fan)) {
                                    carsOriginal.remove(fan);
                                    modeloOriginal.remove(i);
                                }
                                Rectangle r;
                                int altura = 20;
                                jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), 0));

                                for (int x = 0; x < modeloOriginal.size(); x++) {
                                    r = jListOriginal.getCellBounds(0, 0);
                                    altura += (int) (r.getHeight());
                                    if (altura >= jListOriginal.getPreferredSize().getHeight()) {
                                        jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), altura));
                                    }
                                }
                            };
                            outra = new String[]{lingua.translate("Transferir"), lingua.translate("Remover")};
                            pop2 = new Components.PopUpMenu(outra, alas);
                            pop2.create();
                            pop2.show(e.getComponent(), e.getX(), e.getY());
                        } else {
                            pop2 = new Components.PopUpMenu(outra, ala);
                            pop2.create();
                            pop2.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            }
        });
        jListFinal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    removeFromFinalList();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jListFinal.clearSelection();
                }
            }
        });
        jListOriginal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    boolean igual = false;
                    java.util.List<Keys.Feature> feas = getSelectedOriginalFeatures();
                    for (Keys.Feature f : feas) {
                        for (int i = 0; i < carsFinal.size(); i++) {
                            if (f.getDescription().equals(carsFinal.get(i).getDescription())) {
                                igual = true;
                            }
                        }
                        if (!igual) {
                            carsFinal.add(f);
                            if (!f.getUnityMeasure().equals("")) {
                                int u = createMessageMeasure(f).showMessage();
                                if (u < 1) {
                                    carsFinal.remove(f);
                                } else {
                                    double val;
                                    String snumero = numero.getText().replace(",", ".");
                                    try {
                                        val = Double.parseDouble(snumero);
                                    } catch (NumberFormatException ek) {
                                        val = -1;
                                    }
                                    if (val != -1) {
                                        String s = f.toString() + " (" + val + " " + f.getUnityMeasure() + ")";
                                        for (int i = 0; i < carsFinal.size(); i++) {
                                            if (f.compareTo(carsFinal.get(i)) == 0) {
                                                modeloFinal.addElement(s);
                                                carsFinal.get(i).setNumber(val);
                                            }
                                        }
                                    } else {
                                        carsFinal.remove(f);
                                    }
                                }
                            } else {
                                modeloFinal.addElement(f.toString());
                            }
                        } else if (!f.getUnityMeasure().equals("")) {
                            int u = createMessageMeasure(f).showMessage();
                            if (u == 1) {
                                double val;
                                String snumero = numero.getText().replace(",", ".");
                                try {
                                    val = Double.parseDouble(snumero);
                                } catch (NumberFormatException el) {
                                    val = -1;
                                }
                                if (val != -1) {
                                    String s = f.toString() + " (" + val + " " + f.getUnityMeasure() + ")";
                                    for (int i = 0; i < modeloFinal.size(); i++) {
                                        if (f.compareTo(carsFinal.get(i)) == 0) {
                                            modeloFinal.set(i, s);
                                            carsFinal.get(i).setNumber(val);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Rectangle r;
                    int altura = 20;
                    jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), 0));
                    DefaultListModel<String> modelo = (DefaultListModel<String>) jListOriginal.getModel();
                    for (int x = 0; x < modelo.size(); x++) {
                        r = jListOriginal.getCellBounds(0, 0);
                        altura += (int) (r.getHeight());
                        if (altura >= jListOriginal.getPreferredSize().getHeight()) {
                            jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), altura));
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jListOriginal.clearSelection();
                }
            }
        });
        jLabelImagem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                FileIOAux.ImageExtension imo = FileIOAux.ImageAux.getImageFromFileDialog(lingua.translate("Escolha uma imagem para o recurso"), jLabelImagem, (javax.swing.JFrame) SwingUtilities.getWindowAncestor(jLabelImagem), 112, 112);
                if (imo != null){
                    novaimagem = true;
                    bimage.setImage(imo.getImage());
                    bimage.setExtension(imo.getExtension());
                } 
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (bimage != null) {
                    jLabelImagem.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(FileIOAux.ImageAux.makeWhiter(bimage.getImage(), 80), 112, 112)));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (bimage != null) {
                    jLabelImagem.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 112, 112)));
                }
            }
        });
        textNome.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }
        });
        textCodigo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }
        });
        textCodigo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }
        });
        textNome.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String g = textNome.getText();
                String g2 = textCodigo.getText();
                if ((g.equals("")) || (g2.equals(""))) {
                    jButtonConfirmar.setEnabled(false);
                } else {
                    jButtonConfirmar.setEnabled(true);
                }
            }
        });
        
    }

    private void removeFromFinalList() {
        int[] indices = jListFinal.getSelectedIndices();
        for (int i = indices.length - 1; i >= 0; i--) {
            carsFinal.remove(indices[i]);
            modeloFinal.removeElementAt(indices[i]);
        }
        Rectangle r;
        int altura = 20;
        jListFinal.setPreferredSize(new Dimension((int) jListFinal.getPreferredSize().getWidth(), 0));
        for (int x = 0; x < modeloFinal.size(); x++) {
            r = jListFinal.getCellBounds(0, 0);
            altura += (int) (r.getHeight());
            if (altura >= jListFinal.getPreferredSize().getHeight()) {
                jListFinal.setPreferredSize(new Dimension((int) jListFinal.getPreferredSize().getWidth(), altura));
            }
        }
    }

    private Components.MessagePane createMessageMeasure(Keys.Feature f) {
        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setPreferredSize(new Dimension(300, 120));
        panel.setBounds(0, 10, 300, 120);
        javax.swing.JLabel l = new javax.swing.JLabel(lingua.translate("Quantidade") + " (" + f.getUnityMeasure() + ")");
        l.setFocusable(true);
        l.setPreferredSize(new Dimension(250, 30));
        l.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        panel.add(l);
        numero = new Components.PersonalTextField();
        numero.addPlaceHolder(lingua.translate("Inserir quantidade"), l);
        Border border = BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK));
        numero.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        numero.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        numero.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                int val = numero.getText().length();
                if (val > 0) {
                    String aux = "" + numero.getText().charAt(val - 1);
                    if (!aux.matches("[\\d.,]")) {
                        Thread t = new Thread(() -> {
                            if ((val - 1) < numero.getText().length()) {
                                numero.setText(numero.getText().substring(0, val - 1));
                            }
                        });
                        SwingUtilities.invokeLater(t);
                    }
                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        numero.setPreferredSize(new Dimension(250, 30));
        panel.add(numero);
        Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.ACAO, corborda, "Valor da caraterística", 400, 220, panel, "", new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
        return mensagem;
    }

    private java.util.List<Keys.Feature> getSelectedOriginalFeatures() {
        java.util.List<Keys.Feature> cars = new java.util.ArrayList<>();
        int[] valores = jListOriginal.getSelectedIndices();
        if (carsOriginal != null) {
            for (int i = 0; i < valores.length; i++) {
                cars.add(carsOriginal.get(valores[i]));
            }
        }
        return cars;
    }

    private java.util.List<Keys.Feature> getSelectedFinalFeatures() {
        java.util.List<Keys.Feature> cars = new java.util.ArrayList<>();
        int[] valores = jListFinal.getSelectedIndices();
        if (carsFinal != null) {
            for (int i = 0; i < valores.length; i++) {
                cars.add(carsFinal.get(valores[i]));
            }
        }
        return cars;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shapePainter1 = new org.jdesktop.swingx.painter.ShapePainter();
        jPanelInicial = new javax.swing.JPanel();
        jLabelRegisto = new javax.swing.JLabel();
        jButtonConfirmar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabelImagem = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPaneFinal = new javax.swing.JScrollPane();
        jListFinal = new javax.swing.JList<>();
        pButtonAdicionar = new Components.PersonalButton();
        pButtonRemover = new Components.PersonalButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPaneOriginal = new javax.swing.JScrollPane();
        jListOriginal = new javax.swing.JList<>();
        jLabelT = new javax.swing.JLabel();
        textNome = new Components.PersonalTextField();
        textCodigo = new Components.PersonalTextField();
        jLabelNome = new javax.swing.JLabel();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelCarateristica = new javax.swing.JLabel();
        jLabelValor = new javax.swing.JLabel();
        jLabelMedida = new javax.swing.JLabel();
        textValor = new Components.PersonalTextField();
        textMedida = new Components.PersonalTextField();
        jComboBoxTipoMaterial = new javax.swing.JComboBox<>();
        jButtonSair = new javax.swing.JButton();
        pButtonMais = new Components.PersonalButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);

        jPanelInicial.setBackground(new java.awt.Color(254, 254, 254));
        jPanelInicial.setMinimumSize(new java.awt.Dimension(800, 500));
        jPanelInicial.setPreferredSize(new java.awt.Dimension(800, 500));

        jLabelRegisto.setBackground(new java.awt.Color(250, 250, 250));
        jLabelRegisto.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabelRegisto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRegisto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelRegisto.setOpaque(true);

        jButtonConfirmar.setBackground(new java.awt.Color(51, 102, 203));
        jButtonConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonConfirmar.setEnabled(false);
        jButtonConfirmar.setFocusPainted(false);
        jButtonConfirmar.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonConfirmar.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonConfirmar.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setCornerSize(6);
        dropShadowBorder1.setShadowSize(2);
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder1, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel4.setMinimumSize(new java.awt.Dimension(130, 130));
        jPanel4.setPreferredSize(new java.awt.Dimension(130, 130));

        jLabelImagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelImagem.setMaximumSize(new java.awt.Dimension(112, 110));
        jLabelImagem.setMinimumSize(new java.awt.Dimension(112, 110));
        jLabelImagem.setPreferredSize(new java.awt.Dimension(110, 110));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        String path = new File("").getAbsolutePath() + System.getProperty("file.separator") + "Resources" + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator") + "sem.jpg";
        bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.getImageFromFile(new File(path)));
        jLabelImagem.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 112, 112)));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setCornerSize(6);
        dropShadowBorder2.setShadowSize(3);
        dropShadowBorder2.setShowLeftShadow(true);
        jPanel2.setBorder(dropShadowBorder2);
        jPanel2.setPreferredSize(new java.awt.Dimension(440, 252));

        jScrollPaneFinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jListFinal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jListFinal.setPreferredSize(new java.awt.Dimension(420, 200));
        jScrollPaneFinal.setViewportView(jListFinal);
        jListFinal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jListFinal.setCellRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 2L;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = (javax.swing.JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(150, 30));
                if (isSelected) {
                    label.setBackground(Color.GRAY);
                    label.setForeground(Color.WHITE);
                    label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
                return label;
            }
        });
        jListFinal.setPreferredSize(new Dimension(220, 130));
        jListFinal.setBackground(new Color(250, 250, 250));
        jScrollPaneFinal.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneFinal, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );

        pButtonAdicionar.setMaximumSize(new java.awt.Dimension(30, 30));
        pButtonAdicionar.setMinimumSize(new java.awt.Dimension(30, 30));
        pButtonAdicionar.setPreferredSize(new java.awt.Dimension(30, 30));
        pButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pButtonAdicionarActionPerformed(evt);
            }
        });

        pButtonRemover.setMaximumSize(new java.awt.Dimension(30, 30));
        pButtonRemover.setMinimumSize(new java.awt.Dimension(30, 30));
        pButtonRemover.setPreferredSize(new java.awt.Dimension(30, 30));
        pButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pButtonRemoverActionPerformed(evt);
            }
        });

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setCornerSize(6);
        dropShadowBorder3.setShadowSize(3);
        dropShadowBorder3.setShowLeftShadow(true);
        jPanel3.setBorder(dropShadowBorder3);
        jPanel3.setMinimumSize(new java.awt.Dimension(470, 252));
        jPanel3.setPreferredSize(new java.awt.Dimension(470, 258));

        jScrollPaneOriginal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jListOriginal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListOriginal.setDragEnabled(true);
        jListOriginal.setPreferredSize(new java.awt.Dimension(300, 230));
        jScrollPaneOriginal.setViewportView(jListOriginal);
        jListOriginal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jListOriginal.setCellRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 2L;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = (javax.swing.JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(150, 30));
                if (isSelected) {
                    label.setBackground(Color.GRAY);
                    label.setForeground(Color.WHITE);
                    label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
                return label;
            }
        });
        jListOriginal.setPreferredSize(new Dimension(220, 130));
        jListOriginal.setBackground(new Color(250, 250, 250));
        jScrollPaneOriginal.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPaneOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
        );

        jLabelT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder4 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder4.setCornerSize(6);
        dropShadowBorder4.setShadowSize(2);
        dropShadowBorder4.setShowLeftShadow(true);
        dropShadowBorder4.setShowTopShadow(true);
        textNome.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder4, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        textNome.setMinimumSize(new java.awt.Dimension(232, 30));
        textNome.setPreferredSize(new java.awt.Dimension(234, 30));
        textNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNomeActionPerformed(evt);
            }
        });

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder5 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder5.setCornerSize(6);
        dropShadowBorder5.setShadowSize(2);
        dropShadowBorder5.setShowLeftShadow(true);
        dropShadowBorder5.setShowTopShadow(true);
        textCodigo.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder5, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        textCodigo.setMinimumSize(new java.awt.Dimension(234, 30));
        textCodigo.setPreferredSize(new java.awt.Dimension(234, 30));

        jLabelNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabelCodigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCodigo.setPreferredSize(new java.awt.Dimension(168, 30));

        jLabelCarateristica.setBackground(new java.awt.Color(250, 250, 250));
        jLabelCarateristica.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabelCarateristica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCarateristica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelCarateristica.setOpaque(true);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder6 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder6.setCornerSize(6);
        dropShadowBorder6.setShadowSize(2);
        dropShadowBorder6.setShowLeftShadow(true);
        dropShadowBorder6.setShowTopShadow(true);
        textValor.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder6, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        textValor.setMinimumSize(new java.awt.Dimension(274, 30));
        textValor.setPreferredSize(new java.awt.Dimension(274, 30));
        textValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textValorActionPerformed(evt);
            }
        });

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder7 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder7.setCornerSize(6);
        dropShadowBorder7.setShadowSize(2);
        dropShadowBorder7.setShowLeftShadow(true);
        dropShadowBorder7.setShowTopShadow(true);
        textMedida.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder7, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));

        jComboBoxTipoMaterial.setBorder(null);
        jComboBoxTipoMaterial.setFocusable(false);

        jButtonSair.setBackground(new java.awt.Color(1, 1, 1));
        jButtonSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSair.setFocusPainted(false);
        jButtonSair.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonSair.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonSair.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        pButtonMais.setMaximumSize(new java.awt.Dimension(30, 30));
        pButtonMais.setMinimumSize(new java.awt.Dimension(30, 30));
        pButtonMais.setPreferredSize(new java.awt.Dimension(30, 30));
        pButtonMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pButtonMaisActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelInicialLayout = new javax.swing.GroupLayout(jPanelInicial);
        jPanelInicial.setLayout(jPanelInicialLayout);
        jPanelInicialLayout.setHorizontalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRegisto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicialLayout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanelInicialLayout.createSequentialGroup()
                                            .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelInicialLayout.createSequentialGroup()
                                            .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(textCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                                        .addComponent(jLabelT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicialLayout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicialLayout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                                    .addGap(6, 6, 6))))
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelInicialLayout.createSequentialGroup()
                                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pButtonRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textValor, javax.swing.GroupLayout.PREFERRED_SIZE, 205, Short.MAX_VALUE)
                                            .addComponent(textMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(jLabelCarateristica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicialLayout.createSequentialGroup()
                                .addComponent(pButtonMais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanelInicialLayout.setVerticalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCarateristica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBoxTipoMaterial)
                                .addComponent(jLabelT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelInicialLayout.createSequentialGroup()
                                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInicialLayout.createSequentialGroup()
                                .addComponent(textMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pButtonMais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addComponent(pButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(pButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelInicialLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );

        jLabelRegisto.setText(lingua.translate("Registo de material"));
        if (Clavis.KeyQuest.class.getResource("Images/ok.png") != null) {
            BufferedImage im = null;
            try {
                im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
            } catch (IOException ex) {
                Logger.getLogger(WResources.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageIcon icbConfirmar = new ImageIcon(im);
            jButtonConfirmar.setIcon(icbConfirmar);
        } else {
            jButtonConfirmar.setText(lingua.translate("Registar"));
        }
        jButtonConfirmar.setToolTipText(lingua.translate("Registar novo artigo"));
        if (Clavis.KeyQuest.class.getResource("Images/seta_esquerda_negro.png") != null) {
            BufferedImage im = null;
            try {
                im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/seta_esquerda_negro.png"));
            } catch (IOException ex) {
                Logger.getLogger(WResources.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageIcon icbAdicionar = new ImageIcon(im);
            pButtonAdicionar.setIcon(icbAdicionar);
        } else {
            pButtonAdicionar.setText("->");
        }
        pButtonAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        if (Clavis.KeyQuest.class.getResource("Images/seta_direita_negro.png") != null) {
            BufferedImage im2 = null;
            try {
                im2 = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/seta_direita_negro.png"));
            } catch (IOException ex) {
                Logger.getLogger(WResources.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageIcon icbRemover= new ImageIcon(im2);
            pButtonRemover.setIcon(icbRemover);
        } else {
            pButtonRemover.setText("<-");
        }
        pButtonRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelT.setText(lingua.translate("Tipo de material")+":");
        textNome.addPlaceHolder(lingua.translate("Nome do produto")+" ...", jLabelNome);
        textCodigo.addPlaceHolder(lingua.translate("Código do produto")+" ...", jLabelCodigo);
        jLabelNome.setText(lingua.translate("Designação")+":");
        jLabelCodigo.setText(lingua.translate("Código")+":");
        jLabelCarateristica.setText(lingua.translate("Adicionar novo valores ou caraterísticas"));
        jLabelValor.setText(lingua.translate("Descrição")+": ");
        jLabelMedida.setText(lingua.translate("Medida")+": ");
        textValor.addPlaceHolder(lingua.translate("Nova característica")+" ...", jLabelValor);
        textMedida.addPlaceHolder(lingua.translate("Medida de valor")+" ...", jLabelMedida);
        ((javax.swing.JLabel)jComboBoxTipoMaterial.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        BasicComboPopup popupMaterial = (BasicComboPopup) jComboBoxTipoMaterial.getAccessibleContext().getAccessibleChild(0);
        popupMaterial.getList().setSelectionBackground(Color.DARK_GRAY);
        popupMaterial.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        if (Clavis.KeyQuest.class.getResource("Images/exit26x24.png") != null) {
            BufferedImage im = null;
            try {
                im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/exit26x24.png"));
            } catch (IOException ex) {
                Logger.getLogger(WResources.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageIcon icbAdicionar = new ImageIcon(im);
            jButtonSair.setIcon(icbAdicionar);
        } else {
            jButtonSair.setText(lingua.translate("Sair"));
        }
        jButtonSair.setToolTipText(lingua.translate("Voltar"));
        if (Clavis.KeyQuest.class.getResource("Images/plus24x24negro.png") != null) {
            BufferedImage im = null;
            try {
                im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/plus24x24negro.png"));
            } catch (IOException ex) {
                Logger.getLogger(WResources.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageIcon icbMais = new ImageIcon(im);
            pButtonMais.setIcon(icbMais);
        } else {
            pButtonMais.setText("+");
        }
        pButtonMais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setText(lingua.translate("Desloque valores ou caraterísticas para este quadro") + ".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        if (DataBase.DataBase.testConnection(url)) {
            Keys.TypeOfMaterial tipo = this.tiposelecionado;
            String nome = textNome.getText();
            String codigo = textCodigo.getText();
            Keys.Material m = new Keys.Material(tipo, codigo, nome, false);
            if (novaimagem) {
                m.setMaterialImage(bimage.getImage(), bimage.getExtension());
            }
            DataBase.DataBase db = new DataBase.DataBase(url);

            db.setAutoCommit(false);
            db.insertMaterial(m);
            int val = db.getMaterialID(m);
            boolean auxiliar = false;
            for (Keys.Feature f : carsFinal) {
                for (Keys.Feature fo : carsData) {
                    if (fo.compareTo(f) == 0) {
                        auxiliar = true;
                        break;
                    }
                }
                if (auxiliar) {
                    db.associateFeatureWithMaterial(f, m);
                } else {
                    f.setTypeOfMaterial(tipo);
                    db.insertFeature(f);
                    db.associateFeatureWithMaterial(f, m);
                }
            }
            db.commit();
            db.setAutoCommit(true);
            db.close();
            for (int i = 0; i < carsFinal.size(); i++) {
                modeloFinal.remove(0);

            }
            carsFinal.removeAll(carsFinal);
            textNome.setText("");
            textNome.showPLaceHolder();
            textCodigo.setText("");
            textCodigo.showPLaceHolder();
            jListOriginal.clearSelection();
            this.putOrignalIcon();
            jLabelMedida.requestFocus();
        } else {
            Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Informação"), 400, 200, lingua.translate("Não há ligação à base de dados"), new String[]{lingua.translate("Voltar")});
            mensagem.showMessage();
        }

    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void pButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pButtonRemoverActionPerformed
        this.removeFromFinalList();
    }//GEN-LAST:event_pButtonRemoverActionPerformed

    private void pButtonMaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pButtonMaisActionPerformed
        Keys.Feature f;
        String medida = textMedida.getText();
        String valor = textValor.getText();
        if (carsOriginal != null) {
            if (!valor.equals("")) {
                f = new Keys.Feature(valor, medida, 0) {
                    @Override
                    public String toString() {
                        return lingua.translate(getDescription());
                    }
                };
                boolean cond = false;
                for (Keys.Feature fol : carsOriginal) {
                    if ((fol.getDescription().equals(f.getDescription())) && (fol.getUnityMeasure().equals(f.getUnityMeasure()))) {
                        cond = true;
                    }
                }
                if (!cond) {
                    carsOriginal.add(f);
                    Collections.sort(carsOriginal);
                    modeloOriginal.removeAllElements();
                    carsOriginal.stream().forEach((fo) -> {
                        modeloOriginal.addElement(fo.toString());
                    });
                    Rectangle r;
                    int altura = 20;
                    jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), 0));
                    for (int x = 0; x < modeloOriginal.size(); x++) {
                        r = jListOriginal.getCellBounds(0, 0);
                        altura += (int) (r.getHeight());
                        if (altura >= jListOriginal.getPreferredSize().getHeight()) {
                            jListOriginal.setPreferredSize(new Dimension((int) jListOriginal.getPreferredSize().getWidth(), altura));
                        }
                    }
                    textValor.setText("");
                    textMedida.setText("");
                    textValor.showPLaceHolder();
                    textMedida.showPLaceHolder();
                    jLabelMedida.requestFocus();
                }
            } else {
                Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Nota"), 400, 200, lingua.translate("O campo \"Descrição\" tem de ser preenchido") + ".", new String[]{lingua.translate("Voltar")});
                mensagem.showMessage();
            }
        }
    }//GEN-LAST:event_pButtonMaisActionPerformed

    private void pButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pButtonAdicionarActionPerformed
        boolean igual = false;
        java.util.List<Keys.Feature> feas = this.getSelectedOriginalFeatures();
        for (Keys.Feature f : feas) {
            for (int i = 0; i < carsFinal.size(); i++) {
                if (f.getDescription().equals(carsFinal.get(i).getDescription())) {
                    igual = true;
                }
            }
            if (!igual) {
                carsFinal.add(f);
                if (!f.getUnityMeasure().equals("")) {
                    int u = createMessageMeasure(f).showMessage();
                    if (u < 1) {
                        carsFinal.remove(f);
                    } else {
                        double val;
                        String snumero = numero.getText().replace(",", ".");
                        try {
                            val = Double.parseDouble(snumero);
                        } catch (NumberFormatException e) {
                            val = -1;
                        }
                        if (val != -1) {
                            String s = f.toString() + " (" + val + " " + f.getUnityMeasure() + ")";
                            for (int i = 0; i < carsFinal.size(); i++) {
                                if (f.compareTo(carsFinal.get(i)) == 0) {
                                    modeloFinal.addElement(s);
                                    carsFinal.get(i).setNumber(val);
                                }
                            }
                        } else {
                            carsFinal.remove(f);
                        }
                    }
                } else {
                    modeloFinal.addElement(f.toString());
                }
            } else if (!f.getUnityMeasure().equals("")) {
                int u = createMessageMeasure(f).showMessage();
                if (u == 1) {
                    double val;
                    String snumero = numero.getText().replace(",", ".");
                    try {
                        val = Double.parseDouble(snumero);
                    } catch (NumberFormatException e) {
                        val = -1;
                    }
                    if (val != -1) {
                        String s = f.toString() + " (" + val + " " + f.getUnityMeasure() + ")";
                        for (int i = 0; i < modeloFinal.size(); i++) {
                            if (f.compareTo(carsFinal.get(i)) == 0) {
                                modeloFinal.set(i, s);
                                carsFinal.get(i).setNumber(val);
                            }
                        }
                    }
                }
            }
        }
        Rectangle r;
        int altura = 20;
        jListFinal.getPreferredSize().setSize(jListFinal.getPreferredSize().getWidth(), 0);
        for (int x = 0; x < modeloFinal.size(); x++) {
            r = jListFinal.getCellBounds(0, 0);
            altura += (int) (r.getHeight());
            if (altura >= jListFinal.getPreferredSize().getHeight()) {
                jListFinal.setPreferredSize(new Dimension((int) jListFinal.getPreferredSize().getWidth(), altura));
            }
        }
    }//GEN-LAST:event_pButtonAdicionarActionPerformed

    private void textValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textValorActionPerformed

    private void textNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNomeActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WResources.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            WResources wr = new WResources(new Color(125, 234, 145), new Color(255, 255, 255), "", Langs.Locale.getLocale_pt_PT());
            wr.create();
            wr.appear();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox<String> jComboBoxTipoMaterial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCarateristica;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelMedida;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelRegisto;
    private javax.swing.JLabel jLabelT;
    private javax.swing.JLabel jLabelValor;
    private javax.swing.JList<String> jListFinal;
    private javax.swing.JList<String> jListOriginal;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelInicial;
    private javax.swing.JScrollPane jScrollPaneFinal;
    private javax.swing.JScrollPane jScrollPaneOriginal;
    private Components.PersonalButton pButtonAdicionar;
    private Components.PersonalButton pButtonMais;
    private Components.PersonalButton pButtonRemover;
    private org.jdesktop.swingx.painter.ShapePainter shapePainter1;
    private Components.PersonalTextField textCodigo;
    private Components.PersonalTextField textMedida;
    private Components.PersonalTextField textNome;
    private Components.PersonalTextField textValor;
    // End of variables declaration//GEN-END:variables
}
