package simulatorTest;

import java.util.List;
import domain.Department;
import dao.DepartmentDao;
import org.junit.Before;
import org.junit.Test;
import simulation.DepartmentSimulator;
import utils.MysqlConstants;
import static org.junit.Assert.*;

/**
 *
 * @author macmini
 */
public class DepartmentSimulatorTest {

    protected DepartmentSimulator departmentSimulator;
    protected DepartmentDao dao;
    protected Department department;
    protected List<Department> departments;
    protected int[] insert = new int[4];
    long[] exe = new long[4];

    @Before
    public void setup() {
        insert[0] = 1;
        insert[1] = 10;
        insert[2] = 100;
        insert[3] = 1000;

        department = new Department();
        departmentSimulator = new DepartmentSimulator(MysqlConstants.getShemaSimulator());
        dao = new DepartmentDao(MysqlConstants.getShemaSimulator());
    }

    @Test
    public void testShemaSimulatorConfig() {

        assertEquals(MysqlConstants.getShemaSimulator(),
                dao.getCurrentShema());
    }

    @Test
    public void buildDepartmentTest() {
        Department department = DepartmentSimulator.buildDepartment();
        boolean success = false;

        if (department instanceof Department) {
            success = true;
        }

        assertEquals(success, true);
    }

    @Test
    public void shemaEmptyRegistersTest() {

        dao.truncateTableDepartment();
        departments = dao.listDepartments();
        assertEquals(departments.size(), 0);
    }

    @Test
    public void buildMultipleDepartmentTest() {

        int size = 100;

        departments = DepartmentSimulator.buildMultipleDepartament(size);
        assertEquals(departments.size(), size);
    }

    @Test
    public void multipleDepartmentInsertTest() {
        long end = 0;
        long start = 0;
        long time = 0;
        int size = 10;
        boolean success = false;

        for (int x = 0; x < insert.length; x++) {
            start = System.currentTimeMillis();
            success = departmentSimulator.multipleDepartmentInsert(insert[x]);
            end = System.currentTimeMillis();

            time = end - start;

            System.err.println("Total time" + (time));
            exe[x] = time;
        }

        time = end - start;

        // System.err.println("Total time" + (time));
        assertEquals(success, true);
    }
}
