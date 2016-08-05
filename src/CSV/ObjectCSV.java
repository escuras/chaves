/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSV;

/**
 *
 * @author toze
 */
public class ObjectCSV {
    private Keys.Person pessoa;
    private Keys.Subject disciplina;
    private Keys.Material sala;
    private TimeDate.WeekDay dia;
    private TimeDate.Time horainicio;
    private TimeDate.Time horafim;
    private String atividade;
    private Keys.ClassStudents turma;
    public ObjectCSV(ElementsCSV elementos){
        Keys.Function funcao = new Keys.Function("Professor");
        Keys.TypeOfMaterial material = new Keys.TypeOfMaterial("Sala de aula");
        this.pessoa = new Keys.Person(elementos.getPersonName(),elementos.getPersonCode(),funcao);
        this.disciplina =new Keys.Subject(elementos.getSubjectName(),elementos.getCdSubject());
        this.sala = new Keys.Material(material, elementos.getMaterialCode(),Text.TreatText.getNumberfromString(elementos.getMaterialDescription()), false);
        this.dia = new TimeDate.WeekDay(elementos.getDayWeek());
        this.horainicio = new TimeDate.Time(elementos.getHourIni(), elementos.getMinuteIni());
        this.horafim = new TimeDate.Time(elementos.getHourEnd(), elementos.getMinuteEnd());
        this.atividade = elementos.getActivity();
        this.turma = new Keys.ClassStudents(elementos.getClassCode());
    }
    
    
    public ObjectCSV(ObjectCSV objeto){
        this.pessoa = objeto.getPerson();
        this.disciplina = objeto.getSubject();
        this.sala = objeto.getClassRoom();
        this.dia = objeto.getWeekDay();
        this.horainicio = objeto.getIniHour();
        this.horafim = objeto.getEndHour();
        this.atividade = objeto.getActivity();
        this.turma = objeto.getStudentsClass();
    }
    
    public Keys.Request getRequest(TimeDate.Date date){
        return new Keys.Request(date, date, this.dia,this.horainicio,this.horafim,this.pessoa,this.sala,this.disciplina,this.atividade, "csv",turma);
    } 

    /**
     * @return the pessoa
     */
    public Keys.Person getPerson() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPerson(Keys.Person pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the disciplina
     */
    public Keys.Subject getSubject() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setSubject(Keys.Subject disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the sala
     */
    public Keys.Material getClassRoom() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setClassRoom(Keys.Material sala) {
        this.sala = sala;
    }

    /**
     * @return the dia
     */
    public TimeDate.WeekDay getWeekDay() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setWeekday(TimeDate.WeekDay dia) {
        this.dia = dia;
    }

    /**
     * @return the horainicio
     */
    public TimeDate.Time getIniHour() {
        return horainicio;
    }

    /**
     * @param horainicio the horainicio to set
     */
    public void setIniHour(TimeDate.Time horainicio) {
        this.horainicio = horainicio;
    }

    /**
     * @return the horafim
     */
    public TimeDate.Time getEndHour() {
        return horafim;
    }

    /**
     * @param horafim the horafim to set
     */
    public void setEndHour(TimeDate.Time horafim) {
        this.horafim = horafim;
    }

    /**
     * @return the atividade
     */
    public String getActivity() {
        return atividade;
    }

    /**
     * @param atividade the atividade to set
     */
    public void setActivity(String atividade) {
        this.atividade = atividade;
    }

    /**
     * @return the turma
     */
    public Keys.ClassStudents getStudentsClass() {
        return turma;
    }

    /**
     * @param turma the turma to set
     */
    public void setStudentsClass(Keys.ClassStudents turma) {
        this.turma = turma;
    }
    
    
}
