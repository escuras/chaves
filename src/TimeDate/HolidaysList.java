/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeDate;

import Main.FileHolidays;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author toze
 */
public class HolidaysList {

    private int ano;
    private java.util.Set feŕiados;
    private final DinamicHoliday pascoa;
    private final DinamicHoliday carnaval;
    private final DinamicHoliday corpo_cristo;
    private final DinamicHoliday sexta_feira;

    public HolidaysList(int ano) {
        this.ano = ano;
        this.feŕiados = new java.util.TreeSet<>();
        TimeDate.Holiday[] hloi = TimeDate.Holiday.getMobileHolidays(ano);
        pascoa = new DinamicHoliday(hloi[2], "pascoa");;
        carnaval = new DinamicHoliday(hloi[0], "carnaval");
        corpo_cristo = new DinamicHoliday(hloi[3], "corpo_cristo");;
        sexta_feira = new DinamicHoliday(hloi[1], "sexta_feira");
    }

    public java.util.Set<TimeDate.Holiday> getHolidays() {
        return this.feŕiados;
    }

    /**
     * @param feriado add a holiday to a List
     */
    public void addHoliday(TimeDate.Holiday feriado) {
        this.getHolidays().add(feriado);
    }

    public void addHoliday(int dia, int mes) {
        this.getHolidays().add(new TimeDate.Holiday(dia, mes));
    }

    public void removeHoliday(TimeDate.Holiday feriado) {
        if (this.getHolidays().contains(feriado)) {
            this.getHolidays().remove(feriado);
        }
    }

    public int getIndex(TimeDate.Holiday feriado) {
        java.util.List<TimeDate.Holiday> feriad = new java.util.ArrayList<>(this.getHolidays());
        for (int i = 0; i < feriad.size(); i++) {
            if ((feriad.get(i).getDay() == feriado.getDay()) && (feriad.get(i).getMonth() == feriado.getMonth())) {
                return i;
            }
        }
        return 0;
    }

    public TimeDate.Holiday getHoliday(int val) {
        java.util.List<TimeDate.Holiday> feriad = new java.util.ArrayList<>(this.getHolidays());
        return feriad.get(val);
    }

    /**
     * @param feŕiados the List of Holidays to set
     */
    public void setHolidays(java.util.Set<TimeDate.Holiday> feŕiados) {
        this.feŕiados = feŕiados;
    }

    /**
     * @return the Year
     */
    public int getYear() {
        return ano;
    }

    /**
     * @param ano the year to set
     */
    public void setYear(int ano) {
        this.ano = ano;
    }

    public void removeCarnival() {
        this.feŕiados.remove(carnaval);
    }

    public TimeDate.Holiday getCarnival() {
        return this.carnaval;
    }

    public void removeCorpusChristi() {
        this.feŕiados.remove(this.corpo_cristo);
    }

    public TimeDate.Holiday getCorpusChristi() {
        return this.corpo_cristo;
    }

    public void removeGoodFriday() {
        this.feŕiados.remove(this.sexta_feira);
    }

    public TimeDate.Holiday getGoodFriday() {
        return this.sexta_feira;
    }

    public void removeEaster() {
        this.feŕiados.remove(this.pascoa);
    }

    public TimeDate.Holiday getEaster() {
        return this.pascoa;
    }

    public void addCarnival() {
        this.feŕiados.add(carnaval);
    }

    public void addCorpusChristi() {
        this.feŕiados.add(this.corpo_cristo);
    }

    public void addGoodFriday() {
        this.feŕiados.add(this.sexta_feira);
    }

    public void addEaster() {
        this.feŕiados.add(this.pascoa);
    }

    public boolean hasEaster() {
        return this.feŕiados.stream().anyMatch((hol) -> (hol.toString().equals(this.pascoa.toString())));
    }

    public boolean hasGoodFriday() {
        return this.feŕiados.stream().anyMatch((hol) -> (hol.toString().equals(this.sexta_feira.toString())));
    }

    public boolean hasCorpusChristi() {
        return this.feŕiados.stream().anyMatch((hol) -> (hol.toString().equals(this.corpo_cristo.toString())));
    }

    public boolean hasCarnival() {
        return this.feŕiados.stream().anyMatch((hol) -> (hol.toString().equals(this.carnaval.toString())));
    }

    public static void updateDynamicHolidays() {
        TimeDate.Date da = new TimeDate.Date();
        FileHolidays file = new FileHolidays();
        HolidaysList list = file.getHolidays();
        java.util.Map<Integer,TimeDate.Holiday> lista = new ConcurrentHashMap<>();
        int i = 0;
        for (TimeDate.Holiday day : list.getHolidays()) {
            if ( day instanceof TimeDate.DinamicHoliday) {
                DinamicHoliday d = (DinamicHoliday) day;
                switch (d.getName()) {
                    case "pascoa":
                        d = new TimeDate.DinamicHoliday(new HolidaysList(da.getYear()).getEaster(), "pascoa");
                        break;
                    case "sexta_feira":
                        d = new TimeDate.DinamicHoliday(new HolidaysList(da.getYear()).getGoodFriday(), "sexta_feira");
                        break;
                    case "corpo_cristo":
                        d = new TimeDate.DinamicHoliday(new HolidaysList(da.getYear()).getCorpusChristi(), "corpo_cristo");
                        break;
                    case "carnaval":
                        d = new TimeDate.DinamicHoliday(new HolidaysList(da.getYear()).getCarnival(), "carnaval");
                        break;
                    default:
                        break;
                }
                lista.put(i, d);
            } else {
                lista.put(i, day);
            }
            i++;
        }
        list.setHolidays(new java.util.HashSet(new java.util.HashSet(lista.values())));
        file.saveHolidays(list);
    }
}

