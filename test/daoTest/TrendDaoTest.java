package daoTest;

import dao.TrendDao;
import domain.Trend;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import simulation.TrendSimulator;
import utils.MysqlConstants;

/**
 *
 * @author IEUser
 */
public class TrendDaoTest {

    protected int last_id;
    protected TrendDao trendDao;
    protected Trend trend;
    protected boolean flag;
    protected List<Trend> trends;

    @Before
    public void setUp() {

        /**
         * shema_test
         **/
        trendDao = new TrendDao(MysqlConstants.getShemaTest());
        trends = new ArrayList<Trend>();
        trend = new Trend();
        flag = false;
    }

    @Test
    public void testShemaByDefaultConfig() {
        TrendDao dao = new TrendDao();

        assertEquals(MysqlConstants.getShemaRelational(),
                dao.getCurrentShema());

    }
    
     @Test
    public void testShemaToTestConfig(){
        
        assertEquals(trendDao.getCurrentShema(), 
                MysqlConstants.getShemaTest());
    }

    /**
     * The current method test pseudo-update, on all objects obtain from query
     * (SELECT * [table name]) because reflection is not implements
     *
     */
    @Test
    public void testUpdateTrend() {
        trends = trendDao.listTrend();
        if (trends.size() > 0) {
            for (int i = 0; i < trends.size(); i++) {
                trend = trends.get(i);

                if (trend.getId() != 0) {
                    trend.setId(trend.getId());
                    trend.setIntervalo(trend.getIntervalo());
                    flag = trendDao.updateTrend(trend);
                }
            }
        }
        assertEquals(flag, true);
    }

    @Test
    public void testListTrend() {

        trends = trendDao.listTrend();
        assertNotNull(trends);

        int cnt = 0;
        for (int i = 0; i < trends.size(); i++) {

            assertNotNull(trends.get(i));

            cnt = cnt + 1;
        }

        assertEquals(trends.size(), cnt);
    }

    /**
     * @abstract
     * <p>
     * The origin method on DAO return an boolean value</p>
     *
     * Test of createTrend method, of class TrendDao.
     */
    @Test
    public void testCreateTrend() {

        trend = TrendSimulator.buildTrendObject();
        flag = trendDao.createTrend(trend);

        trend.setId(trendDao.getLastId());

        assertEquals(flag, true);
    }
           
    @Test
    public void testDeleteTrend() {
        /**
         * TODO Get the latest id set on insert routine exe before this
         */
        /* int id = 0  get id from database*/;

        trendDao.createTrend(TrendSimulator.buildTrendObject());

        int lst_id = trendDao.getLastId();
        trend.setId(lst_id);

        flag = trendDao.deleteTrend(trend);

        assertEquals(flag, true);
    }

    @Test
    public void testFindTrend() {

        trendDao.createTrend(TrendSimulator.buildTrendObject());
        int lst_id = trendDao.getLastId();
        trend.setId(lst_id);

        Trend found = null;
        found = trendDao.findTrendById(trend.getId());

        assertEquals(lst_id, found.getId());
        assertNotNull(found);/** TODO ... **/
    }
    
    @Test
    public void truncateTableTest(){
        
        trendDao.truncateTable();
        trends = trendDao.listTrend();
        
        assertEquals(trends.size(), 0);        
    }
    
}
