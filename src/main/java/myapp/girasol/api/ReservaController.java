/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import myapp.girasol.entity.ReservaEntity;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.HabitacionRepository;
import myapp.girasol.repository.PensionRepository;
import myapp.girasol.repository.ReservaRepository;
import myapp.girasol.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sara
 */

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    HttpSession oHttpSession;
     
    @Autowired
    ReservaRepository oReservaRepository;
    
    @Autowired
    PensionRepository oPensionRepository;
    
    @Autowired
    HabitacionRepository oHabitacionRepository;
    
    @Autowired
    UsuarioRepository oUsuarioRepository;
    
    //saca reservas en funcion del usuario que se haya logueado
    @GetMapping("/reservas")
    public ResponseEntity<?> getReservaXUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oUsuarioRepository.existsById(id)) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.getOne(id);
            Page<ReservaEntity> oPage = oUsuarioRepository.findByUsuario(oUsuarioEntity, oPageable);
            return new ResponseEntity<Page<ReservaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
   
}
