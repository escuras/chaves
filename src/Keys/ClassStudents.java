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
public class ClassStudents implements Comparable<ClassStudents> {

    private String codigo;
    private String designacao;
    private int numero_alunos;
    private String codigo_curso;
    private String curso;

    public ClassStudents() {
        codigo = "-1";
        designacao = "";
        numero_alunos = 0;
        codigo_curso = "-1";
        curso = "";
    }

    public ClassStudents(String codigo) {
        this.codigo = codigo;
        designacao = "";
        numero_alunos = 0;
        codigo_curso = "-1";
        curso = "";
    }

    public ClassStudents(String codigo, String designacao, int numero, String cd, String curso) {
        this.codigo = codigo;
        this.designacao = designacao;
        numero_alunos = numero;
        codigo_curso = cd;
        this.curso = curso;
    }

    public ClassStudents(ClassStudents turma) {
        this.codigo = turma.getCode();
        this.designacao = turma.getName();
        numero_alunos = turma.getNumberOfStudents();
        codigo_curso = turma.getDegreeCode();
        this.curso = turma.getDegree();
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

    /**
     * @return the designacao
     */
    public String getName() {
        return designacao;
    }

    /**
     * @param designacao the designacao to set
     */
    public void setName(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return the numero_alunos
     */
    public int getNumberOfStudents() {
        return numero_alunos;
    }

    /**
     * @param numero_alunos the numero_alunos to set
     */
    public void setNumberOfStudents(int numero_alunos) {
        this.numero_alunos = numero_alunos;
    }

    /**
     * @return the codigo_curso
     */
    public String getDegreeCode() {
        return codigo_curso;
    }

    /**
     * @param codigo_curso the codigo_curso to set
     */
    public void setDegreeCode(String codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    /**
     * @return the curso
     */
    public String getDegree() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setDegree(String curso) {
        this.curso = curso;
    }


    @Override
    public int compareTo(ClassStudents o) {
        if ((o.getName().equals(this.getName())) && (o.getCode().equals(this.getCode()))&&(o.getDegreeCode().equals(this.getDegreeCode()))&&(o.getDegree().equals(this.getDegree()))) {
            return 0;
        } else {
            return 1;
        }
    }

}
