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

    public BreakPeriodList() {
        lista = new java.util.ArrayList<>();
    }

    /*
    * constructor
     */
    public BreakPeriodList(java.util.List<TimeDate.BreakPeriod> list) {
        this.lista = list;
    }

    public boolean addBreakPeriod(TimeDate.BreakPeriod period) {
        return this.lista.add(period);
    }

    public boolean addBreakPeriod(TimeDate.Date period, TimeDate.Date period2, String name) {
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

    public void setSchoolDefaultValues() {
        this.setBreakPeriodList(showSchoolDefaultValues(new TimeDate.Date().getYear()).getBreakPeriodList());
    }
    
    public static BreakPeriodList showSchoolDefaultValues(int year) {
        TimeDate.Date date = new TimeDate.Date();
        date.setYear(year);
        TimeDate.Date auxiliar;
        TimeDate.Date auxiliar2;
        if (date.betweenDates(new TimeDate.Date(1, 1, year), new TimeDate.Date(31, 8, year))) {
            auxiliar = new TimeDate.Date(1, 1, year);
            auxiliar2 = new TimeDate.Date(1, 1, year);
        } else {
            auxiliar = new TimeDate.Date(1, 1, year + 1);
            auxiliar2 = new TimeDate.Date(1, 1, year + 1);
        }
        while (WeekDay.getDayWeek(auxiliar) != 1) {
            auxiliar.setDay(auxiliar.getDay() + 1);
        }
        BreakPeriod br = new BreakPeriod();
        br.setName("Natal");
        br.setEndDate(auxiliar);
        br.setBeginDate(auxiliar.dateBefore(13));
        BreakPeriod br2 = new BreakPeriod();
        br2.setName("Páscoa");
        TimeDate.Holiday hol = Holiday.getEaster(auxiliar.getYear());
        auxiliar2.setDay(hol.getDay());
        auxiliar2.setMonth(hol.getMonth());
        auxiliar2.setYear(auxiliar.getYear());
        br2.setBeginDate(auxiliar2.dateBefore(6));
        br2.setEndDate(br2.getBeginDate().dateAfter(7));
        BreakPeriodList ls = new BreakPeriodList();
        ls.addBreakPeriod(br);
        ls.addBreakPeriod(br2);
        return ls;
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
