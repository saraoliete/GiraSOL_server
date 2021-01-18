/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import javax.servlet.http.HttpSession;
import myapp.girasol.entity.AdministradorEntity;
import myapp.girasol.repository.AdministradorRepository;
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
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    HttpSession oHttpSession;
    
    @Autowired
    AdministradorRepository oAdministradorRepository;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "idhabitacion") Long id) {
        if (oAdministradorRepository.existsById(id)) {
            return new ResponseEntity<AdministradorEntity>(oAdministradorRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<AdministradorEntity>(oAdministradorRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }
}
