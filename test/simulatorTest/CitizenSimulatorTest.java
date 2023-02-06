/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorTest;

import java.util.List;
import domain.Citizen;
import dao.CitizenDao;
import java.util.ArrayList;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import simulation.CitizenSimulator;
import utils.MysqlConstants;
import static org.junit.Assert.*;

/**
 *
 * @author macmini
 */
public class CitizenSimulatorTest {

    protected CitizenDao dao;
    protected List<Citizen> citizens;

    @Before
    public void setup() {
        dao = new CitizenDao(MysqlConstants.getShemaSimulator());
    }

    @Test
    public void testShemaSimulatorConfig() {

        assertEquals(MysqlConstants.getShemaSimulator(),
                dao.getCurrentShema());
    }

    @Test
    public void buildCitizenTest() {

        Citizen citizen = CitizenSimulator.buildCitizen();
        boolean success = false;

        if (citizen instanceof Citizen) {

            success = true;
        }

        assertTrue(success);
        
    }

    @Test
    public void buildMultipleCitizenTest() {
        assertNull(citizens);
        int size = 10;
        citizens = new ArrayList<Citizen>();
        for (int i = 0; i < size; i++) {

            citizens.add(CitizenSimulator.buildCitizen());
        }
        assertEquals(citizens.size(), size);
    }
                   
    @Test
    public void shemaEmptyRegistersTest(){
        dao.truncateTable();
        citizens = dao.listCitizen();
        assertEquals(citizens.size(), 0);
    }

    @Test
    public void multipleCitizenInsertTest() {
        int size = 10;
        for (int i = 0; i < size; i++) {
            dao.createCitizen(CitizenSimulator.buildCitizen());
        }
        
        
    }
        
}
