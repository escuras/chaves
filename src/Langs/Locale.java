/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Langs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 *
 * @author toze
 */
public class Locale {

    public static Locale getLocale_pt_PT(){
        Locale local = new Locale();
        local.setLocale("pt_PT");
        return local;
    }
    
    public static Locale getLocale_es_ES(){
        Locale local = new Locale();
        local.setLocale("es_ES");
        return local;
    }
     
    public static Locale getLocale_en_US(){
        Locale local = new Locale();
        local.setLocale("en_US");
        return local;
    } 
    
     public static Locale getLocale_fr_FR(){
        Locale local = new Locale();
        local.setLocale("fr_FR");
        return local;
    }
    
    public Collection<String> linguas;
    public String locale;
    public java.util.Locale systemlocale;
    public String traducao;

    public Locale() {
        try {
            InputStream st = Langs.Locale.class.getResourceAsStream("lista");
            linguas = new java.util.HashSet<>();
            if (st != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(st));
                String line = br.readLine();
                while (line != null) {
                    linguas.add(line);
                    line = br.readLine();
                }
            }
        } catch (Exception ex){
             linguas = new java.util.HashSet<>();
             linguas.add(java.util.Locale.getDefault().toString());
        }
        this.locale = "pt_PT";
        this.systemlocale = java.util.Locale.getDefault();
    }

    public void setLocale(String local) {
        if (this.linguas.size() > 0) {
            local = local.replaceAll("[-./|;,:]","_");
            if (this.linguas.contains(local)) {
                this.locale = local;
                String []aux = local.split("_");
                this.systemlocale = new java.util.Locale(aux[0],aux[1]);
            }
        } else {
            this.systemlocale = java.util.Locale.getDefault();
            this.locale = java.util.Locale.getDefault().toString();
            
        }
       
    }
    
    public String getLocale(){
        return (this.locale == null) ? "" : this.locale;
    }
    
    public java.util.Locale getSystemLocale(){
        return this.systemlocale;
    }
    
    public java.util.List<String> getlist(int modo){
        java.util.List<String> lista = new java.util.ArrayList<>();
        String [] val;
        for (String l : this.linguas){
            val = l.split("[-./|;,:_]");
            if (val.length == 2) {
                java.util.Locale loca = new java.util.Locale(val[0], val[1]);
                switch(modo) {
                    case 1:
                        lista.add(loca.getDisplayName());
                        break;
                    case 2:
                        lista.add(loca.getLanguage());
                        break;
                    case 3:
                        lista.add(loca.getISO3Language());
                        break;
                    default:
                        lista.add(loca.getDisplayLanguage());
                }
            }
        }
        return lista;
    }
    
    public String translate(String traducao){
        String [] valores = this.locale.split("[-./|;,:_]");
        this.traducao = null;
        if (valores.length == 2) {
            java.util.Locale loca = new java.util.Locale(valores[0], valores[1]);
            ResourceBundle sms = ResourceBundle.getBundle("Langs.MensagemBundle", loca);
            String auxiliar;
            if (traducao.contains(" ")) auxiliar = traducao.replace(" ", "_");
            else auxiliar = traducao;
            if ((sms.getLocale().toString().equals(this.locale)) && (sms.containsKey(auxiliar))) {
                this.traducao = sms.getString(auxiliar);
            }
        }
        if (this.traducao == null) this.traducao = traducao;
        return this.traducao;  
    }
    
     public String translateWithPlural(String traducao){
        String original = traducao;
        traducao += ".plural";
        String outra =  this.translate(traducao);
        if (traducao.equals(outra)) {
            return original;
        } 
        return outra;
    }
    
    
    public String translate(String traducao,String bundle){
        String [] valores = this.locale.split("[-./|;,:_]");
        this.traducao = null;
        if (valores.length == 2) {
            java.util.Locale loca = new java.util.Locale(valores[0], valores[1]);
            ResourceBundle sms = ResourceBundle.getBundle(bundle, loca);
            if ((sms.getLocale().toString().equals(this.locale)) && (sms.containsKey(traducao))) {
                this.traducao = sms.getString(traducao);
            }
        }
        if (this.traducao == null) this.traducao = traducao;
        return this.traducao;  
    }
}
