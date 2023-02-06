package simulation;

import dao.CitizenDao;
import domain.Citizen;
import java.util.ArrayList;
import java.util.List;
import utils.MysqlConstants;

/**
 *
 * @author IEUser
 */
public class CitizenSimulator {
    
    static Citizen citizen;
    protected CitizenDao citizenDao;

    /**
     *
     *
     * @return Citizen
     *
     */
    public static Citizen buildCitizen() {
        
        citizen = new Citizen();
        
        citizen.setNombre("Nombre citizen generate: ");
        citizen.setCompensacion("Caja de compensacion");
        citizen.setEdad(20);        
        citizen.setSalario(1000000);
        citizen.setSexo("m");
        citizen.setEps("eps to test object");
        citizen.setEmpleo("trabajo test");
        return citizen;
    }

    /**
     *
     * @return List<Citizen>
     *
     */
    public List<Citizen> buildMultipleCitizen(int size) {
        
        List<Citizen> citizens = new ArrayList<Citizen>();
        
        for (int i = 0; i < size; i++) {
            
            citizen = buildCitizen();
            citizens.add(citizen);
        }
        
        return citizens;
    }
    
    public void multipleCitizenInsert(int size) {
        long end = 0;
        long start = 0;
        long time = 0;
        
        start = System.currentTimeMillis();
        
        citizenDao = new CitizenDao(MysqlConstants.getShemaSimulator());
        
        for (int i = 0; i < size; i++) {
            citizen = CitizenSimulator.buildCitizen();
            citizenDao.createCitizen(citizen);
        }
        end = System.currentTimeMillis();
        time = end - start;
        System.out.println(time);
    }
                
    public static void main(String args[]){
        
        
        CitizenSimulator simulator = new CitizenSimulator();
        simulator.multipleCitizenInsert(10000);
    }
}
