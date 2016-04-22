/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeDate;

import Clavis.Request;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toze
 */
public class Holiday implements Comparable<Holiday> {
    private static final long serialVersionUID =1L;
    private int mes;
    private int dia;
    private Langs.Locale locale;
    private static Date pascoa;

    public Holiday() {
        mes = 0;
        dia = 0;
        locale = new Langs.Locale();
    }

    public Holiday(int dia, int mes) {
        this.mes = mes;
        this.dia = dia;
        locale = new Langs.Locale();
    }

    public Holiday(Holiday hol) {
        this.mes = hol.getMonth();
        this.dia = hol.getDay();
        locale = new Langs.Locale();
    }

    /**
     * @return the mes
     */
    public int getMonth() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMonth(int mes) {
        if ((mes > 0) && (mes < 13)) {
            this.mes = mes;
        } else {
            this.mes = 0;
        }
    }

    /**
     * @return the dia
     */
    public int getDay() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDay(int dia) {

        this.dia = dia;
    }

    /**
     * @return the String
     */
    @Override
    public String toString() {
        String sdia = "";
        String smes = "";
        if (dia < 10) {
            sdia = "0" + dia;
        } else {
            sdia = "" + dia;
        }
        if (mes < 10) {
            smes = "0" + mes;
        } else {
            smes = "" + mes;
        }
        return sdia + "/" + smes;
    }

    /**
     * @return the locale
     */
    public Langs.Locale getLanguage() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLanguage(Langs.Locale locale) {
        this.locale = locale;
    }

    public void setLanguage(String locale) {
        if (this.locale == null) {
            this.locale = new Langs.Locale();
        }
        this.locale.setLocale(locale);
    }

    public WeekDay getWeekDay(int ano) {
        Date dat = new Date(dia, mes, ano);
        WeekDay di = null;
        try {
            di = new WeekDay(dat);
        } catch (ParseException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
        }
        return di;
    }

    public static Holiday[] getMobileHolidays(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            Date corpo_de_Deus = pascoa.dateAfter(60);
            Date carnaval = pascoa.dateBefore(48);
            Date sexta_feira_santa = pascoa.dateBefore(2);
            Holiday[] holidays = {new Holiday(carnaval.getDay(),carnaval.getMonth()),
            new Holiday(sexta_feira_santa.getDay(),sexta_feira_santa.getMonth()),
            new Holiday(pascoa.getDay(),pascoa.getMonth()),
            new Holiday(corpo_de_Deus.getDay(),corpo_de_Deus.getMonth())};
            return holidays;
        } else {
            calcEaster(ano);
            Date corpo_de_Deus = pascoa.dateAfter(60);
            Date carnaval = pascoa.dateBefore(48);
            Date sexta_feira_santa = pascoa.dateBefore(2);
            Holiday[] holidays2 = {new Holiday(carnaval.getDay(),carnaval.getMonth()),
            new Holiday(sexta_feira_santa.getDay(),sexta_feira_santa.getMonth()),
            new Holiday(pascoa.getDay(),pascoa.getMonth()),
            new Holiday(corpo_de_Deus.getDay(),corpo_de_Deus.getMonth())};
            return holidays2;
        }
    }

    public static Date getEaster(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            return pascoa;
        } else {
            calcEaster(ano);
            return pascoa;
        }

    }

    public static Holiday getGoodFriday(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            Date sexta_feira_santa = pascoa.dateBefore(2);
            return new Holiday(sexta_feira_santa.getDay(),sexta_feira_santa.getMonth());
        } else {
            calcEaster(ano);
            Date sexta_feira_santa = pascoa.dateBefore(2);
            return new Holiday(sexta_feira_santa.getDay(),sexta_feira_santa.getMonth());
        }
    }

    public static Holiday getCorpusChristi(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            Date corpo_de_Deus = pascoa.dateAfter(60);
            return new Holiday(corpo_de_Deus.getDay(),corpo_de_Deus.getMonth());
        } else {
            calcEaster(ano);
            Date corpo_de_Deus = pascoa.dateAfter(60);
            return new Holiday(corpo_de_Deus.getDay(),corpo_de_Deus.getMonth());
        }
    }

    public static Holiday getCarnaval(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            Date carnaval = pascoa.dateBefore(48);
            return new Holiday(carnaval.getDay(),carnaval.getMonth());
        } else {
            calcEaster(ano);
            Date carnaval = pascoa.dateBefore(48);
            return new Holiday(carnaval.getDay(),carnaval.getMonth());
        }
    }

    private static void calcEaster(int ano) {
        int m = 0;
        int n = 0;
        if (ano >= 2000) {
            if ((ano >= 2000) && (ano <= 2099)) {
                m = 24;
                n = 5;
            } else if ((ano >= 2100) && (ano <= 2199)) {
                m = 24;
                n = 6;
            } else if ((ano >= 2200) && (ano <= 2299)) {
                m = 25;
                n = 0;
            } else if ((ano >= 2300) && (ano <= 2399)) {
                m = 26;
                n = 1;
            } else if ((ano >= 2400) && (ano <= 2499)) {
                m = 25;
                n = 1;
            }
            int a = ano % 19;
            int b = ano % 4;
            int c = ano % 7;
            int d = ((19 * a) + m) % 30;
            int e = ((2 * b) + (4 * c) + (6 * d) + n) % 7;
            int p = 0;
            if ((p = (e + 22 + d)) > 31) {
                if ((p = d + e - 9) > 25) {
                    p = p - 7;
                    pascoa = new Date(p, 4, ano);
                } else {
                    pascoa = new Date(p, 4, ano);
                }
            } else {
                pascoa = new Date(p, 3, ano);
            }
        }
    }

    @Override
    public int compareTo(Holiday o) {
        int valor;
        if((valor = Integer.compare(this.getMonth(), o.getMonth())) == 0 ){
            valor = Integer.compare(this.getDay(), o.getDay());
        }
        return valor;
    }
}
