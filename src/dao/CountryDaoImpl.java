package dao;

import domain.City;
import domain.Country;
import domain.Department;
import domain.Trend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.MysqlService;
import utils.MysqlConstants;

/**
 *
 * @author macmini
 */
class CountryDaoImpl {

    protected String CREATE_COUNTRY = "INSERT INTO country (nombre, capital, "
            + "habitantes, trends, temperatura, area, altitud,"
            + " location) VALUES (?, ?, ?, ? ,? ,? ,?, ?)";
    protected String LIST_COUNTRY = "SELECT * FROM country";
    protected String FIND_COUNTRY = "SELECT * FROM country WHERE id=?";
    protected String UPDATE_COUNTRY = "UPDATE country set temperatura=? WHERE id=?";
    protected String DELETE_COUNTRY = "DELETE FROM country WHERE id=?";
    protected String TRUNCATE_TABLE = "TRUNCATE TABLE country";
    
    protected static int last_id;
    protected PreparedStatement ps = null;
    protected Connection connection = null;
    protected String shema;

    protected CountryDaoImpl() {
        this.shema = MysqlConstants.getShemaRelational();
        connection = MysqlService.connToDatabase(shema);
    }

    public CountryDaoImpl(String shema) {
        this.shema = shema;
        connection = MysqlService.connToDatabase(shema);
    }

    protected boolean createCountry(Country country) {
        System.out.println("Insert Country: "
                + CountryDaoImpl.class.getSimpleName());

        int result = 0;
        boolean success = false;

        if ("object_shema".equals(shema)) {
            insertCountryObjTable(country);
        }

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement(CREATE_COUNTRY,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, country.getNombre());
            ps.setString(2, country.getCapital());
            ps.setInt(3, country.getHabitantes());
            ps.setObject(4, country.getTrends());
            ps.setInt(5, country.getTemperatura());
            ps.setFloat(6, country.getArea());
            ps.setFloat(7, country.getAltitud());
            ps.setInt(8, country.getLocation());

            result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                last_id = rs.getInt(1);
                System.err.println("ID FROM CURRENT DATA INSERT:" + last_id);
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return success;
    }

    protected List<Country> listCountry() {
        System.out.println("List Country: " + CountryDaoImpl.class.getSimpleName());

        List<Country> countries = new ArrayList<Country>();
        try {

            PreparedStatement ps = connection.prepareStatement(LIST_COUNTRY);

            ResultSet rs = ps.executeQuery();
            Country country;

            while (rs.next()) {
                country = new Country();
                country.setId(rs.getInt(1));
                country.setNombre(rs.getString(2));
                countries.add(country);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return countries;
    }

    protected Country findCountryById(int id) {
        System.out.println("Find Country: " + CountryDaoImpl.class.getSimpleName());

        Country country = null;
        try {

            country = new Country();

            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM country WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                country.setNombre(rs.getString("nombre"));
                country.setId(rs.getInt(id));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return country;
    }

    protected boolean updateCountry(Country country) {
        System.out.println("Update Country: "
                + CountryDaoImpl.class.getSimpleName());

        int result = 0;
        boolean success = false;

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement(UPDATE_COUNTRY);

            ps.setInt(1, country.getTemperatura());
            ps.setInt(2, country.getId());

            result = ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException sqle) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE,
                    null, sqle);
        }
        return success;
    }

    protected boolean deleteCountry(int id) {
        System.out.println("Delete Country: "
                + CountryDaoImpl.class.getSimpleName());

        boolean success = false;
        int result = 0;

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement(DELETE_COUNTRY);
            ps.setInt(1, id);

            result = ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return success;
    }

    protected List<Department> getDepartmentsByCountry(int id) {

        List<Department> departments = new ArrayList<Department>();
        String query = "SELECT * FROM department WHERE country_id=?";
        Department department = new Department();

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            department.setName(rs.getString(1));
            department.setUbicacion(rs.getString(1));

            departments.add(department);

        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return departments;
    }

    protected List<City> getCitiesByCountry(int id) {

        City city = new City();
        List<City> cities = new ArrayList<City>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        String query = "";

        try {

            query = "SELECT cities FROM country WHERE id=?";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            ps.setInt(1, id);

            while (rs.next()) {
                city.setNombre(rs.getString(2));
                // TODO         city.setTrends(rs.getArray(4));
                city.setDepartamento(rs.getString(4));
                cities.add(1, city);
            }

        } catch (SQLException e) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE,
                    null, e);
        }

        return cities;
    }

    protected List<Integer> getCitizenByCountry(int id) {

        List<Integer> citizens = new ArrayList<Integer>();
        String query = "SELECT habitantes FROM country";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

                citizens.add(rs.getInt("habitantes"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return citizens;
    }

    protected List<Trend> getTrendsByCountry(int id) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Trend> trends = new ArrayList<Trend>();
        Trend trend = new Trend();
        String query = "SELECT trends FROM country WHERE id=?";

        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();

            trend.setTermino(rs.getInt(1));
            trend.setInicio(rs.getInt(2));
            trend.setUbicacion(rs.getString(1));

            trends.add(trend);

        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return trends;
    }

    protected boolean insertCountryObjTable(Country country) {

        int result = 0;
        boolean success = false;

        try {
            ps = connection.prepareStatement("INSERT INTO country (country, id_gen) VALUES (?, ?)");
            ps.setObject(1, country);
            ps.setObject(2, country.getId());

            result = ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return success;
    }

    protected void truncateTableCountry() {
        String query = TRUNCATE_TABLE;
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        CountryDao dao = new CountryDao("simulator_shema");
        //dao.createCountry(CountrySimulator.buildCountryObj());
        System.out.println(dao.listCountry());
        //System.out.println(dao.findCountryById(1));
        //dao.deleteCountry(2);       
        //dao.updateCountry(CountrySimulator.buildCountry());
    }
}
