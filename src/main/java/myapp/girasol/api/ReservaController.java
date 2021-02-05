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
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    
    /**
     * GET reserva
     * El admin puede ver todas las reservas realizadas
     * El cliente puede ver sus propias reservas
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oReservaRepository.existsById(id)) {
                    return new ResponseEntity<ReservaEntity>(oReservaRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<ReservaEntity>(oReservaRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {
                if (id.equals(oUsuarioEntity.getId())) {  //los datos pedidos por el cliente son sus propios datos
                    return new ResponseEntity<ReservaEntity>(oReservaRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            }
        }
    }
    
    /**
     * create reserva
     * Todos pueden crear reservas siempre y cuando se hayan logueado
     * @param oReservaEntity
     * @return 
     */
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ReservaEntity oReservaEntity) {

       UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oReservaEntity.getId() == null) {
                    return new ResponseEntity<ReservaEntity>(oReservaRepository.save(oReservaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
        }
    }
    
    /**
     * Delete reserva
     * Solo el admin puede hacerlo
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
                oReservaRepository.deleteById(id);
                if (oReservaRepository.existsById(id)) {
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
     * Page reserva
     * El admin puede ver todas las reservas realizadas
     * El clietne puede ver sus propias resrvas
     * @param oPageable
     * @return 
     */
    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");

        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        } else {

            if (oUsuarioEntity.getTipousuario().getId() == 1) { //Es administrador

                return new ResponseEntity<Page<ReservaEntity>>(oReservaRepository.findAll(oPageable), HttpStatus.OK);

            } else {  //Es cliente (puede ver sus propias compras)

                return new ResponseEntity<Page<ReservaEntity>>(oReservaRepository.findByReservaXIdUsuario(oUsuarioEntity.getId(), oPageable), HttpStatus.OK);
            }
        }

    }
}
