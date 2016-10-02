/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistic;

/**
 *
 * @author toze
 */
public class MonthRequests implements Comparable<MonthRequests> {
    private TimeDate.Date dat;
    private double numero;
    private Keys.TypeOfMaterial tipo;
    
    public MonthRequests(){
        this.dat = new TimeDate.Date();
        this.numero = 0;
        this.tipo = new Keys.TypeOfMaterial();
    }
    
    public MonthRequests(TimeDate.Date dat, double numero, Keys.TypeOfMaterial tipo){
        this.dat = dat;
        this.numero = numero;
        this.tipo = tipo;
    }

    public TimeDate.Date getDate() {
        dat= new TimeDate.Date(dat) {
            @Override
            public String toString(){
                return this.getMonth()+"/"+this.getYear();
            } 
        };
        return dat;
    }

    public void setmonth(TimeDate.Date dat) {
        this.dat = dat;
    }


    public double getNumber() {
        return numero;
    }

    public void setNumber(double numero) {
        this.numero = numero;
    }

    @Override
    public int compareTo(MonthRequests o) {
        return (int)(o.getNumber() - this.getNumber());
    }
    
    /**
     * @return the tipo
     */
    public Keys.TypeOfMaterial getType() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setType(Keys.TypeOfMaterial tipo) {
        this.tipo = tipo;
    }

}
