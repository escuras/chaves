/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import TimeDate.HolidaysList;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author toze
 */
public class RequestList {

    private String bd;
    private Keys.TypeOfMaterial material;
    private TimeDate.Date date1;
    private TimeDate.Date date2;
    private HolidaysList feriados;
    private List<Keys.Request> requests;
    private java.util.Map<Keys.Request, Integer> unionrequests;
    public static final int VIEW_BIWEEK = 3;
    public static final int VIEW_WEEK = 2;
    public static final int VIEW_PAIR_OF_DAYS = 1;
    public static final int VIEW_DAY = 0;
    private int vista;
    private boolean estado;
    private boolean terminado;

    public RequestList(String bd, Keys.TypeOfMaterial material, int vista, HolidaysList feriados, boolean estado, boolean terminado) {
        this.estado = estado;
        this.terminado = terminado;
        this.vista = vista;
        this.bd = bd;
        this.material = material;
        this.requests = new ArrayList<>();
        this.feriados = feriados;
        this.calcDates();
        this.unionrequests = new java.util.HashMap<>();
    }

    public RequestList(RequestList req) {
        this.bd = req.getBd();
        this.material = req.getTypeOfMaterial();
        this.date1 = req.getDate1();
        this.date2 = req.getDate2();
        this.requests = new ArrayList<>();
        this.unionrequests = new java.util.HashMap<>();
        this.feriados = req.feriados;
    }

    public void reMake() {
        this.calcDates();
        DataBase.DataBase db = new DataBase.DataBase(bd);
        if (db.isTie()) {
            this.requests = new ArrayList<>(db.getRequests(material, this.date1, this.date2, estado, terminado));
            Collections.sort(requests);
            this.treatUnionRequests();
        }
        db.close();

    }

    public Keys.Request getSelectedRequest(int valor) {
        Keys.Request[] requests2 = new Keys.Request[this.requests.size()];
        requests2 = this.requests.toArray(requests2);
        return requests2[valor];
    }

    public void make() {
        DataBase.DataBase db = new DataBase.DataBase(bd);
        if (db.isTie()) {
            this.requests = new ArrayList<>(db.getRequests(material, this.date1, this.date2, estado, terminado));
            Collections.sort(requests);
            this.treatUnionRequests();
        }
        db.close();
    }

    private void treatUnionRequests() {
        Set<Keys.Request> requisi = new java.util.HashSet<>(requests);
        java.util.ArrayList<Keys.Request> requisicoes = new java.util.ArrayList<>(requisi);
        Collections.sort(requisicoes);
        unionrequests = new java.util.TreeMap<>();
        int i;
        for (Keys.Request req : requests) {
            i = 0;
            for (Keys.Request req2 : requests) {
                if ((req.getId() == req2.getUnionRequest()) && (req2.getUnionRequest() > 0)) {
                    unionrequests.put(new Keys.Request(req), req.getId());
                    if (i == 0) {
                        String auxiliar = "Múltiplas atividades:::" + req.getActivity() + ";;;" + req.getTimeBegin().toString(0) + ";;;" + req.getTimeEnd().toString(0);
                        req.setActivity(auxiliar);
                        i++;
                    }
                    req.setEndDate(req2.getEndDate());
                    req.setTimeEnd(req2.getTimeEnd());
                    req.setActivity(req.getActivity() + ":::" + req2.getActivity() + ";;;" + req2.getTimeBegin().toString(0) + ";;;" + req2.getTimeEnd().toString(0));
                    unionrequests.put(req2, req2.getUnionRequest());
                    requisicoes.remove(req2);
                }
            }
        }
        requests = new ArrayList<>(requisicoes);
        Collections.sort(requests);
    }

    public java.util.Set<Keys.Request> getDifferentUnionRequest(Keys.Request req) {
        java.util.Set<Keys.Request> lista = new java.util.HashSet<>();
        if (this.unionrequests.containsValue(req.getId())) {
            java.util.Set<Entry<Keys.Request, Integer>> rel = this.unionrequests.entrySet();
            Iterator<Entry<Keys.Request, Integer>> iter = rel.iterator();
            while (iter.hasNext()) {
                Entry<Keys.Request, Integer> isto = iter.next();
                if (isto.getValue() == req.getId()) {
                    lista.add(isto.getKey());
                }
            }
            Set<Keys.Request> lista2 = new java.util.HashSet<>(lista);
            TimeDate.Time tim = new TimeDate.Time();
            TimeDate.Date dat = new TimeDate.Date();
            int id = 0;
            for (Keys.Request r : lista2) {
                for (Keys.Request e : lista2) {
                    if (r.getId() == e.getId()) {
                        if ((r.getEndDate().isBigger(e.getEndDate()) < 0) || (r.getTimeEnd().compareTime(e.getTimeEnd()) < 0)) {
                            tim = e.getTimeEnd();
                            dat = e.getEndDate();
                            lista.remove(e);
                            id = r.getId();
                        }
                    }
                }
            }
            for (Keys.Request r : lista) {
                if (id > 0) {
                    if (r.getId() == id) {
                        r.setTimeEnd(tim);
                        r.setEndDate(dat);
                    }
                }
            }
        }
        return lista;
    }

    public void sortbyEndDate() {
        Collections.sort(requests, new Comparator<Keys.Request>() {
            @Override
            public int compare(Keys.Request o1, Keys.Request o2) {
                if (o1.getEndDate() == null || o2.getEndDate() == null) {
                    return 0;
                }
                return o2.getEndDate().isBigger(o1.getEndDate());
            }
        });
        /*
        Keys.Request auxiliar;
        int val = 0;
        if (!requests.isEmpty()) {
            for (int i = 0; i < requests.size(); i++) {
                auxiliar = requests.get(i);
                for (int j = i; j < requests.size(); j++) {
                    if (auxiliar.getEndDate().isBigger(requests.get(j).getEndDate()) < 0) {
                        val = j;
                        auxiliar = requests.get(j);
                    }
                }
                requests.set(val, requests.get(i));
                requests.set(i, auxiliar);
            }
        }*/
    }

    public void searchByTime(Boolean bool, TimeDate.Time time, Boolean estado, Boolean terminado) {
        DataBase.DataBase db = new DataBase.DataBase(bd);
        if (db.isTie()) {
            this.requests = new ArrayList<>(db.getRequestsByTime(this.getTypeOfMaterial(), bool, time, this.date1, this.date2, estado, terminado));
            Collections.sort(requests);
            this.treatUnionRequests();
        }
        db.close();
    }

    public void searchBy(int opcao, String person) {
        DataBase.DataBase db = new DataBase.DataBase(bd);
        if (db.isTie()) {
            this.requests = new ArrayList<>(db.getRequests(opcao, this.getTypeOfMaterial(), person, this.date1, this.date2, estado, terminado));
            Collections.sort(requests);
            this.treatUnionRequests();
        }
        db.close();
    }

    public void removeRequest(Keys.Request request) {
        java.util.Set<Keys.Request> lista = this.getDifferentUnionRequest(request);
        if (lista.size() > 0) {
            lista.stream().forEach((l) -> {
                this.unionrequests.remove(l);
            });
        }
        this.requests.remove(request);
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
    public Keys.TypeOfMaterial getTypeOfMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setTypeOfMaterial(Keys.TypeOfMaterial material) {
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
    public List<Keys.Request> getRequests() {
        return requests;
    }

    public void sortRequests() {
        Collections.sort(requests);
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
        return DataBase.DataBase.testConnection(bd);
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
        if (!estado) {
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
        } else {
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
                    this.date1 = hoje.dateBefore(dia_auxiliar);
                    if (DataBase.DataBase.testConnection(bd)) {
                        DataBase.DataBase db = new DataBase.DataBase(bd);
                        this.date2 = db.getLastdateOnActiveRequests(this.getTypeOfMaterial());
                        db.close();
                    } else {
                        this.date2 = hoje.dateAfter(dia_auxiliar);
                    }
                    break;
                case 2:
                    dia_auxiliar = 6;
                    this.date1 = hoje.dateBefore(dia_auxiliar);
                    if (DataBase.DataBase.testConnection(bd)) {
                        DataBase.DataBase db = new DataBase.DataBase(bd);
                        this.date2 = db.getLastdateOnActiveRequests(this.getTypeOfMaterial());
                        db.close();
                    } else {
                        this.date2 = hoje.dateAfter(dia_auxiliar);
                    }
                    break;
                case 3:
                    dia_auxiliar = 13;
                    this.date1 = hoje.dateBefore(dia_auxiliar);
                    if (DataBase.DataBase.testConnection(bd)) {
                        DataBase.DataBase db = new DataBase.DataBase(bd);
                        this.date2 = db.getLastdateOnActiveRequests(this.getTypeOfMaterial());
                        db.close();
                    } else {
                        this.date2 = hoje.dateAfter(dia_auxiliar);
                    }
                    break;
                case 4:
                    dia_auxiliar = TimeDate.Date.daysOfTheCurrentMonth(hoje);
                    this.date1 = hoje.dateBefore(dia_auxiliar);
                    if (DataBase.DataBase.testConnection(bd)) {
                        DataBase.DataBase db = new DataBase.DataBase(bd);
                        this.date2 = db.getLastdateOnActiveRequests(this.getTypeOfMaterial());
                        db.close();
                    } else {
                        this.date2 = hoje;
                    }
                    break;
                default:
                    this.date1 = hoje.dateBefore(dia_auxiliar);
                    if (DataBase.DataBase.testConnection(bd)) {
                        DataBase.DataBase db = new DataBase.DataBase(bd);
                        this.date2 = db.getLastdateOnActiveRequests(this.getTypeOfMaterial());
                        db.close();
                    } else {
                        this.date2 = hoje;
                    }

            }
        }
    }
    
    
    public static java.util.List<Keys.Request> simplifyRequests(java.util.Set<Keys.Request> requests) {
        Set<Keys.Request> requisi = new java.util.HashSet<>(requests);
        java.util.ArrayList<Keys.Request> requisicoes = new java.util.ArrayList<>(requisi);
        Collections.sort(requisicoes);
        int i;
        for (Keys.Request req : requests) {
            i = 0;
            for (Keys.Request req2 : requests) {
                if ((req.getId() == req2.getUnionRequest()) && (req2.getUnionRequest() > 0)) {
                    if (i == 0) {
                        String auxiliar = "Múltiplas atividades:::" + req.getActivity() + ";;;" + req.getTimeBegin().toString(0) + ";;;" + req.getTimeEnd().toString(0);
                        req.setActivity(auxiliar);
                        i++;
                    }
                    req.setEndDate(req2.getEndDate());
                    req.setTimeEnd(req2.getTimeEnd());
                    req.setActivity(req.getActivity() + ":::" + req2.getActivity() + ";;;" + req2.getTimeBegin().toString(0) + ";;;" + req2.getTimeEnd().toString(0));
                    requisicoes.remove(req2);
                }
            }
        }
        java.util.List<Keys.Request> requess = new ArrayList<>(requisicoes);
        Collections.sort(requess);
        return requess;
    }
}
