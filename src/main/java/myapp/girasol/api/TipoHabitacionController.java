/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import myapp.girasol.entity.TipoHabitacionEntity;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sara
 */

@RestController
@RequestMapping("/tipohabitacion")
public class TipoHabitacionController {
    
    
    @Autowired
    HttpSession oHttpSession;
    
    @Autowired
    TipoHabitacionRepository oTipoHabitacionRepository;
    
    /**
     * GET TipoHabitacion
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oTipoHabitacionRepository.existsById(id)) {
            return new ResponseEntity<TipoHabitacionEntity>(oTipoHabitacionRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<TipoHabitacionEntity>(oTipoHabitacionRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oTipoHabitacionRepository.count() <= 1000) {
            return new ResponseEntity<List<TipoHabitacionEntity>>(oTipoHabitacionRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }
    
    /**
     * Update TipoHabitacion
     * Solo el admin tiene permiso
     * @param id
     * @param oTipoHabitacionEntity
     * @return 
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TipoHabitacionEntity oTipoHabitacionEntity) {
        
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
       
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }else{
                if (oUsuarioEntity.getTipousuario().getId() == 1) { //Administrador

                    oTipoHabitacionEntity.setId(id);
                    if (oTipoHabitacionRepository.existsById(id)) {
                        return new ResponseEntity<TipoHabitacionEntity>(oTipoHabitacionRepository.save(oTipoHabitacionEntity), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                    }
                    
                }else{

                        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                    }
            }           
        }
    
    /**
     * Page tipoHabitacion
     * @param oPageable
     * @return 
     */
    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {

        Page<TipoHabitacionEntity> oPage = oTipoHabitacionRepository.findAll(oPageable);
        return new ResponseEntity<Page<TipoHabitacionEntity>>(oPage, HttpStatus.OK);
        
        
    }
    
    }
