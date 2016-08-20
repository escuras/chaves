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
public class Subject implements Comparable<Subject> {
    private String nome;
    private String codigo;
    private int id;
    
    public Subject(){
        this.nome = "Sem relevância";
        this.codigo = "";
        this.id = -1;
    }
    
    public Subject(int id, String nome,String codigo) {
        this.nome = nome;
        this.id = id;
        this.codigo = codigo;
    }
    
     public Subject(String nome,String codigo) {
        this.nome = nome;
        this.id = -1;
        this.codigo = codigo;
    }
    
    public Subject(Subject disciplina) {
        this.nome = disciplina.getName();
        this.id = disciplina.getId();
        this.codigo = disciplina.getCode();
    }

    /**
     * @return the nome
     */
    public String getName() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setName(String nome) {
        this.nome = nome;
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
     * @return the codigo
     */
    public String getCode() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCode(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int compareTo(Subject o) {
        int val;
        if ((val = this.getName().compareTo(o.getName())) == 0) {
           return this.getCode().compareTo(this.getCode()); 
        }
        return val;
    }
    
}
