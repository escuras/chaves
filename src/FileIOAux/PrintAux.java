/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileIOAux;

import Clavis.Windows.WHorario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Paper;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.NumberOfDocuments;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.Sides;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author toze
 */
public class PrintAux {

    static HashPrintRequestAttributeSet parametros = new HashPrintRequestAttributeSet();
    static javax.print.PrintService[] impressoras = PrinterJob.lookupPrintServices();
    private final PrinterJob impressao;
    private Doc doc;
    private File file;
    private Langs.Locale lingua;

    public PrintAux(File file, Langs.Locale lingua) {
        super();
        this.lingua = lingua;
        impressao = PrinterJob.getPrinterJob();
        impressoras = PrinterJob.lookupPrintServices();
        parametros = new HashPrintRequestAttributeSet();
        this.file = file;
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.PDF, null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
            doc = null;
        }

       
        PageFormat portrait = impressao.defaultPage();
        portrait.setOrientation(PageFormat.LANDSCAPE);
        java.awt.print.Paper papel = new java.awt.print.Paper();
        papel.setSize(Paper.A4.getWidth(), Paper.A4.getHeight());
        //papel.setImageableArea(0, 0, 0, 0);
        portrait.setPaper(papel);

        parametros.add(MediaSizeName.ISO_A4);

        //copias
        parametros.add(new Copies(2));

        //sides
        parametros.add(Sides.DUPLEX);
        Class<?>[] cla = impressoras[0].getSupportedAttributeCategories();
        for (int i=0; i< cla.length ; i++) {
            System.out.println(cla[i].getName());
        }
        // qualidade
        parametros.add(PrintQuality.DRAFT);

        // cor ou cinza
        parametros.add(Chromaticity.MONOCHROME);

        //orientacao
        parametros.add(OrientationRequested.LANDSCAPE);

        //parametros.add(new PageRanges(1, doc.getNumberOfPages()));
        this.showDialog(null);

    }

    public void print() {
        
        /*
         if (impressao.printDialog(parametros)) {
            try {
                this.imprimeParametros();
                PageRanges pageRanges = (PageRanges) parametros.get(PageRanges.class);
                int pagesToBePrinted = getNumberOfPages(pageRanges);
                FileInputStream fis;
                try {

                    fis = new FileInputStream(new File("test.pdf"));
                    Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
                    DocPrintJob printJob = impressoras[0].createPrintJob();
                    try {
                        printJob.print(pdfDoc, parametros);
                    } catch (PrintException ex) {
                        Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    fis.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(WHorario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
                }
                impressao.print(parametros);
                System.out.println(pagesToBePrinted);
            } catch (PrinterException ex) {
                Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         */
    }

    private void setAttributes(int ncopias, Sides sides, MediaSizeName name, PrintQuality printquality, Chromaticity crom, OrientationRequested re) {
        if (doc != null) {
            Copies copias = new Copies(ncopias);
            parametros.add(copias);
            Sides lados = sides;
            parametros.add(lados);
            parametros.add(name);
            parametros.add(printquality);
            parametros.add(crom);
            parametros.add(re);
            PDDocument doco;
            try {
                doco = PDDocument.load(file);
            } catch (IOException ex) {
                Logger.getLogger(PrintAux.class.getName()).log(Level.SEVERE, null, ex);
                doco = null;
            }
            if (doco != null) {
                parametros.add(new PageRanges(1, doco.getNumberOfPages()));
            } else {
                parametros.add(new PageRanges(1, 1));
            }
        }
    }

    /**
     * @see
     * http://stackoverflow.com/questions/3701644/how-can-i-get-the-total-number-of-pages-to-be-printed
     */
    int getNumberOfPages(PageRanges pageRanges) {
        int pages = 0;
        int[][] ranges = pageRanges.getMembers();
        for (int i = 0; i < ranges.length; i++) {
            pages += 1;
            if (ranges[i].length == 2) {
                pages += ranges[i][1] - ranges[i][0];
            }
        }
        pages = Math.min(pages, 40);
        return pages;
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
        javax.swing.JPanel panel = new javax.swing.JPanel(null);
        panel.setPreferredSize(new java.awt.Dimension(400, 300));
        boolean [] ativo = new boolean[6];
        for (int i = 0; i < ativo.length ; i++) {
            ativo[i] = false;
        }
        javax.swing.JLabel label0 = new javax.swing.JLabel(lingua.translate("Impressora: "));
        label0.setPreferredSize(new java.awt.Dimension(160,26));
        label0.setBounds(20, 20, 160, 26);
        panel.add(label0);
        String [] p = new String[impressoras.length]; 
        for (int il = 0 ; il < impressoras.length ; il++) {
            p[il] = impressoras[il].getName();
        }
        javax.swing.JComboBox<String> comboimpressoras = new javax.swing.JComboBox<>(p);
        comboimpressoras.setPreferredSize(new Dimension(140,26));
        comboimpressoras.setBounds(180, 20, 180, 26);
        Components.MessagePane mensagem = new Components.MessagePane(dialogo, Components.MessagePane.ACAO, Clavis.KeyQuest.getSystemColor(), "", 500, 400, panel, "", new String[]{"Imprimir", "Voltar"});
       
        comboimpressoras.addActionListener((ActionEvent e) -> {
            this.drawcomponentsPanel(panel, ativo,mensagem,comboimpressoras.getSelectedIndex());
        });
        panel.add(comboimpressoras);
          this.drawcomponentsPanel(panel, ativo, mensagem, 0);
        if(mensagem.isVisible()) System.out.println("dddddddddddd");
        else System.out.println("ccccccccc");
       
       
        return 0;

    }
    
    private void drawcomponentsPanel(javax.swing.JPanel panel, boolean[] ativo, Components.MessagePane mensagem, int val){
        Class<?>[] cla = impressoras[val].getSupportedAttributeCategories();
        for (int i=0; i< cla.length ; i++) {
            if (cla[i].getName().equals("javax.print.attribute.standard.Copies")) {
                ativo[0] = true;
            } else if (cla[i].getName().equals("javax.print.attribute.standard.Sides")) {
                ativo[1] = true;
            }
            System.out.println(cla[i].getName());
        }
        javax.swing.JLabel label1 = new javax.swing.JLabel(lingua.translate("Número de cópias: "));
        label1.setPreferredSize(new java.awt.Dimension(160, 26));
        label1.setBounds(20, 60, 160, 26);
        panel.add(label1);
        javax.swing.JSpinner spincopias = new javax.swing.JSpinner();
        javax.swing.JSpinner.NumberEditor editor2 = (javax.swing.JSpinner.NumberEditor) spincopias.getEditor();
        editor2.getFormat().setGroupingUsed(false);
        editor2.getModel().setMaximum(9999999);
        editor2.getModel().setMinimum(1);
        editor2.getModel().setValue(1);
        editor2.getModel().setStepSize(1);
        editor2.getTextField().setSelectionColor(Color.DARK_GRAY);
        spincopias.setPreferredSize(new Dimension(140, 26));
        spincopias.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)));
        spincopias.setBounds(180, 60, 180, 26);
        if (!ativo[0]) {
            spincopias.setEnabled(false);
        }
        panel.add(spincopias);
        
        javax.swing.JLabel label2 = new javax.swing.JLabel(lingua.translate(""));
        label2.setPreferredSize(new java.awt.Dimension(160, 26));
        label2.setBounds(20, 100, 160, 26);
        panel.add(label2);
        
        javax.swing.JComboBox<String> combosides = new javax.swing.JComboBox<>(new String[]{lingua.translate("Apenas um lado"),lingua.translate("Duplex \"Normal\""), lingua.translate("Duplex \"Tumble\" ")});
        combosides.setPreferredSize(new Dimension(140,26));
        combosides.setBounds(180, 100, 180, 26);
        panel.add(combosides);
        Window[] w = Window.getWindows();
        boolean janelaaberta;
        
        if (mensagem.showMessage() == 1) {
           Copies copias = new Copies((int)spincopias.getValue());
        }
    }

    /**
     * @return the impressao
     */
    public PrinterJob getPrint() {
        return impressao;
    }

    /**
     * @return the doc
     */
    public Doc getDocument() {
        return doc;
    }
}
