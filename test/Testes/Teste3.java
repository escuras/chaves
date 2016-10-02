/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import TimeDate.Date;
import TimeDate.Holiday;
import TimeDate.WeekDay;
import java.sql.Statement;
import java.text.ParseException;
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
     
        TimeDate.Date dat = new TimeDate.Date();
        java.util.ArrayList<TimeDate.Date> meses = new java.util.ArrayList<>();

        int mes = dat.getMonth();
        int ano = dat.getYear();
        int i = 0;
        int v = mes - 1;
        int j = 0;
        // meses.add(new TimeDate.Date(1,dat.getMonth(), dat.getYear()));
        while (i < 26) {
            mes = v % 12 + 1;
            v++;
            dat.setMonth(mes);
            dat.setYear(ano + j);
            if (v == 12) {
                v = 0;
                j++;
            }
            
            meses.add(new TimeDate.Date(1, dat.getMonth(), dat.getYear()));
            i++;
            System.out.println(dat.toString());
        }
        TimeDate.Time t =  new TimeDate.Time();
        System.out.println(t.toString());
        System.out.println(t.addSeconds(60*60*25).toString());
    }

}
