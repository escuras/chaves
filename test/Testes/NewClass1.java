/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import Clavis.Material;
import java.util.List;
import java.util.Set;

/**
 *
 * @author toze
 */
public class NewClass1 {
    
    public static void main(String[] args) {
        
        DataBase.DataBase db = new DataBase.DataBase("jdbc:mysql://localhost:3306/clavis?autoReconnect=true&useSSL=false&user=root&password=sobral");
        Set<Material> tmas = db.getMaterialsByType(2);
        if (tmas.size() > 0) {
            for (int i=0 ; i<tmas.size();i++){
            }
        }
        Material mo = db.getMaterial("33");
        System.out.println("tipo "+mo.getMaterialTypeID());
        
        Clavis.Classroom clas = db.getClassroom(mo);
        System.out.println(clas.getPlaces());
        List<Clavis.Function> fun = db.getFunctionsWithprivilege(2);
        System.out.println(fun.get(0).getName());
        System.out.println(db.getFunction("Professor").getId());
        Clavis.Person pess = new Clavis.Person("Antonio", "123453", db.getFunction("Professor"));
        //System.out.println(db.insertPerson(pess));
        //pess.setName("Antonio Santos");
        //db.updatePerson(pess);
        //db.alterPrivilegeInPerson(pess, 3);
        //db.deletePerson(pess);
        //Clavis.Material mat = new Clavis.Material(mo.getTypeOfMaterial(), "1234533", "Loja", false);
        //db.insertMaterial(mat);
        
        
        Clavis.Feature fae = new Clavis.Feature("byte");
        
        
        String isto = "Sala de aula";
        System.out.println(isto.compareToIgnoreCase("rojtr"));
        Set<Clavis.Classroom> cl = db.getClassrooms(2);
        for (Clavis.Classroom c : cl) {
            System.out.println(c.getDescription()+" "+c.getComputers());
        }
        Clavis.Subject sub = new Clavis.Subject("Sistemas Digitais II", "56C1024");
        //System.out.println(db.deleteSubject(sub));
        //System.out.println(db.deleteAssociationBetweenSubjectAndClassroom(sub, cl.));
        //db.updateClassroom(cl.get(0));
        Clavis.Feature fea = new Clavis.Feature("teste","mk", 0);
        db.insertFeature(fea);
        db.associateFeatureWithMaterial(fea, mo);
        System.out.println(db.getMaterialsWithSpecificFeature(fea).get(0).getDescription());
        for (Clavis.Request r : db.getRequests(new TimeDate.Date(),new TimeDate.Date(16,6,2016))) {
            System.out.println(r.getPerson().getName());
        }
    }
}
