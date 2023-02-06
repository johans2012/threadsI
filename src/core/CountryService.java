package core;

import dao.CountryDao;
import directory.FilePointsReader;
import domain.Country;
import simulation.CountrySimulator;

/**
 *
 * @author johans caicedo
 */
public class CountryService extends Service {

    protected CountryDao countryDao;

    public CountryService() {
        countryDao = new CountryDao("relational_shema");
    }

    /**
     * Call when use functionality, access database.
     *
     * @param countryDao
     * @param country
     */
    public CountryService(CountryDao countryDao, Country country) {

        //obj.insertCountry(country);
        // System.err.println("" + countryDao.deleteCountry(country));

        //countryDao.updateCountry(country); 
        //obj.findCountry(country);
        System.out.println(countryDao.listCountry());
        // System.err.println(countryDao.getCitizenByCountry(country.getId()).toString());
    }

    /**
     * Call when use functionality, handler file.
     *
     * @param filePointsReader
     */
    public CountryService(FilePointsReader filePointsReader) {

    }

    /**
     * Call when use simulation, handle simulation.
     *
     * @param countrySimulator
     */
    public CountryService(CountrySimulator countrySimulator) {

        /*countrySimulator.multipleCountryInsert(100);*/
    }

    public static void main(String[] args) {

        /*Simulator test 
        CountrySimulator simulator = new CountrySimulator();
        CountryService cs = new CountryService(simulator);
        /*Dao test*/
        CountryDao countrydao = new CountryDao("relational_shema");
        Country country = new Country();
        country.setId(6);

        CountryService simulator = new CountryService(countrydao, country);
    }

}
