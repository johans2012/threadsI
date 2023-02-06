/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author macmini
 */
public class City implements Serializable {

    int id;
    String nombre;
    City capital;
    String location;
    Date date;
    int temperatura;
    String departamento;
    List<Trend> trends;
    int altitud;
    float area;
    int habitantes;

    public int getHabitantes() {
        return this.habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public float getArea() {
        return this.area;
    }

    public void setAltitud(int a) {
        this.altitud = a;
    }

    public int getAltitud() {
        return this.altitud;
    }

    public List<Trend> getTrends() {
        return this.trends;
    }

    public void setTrends(List<Trend> trends) {
        this.trends = trends;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setCity(String departmento) {
        this.departamento = departmento;
    }

    public String getCity() {
        return this.departamento;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public City getCapital() {

        return new City();
    }

    public void setFecha(Date date) {
        this.date = date;
    }

//Date from Iatest query from the server 
    public Date getFecha() {
        return this.date;
    }

    public int getTemperatura() {
        return this.temperatura;
    }

    public String getLocation() {
        return this.location;
    }

    public int getId() {
        return  this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }
}
