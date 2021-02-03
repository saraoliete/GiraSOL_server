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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sara
 */
public class UsuarioController {
    
    @Autowired
    HttpSession oHttpSession;
    
    @Autowired
    UsuarioRepository oUsuarioRepository;
    
    @Autowired
    TipoUsuarioRepository oTipoUsuarioRepository;
    
    //listado de usuarios para el administrador
   /** @GetMapping("/page")
    public ResponseEntity<?> getPage(@RequestParam("filter") Optional<String> strSearch, @PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable
    ) {
        
       UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        
       if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                
                Page<UsuarioEntity> oPage = null;
                if (strSearch.isPresent()) {
                    oPage = oUsuarioRepository.findByCodigoContainingIgnoreCaseOrNombreContainingIgnoreCaseOrTipoproductoNombreContainingIgnoreCase(strSearch.get(), strSearch.get(), strSearch.get(), oPageable);
                } else {
                    oPage = oUsuarioRepository.findAll(oPageable);
                }
                return new ResponseEntity<Page<UsuarioEntity>>(oPage, HttpStatus.OK);

                    }
       }
            
            
    }*/
 
        //listado del usuario que ha iniciado la sesion
    /**
     * 
     * @param oPageable
     * @param id
     * @return 
     * 
     * @GetMapping("/miUsuario")
    public ResponseEntity<?> getUsuarioXTipUsuario(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oTipoUsuarioRepository.existsById(id)) {
            TipoUsuarioEntity oTipoUsuarioEntity = oTipoUsuarioRepository.getOne(id);
            Page<UsuarioEntity> oPage = oUsuarioRepository.findByTipousuario(oTipoUsuarioEntity, oPageable);
            return new ResponseEntity<Page<UsuarioEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
     */
     
}
