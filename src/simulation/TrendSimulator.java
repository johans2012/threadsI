package simulation;

import dao.TrendDao;
import domain.City;
import domain.Department;
import domain.Trend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.MysqlService;

/**
 *
 * @author IEUser
 */
public class TrendSimulator {

    protected MysqlService mysqlService;
    protected Connection connection;

    public TrendSimulator() {
        connection = MysqlService.connToDatabase("simulator_shema");
    }

    public void multipleTrendInsert(int cnt, List<Trend> trends) {
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Trend (trend) values(?)");
            Trend trend;

            for (int i = 0; i <= cnt; i++) {

                trend = trends.get(i);

                ps.setObject(1, trend);
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {

            Logger.getLogger(TrendDao.class.getName()).log(Level.SEVERE, null,
                    sqle);
        }
    }

    /**
     * **
     *
     *
     * @param size
     * @return List<Trend>
     *
     */
    public static List<Trend> buildMultipleTrends(int size) {

        List<Trend> trends = new ArrayList<Trend>();
        Trend trend = null;

        /**
         * TODO 
            - Define  The best format to use, for increase the performance 
                      processing.
            Format: JSON, XML, Plain text.
        
         * Read file: 
         *  Tentative processing:
         *      _ Find name 
         *        scan properties 
         *  Tentative format:
         *   
         *  
         * 
         **/
        
        for (int i = 0; i <= size; i++) {
            trend = buildTrendObject();
            trend.setBusqueda(trend.getBusqueda() + i);
            /**
             trend.setIncXdia(int);
             trend.setTermino(int);
             trend.setUbicacion(String); 
             **/
            
            trends.add(trend);
        }

        return trends;
    }

    /**
     * *
     * @return <h4>Trend</h4>
     *
     *
     * <h3>Ubicacion tendencia: ubicacion test</h3>
     * <h3>Inicio: 1</h3>
     * <h3>Termino: 1<h3>
     * <h3>Intervalo: 1</h3>
     * <h3>Busqueda: Busqueda test</h3>
     * <h3>Categoria: 0 </h3>
     * <h3>Tipo de busqueda: Busqueda test</h3>
     * <h3>Incremento por dia: 1</h3>
     * <h3>Incremento por semana: 1</h3>
     * <h3>Intervalo: 1</h3>
     *
     * @abstract
     * <p>
     * This method return an Trend instance, The values by default set are:</p>
     *
     */
    public static Trend buildTrendObject() {
                                
        /*** TODO **/            
        List<City> cities = new ArrayList<City>();
        
        City city = new City();
        city.setNombre("San Andres");
        city.setArea(1000000);
        city.setDepartamento("Departamento san andres");
        city.setTemperatura(30);
        
        cities.add(city);
        
        Department department = new Department();
        department.setArea(5000000);
        department.setName("Nombre departamento de san Andres");
        department.setTemperatura(30);
        department.setCities(cities);
                        
        Trend trend = new Trend();
        trend.setUbicacion("Ubicacion test");
        trend.setInicio(1);
        trend.setTermino(1);
        trend.setIntervalo(1);
        trend.setBusqueda("Busqueda test");
        trend.setCategoria(0);
        trend.setTipoBusqueda(0);
        trend.setIncXdia(1);
        trend.setIncXSemana(1);
        trend.setIntervalo(1);

        return trend;
    }

    public Trend buildTrendObject(String ubicacion, int inicio, int finalizacion, int intervalo,
            int categoria, int busqueda, String tipoBusqueda, int incXdia, int incXSemana, int incXintervalo) {

        Trend trend = new Trend();
        
        trend.setUbicacion(ubicacion);
        trend.setInicio(inicio);
        trend.setTermino(busqueda);
        trend.setIntervalo(intervalo);
        trend.setBusqueda(trend.getTipoBusqueda(busqueda));
        trend.setCategoria(0);
        trend.setTipoBusqueda(0);
        trend.setIncXdia(1);
        trend.setIncXSemana(1);
        trend.setIntervalo(1);

        return trend;
    }
}
