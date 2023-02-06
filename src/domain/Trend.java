/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author macmini
 */
public class Trend implements Serializable {

    int id;
    String ubicacion;//Ciudad 
    int inicio;
    int finalizacion;
    int intervalo;
    String busqueda;//Palabra o frase que busca el usuario
    String[] categorias;
    String categoria;
    String[] tiposBusqueda;
    String tipoBusqueda;
    
    protected Department department;
    protected City city;

    //Result
    int incXdia;
    int incXSemana;
    int incXintervalo;
       
    public Trend() {
        init();               
    }

    public void init() {

        //Inicialliza las categorias
        tiposBusqueda = new String[10];
        tiposBusqueda[0] = "Busqueda de imagen";
        tiposBusqueda[1] = "Busqueda de noticias";
        tiposBusqueda[2] = "Google shopping";
        tiposBusqueda[3] = "Busqueda de youtube";

        categorias = new String[10];
        categorias[0] = "Todas las categorias";
        categorias[1] = "Aficiones y tiempo libre";
        categorias[2] = "Alimentacion y bebidas";
        categorias[3] = "Animales y mascotas";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoria(int idx) {
        this.categoria = getCategoria(idx);
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public int getIncXSemana() {
        return incXSemana;
    }

    public void setIncXSemana(int incXSemana) {
        this.incXSemana = incXSemana;
    }

    public int getIncXdia() {
        return incXdia;
    }

    public void setIncXdia(int incXdia) {
        this.incXdia = incXdia;
    }

    public int getIncXintervalo() {
        return incXintervalo;
    }

    public void setIncXintervalo(int incXintervalo) {
        this.incXintervalo = incXintervalo;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public int getTermino() {
        return finalizacion;
    }

    public void setTermino(int termino) {
        this.finalizacion = termino;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCategoria(int idx) {
        return categorias[idx];
    }

    public String getTipoBusqueda(int idx) {
        return tiposBusqueda[idx];
    }

    public void setTipoBusqueda(int idx) {
        this.tipoBusqueda = getTipoBusqueda(idx);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    

}
