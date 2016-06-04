/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import java.net.URL;

/**
 *
 * @author toze
 */
public class NewClass {

    public static void main(String[] args) {
        String teste = "12345l6.,";

        System.out.println(Text.TreatText.getNumberfromString(teste));

        TimeDate.HolidaysList lista;
        Main.FileHolidays file = new Main.FileHolidays();
        lista = file.getDefaultHolidaysList();
        lista.addCarnival();
        lista.addCorpusChristi();
        lista.addEaster();
        lista.addGoodFriday();
        lista.getHolidays().stream().filter((hol) -> (hol instanceof TimeDate.DinamicHoliday)).forEach((_item) -> {
            System.out.println("true");
        }); //if (TimeDate.DinamicHoliday.class.isInstance(hol)) System.out.println("true");
        file.saveHolidays(lista);
        lista.getHolidays().stream().map((hol) -> {
            System.out.println(hol.toString());
            return hol;
        }).forEach((_item) -> {
            lista.getHolidays();
        });

        TimeDate.BreakPeriodList bt = new TimeDate.BreakPeriodList();
        bt.addBreakPeriod(new TimeDate.Date(19, 12, 2016), new TimeDate.Date(03, 01, 2017), "Natal");
        Main.FileBreakPeriods fl = new Main.FileBreakPeriods();
        fl.saveHolidays(bt);
        bt = fl.getBreakPeriods();
        for (TimeDate.BreakPeriod pt : bt.getBreakPeriodList()) {
            System.out.println(pt.toString());
        }
        qq q = new qq();
        System.out.println(q.path());
        q.cantar();
        q.falar();
        System.out.println(q.traduz("francês", "Amanhecer"));
        
    }

}

class qq extends qql implements qqo{
     public String path() {
        URL url1 = getClass().getResource("");
        String ur = url1.toString();
        ur = ur.substring(9);
        String truepath[] = ur.split("Clavis.jar!");
        truepath[0] = truepath[0].replaceAll("%20", " ");
        return truepath[0];
    }//This methos will work on Windows and Linux as well.

    @Override
    public void falar() {
        System.out.println("Estou a falar em "+this.getLingua());
    }

    @Override
    public void cantar() {
        System.out.println("Estou a cantar em "+this.getLingua());
    }

    @Override
    String traduz(String lingua, String palavra) {
        return "Ainda estou em desenvolvimento.";
    }
}



interface qqo {
    String isto = "ola";
    void falar();
    void cantar();
    
    static void andar(){
        System.out.println("estou andando!");
    }
}

abstract class qql{
    
    private String lingua;
    public qql(){
        this.lingua = "portugues";
    }
    
    public qql(String lingua) {
        this.lingua = lingua;
    }
    
    public String getLingua(){
        return this.lingua;
    }
    
    abstract String traduz(String lingua, String palavra);
    
    
} 
