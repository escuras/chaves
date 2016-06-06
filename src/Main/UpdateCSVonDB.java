/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author toze
 */
public class UpdateCSVonDB {

    private TimeDate.Date inicio;
    private TimeDate.Date fim;
    private TimeDate.BreakPeriodList ferias;
    private TimeDate.HolidaysList feriados;
    private String database;
    private String csv;
    private Clavis.Function funcao;

    public UpdateCSVonDB() {
        inicio = new TimeDate.Date();
        fim = new TimeDate.Date();
        ferias = new TimeDate.BreakPeriodList();
        feriados = new TimeDate.HolidaysList(new TimeDate.Date().getYear());
        database = "";
        csv = "";
    }

    public UpdateCSVonDB(TimeDate.Date inicio, TimeDate.Date fim, TimeDate.BreakPeriodList ferias, TimeDate.HolidaysList feriados, String database, String csv) {
        this.inicio = inicio;
        this.fim = fim;
        this.ferias = ferias;
        this.feriados = feriados;
        this.database = database;
        this.csv = csv;
    }

    public UpdateCSVonDB(UpdateCSVonDB db) {
        this.inicio = db.getBeginDate();
        this.fim = db.getEndDate();
        this.ferias = db.getBreakPeriods();
        this.feriados = db.getHolidays();
        this.database = db.getDatabaseString();
        this.csv = db.getCSVString();
    }

    public void update(String funcao) {
        if (this.funcao == null) {
            this.funcao = new Clavis.Function(funcao);
        }
        if ((!database.equals("")) && (!csv.equals(""))) {
            DataBase.DataBase db = new DataBase.DataBase(database);
            if (db.isTie()) {
                CSV.HandlingCSV han = new CSV.HandlingCSV(this.csv);
                han.searchElements();
                java.util.List<CSV.ElementsCSV> elementos = han.getElements();
                Set<Clavis.Request> requests = new TreeSet<>();
                if (elementos.size() > 0) {
                    int i = 0;
                    int k = 0;
                    boolean bauxiliar = false;
                    Set<Clavis.Person> pessoas = new java.util.TreeSet<>();
                    Set<Clavis.Material> materiais = new java.util.TreeSet<>();
                    Set<Clavis.Subject> disciplinas = new java.util.TreeSet<>();
                    while (i < elementos.size()) {
                        CSV.ObjectCSV aux = new CSV.ObjectCSV(elementos.get(i));
                        List<TimeDate.Date> datass = TimeDate.Date.DatesBetweenDates(this.inicio, this.fim, aux.getWeekDay().getDayNumber());
                        if (datass.size() > 0) {
                            for (int j = 0; j < datass.size(); j++) {
                                for (TimeDate.Holiday h : this.feriados.getHolidays()) {
                                    if ((datass.get(j).getDay() == h.getDay()) && (datass.get(j).getMonth() == h.getMonth())) {
                                        bauxiliar = true;
                                    }
                                }
                                for (TimeDate.BreakPeriod b : this.ferias.getBreakPeriodList()) {
                                    if ((datass.get(j).betweenDates(b.getBeginDate(), b.getEndDate()))) {
                                        bauxiliar = true;
                                    }
                                }
                                if (!bauxiliar) {
                                    Clavis.Person pes = aux.getPerson();
                                    Clavis.Material sala = aux.getClassRoom();
                                    Clavis.Subject disciplina = aux.getSubject();
                                    pes.setFunction(this.funcao);
                                    pessoas.add(pes);
                                    materiais.add(sala);
                                    disciplinas.add(disciplina);
                                    requests.add(aux.getRequest(datass.get(j)));
                                }
                                bauxiliar = false;
                            }
                        }
                        i++;
                    }
                    //db.insertPersons(pessoas);
                    db.insertMaterials(materiais);
                    //Clavis.Subject ss = new Clavis.Subject("História", "1234567");
                    //disciplinas.add(ss);
                }
            }
        }
    }

    /**
     * @return the begin date
     */
    public TimeDate.Date getBeginDate() {
        return inicio;
    }

    /**
     * @param inicio the date begin to set
     */
    public void setBeginDate(TimeDate.Date inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the end time date
     */
    public TimeDate.Date getEndDate() {
        return fim;
    }

    /**
     * @param fim the end date to set
     */
    public void setEndDate(TimeDate.Date fim) {
        this.fim = fim;
    }

    /**
     * @return the break periods
     */
    public TimeDate.BreakPeriodList getBreakPeriods() {
        return ferias;
    }

    /**
     * @param ferias the break periods to set
     */
    public void setBreakPeriods(TimeDate.BreakPeriodList ferias) {
        this.ferias = ferias;
    }

    /**
     * @return the holidays
     */
    public TimeDate.HolidaysList getHolidays() {
        return feriados;
    }

    /**
     * @param feriados the Holidays to set
     */
    public void setHolidays(TimeDate.HolidaysList feriados) {
        this.feriados = feriados;
    }

    /**
     * @return the database
     */
    public String getDatabaseString() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabaseString(String database) {
        this.database = database;
    }

    /**
     * @return the csv
     */
    public String getCSVString() {
        return csv;
    }

    /**
     * @param csv the csv to set
     */
    public void setCsvString(String csv) {
        this.csv = csv;
    }

    /**
     * @return the funcao
     */
    public Clavis.Function getFunction() {
        if (funcao == null) funcao = new Clavis.Function();
        return funcao;
    }

    /**
     * @param funcao the funcao to set
     */
    public void setFunction(Clavis.Function funcao) {
        this.funcao = funcao;
    }
}
