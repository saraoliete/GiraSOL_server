/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import myapp.girasol.entity.HabitacionEntity;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.HabitacionRepository;
import myapp.girasol.repository.TipoHabitacionRepository;
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
@RequestMapping("/habitacion")
public class HabitacionController {
    
     
    @Autowired
    HttpSession oHttpSession;
    
   @Autowired
    HabitacionRepository oHabitacionRepository;
    
    @Autowired
    TipoHabitacionRepository oTipoHabitacionRepository;    
    
    //permite ver todas las habitaciones que hay
    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return new ResponseEntity<List<HabitacionEntity>>(oHabitacionRepository.findAll(), HttpStatus.OK);
        
    }
    
    //permite al administrador crear una habitacion
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody HabitacionEntity oHabitacionEntity) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oHabitacionEntity.getId() == null) {
                    return new ResponseEntity<HabitacionEntity>(oHabitacionRepository.save(oHabitacionEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }
    
}
