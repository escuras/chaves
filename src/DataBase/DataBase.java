/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
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
                for (Clavis.Person pessoa : pessoas) {
                    String nome = pessoa.getName();
                    String identificacao = pessoa.getIdentification();
                    String email = pessoa.getEmail();
                    String telefone = pessoa.getPhone();
                    String sql = "select count(*) from Persons where identificacao like '" + identificacao + "';";
                    smt = con.createStatement();
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
                return true;
            } catch (SQLException ex) {
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
                        System.out.println(rs.getInt(1));
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
                smt = con.createStatement();
                smt.executeUpdate(sql);
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
                smt = con.createStatement();
                smt.executeUpdate(sql);
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
                smt = con.createStatement();
                smt.executeUpdate(sql);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
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
                smt = con.createStatement();
                String sql = "Insert into Functions (descricao, privilegio) values ('" + funcao.getName() + "'," + funcao.getPrivilege() + ");";
                smt.execute(sql);
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
                smt = con.createStatement();
                String sql = "delete from Functions where id_function = " + funcao.getId() + ";";
                smt.execute(sql);
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
                smt = con.createStatement();
                String sql = "update Functions set privilegio = " + valor + " where id_funcao = " + funcao.getId() + ";";
                smt.execute(sql);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public java.util.List<Clavis.TypeOfMaterial> getTypeOfMaterials() {
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
                String sql = "Select * from Materials group by id_tipo order by descricao;";
                Clavis.Material material;
                try {
                    ResultSet rs = smt.executeQuery(sql);
                    while (rs.next()) {
                        sql = "select * from TypesOfMaterial where id_tipo ='" + rs.getInt("id_tipo") + "'";
                        aux = con.createStatement();
                        rs2 = aux.executeQuery(sql);
                        if (rs2.next()) {
                            Clavis.TypeOfMaterial tipo;
                            if (rs2.getString("Imagem").equals("sem")) {
                                tipo = new Clavis.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"), rs2.getString("imagem"));
                            } else {
                                tipo = new Clavis.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"));
                            }
                            if (!rs.getString("imagem").equals("sem")) {
                                material = new Clavis.Material(tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getString("imagem"), rs.getBoolean("estado"));
                            } else {
                                material = new Clavis.Material(tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
                            }
                            materiais.add(material);

                        }
                    }
                    if (!smt.isClosed()) {
                        smt.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("erro");
                }
            }
        }
        return materiais;
    }

    public java.util.List<Clavis.Material> getMaterialsByType(int id) {
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
                            if (rs2.getString("Imagem").equals("sem")) {
                                tipo = new Clavis.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"), rs2.getString("imagem"));
                            } else {
                                tipo = new Clavis.TypeOfMaterial(rs2.getInt("id_tipo"), rs2.getString("descricao"), rs2.getInt("total"), rs2.getInt("livres"));
                            }
                            if (!rs.getString("imagem").equals("sem")) {
                                material = new Clavis.Material(tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getString("imagem"), rs.getBoolean("estado"));
                            } else {
                                material = new Clavis.Material(tipo, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
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
                                mat = new Clavis.Material(tp, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
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
                                mat = new Clavis.Material(tp, rs.getString("codigo"), rs.getString("descricao"), rs.getBoolean("estado"));
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

    public java.util.List<Clavis.Material> getMaterialwithSameFeature(Clavis.Feature feature) {
        java.util.List<Clavis.Material> materiais = new java.util.ArrayList<>();
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

    public java.util.List<Clavis.Classroom> getClassrooms() {
        java.util.List<Clavis.Classroom> classrooms = new java.util.ArrayList<>();
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
                        if (rs.getString("Imagem").equals("sem")) {
                            tipo = new Clavis.TypeOfMaterial(rs.getInt("id_tipo"), rs.getString("descricao"), rs.getInt("total"), rs.getInt("livres"), rs.getString("imagem"));
                        } else {
                            tipo = new Clavis.TypeOfMaterial(rs.getInt("id_tipo"), rs.getString("descricao"), rs.getInt("total"), rs.getInt("livres"));
                        }
                        sql = "Select * from Materials where id_tipo = '1'";
                        try {
                            smt2 = con.createStatement();
                        } catch (SQLException ex) {
                            smt2 = null;
                        }
                        if (smt2 != null) {
                            ResultSet rs2 = smt2.executeQuery(sql);
                            while (rs2.next()) {
                                if (!rs2.getString("imagem").equals("sem")) {
                                    material = new Clavis.Material(tipo, rs2.getString("codigo"), rs2.getString("caracteristicas"), rs2.getString("imagem"), rs2.getBoolean("estado"));
                                } else {
                                    material = new Clavis.Material(tipo, rs2.getString("codigo"), rs2.getString("descricao"), rs2.getBoolean("estado"));
                                }
                                sql = "Select * from Classrooms where codigo_sala = '" + rs2.getString("codigo") + "'";
                                try {
                                    smt3 = con.createStatement();
                                } catch (SQLException ex) {
                                    smt3 = null;
                                }
                                if (smt3 != null) {
                                    ResultSet rs3 = smt3.executeQuery(sql);
                                    if (rs3.next()) {
                                        String outros;
                                        if ((outros = rs3.getString("outros")) == null) {
                                            outros = "";
                                        }
                                        Clavis.Classroom sala = new Clavis.Classroom(material, outros, rs3.getInt("ncomputadores"), rs3.getInt("lugares"), rs3.getBoolean("projetor"), rs3.getBoolean("quadro_interativo"));
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
                        String outros;
                        if ((outros = rs.getString("outros")) == null) {
                            outros = "";
                        }
                        sala = new Clavis.Classroom(m, outros, rs.getInt("ncomputadores"), rs.getInt("lugares"), rs.getBoolean("projetor"), rs.getBoolean("quadro_interativo"));
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
                            System.out.println(idtipo);
                            System.out.println(material.getTypeOfMaterialName());
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
                    System.out.println("ddd " + idtipo);
                    System.out.println(material.getTypeOfMaterialName());
                    sql = "select id_tipo from TypesOfMaterial where upper(descricao) like upper('" + material.getTypeOfMaterialName() + "');";
                    rs = smt.executeQuery(sql);
                    if (rs.next()) {
                        System.out.println("fff " + idtipo);
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

}
