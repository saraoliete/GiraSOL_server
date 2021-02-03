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
    
     private String nombreusuario;
    private String password;

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreUsuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
