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
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long id;
    private String dni;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private char sexo;
    private Integer telefono;
    private String email;
    private String nombre_usuario;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
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

    @Override
    public String toString() {
        return "UsuarioEntity{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", sexo=" + sexo + ", telefono=" + telefono + ", email=" + email + ", nombre_usuario=" + nombre_usuario + ", password=" + password + '}';
    }

    public List<UsuarioEntity> getReserva() {
        return reserva;
    }

    public void setReserva(List<UsuarioEntity> reserva) {
        this.reserva = reserva;
    }
     
        
    
}
