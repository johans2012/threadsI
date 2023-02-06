/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import lib.Core;

/**
 *
 * @author macmini
 */
public class Intervalo implements Serializable {

    static int ANUAL = 12;
    static int MES = 30;
    static int SEMANA = 7;
    static int DIA = 24;
    int meses;
    int semanas;
    int dias;
    int horas;
    protected Date fechaActual;
    int intervalo;

    public Intervalo() {        
        fechaActual = Core.utils.getCurrentDate();        
    }

    protected void defineIntervalo(int n) {
        this.intervalo = n;
    }

    protected Date getFechaActual(Date date) {
        return this.fechaActual;
    }

    protected void calculaFechaAnterior(int lapso) {
    }

    protected void calculaFechaPosterior(int lapso) {
    }
}
