
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
public class TestDinamicHolidays {
    @Test
    public void TestHoliday(){
        TimeDate.Holiday ho = TimeDate.Holiday.getEaster(2056);
        TimeDate.Holiday hol = new TimeDate.Holiday(2, 4);
        assertEquals(hol.toString(), ho.toString());
        System.out.println(hol.toString());
        System.out.println(ho.toString());
    }
    
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestDinamicHolidays.class);
        result.getFailures().stream().forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println(result.wasSuccessful());
    } 
    
}
