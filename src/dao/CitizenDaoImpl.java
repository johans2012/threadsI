package dao;

import java.sql.PreparedStatement;
import domain.Citizen;
import domain.Country;
import domain.Department;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.MysqlService;
import utils.MysqlConstants;

/**
 *
 * @author johans caicedo
 */
class CitizenDaoImpl {

     protected String CREATE_CITIZEN = "INSERT INTO citizen (edad, sexo,nombre, "
             + "empleo, salario, eps, compensacion) "
            + "VALUES (?,?,?,?,?,?,?)";
    protected String LIST_CITIZEN = "SELECT * FROM citizen";
    protected String UPDATE_CITIZEN = "UPDATE citizen set habitantes=? WHERE id=?";
    protected String DELETE_CITIZEN = "DELETE FROM citizen WHERE id=?";
    protected String FIND_CITIZEN = "SELECT * FROM citizen WHERE id=?";
    protected String TRUNCATE_TABLE = "TRUNCATE TABLE citizen";
    
    private Connection connection;       
    protected String shema;

    protected CitizenDaoImpl() {
        this.shema = MysqlConstants.getShemaRelational();
        connection = MysqlService.connToDatabase(shema);
    }

    public CitizenDaoImpl(String shema) {
        this.shema = shema;
        connection = MysqlService.connToDatabase(shema);
    }

    protected boolean createCitizen(Citizen citizen) {
        System.out.println("Insert citizen: "
                + CitizenDaoImpl.class.getSimpleName());

        int result = 0;
        boolean success = false;

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement(CREATE_CITIZEN);
            ps.setInt(1, citizen.getEdad());
            ps.setString(2, citizen.getSexo());
            ps.setString(3, citizen.getNombre());
            ps.setString(4, citizen.getEmpleo());
            ps.setInt(5, citizen.getSalario());
            ps.setString(6, citizen.getEps());
            ps.setString(7, citizen.getCompensacion());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return success;
    }

    protected List<Citizen> listCitizen() {
        System.out.println("List citizen: " + CitizenDaoImpl.class.getSimpleName());

        List<Citizen> listCitizen = new ArrayList<Citizen>();
        Citizen citizen = new Citizen();

        try {

            PreparedStatement ps = connection.prepareStatement(LIST_CITIZEN);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                citizen.setNombre(rs.getString(1));
                citizen.setCompensacion(rs.getString(2));

                listCitizen.add(citizen);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCitizen;
    }

    protected Citizen findCitizenById(int id) {
        System.out.println("Find citizen: " + CitizenDaoImpl.class.getSimpleName());

        Citizen citizen = new Citizen();
        try {

            PreparedStatement ps = connection.prepareStatement(FIND_CITIZEN);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                citizen.setNombre(rs.getString(1));
                citizen.setCompensacion(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return citizen;
    }

    protected boolean updateCitizen(Citizen citizen) {
        System.out.println("Update citizen: " + CitizenDaoImpl.class.getSimpleName());

        int result = 0;
        boolean success = false;

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement(UPDATE_CITIZEN);

            ps.setInt(1, 20);
            ps.setInt(2, 2);

            result = ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException sqle) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).log(Level.SEVERE, null, sqle);
        }

        return success;
    }

    protected boolean deleteCitizen(Citizen citizen) {
        System.out.println("Delete citizen: " + CitizenDaoImpl.class.getSimpleName());

        int result = 0;
        boolean success = false;

        try {

            PreparedStatement ps = null;
            ps = connection.prepareStatement(DELETE_CITIZEN);
            ps.setInt(1, citizen.getId());

            result = ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    protected Department getDepartmentByCitizen(int id) {

        Department department = null;
        String query = "SELECT department FROM citizen WHERE id=?";

        try {
        } catch (Exception ex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return department;
    }

    protected String getCountryByCitizen(int id) {

        String country = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = connection.prepareStatement("SELECT country FROM citizen WHERE id=?");
            rs = ps.executeQuery();

            if (rs.next()) {
                country = rs.getString("country");
            }

        } catch (Exception ex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return country;
    }

    protected Country getCountryByCitizen(Citizen citizen) {

        Country country = new Country();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = connection.prepareStatement("SELECT country FROM citizen WHERE citizen=?");
            ps.setObject(1, citizen);
            rs = ps.executeQuery();

            while (rs.next()) {
                country.setNombre(rs.getString("country"));
            }

        } catch (Exception ex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return country;
    }

    protected boolean insertCitizenObjTable(Citizen citizen) {

        String query = "INSERT INTO citizen (obj, id_gen) VALUES (?, ?)";
        PreparedStatement ps;
        int result = 0;
        boolean success = true;

        try {

            ps = connection.prepareStatement(query);
            ps.setObject(1, citizen);
            ps.setInt(2, citizen.getId());


            result = ps.executeUpdate();
            if (result == 1) {
                success = true;
            }

        } catch (SQLException sqlex) {
            Logger.getLogger(CitizenDaoImpl.class.getName()).
                    log(Level.SEVERE, null, sqlex);
        }

        return success;
    }

    protected void truncateTableCitizen() {
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
        CitizenDaoImpl daoImpl = new CitizenDaoImpl("simulator_shema");
        /*Citizen citizen;
        citizen = CitizenSimulator.buildCitizen();

        daoImpl.insertCitizenObjTable(citizen);
        daoImpl.createCitizen(citizen);
        daoImpl.truncateTableCitizen();*/
    }
}
