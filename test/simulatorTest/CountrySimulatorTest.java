package simulatorTest;

import domain.Country;
import dao.CountryDao;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import simulation.CountrySimulator;
import utils.MysqlConstants;
import static org.junit.Assert.*;

/**
 *
 * @author macmini
 */
public class CountrySimulatorTest {

    protected List<Country> countries;
    protected CountrySimulator countySimulator;
    protected CountryDao dao;
    protected int[] insert = new int[4];
    long[] exe = new long[4];

    @Before
    public void setup() {
        insert[0] = 1;
        insert[1] = 10;
        insert[2] = 100;
        insert[3] = 1000;

        countySimulator = new CountrySimulator(MysqlConstants.getShemaSimulator());
        dao = new CountryDao(MysqlConstants.getShemaSimulator());
    }

    @Test
    public void testShemaSimulatorConfig() {

        assertEquals(MysqlConstants.getShemaSimulator(),
                dao.getCurrentShema());
    }

    @Test
    public void buildCountryTest() {

        Country country = CountrySimulator.buildCountry();
        boolean success = false;

        if (country instanceof Country) {
            success = true;
        }

        assertEquals(success, true);
    }

    @Test
    public void buildMultipleCountry() {

        int size = 100;
        List<Country> countryes = CountrySimulator.buildMultipleCountry(size);
        assertEquals(countryes.size(), size);
    }

    @Test
    public void shemaEmptyRegistersTest() {

        dao.truncateTable();
        countries = dao.listCountry();
        assertEquals(countries.size(), 0);
    }

    @Test
    public void multipleCountryInsertTest() {
        long end = 0;
        long start = 0;
        long time = 0;
        int size = 10;
        boolean success = false;
        
       
        for (int x = 0; x < insert.length; x++) {
            start = System.currentTimeMillis();
            success = countySimulator.multipleCountryInsert(insert[x]);
            end = System.currentTimeMillis();
            
            time = end - start;
            
            System.err.println("Total time" + (time));
            exe[x] = time;
        }
       
        time = end - start;
        
       // System.err.println("Total time" + (time));
        assertEquals(success, true);
    }
    
    @Test
    public void timeInsertExecutionTest(){
        
        /**         
            time 1:  47
            time 2:  156
            time 3:  461
            time 4:  4150

            time 1: 53  6  
            time 2: 152 4
            time 3: 417 44
            time 4: 4239 89

            time 1: 49 4
            time 2: 160 8
            time 3: 481 64
            time 4: 4258 19

            time 1: 58
            time 2: 142
            time 3: 410
            time 4: 3871

            time 1: 57
            time 2: 145
            time 3: 436
            time 4: 3871
         
         **/
        
        long[] values = new long[4];
        values[0] = 58;
        values[1] = 182;
        values[2] = 435;
        values[3] = 3671;
        
        for (int i = 0; i < insert.length; i++) {
            assertEquals(exe[i], values[i]);                        
        }
    } 
}
