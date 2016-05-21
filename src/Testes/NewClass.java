/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

/**
 *
 * @author toze
 */
public class NewClass {
    public static void main(String[] args) {
        String teste = "12345l6.,";
        
        System.out.println(Text.TreatText.getNumberfromString(teste));
        
        TimeDate.HolidaysList lista = new TimeDate.HolidaysList(2016);
        Main.FileHolidays file = new Main.FileHolidays();
        lista = file.getDefaultHolidaysList();
        lista.addCarnival();
        lista.addCorpusChristi();
        lista.addEaster();
        lista.addGoodFriday();
        for (TimeDate.Holiday hol : lista.getHolidays()) {
            //if (TimeDate.DinamicHoliday.class.isInstance(hol)) System.out.println("true");
            if (hol instanceof TimeDate.DinamicHoliday)  System.out.println("true");
        }
        file.saveHolidays(lista);
        for (TimeDate.Holiday hol : lista.getHolidays()) {
            System.out.println(hol.toString());
            lista.getHolidays();
        }
    }
}
