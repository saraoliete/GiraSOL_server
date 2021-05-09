/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import myapp.girasol.resolver.EntityIdResolver;

/**
 *
 * @author Sara
 */

@Entity
@Table(name="habitacion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HabitacionEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhabitacion")
    private Long id;
    
    //clave foranea: tipohabitacion
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_tipohabitacion")
    private TipoHabitacionEntity tipohabitacion;
    
    //relacion con la tabla reserva
    @JsonBackReference
    @OneToMany(fetch=FetchType.LAZY,mappedBy="habitacion", cascade={CascadeType.REFRESH}, orphanRemoval = true)
    private List<ReservaEntity> reserva = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoHabitacionEntity getTipohabitacion() {
        return tipohabitacion;
    }

    public void setTipohabitacion(TipoHabitacionEntity tipohabitacion) {
        this.tipohabitacion = tipohabitacion;
    }
    
    public List<ReservaEntity> getReserva() {
        return reserva;
    }

    public void setReserva(List<ReservaEntity> reserva) {
        this.reserva = reserva;
    }   
    
    
}
