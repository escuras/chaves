/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Keys;

import TimeDate.WeekDay;
import TimeDate.Time;
import TimeDate.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Subject disciplina;
    private java.util.Set<Keys.Subject> disciplinas;
    private Material material;
    private String origem;
    private int id;
    private boolean ativo;
    private boolean terminado;
    private int substituido;
    private int quantidade;
    private Date data_levantamento;
    private Date data_entrega;
    private Time tempo_levantamento;
    private Time tempo_entrega;
    private String atividade;
    private ClassStudents turma;
    private java.util.Set<Keys.ClassStudents> turmas;
    private int id_conjunta;

    public Request() {
        this.begin = new TimeDate.Date();
        this.end = new TimeDate.Date();
        this.id = -1;
        try {
            this.dia = new TimeDate.WeekDay(this.begin);
        } catch (ParseException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tinicio = new TimeDate.Time();
        this.tfim = new TimeDate.Time();
        this.pessoa = new Keys.Person();
        this.material = new Keys.Material();
        this.disciplina = new Subject();
        this.disciplinas = new java.util.HashSet<>();
        this.disciplinas.add(disciplina);
        this.atividade = "sem";
        this.origem = "";
        this.ativo = false;
        this.terminado = false;
        this.substituido = 0;
        this.quantidade = 1;
        this.tempo_levantamento = null;
        this.tempo_entrega = null;
        this.data_entrega = null;
        this.data_levantamento = null;
        this.turma = new ClassStudents();
        this.turmas = new java.util.HashSet<>();
        this.turmas.add(turma);
        this.id_conjunta = 0;
    }

    public Request(Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, String origem) {
        this.begin = date;
        this.end = date2;
        this.id = -1;
        this.dia = dia;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material = material;
        this.disciplina = new Subject();
        this.disciplinas = new java.util.HashSet<>();
        this.disciplinas.add(disciplina);
        this.atividade = "sem";
        this.origem = origem;
        this.ativo = false;
        this.terminado = false;
        this.substituido = 0;
        this.quantidade = 1;
        this.tempo_levantamento = null;
        this.tempo_entrega = null;
        this.data_entrega = null;
        this.data_levantamento = null;
        this.turma = new ClassStudents();
        this.turmas = new java.util.HashSet<>();
        this.turmas.add(turma);
        this.id_conjunta = 0;
    }

    public Request(Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, String origem, int quantidade) {
        this.begin = date;
        this.end = date2;
        this.id = -1;
        this.dia = dia;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material = material;
        this.disciplina = new Subject();
        this.disciplinas = new java.util.HashSet<>();
        this.disciplinas.add(disciplina);
        this.atividade = "sem";
        this.origem = origem;
        this.ativo = false;
        this.terminado = false;
        this.substituido = 0;
        this.quantidade = quantidade;
        this.tempo_levantamento = null;
        this.tempo_entrega = null;
        this.data_entrega = null;
        this.data_levantamento = null;
        this.turma = new ClassStudents();
        this.turmas = new java.util.HashSet<>();
        this.turmas.add(turma);
        this.id_conjunta = 0;
    }

    public Request(int id, Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, Subject disciplina, String atividade, ClassStudents turma, String origem, boolean ativo, boolean terminado, int substituido, int quantidade, TimeDate.Date data_levantamento, TimeDate.Time tempo_levantamento, TimeDate.Date data_entrega, TimeDate.Time tempo_entrega, int id_conjunta) {
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.id = id;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material = material;
        this.disciplina = disciplina;
        this.disciplinas = new java.util.HashSet<>();
        disciplinas.add(disciplina);
        this.origem = origem;
        this.atividade = atividade;
        this.ativo = ativo;
        this.terminado = terminado;
        this.substituido = substituido;
        this.quantidade = quantidade;
        this.tempo_levantamento = tempo_levantamento;
        this.tempo_entrega = tempo_entrega;
        this.data_entrega = data_entrega;
        this.data_levantamento = data_levantamento;
        this.turma = turma;
        this.turmas = new java.util.HashSet<>();
        turmas.add(turma);
        this.id_conjunta = id_conjunta;
    }
    
    public Request(int id, Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, java.util.Set<Keys.Subject> disciplinas, String atividade, java.util.Set<Keys.ClassStudents> turmas, String origem, boolean ativo, boolean terminado, int substituido, int quantidade, TimeDate.Date data_levantamento, TimeDate.Time tempo_levantamento, TimeDate.Date data_entrega, TimeDate.Time tempo_entrega, int id_conjunta) {
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.id = id;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material = material;
        this.disciplinas = disciplinas;
        if (this.disciplinas.size() > 0) {
            this.disciplina = (Keys.Subject)this.disciplinas.toArray()[0];
        } else {
            this.disciplina = new Keys.Subject();
        }
        this.origem = origem;
        this.atividade = atividade;
        this.ativo = ativo;
        this.terminado = terminado;
        this.substituido = substituido;
        this.quantidade = quantidade;
        this.tempo_levantamento = tempo_levantamento;
        this.tempo_entrega = tempo_entrega;
        this.data_entrega = data_entrega;
        this.data_levantamento = data_levantamento;
        this.turmas = turmas;
        if (this.turmas.size() > 0) {
            this.turma = (Keys.ClassStudents)this.turmas.toArray()[0];
        } else {
            this.turma = new Keys.ClassStudents();
        }
        this.id_conjunta = id_conjunta;
    }
    
    public Request(int id, Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, java.util.Set<Keys.Subject> disciplinas, String atividade, ClassStudents turma, String origem, boolean ativo, boolean terminado, int substituido, int quantidade, TimeDate.Date data_levantamento, TimeDate.Time tempo_levantamento, TimeDate.Date data_entrega, TimeDate.Time tempo_entrega, int id_conjunta) {
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.id = id;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material = material;
        this.disciplinas = disciplinas;
        if (this.disciplinas.size() > 0) {
            this.disciplina = (Keys.Subject)this.disciplinas.toArray()[0];
        } else {
            this.disciplina = new Keys.Subject();
        }
        this.origem = origem;
        this.atividade = atividade;
        this.ativo = ativo;
        this.terminado = terminado;
        this.substituido = substituido;
        this.quantidade = quantidade;
        this.tempo_levantamento = tempo_levantamento;
        this.tempo_entrega = tempo_entrega;
        this.data_entrega = data_entrega;
        this.data_levantamento = data_levantamento;
        this.turma = turma;
        this.turmas = new java.util.HashSet<>();
        turmas.add(turma);
        this.id_conjunta = id_conjunta;
    }
    
    public Request(int id, Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, Subject disciplina, String atividade, java.util.Set<Keys.ClassStudents> turmas, String origem, boolean ativo, boolean terminado, int substituido, int quantidade, TimeDate.Date data_levantamento, TimeDate.Time tempo_levantamento, TimeDate.Date data_entrega, TimeDate.Time tempo_entrega, int id_conjunta) {
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.id = id;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material = material;
        this.disciplina = disciplina;
        this.disciplinas = new java.util.HashSet<>();
        disciplinas.add(disciplina);
        this.origem = origem;
        this.atividade = atividade;
        this.ativo = ativo;
        this.terminado = terminado;
        this.substituido = substituido;
        this.quantidade = quantidade;
        this.tempo_levantamento = tempo_levantamento;
        this.tempo_entrega = tempo_entrega;
        this.data_entrega = data_entrega;
        this.data_levantamento = data_levantamento;
        this.turmas = turmas;
        if (this.turmas.size() > 0) {
            this.turma = (Keys.ClassStudents)this.turmas.toArray()[0];
        } else {
            this.turma = new Keys.ClassStudents();
        }
        this.id_conjunta = id_conjunta;
    }

    public Request(Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, Subject disciplina, String atividade, String origem, ClassStudents turma) {
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.id = -1;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material = material;
        this.disciplina = disciplina;
        this.disciplinas = new java.util.HashSet<>();
        disciplinas.add(disciplina);
        this.atividade = atividade;
        this.origem = origem;
        this.ativo = false;
        this.terminado = false;
        this.substituido = 0;
        this.quantidade = 1;
        this.tempo_levantamento = null;
        this.tempo_entrega = null;
        this.data_entrega = null;
        this.data_levantamento = null;
        this.turma = turma;
        this.turmas = new java.util.HashSet<>();
        turmas.add(turma);
        this.id_conjunta = 0;
    }
    
    public Request(Date date, Date date2, WeekDay dia, Time tinicio, Time tfim, Person pessoa, Material material, java.util.Set<Keys.Subject> disciplinas, String atividade, String origem, java.util.Set<Keys.ClassStudents> turmas) {
        this.begin = date;
        this.end = date2;
        this.dia = dia;
        this.id = -1;
        this.tinicio = tinicio;
        this.tfim = tfim;
        this.pessoa = pessoa;
        this.material = material;
        this.disciplinas = disciplinas;
        if (this.disciplinas.size() > 0) {
            this.disciplina = (Keys.Subject)this.disciplinas.toArray()[0];
        } else {
            this.disciplina = new Keys.Subject();
        }
        this.atividade = atividade;
        this.origem = origem;
        this.ativo = false;
        this.terminado = false;
        this.substituido = 0;
        this.quantidade = 1;
        this.tempo_levantamento = null;
        this.tempo_entrega = null;
        this.data_entrega = null;
        this.data_levantamento = null;
        this.turmas = turmas;
        if (this.turmas.size() > 0) {
            this.turma = (Keys.ClassStudents)this.turmas.toArray()[0];
        } else {
            this.turma = new Keys.ClassStudents();
        }
        this.id_conjunta = 0;
    }

    

    public Request(Request req) {
        this.begin = req.getBeginDate();
        this.end = req.getEndDate();
        this.dia = req.getWeekDay();
        this.id = req.getId();
        this.tinicio = req.getTimeBegin();
        this.tfim = req.getTimeEnd();
        this.pessoa = req.getPerson();
        this.atividade = req.getActivity();
        this.material = req.getMaterial();
        this.disciplina = req.getSubject();
        this.origem = req.getSource();
        this.ativo = req.isActive();
        this.terminado = req.isTerminated();
        this.substituido = req.getSubstitute();
        this.quantidade = req.getQuantity();
        this.data_entrega = req.getDeliveryDate();
        this.data_levantamento = req.getLiftDate();
        this.tempo_entrega = req.getDeliveryTime();
        this.tempo_levantamento = req.getLiftTime();
        this.turma = req.getStudentsClass();
        this.id_conjunta = req.getUnionRequest();
        this.disciplinas = req.getSubjects();
        this.turmas = req.getStudentsClasses();
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
        return disciplina;
    }

    /**
     * @param discplina the discplina to set
     */
    public void setSubject(Subject discplina) {
        this.disciplina = discplina;
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

    public boolean isTerminated() {
        return this.terminado;
    }

    public void setTerminated() {
        this.terminado = true;
    }

    public boolean isActive() {
        return this.ativo;
    }

    public void setActive() {
        this.terminado = true;
    }

    @Override
    public int compareTo(Request o) {
        int valor;
        if ((valor = Integer.compare(this.getBeginDate().getYear(), o.getBeginDate().getYear())) == 0) {
            if ((valor = Integer.compare(this.getBeginDate().getMonth(), o.getBeginDate().getMonth())) == 0) {
                if ((valor = Integer.compare(this.getBeginDate().getDay(), o.getBeginDate().getDay())) == 0) {
                    if ((valor = Integer.compare(this.getTimeBegin().getHour(), o.getTimeBegin().getHour())) == 0) {
                        if ((valor = Integer.compare(this.getTimeBegin().getMinutes(), o.getTimeBegin().getMinutes())) == 0) {
                            if ((valor = Integer.compare(this.getEndDate().getYear(), o.getEndDate().getYear())) == 0) {
                                if ((valor = Integer.compare(this.getEndDate().getMonth(), o.getEndDate().getMonth())) == 0) {
                                    if ((valor = Integer.compare(this.getEndDate().getDay(), o.getEndDate().getDay())) == 0) {
                                        if ((valor = Integer.compare(this.getTimeEnd().getHour(), o.getTimeEnd().getHour())) == 0) {
                                            if ((valor = Integer.compare(this.getTimeEnd().getMinutes(), o.getTimeEnd().getMinutes())) == 0) {
                                                if ((valor = this.getPerson().getName().compareToIgnoreCase(o.getPerson().getName())) == 0) {
                                                    if ((valor = this.getMaterial().getCodeOfMaterial().compareToIgnoreCase(o.getMaterial().getCodeOfMaterial())) == 0) {
                                                        valor = this.getSubject().getCode().compareToIgnoreCase(o.getSubject().getCode());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return valor;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setState(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the substituido
     */
    public int getSubstitute() {
        return substituido;
    }

    /**
     * @param substituido the substituido to set
     */
    public void setSubstitute(int substituido) {
        this.substituido = substituido;
    }

    /**
     * @return the quantidade
     */
    public int getQuantity() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantity(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the data_levantamento
     */
    public Date getLiftDate() {
        return data_levantamento;
    }

    /**
     * @param data_levantamento the data_levantamento to set
     */
    public void setLiftDate(Date data_levantamento) {
        this.data_levantamento = data_levantamento;
    }

    /**
     * @return the data_entrega
     */
    public Date getDeliveryDate() {
        return data_entrega;
    }

    /**
     * @param data_entrega the data_entrega to set
     */
    public void setDeliveryDate(Date data_entrega) {
        this.data_entrega = data_entrega;
    }

    /**
     * @return the tempo_levantamento
     */
    public Time getLiftTime() {
        return tempo_levantamento;
    }

    /**
     * @param tempo_levantamento the tempo_levantamento to set
     */
    public void setLiftTime(Time tempo_levantamento) {
        this.tempo_levantamento = tempo_levantamento;
    }

    /**
     * @return the tempo_entrega
     */
    public Time getDeliveryTime() {
        return tempo_entrega;
    }

    /**
     * @param tempo_entrega the tempo_entrega to set
     */
    public void setDeliveryTime(Time tempo_entrega) {
        this.tempo_entrega = tempo_entrega;
    }

    /**
     * @return the Atividade
     */
    public String getActivity() {
        return atividade;
    }

    /**
     * @param Atividade the Atividade to set
     */
    public void setActivity(String Atividade) {
        this.atividade = Atividade;
    }

    /**
     * @return the turma
     */
    public ClassStudents getStudentsClass() {
        return turma;
    }

    /**
     * @param turma the turma to set
     */
    public void setStudentsClass(ClassStudents turma) {
        this.turma = turma;
    }

    /**
     * @return the id_conjunta
     */
    public int getUnionRequest() {
        return id_conjunta;
    }

    /**
     * @param id_conjunta the id_conjunta to set
     */
    public void setUnionRequest(int id_conjunta) {
        this.id_conjunta = id_conjunta;
    }

    /**
     * @return the turmas
     */
    public java.util.Set<Keys.ClassStudents> getStudentsClasses() {
        return turmas;
    }

    /**
     * @param turmas the turmas to set
     */
    public void setStudentsClasses(java.util.Set<Keys.ClassStudents> turmas) {
        this.turmas = turmas;
        this.turma = (Keys.ClassStudents) this.turmas.toArray()[0];
        
    }

    /**
     * @return the disciplinas
     */
    public java.util.Set<Keys.Subject> getSubjects() {
        return disciplinas;
    }

    /**
     * @param disciplinas the disciplinas to set
     */
    public void setSubjects(java.util.Set<Keys.Subject> disciplinas) {
        this.disciplinas = disciplinas;
        this.disciplina = (Keys.Subject)this.disciplinas.toArray()[0];
    }
    
    public boolean isMultiDisciplinar(){
        return (this.disciplinas.size() > 1);
    }
    
    public boolean isMultiClass(){
        return (this.turmas.size() > 1);
    }

}
