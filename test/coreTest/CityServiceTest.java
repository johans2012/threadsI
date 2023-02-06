/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreTest;

import static junit.framework.Assert.assertNotNull;
import dao.CityDao;
import dao.CityDao;
import org.junit.Before;
import org.junit.Test;

public class CityServiceTest {

    protected CityDao cityDao;

    @Before
    protected void setUp() {
        cityDao = new CityDao("test_shema");
    }

    @Test
    protected void chechLengthRegisters() {
        assertNotNull(this.cityDao);
    }

    @Test
    protected void createCityTest() {

    }

    @Test
    protected void listCityTest() {
         
    }
}
