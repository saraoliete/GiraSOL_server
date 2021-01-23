/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import javax.servlet.http.HttpSession;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.TipoUsuarioRepository;
import myapp.girasol.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Sara
 */
public class UsuarioController {
    
    @Autowired
    HttpSession oHttpSession;
    
    @Autowired
    UsuarioRepository oUsuarioRepository;
    
    @Autowired
    TipoUsuarioRepository oTipoUsuarioRepository;
 
    
}
