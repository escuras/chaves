/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;
import TimeDate.WeekDay;
import TimeDate.Time;
import TimeDate.Date;
/**
 *
 * @author toze
 */
public class Request implements Comparable<Request> {
    private Date begin;
    private Date end;
    private WeekDay dia;
    private Time tinicio;
    private Time tfim;
    private Person pessoa;
    private Subject discplina;
    private Material material;
    private String origem;
    private boolean ativo;
    private boolean terminado;
    private boolean completo;
    
    public Request(Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, String origem){
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material= material;
        this.discplina = new Subject();
        this.origem = origem;
        this.ativo = false;
        this.terminado = false;
        this.completo = false;
    }
    
    public Request(Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, String origem, boolean ativo, boolean terminado, boolean completo){
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material= material;
        this.discplina = new Subject();
        this.origem = origem;
        this.ativo = ativo;
        this.terminado = terminado;
        this.completo = completo;
    }
    
    public Request(Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, Subject disciplina, String origem, boolean ativo, boolean terminado, boolean completo){
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material= material;
        this.discplina = disciplina;
        this.origem = origem;
        this.ativo = ativo;
        this.terminado = terminado;
        this.completo= completo;
    }
    
    public Request(Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, Subject disciplina, String origem){
        this.begin = date;
        this.end = date2;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material= material;
        this.discplina = disciplina;
        this.origem = origem;
        this.ativo = false;
        this.terminado = false;
        this.completo = false;
    }
    
    public Request(Request req){
        this.begin = req.getBeginDate();
        this.end = req.getEndDate();
        this.dia = req.getWeekDay();
        this.tinicio = req.getTimeBegin();
        this.tfim = req.getTimeEnd();
        this.pessoa = req.getPerson();
        this.material= req.getMaterial();
        this.discplina = req.getSubject();
        this.origem = req.getSource();
        this.ativo = req.isActive();
        this.terminado = req.isTerminated();
        this.completo = req.isComplete();
    }
    
    
    /**
     * @return the dinicio
     */
    public Date getBeginDate() {
        return begin;
    }

    /**
     * @param date 
     */
    public void setBeginDate(Date date) {
        this.begin = date;
    }
    
    /**
     * @return the dinicio
     */
    public Date getEndDate() {
        return end;
    }

    /**
     * @param date 
     */
    public void setEndDate(Date date) {
        this.end = date;
    }

    /**
     * @return the dia
     */
    public WeekDay getWeekDay() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setWeekDay(WeekDay dia) {
        this.dia = dia;
    }

    /**
     * @return the tinicio
     */
    public Time getTimeBegin() {
        return tinicio;
    }

    /**
     * @param tinicio the tinicio to set
     */
    public void setTimeBegin(Time tinicio) {
        this.tinicio = tinicio;
    }

    /**
     * @return the tfim
     */
    public Time getTimeEnd() {
        return tfim;
    }

    /**
     * @param tfim the tfim to set
     */
    public void setTimeEnd(Time tfim) {
        this.tfim = tfim;
    }

    /**
     * @return the pessoa
     */
    public Person getPerson() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPerson(Person pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the discplina
     */
    public Subject getSubject() {
        return discplina;
    }

    /**
     * @param discplina the discplina to set
     */
    public void setSubject(Subject discplina) {
        this.discplina = discplina;
    }

    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the origem
     */
    public String getSource() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setSource(String origem) {
        this.origem = origem;
    }
    
    public boolean isTerminated(){
        return this.terminado;
    }
    
    public void setTerminated(){
        this.terminado = true;
    }
    
    public boolean isActive(){
        return this.ativo;
    }
    
    public void setActive(){
        this.terminado = true;
    }
    
    public boolean isComplete(){
        return this.ativo;
    }
    
    public void setComplete(){
        this.completo = true;
    }

    @Override
    public int compareTo(Request o) {
        int valor;
        if((valor = Integer.compare(this.getBeginDate().getYear(), o.getBeginDate().getYear())) == 0){
            if ((valor = Integer.compare(this.getBeginDate().getMonth(), o.getBeginDate().getMonth())) == 0) {
                if ((valor = Integer.compare(this.getBeginDate().getDay(), o.getBeginDate().getDay())) == 0) {
                    if ((valor = Integer.compare(this.getTimeBegin().getHour(), o.getTimeBegin().getHour())) == 0) {
                        if ((valor = Integer.compare(this.getTimeBegin().getMinutes(), o.getTimeBegin().getMinutes())) == 0) {
                            valor = this.getPerson().getName().compareToIgnoreCase(o.getPerson().getName());
                        }
                    }
                }
            }
        }
        return valor;
    }
    
}
