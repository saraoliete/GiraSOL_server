/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import javax.servlet.http.HttpSession;
import myapp.girasol.entity.ReservaEntity;
import myapp.girasol.repository.AdministradorRepository;
import myapp.girasol.repository.ClienteRepository;
import myapp.girasol.repository.HabitacionRepository;
import myapp.girasol.repository.PensionRepository;
import myapp.girasol.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    AdministradorRepository oAdministradorRepository;
    
    @Autowired
    ClienteRepository oClienteRepository;
    
    @GetMapping("/{id}")
            public ResponseEntity<?> get(@PathVariable(value = "idpension") Long id) {
                if (oReservaRepository.existsById(id)) {
                    return new ResponseEntity<ReservaEntity>(oReservaRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<ReservaEntity>(oReservaRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            }   
    
    
    
}
