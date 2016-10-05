/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Toolkit;

/**
 *
 * @author toze
 */
public class PersonalCounter {

    public static final java.awt.Color BACKGROUND_COLOR = new java.awt.Color(255, 255, 255);
    public static final java.awt.Color BORDER_COLOR = new java.awt.Color(155, 155, 155);

    long inicio;
    long tempopassado;
    boolean cond;
    boolean abriumensagem;
    String mensagem;
    Components.MessagePane pan;
    Langs.Locale lingua;
    final javax.swing.JDialog dialogo;
    java.awt.Color corfundo;
    java.awt.Color corborda;

    public PersonalCounter() {
        super();
        mensagem = "";
        dialogo = new javax.swing.JDialog();
        abriumensagem = false;
        cond = true;
        tempopassado = 0;
        inicio = 0;
        this.lingua = Langs.Locale.getLocale_pt_PT();
        corfundo = BACKGROUND_COLOR;
        corborda = BORDER_COLOR;
    }

    public PersonalCounter(String mensagem, Langs.Locale lingua, java.awt.Color corfundo, java.awt.Color corborda) {
        super();
        this.mensagem = mensagem;
        abriumensagem = false;
        cond = true;
        tempopassado = 0;
        dialogo = new javax.swing.JDialog();
        inicio = 0;
        this.lingua = lingua;
        this.corfundo = corfundo;
        this.corborda = corborda;
    }

    public void start(){
        showMessage();
    }

    public long getTimeElapsed() {
        return tempopassado;
    }

    public void end() {
        cond = false;
        if (abriumensagem) {
            this.closeWindow();
        }

    }

    public String getMessage() {
        return mensagem;
    }

    public void setMessage(String mensagem) {
        this.mensagem = mensagem;
    }

    public void showMessage() {
        int altura = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 100;
        int largura = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200;
        dialogo.setLocation(largura, altura);
        dialogo.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        dialogo.setPreferredSize(new java.awt.Dimension(400, 200));
        dialogo.setResizable(false);
        dialogo.setMinimumSize(new java.awt.Dimension(400, 200));
        dialogo.setMaximumSize(new java.awt.Dimension(400, 200));
        dialogo.setBackground(new java.awt.Color(255, 255, 255));
        dialogo.setTitle(lingua.translate("Mensagem de espera"));
        dialogo.setUndecorated(true);
        javax.swing.JPanel panel = new javax.swing.JPanel(null);
        panel.setPreferredSize(new java.awt.Dimension(400, 200));
        panel.setBackground(corfundo);
        panel.setBounds(0, 0, 400, 200);
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1), 1), javax.swing.BorderFactory.createLineBorder(corborda, 2));
        panel.setBorder(border22);
        javax.swing.JLabel l = new javax.swing.JLabel(mensagem);
        l.setPreferredSize(new java.awt.Dimension(300, 30));
        l.setBounds(50, 20, 300, 30);
        panel.add(l);
        javax.swing.JProgressBar bar = new javax.swing.JProgressBar();
        bar.setPreferredSize(new java.awt.Dimension(200, 30));
        bar.setBounds(100, 80, 200, 30);
        bar.setIndeterminate(true);
        panel.add(bar);
        dialogo.add(panel);
        dialogo.pack();
        dialogo.setVisible(true);
    }

    public void closeWindow() {
        if (dialogo.isVisible()) {
            dialogo.setVisible(false);
            dialogo.dispose();
        }
        cond = true;
        inicio = 0;
    }

}
