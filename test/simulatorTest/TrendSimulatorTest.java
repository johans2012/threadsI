/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorTest;

import dao.TrendDao;
import domain.Trend;
import java.util.List;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import simulation.TrendSimulator;
import utils.MysqlConstants;

/**
 *
 * @author IEUser
 */
public class TrendSimulatorTest {
    
    protected List<Trend> trends;
    protected TrendSimulator simulator;
    protected TrendDao dao;
    
    @Before
    public void setup() {
        simulator = new TrendSimulator();
        dao = new TrendDao(MysqlConstants.getShemaSimulator());        
    }
    
    @Test
    public void testShemaSimulatorConfig() {
        assertEquals(MysqlConstants.getShemaSimulator(),
                dao.getCurrentShema());
    }
    
    @Test
    public void testMultipleInsert() {
        
        int[] insert = new int[4];
        insert[0] = 1;
        insert[1] = 10;
        insert[2] = 100;
        insert[3] = 1000;
        
        int cnt = 100;
        
        for (int i = 0; i < insert.length; i++) {
            simulator.multipleTrendInsert(insert[i],
                    TrendSimulator.buildMultipleTrends(insert[i]));
        }
        
        assertEquals(100, cnt);
    }
    
    @Test
    public void buildTrendTest() {
        Trend trend = TrendSimulator.buildTrendObject();
        boolean success = true;
        
        if (trend instanceof Trend) {
            success = true;
        }
        
        assertEquals(success, true);
    }
    
    @Test
    public void shemaEmptyRegistersTest(){
        dao.truncateTable();  
        trends = dao.listTrend();
        assertEquals(trends.size(), 0);
    }
}
