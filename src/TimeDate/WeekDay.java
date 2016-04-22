package TimeDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.text.ParseException;

/**
 *
 * @author toze
 */
public class WeekDay extends Date {
    private int dia;
    private String extenso;
    private Langs.Locale locale;
    
    public WeekDay(int dia){
        if ((dia > 0)&&(dia < 8)) 
        {
            this.dia = dia;
        } else  {
            this.dia = -1;
        }
        extenso= this.calcDayName();
        locale = new Langs.Locale();
    }
    
    
    public WeekDay(Date date) throws ParseException
    {
        super(date);
        if (date.isValid()) {
            int [][] modulos = {{0,3,3,6,1,4,6,2,5,0,3,5},
                            {0,3,4,0,2,5,0,3,6,1,4,6}
                           };
            int auxiliar = (date.getYear() - 1)%7;
            auxiliar += ((date.getYear()-1)/4-3*((date.getYear()-1)/101)/4)%7;
            if (date.isLeap(date.getYear())) auxiliar += modulos[1][date.getMonth()-1];
            else auxiliar += modulos[0][date.getMonth()-1];
            auxiliar += (date.getDay()%7);
            this.dia = auxiliar%7;
            if (this.dia == 0) this.dia = 7;
            extenso= this.calcDayName();
        }
    }
    
    @Override
    public String toString(){
        return this.perDayName();
    }
    
    private String calcDayName(){
        if (this.dia != -1) {
            if (locale == null) locale = new Langs.Locale();
            return locale.translate("dia"+this.getDayNumber());
        } else{
            return "";
        }
    }

    /**
     * @return the dia
     */
    public int getDayNumber() {
        return dia;
    }

    /**
     * @return the extenso
     */
    public String perDayName() {
        return extenso;
    }


    /**
     * @return the locale
     */
    @Override
    public Langs.Locale getLanguage() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    @Override
    public void setLanguage(String locale) {
        if (this.locale == null) this.locale = new Langs.Locale();
        this.locale.setLocale(locale);
        this.extenso = this.calcDayName();    
    }
    
    @Override
    public void setLanguage(Langs.Locale locale) {
        if (this.locale == null) this.locale = new Langs.Locale();
        this.locale = locale;
        this.extenso = this.calcDayName();    
    }
    
    public static int getDayWeek(Date date){
        int [][] modulos = {{0,3,3,6,1,4,6,2,5,0,3,5},
                            {0,3,4,0,2,5,0,3,6,1,4,6}
                           };
        int auxiliar = (date.getYear() - 1)%7;
        auxiliar += ((date.getYear()-1)/4-3*((date.getYear()-1)/101)/4)%7;
        if (date.isLeap(date.getYear())) auxiliar += modulos[1][date.getMonth()-1];
        else auxiliar += modulos[0][date.getMonth()-1];
        auxiliar += (date.getDay()%7);
        int valor = auxiliar%7;
        if (valor == 0) valor = 7;
        return valor;
    }
}
