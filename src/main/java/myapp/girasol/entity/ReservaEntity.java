/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
import myapp.girasol.resolver.EntityIdResolver;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Sara
 */

@Entity
@Table(name="reserva")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReservaEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreserva")
    private Long id;
    
    //clave foranea: usuario
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_usuario")
    private UsuarioEntity usuario;
    
    @Column(name = "fecha_llegada")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_llegada;
    
    @Column(name = "fecha_final")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_final;
    
    // clave foranea: habitacion
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_pension")
    private PensionEntity pension;
    
    //clave foranea: habitacion
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_habitacion")
    private HabitacionEntity habitacion;
    
    @Column(name = "cama_supletoria")
    private boolean cama_supletoria;
    
    @Column(name = "precio_final")
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

    public Date getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(Date fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
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

    public boolean getCama_supletoria() {
       
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
