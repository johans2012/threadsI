package simulation;

import dao.CityDao;
import dao.DepartmentDao;
import domain.City;
import domain.Department;
import java.io.IOException;
import lib.Util;

public class MysqlSimulator {

    protected DepartmentDao departmentDao;
    protected CityDao cityDao;

    /**
     * TODO Definir el tipo de dato a crear
     *
     * @param opt
     * @throws java.io.IOException
     */
    public void run(int opt) throws IOException {
        Department department;
        City city;
        switch (opt) {
            case 1:
                System.out.println("Multiple insert running..");
                //multipleInsert(20, city, "objecttest");
                break;
            case 2:
                //departmentService = new DepartmentDao("test_shema");
                System.out.println("Create trend by department: ");

                department = Util.createTrendByDepartment();
                // departmentService.createTrendByDepartment(department);
                break;
            case 3:
                cityDao = new CityDao("relational_shema");
                System.out.println("Created trend by city: ");
                city = Util.createTrendByCity();

                cityDao.createCity(city);
            default:
                throw new IOException();
        }
    }

}
