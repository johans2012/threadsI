package lib;

import core.CitizenService;
import core.CityService;
import core.CountryService;
import core.DepartmentService;
import core.TrendService;
import simulation.MysqlSimulator;
import domain.Trend;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johans caicedo
 */
public class Core {

    protected CitizenService citizenService;
    protected CityService cityService;
    protected CountryService countryService;
    protected DepartmentService departmentService;
    protected TrendService trendService;

    protected MysqlSimulator simulator;
    protected MysqlService service;
    public static Util utils;
    private int menuOpt;

    public Core() {
        service = new MysqlService();
        utils = new Util();

        countryService = new CountryService();

        /* 
        citizenService = new CitizenService();
        cityService = new CityService();
        
        departmentService = new DepartmentService();
        trendService = new TrendService();
        Thread t1 = new Thread(cityService);   // Using the constructor Thread(Runnable r)  
        Thread t2 = new Thread(countryService);   // Using the constructor Thread(Runnable r)  
        Thread t3 = new Thread(departmentService);   // Using the constructor Thread(Runnable r)  
        Thread t4 = new Thread(trendService);   // Using the constructor Thread(Runnable r)  
        t1.start();
        t2.start();
        t3.start();
        t4.start();*/
    }

    public void run() {

        int option = 0;

        do {
            optionsToAdmin();
            menuOpt = readOption();
            mainProgram();
        } while (option != 0);
    }

    protected int optionsForDataBase() {
        System.out.println("<<<<<<<<<<Menu for database>>>>>>>>>");
        System.out.println("( 1 ) Insertar registros");
        System.out.println("( 2 ) Crear tendencia");
        System.out.println("( 3 ) Buscar por intervalo de fecha");
        System.out.println("( 4 ) Buscar por intervalo mayor que-> X");
        System.out.println("( 5 ) Buscar por intervalo menor que-> X");
        System.out.println("( 6 ) Buscar registro con incremento por dia superior a: ");

        return readOption();
    }

    protected void optionsForFileAdmin() {
        System.out.println("<<<<<<<<<<Menu for file admin>>>>>>>>>");
        System.out.println("( 1 ) Insertar registros");
        System.out.println("( 2 ) Crear tendencia");
        System.out.println("( 3 ) Buscar por intervalo de fecha");
        System.out.println("( 4 ) Buscar por intervalo mayor que-> X");
        System.out.println("( 5 ) Buscar por intervalo menor que-> X");
        System.out.println("( 6 ) Buscar registro con incremento por dia superior a: ");
    }

    public void optionsToAdmin() {
        System.out.println("<<<<<<<<<<Menu to admin>>>>>>>>>");

        System.out.println("( 1 ) Buscar tendencia por departamento: ");
        System.out.println("( 2 ) Buscar tendencia por ciudad: ");
        System.out.println("( 3 ) Opciones de administracion de base de datos: ");
        System.out.println("( 4 ) Opciones de testing");

        System.out.println("( 0 ) Salir: ");
    }

    public int exeTestOptions() {

        System.out.println("<<<<<<<<<<Menu Test Options>>>>>>>>>");
        System.out.println("( 1 ) Insertar registros");

        System.out.println("( 2 ) Crear tendencia por departamento: ");
        System.out.println("( 3 ) Crear tendencia por ciudad: ");

        System.out.println("( 4 ) Crear tendencia por intervalo de tiempo");

        System.out.println("( 5 ) Buscar resultado de tendencia");
        System.out.println("( 6 ) Buscar por intervalo de fecha");
        System.out.println("( 7 ) Buscar por intervalo mayor que-> X");
        System.out.println("( 8 ) Buscar por intervalo menor que-> X");
        System.out.println("[ 9 ] Buscar registro con incremento por dia superior a: ");

        return readOption();
    }

    public int readOption() {
        Scanner sc = new Scanner(System.in);
        return menuOpt = sc.nextInt();
    }

    protected Trend createTrendFrmCmdLne() {

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
            System.out.println(ex.getMessage() + " On method exe subrotuine");
        }

        return trend;
    }

    public void mainProgram() {

        switch (menuOpt) {
            case 1:
                //  this.dpartmntService.
                break;
            case 2:
                break;
            case 3:
                /* Opciones de base de datos*/
                int option = optionsForDataBase();
                service.exeSubRoutine(option);
                break;
            case 4:
                int opt = exeTestOptions();
                 {
                    try {
                        simulator = new MysqlSimulator();
                        simulator.run(opt);
                    } catch (IOException ex) {
                        Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 5:
                
                break;
            default:
                mainProgram();
                break;
        }
    }
}
