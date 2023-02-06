package simulation;

import dao.DepartmentDao;
import lib.MysqlService;
import domain.City;
import domain.Department;
import domain.Trend;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IEUser
 */
public class DepartmentSimulator {

    protected DepartmentDao departmentDao;
    protected static Connection connection;
    protected DepartmentSimulator departmentSimulator;            

    public DepartmentSimulator(String shema) {
        try {
            departmentDao = new DepartmentDao(shema);

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/cubetrendsproject",
                    "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentSimulator.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartmentSimulator.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    public boolean multipleDepartmentInsert(int cnt) {

        boolean success = false;
        Department department = null;
        
        for (int i = 0; i < cnt; i++) {
            
            department = buildDepartment();
            
            success = departmentDao.createDepartment(department);            
        }
        
        return success;
    }

    protected static List<Department> listDepartments() {

        List<Department> result = new ArrayList<Department>();
        String sql = "SELECT * FROM department";
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                result.add((Department) rs.getObject("department"));
            }

            MysqlService.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Eror: " + e);
        }

        return result;
    }

    public void multipleUpdateDepartment() {

        String query_find = "SELECT * FROM department";
        String query_update = "UPDATE department set habitantes=?, trends=? "
                + "WHERE id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(query_find);

            ResultSet rs = ps.executeQuery();

            Department department = new Department();

            while (rs.next()) {
                //System.out.println("Nombre "+ rs.getString(1));

                department.setName(rs.getString(1));

            }

            System.out.println(department.toString());
            /*
            ps.setInt(1, department.getHabitantes());
            ps.setObject(2, department.getTrends()); //"(<Iist>) + () "            
            ps.setInt(3, department.getId());      
            ps.executeUpdate();
             */
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentSimulator.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method read and write on registers, when require analysis on
     * different variants, of changes in labels.
     *
     *
     * In this current iteration--> Two data are available to set or change her
     * value, trends and habitantes
     *
     */
    public void update(Department department) {

        try {

            String query = "UPDATE department set habitantes=?, trends=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, department.getHabitantes());
            ps.setObject(2, department.getTrends()); //"(<Iist>) + () "            
            ps.setInt(3, department.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentSimulator.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    protected static Object deSerializeObjectFromDB(long serizalized_id) throws
            SQLException,
            IOException,
            ClassNotFoundException {

        PreparedStatement pstmt = connection.prepareStatement("");

        pstmt.setLong(1, serizalized_id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;

        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }

        Object deSerializedObject = objectIn.readObject();

        return deSerializedObject;
    }

    /**
     * *
     * This method build multiple department instances.
     *
     * @param size
     * @return
     *
     */
    public static List<Department> buildMultipleDepartament(int size) {
        List<Department> departments = new ArrayList();
        List<City> cities = CitySimulator.buildMultipleCity(100);
        List<Trend> trends = TrendSimulator.buildMultipleTrends(size);

        Department department = null;

        for (int i = 0; i < size; i++) {

            department = buildDepartment();

            department.setName("department test #" + i);
            department.setUbicacion("department test5 #" + i);

            departments.add(department);
        }

        return departments;
    }

    /**
     * This method is an implementation for multiple's insert functionality,
     * using the recursive to sign the current instance create an add on the
     * List
     *
     * @param size
     * @param department
     * @return
     */
    public static List<Department> buildMultipleDepartament(int size, Department department) {

        List<Department> departments = new ArrayList();
        List<Trend> trends = TrendSimulator.buildMultipleTrends(size);

        for (int i = 0; i < size; i++) {

            buildMultipleDepartament(size, department);
        }

        return departments;
    }

    public static Department buildDepartment() {

        List<Trend> trends = TrendSimulator.buildMultipleTrends(10);
        List<City> cities = CitySimulator.buildMultipleCity(10);

        Department department = new Department();
        department.setName("nombre departamento");
        department.setTrends(trends);
        department.setUbicacion("ubicacion for department");
        department.setHabitantes(1000000);
        department.setCities(cities);
        department.setCapital("Nombre de ciudad capital");

        return department;
    }

    public static void main(String[] args) {

        List<Trend> trends = new ArrayList();
        List<Department> departments = new ArrayList<Department>();

        trends = TrendSimulator.buildMultipleTrends(100);
        departments = DepartmentSimulator.buildMultipleDepartament(100);

        Department departamento = DepartmentSimulator.buildDepartment();
        departamento.setId(10);
        departamento.setHabitantes(500000);

        //  DepartmentSimulator.multipleDepartmentInsert(100, departamento);
        DepartmentSimulator simulator = new DepartmentSimulator("");
        simulator.multipleUpdateDepartment();

    }
}
