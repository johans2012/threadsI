package daoTest;

import dao.DepartmentDao;
import domain.Department;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import simulation.DepartmentSimulator;
import utils.MysqlConstants;

/**
 *
 * @author IEUser
 */
public class DepartmentDaoTest {

    protected DepartmentDao departmentDao;
    protected Department department;
    protected List<Department> departments;
    protected Connection connection;
    protected ResultSet resultSet;
    protected PreparedStatement ps;
    protected boolean flag;

    @Before
    public void setup() {
        departments = new ArrayList<Department>();
        departmentDao = new DepartmentDao(MysqlConstants.getShemaTest());
        flag = false;
    }

    @Test
    public void testShemaByDefaultConfig() {
        DepartmentDao dao = new DepartmentDao();
        
        assertEquals(MysqlConstants.getShemaRelational(),
                dao.getCurrentShema());
    }
    
   
    @Test
    public void testShemaToTestConfig() {
        assertEquals(departmentDao.getCurrentShema(),
                MysqlConstants.getShemaTest());
    }

    @Test
    public void testCreateDepartment() {
        department = DepartmentSimulator.buildDepartment();
        flag = departmentDao.createDepartment(department);

        assertEquals(flag, true);
    }

    @Test
    public void testDeleteDepartment() {
        flag = departmentDao.deleteDepartment(department);
        assertEquals(flag, true);
    }

    @Test
    public void testListDepartment() {
        departments = departmentDao.listDepartments();
        assertNotNull(departments);

        int cnt = 0;
        for (int i = 0; i < departments.size(); i++) {

            assertNotNull(departments.get(i));

            cnt = cnt++;
        }

        assertEquals(departments.size(), cnt);
    }

    @Test
    public void testFindDepartment() {
        Department found = null;

        found = departmentDao.findDepartment(department.getId());

        assertNotNull(found);
    }

    @Test
    public void testUpdateDepartment() {
        departments = departmentDao.listDepartments();
        if (departments.size() > 0) {
            for (int i = 0; i < departments.size(); i++) {
                department = departments.get(i);
                int id = department.getId();
                if (id != 0) {
                    department.setId(department.getId());
                    flag = departmentDao.updateDepartment(department);
                }
            }
        }

        assertEquals(flag, true);
    }
    
    @Test
    public void truncateTableTest(){
        
        departmentDao.truncateTableDepartment();
        departments = departmentDao.listDepartments();
        assertEquals(departments.size(), 0);
    }
}
