/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Sara
 */
public class TipoUsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipousuario")
    private Long id;
    private String nombre;
    
    //relacion con la tabla usuario
    @OneToMany(fetch=FetchType.LAZY,mappedBy="tipo_usuario", cascade={CascadeType.REFRESH})
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
