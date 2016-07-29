/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toze
 */
public class DataBase {

    private Connection con;
    private boolean tie;

    public DataBase(String url) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //jdbc:mysql://localhost:3306/Peoples?autoReconnect=true&useSSL=false;

            con = DriverManager.getConnection(url);
            tie = true;
        } catch (SQLException e) {
            tie = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            tie = false;
        }
    }

    public DataBase(String url, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            tie = true;
        } catch (SQLException e) {
            tie = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            tie = false;
        }
    }

    public boolean isTie() {
        return tie;
    }

    public java.util.List<Clavis.Person> getPersons() {
        java.util.List<Clavis.Person> pessoas = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Persons;";
                Clavis.Person pessoa;
                ResultSet rs;
                ResultSet rs2;
                Statement aux;
                try {
                    rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        pessoa = new Clavis.Person(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("identificacao"), rs.getString("telefone"), rs.getString("email"), rs.getInt("privilegio"));
                        sql = "select * from Functions where id_funcao ='" + rs.getInt("id_funcao") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Clavis.Function funcao = new Clavis.Function(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
                            pessoa.setFunction(funcao);
                        }
                        pessoas.add(pessoa);
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return pessoas;
    }

    public Clavis.Person getPerson(String identificacao) {
        Clavis.Person pessoa = new Clavis.Person();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Persons where identificacao like '" + identificacao + "';";
                ResultSet rs;
                ResultSet rs2;
                Statement aux;
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        pessoa = new Clavis.Person(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("identificacao"), rs.getString("telefone"), rs.getString("email"), rs.getInt("privilegio"));
                        sql = "select * from Functions where id_funcao ='" + rs.getInt("id_funcao") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Clavis.Function funcao = new Clavis.Function(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
                            pessoa.setFunction(funcao);
                        }
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return pessoa;
    }

    public Clavis.Person getPerson(int identificacao) {
        Clavis.Person pessoa = new Clavis.Person();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Persons where id_pessoa = " + identificacao + ";";
                ResultSet rs;
                ResultSet rs2;
                Statement aux;
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        pessoa = new Clavis.Person(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("identificacao"), rs.getString("telefone"), rs.getString("email"), rs.getInt("privilegio"));
                        sql = "select * from Functions where id_funcao ='" + rs.getInt("id_funcao") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Clavis.Function funcao = new Clavis.Function(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
                            pessoa.setFunction(funcao);
                        }
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return pessoa;
    }

    public Clavis.Person getPerson(String nome, String identificacao) {
        Clavis.Person pessoa = new Clavis.Person();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Persons where identificacao like '" + identificacao + "' and nome like '" + nome + "';";
                ResultSet rs;
                ResultSet rs2;
                Statement aux;
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        pessoa = new Clavis.Person(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("identificacao"), rs.getString("telefone"), rs.getString("email"), rs.getInt("privilegio"));
                        sql = "select * from Functions where id_funcao ='" + rs.getInt("id_funcao") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Clavis.Function funcao = new Clavis.Function(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
                            pessoa.setFunction(funcao);
                        }
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return pessoa;
    }

    public boolean insertPersons(java.util.Set<Clavis.Person> pessoas) {
        if (this.isTie()) {
            Statement smt;
            Statement smt2;
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
                for (Clavis.Person pessoa : pessoas) {
                    String nome = pessoa.getName();
                    String identificacao = pessoa.getIdentification();
                    String email = pessoa.getEmail();
                    String telefone = pessoa.getPhone();
                    String sql = "select count(*) from Persons where identificacao like '" + identificacao + "';";
                    if (smt != null) {
                        ResultSet rs = smt.executeQuery(sql);
                        if (rs.next()) {
                            if (rs.getInt(1) == 0) {
                                sql = "select id_funcao,privilegio from Functions where descricao like '" + pessoa.getFunction().getName() + "';";
                                smt2 = con.createStatement();
                                ResultSet rs2 = smt2.executeQuery(sql);
                                if (rs2.next()) {
                                    if ((!email.equals("sem")) && (!telefone.equals("sem"))) {
                                        sql = "insert into Persons (id_funcao,nome,identificacao,email,telefone,privilegio) values (" + rs2.getInt(1) + ",'" + nome + "','" + identificacao + "','" + email + "','" + telefone + "'," + rs2.getInt(2) + ");";
                                    } else if (!telefone.equals("sem")) {
                                        sql = "insert into Persons (id_funcao,nome,identificacao,telefone,privilegio) values (" + rs2.getInt(1) + ",'" + nome + "','" + identificacao + "','" + telefone + "'," + rs2.getInt(2) + ");";
                                    } else if (!email.equals("sem")) {
                                        sql = "insert into Persons (id_funcao,nome,identificacao,email,privilegio) values (" + rs2.getInt(1) + ",'" + nome + "','" + email + "','" + telefone + "'," + rs2.getInt(2) + ");";
                                    } else {
                                        sql = "insert into Persons (id_funcao,nome,identificacao,privilegio) values (" + rs2.getInt(1) + ",'" + nome + "','" + identificacao + "'," + rs2.getInt(2) + ");";
                                    }
                                    smt.execute(sql);
                                }
                            }
                        }
                    }
                }
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex1) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex1);
                }
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean insertPerson(Clavis.Person pessoa) {
        if (this.isTie()) {
            Statement smt;
            Statement smt2;
            String nome = pessoa.getName();
            String identificacao = pessoa.getIdentification();
            String email = pessoa.getEmail();
            String telefone = pessoa.getPhone();
            String sql = "select count(*) from Persons where identificacao like '" + identificacao + "';";
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            if (smt != null) {
                ResultSet rs;
                try {
                    rs = smt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                try {
                    if (rs.next()) {
                        if (rs.getInt(1) == 0) {
                            sql = "select id_funcao,privilegio from Functions where descricao like '" + pessoa.getFunction().getName() + "';";
                            smt2 = con.createStatement();
                            ResultSet rs2 = smt2.executeQuery(sql);
                            if (rs2.next()) {
                                if ((!email.equals("sem")) && (!telefone.equals("sem"))) {
                                    sql = "insert into Persons (id_funcao,nome,identificacao,email,telefone,privilegio) values (" + rs2.getInt(1) + ",'" + nome + "','" + identificacao + "','" + email + "','" + telefone + "'," + rs2.getInt(2) + ");";
                                } else if (!telefone.equals("sem")) {
                                    sql = "insert into Persons (id_funcao,nome,identificacao,telefone,privilegio) values (" + rs2.getInt(1) + ",'" + nome + "','" + identificacao + "','" + telefone + "'," + rs2.getInt(2) + ");";
                                } else if (!email.equals("sem")) {
                                    sql = "insert into Persons (id_funcao,nome,identificacao,email,privilegio) values (" + rs2.getInt(1) + ",'" + nome + "','" + email + "','" + telefone + "'," + rs2.getInt(2) + ");";
                                } else {
                                    sql = "insert into Persons (id_funcao,nome,identificacao,privilegio) values (" + rs2.getInt(1) + ",'" + nome + "','" + identificacao + "'," + rs2.getInt(2) + ");";
                                }
                                smt.execute(sql);
                                con.commit();
                                con.setAutoCommit(true);
                                return true;
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return false;
    }

    public boolean updatePerson(Clavis.Person pessoa) {
        if (this.isTie()) {
            Statement smt;
            Statement smt2;
            String identificacao = pessoa.getIdentification();
            String email = pessoa.getEmail();
            String telefone = pessoa.getPhone();
            String sql;
            if (pessoa.getId() < 0) {
                sql = "select id_pessoa from Persons where identificacao like '" + identificacao + "';";
                try {
                    smt = con.createStatement();
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        pessoa.setId(rs.getInt("id_pessoa"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            try {
                if (pessoa.getId() >= 0) {
                    sql = "select id_funcao,privilegio from Functions where descricao like '" + pessoa.getFunction().getName() + "';";
                    con.setAutoCommit(false);
                    smt2 = con.createStatement();
                    smt = con.createStatement();
                    ResultSet rs2 = smt2.executeQuery(sql);
                    if (rs2.next()) {
                        int privilegio;
                        if (pessoa.getPrivilege() < 0) {
                            privilegio = rs2.getInt("privilegio");
                        } else {
                            privilegio = pessoa.getPrivilege();
                        }
                        if ((!email.equals("sem")) && (!telefone.equals("sem"))) {
                            sql = "update Persons set id_funcao = '" + rs2.getInt("id_funcao") + "',nome = '" + pessoa.getName() + "', identificacao = '" + pessoa.getIdentification() + "',email= '" + pessoa.getEmail() + "',telefone = '" + pessoa.getPhone() + "' ,privilegio = " + privilegio + " where id_pessoa = " + pessoa.getId() + ";";
                        } else if (!telefone.equals("sem")) {
                            sql = "update Persons set id_funcao = '" + rs2.getInt("id_funcao") + "',nome = '" + pessoa.getName() + "', identificacao = '" + pessoa.getIdentification() + "',telefone = '" + pessoa.getPhone() + "', privilegio = " + privilegio + " where id_pessoa = " + pessoa.getId() + ";";
                        } else if (!email.equals("sem")) {
                            sql = "update Persons set id_funcao = '" + rs2.getInt("id_funcao") + "',nome = '" + pessoa.getName() + "', identificacao = '" + pessoa.getIdentification() + "',email = '" + pessoa.getEmail() + "', privilegio = " + privilegio + " where id_pessoa = " + pessoa.getId() + ";";
                        } else {
                            sql = "update Persons set id_funcao = '" + rs2.getInt("id_funcao") + "',nome = '" + pessoa.getName() + "', identificacao = '" + pessoa.getIdentification() + "', privilegio = " + privilegio + " where id_pessoa = " + pessoa.getId() + ";";
                        }
                        smt.executeUpdate(sql);
                        con.commit();
                        con.setAutoCommit(true);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    public boolean alterPrivilegeInPerson(Clavis.Person pessoa, int valor) {
        if (this.isTie()) {
            Statement smt;
            String sql = "update Persons set privilegio = " + valor + " where id_pessoa = " + pessoa.getId() + ";";
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
                smt.executeUpdate(sql);
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deletePerson(Clavis.Person pessoa) {
        if (this.isTie()) {
            Statement smt;
            String sql = "delete from Persons where id_pessoa = " + pessoa.getId() + ";";
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
                smt.executeUpdate(sql);
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deletePerson(int pessoa) {
        if (this.isTie()) {
            Statement smt;
            String sql = "delete from Persons where id_pessoa = " + pessoa + ";";
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
                smt.executeUpdate(sql);
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean insertStudentsClass(Clavis.ClassStudents turma) {
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            try {
                smt = con.createStatement();
                String sql = "select count(*) from StudentsClasses where codigo = '" + turma.getCode() + "';";
                rs = smt.executeQuery(sql);
                if ((rs.next()) && (rs.getInt(1) == 0)) {
                    con.setAutoCommit(false);
                    sql = "Insert into StudentsClasses (codigo,descricao,numero_alunos,codigo_curso,descricao_curso) values "
                            + "('" + turma.getCode() + "','" + turma.getName() + "',"
                            + " " + turma.getNumberOfStudents() + ", '" + turma.getDegreeCode() + "',"
                            + "'" + turma.getDegree() + "');";
                    smt.execute(sql);
                    con.commit();
                    con.setAutoCommit(true);
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean insertStudentsClasses(java.util.Set<Clavis.ClassStudents> turmas) {
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            for (Clavis.ClassStudents turma : turmas) {
                try {
                    smt = con.createStatement();
                    String sql = "select count(*) from StudentsClasses where codigo = '" + turma.getCode() + "';";
                    rs = smt.executeQuery(sql);
                    if ((rs.next()) && (rs.getInt(1) == 0)) {
                        con.setAutoCommit(false);
                        sql = "Insert into StudentsClasses (codigo,descricao,numero_alunos,codigo_curso,descricao_curso) values "
                                + "('" + turma.getCode() + "','" + turma.getName() + "',"
                                + " " + turma.getNumberOfStudents() + ", '" + turma.getDegreeCode() + "',"
                                + "'" + turma.getDegree() + "');";
                        smt.execute(sql);
                        con.commit();
                        con.setAutoCommit(true);
                        return true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public java.util.List<Clavis.ClassStudents> getStudentsClasses() {
        java.util.List<Clavis.ClassStudents> turmas = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select (codigo,descricao,numero_alunos,codigo_curso,descricao_curso) from StudentsClasses";
                Clavis.ClassStudents turma;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        turma = new Clavis.ClassStudents(rs.getString("codigo"), rs.getString("descricao"), rs.getInt("numero_alunos"), rs.getString("codigo_curso"), rs.getString("descricao_curso"));
                        turmas.add(turma);
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return turmas;
    }
    
    public Clavis.ClassStudents getStudentsClass(String codigo) {
        Clavis.ClassStudents turma = new Clavis.ClassStudents();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select (codigo,descricao,numero_alunos,codigo_curso,descricao_curso) from StudentsClasses where codigo = '" + codigo + "'";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        turma = new Clavis.ClassStudents(rs.getString("codigo"), rs.getString("descricao"), rs.getInt("numero_alunos"), rs.getString("codigo_curso"), rs.getString("descricao_curso"));
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return turma;
    }

    public java.util.List<Clavis.Function> getFunctions() {
        java.util.List<Clavis.Function> funcoes = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Functions;";
                Clavis.Function funcao;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        funcao = new Clavis.Function(rs.getInt(1), rs.getString(2), rs.getInt(3));
                        funcoes.add(funcao);
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return funcoes;
    }

    public java.util.List<Clavis.Function> getFunctionsWithprivilege(int privilegio) {
        java.util.List<Clavis.Function> funcoes = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Functions where privilegio = " + privilegio + ";";
                Clavis.Function funcao;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        funcao = new Clavis.Function(rs.getInt(1), rs.getString(2), rs.getInt(3));
                        funcoes.add(funcao);
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return funcoes;
    }

    public Clavis.Function getFunction(String descricao) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Functions where descricao like '" + descricao + "';";
                Clavis.Function funcao;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        funcao = new Clavis.Function(rs.getInt(1), rs.getString(2), rs.getInt(3));
                        if (!smt.isClosed()) {
                            smt.close();
                        }
                        return funcao;
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    public boolean insertFunction(Clavis.Function funcao) {
        if (this.isTie()) {
            Statement smt;
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
                String sql = "Insert into Functions (descricao, privilegio) values ('" + funcao.getName() + "'," + funcao.getPrivilege() + ");";
                smt.execute(sql);
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteFunction(Clavis.Function funcao) {
        if (this.isTie()) {
            Statement smt;
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
                String sql = "delete from Functions where id_function = " + funcao.getId() + ";";
                smt.execute(sql);
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean alterPivilegeInFunction(Clavis.Function funcao, int valor) {
        if (this.isTie()) {
            Statement smt;
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
                String sql = "update Functions set privilegio = " + valor + " where id_funcao = " + funcao.getId() + ";";
                smt.execute(sql);
                con.commit();
                con.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public java.util.List<Clavis.Material> getMaterials() {
        java.util.List<Clavis.Material> materiais = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            ResultSet rs2;
            Statement aux;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Materials order by id_tipo, descricao;";
                Clavis.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        sql = "select * from TypesOfMaterial where id_tipo ='" + rs.getInt("id_tipo") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Clavis.TypeOfMaterial tipo;
                            if (!rs2.getString("Imagem").equals("sem")) {
                                tipo = new Clavis.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"), rs2.getString("imagem"));
                            } else {
                                tipo = new Clavis.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"));
                            }
                            if (!rs.getString("imagem").equals("sem")) {
                                material = new Clavis.Material(rs.getInt("id_material"),tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getString("imagem"), rs.getBoolean("estado"));
                            } else {
                                material = new Clavis.Material(rs.getInt("id_material"), tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
                            }
                            materiais.add(material);
                        }
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return materiais;
    }

    public java.util.Set<Clavis.Material> getMaterialsByType(int id) {
        java.util.Set<Clavis.Material> materiais = new java.util.TreeSet<>();
        if (this.isTie()) {
            Statement smt;
            ResultSet rs2;
            Statement aux;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Materials where id_tipo='" + id + "' order by descricao asc;";
                Clavis.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        sql = "select * from TypesOfMaterial where id_tipo ='" + id + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Clavis.TypeOfMaterial tipo;
                            if (!rs2.getString("Imagem").equals("sem")) {
                                tipo = new Clavis.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"), rs2.getString("imagem"));
                            } else {
                                tipo = new Clavis.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"));
                            }
                            if (!rs.getString("imagem").equals("sem")) {
                                material = new Clavis.Material(rs.getInt("id_material"), tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getString("imagem"), rs.getBoolean("estado"));
                            } else {
                                material = new Clavis.Material(rs.getInt("id_material"), tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
                            }
                            materiais.add(material);
                        }
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return materiais;
    }

    public Clavis.Material getMaterial(String codigo) {
        if (this.isTie()) {
            Statement smt;
            Statement smt2;
            try {
                smt = con.createStatement();
                smt2 = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
                smt2 = null;
            }
            if (smt != null) {
                String sql = "select id_material, id_tipo, codigo, descricao, estado, imagem from Materials where codigo like '" + codigo + "';";
                ResultSet rs;
                ResultSet rs2;
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        Clavis.Material mat;
                        if (smt2 != null) {
                            int idtipo = rs.getInt("id_tipo");
                            sql = "select * from TypesOfMaterial where id_tipo = " + idtipo + ";";
                            rs2 = smt2.executeQuery(sql);
                            if (rs2.next()) {
                                Clavis.TypeOfMaterial tp = new Clavis.TypeOfMaterial();
                                tp.setMaterialTypeID(idtipo);
                                tp.setTotal(rs2.getInt("total"));
                                tp.setFree(rs2.getInt("livres"));
                                tp.setTypeOfMaterialName(rs2.getString("descricao"));
                                tp.setTypeOfMaterialImage(rs2.getString("imagem"));
                                mat = new Clavis.Material(rs.getInt("id_material"), tp, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
                                mat.setCodeOfMaterial(rs.getString("codigo"));
                                mat.setDescription(rs.getString("descricao"));
                                mat.setLoaned(rs.getBoolean("estado"));
                                if (!rs.getString("imagem").equals("sem")) {
                                    mat.setMaterialImage(rs.getString("imagem"));
                                }
                                return mat;
                            }

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public Clavis.Material getMaterial(int id_material) {
        if (this.isTie()) {
            Statement smt;
            Statement smt2;
            try {
                smt = con.createStatement();
                smt2 = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
                smt2 = null;
            }
            if (smt != null) {
                String sql = "select id_material, id_tipo, codigo, descricao, estado, imagem from Materials where id_material = " + id_material + ";";
                ResultSet rs;
                ResultSet rs2;
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        Clavis.Material mat;
                        if (smt2 != null) {
                            int idtipo = rs.getInt("id_tipo");
                            sql = "select * from TypesOfMaterial where id_tipo = " + idtipo + ";";
                            rs2 = smt2.executeQuery(sql);
                            if (rs2.next()) {
                                Clavis.TypeOfMaterial tp = new Clavis.TypeOfMaterial();
                                tp.setMaterialTypeID(idtipo);
                                tp.setTotal(rs2.getInt("total"));
                                tp.setFree(rs2.getInt("livres"));
                                tp.setTypeOfMaterialName(rs2.getString("descricao"));
                                tp.setTypeOfMaterialImage(rs2.getString("imagem"));
                                mat = new Clavis.Material(rs.getInt("id_material"), tp, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
                                mat.setCodeOfMaterial(rs.getString("codigo"));
                                mat.setDescription(rs.getString("descricao"));
                                mat.setLoaned(rs.getBoolean("estado"));
                                if (!rs.getString("imagem").equals("sem")) {
                                    mat.setMaterialImage(rs.getString("imagem"));
                                }
                                return mat;
                            }

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public java.util.Set<Clavis.Material> getMaterialwithSameFeature(Clavis.Feature feature) {
        java.util.Set<Clavis.Material> materiais = new java.util.TreeSet<>();
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            Statement smt2;
            ResultSet rs2;
            try {
                smt = con.createStatement();
                smt2 = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
                smt2 = null;
            }
            if (smt != null) {
                String sql = "select id_caracteristica from Features where upper(descricao) like upper('%" + feature.getDescription() + "%');";
                try {
                    rs = smt.executeQuery(sql);
                    int idcar;
                    while (rs.next()) {
                        idcar = rs.getInt("id_caracteristica");
                        sql = "select id_material from Rel_features_materials where id_caracteristica = " + idcar + ";";
                        if ((rs2 = smt2.executeQuery(sql)) != null) {
                            while (rs2.next()) {
                                materiais.add(this.getMaterial(rs2.getInt("id_material")));
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return materiais;
    }

    public java.util.Set<Clavis.Classroom> getClassrooms(int tipopesquisa) {
        java.util.Set<Clavis.Classroom> classrooms = new java.util.TreeSet<>();
        if (this.isTie()) {
            Statement smt;
            Statement smt2;
            Statement smt3;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "select * from TypesOfMaterial where id_tipo = 1";
                Clavis.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        Clavis.TypeOfMaterial tipo;
                        if (!rs.getString("Imagem").equals("sem")) {
                            tipo = new Clavis.TypeOfMaterial(rs.getInt("id_tipo"), rs.getString("descricao"), rs.getInt("total"), rs.getInt("livres"), rs.getString("imagem"));
                        } else {
                            tipo = new Clavis.TypeOfMaterial(rs.getInt("id_tipo"), rs.getString("descricao"), rs.getInt("total"), rs.getInt("livres"));
                        }
                        switch (tipopesquisa) {
                            case 0:
                                sql = "Select * from Materials where id_tipo = '1' and estado = 0 order by descricao";
                                break;
                            case 1:
                                sql = "Select * from Materials where id_tipo = '1' and estado = 1 order by descricao";
                                break;
                            default:
                                sql = "Select * from Materials where id_tipo = '1' order by descricao";
                                break;
                        }
                        try {
                            smt2 = con.createStatement();
                        } catch (SQLException ex) {
                            smt2 = null;
                        }
                        if (smt2 != null) {
                            ResultSet rs2 = smt2.executeQuery(sql);
                            while (rs2.next()) {
                                material = new Clavis.Material(rs2.getInt("id_material"), tipo, rs2.getString("codigo"), rs2.getString("descricao"), rs2.getString("imagem"), rs2.getBoolean("estado"));
                                sql = "Select * from Classrooms where codigo_sala = '" + rs2.getString("codigo") + "'";
                                try {
                                    smt3 = con.createStatement();
                                } catch (SQLException ex) {
                                    smt3 = null;
                                }
                                if (smt3 != null) {
                                    ResultSet rs3 = smt3.executeQuery(sql);
                                    if (rs3.next()) {
                                        Clavis.Classroom sala = new Clavis.Classroom(material, rs3.getInt("ncomputadores"), rs3.getInt("lugares"), rs3.getBoolean("projetor"), rs3.getBoolean("quadro_interativo"));
                                        classrooms.add(sala);
                                    }
                                    if (!smt3.isClosed()) {
                                        smt3.close();
                                    }
                                }
                            }
                            if (!smt2.isClosed()) {
                                smt2.close();
                            }
                        }
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return classrooms;
    }
    

    public Clavis.Classroom getClassroom(Clavis.Material m) {
        Clavis.Classroom sala = new Clavis.Classroom();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "select * from Classrooms where codigo_sala = '" + m.getCodeOfMaterial() + "'";
                Clavis.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        sala = new Clavis.Classroom(m, rs.getInt("ncomputadores"), rs.getInt("lugares"), rs.getBoolean("projetor"), rs.getBoolean("quadro_interativo"));
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return sala;
    }

    public boolean updateClassroom(Clavis.Classroom clas) {
        System.out.println("passou");
        if (this.isTie()) {
            String sql;
            Statement smt;
            System.out.println("passou");
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                sql = "update Classrooms set quadro_interativo = " + clas.hasInteractiveTable() + ", ncomputadores = " + clas.getComputers() + ", lugares = " + clas.getPlaces() + ", projetor = " + clas.hasProjector() + " where codigo_sala = '" + clas.getCodeOfMaterial() + "';";
                System.out.println("passou");
                try {
                    return (!smt.execute(sql));
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return false;
    }

    public boolean insertMaterials(java.util.Set<Clavis.Material> materiais) {
        if (this.isTie()) {
            if (materiais.size() > 0) {
                String sql;
                Statement smt;
                ResultSet rs;
                Statement smt2;
                ResultSet rs2;
                int idtipo = 0;
                boolean nemtodos = true;
                boolean passa;
                for (Clavis.Material material : materiais) {
                    try {
                        smt = con.createStatement();
                        passa = true;
                        idtipo = material.getMaterialTypeID();
                        if (material.getMaterialTypeID() == -1) {
                            sql = "select id_tipo from TypesOfMaterial where upper(descricao) like upper('" + material.getTypeOfMaterialName() + "');";
                            rs = smt.executeQuery(sql);
                            if (rs.next()) {
                                idtipo = rs.getInt(1);
                                passa = true;
                            } else {
                                passa = false;
                                nemtodos = false;
                            }
                        }
                        if (passa) {
                            sql = "select count(*) from Materials where codigo like '" + material.getCodeOfMaterial() + "' and id_tipo = " + idtipo + ";";
                            rs = smt.executeQuery(sql);
                            if (rs.next()) {
                                if (rs.getInt(1) == 0) {
                                    smt2 = con.createStatement();
                                    sql = "insert into Materials (id_tipo, codigo, descricao, estado, imagem) values (" + idtipo + ",'" + material.getCodeOfMaterial() + "','" + material.getDescription() + "'," + material.isLoaned() + ",'" + material.getMaterialImage() + "')";
                                    smt2.execute(sql);
                                    if (idtipo == 1) {
                                        sql = "insert into Classrooms (codigo_sala) values (" + material.getCodeOfMaterial() + ");";
                                        smt2.execute(sql);
                                    }
                                }
                            } else {
                                nemtodos = false;
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
                return nemtodos;
            }
        }
        return false;
    }

    public boolean insertMaterial(Clavis.Material material) {
        if (this.isTie()) {
            String sql;
            Statement smt;
            ResultSet rs;
            int idtipo = 0;
            boolean passa;
            try {
                smt = con.createStatement();
                passa = true;
                idtipo = material.getMaterialTypeID();
                if (material.getMaterialTypeID() == -1) {
                    sql = "select id_tipo from TypesOfMaterial where upper(descricao) like upper('" + material.getTypeOfMaterialName() + "');";
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        idtipo = rs.getInt(1);
                        passa = true;
                    } else {
                        passa = false;
                    }
                }
                if (passa) {
                    sql = "select count(*) from Materials where codigo like '" + material.getCodeOfMaterial() + "' and id_tipo = " + idtipo + ";";
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        if (rs.getInt(1) == 0) {
                            sql = "insert into Materials (id_tipo, codigo, descricao, estado, imagem) values (" + idtipo + ",'" + material.getCodeOfMaterial() + "','" + material.getDescription() + "'," + material.isLoaned() + ",'" + material.getMaterialImage() + "')";
                            smt.execute(sql);
                            if (idtipo == 1) {
                                sql = "insert into Classrooms (codigo_sala) values ('" + material.getCodeOfMaterial() + "');";
                                smt.execute(sql);
                            }
                        }

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean insertSubject(Clavis.Subject sub) {
        if (this.isTie()) {
            String sql;
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                sql = "insert into Subjects (descricao,codigo) values ('" + sub.getName() + "','" + sub.getCode() + "');";
                try {
                    return (!smt.execute(sql));
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean insertSubjects(java.util.Set<Clavis.Subject> subs) {
        if (this.isTie()) {
            String sql;
            Statement smt;
            Statement smt2;
            try {
                smt = con.createStatement();
                smt2 = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
                smt2 = null;
            }
            if (smt != null) {
                ResultSet rs;
                boolean cond = false;
                for (Clavis.Subject sub : subs) {
                    sql = "select count(*) from Subjects where descricao like '" + sub.getName() + "' and codigo like '" + sub.getCode() + "';";
                    if (smt2 != null) {
                        try {
                            rs = smt2.executeQuery(sql);
                            if (rs.next()) {
                                if (rs.getInt(1) == 0) {
                                    sql = "insert into Subjects (descricao,codigo) values ('" + sub.getName() + "','" + sub.getCode() + "');";
                                    try {
                                        smt.execute(sql);
                                        cond = true;
                                    } catch (SQLException ex) {
                                        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                                        cond = false;
                                    }
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                            cond = false;
                        }
                    } else {
                        cond = false;
                    }
                }
                return cond;
            }
        }
        return false;
    }

    public boolean deleteSubject(Clavis.Subject sub) {
        if (this.isTie()) {
            String sql;
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                sql = "delete from Subjects where codigo like '" + sub.getCode() + "';";
                try {
                    return (!smt.execute(sql));
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean associateSubjectWithClassroom(Clavis.Subject sub, Clavis.Classroom clas) {
        if (this.isTie()) {
            String sql;
            Statement smt;
            ResultSet rs;
            ResultSet rs2;
            int val = 0;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                try {
                    sql = "select id_disciplina from Subjects where codigo like '" + sub.getCode() + "' and descricao like '" + sub.getName() + "';";
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        val = rs.getInt("id_disciplina");
                        sql = "select count(*) from  Rel_classrooms_subjects where codigo_sala like '" + clas.getCodeOfMaterial() + "' and id_disciplina = " + val + ";";
                        rs2 = smt.executeQuery(sql);
                        if (rs2.next()) {
                            if (rs2.getInt(1) == 0) {
                                sql = "insert into Rel_classrooms_subjects (codigo_sala,id_disciplina) values('" + clas.getCodeOfMaterial() + "'," + val + ");";
                                return (!smt.execute(sql));
                            }
                        }
                    }
                    return false;
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean deleteAssociationBetweenSubjectAndClassroom(Clavis.Subject sub, Clavis.Classroom clas) {
        if (this.isTie()) {
            String sql;
            Statement smt;
            ResultSet rs;
            ResultSet rs2;
            int val = 0;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                try {
                    sql = "select id_disciplina from Subjects where codigo like '" + sub.getCode() + "' and descricao like '" + sub.getName() + "';";
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        val = rs.getInt("id_disciplina");
                        sql = "select count(*) from  Rel_classrooms_subjects where codigo_sala like '" + clas.getCodeOfMaterial() + "' and id_disciplina = " + val + ";";
                        rs2 = smt.executeQuery(sql);
                        if (rs2.next()) {
                            if (rs2.getInt(1) > 0) {
                                sql = "delete from Rel_classrooms_subjects where codigo_sala = '" + clas.getCodeOfMaterial() + "' and id_disciplina = " + val + ";";
                                return (!smt.execute(sql));
                            }
                        }
                    }
                    return false;
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean insertTypeOfMaterial(Clavis.TypeOfMaterial tipo) {
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "insert into TypesOfMaterial (descricao, total, livres, imagem) values('" + tipo.getTypeOfMaterialName() + "'," + tipo.getTotal() + "," + tipo.getFree() + ",'" + tipo.getTypeOfMaterialImage() + "');";
                    return (!smt.execute(sql));
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean deleteTypeOfMaterial(Clavis.TypeOfMaterial tipo) {
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                int valor = tipo.getMaterialTypeID();
                if (valor < 0) {
                    String sql = "select id_tipo from TypesOfMaterial where descricao like '" + tipo.getTypeOfMaterialName() + "';";
                    try {
                        rs = smt.executeQuery(sql);
                        if (rs.next()) {
                            valor = rs.getInt("id_tipo");
                        } else {
                            valor = -1;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
                if (valor > -1) {
                    try {
                        String sql = "delete from TypesOfMaterial where id_tipo = " + tipo.getMaterialTypeID() + ";";
                        return (!smt.execute(sql));
                    } catch (SQLException ex) {
                        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public java.util.List<Clavis.TypeOfMaterial> getTypesOfMaterial() {
        java.util.List<Clavis.TypeOfMaterial> tipos = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from TypesOfMaterial;";
                Clavis.TypeOfMaterial tipo;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        tipo = new Clavis.TypeOfMaterial(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
                        tipos.add(tipo);
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return tipos;
    }

    public Clavis.TypeOfMaterial getTypeOfMaterial(int id) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select id_tipo, descricao, total, livres, imagem from TypesOfMaterial where id_tipo = " + id + ";";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        return new Clavis.TypeOfMaterial(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    public int getNumberOfFreeMaterials(Clavis.TypeOfMaterial mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select livres from TypesOfMaterial where id_tipo = " + mat.getMaterialTypeID() + ";";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        mat.setFree(rs.getInt("livres"));
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return mat.getFree();
    }

    public int getTotalOfMaterials(Clavis.TypeOfMaterial mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select total from TypesOfMaterial where id_tipo = " + mat.getMaterialTypeID() + ";";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        mat.setTotal(rs.getInt("total"));
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
        return mat.getTotal();
    }

    public boolean insertFeature(Clavis.Feature feature) {
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select count(*) from Features where descricao like '" + feature.getDescription() + "' and quantidade = " + feature.getNumber() + " and medida like '" + feature.getUnityMeasure() + "' ;";
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        if (rs.getInt(1) == 0) {
                            sql = "insert into Features (descricao, quantidade, medida) values ('" + feature.getDescription() + "'," + feature.getNumber() + ",'" + feature.getUnityMeasure() + "')";
                            return (!smt.execute(sql));
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean deleteFeature(Clavis.Feature feature) {
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select id_caracteristica from Features where descricao like '" + feature.getDescription() + "' and quantidade = " + feature.getNumber() + " and medida like '" + feature.getUnityMeasure() + "' ;";
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        int val = rs.getInt(1);
                        if (val > 0) {
                            sql = "delete from Features where id_caracteristica = " + val + ";";
                            return (!smt.execute(sql));
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean associateFeatureWithMaterial(Clavis.Feature feature, Clavis.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select id_caracteristica from Features where descricao like '" + feature.getDescription() + "' and quantidade = " + feature.getNumber() + " and medida like '" + feature.getUnityMeasure() + "' ;";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        int val = rs.getInt(1);
                        if (val > 0) {
                            sql = "select id_material from Materials where id_tipo = " + mat.getMaterialTypeID() + " and codigo like '" + mat.getCodeOfMaterial() + "' and descricao like '" + mat.getDescription() + "';";
                            ResultSet rs2 = smt.executeQuery(sql);
                            if (rs2.next()) {
                                int idmaterial = rs2.getInt("id_material");
                                sql = "select count(*) from Rel_features_materials where id_caracteristica = " + val + " and id_material = " + idmaterial + ";";
                                ResultSet rs3 = smt.executeQuery(sql);
                                if (rs3.next()) {
                                    if (rs3.getInt(1) == 0) {
                                        sql = "insert into Rel_features_materials (id_caracteristica, id_material)  values (" + val + "," + idmaterial + ")";
                                        return (!smt.execute(sql));
                                    }
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public java.util.List<Clavis.Material> getMaterialsWithSpecificFeature(Clavis.Feature feature) {
        java.util.List<Clavis.Material> mats = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select id_caracteristica from Features where descricao like '" + feature.getDescription() + "' and quantidade = " + feature.getNumber() + " and medida like '" + feature.getUnityMeasure() + "' ;";
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        sql = "select id_material from Rel_features_materials where id_caracteristica = " + rs.getInt("id_caracteristica") + ";";
                        ResultSet rs2 = smt.executeQuery(sql);
                        java.util.ArrayList<Integer> materiais = new java.util.ArrayList<>();
                        while (rs2.next()) {
                            materiais.add(rs2.getInt("id_material"));
                        }
                        if (materiais.size() > 0) {
                            for (int i : materiais) {
                                mats.add(this.getMaterial(i));
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return mats;
    }

    public java.util.List<Clavis.Feature> getFeaturesByTypeOfMaterial() {
        java.util.List<Clavis.Feature> lista = new java.util.ArrayList<>();
        return lista;
    }

    public boolean deleteAssociationFeatureWithMaterial(Clavis.Feature feature, Clavis.Material mat) {
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            ResultSet rs2;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select id_caracteristica from Features where descricao like '" + feature.getDescription() + "' and quantidade = " + feature.getNumber() + " and medida like '" + feature.getUnityMeasure() + "' ;";
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        int val = rs.getInt(1);
                        if (val > 0) {
                            sql = "select id_material from Materials where id_tipo = " + mat.getMaterialTypeID() + " and codigo like '" + mat.getCodeOfMaterial() + "' and descricao like '" + mat.getDescription() + "';";
                            rs2 = smt.executeQuery(sql);
                            if (rs2.next()) {
                                int idmaterial = rs2.getInt("id_material");
                                sql = "delete from Rel_features_materials where id_caracteristica = " + val + " and id_material = " + idmaterial + ";";
                                return (!smt.execute(sql));
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean updateAllRequests(java.util.Set<Clavis.Request> requests, TimeDate.Date dat1, TimeDate.Date dat2) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "delete from Requests where origem like 'csv' and data_inicio >=STR_TO_DATE('" + dat1.toString() + "','%d/%m/%Y') and data_fim <= STR_TO_DATE('" + dat2.toString() + "','%d/%m/%Y') and ativo = 0 and terminado = 0";
                try {
                    smt.execute(sql);
                    ResultSet rs;
                    ResultSet rs2;
                    ResultSet rs3;
                    ResultSet rs4;
                    int id_material;
                    int id_pessoa;
                    int id_disciplina;
                    for (Clavis.Request request : requests) {
                        sql = "select id_material from Materials where codigo like '" + request.getMaterial().getCodeOfMaterial() + "' and descricao like '" + request.getMaterial().getDescription() + "';";
                        rs = smt.executeQuery(sql);
                        if (rs.next()) {
                            id_material = rs.getInt("id_material");
                            sql = "select id_pessoa from Persons where identificacao like '" + request.getPerson().getIdentification() + "' and nome like '" + request.getPerson().getName() + "';";
                            rs2 = smt.executeQuery(sql);
                            if (rs2.next()) {
                                id_pessoa = rs2.getInt("id_pessoa");
                                sql = "select id_disciplina from Subjects where codigo like '" + request.getSubject().getCode() + "' and descricao like '" + request.getSubject().getName() + "'";
                                rs3 = smt.executeQuery(sql);
                                if (rs3.next()) {
                                    id_disciplina = rs3.getInt("id_disciplina");
                                    sql = "select count(*) from Requests where id_material = " + id_material + " "
                                            + "and id_pessoa = " + id_pessoa + " and id_disciplina = " + id_disciplina + " and "
                                            + "data_inicio = STR_TO_DATE('" + request.getBeginDate().toString() + "','%d/%m/%Y') and "
                                            + "data_fim = STR_TO_DATE('" + request.getEndDate().toString() + "','%d/%m/%Y') and "
                                            + "quantidade = " + request.getQuantity() + " and "
                                            + "hora_inicio ='" + request.getTimeBegin().toString() + "' and "
                                            + "hora_fim = '" + request.getTimeEnd().toString() + "' and "
                                            + "dia_semana = " + request.getWeekDay().getDayNumber() + " and "
                                            + "atividade = '" + request.getActivity() + "' and "
                                            + "codigo_turma = '"+request.getStudentsClass().getCode()+"' and "
                                            + "origem = '" + request.getSource() + "';";
                                    rs4 = smt.executeQuery(sql);
                                    if ((rs4.next()) && (rs4.getInt(1) == 0)) {
                                        sql = "insert into Requests (id_material,id_pessoa,id_disciplina,data_inicio,data_fim,hora_inicio,hora_fim,dia_semana,quantidade,origem,ativo,terminado,substituido,atividade,codigo_turma) "
                                                + "values (" + id_material + "," + id_pessoa + "," + id_disciplina + ",STR_TO_DATE('" + request.getBeginDate().toString() + "','%d/%m/%Y'), STR_TO_DATE('" + request.getEndDate().toString() + "','%d/%m/%Y'), '"
                                                + request.getTimeBegin().toString() + "','" + request.getTimeEnd().toString() + "',"
                                                + request.getWeekDay().getDayNumber() + ",1,'csv',false,false,0,'" + request.getActivity() + "','"+request.getStudentsClass().getCode()+"');";
                                        Threads.InsertRequest rq = new Threads.InsertRequest(sql, con);
                                        rq.start();
                                        try {
                                            rq.join();
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean updateRequests(java.util.Set<Clavis.Request> requests) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "delete from Requests where origem like 'csv'";
                try {
                    smt.execute(sql);
                    ResultSet rs;
                    ResultSet rs2;
                    ResultSet rs3;
                    int id_material;
                    int id_pessoa;
                    int id_disciplina;
                    for (Clavis.Request request : requests) {
                        sql = "select id_material from Materials where codigo like '" + request.getMaterial().getCodeOfMaterial() + "' and descricao like '" + request.getMaterial().getDescription() + "';";
                        rs = smt.executeQuery(sql);
                        if (rs.next()) {
                            id_material = rs.getInt("id_material");
                            sql = "select id_pessoa from Persons where identificacao like '" + request.getPerson().getIdentification() + "' and nome like '" + request.getPerson().getName() + "';";
                            rs2 = smt.executeQuery(sql);
                            if (rs2.next()) {
                                id_pessoa = rs2.getInt("id_pessoa");
                                sql = "select id_disciplina from Subjects where codigo like '" + request.getSubject().getCode() + "' and descricao like '" + request.getSubject().getName() + "'";
                                rs3 = smt.executeQuery(sql);
                                if (rs3.next()) {
                                    id_disciplina = rs3.getInt("id_disciplina");
                                    sql = "insert into Requests (id_material,id_pessoa,id_disciplina,data_inicio,data_fim,hora_inicio,hora_fim,dia_semana,quantidade,origem,ativo,terminado,substituido) "
                                            + "values (" + id_material + "," + id_pessoa + "," + id_disciplina + ",STR_TO_DATE('" + request.getBeginDate().toString() + "','%d/%m/%Y'), STR_TO_DATE('" + request.getEndDate().toString() + "','%d/%m/%Y'), '"
                                            + request.getTimeBegin().toString() + "','" + request.getTimeEnd().toString() + "',"
                                            + request.getWeekDay().getDayNumber() + ",1,'csv',false,false,0);";
                                    Threads.InsertRequest rq = new Threads.InsertRequest(sql, con);
                                    rq.start();
                                    try {
                                        rq.join();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public java.util.Set<Clavis.Request> getAllRequests() {
        java.util.Set<Clavis.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao,id_material,id_pessoa,id_disciplina,DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio,"
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim,"
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma,"
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega from Requests;";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Clavis.Request request;
                String[] aux;
                Clavis.Person pessoa;
                Clavis.Material material;
                Clavis.Subject disciplina;
                Clavis.ClassStudents turma;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                String origem;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    turma = this.getStudentsClass(rs.getString("codigo_turma"));
                    int discip = rs.getInt("id_disciplina");
                    if (discip != 0) {
                        disciplina = this.getSubject(discip);
                    } else {
                        disciplina = new Clavis.Subject();
                    }
                    aux = rs.getString("inicio").split("/");
                    inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("fim").split("/");
                    fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tinicio").split(":");
                    tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tfim").split(":");
                    tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                    origem = rs.getString("origem");
                    if (rs.getString("data_levantamento") != null) {
                        aux = rs.getString("data_levantamento").split("/");
                        dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_levantamento").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dlevantamento = null;
                        tlevantamento = null;
                    }
                    if (rs.getString("data_entrega") != null) {
                        aux = rs.getString("data_entrega").split("/");
                        dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_entrega").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dentrega = null;
                        tentrega = null;
                    }
                    request = new Clavis.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"), turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public java.util.Set<Clavis.Request> getRequests(TimeDate.Date dinicio, TimeDate.Date dfim) {
        java.util.Set<Clavis.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao,id_material,id_pessoa,id_disciplina,DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio,"
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim,"
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega from Requests"
                    + "where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y')"
                    + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y');";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Clavis.Request request;
                String[] aux;
                Clavis.Person pessoa;
                Clavis.Material material;
                Clavis.Subject disciplina;
                Clavis.ClassStudents turma;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                String origem;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    turma = this.getStudentsClass(rs.getString("codigo_turma"));
                    int discip = rs.getInt("id_disciplina");
                    if (discip != 0) {
                        disciplina = this.getSubject(discip);
                    } else {
                        disciplina = new Clavis.Subject();
                    }
                    aux = rs.getString("inicio").split("/");
                    inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("fim").split("/");
                    fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tinicio").split(":");
                    tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tfim").split(":");
                    tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                    origem = rs.getString("origem");
                    if (rs.getString("data_levantamento") != null) {
                        aux = rs.getString("data_levantamento").split("/");
                        dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_levantamento").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dlevantamento = null;
                        tlevantamento = null;
                    }
                    if (rs.getString("data_entrega") != null) {
                        aux = rs.getString("data_entrega").split("/");
                        dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_entrega").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dentrega = null;
                        tentrega = null;
                    }
                    request = new Clavis.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"), turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public java.util.Set<Clavis.Request> getRequests(Clavis.Material mat, TimeDate.Date dinicio, TimeDate.Date dfim) {
        java.util.Set<Clavis.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            System.out.println(mat.getId());
            String sql = "select id_requisicao,id_material,id_pessoa,id_disciplina,DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega from Requests "
                    + "where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                    + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                    + "and id_material = "+mat.getId()+";";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Clavis.Request request;
                String[] aux;
                Clavis.Person pessoa;
                Clavis.Material material;
                Clavis.Subject disciplina;
                Clavis.ClassStudents turma;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                String origem;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    turma = this.getStudentsClass(rs.getString("codigo_turma"));
                    int discip = rs.getInt("id_disciplina");
                    if (discip != 0) {
                        disciplina = this.getSubject(discip);
                    } else {
                        disciplina = new Clavis.Subject();
                    }
                    aux = rs.getString("inicio").split("/");
                    inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("fim").split("/");
                    fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tinicio").split(":");
                    tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tfim").split(":");
                    tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                    origem = rs.getString("origem");
                    if (rs.getString("data_levantamento") != null) {
                        aux = rs.getString("data_levantamento").split("/");
                        dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_levantamento").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dlevantamento = null;
                        tlevantamento = null;
                    }
                    if (rs.getString("data_entrega") != null) {
                        aux = rs.getString("data_entrega").split("/");
                        dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_entrega").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dentrega = null;
                        tentrega = null;
                    }
                    request = new Clavis.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"),turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }
    
     public Clavis.Request getNextRequest(Clavis.Material mat) {
        Clavis.Request request = new Clavis.Request();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao,id_material,id_pessoa,id_disciplina,DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega from Requests "
                    + " where id_material = "+mat.getId()+" and data_inicio > curdate() or (data_inicio = curdate() and hora_inicio >= curtime()) and ativo = 0 and terminado = 0  order by data_inicio, hora_inicio limit 1;";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                String[] aux;
                Clavis.Person pessoa;
                Clavis.Material material;
                Clavis.Subject disciplina;
                Clavis.ClassStudents turma;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                String origem;
                int ido;
                if (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    turma = this.getStudentsClass(rs.getString("codigo_turma"));
                    int discip = rs.getInt("id_disciplina");
                    if (discip != 0) {
                        disciplina = this.getSubject(discip);
                    } else {
                        disciplina = new Clavis.Subject();
                    }
                    aux = rs.getString("inicio").split("/");
                    inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("fim").split("/");
                    fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tinicio").split(":");
                    tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tfim").split(":");
                    tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                    origem = rs.getString("origem");
                    if (rs.getString("data_levantamento") != null) {
                        aux = rs.getString("data_levantamento").split("/");
                        dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_levantamento").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dlevantamento = null;
                        tlevantamento = null;
                    }
                    if (rs.getString("data_entrega") != null) {
                        aux = rs.getString("data_entrega").split("/");
                        dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_entrega").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dentrega = null;
                        tentrega = null;
                    }
                    request = new Clavis.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"),turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return request;
    }
     
     public Clavis.Request getCurrentRequest(Clavis.Material mat) {
        Clavis.Request request = new Clavis.Request();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao,id_material,id_pessoa,id_disciplina,DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega from Requests "
                    + " where id_material = "+mat.getId()+" and ativo = 1 and terminado = 0  order by data_inicio, hora_inicio limit 1;";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                String[] aux;
                Clavis.Person pessoa;
                Clavis.Material material;
                Clavis.Subject disciplina;
                Clavis.ClassStudents turma;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                String origem;
                int ido;
                if (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    turma = this.getStudentsClass(rs.getString("codigo_turma"));
                    int discip = rs.getInt("id_disciplina");
                    if (discip != 0) {
                        disciplina = this.getSubject(discip);
                    } else {
                        disciplina = new Clavis.Subject();
                    }
                    aux = rs.getString("inicio").split("/");
                    inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("fim").split("/");
                    fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tinicio").split(":");
                    tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tfim").split(":");
                    tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                    origem = rs.getString("origem");
                    if (rs.getString("data_levantamento") != null) {
                        aux = rs.getString("data_levantamento").split("/");
                        dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_levantamento").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dlevantamento = null;
                        tlevantamento = null;
                    }
                    if (rs.getString("data_entrega") != null) {
                        aux = rs.getString("data_entrega").split("/");
                        dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_entrega").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dentrega = null;
                        tentrega = null;
                    }
                    request = new Clavis.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"),turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return request;
    }

    public java.util.Set<Clavis.Request> getRequests(Clavis.Person pess, TimeDate.Date dinicio, TimeDate.Date dfim) {
        java.util.Set<Clavis.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            int id = -1;
            boolean cond = false;
            try {
                if (pess.getId() == -1) {
                    String sql = "select id_pessoa from Persons where identificacao like ?;";
                    smt = con.prepareStatement(sql);
                    smt.setString(1, pess.getIdentification());
                    ResultSet rsaux = smt.executeQuery();
                    if (rsaux.next()) {
                        id = rsaux.getInt("id_pessoa");
                        cond = true;
                    }
                } else {
                    id = pess.getId();
                    cond = true;
                }
                if (cond) {
                    String sql = "select id_requisicao,id_material,id_pessoa,id_disciplina,DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                            + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                            + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio,"
                            + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim,"
                            + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                            + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                            + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                            + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                            + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega from Requests"
                            + "where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y')"
                            + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                            + "and id_pessoa = " + id + ";";
                    smt = con.prepareStatement(sql);
                    rs = smt.executeQuery();
                    Clavis.Request request = null;
                    String[] aux;
                    Clavis.Person pessoa;
                    Clavis.Material material;
                    Clavis.Subject disciplina;
                    Clavis.ClassStudents turma;
                    TimeDate.Date inicio;
                    TimeDate.Date fim;
                    TimeDate.Time tinicio;
                    TimeDate.Time tfim;
                    TimeDate.WeekDay dia;
                    TimeDate.Date dlevantamento;
                    TimeDate.Date dentrega;
                    TimeDate.Time tlevantamento;
                    TimeDate.Time tentrega;
                    String origem;
                    int ido;
                    while (rs.next()) {
                        ido = rs.getInt("id_requisicao");
                        pessoa = this.getPerson(rs.getInt("id_pessoa"));
                        material = this.getMaterial(rs.getInt("id_material"));
                        turma = this.getStudentsClass(rs.getString("codigo_turma"));
                        int discip = rs.getInt("id_disciplina");
                        if (discip != 0) {
                            disciplina = this.getSubject(discip);
                        } else {
                            disciplina = new Clavis.Subject();
                        }
                        aux = rs.getString("inicio").split("/");
                        inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("fim").split("/");
                        fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("tinicio").split(":");
                        tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("tfim").split(":");
                        tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                        origem = rs.getString("origem");
                        if (rs.getString("data_levantamento") != null) {
                            aux = rs.getString("data_levantamento").split("/");
                            dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                            aux = rs.getString("hora_levantamento").split(":");
                            if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                                aux[0] = aux[0].replaceFirst("0", "");
                            }
                            if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                                aux[1] = aux[1].replaceFirst("0", "");
                            }
                            if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                                aux[2] = aux[2].replaceFirst("0", "");
                            }
                            tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        } else {
                            dlevantamento = null;
                            tlevantamento = null;
                        }
                        if (rs.getString("data_entrega") != null) {
                            aux = rs.getString("data_entrega").split("/");
                            dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                            aux = rs.getString("hora_entrega").split(":");
                            if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                                aux[0] = aux[0].replaceFirst("0", "");
                            }
                            if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                                aux[1] = aux[1].replaceFirst("0", "");
                            }
                            if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                                aux[2] = aux[2].replaceFirst("0", "");
                            }
                            tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        } else {
                            dentrega = null;
                            tentrega = null;
                        }
                        request = new Clavis.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"), turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                        requisicoes.add(request);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return requisicoes;
    }

    public java.util.Set<Clavis.Request> getRequests(int tipo, String nome, TimeDate.Date dinicio, TimeDate.Date dfim, boolean estado, boolean terminado) {
        java.util.Set<Clavis.Request> requisicoes = new java.util.TreeSet<>();

        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            int id;
            try {
                String sql = "";
                switch (tipo) {
                    case 1:
                        sql = "select id_material from Materials where upper(descricao) like upper('" + nome + "%');";
                        break;
                    default:
                        sql = "select id_pessoa from Persons where upper(nome) like upper('%" + nome + "%');";
                        break;
                }
                if (!sql.equals("")) {
                    smt = con.prepareStatement(sql);
                    ResultSet rsaux = smt.executeQuery();
                    while (rsaux.next()) {
                        sql = "";
                        if (tipo == 0) {
                            id = rsaux.getInt("id_pessoa");
                            sql = "select id_requisicao, id_material, id_pessoa, id_disciplina, "
                                    + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                                    + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega "
                                    + "from Requests "
                                    + "where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                                    + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                                    + "and id_pessoa = " + id + " and ativo = " + estado + " and terminado = " + terminado + ";";
                        } else if (tipo == 1) {
                            id = rsaux.getInt("id_material");
                            sql = "select id_requisicao, id_material, id_pessoa, id_disciplina, "
                                    + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                                    + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega "
                                    + "from Requests "
                                    + "where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                                    + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                                    + "and id_material = " + id + " and ativo = " + estado + " and terminado = " + terminado + ";";
                        }
                        if (!sql.equals("")) {
                            smt = con.prepareStatement(sql);
                            rs = smt.executeQuery();
                            Clavis.Request request;
                            String[] aux;
                            Clavis.Person pessoa;
                            Clavis.Material material;
                            Clavis.Subject disciplina;
                            Clavis.ClassStudents turma;
                            TimeDate.Date inicio;
                            TimeDate.Date fim;
                            TimeDate.Time tinicio;
                            TimeDate.Time tfim;
                            TimeDate.WeekDay dia;
                            TimeDate.Date dlevantamento;
                            TimeDate.Date dentrega;
                            TimeDate.Time tlevantamento;
                            TimeDate.Time tentrega;
                            String origem;
                            int ido;
                            while (rs.next()) {
                                ido = rs.getInt("id_requisicao");
                                pessoa = this.getPerson(rs.getInt("id_pessoa"));
                                material = this.getMaterial(rs.getInt("id_material"));
                                turma = this.getStudentsClass(rs.getString("codigo_turma"));
                                int discip = rs.getInt("id_disciplina");
                                if (discip != 0) {
                                    disciplina = this.getSubject(discip);
                                } else {
                                    disciplina = new Clavis.Subject();
                                }
                                aux = rs.getString("inicio").split("/");
                                inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                                aux = rs.getString("fim").split("/");
                                fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                                aux = rs.getString("tinicio").split(":");
                                tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                                aux = rs.getString("tfim").split(":");
                                tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                                dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                                origem = rs.getString("origem");
                                if (rs.getString("data_levantamento") != null) {
                                    aux = rs.getString("data_levantamento").split("/");
                                    dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                                    aux = rs.getString("hora_levantamento").split(":");
                                    if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                                        aux[0] = aux[0].replaceFirst("0", "");
                                    }
                                    if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                                        aux[1] = aux[1].replaceFirst("0", "");
                                    }
                                    if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                                        aux[2] = aux[2].replaceFirst("0", "");
                                    }
                                    tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                                } else {
                                    dlevantamento = null;
                                    tlevantamento = null;
                                }
                                if (rs.getString("data_entrega") != null) {
                                    aux = rs.getString("data_entrega").split("/");
                                    dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                                    aux = rs.getString("hora_entrega").split(":");
                                    if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                                        aux[0] = aux[0].replaceFirst("0", "");
                                    }
                                    if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                                        aux[1] = aux[1].replaceFirst("0", "");
                                    }
                                    if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                                        aux[2] = aux[2].replaceFirst("0", "");
                                    }
                                    tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                                } else {
                                    dentrega = null;
                                    tentrega = null;
                                }
                                request = new Clavis.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"), turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                                requisicoes.add(request);
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return requisicoes;
    }

    public java.util.Set<Clavis.Request> getRequestsByTime(Boolean bool, TimeDate.Time time, TimeDate.Date dinicio, TimeDate.Date dfim, boolean estado, boolean terminado) {
        java.util.Set<Clavis.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            try {
                String sql;
                if (!bool) {
                    sql = "select id_requisicao, id_material, id_pessoa, id_disciplina, "
                            + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                            + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                            + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                            + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                            + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                            + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                            + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                            + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                            + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega "
                            + "from Requests "
                            + "where TIME_FORMAT(hora_inicio,'%H:%i:%s') = '" + time.toString() + "' "
                            + "and data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                            + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                            + "and ativo = " + estado + " and terminado = " + terminado + ";";
                } else {
                    sql = "select id_requisicao, id_material, id_pessoa, id_disciplina, "
                            + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                            + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                            + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                            + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                            + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                            + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                            + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                            + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                            + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega "
                            + "from Requests "
                            + "where TIME_FORMAT(hora_fim,'%H:%i:%s') = '" + time.toString() + "' "
                            + "and data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                            + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                            + "and ativo = " + estado + " and terminado = " + terminado + ";";
                }
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Clavis.Request request;
                String[] aux;
                Clavis.Person pessoa;
                Clavis.Material material;
                Clavis.Subject disciplina;
                Clavis.ClassStudents turma;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                String origem;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    turma = this.getStudentsClass(rs.getString("codigo_turma"));
                    int discip = rs.getInt("id_disciplina");
                    if (discip != 0) {
                        disciplina = this.getSubject(discip);
                    } else {
                        disciplina = new Clavis.Subject();
                    }
                    aux = rs.getString("inicio").split("/");
                    inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("fim").split("/");
                    fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tinicio").split(":");
                    tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tfim").split(":");
                    tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                    origem = rs.getString("origem");
                    if (rs.getString("data_levantamento") != null) {
                        aux = rs.getString("data_levantamento").split("/");
                        dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_levantamento").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dlevantamento = null;
                        tlevantamento = null;
                    }
                    if (rs.getString("data_entrega") != null) {
                        aux = rs.getString("data_entrega").split("/");
                        dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_entrega").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dentrega = null;
                        tentrega = null;
                    }
                    request = new Clavis.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"), turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public java.util.Set<Clavis.Request> getRequests(Clavis.TypeOfMaterial mat, TimeDate.Date dinicio, TimeDate.Date dfim, boolean estado, boolean terminado) {
        java.util.Set<Clavis.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            int ido = mat.getMaterialTypeID();
            ResultSet rs;
            String sql = "select id_requisicao, id_material, id_pessoa, id_disciplina, "
                    + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                    + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, atividade, codigo_turma, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega "
                    + "from Requests right join (select id_material from Materials where id_tipo=" + ido + ") "
                    + "auxiliar using (id_material) where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                    + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') and ativo = " + estado + " and terminado = " + terminado + ";";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Clavis.Request request = null;
                String[] aux;
                Clavis.Person pessoa;
                Clavis.Material material;
                Clavis.Subject disciplina;
                Clavis.ClassStudents turma;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                String origem;
                int id;
                while (rs.next()) {
                    id = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    turma = this.getStudentsClass(rs.getString("codigo_turma"));
                    int discip = rs.getInt("id_disciplina");
                    if (discip != 0) {
                        disciplina = this.getSubject(discip);
                    } else {
                        disciplina = new Clavis.Subject();
                    }
                    aux = rs.getString("inicio").split("/");
                    inicio = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("fim").split("/");
                    fim = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tinicio").split(":");
                    tinicio = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    aux = rs.getString("tfim").split(":");
                    tfim = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    dia = new TimeDate.WeekDay(rs.getInt("dia_semana"));
                    origem = rs.getString("origem");
                    if (rs.getString("data_levantamento") != null) {
                        aux = rs.getString("data_levantamento").split("/");
                        dlevantamento = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_levantamento").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tlevantamento = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dlevantamento = null;
                        tlevantamento = null;
                    }
                    if (rs.getString("data_entrega") != null) {
                        aux = rs.getString("data_entrega").split("/");
                        dentrega = new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                        aux = rs.getString("hora_entrega").split(":");
                        if ((aux[0].length() > 1) && (aux[0].charAt(0) == '0')) {
                            aux[0] = aux[0].replaceFirst("0", "");
                        }
                        if ((aux[1].length() > 1) && (aux[1].charAt(0) == '0')) {
                            aux[1] = aux[1].replaceFirst("0", "");
                        }
                        if ((aux[2].length() > 1) && (aux[2].charAt(0) == '0')) {
                            aux[2] = aux[2].replaceFirst("0", "");
                        }
                        tentrega = new TimeDate.Time(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    } else {
                        dentrega = null;
                        tentrega = null;
                    }
                    request = new Clavis.Request(id, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplina, rs.getString("atividade"), turma, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega);
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public boolean changeRequestActiveState(Clavis.Request req) {
        if (this.isTie()) {
            PreparedStatement smt;
            PreparedStatement smt2;
            ResultSet rs;
            String sql = "update Requests set ativo = 1, data_levantamento = CURDATE(), hora_levantamento = CURTIME() where  id_requisicao = " + req.getId() + "; ";
            try {
                con.setAutoCommit(false);
                smt = con.prepareStatement(sql);
                smt.executeUpdate();
                sql = "update Materials set estado = 1 where codigo = '" + req.getMaterial().getCodeOfMaterial() + "';";
                smt2 = con.prepareStatement(sql);
                smt2.executeLargeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    public boolean changeRequestTerminateState(Clavis.Request req) {
        if (this.isTie()) {
            PreparedStatement smt;
            PreparedStatement smt2;

            String sql = "update Requests set terminado = 1, ativo = 0, data_entrega = CURDATE(), hora_entrega = CURTIME() where  id_requisicao = " + req.getId() + "; ";
            try {
                con.setAutoCommit(false);
                smt = con.prepareStatement(sql);
                smt.executeUpdate();
                sql = "update Materials set estado = 0 where codigo = '" + req.getMaterial().getCodeOfMaterial() + "';";
                smt2 = con.prepareStatement(sql);
                smt2.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    public Clavis.Subject getSubject(int id) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select descricao,codigo from Subjects where id_disciplina = " + id + ";";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        return new Clavis.Subject(id, rs.getString("descricao"), rs.getString("codigo"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public void close() {
        try {
            if (!con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
