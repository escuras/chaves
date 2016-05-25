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
    }

}

class qq{
     public String path() {
        URL url1 = getClass().getResource("");
        String ur = url1.toString();
        ur = ur.substring(9);
        String truepath[] = ur.split("Clavis.jar!");
        truepath[0] = truepath[0].replaceAll("%20", " ");
        return truepath[0];
    }//This methos will work on Windows and Linux as well.
}
