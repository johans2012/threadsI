package dao;

import domain.Trend;
import java.util.List;

/**
 *
 * @author macmini
 */
public class TrendDao {

    protected TrendDaoImpl trendDaoImpl;
    
     public String getCurrentShema() {
        String crrntShema = "";
        try {
            crrntShema = trendDaoImpl.shema;
        } catch (Exception ex) {            
        }
        return crrntShema;
    }

    public TrendDao(String shema) {
        trendDaoImpl = new TrendDaoImpl(shema);
    }

    public TrendDao() {
        trendDaoImpl = new TrendDaoImpl();
    }

    public boolean createTrend(Trend t) {
        return trendDaoImpl.createTrend(t);
    }

    public List<Trend> listTrend() {
        return trendDaoImpl.listTrends();
    }

    public Trend findTrendById(int id) {
        return trendDaoImpl.findTrendById(id);
    }

    public boolean updateTrend(Trend t) {
        return trendDaoImpl.updateTrend(t);
    }

    public boolean deleteTrend(Trend t) {
        return trendDaoImpl.deleteTrend(t);
    }

    public int getLastId() {
        return TrendDaoImpl.last_id;
    }

    public void truncateTable(){
        trendDaoImpl.truncateTableTrend();
    }
}
