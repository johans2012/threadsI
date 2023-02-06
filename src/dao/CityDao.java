package dao;

import domain.City;
import java.util.List;

/**
 *
 * @author Johans Caicedo
 */
public class CityDao {

    protected CityDaoImpl cityDaoImpl;

    public String getCurrentShema() {
        String crrntShema = "";

        try {
            crrntShema = cityDaoImpl.shema;
        } catch (Exception ex) {
        }
        return crrntShema;
    }

    public CityDao(String shema) {
        cityDaoImpl = new CityDaoImpl(shema);
    }

    public CityDao() {
        /** relational_shema is set by default**/
        cityDaoImpl = new CityDaoImpl();
    }

    public boolean createCity(City c) {
        return cityDaoImpl.createCity(c);
    }

    public List<City> listCities() {
        return cityDaoImpl.listCities();
    }

    public City findCity(int id) {
        return cityDaoImpl.findCity(id);
    }

    public boolean updateCity(City c) {
        return cityDaoImpl.updateCity(c);
    }

    public boolean deleteCity(City c) {
        return cityDaoImpl.deleteCity(c);
    }
    
    public void truncateTable(){
        cityDaoImpl.truncateTableCity();
    }
}
