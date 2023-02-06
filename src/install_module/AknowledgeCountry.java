/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package install_module;

import domain.Country;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IEUser
 */
public class AknowledgeCountry {

    protected List<Country> countries;

    protected List<String> capital;
    protected List<String> names;
    protected List<Integer> populaty;
    protected List<Integer> area;
    protected List<Integer> temperatura;

    public AknowledgeCountry() {

        Country country;

        area = new ArrayList<Integer>();
        area.add(128521620);
        area.add(256370);
        area.add(2780400);

        temperatura = new ArrayList<Integer>();
        temperatura.add(20);
        temperatura.add(20);
        temperatura.add(30);

        names = new ArrayList<String>();
        names.add("Peru");
        names.add("Ecuador");
        names.add("Argentina");

        populaty = new ArrayList<Integer>();
        populaty.add(33149016);
        populaty.add(17803339);
        populaty.add(45195777);

        capital = new ArrayList<String>();
        capital.add("Lima");
        capital.add("Quito");
        capital.add("Buenos aires");

        countries = new ArrayList<Country>();
        for (int i = 0; i < names.size(); i++) {

            country = new Country();
            country.setNombre(names.get(i));
            country.setHabitantes(populaty.get(i));
            country.setCapital(capital.get(i));
            country.setTemperatura(temperatura.get(i));
            country.setArea(area.get(i));

            countries.add(country);
        }
    }

    protected void logCountries() {

        for (Country get : countries) {
            System.out.println("Nombre :" + get.getNombre());
            System.out.println("Poblacion: " + get.getHabitantes());
            System.out.println("-------------------------------------");
        }
    }

    public static void main(String[] args) {
//        AknowledgeCountry obj = new AknowledgeCountry();
//        obj.logCountries();
        // System.out.println(AknowledgeCountry.getCountry());
    }

    protected void countDiffFromPopulaty() {

        int cnt = 0;
        int diff = 0;

        //TODO Calcula difference betwen idx
        for (Country get : countries) {
            cnt = get.getHabitantes();
            diff = cnt - diff;
        }

    }

    public static List<Country> getCountry() {

        AknowledgeCountry obj = new AknowledgeCountry();
        return obj.countries;
    }

}
