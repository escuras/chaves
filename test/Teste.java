/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toze
 */
public class Teste {
    
    public static void main(String[] args) {
        TimeDate.Date dat = new TimeDate.Date();
        System.out.println(dat.getDayYear());
        TimeDate.Date dat2 = new TimeDate.Date(1,1,2016);
        System.out.println(dat2.isLeap(1980));System.out.println(dat.isLeap(1976));
        System.out.println(dat2.getDayYear());
        System.out.println(dat2.isBigger(dat));
        System.out.println(TimeDate.Date.numberOfDaysBetweenDates(dat2, dat));
        
        System.out.println(dat.dateBefore(366+365+365+365+366));
        DataBase.DataBase db = new DataBase.DataBase("jdbc:mysql://localhost:3306/clavis?autoReconnect=false&useSSL=false&user=root&password=sobral");
        System.out.println(db.getMaterial(283));
    }
}