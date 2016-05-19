/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author toze
 */
public class HolidaysList {
    private int ano;
    private java.util.Set<TimeDate.Holiday> feŕiados;
    private final TimeDate.Holiday pascoa;
    private final TimeDate.Holiday carnaval;
    private final TimeDate.Holiday corpo_cristo;
    private final TimeDate.Holiday sexta_feira;
    private boolean bpascoa;
    private boolean bcarnaval;
    private boolean bcorpo_cristo;
    private boolean bsexta_feira;
    
    
    public HolidaysList(int ano) {
        this.ano = ano;
        this.feŕiados = new java.util.TreeSet<>();
        TimeDate.Holiday[] lista = TimeDate.Holiday.getMobileHolidays(ano);
        pascoa = lista[2];
        carnaval = lista[0];
        corpo_cristo = lista[3];
        sexta_feira = lista[1];
        bpascoa = false;
        bcarnaval = false;
        bcorpo_cristo = false;
        bsexta_feira = false;
    }
    
    /**
     * @param feriado add a holiday to a List
     */
    public void addHoliday(TimeDate.Holiday feriado){
        this.getHolidays().add(feriado);
    }
    
    public void addHoliday(int dia,int mes){
        this.getHolidays().add(new TimeDate.Holiday(dia,mes));
    }
    
    public void removeHoliday(TimeDate.Holiday feriado){
        if (this.getHolidays().contains(feriado)) this.getHolidays().remove(feriado);
    }
    
    public int getIndex(TimeDate.Holiday feriado){
        java.util.List<TimeDate.Holiday> feriad = new java.util.ArrayList<>(this.getHolidays());
        for (int i = 0; i< feriad.size(); i++) {
            if ((feriad.get(i).getDay() == feriado.getDay())&&(feriad.get(i).getMonth() == feriado.getMonth())) return i;
        }
        return 0;
    }
    
    public TimeDate.Holiday getHoliday(int val){
        java.util.List<TimeDate.Holiday> feriad = new java.util.ArrayList<>(this.getHolidays());
        for (int i = 0; i< feriad.size(); i++) {
            return feriad.get(i);
        }
        return new TimeDate.Holiday();
    }

    /**
     * @return the List of holidays
     */
    public java.util.Set<TimeDate.Holiday> getHolidays() {
        return feŕiados;
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
    
    public void removeCarnival(){
        this.feŕiados.remove(carnaval);
    }
    
    public TimeDate.Holiday getCarnival(){
        return this.carnaval;
    }
    
    public void removeCorpusChristi(){
        this.feŕiados.remove(this.corpo_cristo);
    }
    
    public TimeDate.Holiday getCorpusChristi(){
        return this.corpo_cristo;
    }
    
    public void removeGoodFriday(){
        this.feŕiados.remove(this.sexta_feira);
    }
    
    public TimeDate.Holiday getGoodFriday(){
        return this.sexta_feira;
    }
    
    public void removeEaster(){
        this.feŕiados.remove(this.pascoa);
    }
    
    public TimeDate.Holiday getEaster(){
        return this.pascoa;
    }
    
    public void addCarnival(){
        this.feŕiados.add(carnaval);
    }
    
    public void addCorpusChristi(){
        this.feŕiados.add(this.corpo_cristo);
    }
    
    public void addGoodFriday(){
        this.feŕiados.add(this.sexta_feira);
    }
    
    public void addEaster(){
        this.feŕiados.add(this.pascoa);
    }
    
    public boolean hasEaster(){
        return this.feŕiados.stream().anyMatch((hol) -> (hol.toString().equals(this.pascoa.toString())));
    }
    
    public boolean hasGoodFriday(){
        return this.feŕiados.stream().anyMatch((hol) -> (hol.toString().equals(this.sexta_feira.toString())));
    }
    
    public boolean hasCorpusChristi(){
        return this.feŕiados.stream().anyMatch((hol) -> (hol.toString().equals(this.corpo_cristo.toString())));
    }
    
    public boolean hasCarnival(){
        return this.feŕiados.stream().anyMatch((hol) -> (hol.toString().equals(this.carnaval.toString())));
    }
}
