
import java.awt.Color;
import org.jfree.util.Log;
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
public class TestAddRessource {
    String url = "jdbc:mysql://localhost:3306/clavis?autoReconnect=false&useSSL=false&user=root&password=sobral";
    DataBase.DataBase db = new DataBase.DataBase(url);
     
    @Test
    public void testInsertOnDataBase(){
        Keys.TypeOfMaterial tipo = db.getTypeOfMaterial(2);
        Keys.Material m = new Keys.Material(tipo, "Len710", "Lenovo Yoga 710", false);
        db.insertMaterial(m);
        Keys.Material mo = db.getMaterial("Len710");
        db.close();
        Assert.assertNotNull(mo);
    }
    
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestAddRessource.class);
        result.getFailures().stream().forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println(result.wasSuccessful());
    } 
}
