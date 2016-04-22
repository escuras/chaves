/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

/**
 *
 * @author toze
 */
public class Classroom extends Material{
    private String outros;
    private int ncomputadores; 
    private int lugares;
    private boolean projetor;
    private boolean quadro;
    private java.util.ArrayList<Subject> disciplinas;
    
    
    public Classroom(){
        super();
        this.outros = "";
        this.ncomputadores = -1;
        this.lugares = -1;
        this.projetor = false;
        this.quadro = false;
        this.disciplinas = new java.util.ArrayList<>();
    }
    
    public Classroom(Material m){
        super(m);
        this.outros = "";
        this.ncomputadores = -1;
        this.lugares = -1;
        this.projetor = false;
        this.quadro = false;
        this.disciplinas = new java.util.ArrayList<>();
    }
    
    public Classroom(Material m, int ncomputadores, int lugares, boolean projetor, boolean quadro){
        super(m);
        this.outros = "";
        this.ncomputadores = ncomputadores;
        this.lugares = lugares;
        this.projetor = projetor;
        this.quadro = quadro;
        this.disciplinas = new java.util.ArrayList<>();
    }
    
    public Classroom(Material m, String outros, int ncomputadores, int lugares, boolean projetor, boolean quadro){
        super(m);
        this.outros = outros;
        this.ncomputadores = ncomputadores;
        this.lugares = lugares;
        this.projetor = projetor;
        this.quadro = quadro;
        this.disciplinas = new java.util.ArrayList<>();
    }
    
    public Classroom(Material m, String outros, int ncomputadores, int lugares, boolean projetor, boolean quadro, java.util.ArrayList<Subject> disciplinas){
        super(m);
        this.outros = outros;
        this.ncomputadores = ncomputadores;
        this.lugares = lugares;
        this.projetor = projetor;
        this.quadro = quadro;
        this.disciplinas = disciplinas;
    }

    public Classroom(Classroom classroom){
        super(classroom);
        this.outros = classroom.getAnotherValues();
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

    /**
     * @return another values
     */
    public String getAnotherValues() {
        return this.outros;
    }

    /**
     * @param outros the another values to set
     */
    public void setAnotherValues(String outros) {
        this.outros = outros;
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
    public void addProjector(boolean projetor) {
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
    
}
