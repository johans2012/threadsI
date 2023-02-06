/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author IEUser
 */
public class Citizen  implements Serializable{

    protected int id;
    protected int edad;
    /**
     * Enable other options to add scope to app
     *
     * Man, woman, and Others ... *
     *
     * String[] sex = ("man", "woman", "other", "etc")
     *
     */
    protected String sexo;
    protected String nombre;
    protected String empleo;
    protected int salario;
    /**
     * Data to mining
     **
     */
    protected String eps;
    protected String caja_compensacion;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        this.id = 10;
        
        return this.id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpleo() {
        return empleo;
    }

    public void setEmpleo(String empleo) {
        this.empleo = empleo;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getCompensacion() {
        return caja_compensacion;
    }

    public void setCompensacion(String caja_compensacion) {
        this.caja_compensacion = caja_compensacion;
    }
}
