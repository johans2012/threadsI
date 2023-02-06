/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorTest;

import com.mysql.jdbc.Connection;
import dao.CityDao;
import domain.City;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import simulation.CitySimulator;

import utils.MysqlConstants;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.*;

/**
 *
 * @author IEUser
 */
public class CitySimulatorTest {

    @Mock
    protected Connection connection;
    protected CitySimulator citySimulator;
    protected CityDao dao;
    protected List<City> cities;

    @Before
    public void setup() {
        citySimulator = new CitySimulator(MysqlConstants.getShemaSimulator());

        initMocks(this);
        dao = mock(CityDao.class);
        //when(cityDao).thenReturn();
        dao = new CityDao(MysqlConstants.getShemaSimulator());
    }

    @Test
    public void testShemaSimulatorConfig() {
        assertEquals(MysqlConstants.getShemaSimulator(),
                dao.getCurrentShema());
    }

    @Test
    public void multipleCityInsertTest() {
        long end = 0;
        long start = 0;
        start = System.currentTimeMillis();

        int size = 10;
        long time = start - end;
        boolean success = false;

        //for( i< 4 loop){}
        success = citySimulator.multipleCityInsert(size);
        end = System.currentTimeMillis();

        System.err.println("Total time: " + (time));
        
        assertEquals(success, true);
    }

    @Test
    public void buildMultipleCityTest() {

        int size = 100;
        List<City> cities = CitySimulator.buildMultipleCity(size);

        assertEquals(cities.size(), size);
    }

    @Test
    public void buildCityTest() {
        City city = CitySimulator.buildCity();
        boolean success = false;

        if (city instanceof City) {
            success = true;
        }

        //TODO check the values on tables when create the object

        assertTrue(success);
        assertEquals(success, true);
    }

    @Test
    public void shemaTestEmptyRegisters() {
      
        dao.truncateTable();
        cities = dao.listCities();
        assertEquals(cities.size(), 0);
    }
        
}
