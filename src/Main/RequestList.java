/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import TimeDate.HolidaysList;
import java.awt.FontMetrics;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author toze
 */
public class RequestList {

    private String bd;
    private DataBase.DataBase db;
    private Clavis.TypeOfMaterial material;
    private TimeDate.Date date1;
    private TimeDate.Date date2;
    private HolidaysList feriados;
    private Set<Clavis.Request> requests;
    private java.util.Map<Clavis.Request, Integer> unionrequests;
    public static final int VIEW_BIWEEK = 3;
    public static final int VIEW_WEEK = 2;
    public static final int VIEW_PAIR_OF_DAYS = 1;
    public static final int VIEW_DAY = 0;
    private int vista;
    private boolean estado;
    private boolean terminado;

    public RequestList(String bd, Clavis.TypeOfMaterial material, int vista, HolidaysList feriados, boolean estado, boolean terminado) {
        this.estado = estado;
        this.terminado = terminado;
        this.vista = vista;
        this.db = new DataBase.DataBase(bd);
        this.bd = bd;
        this.material = material;
        this.requests = new TreeSet<>();
        this.feriados = feriados;
        this.calcDates();
        this.unionrequests = new java.util.HashMap<>();
    }

    public RequestList(RequestList req) {
        this.bd = req.getBd();
        this.material = req.getTypeOfMaterial();
        this.date1 = req.getDate1();
        this.date2 = req.getDate2();
        this.requests = new TreeSet<>();
        this.unionrequests = new java.util.HashMap<>();
        this.feriados = req.feriados;
    }

    public void reMake() {
        this.calcDates();
        this.requests = db.getRequests(material, this.date1, this.date2, estado, terminado);
        this.treatUnionRequests();
    }

    public Clavis.Request getSelectedRequest(int valor) {
        Clavis.Request[] requests2 = new Clavis.Request[this.requests.size()];
        requests2 = this.requests.toArray(requests2);
        return requests2[valor];
    }

    public void make() {
        this.requests = db.getRequests(material, this.date1, this.date2, estado, terminado);
        this.treatUnionRequests();
    }

    private void treatUnionRequests() {
        Set<Clavis.Request> requisicoes = new java.util.TreeSet<>(requests);
        unionrequests = new java.util.TreeMap<>();
        requests.stream().forEach((req) -> {
            String auxiliar = req.getActivity();
            requests.stream().map((req2) -> {
                int i = 0;
                if (req.getId() == req2.getUnionRequest()) {
                    if (i == 0) {
                        unionrequests.put(req, req.getId());
                        req.setEndDate(req2.getEndDate());
                        req.setTimeEnd(req2.getTimeEnd());
                        req.setActivity("<html><div>Múltiplas atividades:<br></div></b>" + auxiliar + "</html>");
                      
                    }
                    unionrequests.put(req2, req2.getUnionRequest());
                    //req.setActivity(req.getActivity()+"<br/>"+req2.getActivity());
                    requisicoes.remove(req2);
                }
                return i;
            }).forEach((i) -> {
                i++;
            });
        });
        requests = requisicoes;
    }

    public java.util.Map<Clavis.Request, Integer> getUnionRequests() {
        return this.unionrequests;
    }

    public java.util.Set<Clavis.Request> getDifferentUnionRequest(Clavis.Request req) {
        java.util.Set<Clavis.Request> lista = new java.util.HashSet<>();
        if (this.unionrequests.containsValue(req.getId())) {
            java.util.Set<Entry<Clavis.Request, Integer>> rel = this.unionrequests.entrySet();
            Iterator<Entry<Clavis.Request, Integer>> iter = rel.iterator();
            while (iter.hasNext()) {
                Entry<Clavis.Request, Integer> isto = iter.next();
                if (isto.getValue() == req.getId()) {
                    lista.add(isto.getKey());
                }
            }
        }
        return lista;
    }

    public void searchByTime(Boolean bool, TimeDate.Time time, Boolean estado, Boolean terminado) {
        this.requests = db.getRequestsByTime(bool, time, this.date1, this.date2, estado, terminado);
    }

    public void searchBy(int opcao, String person) {
        this.requests = db.getRequests(opcao, person, this.date1, this.date2, estado, terminado);
    }

    public boolean removeRequest(Clavis.Request request) {
        return this.requests.remove(request);
    }

    /**
     * @return the bd
     */
    public String getBd() {
        return bd;
    }

    /**
     * @param bd the bd to set
     */
    public void setBd(String bd) {
        this.bd = bd;
    }

    /**
     * @return the material
     */
    public Clavis.TypeOfMaterial getTypeOfMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setTypeOfMaterial(Clavis.TypeOfMaterial material) {
        this.material = material;
    }

    /**
     * @return the date1
     */
    public TimeDate.Date getDate1() {
        return date1;
    }

    /**
     * @param date1 the date1 to set
     */
    public void setDate1(TimeDate.Date date1) {
        this.date1 = date1;
    }

    /**
     * @return the date2
     */
    public TimeDate.Date getDate2() {
        return date2;
    }

    /**
     * @param date2 the date2 to set
     */
    public void setDate2(TimeDate.Date date2) {
        this.date2 = date2;
    }

    /**
     * @return the requests
     */
    public Set<Clavis.Request> getRequests() {
        return requests;
    }

    /**
     * @return the feriados
     */
    public HolidaysList getHolidays() {
        return feriados;
    }

    /**
     * @param feriados the feriados to set
     */
    public void setHolidays(HolidaysList feriados) {
        this.feriados = feriados;
    }

    /**
     * @return the vista
     */
    public int getView() {
        return vista;
    }

    /**
     * @param vista the vista to set
     */
    public void setView(int vista) {
        this.vista = vista;
        this.calcDates();
    }

    public boolean isConnected() {
        return db.isTie();
    }

    /**
     * @return the terminado
     */
    public boolean isConcluded() {
        return terminado;
    }

    /**
     * @param terminado the terminado to set
     */
    public void setConcluded(boolean terminado) {
        this.terminado = terminado;
    }

    private void calcDates() {
        Iterator<TimeDate.Holiday> fer_auxiliar = feriados.getHolidays().iterator();
        TimeDate.Date hoje = new TimeDate.Date();
        int dia_auxiliar = 0;
        switch (this.getView()) {
            case 1:
                dia_auxiliar = 1;
                while (fer_auxiliar.hasNext()) {
                    TimeDate.Holiday der = fer_auxiliar.next();
                    if ((der.getDay() == hoje.dateAfter(1).getDay()) && (der.getMonth() == hoje.dateAfter(1).getMonth())) {
                        dia_auxiliar++;
                    }
                }
                if (TimeDate.WeekDay.getDayWeek(hoje.dateAfter(1)) == 1) {
                    dia_auxiliar++;
                }
                this.date1 = hoje;
                this.date2 = hoje.dateAfter(dia_auxiliar);
                break;
            case 2:
                dia_auxiliar = 6;
                this.date1 = hoje;
                this.date2 = hoje.dateAfter(dia_auxiliar);
                break;
            case 3:
                dia_auxiliar = 13;
                this.date1 = hoje;
                this.date2 = hoje.dateAfter(dia_auxiliar);
                break;
            case 4:
                dia_auxiliar = TimeDate.Date.daysOfTheCurrentMonth(hoje);
                this.date1 = hoje;
                this.date2 = hoje.dateAfter(dia_auxiliar);
                break;
            default:
                this.date1 = hoje;
                this.date2 = hoje.dateAfter(dia_auxiliar);

        }
    }
}
