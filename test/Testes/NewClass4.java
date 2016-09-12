/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author toze
 */
public class NewClass4 {
    
public static void main(String[] args) throws IOException {
        
        File file = new File("test.pdf");
        TimeDate.Date datatop = new TimeDate.Date(0, 0, 0);
        //Langs.Locale lingua = Langs.Locale.getLocale_pt_PT();
        //FileIOAux.PrintAux print = new FileIOAux.PrintAux(file,lingua,null);
        //print.print();
        TimeDate.Date tim = new TimeDate.Date(12, 03, 2016);
        TimeDate.Date tim2 = new TimeDate.Date(12, 04, 2016);
        TimeDate.Time tem = new TimeDate.Time(12, 03, 03);
        TimeDate.Time tem2 = new TimeDate.Time(12, 04, 03);
        System.out.println(tim2.isBigger(tim));
        System.out.println(datatop.toString());
        System.out.println(tem.compareTime(tem2));
    }

}
