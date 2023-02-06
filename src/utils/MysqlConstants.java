/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author IEUser
 */
public class MysqlConstants {

    public static String DEPARTMENT_TABLE_NAME = "departamento";
    protected static String CITY_TABlE_NAME = "ciudad";
    protected static String COUNTRY_TABlE_NAME = "pais";
    protected static String TREND_TABlE_NAME = "trend";
    protected static String CITIZEN_TABlE_NAME = "citizen";

    public static String DB_SHEMA_SHEMA = "cubetrendsproject";
    protected static String DB_SHEMA_TEST = "shema_trends_test";

    protected static String DB_OBJECT_SHEMA = "shema_trends_test";
    public static String DB_SIMULATOR_ = "cubetrendsimulator";

    protected static String DB_SIMULATOR_SHEMA = "shema_trends_test";
    protected static String DB_SIMULATOR_TEST = "shema_trends_test";

    protected static String DB_SIMULATOR_OBJECT = "shema_trends_test";
    protected static String SIMULATOR_OBJECT_TEST = "shema_trends_test";
    
    protected static String RELATIONAL_SHEMA = "relational_shema";
    protected static String OBJECT_SHEMA = "object_shema";
    protected static String TEST_SHEMA = "test_shema";
    protected static String SIMULATOR_SHEMA = "simulator_shema";
    
    public static String getShemaTest(){
        return TEST_SHEMA;
    }

    public static String getShemaRelational(){
        return RELATIONAL_SHEMA;
    }
        
    public static String getShemaSimulator(){
        return SIMULATOR_SHEMA;
    }
    
    public static String getObjectShema(){
        return OBJECT_SHEMA;
    }
       
}
