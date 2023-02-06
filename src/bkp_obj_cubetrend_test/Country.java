/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bkp_obj_cubetrend_test;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author macmini
 */
@Entity
@Table(name = "country")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findById", query = "SELECT c FROM Country c WHERE c.id = :id"),
    @NamedQuery(name = "Country.findByNombre", query = "SELECT c FROM Country c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Country.findByCapital", query = "SELECT c FROM Country c WHERE c.capital = :capital"),
    @NamedQuery(name = "Country.findByHabitantes", query = "SELECT c FROM Country c WHERE c.habitantes = :habitantes"),
    @NamedQuery(name = "Country.findByTemperatura", query = "SELECT c FROM Country c WHERE c.temperatura = :temperatura"),
    @NamedQuery(name = "Country.findByArea", query = "SELECT c FROM Country c WHERE c.area = :area"),
    @NamedQuery(name = "Country.findByAltitud", query = "SELECT c FROM Country c WHERE c.altitud = :altitud"),
    @NamedQuery(name = "Country.findByLocation", query = "SELECT c FROM Country c WHERE c.location = :location")})
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "capital")
    private String capital;
    @Basic(optional = false)
    @Column(name = "habitantes")
    private int habitantes;
    @Basic(optional = false)
    @Lob
    @Column(name = "trends")
    private byte[] trends;
    @Basic(optional = false)
    @Column(name = "temperatura")
    private int temperatura;
    @Basic(optional = false)
    @Column(name = "area")
    private float area;
    @Basic(optional = false)
    @Column(name = "altitud")
    private int altitud;
    @Basic(optional = false)
    @Column(name = "location")
    private int location;

    public Country() {
    }

    public Country(Integer id) {
        this.id = id;
    }

    public Country(Integer id, String nombre, String capital, int habitantes, byte[] trends, int temperatura, float area, int altitud, int location) {
        this.id = id;
        this.nombre = nombre;
        this.capital = capital;
        this.habitantes = habitantes;
        this.trends = trends;
        this.temperatura = temperatura;
        this.area = area;
        this.altitud = altitud;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    public byte[] getTrends() {
        return trends;
    }

    public void setTrends(byte[] trends) {
        this.trends = trends;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getAltitud() {
        return altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bkp_obj_cubetrend_test.Country[ id=" + id + " ]";
    }
    
}
