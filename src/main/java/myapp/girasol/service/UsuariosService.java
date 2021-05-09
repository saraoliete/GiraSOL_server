/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.service;

import javax.transaction.Transactional;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sara
 */
@Service
@Transactional
public class UsuariosService {
    
    @Autowired
    UsuarioRepository oUsuarioRepository;
    
    public Page<UsuarioEntity> paginas(Pageable pageable){
        
        return oUsuarioRepository.findAll(pageable);
    }
    
}
