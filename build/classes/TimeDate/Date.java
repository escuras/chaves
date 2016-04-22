/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeDate;

import org.joda.time.DateTime;

/**
 *
 * @author toze
 */
public class Date {

    private int dia;
    private int mes;
    private int ano;
    private final int diasdoano;
    private final int semanasdoano;
    private Langs.Locale locale;

    public Date() {
        DateTime datr = new DateTime();
        ano = datr.getYear();
        mes = datr.getMonthOfYear();
        dia = datr.getDayOfMonth();
        diasdoano = datr.getDayOfYear();
        semanasdoano = datr.getWeekyear();
        locale = new Langs.Locale();
    }

    public Date(int dias) {
        DateTime datr = new DateTime();
        ano = datr.getYear();
        mes = datr.getMonthOfYear();
        dia = datr.getDayOfMonth();
        Date aux = this.dateAfter(dias);
        ano = aux.getYear();
        mes = aux.getMonth();
        dia = aux.getDay();
        diasdoano = aux.getDayYear();
        semanasdoano = aux.getWeekYear();
        locale = new Langs.Locale();
    }

    public Date(int dia, int mes, int ano) {
        if ((mes <= 12) && (mes > 0)) {
            if (this.validateMonthDays(dia, mes, ano)) {
                this.mes = mes;
                this.ano = ano;
                this.dia = dia;
                this.diasdoano = this.getDayOfTheYear();
                this.semanasdoano = this.getWeekOfTheYear();
            } else {
                this.mes = -1;
                this.dia = -1;
                this.ano = -1;
                this.diasdoano = -1;
                this.semanasdoano = -1;
            }

        } else {
            this.dia = -1;
            this.mes = -1;
            this.ano = -1;
            this.diasdoano = -1;
            this.semanasdoano = -1;
        }
        this.locale = new Langs.Locale();
    }

    public Date(Date date) {
        this.mes = date.mes;
        this.ano = date.ano;
        this.dia = date.dia;
        this.diasdoano = date.getDayOfTheYear();
        this.semanasdoano = date.getWeekOfTheYear();
        this.locale = new Langs.Locale();
    }

    private boolean validateMonthDays(int dia, int mes, int ano) {
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return (dia <= 31) && (dia > 0);
            case 4:
            case 6:
            case 9:
            case 11:
                return (dia <= 30) && (dia > 0);
            case 2:
                if (isLeap(ano)) {
                    return (dia <= 29) && (dia > 0);
                } else {
                    return (dia <= 28) && (dia > 0);
                }
            default:
                return false;
        }
    }

    public boolean isLeap(int ano) {
        return (ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0));
    }

    /**
     * @param mes the mes to set
     */
    public void setMonth(int mes) {
        if ((mes <= 12) && (mes > 0)) {
            this.mes = mes;
        }
    }

    /**
     * @return the mes
     */
    public int getMonth() {
        return this.mes;
    }

    /**
     * @return the ano
     */
    public int getYear() {
        return this.ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setYear(int ano) {
        this.ano = ano;
    }

    /**
     * @return the dia
     */
    public int getDay() {
        return this.dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDay(int dia) {
        if (this.validateMonthDays(dia, mes, ano)) {
            this.dia = dia;
        } else {
            this.dia = 0;
        }
    }

    @Override
    public String toString() {
        String sdia;
        if (dia < 10) {
            sdia = "0" + this.dia;
        } else {
            sdia = "" + this.dia;
        }
        String smes;
        if (mes < 10) {
            smes = "0" + this.mes;
        } else {
            smes = "" + this.mes;
        }
        String sano;
        if (ano >= 0) {
            if (ano < 10) {
                sano = "000" + this.ano;
            } else if (ano < 100) {
                sano = "00" + this.ano;
            } else if (ano < 1000) {
                sano = "0" + this.ano;
            } else {
                sano = "" + this.ano;
            }
        } else {
            if (ano > -10) {
                sano = "-000" + -this.ano;
            } else if (ano > -100) {
                sano = "-00" + -this.ano;
            } else if (ano > -1000) {
                sano = "-0" + -this.ano;
            } else {
                sano = "" + this.ano;
            }
        }
        return sdia + "/" + smes + "/" + sano;
    }

    public String toString(String simbolo) {
        String sdia;
        if (dia < 10) {
            sdia = "0" + this.dia;
        } else {
            sdia = "" + this.dia;
        }
        String smes;
        if (mes < 10) {
            smes = "0" + this.mes;
        } else {
            smes = "" + this.mes;
        }
        String sano;
        if (ano >= 0) {
            if (ano < 10) {
                sano = "000" + this.ano;
            } else if (ano < 100) {
                sano = "00" + this.ano;
            } else if (ano < 1000) {
                sano = "0" + this.ano;
            } else {
                sano = "" + this.ano;
            }
        } else {
            if (ano > -10) {
                sano = "-000" + -this.ano;
            } else if (ano > -100) {
                sano = "-00" + -this.ano;
            } else if (ano > -1000) {
                sano = "-0" + -this.ano;
            } else {
                sano = "" + this.ano;
            }
        }
        return sdia + simbolo + smes + simbolo + sano;
    }

    public String toStringWithMonthWord() {
        String sdia;
        if (dia < 10) {
            sdia = "0" + this.dia;
        } else {
            sdia = "" + this.dia;
        }
        String sano;
        if (ano > 0) {
            if (ano < 10) {
                sano = "000" + this.ano;
            } else if (ano < 100) {
                sano = "00" + this.ano;
            } else if (ano < 1000) {
                sano = "0" + this.ano;
            } else {
                sano = "" + this.ano;
            }
        } else {
            if (ano > -10) {
                sano = "-000" + -this.ano;
            } else if (ano > -100) {
                sano = "-00" + -this.ano;
            } else if (ano > -1000) {
                sano = "-0" + -this.ano;
            } else {
                sano = "" + this.ano;
            }
        }
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return sdia + " " + locale.translate(meses[this.mes - 1]) + " " + sano;
    }

    public boolean betweenDates(Date date, Date date2) {
        if (date.getYear() > date2.getYear()) {
            Date aux = date2;
            date2 = date;
            date = aux;
        } else if (date.getYear() == date2.getYear()) {
            if (date.getDayYear() > date2.getDayYear()) {
                Date aux = date2;
                date2 = date;
                date = aux;
            }
        }
        if ((date.getYear() < this.ano) && (this.ano < date2.getYear())) {
            return true;
        } else if ((date.getYear() <= this.ano) && (this.ano < date2.getYear())) {
            if (date.getMonth() < this.mes) {
                return true;
            } else if (date.getMonth() == this.mes) {
                return date.getDay() <= this.dia;
            } else {
                return false;
            }
        } else if ((date.getYear() < this.ano) && (this.ano <= date2.getYear())) {
            if (date2.getMonth() > this.mes) {
                return true;
            } else if (date2.getMonth() == this.mes) {
                return date2.getDay() >= this.dia;
            } else {
                return false;
            }
        } else if ((date.getYear() == this.ano) && (this.ano == date2.getYear())) {
            if ((date.getMonth() < this.mes) && (this.mes < date2.getMonth())) {
                return true;
            } else if ((date.getMonth() == this.mes) && (this.mes < date2.getMonth())) {
                return date.getDay() <= this.dia;
            } else if ((date.getMonth() < this.mes) && (this.mes == date2.getMonth())) {
                return date2.getDay() >= this.dia;
            } else if ((date.getMonth() == this.mes) && (this.mes == date2.getMonth())) {
                return (date.getDay() <= this.dia) && (this.dia <= date2.getDay());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Date dateAfter(int dias) {
        int year = this.ano;
        int month = this.mes;
        int day = this.dia;
        if (dias > 0) {
            if ((this.dia + dias) > (this.getDayOfTheMonth(this.mes, this.ano))) {
                int[] meses = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                int dauxiliar = this.getDayOfTheYear() + dias;
                int anoauxiliar;
                if (this.isLeap(this.ano)) {
                    anoauxiliar = 366;
                } else {
                    anoauxiliar = 365;
                }
                int i = 1;
                while (dauxiliar > anoauxiliar) {
                    if (this.isLeap(year)) {
                        if (dauxiliar > 366) {
                            dauxiliar -= 366;
                            year = this.ano + i;
                            i++;
                        }
                    } else {
                        if (dauxiliar > 365) {
                            dauxiliar -= 365;
                            year = this.ano + i;
                            i++;
                        }
                    }
                    if (this.isLeap(this.ano + i)) {
                        anoauxiliar = 366;
                    } else {
                        anoauxiliar = 365;
                    }
                }
                if (this.isLeap(year)) {
                    meses[2] = 29;
                }
                for (int j = 1; j < 13; j++) {
                    month = j;
                    if (dauxiliar > meses[j]) {
                        dauxiliar -= meses[j];
                    } else {
                        day = dauxiliar;
                        break;
                    }
                }
                return new Date(day, month, year);
            } else {
                return new Date(this.dia + dias, this.mes, this.ano);
            }

        } else {
            return new Date(this.dia, this.mes, this.ano);
        }
    }

    public Date dateBefore(int dias) {
        int year = this.ano;
        int month = this.mes;
        int day = this.dia;
        if (dias > 0) {
            if ((this.dia - dias) < 0) {
                int[] meses = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                int dauxiliar = this.getDayOfTheYear() - dias;
                if (dauxiliar > 0) {
                    return new Date(1, 1, this.ano).dateAfter(dauxiliar);
                } else {
                    int anoauxiliar;
                    if (this.isLeap(this.ano - 1)) {
                        anoauxiliar = 366;
                    } else {
                        anoauxiliar = 365;
                    }
                    year = this.ano - 1;
                    int i = 2;
                    while (dauxiliar < -anoauxiliar) {
                        if (this.isLeap(year)) {
                            if (dauxiliar < -366) {
                                dauxiliar += 366;
                                year = this.ano - i;
                                i++;
                            }
                        } else {
                            if (dauxiliar < -365) {
                                dauxiliar += 365;
                                year = this.ano - i;
                                i++;
                            }
                        }
                        if (this.isLeap(this.ano - i)) {
                            anoauxiliar = 366;
                        } else {
                            anoauxiliar = 365;
                        }
                    }
                    if (this.isLeap(year)) {
                        meses[2] = 29;
                    }
                    if(year > 0) {
                        System.out.println("fff");
                        for (int j = 12; j >= 1; j--) {
                            month = j;
                            if (dauxiliar <= -meses[j]) {
                                dauxiliar += meses[j];
                                System.out.println("if"+dauxiliar);
                                if (dia == 0) dia = 1;
                            } else {
                                day = meses[j] + dauxiliar;
                                System.out.println("else"+day);
                                break;
                            }
                            if ((dauxiliar == 0)&&(month == 1)&&(year == 1)) {day = 1;year = 0;}
                            if ((dauxiliar == -1)&&(month == 1)&&(year == 1)) {day = 2;year = 0;}
                        } 
                    } else {
                        System.out.println("vvv");
                        dauxiliar = -(dauxiliar-1);
                        for (int j = 1; j <= 12; j++) {
                            month = j;
                            if (dauxiliar > meses[j]) {
                                dauxiliar -= meses[j];
                            } else {
                                day = dauxiliar;
                                break;
                            }
                        } 
                    }
                    if (year <= 0) {
                        year = year - 1;
                    }
                    if (day < 0) {
                       day = -day;
                    }
                    if (day == 0) day = 31;
                    System.out.println("day  :" + day);
                    System.out.println("month  :" + month);
                    System.out.println("year  :" + year);
                    return new Date(day, month, year);
                }
            } else {
                return new Date(this.dia - dias, this.mes, this.ano);
            }
        } else {
            return new Date(this.dia, this.mes, this.ano);
        }
    }

    private int getDayOfTheYear() {
        int i = 1;
        int conta = 0;
        while (i < this.mes) {
            conta += this.getDayOfTheMonth(i, this.ano);
            i++;
        }
        conta += this.dia;
        return conta;
    }
    
    private int getDayOfTheYear(int dia, int mes,int ano) {
        int i = 1;
        int conta = 0;
        while (i < mes) {
            conta += this.getDayOfTheMonth(i, ano);
            i++;
        }
        conta += dia;
        return conta;
    }

    private int getDayOfTheMonth(int mes, int ano) {
        if ((mes > 0) && (mes < 13)) {
            int[] meses = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (!this.isLeap(ano)) {
                return meses[mes];
            } else {
                meses[2] = 29;
                return meses[mes];
            }
        } else {
            return 0;
        }
    }

    private int getWeekOfTheYear() {
        int dias = this.getDayOfTheYear();
        int conta = 1;
        while (dias > 7) {
            dias -= 7;
            conta++;
        }
        return conta;
    }

    public static int numberOfDaysBetweenDates(Date a, Date b) {
        int dias = 0;
        if ((b.getYear() - a.getYear()) > 0) {
            int aux = a.getYear();
            while (aux < b.getYear()) {
                if (a.isLeap(aux)) {
                    dias += 366;
                } else {
                    dias += 365;
                }
                aux++;
            }
            dias += b.getDayYear();
            dias -= a.getDayYear();
            return dias;
        } else {
            dias = b.getDayYear() - a.getDayYear();
            if (dias > 0) {
                return dias;
            } else {
                return 0;
            }
        }
    }

    public int getWeekYear() {
        return this.semanasdoano;
    }

    public int getDayYear() {
        return this.diasdoano;
    }

    public boolean isValid() {
        return (!(this.dia == -1) || !(this.mes == -1) || !(this.ano == -1));
    }

    public static java.util.List<Date> DatesBetweenDates(Date a, Date b, int diaw) {
        java.util.List<Date> datas = new java.util.ArrayList<>();
        int dias;
        if ((dias = numberOfDaysBetweenDates(a, b)) >= 0) {
            int aux = 0;
            int diaa = WeekDay.getDayWeek(a);
            if (diaa < diaw) {
                diaa = (diaw - diaa);
            } else if (diaa == diaw) {
                diaa = 0;
            } else {
                diaa = (7 - diaa) + diaw;
            }
            while (diaa <= dias) {
                for (int i = 1; i <= 7; i++) {
                    if (diaw == i) {
                        Date date = a.dateAfter(diaa);
                        datas.add(date);
                        break;
                    }
                }
                diaa += 7;
            }
        }
        return datas;

    }

    /**
     * @return the locale
     */
    public Langs.Locale getLanguage() {
        return locale;
    }

    /**
     * @param local
     */
    public void setLanguage(String local) {
        if (this.locale == null) {
            this.locale = new Langs.Locale();
        }
        this.locale.setLocale(local);
    }

    /**
     * @param locale
     */
    public void setLanguage(Langs.Locale locale) {
        if (this.locale == null) {
            this.locale = new Langs.Locale();
        }
        this.locale = locale;
    }

}
