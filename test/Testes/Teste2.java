/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import CSV.ElementsCSV;
import CSV.HandlingCSV;
import CSV.ObjectCSV;
import Clavis.Function;
import Clavis.TypeOfMaterial;
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
        Function fun = new Function(1, "Professor", 1);
        HandlingCSV han = new HandlingCSV("http://localhost:8080/horario_disciplinas.csv");
        //han.searchElements();
        List<ElementsCSV> elementos = han.getElements();
        List<ObjectCSV> objetos = new ArrayList<>();
        Set<Clavis.Request> req = new TreeSet<>();
        Function funcao = new Function("Professor",2);
        TypeOfMaterial material = new TypeOfMaterial("Sala", 70);
        Date datainicio = new Date();
        Date datafim = new Date(-1460);
        System.out.println(datafim.toString());
        System.out.println(datafim.getDayYear());
        
        // datas entre periodos para calacular requisicoes
        
        Date dat = new Date(17,2,2016);
        Date dat2 = new Date(28,4,2016);
        
        
        //
        if (elementos != null) {
            System.out.println("ppp");
            System.out.println(elementos.size());
            int i = 0;
            while (i < elementos.size()){
                    ObjectCSV aux = new ObjectCSV(elementos.get(i));
                    List<Date> datass = Date.DatesBetweenDates(dat, dat2, aux.getWeekDay().getDayNumber());
                    for (int j=0; j< datass.size();j++) {
                        req.add(aux.getRequest(datass.get(j)));
                    }
                i++;
            }
        }
        
        for (Clavis.Request re : req) {
            System.out.print(re.getBeginDate().toString()+ " - "+re.getPerson().getName()+" - "+re.getMaterial().getDescription() + " - "+ re.getTimeBegin().toString(0));
            System.out.println("");
        }
        
        TimeDate.Time tempo = new TimeDate.Time();
        TimeDate.Time tempo2 = new TimeDate.Time(22, 43);
        System.out.println(tempo.compareTime(tempo2));
        
        TimeDate.Holiday hol = new TimeDate.Holiday(1, 12);
        TimeDate.Date dat3 = hol.setExpanded();
        //System.out.println(hol.isExpanded());
        //if (hol.isExpanded()) System.out.println(dat3.toString());
        System.out.println(TimeDate.WeekDay.getDayWeek(dat3.dateBefore(365*2018-271)));
        System.out.println(TimeDate.Date.numberOfDaysBetweenDates(dat,dat));
        TimeDate.BreakPeriodList br = new TimeDate.BreakPeriodList();
        br.setSchoolDefaultValues();
        System.out.println(br.getBreakPeriodList().get(1).toString());
         System.out.println(br.getBreakPeriodList().get(0).toString());
    }
}
