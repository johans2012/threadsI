package simulation;

import dao.CityDao;
import domain.City;
import domain.Trend;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johans Caicedo
 */
public class CitySimulator extends Simulator {

    protected static CityDao dao;
    protected static Connection connection;
    protected static PreparedStatement ps;

    public CitySimulator(String shema) {

        //cityDao = MysqlService.builDao("shema_simulator");
        //cityDao = new CityDao("simulator_shema");
        dao = new CityDao(shema);

        //connection = getConnection();
    }

    /**
     *
     * @param size
     * @return *
     */
    public static List<City> buildMultipleCity(int size) {

        List<City> cities = new ArrayList<City>();
        City city = null;

        for (int i = 0; i < size; i++) {
            city = buildCity();
            cities.add(city);
        }

        return cities;
    }

    /**
     * *
     * @return <h4>City</h4>
     * 
     *  
     * <h3>Nombre: Nombre de ciudad test</h3>
     * <h3>Location: Ubicacion de ciudad test: </h3>
     * <h3>Fecha: new Date() java.sql.Date  </h3>
     * <h3>Temperatura: 25  </h3>
     * <h3>Departamento: Departamento test </h3>
     * <h3>Trends: List<Trend> size(n=userinput)</h3>
     * <h3>Altitud: 120 </h3>
     * <h3>Area: 500000 </h3>
     * <h3>Habitantes: 2500000</h3> 
     *
     * @abstract this method return an city instance, The values by default set are:
     *
     */
    public static City buildCity() {
        List<Trend> trends = TrendSimulator.buildMultipleTrends(10);

        City city = new City();
        city.setNombre("Nombre de ciudad test: ");
        city.setLocation("Ubicacion de ciudad test: ");
        city.setFecha(new Date(10));
        city.setTemperatura(25);
        city.setDepartamento("Departamento test: ");
        city.setTrends(trends);
        city.setAltitud(120);
        city.setArea(500000);
        city.setHabitantes(2500000);

        City capital = new City();
        capital.setNombre("capital de: " + city.toString());
        capital.setLocation("Ubicacion de capital test: ");
        capital.setFecha(new Date(10));
        capital.setTemperatura(25);
        capital.setDepartamento("Departamento capital: ");
        capital.setTrends(trends);
        capital.setAltitud(120);
        capital.setArea(500000);
        capital.setHabitantes(2500000);

        city.setCapital(capital);
        capital.setCapital(capital); // The current object is the same capital, 
        //then is capital

        return city;
    }

    public static boolean multipleCityInsert(int size) {

        boolean success = false;
        City city = null;

        for (int i = 0; i < size; i++) {

            city = buildCity();
            city.setNombre("Nombre ciudad: " + i);
            city.setCity("departamento: " + i);

            success = dao.createCity(city);
        }

        return success;
    }

    public boolean multipleInsert(int size) {
        return CitySimulator.multipleCityInsert(size);
    }

    public static void main(String[] args) {

        CitySimulator simulator = new CitySimulator("simulator_shema");
        simulator.multipleInsert(10);
        //CitySimulator.multipleCityInsert(10);
        
    }
}
