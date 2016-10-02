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
public class SemesterList {

    private Semester[] lista;

    public SemesterList() {
        lista = new Semester[2];
    }

    public void addSemester(TimeDate.Semester period) {
        if (period.getPeriod() == 0) {
            this.lista[0] = period;
        } else {
            this.lista[1] = period;
        }
    }

    public Semester getFirstSemester() {
        return lista[0];
    }

    public Semester getLastSemester() {
        return lista[1];
    }
    
    public Semester dateInSemester(TimeDate.Date dat){
        Semester r1 = lista[0];
        Semester r2 = lista[0];
        if ((r1.getBeginDate().isBigger(dat) >= 0)&&(r1.getEndDate().isBigger(dat) <= 0)) {
            return r1;
        } else if ((r2.getBeginDate().isBigger(dat) >= 0)&&(r2.getEndDate().isBigger(dat) <= 0)) {
            return r2;
        } else if ((r1.getEndDate().isBigger(dat) > 0)&&(r2.getBeginDate().isBigger(dat) <= 0)){
            return r2;
        } 
        return r1;
    }
        
}
