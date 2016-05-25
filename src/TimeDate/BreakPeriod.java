/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeDate;

/**
 *
 * @author toze
 */
public class BreakPeriod {
    private TimeDate.Date date1;
    private TimeDate.Date date2;
    private String nome;
    
    
    /**
     * empty construtor, same date on date1 and date2
     */
    public BreakPeriod(){
        this.date1 = new TimeDate.Date();
        this.date2 = new TimeDate.Date();
        nome = "";
    }
    
    /**
     * construtor
     * @param date1
     * @param date2
     * @param name
     */
    public BreakPeriod(TimeDate.Date date1, TimeDate.Date date2, String name){
        this.date1 = date1;
        this.date2 = date2;
        this.nome = name;
    }
    
    

    /**
     * @return the date1
     */
    public TimeDate.Date getBeginDate() {
        return date1;
    }

    /**
     * @param date1 the date1 to set
     */
    public void setBeginDate(TimeDate.Date date1) {
        this.date1 = date1;
    }

    /**
     * @return the date2
     */
    public TimeDate.Date getEndDate() {
        return date2;
    }

    /**
     * @param date2 the date2 to set
     */
    public void setEndDate(TimeDate.Date date2) {
        this.date2 = date2;
    }

    /**
     * @return the name of the break
     */
    public String getName() {
        return nome;
    }

    /**
     * @param name the nome to set
     */
    public void setName(String name) {
        this.nome = name;
    }
    
    @Override
    public String toString(){
        return this.getName() + ": " + this.getBeginDate().toString() + " - " + this.getEndDate().toString();
    }
    
    public String toStringSimple(){
        return this.getBeginDate().toString() + " - " + this.getEndDate().toString();
    }
    
    
    
    
    
    
}
