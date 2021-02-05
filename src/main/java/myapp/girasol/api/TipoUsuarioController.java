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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/tipousuario")
public class TipoUsuarioController {
    
    
     @Autowired
    HttpSession oHttpSession;
    
    @Autowired
    TipoUsuarioRepository oTipoUsuarioRepository;
    
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
    
    /**
     * Page tipoUsuario
     * @param oPageable
     * @return 
     */
    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {

        Page<TipoUsuarioEntity> oPage = oTipoUsuarioRepository.findAll(oPageable);
        return new ResponseEntity<Page<TipoUsuarioEntity>>(oPage, HttpStatus.OK);
    }

}
