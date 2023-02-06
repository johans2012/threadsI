package lib;

import dao.CityDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MysqlConstants;

/**
 *
 * @author Johans caicedo
 */
public class MysqlService {

    protected Mysql mysql;
    protected CityDao cityService;
    //protected DepartmentService departmentService;
    protected StatisticsService statisticsService;
    protected static String sqlStic;
    protected static PreparedStatement pstmtStatic;
    protected static Connection connSttc = null;
    protected Connection conn = null;
    static final String WRITE_CREDENCIAL_SQL = "INSERT INTO objecttest(object) VALUES (?)";
    protected static String EXTENSIBLE_INSERT_QUERY = "INSERT INTO ? (object) VALUES (?)";

    public MysqlService() {
        conn = getConnectionShema();
    }

    public static Connection connToDatabase(String database) {
      
        if (database.equals(MysqlConstants.getShemaTest())) {
            connSttc = getConnecTest();
        }        
        if(database.equals(MysqlConstants.getShemaRelational())){      
            connSttc = getConnectionShema();
        }
        if(database.equals(MysqlConstants.getObjectShema())){     
            connSttc = getConnObjectShema();
        }
        if(database.equals(MysqlConstants.getShemaSimulator())){       
            connSttc = getConnSimulateShema();
        }
        return connSttc;
    }    
    
    public static Connection getConnSimulateShema() {
        return connSttc = Mysql.connSimulatorShema();
    }

    /**
     * *
     *
     *
     * @return 
     */
    public static Connection getConnecTest() {
        return connSttc = Mysql.getConnShemaTest();
    }

    /**
     * *
     *
     *
     * @return 
     */
    public static Connection getConnectionShema() {
        return connSttc = Mysql.getConnShema();
    }

    /**
     * *
     *
     *
     * @return 
     */
    public static Connection getConnObjectShema() {
        return connSttc = Mysql.getConnObjectShema();
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     *
     *
     * @param method
     */
    public void exeSubRoutine(int method) {

        switch (method) {
            case 1:

                break;
            case 2:

                break;
            case 3:
                int min = 0;
                int max = 0;
                System.out.println("Fecha inicial: ");
                Scanner sc = new Scanner(System.in);
                min = sc.nextInt();
                System.out.println("Fecha final: ");
                max = sc.nextInt();
                findBetwenInterval(min, max);
                break;
            default:

                break;
        }
    }

    public static void multipleInsert(int cnt, String description, Date fecha, int intervalo,
            String ubicacion, int puntaje, int incXdia, int incXsemana, int incXintervalo, boolean test) {

        sqlStic = "INSERT INTO TESTDATA (description, fecha, intervalo, ubicacion,"
                + " puntaje, incXdia, incXsemana, incXintervalo) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";

        if (test) {
            description = "descripttion test data";
            fecha = new Date(100);
            intervalo = 3;
            ubicacion = "ubicacion test data";
            puntaje = 50;
            incXdia = 1;
            incXintervalo = 1;
            incXsemana = 1;
            /**
             *
             *
             * (String description, Date fecha, int intervalo, Sting ubicacion,
             * int puntaje, int incXdia, int incXsemana, int incXintervalo)
             *
             * description fecha intervalo ubicacion puntaje incXdia incXsemana
             * incXintervalo
             *
             *
             */
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connSttc = DriverManager.getConnection("jdbc:mysql://localhost:8889/datamining", "root", "root");

            pstmtStatic = null;
            pstmtStatic = connSttc.prepareStatement(sqlStic);

            for (int i = 0; i < cnt; i++) {
                pstmtStatic.setString(1, description);
                pstmtStatic.setDate(2, fecha);
                pstmtStatic.setInt(3, intervalo);
                pstmtStatic.setString(4, ubicacion);
                pstmtStatic.setInt(5, puntaje);
                pstmtStatic.setInt(6, incXdia);
                pstmtStatic.setInt(7, incXsemana);
                pstmtStatic.setInt(8, incXintervalo);
                pstmtStatic.executeUpdate();
            }

        } catch (ClassCastException ex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException sqle) {
            System.out.print(sqle);
        } catch (ClassNotFoundException sqle) {
            System.out.print(sqle);
        }

    }

    public void multipleInsert(int registers, Object object, String tableName) {
        try {

            PreparedStatement pstmt = conn.prepareStatement(WRITE_CREDENCIAL_SQL);

            for (int i = 0; i <= registers; i++) {

                pstmt.setObject(1, object);
                pstmt.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, ex.getCause());

        }
    }

    public void multipleInsert(int cnt, Object obj) throws ClassNotFoundException {

        try {

            PreparedStatement pstmt = conn.prepareStatement(WRITE_CREDENCIAL_SQL);

            for (int i = 0; i <= cnt; i++) {
                pstmt.setObject(1, obj);
                pstmt.setString(2, "Objeto: [" + i + "] creado");
                pstmt.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, ex.getCause());
        }
    }

    public void findAndDeleteFilter(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM objecttest WHERE id=?";

        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

        } catch (SQLException sqle) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    public void findByDate(Date date, int intervalo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM objecttest WHERE fecha=?";

        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1, intervalo);
            rs = ps.executeQuery();

        } catch (SQLException sqle) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    public void findBetwenInterval(int min, int max) {

        String sql = "SELECT * FROM objecttest WHERE intervalo >?  AND intervalo <?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1, min);
            ps.setInt(2, max);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getObject(2) + "  " + rs.getString(3));
            }

        } catch (SQLException sqle) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, sqle);
        }

    }

    public void countRegisterGreaterThan(int value) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SElECT * FROM objecttest WHERE intervalo >?";

        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "  " + rs.getObject(2) + "  " + rs.getString(3));
                }
            } else {
                System.out.println("No existen registros con intervalos de tiempo mayores que: " + value);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void countRegisterSmallerThan(int value) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SElECT * FROM objecttest WHERE intervalo <?";

        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1, value);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(5));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void countIncreaseGreaterThan(int value) {

        List ids = new ArrayList();

        int cnt = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SElECT * FROM objecttest WHERE incXdia>?";

        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1, value);
            rs = ps.executeQuery();

            while (rs.next()) {

                cnt = cnt + 1;

                System.out.println(rs.getInt(5));
                ids.add(rs.getInt("incXdia"));

            }

            System.out.println("Total de registros encontrados: " + cnt);
            System.out.println("Ids found: " + ids.toString());

        } catch (SQLException ex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
