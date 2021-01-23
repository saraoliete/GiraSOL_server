/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.bean;

/**
 *
 * @author Sara
 */
public class UsuarioBean {
    
     private String nombre_usuario;
    private String password;

    public String getNombre_Usuario() {
        return nombre_usuario;
    }

    public void setNombre_Usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
