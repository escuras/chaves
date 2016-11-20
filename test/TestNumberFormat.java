
import static junit.framework.Assert.assertEquals;
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
public class TestNumberFormat {
    String t = "0k8";
    TimeDate.Time time;
    
    @Test (expected = NumberFormatException.class)
    public void testTimerNumber(){
        int num = Integer.parseInt(t);
        time = new TimeDate.Time(23, 8, num);
        assertEquals("23:08:08", time.toString());
    }
    
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestNumberFormat.class);
        result.getFailures().stream().forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println(result.wasSuccessful());
    } 
}
