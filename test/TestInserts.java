
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toze
 */
public class TestInserts {
    String url = "jdbc:mysql://localhost:3306/clavis?autoReconnect=false&useSSL=false&user=root&password=sobral";
    DataBase.DataBase db;
     
    @Test
    public void testInsertOnDataBase(){
        db = new DataBase.DataBase(url);
        Keys.TypeOfMaterial tipo = db.getTypeOfMaterial(2);
        Keys.Material m = new Keys.Material(tipo, "Len710", "Lenovo Yoga 710", false);
        db.insertMaterial(m);
        Keys.Material mo = db.getMaterial("Len710");
        db.close();
        Assert.assertNotNull(mo);
    }
    
    @Test 
    public void testInsertDeleteUserOnDataBase(){
        db = new DataBase.DataBase(url);
        java.util.List<Keys.Function> f = db.getFunctions();
        Keys.Person p = new Keys.Person("José Mário Pereirinha", "1010234", f.get(2), 3);
        db.insertPerson(p);
        Keys.Person po = db.getPerson("1010234");
        Assert.assertNotNull(po);
        db.deletePerson(p);
        po = db.getPerson("1010234");
        Assert.assertEquals("", po.getName());
        db.close();
    }
    
    @Test
    public void testInsertEmptyFields() {
        db = new DataBase.DataBase(url);
        Keys.Person p = new Keys.Person();
        int val = db.insertPerson(p);
        // 0 Não realiza registo
        // 1 Sim
        Assert.assertEquals(0, val);
    }
    
    @Test
    public void testupdate() {
        db = new DataBase.DataBase(url);
        java.util.List<Keys.Function> f = db.getFunctions();
        Keys.Person p = new Keys.Person("José Mário Pereirinha", "1010234", f.get(2), 3);
        db.insertPerson(p);
        p.setName("José Alberto Pereira");
        int val = db.updatePerson(p);
        // 0 Não realiza registo
        // 1 Sim
        Assert.assertEquals(1, val);
        Keys.Person po = db.getPerson("1010234");
        Assert.assertEquals("José Alberto Pereira", po.getName());
        db.deletePerson(p);
        db.close();
    }
    
    @Test
    public void testUpdateEmptyFields() {
        db = new DataBase.DataBase(url);
        java.util.List<Keys.Function> f = db.getFunctions();
        Keys.Person p = new Keys.Person("José Mário Pereirinha", "1010234", f.get(2), 3);
        db.insertPerson(p);
        p.setName("");
        int val = db.updatePerson(p);
        // 0 Não realiza registo
        // 1 Sim
        Assert.assertEquals(0, val);
        Keys.Person po = db.getPerson("1010234");
        db.deletePerson(po);
        db.close();
    }
    
    public static void main(String[] args) {
        Result resultado = JUnitCore.runClasses(TestInserts.class);
        resultado.getFailures().stream().forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println(resultado.wasSuccessful());
    } 
}
