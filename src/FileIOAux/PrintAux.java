/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileIOAux;

import Clavis.Windows.WHorario;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterGraphics;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Paper;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.NumberOfDocuments;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.Sides;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.JTextComponent;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

/**
 *
 * @author toze
 */
public class PrintAux {

    private HashPrintRequestAttributeSet parametros;
    private javax.print.PrintService[] impressoras = PrinterJob.lookupPrintServices();
    private PrinterJob impressao;
    private PDDocument doc;
    private final javax.swing.JDialog dialogo;
    private File file;
    private Langs.Locale lingua;
    private int ncopias;
    private int valormaximo;
    private int valorminimo;
    private int valorqualidade;
    private int valorlados;
    private int valorcores;

    public PrintAux(File file, Langs.Locale lingua, javax.swing.JDialog dialogo) {
        super();
        this.lingua = lingua;
        this.dialogo = dialogo;
        impressao = PrinterJob.getPrinterJob();
        impressoras = PrinterJob.lookupPrintServices();
        parametros = new HashPrintRequestAttributeSet();
        valorqualidade = 4;
        valorlados = 0;
        valorminimo = 1;
        valormaximo = 1;
        valorcores = 1;
        ncopias = 1;
        this.file = file;
        FileInputStream fis;
        try {
            doc = PDDocument.load(file);
        } catch (IOException ex) {
            Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
            doc = null;
        }

    }

    public synchronized void print() {
        int val = this.showDialog(dialogo);
        if (val > -1) {
            try {
                PDDocument pdf = PDDocument.load(file);
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(pdf));
                job.setPrintService(impressoras[val]);
                switch (valorqualidade) {
                    case 5:
                        parametros.add(PrintQuality.HIGH);
                        break;
                    case 3:
                        parametros.add(PrintQuality.DRAFT);
                        break;
                    default:
                        parametros.add(PrintQuality.NORMAL);
                        break;
                }
                switch (valorlados) {
                    case 1:
                        parametros.add(Sides.DUPLEX);
                        break;
                    case 2:
                        parametros.add(Sides.TUMBLE);
                        break;
                    default:
                        parametros.add(Sides.ONE_SIDED);
                        break;
                }
                switch (valorcores) {
                    case 0:
                        parametros.add(Chromaticity.MONOCHROME);
                        break;
                    default:
                        parametros.add(Chromaticity.COLOR);
                        break;
                }
                parametros.add(new PageRanges(valorminimo, valormaximo));
                parametros.add(new Copies(ncopias));
                job.print(parametros);
                pdf.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WHorario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | PrinterException ex) {
                Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            doc.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @see
     * http://stackoverflow.com/questions/23326562/apache-pdfbox-convert-pdf-to-images
     * @param fil
     * @return
     */
    public static BufferedImage[] pdfToImage(String fil) {
        BufferedImage[] bim = null;
        try {
            PDDocument document = PDDocument.load(new File(fil));
            if (document != null) {
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                bim = new BufferedImage[document.getNumberOfPages()];
                for (int i = 0; i < document.getNumberOfPages(); i++) {
                    bim[i] = pdfRenderer.renderImage(i);
                }
                document.close();
            } else {
                System.out.println("nulo");
            }
        } catch (IOException ex) {
            Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bim;
    }

    /**
     * @see
     * http://stackoverflow.com/questions/3701644/how-can-i-get-the-total-number-of-pages-to-be-printed
     */
    int getNumberOfPages(PageRanges pageRanges) {
        if (doc != null) {
            PDDocument doco;
            try {
                doco = PDDocument.load(file);
            } catch (IOException ex) {
                Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
                doco = null;
            }
            if (doco != null) {
                int pages = 0;
                int[][] ranges = pageRanges.getMembers();
                for (int i = 0; i < ranges.length; i++) {
                    pages += 1;
                    if (ranges[i].length == 2) {
                        pages += ranges[i][1] - ranges[i][0];
                    }
                }
                pages = Math.min(pages, doco.getNumberOfPages());
                return pages;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void imprimeParametros() {
        Attribute[] at = parametros.toArray();
        int hg = 0;
        while (hg < at.length) {
            System.out.println(at[hg].getName());
            hg++;
        }
    }

    public int showDialog(javax.swing.JDialog dialogo) {
        java.util.ArrayList<javax.print.PrintService> servicos = new java.util.ArrayList<>();
        for (PrintService impressora : impressoras) {
            servicos.add(impressora);
        }
        if (servicos.size() > 0) {
            javax.swing.JPanel panel = new javax.swing.JPanel(null);
            panel.setPreferredSize(new java.awt.Dimension(700, 400));
            boolean[] ativo = new boolean[5];
            for (int i = 0; i < ativo.length; i++) {
                ativo[i] = false;
            }
            javax.swing.JLabel label0 = new javax.swing.JLabel(lingua.translate("Impressora: "));
            label0.setPreferredSize(new java.awt.Dimension(160, 26));
            label0.setBounds(10, 60, 200, 26);

            panel.add(label0);
            String[] p = new String[servicos.size()];

            for (int il = 0; il < servicos.size(); il++) {
                p[il] = servicos.get(il).getName();
            }
            javax.swing.JComboBox<String> comboimpressoras = new javax.swing.JComboBox<>(p);
            comboimpressoras.setPreferredSize(new Dimension(140, 26));
            comboimpressoras.setBounds(210, 60, 180, 26);
            ((javax.swing.JLabel) comboimpressoras.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
            BasicComboPopup popupVista = (BasicComboPopup) comboimpressoras.getAccessibleContext().getAccessibleChild(0);
            popupVista.getList().setSelectionBackground(Color.DARK_GRAY);
            popupVista.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
            comboimpressoras.setBackground(new Color(213, 213, 213));
            comboimpressoras.setFocusable(false);
            Components.MessagePane mensagem = new Components.MessagePane(dialogo, Components.MessagePane.ACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Opções de impressão"), 800, 500, panel, "", new String[]{lingua.translate("Imprimir"), lingua.translate("Voltar")});
            comboimpressoras.addItemListener((ItemEvent e) -> {
                for (int j = 9; j < panel.getComponentCount() - 1; j++) {
                    if (panel.getComponent(j) instanceof javax.swing.JComboBox<?>) {
                        panel.remove(9);
                    }
                }
                drawcomponentsPanel(panel, ativo, mensagem, comboimpressoras.getSelectedIndex(), false);
            });
            panel.add(comboimpressoras);
            if (drawcomponentsPanel(panel, ativo, mensagem, comboimpressoras.getSelectedIndex(), true) == 1) {
                return comboimpressoras.getSelectedIndex();
            } else {
                return -1;
            }
        } else {
            Components.MessagePane mensagem = new Components.MessagePane(dialogo, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Erro de impressão"), 400, 200, lingua.translate("Não existem impressoras instaladas") + ".", new String[]{lingua.translate("Voltar")});
            return mensagem.showMessage() - 2;
        }
    }

    private int drawcomponentsPanel(javax.swing.JPanel panel, boolean[] ativo, Components.MessagePane mensagem, int val, boolean criacao) {
        Class<?>[] cla = impressoras[val].getSupportedAttributeCategories();
        for (Class<?> cla1 : cla) {
            if (cla1.getName().equals("javax.print.attribute.standard.Copies")) {
                ativo[0] = true;
            } else if (cla1.getName().equals("javax.print.attribute.standard.PageRanges")) {
                ativo[1] = true;
            } else if (cla1.getName().equals("javax.print.attribute.standard.Sides")) {
                ativo[2] = true;
            } else if (cla1.getName().equals("javax.print.attribute.standard.PrintQuality")) {
                ativo[3] = true;
            } else if (cla1.getName().equals("javax.print.attribute.standard.Chromaticity")) {
                ativo[4] = true;
            }
        }
        javax.swing.JLabel label1 = new javax.swing.JLabel(lingua.translate("Número de cópias") + ": ");
        javax.swing.JSpinner spincopias = new javax.swing.JSpinner();
        javax.swing.JLabel labelmax = new javax.swing.JLabel(lingua.translate("até"));
        javax.swing.JLabel labelmin = new javax.swing.JLabel(lingua.translate("desde"));
        javax.swing.JLabel label2 = new javax.swing.JLabel(lingua.translate("Intervalo de impressão") + ": ");
        javax.swing.JSpinner spinmin = new javax.swing.JSpinner();
        javax.swing.JSpinner spinmax = new javax.swing.JSpinner();
        if (criacao) {
            label1.setPreferredSize(new java.awt.Dimension(160, 30));
            label1.setBounds(10, 110, 200, 30);
            panel.add(label1);
            javax.swing.JSpinner.NumberEditor editor2 = (javax.swing.JSpinner.NumberEditor) spincopias.getEditor();
            editor2.getFormat().setGroupingUsed(false);
            editor2.getModel().setMaximum(9999999);
            editor2.getModel().setMinimum(1);
            editor2.getModel().setValue(1);
            editor2.getModel().setStepSize(1);
            editor2.getTextField().setSelectionColor(Color.DARK_GRAY);
            spincopias.setPreferredSize(new Dimension(140, 30));
            editor2.getTextField().setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            spincopias.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)));
            spincopias.setBounds(210, 110, 180, 30);
            if (!ativo[0]) {
                spincopias.setEnabled(false);
            }
            panel.add(spincopias);
            label2.setPreferredSize(new java.awt.Dimension(160, 26));
            label2.setBounds(10, 160, 200, 26);
            panel.add(label2);
            labelmax.setPreferredSize(new java.awt.Dimension(70, 26));
            labelmax.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            labelmax.setBounds(310, 135, 70, 26);
            labelmax.setFont(new Font("Cantarell", Font.PLAIN, 11));
            panel.add(labelmax);

            javax.swing.JSpinner.NumberEditor editormax = (javax.swing.JSpinner.NumberEditor) spinmax.getEditor();
            editormax.getFormat().setGroupingUsed(false);
            if (doc != null) {
                editormax.getModel().setMaximum(doc.getNumberOfPages());
                editormax.getModel().setValue(doc.getNumberOfPages());
            } else {
                editormax.getModel().setMaximum(1);
                editormax.getModel().setValue(1);
            }
            editormax.getModel().setMinimum(1);
            editormax.getModel().setStepSize(1);
            editormax.getTextField().setSelectionColor(Color.DARK_GRAY);
            spinmax.setPreferredSize(new Dimension(80, 30));
            spinmax.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)));
            spinmax.setBounds(310, 160, 80, 30);
            if (!ativo[1]) {
                spinmax.setEnabled(false);
            }
            panel.add(spinmax);

            labelmin.setFont(new Font("Cantarell", Font.PLAIN, 11));
            labelmin.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            labelmin.setPreferredSize(new java.awt.Dimension(70, 26));
            labelmin.setBounds(210, 135, 70, 26);
            panel.add(labelmin);

            javax.swing.JSpinner.NumberEditor editormin = (javax.swing.JSpinner.NumberEditor) spinmin.getEditor();
            editormin.getFormat().setGroupingUsed(false);
            if (doc != null) {
                editormin.getModel().setMaximum(doc.getNumberOfPages());
            } else {
                editormin.getModel().setMaximum(1);
            }
            editormin.getModel().setValue(1);
            editormin.getModel().setMinimum(1);
            editormin.getModel().setStepSize(1);
            editormin.getTextField().setSelectionColor(Color.DARK_GRAY);
            spinmin.setPreferredSize(new Dimension(80, 30));
            spinmin.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)));
            spinmin.setBounds(210, 160, 80, 30);
            if (!ativo[1]) {
                spinmin.setEnabled(false);
            }
            panel.add(spinmin);
        }

        Sides[] res = (Sides[]) impressoras[val].getSupportedAttributeValues(Sides.class, null, null);
        java.util.Set<Integer> valores = new java.util.HashSet<>();
        java.util.List<String> vals = new java.util.ArrayList<>();
        if (res != null) {
            for (Sides sides : res) {
                if (sides instanceof Sides) {
                    valores.add(sides.getValue());
                }
            }
            if (valores.contains(Sides.ONE_SIDED.getValue())) {
                vals.add(lingua.translate("apenas um lado"));
            }
            if (valores.contains(Sides.DUPLEX.getValue())) {
                vals.add(lingua.translate("duplex"));
            }
            if (valores.contains(Sides.TUMBLE.getValue())) {
                vals.add(lingua.translate("tumble"));
            }
        }
        if (criacao) {
            javax.swing.JLabel label3 = new javax.swing.JLabel(lingua.translate("Impressão da página") + ": ");
            label3.setPreferredSize(new java.awt.Dimension(160, 26));
            label3.setBounds(10, 210, 200, 26);
            panel.add(label3);
        }
        javax.swing.JComboBox<Object> combosides = new javax.swing.JComboBox<>(vals.toArray());
        combosides.setPreferredSize(new Dimension(140, 26));
        combosides.setBounds(210, 210, 180, 26);
        combosides.setBackground(new Color(213, 213, 213));
        combosides.setFocusable(false);
        ((javax.swing.JLabel) combosides.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        BasicComboPopup popupVista = (BasicComboPopup) combosides.getAccessibleContext().getAccessibleChild(0);
        popupVista.getList().setSelectionBackground(Color.DARK_GRAY);
        popupVista.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        if (!ativo[2]) {
            combosides.setEnabled(false);
        }
        panel.add(combosides);

        PrintQuality[] pri = (PrintQuality[]) impressoras[val].getSupportedAttributeValues(PrintQuality.class, null, null);
        vals = new java.util.ArrayList<>();
        vals.add(lingua.translate("normal"));
        vals.add(lingua.translate("baixa"));
        vals.add(lingua.translate("alta"));
        if (criacao) {
            javax.swing.JLabel label4 = new javax.swing.JLabel(lingua.translate("Qualidade da impressão") + ": ");
            label4.setPreferredSize(new java.awt.Dimension(160, 26));
            label4.setBounds(10, 260, 200, 26);
            panel.add(label4);
        }
        javax.swing.JComboBox<Object> comboqualidade = new javax.swing.JComboBox<>(vals.toArray());
        ((javax.swing.JLabel) comboqualidade.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        popupVista = (BasicComboPopup) comboqualidade.getAccessibleContext().getAccessibleChild(0);
        popupVista.getList().setSelectionBackground(Color.DARK_GRAY);
        popupVista.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        comboqualidade.setBackground(new Color(213, 213, 213));
        comboqualidade.setFocusable(false);
        comboqualidade.setPreferredSize(new Dimension(140, 26));
        comboqualidade.setBounds(210, 260, 180, 26);
        if (!ativo[3]) {
            comboqualidade.setEnabled(false);
        }
        panel.add(comboqualidade);

        Chromaticity[] chr = (Chromaticity[]) impressoras[val].getSupportedAttributeValues(Chromaticity.class, null, null);
        valores = new java.util.HashSet<>();
        vals = new java.util.ArrayList<>();
        if (chr != null) {
            for (Chromaticity c : chr) {
                valores.add(c.getValue());
            }
            if (valores.contains(Chromaticity.COLOR.getValue())) {
                vals.add(lingua.translate("cores"));
            }
            if (valores.contains(Chromaticity.MONOCHROME.getValue())) {
                vals.add(lingua.translate("monocromático"));
            }
        }
        if (criacao) {
            javax.swing.JLabel label5 = new javax.swing.JLabel(lingua.translate("Cor da impressão") + ": ");
            label5.setPreferredSize(new java.awt.Dimension(160, 26));
            label5.setBounds(10, 310, 200, 26);
            panel.add(label5);
        }
        javax.swing.JComboBox<Object> combocores = new javax.swing.JComboBox<>(vals.toArray());
        ((javax.swing.JLabel) combocores.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        popupVista = (BasicComboPopup) combocores.getAccessibleContext().getAccessibleChild(0);
        popupVista.getList().setSelectionBackground(Color.DARK_GRAY);
        popupVista.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        combocores.setBackground(new Color(213, 213, 213));
        combocores.setFocusable(false);
        combocores.setPreferredSize(new Dimension(140, 26));
        combocores.setBounds(210, 310, 180, 26);
        if (!ativo[4]) {
            comboqualidade.setEnabled(false);
        }
        panel.add(combocores);

        if (criacao) {
            javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(null);
            scroll.setPreferredSize(new Dimension(260, 310));
            scroll.setBounds(420, 40, 260, 310);
            scroll.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            javax.swing.JPanel panelimagem = new javax.swing.JPanel();
            panelimagem.setPreferredSize(new Dimension(250, 0));
            panelimagem.setBounds(0, 0, 250, 300);

            panelimagem.setBackground(new Color(205, 205, 205));
            panelimagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            panelimagem.addMouseListener(new MouseAdapter() {
                Color cor = panelimagem.getBackground();

                @Override
                public void mousePressed(MouseEvent e) {
                    panelimagem.setBackground(Color.BLACK);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    try {
                        panelimagem.setBackground(cor);
                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().open(file);
                        }  else {
                            Components.MessagePane mensagem = new Components.MessagePane(dialogo, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Nota"), 400, 200, lingua.translate("O sistema operativa não permite ações de chamada") + ".", new String[]{lingua.translate("Voltar")});
                            mensagem.showMessage();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            if (doc != null) {
                javax.swing.JLabel labelimagem;
                ImageIcon icon;
                for (int i = 0; i < doc.getNumberOfPages(); i++) {
                    labelimagem = new javax.swing.JLabel();
                    labelimagem.setBorder(BorderFactory.createLineBorder(Color.black));
                    icon = new ImageIcon(FileIOAux.ImageAux.resize(PrintAux.pdfToImage(file.getName())[i], 220, 280));
                    labelimagem.setPreferredSize(new Dimension(220, 280));
                    labelimagem.setIcon(icon);
                    panelimagem.add(labelimagem);
                    panelimagem.setPreferredSize(new Dimension(240, (int) panelimagem.getPreferredSize().getHeight() + 288));

                }
            }
            scroll.setViewportView(panelimagem);
            scroll.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, true, true, true),BorderFactory.createLineBorder(Color.BLACK, 1)));
            panel.add(scroll);
        }
        if (mensagem.showMessage() == 1) {
            if (ativo[0]) {
                ncopias = (int) spincopias.getValue();
            }
            if (ativo[1]) {
                valorminimo = (int) spinmin.getValue();
                valormaximo = (int) spinmax.getValue();
            }
            if (ativo[2]) {
                valorlados = combosides.getSelectedIndex();
            }
            if (ativo[3]) {
                valorqualidade = combosides.getSelectedIndex();

            }
            javax.print.attribute.standard.Chromaticity cor = Chromaticity.COLOR;
            if (ativo[4]) {
                switch (combocores.getSelectedIndex()) {
                    case 1:
                        cor = Chromaticity.MONOCHROME;
                        break;
                    default:
                        cor = Chromaticity.COLOR;
                        break;
                }
            }
            //this.setAttributes(copias, sides, ranges, MediaSizeName.ISO_A4, qualidade, cor, orienta);
            return 1;
        }
        return 0;
    }

    /**
     * @return the doc
     */
    public PDDocument getDocument() {
        return doc;
    }
}

class paintContent implements Printable {

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        System.out.println("Page index = " + pageIndex);
        // pageIndex 1 corresponds to page number 2.
        if (pageIndex > 2) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2 = (Graphics2D) g;

        double w = pf.getImageableWidth();
        double h = pf.getImageableHeight();

        int xo = (int) pf.getImageableX();
        int yo = (int) pf.getImageableY();

        Rectangle2D r = new Rectangle2D.Double(xo, yo, w, h);

        g2.setColor(Color.red);
        g2.draw(r);

        return Printable.PAGE_EXISTS;
    }
}

class paintCover implements Printable {

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        Graphics2D g2 = (Graphics2D) g;

        double w = pf.getImageableWidth();
        double h = pf.getImageableHeight();

        int xo = (int) pf.getImageableX();
        int yo = (int) pf.getImageableY();

        Rectangle2D r = new Rectangle2D.Double(xo, yo, w, h);

        g2.setColor(Color.red);
        g2.draw(r);

        PrinterGraphics p = (PrinterGraphics) g2;
        String s = p.getPrinterJob().getJobName();

        g2.setPaint(Color.black);
        g2.drawString(s, 0, 0);

        return Printable.PAGE_EXISTS;
    }
}
