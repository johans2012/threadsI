package daoTest;

import dao.CityDao;
import domain.City;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import simulation.CitySimulator;
import utils.MysqlConstants;

/**
 *
 * @author IEUser
 */
public class CityDaoTest {

    protected CityDao cityDao;
    protected List<City> cities;
    protected City city;
    protected boolean success;

    
    /**
     *
     * The constructor set is test
     * 
     ***/
    
    @Before
    public void setup() {
        cities = new ArrayList<City>();
        cityDao = new CityDao(MysqlConstants.getShemaTest());
        city = new City();
        success = false;
    }
    
    @Test
    public void testShemaTotestConfig(){
        
        assertEquals(MysqlConstants.getShemaTest(),
               cityDao.getCurrentShema() );
    }
    
    
    @Test
    public void testShemaByDefaultConfig(){
        CityDao dao = new CityDao();
        
        assertEquals(MysqlConstants.getShemaRelational(),
               dao.getCurrentShema() );
    }

    @Test
    public void testCreateCity() {
        city = CitySimulator.buildCity();
        success = cityDao.createCity(city);

        assertEquals(success, true);
    }

    @Test
    public void testFindCity() {
        City found = null;

        found = cityDao.findCity(7);

        assertNotNull(found);
    }

    @Test
    public void testUpdateCity() {
        cities = cityDao.listCities();

        if (cities.size() > 0) {
            for (int i = 0; i < cities.size(); i++) {
                city = cities.get(i);
                int id = city.getId();
                if (id != 0) {
                    city.setId(city.getId());
                    success = cityDao.updateCity(city);
                }
            }
        }

        assertEquals(success, true);
    }

    @Test
    public void testDeleteCity() {
        
        
        success = cityDao.deleteCity(city);
        assertEquals(success, true);
    }

    @Test
    public void testListCity() {

        cities = cityDao.listCities();
        assertNotNull(cities);

        int cnt = 0;
        for (int i = 0; i < cities.size(); i++) {

            assertNotNull(cities.get(i));

            cnt = cnt + 1;
        }

        assertEquals(cities.size(), cnt);
    }
    
    
    
    @Test
    public void truncateTableTest(){
        
        cityDao.truncateTable();
        cities = new ArrayList<City>();
        
        assertEquals(cities.size(), 0);
    }

}
