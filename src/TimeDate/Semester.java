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
public class Semester {
    private TimeDate.Date date1;
    private TimeDate.Date date2;
    private short periodo;
    
    
    /**
     * empty construtor, same date on date1 and date2
     */
    public Semester(){
        this.date1 = new TimeDate.Date();
        this.date2 = new TimeDate.Date();
        periodo = 0;
    }
    
    /**
     * construtor
     * @param date1
     * @param date2
     * @param name
     */
    public Semester(TimeDate.Date date1, TimeDate.Date date2, short s){
        this.date1 = date1;
        this.date2 = date2;
        if (s <= 0) {
            s = 0;
        } 
        else if (s > 0) {
            s = 1;
        }
        this.periodo = s;
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
    public short getPeriod() {
        return periodo;
    }

    /**
     * @param s the periodo to set
     */
    public void setPeriod(short s) {
        if (s <= 0) {
            s = 0;
        } 
        else if (s > 0) {
            s = 1;
        }
        this.periodo = s;
    }
    
    @Override
    public String toString(){
        if (periodo == 0) {
            return "1º Semestre" + ": " + this.getBeginDate().toString() + " - " + this.getEndDate().toString();
        } else {
            return "2º Semestre" + ": " + this.getBeginDate().toString() + " - " + this.getEndDate().toString();
        }
    }
    
    public String toString(Langs.Locale lingua){
        if (periodo == 0) {
            return lingua.translate("1º Semestre") + ": " + this.getBeginDate().toString() + " - " + this.getEndDate().toString();
        } else {
            return lingua.translate("2º Semestre") + ": " + this.getBeginDate().toString() + " - " + this.getEndDate().toString();
        }
    }
    
    public String toStringSimple(){
        return this.getPeriod() + ":" + this.getBeginDate().toString() + " - " + this.getEndDate().toString();
    }
}
