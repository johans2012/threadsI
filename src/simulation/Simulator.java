/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import dao.CitizenDao;
import dao.CityDao;
import dao.CountryDao;
import dao.DepartmentDao;
import dao.TrendDao;
import java.sql.Connection;
import lib.MysqlService;

/**
 *
 * @author IEUser
 */
public class Simulator {

    protected MysqlService service;
    
    protected TrendDao trendDao;
    protected DepartmentDao departmentDao;
    protected CountryDao countryDao;
    protected CityDao cityDao;
    protected CitizenDao citizenDao;
    
    public Simulator() {
        service = new MysqlService();
    }
    
    public Connection getConnection(){
        return MysqlService.connToDatabase("simulator_shema");
    }
    
    
}
