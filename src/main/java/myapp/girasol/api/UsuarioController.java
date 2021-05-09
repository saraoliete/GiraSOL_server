/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import myapp.girasol.entity.TipoUsuarioEntity;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.TipoUsuarioRepository;
import myapp.girasol.repository.UsuarioRepository;
import myapp.girasol.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    HttpSession oHttpSession;
    
    @Autowired
    UsuarioRepository oUsuarioRepository;
    
    @Autowired
    TipoUsuarioRepository oTipoUsuarioRepository;
    
    @Autowired
    UsuariosService oUsuariosService;
    
    /**
     * GET
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {

        if (oUsuarioRepository.existsById(id)) {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
        
    }    
    
    /**
     * //solo el administrador puede crear usuarios
     * aunque quiero que un usuario no registrado pueda registrarse
     * @param oNewUsuarioEntity
     * @return 
     */
   @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UsuarioEntity oNewUsuarioEntity) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oNewUsuarioEntity.getId() == null) {
                    oNewUsuarioEntity.setPassword("b9da943bf1dcb00b784cf3612d450f91");
                    oNewUsuarioEntity.setActivo(false);
                    oNewUsuarioEntity.setValidado(false);
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oNewUsuarioEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
    }
    
    
    /**
     * editar usuario
     * El administrador puede editar a cualquier usuario
     * El cliente solo puede editarse a si mismo
     * @param id
     * @param oUsuarioEntity
     * @return 
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioEntity oUsuarioEntity) {

        UsuarioEntity oUsuarioEntity2 = (UsuarioEntity) oHttpSession.getAttribute("usuario"); // para ver si ingresas como admin o cliente

        if (oUsuarioEntity2 == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity2.getTipousuario().getId() == 1) { //administrador
                if (oUsuarioRepository.existsById(id)) {
                    UsuarioEntity oUsuarioEntity3 = oUsuarioRepository.getOne(id);
                    oUsuarioEntity.setPassword(oUsuarioEntity3.getPassword());
                    oUsuarioEntity.setToken(oUsuarioEntity3.getToken());
                    oUsuarioEntity.setActivo(oUsuarioEntity3.isActivo());
                    oUsuarioEntity.setValidado(oUsuarioEntity3.isValidado());
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {  //cliente
                if (oUsuarioEntity2.getId() == id) {
                    UsuarioEntity oUsuarioEntity3 = oUsuarioRepository.getOne(id);
                    oUsuarioEntity.setPassword(oUsuarioEntity3.getPassword());
                    oUsuarioEntity.setToken(oUsuarioEntity3.getToken());
                    oUsuarioEntity.setActivo(oUsuarioEntity3.isActivo());
                    oUsuarioEntity.setValidado(oUsuarioEntity3.isValidado());
                    return new ResponseEntity<UsuarioEntity>(oUsuarioRepository.save(oUsuarioEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            }
        }
    }
    
    
    /**
     * Solo el administrador puede borrar usuarios
     * @param id
     * @return 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        if (oUsuarioRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
    }
    
    
    /**
     * listado de todos los usuarios
     * Esto solo lo puede hacer el admin
     * @param oPageable
     * @return 
     */
    @GetMapping("/page")
    public ResponseEntity<Page<UsuarioEntity>> getPage(
         @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idusuario") String order,
            @RequestParam(defaultValue = "true") boolean asc){
        
        
        Page<UsuarioEntity> usuarios = oUsuariosService.paginas(
        
                PageRequest.of(page, size, Sort.by(order))
        );
        
            if(!asc){
                usuarios = oUsuariosService.paginas(

                        PageRequest.of(page, size, Sort.by(order).descending())
                );
            
            }

        return new ResponseEntity<Page<UsuarioEntity>>(usuarios, HttpStatus.OK);
            
    }
        
           
     
}
