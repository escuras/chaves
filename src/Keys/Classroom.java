/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Keys;

/**
 *
 * @author toze
 */
public class Classroom extends Material{
    private int ncomputadores; 
    private int lugares;
    private boolean projetor;
    private boolean quadro;
    private java.util.ArrayList<Subject> disciplinas;
    private java.util.ArrayList<Software> programas;
    
    
    public Classroom(){
        super();
        this.ncomputadores = -1;
        this.lugares = -1;
        this.projetor = false;
        this.quadro = false;
        this.disciplinas = new java.util.ArrayList<>();
        this.programas = new java.util.ArrayList<>();
    }
    
    public Classroom(Material m){
        super(m);
        this.ncomputadores = -1;
        this.lugares = -1;
        this.projetor = false;
        this.quadro = false;
        this.disciplinas = new java.util.ArrayList<>();
        this.programas = new java.util.ArrayList<>();
    }
    
    public Classroom(Material m, int ncomputadores, int lugares, boolean projetor, boolean quadro){
        super(m);
        this.ncomputadores = ncomputadores;
        this.lugares = lugares;
        this.projetor = projetor;
        this.quadro = quadro;
        this.disciplinas = new java.util.ArrayList<>();
        this.programas = new java.util.ArrayList<>();
    }
    
    
    public Classroom(Material m, int ncomputadores, int lugares, boolean projetor, boolean quadro, java.util.ArrayList<Subject> disciplinas, java.util.ArrayList<Software> programas){
        super(m);
        this.ncomputadores = ncomputadores;
        this.lugares = lugares;
        this.projetor = projetor;
        this.quadro = quadro;
        this.disciplinas = disciplinas;
        this.programas = programas;
    }

    public Classroom(Classroom classroom){
        super(classroom);
        this.ncomputadores = classroom.getComputers();
        this.lugares = classroom.getPlaces();
        this.projetor = classroom.hasProjector();
        this.disciplinas = classroom.getSubjects();
        this.quadro = classroom.hasInteractiveTable();
    }
    
    public void addSubjectSpecific(Subject disciplina){
        this.disciplinas.add(disciplina);
    }
    
    public void addSubjectSpecific(int id, String disciplina, String code){
        this.disciplinas.add(new Subject(id,disciplina,code));
    }
    
    public void removeSuject(Subject disciplina){
        this.disciplinas.stream().forEach((dis) -> {
            if (dis.equals(disciplina)) this.disciplinas.remove(dis);
        });
    }

    public void removeSuject(String disciplina){
        this.disciplinas.stream().forEach((dis) -> {
            if (dis.getName().equals(disciplina)) this.disciplinas.remove(dis);
        });
    }
    
    public String listSubjects(){
        String lista = "";
        lista = this.disciplinas.stream().map((dis) -> dis.getName()+"\n").reduce(lista, String::concat);
        return lista;
    } 
    
    public void addSoftware(Software soft){
        this.programas.add(soft);
    }
    
    public void addSoftware(String nome, String versao, boolean atualizado){
        this.programas.add(new Software(nome,versao,atualizado));
    }
    
    public void removeSoftare(Software soft){
        this.programas.stream().forEach((dis) -> {
            if (dis.equals(soft)) this.programas.remove(dis);
        });
    }

    public void removeSoftware(String nome){
        this.programas.stream().forEach((dis) -> {
            if (dis.getName().equals(nome)) this.programas.remove(dis);
        });
    }
    
    public String listSoftware(){
        String lista = "";
        lista = this.programas.stream().map((dis) -> dis.getName()+"\n").reduce(lista, String::concat);
        return lista;
    } 

    /**
     * @return the ncomputadores
     */
    public int getComputers() {
        return ncomputadores;
    }

    /**
     * @param ncomputadores the ncomputadores to set
     */
    public void setComputers(int ncomputadores) {
        this.ncomputadores = ncomputadores;
    }

    /**
     * @return the lugares
     */
    public int getPlaces() {
        return lugares;
    }

    /**
     * @param lugares the lugares to set
     */
    public void setPlaces(int lugares) {
        this.lugares = lugares;
    }

    /**
     * @return the projetor
     */
    public boolean hasProjector() {
        return projetor;
    }

    /**
     * @param projetor the projetor to set
     */
    public void setProjector(boolean projetor) {
        this.projetor = projetor;
    }
    
    public boolean hasSpace(int val) {
        return (val >= this.getPlaces());
    }

    /**
     * @return the disciplinas
     */
    public java.util.ArrayList<Subject> getSubjects() {
        return disciplinas;
    }

    /**
     * @param disciplinas the disciplinas to set
     */
    public void setSubjects(java.util.ArrayList<Subject> disciplinas) {
        this.disciplinas = disciplinas;
    }

    /**
     * @return the quadro
     */
    public boolean hasInteractiveTable() {
        return quadro;
    }

    /**
     * @param quadro the quadro to set
     */
    public void setInteractiveTable(boolean quadro) {
        this.quadro = quadro;
    }

    /**
     * @return the programas
     */
    public java.util.ArrayList<Software> getSoftwareInClassroomComputers() {
        return programas;
    }

    /**
     * @param programas the programas to set
     */
    public void setSofwareInClassroomComputers(java.util.ArrayList<Software> programas) {
        this.programas = programas;
    }
    
}
