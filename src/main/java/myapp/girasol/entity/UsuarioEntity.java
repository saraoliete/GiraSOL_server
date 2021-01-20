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
@Table(name="usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsuarioEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long id;
    private String nombre;
    private String password;
    
    //clave foranea: tipousuario
     @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_tipousuario")
    private TipoUsuarioEntity tipousuario;
     
     //relacion con la tabla reserva
     @OneToMany(fetch=FetchType.LAZY,mappedBy="usuario", cascade={CascadeType.REFRESH})
    private List<UsuarioEntity> reserva = new ArrayList<>();
     
     

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuarioEntity getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipoUsuarioEntity tipousuario) {
        this.tipousuario = tipousuario;
    }

    public List<UsuarioEntity> getReserva() {
        return reserva;
    }

    public void setReserva(List<UsuarioEntity> reserva) {
        this.reserva = reserva;
    }
    
    
}
