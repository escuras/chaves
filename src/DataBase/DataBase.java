/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.io.IOException;
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

    private String url;
    private Connection con;
    private boolean tie;
    private Threads.InsertRequest tupdate;

    public static boolean testConnection(String urlb) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlb);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return false;
    }

    public DataBase(String url) {
        tie = testConnection(url);
        if (tie) {
            try {
                this.url = url;
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url);
                tie = true;
            } catch (ClassNotFoundException | SQLException e) {
                tie = false;
            }
        }
    }

    public DataBase(String url, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            tie = true;
        } catch (ClassNotFoundException | SQLException e) {
            tie = false;
        }
    }

    public boolean isTie() {
        return tie;
    }

    public java.util.List<Keys.Person> getPersons() {
        java.util.List<Keys.Person> pessoas = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Persons;";
                Keys.Person pessoa;
                ResultSet rs;
                ResultSet rs2;
                Statement aux;
                try {
                    rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        pessoa = new Keys.Person(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("identificacao"), rs.getString("telefone"), rs.getString("email"), rs.getInt("privilegio"));
                        sql = "select * from Functions where id_funcao ='" + rs.getInt("id_funcao") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Keys.Function funcao = new Keys.Function(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
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

    public Keys.Person getPerson(String identificacao) {
        Keys.Person pessoa = new Keys.Person();
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
                        pessoa = new Keys.Person(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("identificacao"), rs.getString("telefone"), rs.getString("email"), rs.getInt("privilegio"));
                        sql = "select * from Functions where id_funcao ='" + rs.getInt("id_funcao") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Keys.Function funcao = new Keys.Function(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
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

    public Keys.Person getPerson(int identificacao) {
        Keys.Person pessoa = new Keys.Person();
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
                        pessoa = new Keys.Person(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("identificacao"), rs.getString("telefone"), rs.getString("email"), rs.getInt("privilegio"));
                        sql = "select * from Functions where id_funcao ='" + rs.getInt("id_funcao") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Keys.Function funcao = new Keys.Function(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
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

    public Keys.Person getPerson(String nome, String identificacao) {
        Keys.Person pessoa = new Keys.Person();
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
                        pessoa = new Keys.Person(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("identificacao"), rs.getString("telefone"), rs.getString("email"), rs.getInt("privilegio"));
                        sql = "select * from Functions where id_funcao ='" + rs.getInt("id_funcao") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Keys.Function funcao = new Keys.Function(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
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

    public boolean insertPersons(java.util.Set<Keys.Person> pessoas) {
        if (this.isTie()) {
            Statement smt;
            Statement smt2;
            try {
                con.setAutoCommit(false);
                smt = con.createStatement();
                for (Keys.Person pessoa : pessoas) {
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

    public boolean insertPerson(Keys.Person pessoa) {
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

    public boolean updatePerson(Keys.Person pessoa) {
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

    public boolean alterPrivilegeInPerson(Keys.Person pessoa, int valor) {
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

    public boolean deletePerson(Keys.Person pessoa) {
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

    public boolean insertStudentsClass(Keys.ClassStudents turma) {
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
                    smt.executeUpdate(sql);
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

    public boolean insertStudentsClasses(java.util.Set<Keys.ClassStudents> turmas) {
        if (this.isTie()) {
            Statement smt;
            ResultSet rs;
            for (Keys.ClassStudents turma : turmas) {
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
                        smt.executeUpdate(sql);
                        con.commit();
                        con.setAutoCommit(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return true;
        }
        return false;
    }

    public java.util.List<Keys.ClassStudents> getStudentsClasses() {
        java.util.List<Keys.ClassStudents> turmas = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select (codigo,descricao,numero_alunos,codigo_curso,descricao_curso) from StudentsClasses";
                Keys.ClassStudents turma;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        turma = new Keys.ClassStudents(rs.getString("codigo"), rs.getString("descricao"), rs.getInt("numero_alunos"), rs.getString("codigo_curso"), rs.getString("descricao_curso"));
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

    public Keys.ClassStudents getStudentsClass(String codigo) {
        Keys.ClassStudents turma = new Keys.ClassStudents();
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
                        turma = new Keys.ClassStudents(rs.getString("codigo"), rs.getString("descricao"), rs.getInt("numero_alunos"), rs.getString("codigo_curso"), rs.getString("descricao_curso"));
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

    public java.util.List<Keys.Function> getFunctions() {
        java.util.List<Keys.Function> funcoes = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Functions;";
                Keys.Function funcao;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        funcao = new Keys.Function(rs.getInt(1), rs.getString(2), rs.getInt(3));
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

    public java.util.List<Keys.Function> getFunctionsWithprivilege(int privilegio) {
        java.util.List<Keys.Function> funcoes = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Functions where privilegio = " + privilegio + ";";
                Keys.Function funcao;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        funcao = new Keys.Function(rs.getInt(1), rs.getString(2), rs.getInt(3));
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

    public Keys.Function getFunction(String descricao) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from Functions where descricao like '" + descricao + "';";
                Keys.Function funcao;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        funcao = new Keys.Function(rs.getInt(1), rs.getString(2), rs.getInt(3));
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

    public boolean insertFunction(Keys.Function funcao) {
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

    public boolean deleteFunction(Keys.Function funcao) {
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

    public boolean alterPivilegeInFunction(Keys.Function funcao, int valor) {
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

    public java.util.List<Keys.Material> getMaterials() {
        java.util.List<Keys.Material> materiais = new java.util.ArrayList<>();
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
                Keys.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        sql = "select * from TypesOfMaterial where id_tipo ='" + rs.getInt("id_tipo") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Keys.TypeOfMaterial tipo;
                            if (!rs2.getString("Imagem").equals("sem")) {
                                tipo = new Keys.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"), rs2.getString("imagem"));
                            } else {
                                tipo = new Keys.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"));
                            }
                            if (!rs.getString("imagem").equals("sem")) {
                                material = new Keys.Material(rs.getInt("id_material"), tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getString("imagem"), rs.getBoolean("estado"));
                            } else {
                                material = new Keys.Material(rs.getInt("id_material"), tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
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

    public java.util.Set<Keys.Material> getMaterialsByType(int id, int estado) {
        java.util.Set<Keys.Material> materiais = new java.util.TreeSet<>();
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
                String sql; 
                switch (estado) {
                    case 0:
                        sql = "Select * from Materials where id_tipo='" + id + "' and estado = 0 order by descricao asc;";
                        break;
                    case 1:
                        sql = "Select * from Materials where id_tipo='" + id + "' and estado = 1 order by descricao asc;";
                        break;
                    default:
                        sql = "Select * from Materials where id_tipo='" + id + "' order by descricao asc;";
                        break;
                }
                Keys.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        sql = "select * from TypesOfMaterial where id_tipo ='" + id + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Keys.TypeOfMaterial tipo;
                            if (!rs2.getString("Imagem").equals("sem")) {
                                tipo = new Keys.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"), rs2.getString("imagem"));
                            } else {
                                tipo = new Keys.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"));
                            }
                            if (!rs.getString("imagem").equals("sem")) {
                                material = new Keys.Material(rs.getInt("id_material"), tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getString("imagem"), rs.getBoolean("estado"));
                            } else {
                                material = new Keys.Material(rs.getInt("id_material"), tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
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

    public Keys.Material getMaterial(String codigo) {
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
                        Keys.Material mat;
                        if (smt2 != null) {
                            int idtipo = rs.getInt("id_tipo");
                            sql = "select * from TypesOfMaterial where id_tipo = " + idtipo + ";";
                            rs2 = smt2.executeQuery(sql);
                            if (rs2.next()) {
                                Keys.TypeOfMaterial tp = new Keys.TypeOfMaterial();
                                tp.setMaterialTypeID(idtipo);
                                tp.setTotal(rs2.getInt("total"));
                                tp.setFree(rs2.getInt("livres"));
                                tp.setTypeOfMaterialName(rs2.getString("descricao"));
                                tp.setTypeOfMaterialImage(rs2.getString("imagem"));
                                mat = new Keys.Material(rs.getInt("id_material"), tp, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
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

    public Keys.Material getMaterial(int id_material) {
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
                        Keys.Material mat;
                        if (smt2 != null) {
                            int idtipo = rs.getInt("id_tipo");
                            sql = "select * from TypesOfMaterial where id_tipo = " + idtipo + ";";
                            rs2 = smt2.executeQuery(sql);
                            if (rs2.next()) {
                                Keys.TypeOfMaterial tp = new Keys.TypeOfMaterial();
                                tp.setMaterialTypeID(idtipo);
                                tp.setTotal(rs2.getInt("total"));
                                tp.setFree(rs2.getInt("livres"));
                                tp.setTypeOfMaterialName(rs2.getString("descricao"));
                                tp.setTypeOfMaterialImage(rs2.getString("imagem"));
                                mat = new Keys.Material(rs.getInt("id_material"), tp, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
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

    public int getStateOfMaterial(Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "select estado from Materials where id_material = " + mat.getId() + ";";
                ResultSet rs;
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        return rs.getInt("estado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }

    public java.util.Set<Keys.Material> getMaterialwithSameFeature(Keys.Feature feature) {
        java.util.Set<Keys.Material> materiais = new java.util.TreeSet<>();
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

    public java.util.Set<Keys.Classroom> getClassrooms(int tipopesquisa) {
        java.util.Set<Keys.Classroom> classrooms = new java.util.TreeSet<>();
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
                Keys.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        Keys.TypeOfMaterial tipo;
                        if (!rs.getString("Imagem").equals("sem")) {
                            tipo = new Keys.TypeOfMaterial(rs.getInt("id_tipo"), rs.getString("descricao"), rs.getInt("total"), rs.getInt("livres"), rs.getString("imagem"));
                        } else {
                            tipo = new Keys.TypeOfMaterial(rs.getInt("id_tipo"), rs.getString("descricao"), rs.getInt("total"), rs.getInt("livres"));
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
                                material = new Keys.Material(rs2.getInt("id_material"), tipo, rs2.getString("codigo"), rs2.getString("descricao"), rs2.getString("imagem"), rs2.getBoolean("estado"));
                                sql = "Select * from Classrooms where codigo_sala = '" + rs2.getString("codigo") + "'";
                                try {
                                    smt3 = con.createStatement();
                                } catch (SQLException ex) {
                                    smt3 = null;
                                }
                                if (smt3 != null) {
                                    ResultSet rs3 = smt3.executeQuery(sql);
                                    if (rs3.next()) {
                                        Keys.Classroom sala = new Keys.Classroom(material, rs3.getInt("ncomputadores"), rs3.getInt("lugares"), rs3.getBoolean("projetor"), rs3.getBoolean("quadro_interativo"));
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

    public Keys.Classroom getClassroom(Keys.Material m) {
        Keys.Classroom sala = new Keys.Classroom();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "select * from Classrooms where codigo_sala = '" + m.getCodeOfMaterial() + "'";
                Keys.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        sala = new Keys.Classroom(m, rs.getInt("ncomputadores"), rs.getInt("lugares"), rs.getBoolean("projetor"), rs.getBoolean("quadro_interativo"));
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

    public int updateClassroom(Keys.Classroom clas) {
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
                sql = "update Classrooms set quadro_interativo = " + clas.hasInteractiveTable() + ", ncomputadores = " + clas.getComputers() + ", lugares = " + clas.getPlaces() + ", projetor = " + clas.hasProjector() + " where codigo_sala = '" + clas.getCodeOfMaterial() + "';";
                try {
                    smt.execute(sql);
                    return 0;
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                    return -2;
                }
            }
        }
        return -1;
    }

    public int updateMaterial(Keys.Material mat, boolean image) {
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
                if (image) {
                    sql = "update Materials set codigo = '" + mat.getCodeOfMaterial() + "', descricao = '" + mat.getDescription() + "', imagem = '" + mat.getMaterialImage() + "' where id_material = " + mat.getId() + ";";
                } else {
                    sql = "update Materials set codigo = '" + mat.getCodeOfMaterial() + "', descricao = '" + mat.getDescription() + "' where id_material = " + mat.getId() + ";";
                }
                try {
                    smt.execute(sql);
                    return 0;
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                    return -2;
                }
               
            }
        }
        return -1;
    }

    public boolean insertMaterials(java.util.Set<Keys.Material> materiais) {
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
                for (Keys.Material material : materiais) {
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

    public boolean insertMaterial(Keys.Material material) {
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

    public boolean insertSubject(Keys.Subject sub) {
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

    public boolean insertSubjects(java.util.Set<Keys.Subject> subs) {
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
                for (Keys.Subject sub : subs) {
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

    public boolean deleteSubject(Keys.Subject sub) {
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

    public java.util.List<Keys.Subject> getSubjectsByMaterial(Keys.Material mat) {
        java.util.List<Keys.Subject> disciplinas = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "select id_disciplina, descricao, codigo from Subjects where id_disciplina in (select id_disciplina from Rel_classrooms_subjects where codigo_sala = '" + mat.getCodeOfMaterial() + "');";
                    ResultSet rs = smt.executeQuery(sql);
                    Keys.Subject disciplina;
                    while (rs.next()) {
                        disciplina = new Keys.Subject(rs.getInt("id_disciplina"), rs.getString("descricao"), rs.getString("codigo"));
                        disciplinas.add(disciplina);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return disciplinas;
    }

    public java.util.List<Keys.Subject> getAllSubjects() {
        java.util.List<Keys.Subject> disciplinas = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "select id_disciplina, descricao, codigo from Subjects;";
                    ResultSet rs = smt.executeQuery(sql);
                    Keys.Subject disciplina;
                    while (rs.next()) {
                        disciplina = new Keys.Subject(rs.getInt("id_disciplina"), rs.getString("descricao"), rs.getString("codigo"));
                        disciplinas.add(disciplina);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return disciplinas;
    }

    public boolean associateSubjectWithClassroom(Keys.Subject sub, Keys.Classroom clas) {
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

    public boolean deleteAssociationBetweenSubjectAndClassroom(Keys.Subject sub, Keys.Material clas) {
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

    public boolean insertTypeOfMaterial(Keys.TypeOfMaterial tipo) {
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

    public boolean deleteTypeOfMaterial(Keys.TypeOfMaterial tipo) {
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

    public java.util.List<Keys.TypeOfMaterial> getTypesOfMaterial() {
        java.util.List<Keys.TypeOfMaterial> tipos = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                smt = null;
            }
            if (smt != null) {
                String sql = "Select * from TypesOfMaterial;";
                Keys.TypeOfMaterial tipo;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        tipo = new Keys.TypeOfMaterial(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
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

    public Keys.TypeOfMaterial getTypeOfMaterial(int id) {
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
                        return new Keys.TypeOfMaterial(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
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

    public int getNumberOfFreeMaterials(Keys.TypeOfMaterial mat) {
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

    public int getTotalOfMaterials(Keys.TypeOfMaterial mat) {
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

    public boolean insertFeature(Keys.Feature feature) {
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
                String sql = "select count(*) from Features where descricao like '" + feature.getDescription() + "';";
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        if (rs.getInt(1) == 0) {
                            con.setAutoCommit(false);
                            sql = "insert into Features (descricao, medida) values ('" + feature.getDescription() + "','" + feature.getUnityMeasure() + "')";
                            smt.executeUpdate(sql);
                            Keys.TypeOfMaterial tipo = feature.getTypeOfMaterial();
                            sql = "insert into Rel_features_materials (id_caracteristica, id_tipo) values (" + this.getFeatureId(feature) + "," + tipo.getMaterialTypeID() + ") ";
                            smt.executeUpdate(sql);

                            con.commit();
                            con.setAutoCommit(true);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean insertFeatureAndAssociateWithMaterial(Keys.Feature feature, Keys.Material mat) {
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
                String sql = "select count(*) from Features where descricao like '" + feature.getDescription() + ";";
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        if (rs.getInt(1) == 0) {
                            con.setAutoCommit(false);
                            sql = "insert into Features (descricao, medida) values ('" + feature.getDescription() + "','" + feature.getUnityMeasure() + "')";
                            smt.executeUpdate(sql);
                            sql = "insert into Rel_features_materials (id_caracteristica, id_tipo, id_material, quantidade) values (" + this.getFeatureId(feature) + "," + mat.getTypeOfMaterial().getMaterialTypeID() + "," + mat.getId() + "," + feature.getNumber() + ")";
                            smt.executeUpdate(sql);
                            con.commit();
                            con.setAutoCommit(true);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public int getFeatureId(Keys.Feature feature) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select id_caracteristica from Features where descricao = '" + feature.getDescription() + "' and medida = '" + feature.getUnityMeasure() + "' ;";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        return rs.getInt("id_caracteristica");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }

    public int updateFeatureWithoutAssociation(Keys.Feature velho, Keys.Feature novo) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                int id = this.getFeatureId(velho);
                int compara = velho.compareTo(novo);
                String sql = "select count(*) from Features where descricao = '" + novo.getDescription() + "' and medida = '" + novo.getUnityMeasure() + "';";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        if ((rs.getInt(1) == 0) || (compara == 0)) {
                            sql = "update Features set descricao = '" + novo.getDescription() + "', medida = '" + novo.getUnityMeasure() + "' where id_caracteristica = " + id + ";";
                            con.setAutoCommit(false);
                            smt.executeUpdate(sql);
                            con.commit();
                            con.setAutoCommit(true);
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }

    public int updateFeatureWithAssociation(Keys.Feature velho, Keys.Feature novo, Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                int id = this.getFeatureId(velho);
                int compara = velho.compareTo(novo);
                String sql = "select count(*) from Features where descricao = '" + novo.getDescription() + "' and medida = '" + novo.getUnityMeasure() + "';";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        if ((rs.getInt(1) == 0) || (compara == 0)) {
                            sql = "update Features set descricao = '" + novo.getDescription() + "', medida = '" + novo.getUnityMeasure() + "' where id_caracteristica = " + id + ";";
                            con.setAutoCommit(false);
                            smt.executeUpdate(sql);
                            sql = "update Rel_features_materials set quantidade = " + novo.getNumber() + " where id_caracteristica = " + id + " and id_material = " + mat.getId() + ";";
                            smt.executeUpdate(sql);
                            con.commit();
                            con.setAutoCommit(true);
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }

    public boolean deleteFeature(Keys.Feature feature) {
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
                String sql = "select id_caracteristica from Features where descricao like '" + feature.getDescription() + "' and medida like '" + feature.getUnityMeasure() + "' ;";
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

    public boolean associateFeatureWithMaterial(Keys.Feature feature, Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select id_caracteristica from Features where descricao like '" + feature.getDescription() + "' and medida like '" + feature.getUnityMeasure() + "' ;";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        int val = rs.getInt(1);
                        if (val > 0) {
                            sql = "select id_material, id_tipo from Materials where id_tipo = " + mat.getMaterialTypeID() + " and codigo like '" + mat.getCodeOfMaterial() + "' and descricao like '" + mat.getDescription() + "';";
                            ResultSet rs2 = smt.executeQuery(sql);
                            if (rs2.next()) {
                                int idmaterial = rs2.getInt("id_material");
                                int idtipo = rs2.getInt("id_tipo");
                                sql = "select count(*) from Rel_features_materials where id_caracteristica = " + val + " and id_material = " + idmaterial + ";";
                                ResultSet rs3 = smt.executeQuery(sql);
                                if (rs3.next()) {
                                    if (rs3.getInt(1) == 0) {
                                        sql = "insert into Rel_features_materials (id_caracteristica, id_material, id_tipo, quantidade)  values (" + val + "," + idmaterial + "," + idtipo + "," + feature.getNumber() + ")";
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

    public java.util.List<Keys.Material> getMaterialsWithSpecificFeature(Keys.Feature feature) {
        java.util.List<Keys.Material> mats = new java.util.ArrayList<>();
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
                String sql = "select id_caracteristica from Features where descricao like '" + feature.getDescription() + "' and medida like '" + feature.getUnityMeasure() + "' ;";
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

    public java.util.List<Keys.Feature> getFeaturesByTypeOfMaterial(Keys.TypeOfMaterial tipo) {
        java.util.List<Keys.Feature> lista = new java.util.ArrayList<>();
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
                String sql = "select id_caracteristica, descricao, medida from Features where id_caracteristica in (select distinct(id_caracteristica) from Rel_features_materials where id_tipo = " + tipo.getMaterialTypeID() + ") order by descricao;";
                try {
                    rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        Keys.Feature feature = new Keys.Feature(rs.getString("descricao"), rs.getString("medida"), 0, tipo);
                        lista.add(feature);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }

    public java.util.List<Keys.Feature> getFeaturesByMaterial(Keys.Material mat) {
        java.util.List<Keys.Feature> lista = new java.util.ArrayList<>();
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
                String sql = "select f.id_caracteristica, f.descricao, f.medida, t.quantidade from Features f left join Rel_features_materials t on (f.id_caracteristica = t.id_caracteristica ) where t.id_caracteristica in (select distinct(id_caracteristica) from Rel_features_materials where id_material = " + mat.getId() + ") and t.id_material =" + mat.getId() + " and t.id_material is not null order by f.descricao; ";

                try {
                    rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        Keys.Feature feature = new Keys.Feature(rs.getString("descricao"), rs.getString("medida"), rs.getInt("quantidade"), mat.getTypeOfMaterial());
                        lista.add(feature);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }

    public boolean deleteAssociationFeatureWithMaterial(Keys.Feature feature, Keys.Material mat) {
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
                String sql = "select id_caracteristica from Features where descricao like '" + feature.getDescription() + "' and medida like '" + feature.getUnityMeasure() + "' ;";
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

    public boolean updateAllRequests(java.util.Set<Keys.Request> requests, TimeDate.Date dat1, TimeDate.Date dat2) {
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
                    ResultSet rs1;
                    ResultSet rs2;
                    ResultSet rs3;
                    ResultSet rs4;
                    ResultSet rs5;
                    int id_material;
                    int id_pessoa;
                    int id_disciplina = 0;
                    int id_atividade = 0;
                    for (Keys.Request request : requests) {
                        sql = "select id_material from Materials where codigo like '" + request.getMaterial().getCodeOfMaterial() + "' and descricao like '" + request.getMaterial().getDescription() + "';";
                        rs = smt.executeQuery(sql);
                        if (rs.next()) {
                            id_material = rs.getInt("id_material");
                            sql = "select id_atividade from Activities where descricao like '%" + request.getActivity() + "%';";
                            rs1 = smt.executeQuery(sql);
                            if (rs1.next()) {
                                id_atividade = rs1.getInt("id_atividade");
                            }
                            sql = "select id_pessoa from Persons where identificacao like '" + request.getPerson().getIdentification() + "' and nome like '" + request.getPerson().getName() + "';";
                            rs2 = smt.executeQuery(sql);
                            if (rs2.next()) {
                                id_pessoa = rs2.getInt("id_pessoa");
                                sql = "select id_disciplina from Subjects where codigo like '" + request.getSubject().getCode() + "' and descricao like '" + request.getSubject().getName() + "'";
                                rs3 = smt.executeQuery(sql);
                                if (rs3.next()) {
                                    id_disciplina = rs3.getInt("id_disciplina");
                                }
                                sql = "select count(*), id_requisicao from Requests where id_material = " + id_material + " "
                                        + "and id_pessoa = " + id_pessoa + " and "
                                        + "data_inicio = STR_TO_DATE('" + request.getBeginDate().toString() + "','%d/%m/%Y') and "
                                        + "data_fim = STR_TO_DATE('" + request.getEndDate().toString() + "','%d/%m/%Y') and "
                                        + "quantidade = " + request.getQuantity() + " and "
                                        + "hora_inicio ='" + request.getTimeBegin().toString() + "' and "
                                        + "hora_fim = '" + request.getTimeEnd().toString() + "' and "
                                        + "dia_semana = " + request.getWeekDay().getDayNumber() + " and "
                                        + "origem = '" + request.getSource() + "' group by id_requisicao;";
                                rs4 = smt.executeQuery(sql);
                                int valor = 0;
                                int id_requisicao = 0;
                                if (rs4.next()) {
                                    valor = rs4.getInt(1);
                                    id_requisicao = rs4.getInt(2);
                                }
                                if (valor == 0) {
                                    sql = "select count(*), id_requisicao, requisicao_conjunta from Requests where id_material = " + id_material + " "
                                            + "and id_pessoa = " + id_pessoa + " and "
                                            + "data_inicio = STR_TO_DATE('" + request.getBeginDate().toString() + "','%d/%m/%Y') and "
                                            + "data_fim = STR_TO_DATE('" + request.getEndDate().toString() + "','%d/%m/%Y') and "
                                            + "quantidade = " + request.getQuantity() + " and "
                                            + "hora_fim = '" + request.getTimeBegin().toString() + "' and "
                                            + "dia_semana = " + request.getWeekDay().getDayNumber() + " and "
                                            + "id_requisicao in (select id_requisicao from Rel_requests_studentsclasses where codigo_turma = '" + request.getStudentsClass().getCode() + "') and "
                                            + "origem = '" + request.getSource() + "' group by id_requisicao , requisicao_conjunta;";
                                    rs5 = smt.executeQuery(sql);
                                    if ((rs5.next()) && (rs5.getInt(1) == 1)) {
                                        int numero;
                                        if (rs5.getInt("requisicao_conjunta") != 0) {
                                            numero = rs5.getInt("requisicao_conjunta");
                                        } else {
                                            numero = rs5.getInt("id_requisicao");
                                        }
                                        sql = "insert into Requests (id_material,id_pessoa,data_inicio,data_fim,hora_inicio,hora_fim,dia_semana,quantidade,origem,ativo,terminado,substituido,id_atividade,requisicao_conjunta) "
                                                + "values (" + id_material + "," + id_pessoa + ", STR_TO_DATE('" + request.getBeginDate().toString() + "','%d/%m/%Y'), STR_TO_DATE('" + request.getEndDate().toString() + "','%d/%m/%Y'), '"
                                                + request.getTimeBegin().toString() + "','" + request.getTimeEnd().toString() + "',"
                                                + request.getWeekDay().getDayNumber() + ",1,'csv',false,false,0," + id_atividade + "," + numero + ");";
                                        tupdate = new Threads.InsertRequest(sql, con);
                                        tupdate.start();
                                        try {
                                            tupdate.join();
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        this.associateClassroomWithSubject(this.getMaterial(id_material), this.getSubject(id_disciplina));
                                        int id = this.getRequestID(request, id_pessoa, id_material);
                                        if (id > 0) {
                                            this.associateRequestWithDiscipline(id, id_disciplina);
                                            this.associateRequestWithStudentClass(id, request.getStudentsClass().getCode());
                                        }
                                    } else {
                                        sql = "insert into Requests (id_material,id_pessoa, data_inicio,data_fim,hora_inicio,hora_fim,dia_semana,quantidade,origem,ativo,terminado,substituido,id_atividade) "
                                                + "values (" + id_material + "," + id_pessoa + ", STR_TO_DATE('" + request.getBeginDate().toString() + "','%d/%m/%Y'), STR_TO_DATE('" + request.getEndDate().toString() + "','%d/%m/%Y'), '"
                                                + request.getTimeBegin().toString() + "','" + request.getTimeEnd().toString() + "',"
                                                + request.getWeekDay().getDayNumber() + ",1,'csv',false,false,0," + id_atividade + ");";
                                        tupdate = new Threads.InsertRequest(sql, con);
                                        tupdate.start();
                                        try {
                                            tupdate.join();
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        this.associateClassroomWithSubject(this.getMaterial(id_material), this.getSubject(id_disciplina));
                                        int id = this.getRequestID(request, id_pessoa, id_material);
                                        if (id > 0) {
                                            this.associateRequestWithDiscipline(id, id_disciplina);
                                            this.associateRequestWithStudentClass(id, request.getStudentsClass().getCode());
                                        }
                                    }
                                } else if (valor > 0) {
                                    this.associateRequestWithDiscipline(id_requisicao, id_disciplina);
                                    this.associateRequestWithStudentClass(id_requisicao, request.getStudentsClass().getCode());
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

    public int getRequestID(Keys.Request req, int id_pessoa, int id_material) {
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
                String sql = "select id_requisicao from Requests where id_material = " + id_material + " "
                        + "and id_pessoa = " + id_pessoa + " and "
                        + "data_inicio = STR_TO_DATE('" + req.getBeginDate().toString() + "','%d/%m/%Y') and "
                        + "data_fim = STR_TO_DATE('" + req.getEndDate().toString() + "','%d/%m/%Y') and "
                        + "quantidade = " + req.getQuantity() + " and "
                        + "hora_inicio ='" + req.getTimeBegin().toString() + "' and "
                        + "hora_fim = '" + req.getTimeEnd().toString() + "' and "
                        + "dia_semana = " + req.getWeekDay().getDayNumber() + " and "
                        + "origem = '" + req.getSource() + "';";
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        return rs.getInt("id_requisicao");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }

    public void associateRequestWithDiscipline(int id_requisicao, int id_disciplina) {
        if (this.isTie()) {
            ResultSet rs;
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                String sql = "select count(*) from Rel_requests_subjects where id_requisicao = " + id_requisicao + " and id_disciplina=" + id_disciplina + ";";
                try {
                    rs = smt.executeQuery(sql);
                    if (rs.next() && (rs.getInt(1) == 0)) {
                        sql = "insert into Rel_requests_subjects (id_requisicao, id_disciplina) values (" + id_requisicao + "," + id_disciplina + ")";
                        con.setAutoCommit(false);
                        smt.executeUpdate(sql);
                        con.commit();
                        con.setAutoCommit(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void associateRequestWithStudentClass(int id_requisicao, String codigo_turma) {
        if (this.isTie()) {
            ResultSet rs;
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                String sql = "select count(*) from Rel_requests_studentsclasses where id_requisicao = " + id_requisicao + " and codigo_turma = '" + codigo_turma + "';";
                try {
                    rs = smt.executeQuery(sql);
                    rs.next();
                    if ((rs.getInt(1) == 0)) {
                        sql = "insert into Rel_requests_studentsclasses (id_requisicao, codigo_turma) values (" + id_requisicao + ",'" + codigo_turma + "')";
                        con.setAutoCommit(false);
                        smt.executeUpdate(sql);
                        con.commit();
                        con.setAutoCommit(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void associateSoftwareWithMaterial(Keys.Software soft, Keys.Material mat, boolean bool) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    int idsoft = this.getSoftwareID(soft);
                    String codigomat = mat.getCodeOfMaterial();
                    String sql = "select count(*) from Rel_material_software where codigo_material = '" + codigomat + "' and id_software = " + idsoft + ";";
                    ResultSet rs = smt.executeQuery(sql);
                    if ((rs.next()) && (rs.getInt(1) == 0)) {
                        sql = "insert into Rel_material_software (codigo_material, id_software, atualizado) values ('" + codigomat + "', " + idsoft + "," + bool + ");";
                        smt.executeUpdate(sql);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void insertSoftware(Keys.Software soft) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    if (soft.getVersion().equals("")) {
                        soft.setVersion("sem");
                    }
                    if (soft.getYear().equals("")) {
                        soft.setYear("sem");
                    }
                    if (soft.getInterprise().equals("")) {
                        soft.setInterprise("sem");
                    }
                    String sql = "select count(*) from Software where nome like '" + soft.getName() + "' and versao like '" + soft.getVersion() + "' and ano like '" + soft.getYear() + "' and empresa like '" + soft.getInterprise() + "';";
                    ResultSet rs = smt.executeQuery(sql);
                    if ((rs.next()) && (rs.getInt(1) == 0)) {
                        sql = "insert into Software (nome, versao, ano, empresa) values ('" + soft.getName() + "','" + soft.getVersion() + "','" + soft.getYear() + "','" + soft.getInterprise() + "') ";
                        con.setAutoCommit(false);
                        smt.executeUpdate(sql);
                        con.commit();
                        con.setAutoCommit(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int updateSofware(Keys.Software velho, Keys.Software novo, Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, "Erro no update de software", ex);
                smt = null;
            }
            if (smt != null) {
                if (velho.getVersion().equals("")) {
                    velho.setVersion("sem");
                }
                if (velho.getYear().equals("")) {
                    velho.setYear("sem");
                }
                if (velho.getInterprise().equals("")) {
                    velho.setInterprise("sem");
                }
                if (novo.getVersion().equals("")) {
                    novo.setVersion("sem");
                }
                if (novo.getYear().equals("")) {
                    novo.setYear("sem");
                }
                if (novo.getInterprise().equals("")) {
                    novo.setInterprise("sem");
                }
                int compara = velho.compareTo(novo);
                String sql = "select count(*) from Software where nome = '" + novo.getName() + "' and versao = '" + novo.getVersion() + "' and empresa = '" + novo.getInterprise() + "';";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        if ((rs.getInt(1) == 0) || (compara == 0)) {
                            sql = "update Software set nome = '" + novo.getName() + "', versao = '" + novo.getVersion() + "', ano = '" + novo.getYear() + "', empresa = '" + novo.getInterprise() + "' where id_software = " + this.getSoftwareID(velho) + " ;";
                            con.setAutoCommit(false);
                            smt.executeUpdate(sql);
                            con.commit();
                            con.setAutoCommit(true);
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }

    public void deleteSoftware(Keys.Software soft) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "delete from Software where nome = '" + soft.getName() + "' and versao = '" + soft.getVersion() + "' and ano = '" + soft.getYear() + "' and empresa = '" + soft.getInterprise() + "';";
                    con.setAutoCommit(false);
                    smt.executeUpdate(sql);
                    con.commit();
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void deleteAssociationBetweenSoftwareAndMaterial(Keys.Software soft, Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    int val = this.getSoftwareID(soft);
                    String sql = "delete from Rel_material_software where id_software = " + val + " and codigo_material = '" + mat.getCodeOfMaterial() + "';";
                    con.setAutoCommit(false);
                    smt.executeUpdate(sql);
                    con.commit();
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean isSoftwareAssociatedWithMaterial(Keys.Software soft, Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    int val = this.getSoftwareID(soft);
                    String sql = "select count(*) from Rel_material_software where id_software = " + val + " and codigo_material = '" + mat.getCodeOfMaterial() + "';";
                    ResultSet rs = smt.executeQuery(sql);
                    if ((rs.next()) && (rs.getInt(1) > 0)) {
                        return true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean isSubjectAssociatedWithClassroom(Keys.Subject sub, Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "select count(*) from Rel_classrooms_subjects where id_disciplina = " + sub.getId() + " and codigo_sala = '" + mat.getCodeOfMaterial() + "';";
                    ResultSet rs = smt.executeQuery(sql);
                    if ((rs.next()) && (rs.getInt(1) > 0)) {
                        return true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean isFeatureAssociatedWithMaterial(Keys.Feature fea, Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "select count(*) from Rel_features_materials where id_caracteristica = " + this.getFeatureId(fea) + " and id_material = " + mat.getId() + ";";
                    ResultSet rs = smt.executeQuery(sql);
                    if ((rs.next()) && (rs.getInt(1) > 0)) {
                        return true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public int updateStateOfSoftware(Keys.Software soft, Keys.Material mat, boolean bool) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "update Rel_material_software set atualizado = " + bool + " where id_software = " + this.getSoftwareID(soft) + " and codigo_material = '" + mat.getCodeOfMaterial() + "';";
                    con.setAutoCommit(false);
                    smt.executeUpdate(sql);
                    con.commit();
                    con.setAutoCommit(true);
                    return 0;
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }

    public boolean getStateOfSoftwareUpdated(Keys.Software soft, Keys.Material mat) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "select atualizado from Rel_material_software where id_software = " + this.getSoftwareID(soft) + " and codigo_material = '" + mat.getCodeOfMaterial() + "';";
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        return rs.getBoolean("atualizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public int getSoftwareID(Keys.Software soft) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    if (soft.getVersion().equals("")) {
                        soft.setVersion("sem");
                    }
                    if (soft.getYear().equals("")) {
                        soft.setYear("sem");
                    }
                    if (soft.getInterprise().equals("")) {
                        soft.setInterprise("sem");
                    }
                    String sql = "select id_software from Software where nome like  '" + soft.getName() + "' and versao = '" + soft.getVersion() + "' and ano = '" + soft.getYear() + "' and empresa = '" + soft.getInterprise() + "';";
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        return rs.getInt("id_software");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }

    public java.util.List<Keys.Software> getSoftwareList() {
        java.util.List<Keys.Software> lista = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "select nome, versao, ano, empresa from Software order by nome;";
                    ResultSet rs = smt.executeQuery(sql);
                    Keys.Software soft;
                    while (rs.next()) {
                        soft = new Keys.Software(rs.getString("nome"), rs.getString("versao"), rs.getString("ano"), rs.getString("empresa"));
                        if (soft.getVersion().equals("sem")) {
                            soft.setVersion("");
                        }
                        if (soft.getYear().equals("sem")) {
                            soft.setYear("");
                        }
                        if (soft.getInterprise().equals("sem")) {
                            soft.setInterprise("");
                        }
                        lista.add(soft);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }

    public java.util.List<Keys.Software> getSoftwareListByMaterial(Keys.Material mat) {
        java.util.List<Keys.Software> lista = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "select nome, versao, ano, empresa from Software where id_software in (select id_software from Rel_material_software where codigo_material = '" + mat.getCodeOfMaterial() + "') order by nome;";
                    ResultSet rs = smt.executeQuery(sql);
                    Keys.Software soft;
                    while (rs.next()) {
                        soft = new Keys.Software(rs.getString("nome"), rs.getString("versao"), rs.getString("ano"), rs.getString("empresa"));
                        if (soft.getVersion().equals("sem")) {
                            soft.setVersion("");
                        }
                        if (soft.getYear().equals("sem")) {
                            soft.setYear("");
                        }
                        if (soft.getInterprise().equals("sem")) {
                            soft.setInterprise("");
                        }
                        lista.add(soft);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }

    public java.util.List<Keys.Software> getSoftwareListByYear(String ano) {
        java.util.List<Keys.Software> lista = new java.util.ArrayList<>();
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
                smt = null;
            }
            if (smt != null) {
                try {
                    String sql = "select nome, versao, ano, empresa from Software where ano = '" + ano + "' and ano != 'sem' order by nome;";
                    ResultSet rs = smt.executeQuery(sql);
                    Keys.Software soft;
                    while (rs.next()) {
                        soft = new Keys.Software(rs.getString("nome"), rs.getString("versao"), rs.getString("ano"), rs.getString("empresa"));
                        if (soft.getVersion().equals("sem")) {
                            soft.setVersion("");
                        }
                        if (soft.getInterprise().equals("sem")) {
                            soft.setInterprise("");
                        }
                        lista.add(soft);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }

    public String getActivity(int id) {
        if (this.isTie()) {
            String sql = "select descricao from Activities where id_atividade = " + id + ";";
            try {
                Statement smt = con.createStatement();
                ResultSet rs = smt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getString("descricao");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    public java.util.Set<String> getActivity(String def) {
        java.util.Set<String> atividades = new java.util.HashSet<>();
        if (this.isTie()) {
            String sql = "select descricao from Activities where lower(descricao) like lower('%" + def + "%');";
            try {
                Statement smt = con.createStatement();
                ResultSet rs = smt.executeQuery(sql);
                while (rs.next()) {
                    atividades.add(rs.getString("descricao"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return atividades;
    }

    public boolean insertActivities(java.util.Set<String> atividades) {
        if (this.isTie()) {
            String sql;
            ResultSet rs;
            try {
                Statement smt = con.createStatement();
                for (String atividade : atividades) {
                    sql = "select count(*) from Activities where descricao like '%" + atividade + "%'";
                    rs = smt.executeQuery(sql);
                    if ((rs.next()) && (rs.getInt(1) == 0)) {
                        sql = "insert into Activities (descricao) values ('" + atividade + "');";
                        smt.executeUpdate(sql);
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

    public boolean insertActivity(String atividade) {
        if (this.isTie()) {
            try {
                String sql = "select count(*) from Activities where descricao like '%" + atividade + "%'";
                Statement smt = con.createStatement();
                ResultSet rs = smt.executeQuery(sql);
                if ((rs.next()) && (rs.getInt(1) == 0)) {
                    sql = "insert into Activities (descricao) values ('" + atividade + "');";
                    smt.executeUpdate(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        }
        return false;
    }

    public java.util.Set<String> getActivities() {
        java.util.Set<String> atividades = new java.util.HashSet<>();
        if (this.isTie()) {
            String sql = "select descricao from Activities;";
            try {
                Statement smt = con.createStatement();
                ResultSet rs = smt.executeQuery(sql);
                while (rs.next()) {
                    atividades.add(rs.getString("descricao"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atividades;
    }

    public void associateClassroomWithSubject(Keys.Material mat, Keys.Subject sub) {
        if (this.isTie()) {
            PreparedStatement smt;
            String sql = "select count(*) from Rel_classrooms_subjects where codigo_sala like '" + mat.getCodeOfMaterial() + "' and id_disciplina = " + sub.getId() + ";";
            try {
                smt = con.prepareStatement(sql);
                ResultSet rs = smt.executeQuery(sql);
                if ((rs.next()) && (rs.getInt(1) == 0)) {
                    sql = "insert into Rel_classrooms_subjects (codigo_sala,id_disciplina) values ('" + mat.getCodeOfMaterial() + "'," + sub.getId() + ")";
                    con.setAutoCommit(false);
                    smt = con.prepareStatement(sql);
                    smt.executeUpdate();
                    con.commit();
                    con.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public java.util.Set<Keys.Request> getAllRequests() {
        java.util.Set<Keys.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao, id_material, id_pessoa, id_atividade, DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio,"
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim,"
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega, "
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega, "
                    + "requisicao_conjunta "
                    + "from Requests;";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Keys.Request request;
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                String origem;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public java.util.Set<Keys.Request> getRequests(TimeDate.Date dinicio, TimeDate.Date dfim) {
        java.util.Set<Keys.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao, id_material, id_pessoa, id_atividade, DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio,"
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim,"
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega, "
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega, "
                    + "requisicao_conjunta "
                    + "from Requests"
                    + "where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y')"
                    + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y');";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Keys.Request request;
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                String origem;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public java.util.Set<Keys.Request> getRequests(Keys.Material mat, TimeDate.Date dinicio, TimeDate.Date dfim) {
        java.util.Set<Keys.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao, id_material, id_pessoa, id_atividade, DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega, "
                    + "requisicao_conjunta "
                    + "from Requests "
                    + "where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                    + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                    + "and id_material = " + mat.getId() + ";";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Keys.Request request;
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
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
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public java.util.Set<Keys.Request> getUnionRequests(Keys.Request req) {
        java.util.Set<Keys.Request> requisicoes = new java.util.HashSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao, id_material, id_pessoa, id_atividade, DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega, "
                    + "requisicao_conjunta "
                    + "from Requests "
                    + "where requisicao_conjunta = " + req.getId() + ";";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Keys.Request request;
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                String origem;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public Keys.Request getNextRequest(Keys.Material mat) {
        Keys.Request request = new Keys.Request();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao, id_material, id_pessoa, id_atividade, DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega, "
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega, "
                    + "requisicao_conjunta "
                    + "from Requests "
                    + "where id_material = " + mat.getId() + " and (data_inicio > curdate() or (data_inicio = curdate() and hora_fim >= curtime())) and ativo = 0 and terminado = 0  order by data_inicio, hora_inicio limit 1;";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
                TimeDate.Date inicio;
                TimeDate.Date fim;
                TimeDate.Time tinicio;
                TimeDate.Time tfim;
                TimeDate.WeekDay dia;
                TimeDate.Date dlevantamento;
                TimeDate.Date dentrega;
                TimeDate.Time tlevantamento;
                TimeDate.Time tentrega;
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                String origem;
                int ido;
                if (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return request;
    }

    public java.util.Set<Keys.Subject> getSubjectsAssociatedWithSimpleRequest(int id) {
        java.util.Set<Keys.Subject> disciplinas = new java.util.HashSet<>();
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
                String sql = "select id_requisicao, id_disciplina from Rel_requests_subjects where id_requisicao = " + id + ";";
                try {
                    int id_disciplina = 0;
                    rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        id_disciplina = rs.getInt("id_disciplina");
                        disciplinas.add(this.getSubject(id_disciplina));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return disciplinas;
    }

    public java.util.Set<Keys.ClassStudents> getStudentsClassesAssociatedWithSimpleRequest(int id) {
        java.util.Set<Keys.ClassStudents> turmas = new java.util.HashSet<>();
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
                String sql = "select codigo_turma from Rel_requests_studentsclasses where id_requisicao = " + id + ";";
                try {
                    String codigo;
                    rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        codigo = rs.getString("codigo_turma");
                        turmas.add(this.getStudentsClass(codigo));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return turmas;
    }

    public Keys.Request getCurrentRequest(Keys.Material mat) {
        Keys.Request request = new Keys.Request();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            String sql = "select id_requisicao, id_material, id_pessoa, id_atividade, DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                    + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, codigo_turma, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento,"
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento,"
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega, "
                    + "requisicao_conjunta "
                    + "from Requests "
                    + " where id_material = " + mat.getId() + " and ativo = 1 and terminado = 0  order by data_inicio, hora_inicio limit 1;";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
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
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                int ido;
                if (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return request;
    }

    public java.util.Set<Keys.Request> getRequests(Keys.Person pess, TimeDate.Date dinicio, TimeDate.Date dfim, boolean estado, boolean terminado) {
        java.util.Set<Keys.Request> requisicoes = new java.util.TreeSet<>();
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
                    String sql = "select id_requisicao, id_material, id_pessoa, id_atividade, DATE_FORMAT(data_inicio,'%d/%m/%Y') "
                            + "inicio,DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                            + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio,"
                            + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim,"
                            + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                            + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                            + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                            + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega, "
                            + "TIME_FORMAT(hora_entrega,'%H:%i:%s')hora_entrega, "
                            + "requisicao_conjunta "
                            + "from Requests "
                            + "where data_inicio >= STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                            + "and data_fim <= STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                            + "and id_pessoa = " + id + ";";
                    smt = con.prepareStatement(sql);
                    rs = smt.executeQuery();
                    Keys.Request request = null;
                    String[] aux;
                    Keys.Person pessoa;
                    Keys.Material material;
                    String atividade;
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
                    java.util.Set<Keys.Subject> disciplinas;
                    java.util.Set<Keys.ClassStudents> turmas;
                    int ido;
                    while (rs.next()) {
                        ido = rs.getInt("id_requisicao");
                        pessoa = this.getPerson(rs.getInt("id_pessoa"));
                        material = this.getMaterial(rs.getInt("id_material"));
                        atividade = this.getActivity(rs.getInt("id_atividade"));
                        disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                        turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                        request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                        requisicoes.add(request);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public java.util.Set<Keys.Request> getRequests(int tipo, Keys.TypeOfMaterial mat, String nome, TimeDate.Date dinicio, TimeDate.Date dfim, boolean estado, boolean terminado) {
        java.util.Set<Keys.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            int id;
            try {
                String sql;
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
                    String auxilia;
                    while (rsaux.next()) {
                        sql = "";
                        if (estado) {
                            auxilia = "data_fim";
                        } else {
                            auxilia = "data_inicio";
                        }
                        if (tipo == 0) {
                            id = rsaux.getInt("id_pessoa");
                            sql = "select id_requisicao, id_material, id_pessoa, id_atividade, "
                                    + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                                    + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega, "
                                    + "requisicao_conjunta "
                                    + "from Requests right join (select id_material from Materials where id_tipo=" + mat.getMaterialTypeID() + ") "
                                    + "auxiliar using (id_material) "
                                    + " where " + auxilia + " between STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                                    + "and STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                                    + "and id_pessoa = " + id + " and ativo = " + estado + " and terminado = " + terminado + ";";
                        } else if (tipo == 1) {
                            id = rsaux.getInt("id_material");
                            sql = "select id_requisicao, id_material, id_pessoa, id_atividade, "
                                    + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                                    + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega, "
                                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega, "
                                    + "requisicao_conjunta "
                                    + "from Requests  right join (select id_material from Materials where id_tipo=" + mat.getMaterialTypeID() + ") "
                                    + "auxiliar using (id_material) "
                                    + "where " + auxilia + " between STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                                    + "and STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                                    + "and id_material = " + id + " and ativo = " + estado + " and terminado = " + terminado + ";";
                        }
                        if (!sql.equals("")) {
                            smt = con.prepareStatement(sql);
                            rs = smt.executeQuery();
                            Keys.Request request;
                            String[] aux;
                            Keys.Person pessoa;
                            Keys.Material material;
                            String atividade;
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
                            java.util.Set<Keys.Subject> disciplinas;
                            java.util.Set<Keys.ClassStudents> turmas;
                            int ido;
                            while (rs.next()) {
                                ido = rs.getInt("id_requisicao");
                                pessoa = this.getPerson(rs.getInt("id_pessoa"));
                                material = this.getMaterial(rs.getInt("id_material"));
                                atividade = this.getActivity(rs.getInt("id_atividade"));
                                disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                                turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                                request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
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

    public java.util.Set<Keys.Request> getRequestsByTime(Keys.TypeOfMaterial mat, Boolean bool, TimeDate.Time time, TimeDate.Date dinicio, TimeDate.Date dfim, boolean estado, boolean terminado) {
        java.util.Set<Keys.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            ResultSet rs;
            try {
                String sql;
                String auxilia;
                if (!bool) {
                    auxilia = "data_inicio";
                    sql = "select id_requisicao, id_material, id_pessoa, id_atividade, "
                            + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                            + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                            + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                            + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                            + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                            + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                            + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                            + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                            + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega, "
                            + "requisicao_conjunta "
                            + "from Requests right join (select id_material from Materials where id_tipo=" + mat.getMaterialTypeID() + ") "
                            + "auxiliar using (id_material) "
                            + "where TIME_FORMAT(hora_inicio,'%H:%i:%s') = '" + time.toString() + "' "
                            + "and " + auxilia + " between STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                            + "and STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                            + "and ativo = " + estado + " and terminado = " + terminado + ";";
                } else {
                    auxilia = "data_fim";
                    sql = "select id_requisicao, id_material, id_pessoa, id_atividade, "
                            + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                            + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                            + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                            + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                            + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                            + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                            + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                            + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                            + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega, "
                            + "requisicao_conjunta "
                            + "from Requests right join (select id_material from Materials where id_tipo=" + mat.getMaterialTypeID() + ") "
                            + "auxiliar using (id_material) "
                            + "where TIME_FORMAT(hora_fim,'%H:%i:%s') = '" + time.toString() + "' "
                            + "and " + auxilia + " between STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                            + "and STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') "
                            + "and ativo = " + estado + " and terminado = " + terminado + ";";
                }
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Keys.Request request;
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
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
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                int ido;
                while (rs.next()) {
                    ido = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(ido);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(ido, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public java.util.Set<Keys.Request> getRequests(Keys.TypeOfMaterial mat, TimeDate.Date dinicio, TimeDate.Date dfim, boolean estado, boolean terminado) {
        java.util.Set<Keys.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            int ido = mat.getMaterialTypeID();
            ResultSet rs;
            String sql;
            String auxilia;
            if (estado) {
                auxilia = "data_fim";
            } else {
                auxilia = "data_inicio";
            }

            sql = "select id_requisicao, id_material, id_pessoa, id_atividade, "
                    + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                    + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega, "
                    + "requisicao_conjunta "
                    + "from Requests right join (select id_material from Materials where id_tipo=" + ido + ") "
                    + "auxiliar using (id_material) where " + auxilia + " between STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                    + "and STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y') and ativo = " + estado + " and terminado = " + terminado + ";";

            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Keys.Request request;
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
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
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                int id;
                while (rs.next()) {
                    id = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(id);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(id, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }
    
    public TimeDate.Date getLastdateOnActiveRequests(Keys.TypeOfMaterial mat){
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select DATE_FORMAT(data_fim,'%d/%m/%Y') data from Requests right join (select id_material from Materials where id_tipo=" + mat.getMaterialTypeID() + ") "
                    + "auxiliar using (id_material) where ativo = 1 order by data_fim desc limit 1";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        String[] aux = rs.getString("data").split("/");
                        return new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return new TimeDate.Date();
    }
    
    public TimeDate.Date getFirstdateOnActiveRequests(Keys.TypeOfMaterial mat){
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select DATE_FORMAT(data_inicio,'%d/%m/%Y') from Requests right join (select id_material from Materials where id_tipo=" + mat.getMaterialTypeID() + ") "
                    + "auxiliar using (id_material) where ativo = 1 order by data_inicio asc limit 1";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        String[] aux = rs.getString("data").split("/");
                        return new TimeDate.Date(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), Integer.valueOf(aux[2]));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return new TimeDate.Date();
    }

    public java.util.Set<Keys.Request> getRequestsByMaterialByDateInterval(Keys.Material mat, TimeDate.Date dinicio, TimeDate.Date dfim) {
        java.util.Set<Keys.Request> requisicoes = new java.util.TreeSet<>();
        if (this.isTie()) {
            PreparedStatement smt;
            int ido = mat.getId();
            ResultSet rs;
            String sql;
            sql = "select id_requisicao, id_material, id_pessoa, id_atividade, "
                    + "DATE_FORMAT(data_inicio,'%d/%m/%Y') inicio, "
                    + "DATE_FORMAT(data_fim,'%d/%m/%Y') fim, "
                    + "TIME_FORMAT(hora_inicio,'%H:%i:%s') tinicio, "
                    + "TIME_FORMAT(hora_fim,'%H:%i:%s') tfim, "
                    + "dia_semana, quantidade, origem, ativo, terminado, substituido, "
                    + "DATE_FORMAT(data_levantamento,'%d/%m/%Y') data_levantamento, "
                    + "TIME_FORMAT(hora_levantamento,'%H:%i:%s')hora_levantamento, "
                    + "DATE_FORMAT(data_entrega,'%d/%m/%Y') data_entrega,"
                    + "TIME_FORMAT(hora_entrega,'%H:%i:%s') hora_entrega, "
                    + "requisicao_conjunta "
                    + "from Requests where id_material = " + ido + " and data_inicio between STR_TO_DATE('" + dinicio.toString() + "','%d/%m/%Y') "
                    + "and STR_TO_DATE('" + dfim.toString() + "','%d/%m/%Y');";
            try {
                smt = con.prepareStatement(sql);
                rs = smt.executeQuery();
                Keys.Request request;
                String[] aux;
                Keys.Person pessoa;
                Keys.Material material;
                String atividade;
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
                java.util.Set<Keys.Subject> disciplinas;
                java.util.Set<Keys.ClassStudents> turmas;
                int id;
                while (rs.next()) {
                    id = rs.getInt("id_requisicao");
                    pessoa = this.getPerson(rs.getInt("id_pessoa"));
                    material = this.getMaterial(rs.getInt("id_material"));
                    atividade = this.getActivity(rs.getInt("id_atividade"));
                    disciplinas = this.getSubjectsAssociatedWithSimpleRequest(id);
                    turmas = this.getStudentsClassesAssociatedWithSimpleRequest(ido);
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
                    request = new Keys.Request(id, inicio, fim, dia, tinicio, tfim, pessoa, material, disciplinas, atividade, turmas, origem, rs.getBoolean("ativo"), rs.getBoolean("terminado"), rs.getInt("substituido"), rs.getInt("quantidade"), dlevantamento, tlevantamento, dentrega, tentrega, rs.getInt("requisicao_conjunta"));
                    requisicoes.add(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requisicoes;
    }

    public boolean changeRequestActiveState(Keys.Request req) {
        if (this.isTie()) {
            PreparedStatement smt;
            PreparedStatement smt2;
            ResultSet rs;
            String sql = "update Requests set ativo = 1, data_levantamento = CURDATE(), hora_levantamento = CURTIME() where  id_requisicao = " + req.getId() + "; ";
            try {
                con.setAutoCommit(false);
                smt = con.prepareStatement(sql);
                smt.executeUpdate();
                con.commit();
                sql = "update Materials set estado = 1 where codigo = '" + req.getMaterial().getCodeOfMaterial() + "';";
                smt2 = con.prepareStatement(sql);
                smt2.executeLargeUpdate();
                con.commit();
                con.setAutoCommit(true);

            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class
                        .getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    public boolean changeRequestTerminateState(Keys.Request req) {
        if (this.isTie()) {
            PreparedStatement smt;
            PreparedStatement smt2;
            String sql = "update Requests set terminado = 1, ativo = 0, data_entrega = CURDATE(), hora_entrega = CURTIME() where  id_requisicao = " + req.getId() + "; ";
            try {
                con.setAutoCommit(false);
                smt = con.prepareStatement(sql);
                smt.executeUpdate();
                con.commit();
                sql = "update Materials set estado = 0 where codigo = '" + req.getMaterial().getCodeOfMaterial() + "';";
                smt2 = con.prepareStatement(sql);
                smt2.executeUpdate();
                con.commit();
                con.setAutoCommit(true);

            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class
                        .getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    public Keys.Subject getSubject(int id) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();

            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class
                        .getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select descricao,codigo from Subjects where id_disciplina = " + id + ";";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        return new Keys.Subject(id, rs.getString("descricao"), rs.getString("codigo"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public boolean getStateOfMaterial(Keys.Request req) {
        if (this.isTie()) {
            Statement smt;
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                smt = null;
            }
            if (smt != null) {
                String sql = "select estado from Materials where id_material = " + req.getMaterial().getId() + ";";
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        return rs.getBoolean(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return false;
    }

    public void close() {
        try {
            if ((this.isTie()) && (!con.isClosed())) {
                con.close();
                this.tie = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
