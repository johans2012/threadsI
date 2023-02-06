package core;

import lib.MysqlService;

class Service implements Runnable {
    
    MysqlService mysqlService;

    Thread mainMenu;

    protected int showMainMenu() {
        int option = 0;
        System.out.println("Ver opciones de administracion: ");
        System.out.println("Ver opciones de simulacion: ");
        System.out.println("Ver opciones de test:");
        System.out.println("0 Exit: ");

        return option;
    }

    @Override
    public void run() {

        int run = 0;

        do {

            run = showMainMenu();

        } while (run > 0);

    }

}
