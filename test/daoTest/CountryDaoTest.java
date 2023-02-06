package daoTest;

import dao.CountryDao;
import domain.Country;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import simulation.CountrySimulator;
import utils.MysqlConstants;

/**
 *
 * @author IEUser
 */
public class CountryDaoTest {

    protected List<Country> countries;
    protected CountryDao countryDao;
    protected Country country;
    protected boolean flag;

    @Before
    public void setUp() {
        countryDao = new CountryDao(MysqlConstants.getShemaTest());
        country = new Country();
        countries = new ArrayList<Country>();
    }

    @Test
    public void testShemaByDefaultConfig() {
        CountryDao dao = new CountryDao();

        assertEquals(MysqlConstants.getShemaRelational(),
                dao.getCurrentShema());
    }

    @Test
    public void testShemaToTestConfig() {
        //TODO  get config from file or database

        assertEquals(countryDao.getCurrentShema(),
                MysqlConstants.getShemaTest());       
    }

    @Test
    public void testCreateCountry() {

        country = CountrySimulator.buildCountry();
        flag = countryDao.createCountry(country);

        assertEquals(flag, true);
    }

    @Test
    public void testDeleteCountry() {

        countryDao.createCountry(CountrySimulator.buildCountry());

        int lst_id = countryDao.getLstId();

        flag = countryDao.deleteCountry(lst_id);

        assertEquals(flag, true);
    }

    @Test
    public void testListCountry() {

        countries = countryDao.listCountry();
        assertNotNull(countries);

        int cnt = 0;
        for (int i = 0; i < countries.size(); i++) {

            assertNotNull(countries.get(i));
            /**This assert check registers don't empty**/
            cnt = cnt + 1;
        }

        assertEquals(countries.size(), cnt);
    }

    @Test
    public void testFindCountry() {

        countryDao.createCountry(CountrySimulator.buildCountry());
        int lst_id = countryDao.getLstId();
     

        Country found = null;
        found = countryDao.findCountryById(lst_id);

        assertEquals(lst_id, found.getId());
        assertNotNull(found);
    }

    @Test
    public void testUpdateCountry() {

        countries = countryDao.listCountry();

        if (countries.size() > 0) {
            for (int i = 0; i < countries.size(); i++) {
                country = countries.get(i);
                int id = country.getId();
                if (id != 0) {
                    country.setId(country.getId());
                    flag = countryDao.updateCountry(country);
                }
            }
        }

        assertEquals(flag, true);
    }
    
    @Test
    public void truncateTableTest(){
        
        countryDao.truncateTable();
        countries = countryDao.listCountry();
        
        assertEquals(countries.size(), 0);
        
    }
}
