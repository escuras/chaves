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
public class BreakPeriodList {
    private java.util.List<BreakPeriod> lista;
    
    public BreakPeriodList(){
        lista = new java.util.ArrayList<>();
    }
    
    /*
    * constructor
    */
    public BreakPeriodList(java.util.List list){
        this.lista = list;
    }
    
    public boolean addBreakPeriod(TimeDate.BreakPeriod period){
        return this.lista.add(period);
    }
    
    public boolean addBreakPeriod(TimeDate.Date period, TimeDate.Date period2, String name){
        return this.lista.add(new BreakPeriod(period, period2, name));
    }
    
    public boolean removePeriod(TimeDate.BreakPeriod period) {
        return this.lista.remove(period);
    }
    
    public boolean removePeriod(String name) {
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).getName().equals(name)) {
                this.lista.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterPeriod(String name, TimeDate.Date dat1, TimeDate.Date dat2) {
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).getName().equals(name)) {
                this.lista.get(i).setBeginDate(dat1);
                this.lista.get(i).setEndDate(dat2);
                return true;
            }
        }
        return false;
    }

    /**
     * @return the lista
     */
    public java.util.List<BreakPeriod> getBreakPeriodList() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setBreakPeriodList(java.util.List<BreakPeriod> lista) {
        this.lista = lista;
    }
    
    
}
