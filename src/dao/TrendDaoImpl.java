/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
import simulation.TrendSimulator;
import utils.MysqlConstants;

/**
 *
 * @author macmini
 */
class TrendDaoImpl {

    protected String CREATE_TREND = "INSERT INTO trend (ubicacion, inicio,"
            + " finalizacion, intervalo, busqueda, categoria, tipoBusqueda,"
            + " incXdia, incXsemana, incXintervalo) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected String FIND_TREND = "SELECT * FROM trend WHERE id=?";
    protected String UPDATE_TREND = "UPDATE trend set intervalo=? WHERE id=?";
    protected String DELETE_TREND = "DELETE FROM trend WHERE id=?";
    protected String TRUNCATE_TABLE = "TRUNCATE TABLE trend";
    protected static int last_id;
    protected static List<Trend> trends;
    protected MysqlService mysqlService;
    protected Connection connection;
    protected String shema;

    protected TrendDaoImpl() {
        this.shema = MysqlConstants.getShemaRelational();
        connection = MysqlService.connToDatabase(this.shema);
    }

    public TrendDaoImpl(String shema) {

        this.shema = shema;
        connection = MysqlService.connToDatabase(this.shema);
    }

    public static void main(String[] args) {

        Trend trend = TrendSimulator.buildTrendObject();

        //TrendDaoImpl dao = new TrendDaoImpl("object_shema");
        //TrendDaoImpl dao = new TrendDaoImpl("relational_shema");
        TrendDaoImpl dao = new TrendDaoImpl();
        //System.out.println(Util.GetObjectSize(trend));

        dao.createTrend(trend);

        //dao.insertTrendObjTable(trend);
        System.out.println(last_id);
        //trend.setId(1);
        //dao.deleteTrend(trend);
        //dao.listTrends();
        //service.findTrend(trend);
        //service.updateTrend(trend);
        /**
         *
         * registers = service.seIectTrendRegisters();
         *
         * System.out.println(registers);
         *
         *
         * Subroutine to insert multiple registers with fail handler
         *
         */
        //service.mutipleTrendInsert(100000, null);
    }
  
    protected boolean createTrend(Trend trend) {
        System.out.println("Insert Trend: " + TrendDaoImpl.class.getSimpleName());

        int result = 0;
        boolean success = false;

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement(CREATE_TREND, 
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, trend.getUbicacion());
            ps.setInt(2, trend.getInicio());
            ps.setInt(3, trend.getTermino());
            ps.setInt(4, trend.getIntervalo());
            ps.setString(5, trend.getBusqueda());
            ps.setString(6, "categoria");
            ps.setString(7, "tipo busqueda");
            ps.setInt(8, trend.getIncXdia());
            ps.setInt(9, trend.getIncXSemana());
            ps.setInt(10, trend.getIncXintervalo());

            result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                last_id = rs.getInt(1);               
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrendDaoImpl.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return success;
    }

    protected boolean deleteTrend(Trend trend) {

        System.out.println("Delete trend: " + TrendDaoImpl.class.getSimpleName());

        PreparedStatement ps;
        boolean success = false;
        int result = 0;

        try {

            ps = connection.prepareStatement(DELETE_TREND);
            ps.setInt(1, trend.getId());

            result = ps.executeUpdate();

            if (result == 1) {
                success = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrendDaoImpl.class.getName()).log(Level.SEVERE, null,
                    ex);
        }

        return success;
    }

    protected List<Trend> listTrends() {

        System.out.println("List trend: " + TrendDaoImpl.class.getSimpleName());

        List<Trend> trendLst = new ArrayList<Trend>();
        Trend trend;

        try {

            PreparedStatement ps;
            ps = connection.prepareStatement("SELECT * FROM trend");
            ResultSet rs = null;

            rs = ps.executeQuery();

            while (rs.next()) {
                trend = new Trend();

                trend.setBusqueda(rs.getString("busqueda"));
                trend.setId(rs.getInt("id"));

                trendLst.add(trend);
            }

        } catch (Exception ex) {
            Logger.getLogger(TrendDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return trendLst;
    }

    protected Trend findTrendById(int id) {

        Trend trend = null;
        System.out.println("Find trend: " + TrendDaoImpl.class.getSimpleName());

        try {

            PreparedStatement ps = connection.prepareStatement(FIND_TREND);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                trend = new Trend();
                trend.setId(rs.getInt("id"));
                trend.setTermino(rs.getInt("finalizacion"));
            }

        } catch (Exception ex) {
            Logger.getLogger(TrendDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trend;
    }

    protected boolean updateTrend(Trend t) {

        System.out.println("Update trend: " + TrendDaoImpl.class.getSimpleName());

        boolean success = false;
        int result = 0;

        try {
            PreparedStatement ps;
            ps = connection.prepareStatement(UPDATE_TREND);

            ps.setInt(1, t.getIntervalo());
            ps.setInt(2, t.getId());

            result = ps.executeUpdate();
            if (result == 1) {
                success = true;
            }

        } catch (SQLException sqle) {
            Logger.getLogger(TrendDaoImpl.class.getName()).log(Level.SEVERE, null, sqle);
        }

        return success;
    }

    public boolean insertTrendObjTable(Trend trend) {

        String query = "INSERT  INTO trend (obj, id_gen) VALUES (?, ?)";
        PreparedStatement ps;
        int result = 0;
        boolean success = true;

        try {

            ps = connection.prepareStatement(query);
            ps.setObject(1, trend);
            ps.setInt(2, trend.getId());

            result = ps.executeUpdate();
            if (result == 1) {
                success = true;
            }

        } catch (SQLException sqlex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).
                    log(Level.SEVERE, null, sqlex);
        }

        return success;
    }

    protected void truncateTableTrend() {
        String query = TRUNCATE_TABLE;
        PreparedStatement ps;
        
        try{
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
            
        }catch(Exception ex){
            Logger.getLogger(TrendDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
}
