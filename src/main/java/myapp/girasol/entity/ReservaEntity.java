/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Sara
 */

@Entity
@Table(name="reserva")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReservaEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreserva")
    private Long id;
    
    //clave foranea: usuario
     @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_usuario")
    private UsuarioEntity usuario;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime fecha_llegada;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime fecha_final;
    
    // clave foranea: habitacion
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_pension")
    private PensionEntity pension;
    
    //clave foranea: habitacion
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_habitacion")
    private HabitacionEntity habitacion;
    
    private boolean cama_supletoria;
    private Double precio_final;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(LocalDateTime fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public LocalDateTime getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(LocalDateTime fecha_final) {
        this.fecha_final = fecha_final;
    }

    public PensionEntity getPension() {
        return pension;
    }

    public void setPension(PensionEntity pension) {
        this.pension = pension;
    }

    public HabitacionEntity getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionEntity habitacion) {
        this.habitacion = habitacion;
    }

    public boolean isCama_supletoria() {
        return cama_supletoria;
    }

    public void setCama_supletoria(boolean cama_supletoria) {
        this.cama_supletoria = cama_supletoria;
    }

    public Double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(Double precio_final) {
        this.precio_final = precio_final;
    }
    
    
    
}
