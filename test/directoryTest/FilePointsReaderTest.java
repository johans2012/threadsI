/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package directoryTest;

import directory.FilePointsReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author macmini
 */
public class FilePointsReaderTest {
    
     
    
    public FilePointsReaderTest() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        
    }
    
    @Test
    public void loadFileTest(){
        String path = "/Users/macmini/Downloads/CubeTrendsUtil";
        
        FilePointsReader.loadFile("", "");
        assertEquals("", "");
    }
}
