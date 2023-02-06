package dao;

import domain.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.MysqlService;
import lib.Util;
import simulation.CitySimulator;
import utils.MysqlConstants;

/**
 *
 * @author macmini
 */
class CityDaoImpl {

    protected String CREATE_CITY = "INSERT INTO ciudad (nombre, capital, "
            + "location, date, temperatura, departamento, trends, altitud, "
            + "area, habitantes) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected String LIST_CITY = "SELECT * FROM ciudad";
    protected String UPDATE_CITY = "UPDATE ciudad set habitantes=? WHERE id=?";
    protected String DELETE_CITY = "DELETE FROM ciudad WHERE id=?";
    protected String FIND_CITY = "SELECT * FROM ciudad WHERE id=?";
    protected String TRUNCATE_TABLE = "TRUNCATE TABLE ciudad";
    private Connection connection;
    String shema;

    protected CityDaoImpl() {
        /*
        TODO On void constructor connect to database by default
         */
        this.shema = MysqlConstants.getShemaRelational();
        connection = MysqlService.connToDatabase(shema);
    }

    /**
     * This method enable the test options
     *
     * @param enable
     */
    protected CityDaoImpl(boolean enable) {

        if (enable) {
            ciudades = CitySimulator.buildMultipleCity(1000);
        }

    }

    public CityDaoImpl(String shema) {

        this.shema = shema;
        connection = MysqlService.connToDatabase(shema);
    }

    public static void main(String[] args) {

        CityDaoImpl daoImpl = new CityDaoImpl("simulator_shema");
        //daoImpl.createCity(CitySimulator.buildCityObject());
        //daoImpl.deleteCity(city);
        //daoImpl.updateCity(city);
        //daoImpl.findCity(city);
        //daoImpl.listCities();
        //daoImpl.insertCityObjTable(CitySimulator.buildCityObject());
        daoImpl.truncateTableCity();
    }

    protected boolean createCity(City city) {
        System.out.println("Insert City: " + CityDaoImpl.class.getSimpleName());

        int result = 0;
        boolean success = false;

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement(CREATE_CITY);
            ps.setString(1, city.getNombre());
            ps.setObject(2, city.getCapital());
            ps.setString(3, city.getLocation());
            ps.setDate(4, city.getFecha());
            ps.setInt(5, city.getTemperatura());
            ps.setString(6, city.getDepartamento());
            ps.setObject(7, city.getTrends());
            ps.setInt(8, city.getAltitud());
            ps.setFloat(9, city.getArea());
            ps.setInt(10, city.getHabitantes());

            ps.executeUpdate();

            if (result == 0) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CityDaoImpl.class.getName()).log(Level.SEVERE, null,
                    ex);
        }

        return success;
    }

    protected List<City> listCities() {
        System.out.println("List City: " + CityDaoImpl.class.getSimpleName());
        List<City> cities = new ArrayList<City>();
        City city;
        PreparedStatement ps = null;
        ResultSet rs;

        try {

            ps = connection.prepareStatement(LIST_CITY);
            rs = ps.executeQuery();

            while (rs.next()) {
                city = new City();
                city.setNombre(rs.getString("nombre"));

                cities.add(city);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CityDaoImpl.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return cities;
    }

    protected City findCity(int id) {
        System.out.println("Find City: " + CityDaoImpl.class.getSimpleName());
        City city = null;
        PreparedStatement ps;
        ResultSet rs = null;

        try {

            ps = connection.prepareStatement(FIND_CITY);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                city = new City();
                city.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CityDaoImpl.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return city;
    }

    protected boolean updateCity(City city) {
        System.out.println("Update City: " + CityDaoImpl.class.getSimpleName());
        PreparedStatement ps;
        int result = 0;
        boolean success = false;

        try {

            ps = connection.prepareStatement(UPDATE_CITY);
            ps.setInt(1, city.getHabitantes());
            ps.setInt(2, city.getId());

            result = ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException sqle) {
            Logger.getLogger(CityDaoImpl.class.getName()).log(Level.SEVERE, null,
                    sqle);
        }

        return success;
    }

    protected boolean deleteCity(City city) {
        System.out.println("Delete City: " + CityDaoImpl.class.getSimpleName());

        PreparedStatement ps;
        int result = 0;
        boolean success = false;

        try {

            ps = connection.prepareStatement(DELETE_CITY);
            ps.setInt(1, city.getId());

            result = ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CityDaoImpl.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return success;
    }
    protected Connection conn;
    protected City ciudad;
    protected List<City> ciudades;
    List<City> cities;
    private static final String WRITE_CITY_SQL = "INSERT INTO city (object) VALUES (?)";

    protected void setCiudades(List<City> ciudades) {

        /**
         * *
         * Precondicion: - Read database registers if ( null ) - Ieer archivo -
         * Formatear vaIores - guardar vaIores
         *
         */
        this.ciudades = ciudades;
    }

    protected void loadCurrentLocation() {
    }

    protected void loadTemperatura() {
    }

    protected void loadCurrentDate() {
        DateFormat dform = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //Date obj = new Date();

        // return dform.format(dform.format(obj));
    }

    protected List<City> loadCityFromDateInterval(Date date) {

        /**
         *
         *
         * INSERT INTO `datamining`.`date` (`id` ,`fechaInicial`
         * ,`fechaFin`)VALUES (NULL , '2021-11-09', '2021-11-30');
         *
         * SELECT colum_name(s) FROM tabLe_name WHERE column_name BETWEEN values
         * AND value;
         *
         * SElECT () FROM TABLE_NAME WHERE date between ? and ?
         *
         * SElECT () FROM TABLE_NAME WHERE date between ? and ?
         *
         *
         *
         * SELECT * FROM `date` WHERE `fechaInicial` = '2021-11-09' AND
         * `fechaFin` = '2021-11-30' LIMIT 0 , 30
         *
         *
         *
         */
        Util.getCurrentDateToString();

        this.cities = null;

        String sql = "SELECT * FROM  date WHERE  `fechaInicial` =  '2021-11-09' AND  `fechaFin` =  '2021-11-30'";
        sql = "SELECT fechainicial, fechaFin FROM date where fechainicial between ? and ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
//            ps.setShort(1, Core.getCurrentDateToString());
//            ps.setShort(2, Core.getCurrentDateToString());
            while (rs.next()) {
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.print("Exception: " + ex);
        }
        return this.cities;
    }

    protected List<City> loadCitiesFromBaseAknowledge() {

        this.cities = null;
        List<City> citys;
        String sqI = "SELECT * FROM city";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sqI);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt(1));
                System.out.println("City: " + rs.getObject("object"));
            }
        } catch (SQLException sqle) {
            System.out.print(sqle.getCause());
        }
        return cities;
    }

    /**
     *
     * This method find the trends on database save
     *
     **
     */
    protected void loadTrendsByCity(City city) {
    }

    protected void loadTrensByDepartment(List<City> department) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM department";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("Id: " + rs.getInt(1));
                System.out.println("City: " + rs.getObject("name"));
                System.out.println("Ubicacion: " + rs.getString("ubicacion"));
                System.out.println("Ciudades: " + rs.getString("cities"));
                System.out.println("Tendencias: " + rs.getObject("trends"));
                System.out.println("Habitantes: " + rs.getInt("habitantes"));
            }

        } catch (Exception ex) {
            Logger.getLogger(CityDao.class.getName()).log(Level.SEVERE, null, ex.getCause());
        }
    }

    protected void createTrendByCity(City city) {
        String sql = "";
        PreparedStatement ps = null;

        try {
            sql = "INSERT INTO ciudad "
                    + "(nombre, capital, ubicacion, fecha, temperatura, departamento, trends, altitud, area, habitantes) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, city.getNombre());
            ps.setObject(2, city.getCapital());
            ps.setString(3, city.getLocation());
            ps.setDate(4, city.getFecha());
            ps.setInt(5, city.getTemperatura());
            ps.setString(6, city.getDepartamento());
            ps.setObject(7, city.getTrends());
            ps.setInt(8, city.getAltitud());
            ps.setFloat(9, city.getArea());
            ps.setInt(10, city.getHabitantes());
            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(CityDao.class.getName()).log(Level.SEVERE, null,
                    e.getErrorCode());
        }
    }

    protected boolean insertCityObjTable(City city) {

        PreparedStatement ps;
        String sql = "INSERT INTO city (object, id_gen) VALUES (?, ?) ";
        int result = 0;
        boolean success = false;

        try {

            ps = connection.prepareStatement(sql);
            ps.setObject(1, city);
            ps.setInt(2, city.getId());

            result = ps.executeUpdate();
            if (result == 1) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return success;
    }
    
    protected void truncateTableCity(){
        String query = TRUNCATE_TABLE;
        PreparedStatement ps;
        
        try{
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
            
            
        }catch(Exception ex){
             Logger.getLogger(CityDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
    }
}
