/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorTest;

import dao.CountryDao;
import domain.City;
import domain.Country;
import domain.Department;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import simulation.CitySimulator;
import simulation.CountrySimulator;
import simulation.DepartmentSimulator;

/**
 *
 * @author macmini
 */
public class PerformaceMysqlTest {

    /**
    Time to set 10.000 resgisters
     * 
    
     * Running
    
     * BUILD SUCCESSFUL (total time: 9 seconds)
     * 
     */
    /***
     * 
     * 
     **/
    
    @Test
    public void multipleCountryInsert() {

        int size = 10;
        long startTime = 0;
        long endTime = 0;

        List<City> cities = CitySimulator.buildMultipleCity(size);
        List<Department> departments = DepartmentSimulator.buildMultipleDepartament(size);
        List<Country> countries = CountrySimulator.buildMultipleCountry(size);
        Country country = new Country();

        CountryDao countryDao = new CountryDao();

        try {
            startTime = System.nanoTime();
                                   
            for (int i = 0; i < size; i++) {
                country = countries.get(i);
                country.setCities(cities);
                country.setDepartments(departments);
                countryDao.createCountry(country);
            }
            
            endTime = System.nanoTime();

        } catch (Exception ex) {
            Logger.getLogger(PerformaceMysqlTest.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
      
        System.out.println(startTime - endTime);
        
    } 
}
