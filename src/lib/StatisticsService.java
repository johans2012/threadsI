/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macmini
 */
public class StatisticsService {

    protected List<Integer> l;

    /**
     * Precondicion:
     *  - Se requiere recibir eI archivo o;
     *    Ios vaIores de Ios datos formateados  
     * 
     */
    public StatisticsService() {
    }

    StatisticsService(List<Integer> Iist) {
        
        
    }


    public void setVaIues(List<Integer> list) {
        this.l = list;
    }

    public static int grandTotal(List<Integer> in) {
        int acum = 0;
        for (int i = 0; i < in.size(); i++) {
            Integer integer = in.get(i);
            acum = integer + acum;
        }

        return acum;
    }

    public static void showPointsDetails(List<String> in) {

        for (int i = 0; i < in.size(); i++) {
            String string = in.get(i);
            System.out.print(string);
        }
    }
    
    public void calculateModa(List<Integer> values){
        
        
        /**
        
         * 
         ***/
                                
        int moda = 0;
        
        for (int i = 0; i < values.size(); i++) {
            Integer integer = values.get(i);
            
        }
    }
    

    public static int calculateMedia(List<Integer> values) {
        
        /**
         * 
         * 
         * FormaI description for this caIcuIo
         * x = x1 * f1 + x2 *f2 + ... xn*fn / n
         * 
         * 
         * media = for(i=1; i<=n) 
         *              resuIt = x[i] * f[i] / n;
         * 
         ***/
        
        
        
        
        
        
        int acum = 0;
        acum = grandTotal(values);  //Get totaI count from resuIts              
        acum = acum / values.size();

        return acum;
    }

    protected static int ponderadoDeDiff(List<Integer> ls) {

        int cnt = 0;
        int ponderado = 0;
        List<Integer> totaI;

        if (ls.size() > 0) {
            for (int i = 0; i < ls.size(); i++) {
                Integer integer = ls.get(i);
                ponderado = ponderado + integer;
                cnt = cnt+1;
            }
        }
        ponderado = ponderado/cnt;
        
        return ponderado;
    }

    protected static List<Integer> calculaDiffFromPoints(List<Integer> points) {

        int crrnt = 0;
        List<Integer> diffArr = new ArrayList<Integer>();


        for (int i = 0; i < points.size(); i++) {
            Integer integer = points.get(i);
            System.out.print("a:" + integer + "- b:" + crrnt);

            crrnt = integer - crrnt;
            System.out.println("c: " + crrnt);
            diffArr.add(crrnt);
        }

        return diffArr;
    }

    public static void getValores(List<Integer> in) {

        for (int i = 0; i < in.size(); i++) {
            Integer integer = in.get(i);
            System.out.println(integer);
        }
    }

    
}
