/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name="tipo_usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoUsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipousuario")
    private Long id;
    private String nombre;
    
    //relacion con la tabla usuario
   @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy="tipousuario", cascade={CascadeType.REFRESH})
    private List<UsuarioEntity> usuario = new ArrayList<>();

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

    public List<UsuarioEntity> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<UsuarioEntity> usuario) {
        this.usuario = usuario;
    }
    
    
    
}
