
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
public class TestDateAfter {

    int vals = 365;
    TimeDate.Date dat = new TimeDate.Date(1, 10, 2016);

    @Test
    public void testDateAfter() {
        TimeDate.Date dat2 = new TimeDate.Date(1, 10, 2017);
        assertEquals(dat2.toString(), this.dat.dateAfter(vals).toString());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestDateAfter.class);
        result.getFailures().stream().forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println(result.wasSuccessful());
    }
}
