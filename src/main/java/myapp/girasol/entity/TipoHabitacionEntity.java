/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.entity;

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
    private Integer id;
    private String nombre;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="tipo_habitacion", cascade={CascadeType.REFRESH})
    private List<HabitacionEntity> habitaciones = new ArrayList<>(); 

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

    public List<HabitacionEntity> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<HabitacionEntity> habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    
    
}
