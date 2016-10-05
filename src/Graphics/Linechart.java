/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author toze
 */
public class Linechart extends javax.swing.JFrame {
    
    private static final long serialVersionUID = 365L;
    private java.util.List<Statistic.MonthRequests> valores;
    private Langs.Locale lingua;
    private int quantidade;
    JFreeChart grafico;
    int altura;
    int largura;
    String titulos;
    String xtitle;
    String ytitle;

    public Linechart(String title, String xtitle, String ytitle, java.util.List<Statistic.MonthRequests> valores, Langs.Locale lingua, int quantidade) {
        super(title);
        this.valores = valores;
        this.lingua = lingua;
        this.quantidade = quantidade;
        grafico = null;
        this.xtitle = xtitle;
        this.ytitle = ytitle;
        
    }

    public void createGraphic(String titulo, int largura, int altura) {
        CategoryDataset data = this.createDataset();
        grafico = ChartFactory.createLineChart(titulo, xtitle, ytitle, data, PlotOrientation.VERTICAL, true, false, false);
        this.altura = altura;
        this.largura = largura;
        ChartPanel ch = new ChartPanel(grafico);
        ch.setSize(largura, altura);
        ch.setBounds(0, 0, largura, altura);
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(ch);
    }

    public void showGraphic() {
        this.setSize(largura, altura);
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Collections.sort(valores);
        for (int h = 0; h < valores.size(); h++) {
            dataset.setValue(valores.get(h).getNumber(),lingua.translate(valores.get(h).getType().getTypeOfMaterialName()), valores.get(h).getDate().toString());
            if (h == quantidade - 1) {
                break;
            }
        }
        return dataset;
    }

    public void createImage(String nome, boolean jpg, int larg, int alt) {
        File pieChart = new File(nome);
        if (jpg) {
            try {
                ChartUtilities.saveChartAsJPEG(pieChart, grafico, larg, alt);
            } catch (IOException ex) {
                Logger.getLogger(Piechart.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                ChartUtilities.saveChartAsPNG(pieChart, grafico, larg, alt);
            } catch (IOException ex) {
                Logger.getLogger(Piechart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
