/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.service;

import javax.transaction.Transactional;
import myapp.girasol.entity.TipoHabitacionEntity;
import myapp.girasol.repository.TipoHabitacionRepository;
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
public class TipoHabitacionesService {
    
    @Autowired
    TipoHabitacionRepository oTipoHabitacionRepository;
    
    public Page<TipoHabitacionEntity> paginas(Pageable pageable){
        
        return oTipoHabitacionRepository.findAll(pageable);
    }
    
}
