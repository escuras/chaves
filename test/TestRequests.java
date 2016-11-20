/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 *
 * @author toze
 */
public class TestRequests {
    String url = "jdbc:mysql://localhost:3306/clavis?autoReconnect=false&useSSL=false&user=root&password=sobral";
    DataBase.DataBase db;
    
    @Test
    public void testActionRequests() throws ParseException{
        TimeDate.Date date = new TimeDate.Date();
        TimeDate.Date date2 = new TimeDate.Date();
        TimeDate.Time tinicio = new TimeDate.Time();
        TimeDate.Time tfim = new TimeDate.Time().addSeconds(60*10);
        db = new DataBase.DataBase(url);
        java.util.List<Keys.Function> f = db.getFunctions();
        Keys.Person p = new Keys.Person("José Mário Pereirinha", "1010234", f.get(2), 3);
        db.insertPerson(p);
        Keys.TypeOfMaterial tipo = db.getTypeOfMaterial(2);
        Keys.Material m = new Keys.Material(tipo, "Len71000", "Lenovo Yoga 770", false);
        db.insertMaterial(m);
        m.setId(db.getMaterialID(m));
        Keys.Request re = new Keys.Request(date, date2, new TimeDate.WeekDay(date), tinicio, tfim, p, m, "local");
        int val = db.insertRequest(m, p, "", new java.util.ArrayList<>(), new java.util.ArrayList<>(), date, date2, tinicio, tfim);
        // teste criação val = 1, sucesso.
        Assert.assertTrue(val > 0);
        Assert.assertTrue(db.changeRequestActiveState(re));
        // material emprestado
        Assert.assertTrue(db.getMaterial(m.getCodeOfMaterial()).isLoaned());
        Assert.assertTrue(db.changeRequestTerminateState(re));
        // devolução realizada, material livre
        Assert.assertTrue(!db.getMaterial(m.getCodeOfMaterial()).isLoaned());
        db.deleteRequest(re, p, m);
        db.deletePerson(p);
        db.deleteMaterial(m);
        db.close();
    }
    
    public static void main(String[] args) {
        Result resultado = JUnitCore.runClasses(TestRequests.class);
        resultado.getFailures().stream().forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println(resultado.wasSuccessful());
    } 
}
