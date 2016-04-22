/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import CSV.ElementsCSV;
import CSV.HandlingCSV;
import CSV.ObjectCSV;
import TimeDate.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author toze
 */
public class RequestList {
    private String bd;
    private String csv;
    private Clavis.TypeOfMaterial material;
    private Clavis.Function funcao;
    private TimeDate.Date date1;
    private TimeDate.Date date2;
    private HolidaysList feriados;
    private Set<Clavis.Request> requests;
    
    public RequestList(String bd, String csv, Clavis.TypeOfMaterial material, Clavis.Function funcao, TimeDate.Date date1, TimeDate.Date date2, HolidaysList feriados) {
        this.bd = bd;
        this.csv = csv;
        this.material = material;
        this.funcao = funcao;
        this.date1 = date1;
        this.date2 = date2;
        this.requests = new TreeSet<>();
        this.feriados = feriados;
    }
    
     public RequestList(RequestList req) {
        this.bd = req.getBd();
        this.csv = req.getCsv();
        this.material = req.getTypeOfMaterial();
        this.funcao = req.getFunction();
        this.date1 = req.getDate1();
        this.date2 = req.getDate2();
        this.requests = new TreeSet<>();
        this.feriados = req.feriados;
    }
    
    public void reMake(TimeDate.Date date1, TimeDate.Date date2, HolidaysList Holdays){
        this.date1 = date1;
        this.date2 = date2;
        HandlingCSV han = new HandlingCSV(csv);
        han.searchElements();
        List<ElementsCSV> elementos = han.getElements();
        this.requests = new TreeSet<>();
        if (elementos.size() > 0){
            int i = 0;
            int k = 0;
            boolean bauxiliar= false;
            while (i < elementos.size()){
                    ObjectCSV aux = new ObjectCSV(elementos.get(i),this.funcao,this.material);
                    List<Date> datass = Date.DatesBetweenDates(this.date1, this.date2, aux.getWeekDay().getDayNumber());
                    if (datass.size() > 0) {
                        for (int j=0; j< datass.size();j++) { 
                            for (TimeDate.Holiday h: this.feriados.getHolidays()){
                                if ((datass.get(j).getDay() == h.getDay())&&(datass.get(j).getMonth() == h.getMonth())) {
                                    bauxiliar = true;
                                }
                            }
                            if (!bauxiliar) this.requests.add(aux.getRequest(datass.get(j)));
                            bauxiliar = false;
                        }
                    }
                i++;
            }
        }
    }
    
    public Clavis.Request getSelectedRequest(int valor) {
        Clavis.Request[] requests2 = new Clavis.Request[this.requests.size()];
        requests2 = this.requests.toArray(requests2);
        return requests2[valor];
    }
    
    public void make(){
        HandlingCSV han = new HandlingCSV(csv);
        han.searchElements();
        List<ElementsCSV> elementos = han.getElements();
        this.requests = new TreeSet<>();
        if (elementos.size() > 0){
            int i = 0;
            int k = 0;
            boolean bauxiliar= false;
            while (i < elementos.size()){
                    ObjectCSV aux = new ObjectCSV(elementos.get(i),this.funcao,this.material);
                    List<Date> datass = Date.DatesBetweenDates(this.date1, this.date2, aux.getWeekDay().getDayNumber());
                    if (datass.size() > 0) {
                        for (int j=0; j< datass.size();j++) { 
                            for (TimeDate.Holiday h: this.feriados.getHolidays()){
                                if ((datass.get(j).getDay() == h.getDay())&&(datass.get(j).getMonth() == h.getMonth())) {
                                    bauxiliar = true;
                                }
                            }
                            if (!bauxiliar) this.requests.add(aux.getRequest(datass.get(j)));
                            bauxiliar = false;
                        }
                    }
                i++;
            }
        }
    }

    /**
     * @return the bd
     */
    public String getBd() {
        return bd;
    }

    /**
     * @param bd the bd to set
     */
    public void setBd(String bd) {
        this.bd = bd;
    }

    /**
     * @return the csv
     */
    public String getCsv() {
        return csv;
    }

    /**
     * @param csv the csv to set
     */
    public void setCsv(String csv) {
        this.csv = csv;
    }

    /**
     * @return the material
     */
    public Clavis.TypeOfMaterial getTypeOfMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setTypeOfMaterial(Clavis.TypeOfMaterial material) {
        this.material = material;
    }

    /**
     * @return the funcao
     */
    public Clavis.Function getFunction() {
        return funcao;
    }

    /**
     * @param funcao the funcao to set
     */
    public void setFunction(Clavis.Function funcao) {
        this.funcao = funcao;
    }

    /**
     * @return the date1
     */
    public TimeDate.Date getDate1() {
        return date1;
    }

    /**
     * @param date1 the date1 to set
     */
    public void setDate1(TimeDate.Date date1) {
        this.date1 = date1;
    }

    /**
     * @return the date2
     */
    public TimeDate.Date getDate2() {
        return date2;
    }

    /**
     * @param date2 the date2 to set
     */
    public void setDate2(TimeDate.Date date2) {
        this.date2 = date2;
    }

    /**
     * @return the requests
     */
    public Set<Clavis.Request> getRequests() {
        return requests;
    }

    /**
     * @return the feriados
     */
    public HolidaysList getHolidays() {
        return feriados;
    }

    /**
     * @param feriados the feriados to set
     */
    public void setHolidays(HolidaysList feriados) {
        this.feriados = feriados;
    }
    
    
    
    
    
    
    
    
     
}
