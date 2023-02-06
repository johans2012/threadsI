package dao;


import domain.City;
import domain.Country;
import domain.Department;
import domain.Trend;
import java.util.List;

/**
 *
 * @author Johans Caicedo
 */


public class CountryDao {

    /**
     *
     */
    protected CountryDaoImpl countryDaoImpl;
        
    public String getCurrentShema(){
        String crrntShema = "";
        
        try{
            crrntShema = countryDaoImpl.shema;
        }catch(Exception ex){       
            
        }
        return crrntShema;
    }
    
    public CountryDao(String shema) {
        countryDaoImpl = new CountryDaoImpl(shema);
    }
    
    public CountryDao(){
        countryDaoImpl = new CountryDaoImpl();
    }

    public boolean createCountry(Country c) {
        return countryDaoImpl.createCountry(c);
    }

    public List<Country> listCountry() {
        return countryDaoImpl.listCountry();
    }

    public Country findCountryById(int id) {
        return countryDaoImpl.findCountryById(id);
    }

    public boolean updateCountry(Country c) {
        return countryDaoImpl.updateCountry(c);
    }

    public boolean deleteCountry(int id) {
        return countryDaoImpl.deleteCountry(id);
    }

    public List<Department> getDepartmentsByCountry(int id) {
        return countryDaoImpl.getDepartmentsByCountry(id);
    }

    public List<City> getCitiesByCountry(int id) {
        return countryDaoImpl.getCitiesByCountry(id);
    }

    public List<Integer> getCitizenByCountry(int id) {
        return countryDaoImpl.getCitizenByCountry(id);
    }

    public List<Trend> getTrendsByCountry(int id) {
        return countryDaoImpl.getTrendsByCountry(id);
    }
    
    public int getLstId(){
        return CountryDaoImpl.last_id;
    }
    
    public void truncateTable(){
        countryDaoImpl.truncateTableCountry();
    }
}
