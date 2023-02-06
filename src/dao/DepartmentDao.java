package dao;

import domain.Department;
import java.util.List;

/**
 * Department service hierarchy of department simulator because the CRUD
 * "structured query language SQL" query are not native in the requirement
 *
 * @author macmini
 */
public class DepartmentDao {

    protected DepartmentDaoImpl departmentDaoImpl;
    
    
    public String getCurrentShema(){
        
        String crrntShema = "";        
        try{
            crrntShema = departmentDaoImpl.shema;
        }catch(Exception ex){        
        }        
        return crrntShema;
    }

    public DepartmentDao(String shema) {
        departmentDaoImpl = new DepartmentDaoImpl(shema);
    }
    
    public DepartmentDao() {
        departmentDaoImpl = new DepartmentDaoImpl();
    }

    public boolean createDepartment(Department c) {
        return departmentDaoImpl.createDepartment(c);
    }

    public List<Department> listDepartments() {
        return departmentDaoImpl.listDepartments();
    }

    public Department findDepartment(int id) {
        return departmentDaoImpl.getDepartmentById(id);
    }

    public boolean updateDepartment(Department c) {
        return departmentDaoImpl.updateDepartment(c);
    }

    public boolean deleteDepartment(Department c) {
        return departmentDaoImpl.deleteDepartment(c);
    }
    
    public void truncateTableDepartment(){
        departmentDaoImpl.truncateTable();
    }
}
