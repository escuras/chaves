/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import CSV.ElementsCSV;
import CSV.HandlingCSV;
import CSV.ObjectCSV;
import Keys.Function;
import Keys.TypeOfMaterial;
import TimeDate.Date;
import TimeDate.WeekDay;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.*;
/**
 *
 * @author toze
 */
public class Teste2 {
    public static void main(String[] args){
        
        
        Date dat = new Date();
        System.out.println(dat.toString());
        Date dat2 = new Date(1,1,2016);
        WeekDay d = new WeekDay(WeekDay.getDayWeek(dat));
        System.out.println(d.perDayName());
        TimeDate.Time tim1 = new TimeDate.Time();
         TimeDate.Time tim2 = new TimeDate.Time(22,34,44);
         System.out.println(tim1.compareTime(tim2));
         System.out.println(dat.isBigger(dat2));
    }
}
