
import java.util.Arrays;
import java.util.Collection;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author toze
 */

@RunWith(Parameterized.class)
public class TestDateBefore {
    
    TimeDate.Date dat = new TimeDate.Date(1, 10, 2016);

    @Parameters
    public static Collection<Object[]> dsata() {
        return Arrays.asList(new Object[][] {
                {366, "01/10/2015"},
                {731, "01/10/2014"},
                {16802, "01/10/1970"}
           });
    }

    @Parameter// por defeito é 0, mas pode indicar-se value = 0
    // Nao pode ser privado
    public int input;

    @Parameter(value = 1)
    // Não pode ser privado
    public String esperado;
    
    @Test
    public void testDateBefore() {
        TimeDate.Date dat2 = new TimeDate.Date(1, 10, 2015);
        assertEquals(esperado, this.dat.dateBefore(input).toString());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestDateBefore.class);
        result.getFailures().stream().forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println(result.wasSuccessful());
    }
}
