/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.service;

import javax.transaction.Transactional;
import myapp.girasol.entity.PensionEntity;
import myapp.girasol.repository.PensionRepository;
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
public class PensionesService {
    
    @Autowired
    PensionRepository oPensionRepository;
    
    public Page<PensionEntity> paginas(Pageable pageable){
        
        return oPensionRepository.findAll(pageable);
    }
}
