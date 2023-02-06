package dao;

import domain.Citizen;
import domain.Department;
import domain.Trend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.MysqlService;
import simulation.DepartmentSimulator;
import utils.MysqlConstants;

/**
 *
 * @author macmini
 */
class DepartmentDaoImpl {

    protected String CREATE_DEPARTMENT = "INSERT INTO department (name, ubicacion,"
            + " cities, trends, habitantes, capital) VALUES (?, ?, ?, ? ,?, ?)";
    protected String LIST_DEPARTMENT = "SELECT * FROM department";
    protected String FIND_DEPARTMENT = "SELECT * FROM department WHERE id=?";
    protected String UPDATE_DEPARTMENT = "UPDATE department set name=?, ubicacion=?, habitantes=? WHERE id=?";
    protected String DELETE_DEPARTMENT = "DELETE FROM department WHERE id=?";
    protected String TRUNCATE_TABLE = "TRUNCATE TABLE department";
    protected Connection connection;
    String shema;

    protected DepartmentDaoImpl() {
        this.shema = MysqlConstants.getShemaRelational();
        connection = MysqlService.connToDatabase(shema);
    }

    public DepartmentDaoImpl(String shema) {

        this.shema = shema;
        connection = MysqlService.connToDatabase(shema);
    }

    public static void main(String[] args) {
        Department department = DepartmentSimulator.buildDepartment();
//        department.setId(1);
//        department.setName("Ubicacion  ");
//        department.setHabitantes(3500000);
//        department.setUbicacion("ubicacion de los chismes");
//        department.setCapital("capital creater");

        //DepartmentDao departmentService = new DepartmentDao("relational_shema");
        //departmentService.createTrendByDepartment(department);
        //departmentService.createDepartment(department);
        //DepartmentDaoImpl.multipleDepartmentInsert(100, department);
        DepartmentDaoImpl dao = new DepartmentDaoImpl();

        //DepartmentSimulator departmentSimulator = 
        //new DepartmentSimulator("relational_shema");
        //departmentSimulator.multipleDepartmentInsert(100, department);
        dao.createDepartment(department);
        //dao.deleteDepartment(department);
        //dao.updateDepartment(department);
        //dao.getCapitalByDepartment(1);
        //dao.listDepartments();
        //dao.insertDepartmentObjTable(department);
    }

    /**
     * @param department
     *
     */
    protected boolean createDepartment(Department department) {
        System.out.println("Insert department: "
                + CitizenDaoImpl.class.getSimpleName());

        int result = 0;
        boolean success = false;

        if ("object_shema".equals(shema)) {

            insertDepartmentObjTable(department);
        }

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement(CREATE_DEPARTMENT);

            ps.setString(1, department.getName());
            ps.setString(2, department.getUbicacion());
            ps.setObject(3, department.getCities());
            ps.setObject(4, department.getTrends());
            ps.setInt(5, department.getHabitantes());
            ps.setString(6, department.getCapital());

            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return success;
    }

    /**
     * @param id
     * @return List<Department>
     */
    protected List<Department> listDepartments() {
        System.out.println("List department: "
                + DepartmentDaoImpl.class.getSimpleName());

        List<Department> departments = new ArrayList<Department>();
        Department department = new Department();

        try {

            PreparedStatement ps = connection.prepareStatement(LIST_DEPARTMENT);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                department.setName(rs.getString(1));
                department.setUbicacion(rs.getString(1));
                department.setHabitantes(1000000);

                System.out.println("Id: " + rs.getInt(1));
                System.out.println("Nombre: " + rs.getString(2));
                System.out.println("Ubicacion: " + rs.getString(3));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return departments;
    }

    protected Department getDepartmentById(int id) {

        System.out.println("Find department: "
                + DepartmentDaoImpl.class.getSimpleName());

        Department department = new Department();

        try {

            PreparedStatement ps = connection.prepareStatement(FIND_DEPARTMENT);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("Edad: " + rs.getInt(2));
                department.setName(rs.getString("name"));
                department.setUbicacion(rs.getString("ubicacion"));
                department.setHabitantes(rs.getInt("habitantes"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);

        }

        return department;
    }

    /**
     *
     * @param department
     */
    protected boolean updateDepartment(Department department) {
        System.out.println("Update department: " + DepartmentDaoImpl.class.getSimpleName());
        PreparedStatement ps;

        int result = 0;
        boolean success = false;
        try {

            ps = connection.prepareStatement(UPDATE_DEPARTMENT);

            ps.setString(1, department.getName());
            ps.setString(2, department.getUbicacion());
            ps.setInt(3, department.getHabitantes());
            ps.setInt(4, department.getId());

            ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException sqle) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, sqle);
        }

        return success;
    }

    /**
     *
     * @param department
     *
     */
    protected boolean deleteDepartment(Department department) {
        System.out.println("Delete Department: " + DepartmentDaoImpl.class.getSimpleName());

        boolean success = false;
        int result = 0;

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement("DELETE FROM department WHERE id=?");
            ps.setInt(1, department.getId());

            result = ps.executeUpdate();

            if (result == 0) {
                System.out.println("Register is empty");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    /**
     * @param id
     *
     */
    protected List<Citizen> getCitizenByDepartment(int id) {
        System.out.println("List department: "
                + CitizenDaoImpl.class.getSimpleName());

        List<Citizen> citizenList = new ArrayList<Citizen>();
        Citizen citizen = null;

        try {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM department WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            citizen = new Citizen();

            while (rs.next()) {
                citizen.setNombre(rs.getString(3));
                citizen.setEmpleo(rs.getString(4));

                citizenList.add(citizen);
                System.out.println("Edad: " + rs.getInt(2));
                System.out.println("Nombre: " + rs.getString(4));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return citizenList;
    }

    /**
     * @param id
     * @return String
     */
    protected String getCapitalByDepartment(int id) {

        ResultSet resultSet = null;
        String capital = "default-value-set";

        String query = "SELECT capital FROM department WHERE id=?";

        try {

            PreparedStatement ps = connection.prepareStatement("SELECT capital FROM department WHERE id=?");
            ps.setInt(1, id);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                System.out.println("--->" + resultSet.getString(1));
                capital = resultSet.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return capital;
    }

    protected List<Trend> getTrendsByDepartment(int id) {

        List<Trend> trends = new ArrayList<Trend>();
        String query = "";
        ResultSet rs = null;

        try {

            query = "SELECT trend FROM department WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            Trend trend;
            while (rs.next()) {
                trend = new Trend();
                trend.setBusqueda(rs.getString("busqueda"));
                trend.setUbicacion(rs.getString("ubicacion"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return trends;
    }

    protected boolean insertDepartmentObjTable(Department department) {

        PreparedStatement ps;
        String query = "INSERT INTO department (obj, id_gen) VALUES (?, ?)";
        int result = 0;
        boolean success = false;

        try {

            ps = connection.prepareStatement(query);
            ps.setObject(1, department);
            ps.setInt(2, department.getId());
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

    protected void truncateTable() {
        String query = TRUNCATE_TABLE;
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    /**
     *
     *
     *
     * //departmentService.multipleDepartmentInsert(100, "amazonas");
     * //departmentService.loadDepartments(); // List<Object> departments =
     * departmentService.getDepartments(); //
     * System.out.println(departments.toString()); // // List< Object>
     * cities = new ArrayList<Object>(); // cities =
     * departmentService.listDepartments(); // for (int i = 0; i <
     * cities.size(); i++) { // City object = (City) cities.get(i);
     * System.out.println(object.toString());
     */
    /**
     * * }
     *
     * SQL query definition:
     *
     * INSERT INTO departament (name, ubicacion, cities, trends, habitantes) " +
     * " VALUES (?,?,?,?,?)
     *
     * "DELETE FROM `ciudad` WHERE `ciudad`.`id` = 4"?
     *
     *
     *
     * protected static String INSERT_DEPARTMENT = "INSERT INTO" +
     * Contants.DEPARTMENT_TABlE_NAME + "(name, ubicacion, cities, trends,
     * habitantes) VALUES (?,?,?,?,?)"; protected static String
     * DELETE_DEPARTMENT = "DELETE FROM" + Contants.DEPARTMENT_TABlE_NAME +
     * "WHERE" + "id=?"; protected static String UPDATE_DEPARTMENT = "";
     * protected static String FIND_DEPARTMENT = ""; protected static String
     * LIST_DEPARTMENT = ""; protected static final String
     * SQL_DESERIALIZE_OBJECT = "SELECT object FROM department WHERE id=?";
     * protected static final String SELECT_DEPARTMENTS = "SELECT * FROM
     * department"; protected static final String SELECT_CITIES = "SELECT * FROM
     * cities WHERE department =?"; protected static String sql; protected
     * ResultSet rs; protected PreparedStatement pstmtStatic; protected
     * List<Object> departments; protected static List<Object>
     * dptos_stic_lst; protected static Connection conn; protected MysqlService
     * mysqlService;
     *
     * public void createTrendByDepartment(Department department) {
     *
     * try {
     *
     * //PreparedStatement pstmtStatic = null; pstmtStatic =
     * conn.prepareStatement("INSERT INTO departament (name, ubicacion, cities,
     * trends, habitantes) " + "VALUES (?,?,?,?,?)");
     *
     * pstmtStatic.setString(1, department.getName()); pstmtStatic.setString(2,
     * department.getUbicacion()); pstmtStatic.setObject(3,
     * department.getCities()); pstmtStatic.setObject(4,
     * department.getTrends()); pstmtStatic.setInt(5,
     * department.getHabitantes());
     *
     * pstmtStatic.executeUpdate();
     *
     * } catch (SQLException sqle) {
     * Logger.getLogger(DepartmentDao.class.getName()).log(Level.SEVERE, null,
     * sqle); } }
     *
     * public List<Trend> findTrendByDepartment(String department) {
     *
     * List<Trend> trends = null;
     *
     * return trends; }
     *
     * /**
     * *
     *
     * By default the connection is front database shema relational.
     *
     * /
     * public void DepartmentService() { mysqlService = new MysqlService();
     * //conn = mysqlService.conn; }
     *
     * public DepartmentDao(String shema) { conn =
     * MysqlService.connToDatabase(shema); }
     *
     * public Connection getConnection() { return conn; }
     *
     * public List<Object> getDepartments() { return this.departments; }
     *
     * public void createDepartment(Department department) {
     *
     * String sql = "INSERT INTO departament (name, ubicacion, cities, trends,
     * habitantes) " + "VALUES (?,?,?,?,?)";
     *
     * try { Class.forName("com.mysql.jdbc.Driver"); conn =
     * DriverManager.getConnection("jdbc:mysql://localhost:3306/cubetrendsproject",
     * "backmachine", ""); pstmtStatic = conn.prepareStatement(sql);
     * pstmtStatic.setString(1, department.getName()); pstmtStatic.setString(2,
     * department.getUbicacion()); pstmtStatic.setObject(3,
     * department.getCities()); pstmtStatic.setObject(4,
     * department.getTrends()); pstmtStatic.setInt(5,
     * department.getHabitantes());
     *
     * pstmtStatic.executeUpdate();
     *
     * } catch (SQLException ex) {
     * Logger.getLogger(DepartmentDao.class.getName()).log(Level.SEVERE, null,
     * ex.getErrorCode()); } catch (ClassNotFoundException ex) {
     * Logger.getLogger(DepartmentDao.class.getName()).log(Level.SEVERE, null,
     * ex); } }
     *
     * protected List<Object> findCitiesByDepartment(String departamento) {
     *
     * List<Object> cities = new ArrayList<Object>(); String sql = "SELECT *
     * FROM city"; rs = null; pstmtStatic = null;
     *
     * try {
     *
     * departments = new ArrayList<Object>(); pstmtStatic =
     * conn.prepareStatement(sql); rs = pstmtStatic.executeQuery(); while
     * (rs.next()) { cities.add(rs.getObject("object")); }
     *
     * } catch (SQLException e) { System.out.println("Eror: " + e); }
     *
     * return cities; }
     *
     * protected List<Object> showDepartments() {
     *
     * List<Object> result = new ArrayList<Object>();
     *
     * try {
     *
     * pstmtStatic = conn.prepareStatement(sql); rs =
     * pstmtStatic.executeQuery(); while (rs.next()) {
     * result.add(rs.getObject("department")); }
     *
     * } catch (SQLException ex) {
     * Logger.getLogger(DepartmentDao.class.getName()).log(Level.SEVERE, null,
     * ex.getErrorCode()); }
     *
     * return result; }
     */
}
