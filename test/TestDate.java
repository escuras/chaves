
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toze
 */
public class TestDate {
    
    TimeDate.Date data = new TimeDate.Date();
    
    @Test
    public void testeDatetoString(){
        String data = "08/10/2016";
        assertEquals(data,this.data.toString());
    }
    
}
