package daoTest;

import dao.CitizenDao;
import domain.Citizen;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.Before;
import simulation.CitizenSimulator;
import utils.MysqlConstants;
import static org.junit.Assert.*;

import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 *
 * @author
 */
public class CitizenDaoTest {

    protected CitizenDao citizenDao;
    protected List<Citizen> citizens;
    protected Citizen citizen;
    protected boolean success;

    @Before
    public void Before() {

        citizenDao = new CitizenDao(MysqlConstants.getShemaTest());
        citizen = new Citizen();
        citizens = new ArrayList<Citizen>();
        success = false;

    }
    
    @Test
    public void testShemaTotestConfig(){
        
        assertEquals(MysqlConstants.getShemaTest(),
               citizenDao.getCurrentShema() );
    }

    @Test
    public void testShemaByDefaultConfig(){
        CitizenDao dao = new CitizenDao();
        
        assertEquals(MysqlConstants.getShemaRelational() 
                ,dao.getCurrentShema());
    } 
        
    @Test
    public void testCreateCitizen() {

        citizen = CitizenSimulator.buildCitizen();
        success = citizenDao.createCitizen(citizen);
                
        assertTrue(success);       
    }

    @Test
    public void testDeleteCitizen() {

        success = citizenDao.deleteCitizen(citizen);
        assertTrue(success);        
    }

    
    /**
     *
     * En este metodo se verifica un requerimiento de valores no null,
     * derivado de la definicion (constraint not null en algunos de los campos
     * en el esquema de la base de datos)
     *  
     */
    @Test
    public void testListCitizen() {

        citizens = citizenDao.listCitizen();
        assertNotNull(citizens);

        int cnt = 0;
        for (int i = 0; i < citizens.size(); i++) {

            assertNotNull(citizens.get(i));

            cnt = cnt++;
        }

        assertEquals(citizens.size(), cnt);
    }

    @Test
    public void testFindCitizen() {

        Citizen found = null;

        found = citizenDao.findCitizen(citizen.getId());

        assertNotNull(found);

    }

    @Test
    public void testUpdateCitizen() {
        citizens = citizenDao.listCitizen();
        if (citizens.size() > 0) {
            for (int i = 0; i < citizens.size(); i++) {
                citizen = citizens.get(i);
                int id = citizen.getId();
                if (id != 0) {
                    citizen.setId(citizen.getId());
                    success = citizenDao.updateCitizen(citizen);
                }
            }
        }
        
        assertTrue(success);        
    }
    
    @Test
    public void truncateTableTest(){
        
        citizenDao.truncateTable();        
        citizens = citizenDao.listCitizen();        
        assertEquals(citizens.size(), 0);
        
    }
        
}
