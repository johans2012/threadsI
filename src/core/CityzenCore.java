

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import dao.CitizenDao;
import domain.Citizen;

/**
 *
 * @author macmini
 */
class CityzenCore extends Service implements Runnable {

    protected CitizenDao citizenDao;

    public void init() {
        citizenDao = new CitizenDao();
    }

    public void createCitizen(Citizen citizen) {
        citizenDao.createCitizen(citizen);
    } 
}