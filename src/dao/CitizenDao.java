package dao;

import domain.Citizen;
import java.util.List;

/**
 *
 * @author johans caicedo
 */
public class CitizenDao {

    protected CitizenDaoImpl citizenImpl;

    
    public String getCurrentShema(){
        
        String crrntShema = ""; 
        try{
            crrntShema = citizenImpl.shema;
        }catch(Exception ex){            
        }                
        return crrntShema;
    }
    
    public CitizenDao(String shema) {
        citizenImpl = new CitizenDaoImpl(shema);
    }

    public CitizenDao() {
        citizenImpl = new CitizenDaoImpl();
    }

    public boolean createCitizen(Citizen c) {
        return citizenImpl.createCitizen(c);
    }

    public List<Citizen> listCitizen() {
        return citizenImpl.listCitizen();
    }

    public Citizen findCitizen(int id) {
        return citizenImpl.findCitizenById(id);
    }

    public boolean updateCitizen(Citizen c) {
        return citizenImpl.updateCitizen(c);
    }

    public boolean deleteCitizen(Citizen c) {
        return citizenImpl.deleteCitizen(c);
    }
    
    public void truncateTable(){
        citizenImpl.truncateTableCitizen();
    } 

}
