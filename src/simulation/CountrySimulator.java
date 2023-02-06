package simulation;

import dao.CityDao;
import dao.CountryDao;
import dao.DepartmentDao;
import domain.City;
import domain.Country;
import domain.Department;
import domain.Trend;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MysqlConstants;

/*
 *
 * @author macmini
 */
public class CountrySimulator {

    protected static List<City> cities;
    protected static List<Department> departments;
    protected static List<Trend> trends;
    protected CountryDao countryDao;

    CountrySimulator() {

        /**
         * simulator_shema by default is use.
         **/
        countryDao = new CountryDao(MysqlConstants.getShemaSimulator());
    }

    protected void loadDepartments() {
        DepartmentDao dao = new DepartmentDao();
        departments = dao.listDepartments();
    }

    protected void loadCities() {
        CityDao dao = new CityDao();
        //cities = dao.listCities();
        cities = CitySimulator.buildMultipleCity(100);
    }

    protected static void initTrends() {
        trends = TrendSimulator.buildMultipleTrends(100);
    }

    public CountrySimulator(String shema) {
        countryDao = new CountryDao(shema);
    }

    public boolean multipleCountryInsert(int size) {

        boolean success = false;
        Country country = null;

        for (int i = 0; i < size; i++) {

            country = buildCountry();

            success = countryDao.createCountry(country);
        }

        return success;
    }

    public static List<Country> buildMultipleCountry(int length) {

        Country country = null;
        List<Country> countries = new ArrayList<Country>();
        initTrends();

        try {

            for (int i = 0; i < length; i++) {

                country = buildCountry();
                country.setNombre("Nombre del pais: " + i);

                country.setTrends(trends);

                countries.add(country);
            }

        } catch (Exception ex) {
            Logger.getLogger(CountrySimulator.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return countries;
    }

    public static Country buildCountry() {

        initTrends();

        Country country = new Country();
        country.setId(1);
        country.setAltitud(15000);
        country.setArea(1000000);
        country.setCapital("Capital de: " + country.hashCode());
        country.setTemperatura(20);
        country.setLocation(1);
        country.setTrends(trends);
        country.setNombre("Nombre de pais: ");
        country.setHabitantes(1000000);
        country.setDepartments(departments);
        country.setCities(cities);

        return country;
    }

    public static void main(String args[]) {
        CountrySimulator simulator = new CountrySimulator();
        simulator.multipleCountryInsert(1000);
    }
}
