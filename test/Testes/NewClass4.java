/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author toze
 */
public class NewClass4 {
    
public static void main(String[] args) throws IOException {
        
        File file = new File("test.pdf");
        Langs.Locale lingua = Langs.Locale.getLocale_pt_PT();
        FileIOAux.PrintAux print = new FileIOAux.PrintAux(file,lingua,null);
        print.print();
    
    }

}
