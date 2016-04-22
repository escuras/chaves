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
public class Time {
     
    private int hora;
    private int minutos;
    private int segundos;

    public Time(int hora, int minutos, int segundos)
    {
        if ((hora >= 0 )&&(hora <= 23)) 
        {
            if ((minutos >= 0)&&(minutos <= 59)&&(segundos >= 0)&&(segundos <= 59)) 
            {
                this.hora = hora;
                this.minutos = minutos;
                this.segundos = segundos;
            } 
            else 
            {
                this.hora = -1;
                this.minutos = -1;
                this.segundos = -1;
            }
        } 
        else 
        {
            this.hora = -1;
            this.minutos = -1;
            this.segundos = -1;
        }
    }
    
    public Time(int hora, int minutos)
    {
        if ((hora >= 0 )&&(hora <= 23)) 
        {
            if ((minutos >= 0)&&(minutos <= 59)) 
            {
                this.hora = hora;
                this.minutos = minutos;
                this.segundos = 0;
            } 
            else 
            {
                this.hora = -1;
                this.minutos = -1;
                this.segundos = -1;
            }
        } 
        else 
        {
            this.hora = -1;
            this.minutos = -1;
            this.segundos = -1;
        }
    }
    
    public Time(){
        DateTime tmr = new DateTime();
        this.hora = tmr.getHourOfDay();
        this.minutos = tmr.getMinuteOfHour();
        this.segundos = tmr.getSecondOfMinute();
    }
    
    /**
     * @return the hora
     */
    public int getHour() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHour(int hora) {
        if ((hora >= 0 )&&(hora <= 23)) this.hora = hora;
    }

    /**
     * @return the minutos
     */
    public int getMinutes() {
        return minutos;
    }

    /**
     * @param minutos the minutos to set
     */
    public void setMinutes(int minutos) {
        if ((minutos >= 0)&&(minutos <= 59)) this.minutos = minutos;
    }

    /**
     * @return the segundos
     */
    public int getSeconds() {
        return segundos;
    }

    /**
     * @param segundos the segundos to set
     */
    public void setSeconds(int segundos) {
        if ((segundos >= 0)&&(segundos <= 59)) this.segundos = segundos;
    }
    
    @Override
    public String toString(){
        String shora;
        String sminutos;
        String ssegundos;
        if (this.hora < 10) shora = "0"+this.hora;
        else shora = ""+this.hora;
        if (this.minutos < 10) sminutos = "0"+this.minutos;
        else sminutos = ""+this.minutos;
        if (this.segundos < 10) ssegundos = "0"+this.segundos; 
        else ssegundos = ""+this.segundos;
        return shora+":"+sminutos+":"+ssegundos;
    }
    
     public String toString(int format){
        String shora;
        String shora2;
        String sminutos;
        String ssegundos;
        if (this.hora < 10) shora = "0"+this.hora;
        else shora = ""+this.hora;
        if (this.minutos < 10) sminutos = "0"+this.minutos;
        else sminutos = ""+this.minutos;
        if (this.segundos < 10) ssegundos = "0"+this.segundos; 
        else ssegundos = ""+this.segundos;
        if (format == 0)return shora+":"+sminutos;
        if (format == 1) {
            if (this.hora > 12) return (this.hora -12)+":"+this.minutos+" pm";
            else return (this.hora -12)+":"+this.minutos+" am";
        }
        else 
        {
            return shora+":"+sminutos+":"+ssegundos;
        }
    }
    
    public int compareTime(Time time)
    {
        // valor maximo 86399, pois o dia tem 86400. Com -100000 serve para indicar erro na validação. Com 0 as horas são iguais. 
        if (!time.isValid()) return -100000;
        if ((hora > 23)||(hora < 0)||(minutos > 59)||(minutos < 0)||(segundos < 0)||(segundos > 59)) return -100000;
        return ((time.getHour()-this.getHour())*3600) + ((time.getMinutes()-this.getMinutes())*60) + (time.getSeconds()-this.getSeconds());
    }
    
    public int compareTime(int hora, int minutos, int segundos)
    {
        if ((hora > 23)||(hora < 0)||(minutos > 59)||(minutos < 0)||(segundos < 0)||(segundos > 59)) return -100000;
        Time time = new Time(hora,minutos,segundos);
        return ((time.getHour()-this.getHour())*3600) + ((time.getMinutes()-this.getMinutes())*60) + (time.getSeconds()-this.getSeconds());
    }
    
    public boolean isValid()
    {
        return ((this.hora != -1)&&(this.minutos != -1)&&(this.segundos != -1));
    }
}
