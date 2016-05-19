package Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toze
 */
public class TreatText {
    
    public static String getNumberfromString(String string){
        char[] valor = string.toCharArray();
        String fina = "";
        int k = 0;
        Pattern p = Pattern.compile("[0-9.,]");
        System.out.println("qulquer coisa");
        for (int i=0; i< string.length(); i++){
            String aux =  string.substring(i,i+1);
            Matcher m = p.matcher(aux);
            if (m.find()) {
                fina += aux;
            } 
        }
        System.out.println(fina);
        return fina;
    }
    
    
}
