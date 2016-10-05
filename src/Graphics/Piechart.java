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
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

public class Piechart extends javax.swing.JFrame {

    private static final long serialVersionUID = 665L;
    private java.util.List<Statistic.TypeNumber> valores;
    private Langs.Locale lingua;
    private int quantidade;
    JFreeChart grafico;
    int altura;
    int largura;
    String titulos;

    public Piechart(String title, java.util.List<Statistic.TypeNumber> valores, Langs.Locale lingua, int quantidade) {
        super(title);
        this.valores = valores;
        this.lingua = lingua;
        this.quantidade = quantidade;
        grafico = null;
    }

    public void createGraphic(String titulo, int largura, int altura) {
        PieDataset data = this.createDataset();
        grafico = ChartFactory.createPieChart(titulo, data, true, true, lingua.getSystemLocale());
        this.altura = altura;
        this.largura = largura;
        ChartPanel ch = new ChartPanel(grafico);
        ch.setSize(largura, altura);
        ch.setBounds(0, 0, largura, altura);
        this.add(ch);
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }

    public void showGraphic() {
        this.setSize(largura, altura);
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Collections.sort(valores);
        for (int h = 0; h < valores.size(); h++) {
            dataset.setValue(lingua.translate(valores.get(h).getTipo().getTypeOfMaterialName())+" "+lingua.translate(valores.get(h).getTipo().getDescription()), valores.get(h).getNumber());
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
