/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sara
 */

@Entity
@Table(name="tipo_habitacion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoHabitacionEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipohabitacion")
    private Long id;
    private String nombre;
    private Integer numero_camas;
    private String descripcion;
    private Double precio;
    
    
    // relacion con la tabla habitacion
    @JsonBackReference
    @OneToMany(fetch=FetchType.LAZY,mappedBy="tipohabitacion", cascade={CascadeType.REFRESH})
    private List<HabitacionEntity> habitacion = new ArrayList<>(); 
    

    private Long id_file;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero_camas() {
        return numero_camas;
    }

    public void setNumero_camas(Integer numero_camas) {
        this.numero_camas = numero_camas;
    }

    public List<HabitacionEntity> getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(List<HabitacionEntity> habitacion) {
        this.habitacion = habitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

     /**
     * @return the id_file
     */
    public Long getId_file() {
        return id_file;
    }

    /**
     * @param id_file the id_file to set
     */
    public void setId_file(Long id_file) {
        this.id_file = id_file;
    }
    
    
    
}
