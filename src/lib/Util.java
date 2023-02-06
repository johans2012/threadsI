/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import domain.City;
import domain.Department;
import domain.Trend;
import java.lang.instrument.Instrumentation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.instrument.InstrumentationImpl;

/**
 *
 * @author macmini
 */
public class Util {

    protected static Scanner scanner;

    public static Department createTrendByDepartment() {
        Department department = new Department();
        scanner = new Scanner(System.in);

        try {
            System.out.println("Digite el nombre del departamento");
            department.setName(scanner.nextLine());
            System.out.println("Digite el numero de habitantes");
            department.setHabitantes(scanner.nextInt());

            Trend trend = new Trend();
            System.out.println("Cuantas tendencias desea crear: ?");
            int cnt = 0;

            cnt = scanner.nextInt();
            List<Trend> trends = new ArrayList();

            while (cnt > 0) {
                cnt = cnt - 1;
                System.out.println("Digite el termino que desea buscar: ");
                String busqueda = scanner.nextLine();
                trend.setBusqueda(busqueda);
                System.out.println("Digite la ubicacion: ");
                String ubicacion = scanner.nextLine();
                trend.setUbicacion(ubicacion);

                trends.add(trend);
            }
            department.setTrends(trends);

        } catch (Exception e) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, e.getCause());
        }

        return department;
    }

    public static City createTrendByCity() {
        scanner = new Scanner(System.in);

        City city = new City();
        try {
            System.out.println("Digite el nombre de la ciudad: ");
            city.setNombre(scanner.nextLine());
            System.out.println("Digite el nombre del departamento: ");
            city.setDepartamento(scanner.nextLine());
            System.out.println("Digite el area: ");
            city.setArea(scanner.nextInt());
            System.out.println("Digite la capital: ");
            //city.setCapital(scanner.nextLine());

            Trend trend = new Trend();
            System.out.println("Cuantas tendencias desea crear: ?");
            int cnt = 0;

            cnt = scanner.nextInt();
            List<Trend> trends = new ArrayList();

            while (cnt > 0) {
                cnt = cnt - 1;
                System.out.println("Digite el termino que desea buscar: ");
                String busqueda = scanner.nextLine();
                trend.setBusqueda(busqueda);
                System.out.println("Digite la ubicacion: ");
                String ubicacion = scanner.nextLine();
                trend.setUbicacion(ubicacion);

                trends.add(trend);
            }
            city.setTrends(trends);
        } catch (Exception io) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, io.getCause());
        }
        return city;
    }

    /**
     * *
     * Options to read from input user * **
     */
    public static Trend createTrendFrmCmdLne() {

        Scanner sc = new Scanner(System.in);
        Trend trend = new Trend();

        try {

            System.out.println("Digite el departamento o la ciudad:  ");
            trend.setUbicacion(sc.nextLine());

            System.out.println("Digite la tendencia a buscar: ");
            trend.setBusqueda(sc.nextLine());

            int enable = 0;

            System.out.println("Hacer busqueda por un intervalo definido?: ");
            System.out.println("(1) Si");
            System.out.println("(0) No");
            enable = sc.nextInt();

            if (enable == 1) {
                System.out.println("Digite el intervalo de tiempo: ");
                trend.setIntervalo(sc.nextInt());

            } else if (enable == 0) {

                System.out.println("Busqueda por fecha de inicio y fecha fin: ");
                System.out.println("Digite el inicio del intervalo de busqueda: ");
                trend.setInicio(sc.nextInt());

                System.out.println("Digite el fin del intervalo de busqueda: ");
                trend.setTermino(sc.nextInt());
            }

            System.out.println("Digite la ciudad de la busqueda: ");
            trend.setUbicacion(sc.nextLine());

        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex.getCause());
        }
        return trend;
    }

    public static Date getCurrentDate() {
        Date obj = new Date(1);
        return obj;
    }

    public static String getCurrentDateToString() {
        DateFormat dform = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date obj = new Date(1);

        return dform.format(dform.format(obj));
    }
        
  
}
