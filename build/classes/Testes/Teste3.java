/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import TimeDate.Date;
import TimeDate.Holiday;
import TimeDate.WeekDay;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toze
 */
public class Teste3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i = 0;
      
        TimeDate.Holiday hol = new TimeDate.Holiday(10,06);
        TimeDate.WeekDay dia = hol.getWeekDay(2020);
        
        TimeDate.Date date = new TimeDate.Date(27,3,2016);
        date = date.dateAfter(2222221);
        System.out.println(date.toString());
        date.setLanguage("fr-FR");
        try {
            WeekDay daat = new WeekDay(date);
            daat.setLanguage("fr-FR");
            System.out.println(daat.perDayName());
        } catch (ParseException ex) {
            Logger.getLogger(Teste3.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(date.dateBefore(1736054));
        TimeDate.Holiday holoo = new TimeDate.Holiday();
        TimeDate.Holiday [] dat = Holiday.getMobileHolidays(2078);
        if (dat.length > 0) {
            System.out.println("maior que 0");
        } else {
            System.out.println("zero");
        }
        
    }
    
}
