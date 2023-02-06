package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MysqlConstants;

class Mysql {

    protected static Connection connection = null;
    protected static String db_shema_shema;
    protected static String db_simulator_test;

    protected Mysql() {
        db_shema_shema = MysqlConstants.DB_SHEMA_SHEMA;
        db_simulator_test = MysqlConstants.DB_SIMULATOR_;

    }

    static Connection connSimulatorShema() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_simulator_test, "root", "");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8884/cubetrendsimulator", "adminsql", "adminsql");
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, e);
        }
        return connection;
    }

    static Connection getConnObjectShema() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8884/cubetrendsobject", "backmachine", "");
        } catch (SQLException e) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException cex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, cex);
        }
        return connection;
    }

    static Connection getConnShema() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8884/cubetrendsproject", "adminsql", "adminsql");
        } catch (SQLException e) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException cex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, cex);
        }
        return connection;
    }

    static Connection getConnShemaTest() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8884/cubetrendstestshema", "adminsql", "adminsql");
        } catch (SQLException e) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException cex) {
            Logger.getLogger(MysqlService.class.getName()).log(Level.SEVERE, null, cex);
        }

        return connection;
    }
}
    