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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private Integer numero_camas;
    private String descripcion;
    private Double precio;
    private boolean cama_supletoria;
    
    //clave foranea: tipohabitacion
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_tipohabitacion")
    private TipoHabitacionEntity tipohabitacion;
    
    //relacion con la tabla reserva
    @OneToMany(fetch=FetchType.LAZY,mappedBy="habitacion", cascade={CascadeType.REFRESH})
    private List<ReservaEntity> reserva = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero_camas() {
        return numero_camas;
    }

    public void setNumero_camas(Integer numero_camas) {
        this.numero_camas = numero_camas;
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

    public boolean isCama_supletoria() {
        return cama_supletoria;
    }

    public void setCama_supletoria(boolean cama_supletoria) {
        this.cama_supletoria = cama_supletoria;
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
