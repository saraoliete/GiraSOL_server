/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import myapp.girasol.entity.HabitacionEntity;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.HabitacionRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    
    /**
     * GET Habitacion
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oHabitacionRepository.existsById(id)) {
            return new ResponseEntity<HabitacionEntity>(oHabitacionRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<HabitacionEntity>(oHabitacionRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Create Habitacion
     * Solo tiene permiso el admin
     * @param oHabitacionEntity
     * @return 
     */
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
    
    /**
     * Update Habitacion
     * Solo tiene permiso el admin
     * @param id
     * @param oProductoEntity
     * @return 
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody HabitacionEntity oProductoEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) { //administrador
                oProductoEntity.setId(id);
                if (oHabitacionRepository.existsById(id)) {
                    return new ResponseEntity<HabitacionEntity>(oHabitacionRepository.save(oProductoEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {  //cliente
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

    }
    
    /**
     * Delete Habitacion
     * Solo el admin tiene permiso
     * @param id
     * @return 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {

                oHabitacionRepository.deleteById(id);

                if (oHabitacionRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }
    
    /**
     * Busca las habitaciones por el nombre del tipoabitacion
     * Tanto el admin como el cliente tienen derecho
     * @param strSearch
     * @param oPageable
     * @return 
     */
    @GetMapping("/page")
        public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {

            Page<HabitacionEntity> oPage = oHabitacionRepository.findAll(oPageable);
            return new ResponseEntity<Page<HabitacionEntity>>(oPage, HttpStatus.OK);
        }
    
}
