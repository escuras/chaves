
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toze
 */
public class TestDataRunner {
    
    public static void main (String[] args) {
        Result result = JUnitCore.runClasses(TestDate.class);
        for (Failure f : result.getFailures()) {
            System.out.println(f.toString());
        }
        
        System.out.println(result.wasSuccessful());
    }
}
