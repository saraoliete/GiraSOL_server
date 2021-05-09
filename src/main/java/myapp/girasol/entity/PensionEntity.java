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
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import myapp.girasol.resolver.EntityIdResolver;

/**
 *
 * @author Sara
 */

@Entity
@Table(name="pension")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PensionEntity implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpension")
    private Long id;
    
    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "precio")
    private Double precio;
    
    // relacion con la tabla reserva
    @JsonBackReference
    @OneToMany(fetch=FetchType.LAZY,mappedBy="pension", cascade={CascadeType.REFRESH}, orphanRemoval = true)
    private List<ReservaEntity> reserva = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public List<ReservaEntity> getReserva() {
        return reserva;
    }

    public void setReserva(List<ReservaEntity> reserva) {
        this.reserva = reserva;
    }
    
    
}
