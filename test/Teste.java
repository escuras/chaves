
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        
       java.util.List<String> l= new java.util.ArrayList<>();
       java.util.Map<Object, Integer> m = new java.util.HashMap<>();
      
       m.put("a", 100);
       java.util.Stack<String> s = new java.util.Stack<>();
       s.push("Boommmmmmm");
       s.push("um");
       s.push("dois");
       s.push("tres");
       while (!s.isEmpty()) {
           System.out.println(s.peek());
           s.pop();
       }
       
       List<Set<List<Set<List<Set<Integer>>>>>> lo = new ArrayList<>();
       
               
    }
}
