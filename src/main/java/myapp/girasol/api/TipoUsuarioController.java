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
import myapp.girasol.service.TipoUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/tipousuario")
public class TipoUsuarioController {
    
    
     @Autowired
    HttpSession oHttpSession;
    
    @Autowired
    TipoUsuarioRepository oTipoUsuarioRepository;
    
    @Autowired
    TipoUsuariosService oTipoUsuariosService;
    
    /**
     * GET tipoUsuario
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oTipoUsuarioRepository.existsById(id)) {
            return new ResponseEntity<TipoUsuarioEntity>(oTipoUsuarioRepository.getOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<TipoUsuarioEntity>(oTipoUsuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping("/all")
    public ResponseEntity<?> all() {
        if (oTipoUsuarioRepository.count() <= 1000) {
            return new ResponseEntity<List<TipoUsuarioEntity>>(oTipoUsuarioRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }
    
    
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TipoUsuarioEntity oTipoUsuarioEntity) {
        
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
       
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }else{
                if (oUsuarioEntity.getTipousuario().getId() == 1) { //Administrador
        
        if (oTipoUsuarioEntity.getId() == null) {
                    return new ResponseEntity<TipoUsuarioEntity>(oTipoUsuarioRepository.save(oTipoUsuarioEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
        
        }else{

                        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                    }
            }    
    }
    
    /**
     * Update TipoUsuario
     * Solo el admin tiene permiso
     * @param id
     * @param oTipoUsuarioEntity
     * @return 
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TipoUsuarioEntity oTipoUsuarioEntity) {
        
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
       
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }else{
                if (oUsuarioEntity.getTipousuario().getId() == 1) { //Administrador

                    oTipoUsuarioEntity.setId(id);
                    if (oTipoUsuarioRepository.existsById(id)) {
                        return new ResponseEntity<TipoUsuarioEntity>(oTipoUsuarioRepository.save(oTipoUsuarioEntity), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                    }
                    
                }else{

                        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                    }
            }           
        }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        
        oTipoUsuarioRepository.deleteById(id);
        
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
       
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }else{
                if (oUsuarioEntity.getTipousuario().getId() == 1) { //Administrador

                if (oTipoUsuarioRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
                
              }else{

                        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                    }
            }
    }
    
    
    /**
     * Page tipoUsuario
     * @param oPageable
     * @return 
     */
   @GetMapping("/page")
    public ResponseEntity<Page<TipoUsuarioEntity>> getAllTipoUsuarios(
            
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String order,
            @RequestParam(defaultValue = "true") boolean asc) {
        
        Page<TipoUsuarioEntity> TipoUsuarios = oTipoUsuariosService.paginas(
        
                PageRequest.of(page, size, Sort.by(order))
        );
        
        if(!asc){
            TipoUsuarios = oTipoUsuariosService.paginas(
            
                    PageRequest.of(page, size, Sort.by(order).descending())
            );
            
        }
        
        return  new ResponseEntity< Page<TipoUsuarioEntity>>(TipoUsuarios, HttpStatus.OK);
        
    }

}
