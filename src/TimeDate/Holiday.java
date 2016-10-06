/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeDate;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toze
 */
public class Holiday implements Comparable<Holiday> {

    private static final long serialVersionUID = 1L;
    private int mes;
    private int dia;
    private boolean dinamico;
    private Langs.Locale locale;
    private static Date pascoa;
    private boolean ponte;
    private boolean expandido;

    public Holiday() {
        mes = 0;
        dia = 0;
        locale = new Langs.Locale();
        dinamico = false;
        ponte = false;
        expandido = false;
    }

    public Holiday(int dia, int mes) {
        this.mes = mes;
        this.dia = dia;
        dinamico = false;
        locale = new Langs.Locale();
        ponte = false;
        expandido = false;
    }

    public Holiday(Holiday hol) {
        this.mes = hol.getMonth();
        this.dia = hol.getDay();
        locale = hol.getLanguage();
        this.dinamico = hol.isDinamic();
        this.ponte = hol.isAdjusted();
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
        String sdia;
        String smes;
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
        return sdia + " / " + smes;
    }

    public String toLongString() {
        String sdia;
        if (dia < 10) {
            sdia = "0" + dia;
        } else {
            sdia = "" + dia;
        }
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return sdia + " " + locale.translate("de") + " " + locale.translate(meses[mes - 1]);
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
            di.setLanguage(locale);
        } catch (ParseException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
        }
        return di;
    }

    public static Holiday[] getMobileHolidays(int ano) {
        calcEaster(ano);
        Date corpo_de_Deus = pascoa.dateAfter(60);
        Date carnaval = pascoa.dateBefore(47);
        Date sexta_feira_santa = pascoa.dateBefore(2);
        Holiday[] holidays2 = {
            new Holiday(carnaval.getDay(), carnaval.getMonth()),
            new Holiday(sexta_feira_santa.getDay(), sexta_feira_santa.getMonth()),
            new Holiday(pascoa.getDay(), pascoa.getMonth()),
            new Holiday(corpo_de_Deus.getDay(), corpo_de_Deus.getMonth())
        };
        return holidays2;

    }

    public static Holiday getEaster(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            Holiday pasc = new Holiday(pascoa.getDay(), pascoa.getMonth());
            pasc.setDinamic(true);
            return pasc;
        } else {
            calcEaster(ano);
            Holiday pasc = new Holiday(pascoa.getDay(), pascoa.getMonth());
            pasc.setDinamic(true);
            return pasc;
        }

    }

    public static Holiday getGoodFriday(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            Date sexta_feira_santa = pascoa.dateBefore(2);
            Holiday sexta = new Holiday(sexta_feira_santa.getDay(), sexta_feira_santa.getMonth());
            sexta.setDinamic(true);
            return sexta;
        } else {
            calcEaster(ano);
            Date sexta_feira_santa = pascoa.dateBefore(2);
            Holiday sexta = new Holiday(sexta_feira_santa.getDay(), sexta_feira_santa.getMonth());
            sexta.setDinamic(true);
            return sexta;
        }
    }

    public static Holiday getCorpusChristi(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            Date corpo_de_Deus = pascoa.dateAfter(60);
            Holiday corpo = new Holiday(corpo_de_Deus.getDay(), corpo_de_Deus.getMonth());
            corpo.setDinamic(true);
            return corpo;
        } else {
            Date corpo_de_Deus = pascoa.dateAfter(60);
            Holiday corpo = new Holiday(corpo_de_Deus.getDay(), corpo_de_Deus.getMonth());
            corpo.setDinamic(true);
            return corpo;
        }
    }

    public static Holiday getCarnival(int ano) {
        if ((pascoa != null) && (pascoa.isValid())) {
            Date carnaval = pascoa.dateBefore(47);
            Holiday carn = new Holiday(carnaval.getDay(), carnaval.getMonth());
            carn.setDinamic(true);
            return carn;
        } else {
            calcEaster(ano);
            Date carnaval = pascoa.dateBefore(47);
            Holiday carn = new Holiday(carnaval.getDay(), carnaval.getMonth());
            carn.setDinamic(true);
            return carn;
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
            int p;
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
        if ((valor = Integer.compare(this.getMonth(), o.getMonth())) == 0) {
            valor = Integer.compare(this.getDay(), o.getDay());
        }
        return valor;
    }

    /**
     * @return the dinamico
     */
    public final boolean isDinamic() {
        return dinamico;
    }

    /**
     * @param dinamico the dinamico to set
     */
    public final void setDinamic(boolean dinamico) {
        this.dinamico = dinamico;
    }

    /**
     * @return the state of bridge
     */
    public boolean isAdjusted() {
        return ponte;
    }

    /**
     *
     */
    public void adjust() {
        TimeDate.Date dat = new TimeDate.Date();
        dat.setDay(dia);
        dat.setMonth(mes);
        TimeDate.WeekDay di;
        try {
            switch ((di = new WeekDay(dat)).getDayNumber()) {
                case 5:
                    dat = dat.dateAfter(1);
                    this.ponte = true;
                    break;
                case 3:
                    dat = dat.dateBefore(1);
                    this.ponte = true;
                    break;
                default:
                    this.ponte = false;
                    break;
            }
        } catch (ParseException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
        }
        dia = dat.getDay();
        mes = dat.getMonth();
    }

    /**
     * @return the state of expanded
     */
    public boolean isExpanded() {
        return expandido;
    }

    /**
     * @return TimeDate.Date data
     */
    public TimeDate.Date setExpanded() {
        TimeDate.Date dat = new TimeDate.Date();
        dat.setDay(this.dia);
        dat.setMonth(this.mes);
        switch (TimeDate.WeekDay.getDayWeek(dat)) {
            case 3:
                dat = dat.dateBefore(1);
                this.expandido = true;
                return dat;
            case 5:
                dat = dat.dateAfter(1);
                this.expandido = true;
                return dat;
            default:
                this.expandido = false;
                return null;
        }
    }

    public void cleanExpandedState() {
        this.expandido = false;
    }

    public void clearAdjustedState() {
        this.ponte = false;
    }

}
