/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import myapp.girasol.entity.TipoUsuarioEntity;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sara
 */

@RestController
@RequestMapping("/tipousuario")
public class TipoUsuarioController {
    
    
     @Autowired
    HttpSession oHttpSession;
    
    @Autowired
    TipoUsuarioRepository oTipoUsuarioRepository;
    
    //permite al administrador crear un tipo de usuario
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TipoUsuarioEntity oTipoUsuarioEntity) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oTipoUsuarioEntity.getId() == null) {
                    return new ResponseEntity<TipoUsuarioEntity>(oTipoUsuarioRepository.save(oTipoUsuarioEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }
    
    
}
