/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import myapp.girasol.entity.PensionEntity;
import myapp.girasol.entity.UsuarioEntity;
import myapp.girasol.repository.PensionRepository;
import myapp.girasol.service.PensionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.PageRequest;
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
    @RequestMapping("/pension")
    public class PensionController {
        

        @Autowired
        HttpSession oHttpSession;

        @Autowired
        PensionRepository oPensionRepository;
        
        @Autowired
        PensionesService oPensionesService;
        
        
        /**
         * GET pension
         * @param id
         * @return 
         */
        @GetMapping("/{id}")
        public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
            if (oPensionRepository.existsById(id)) {
                return new ResponseEntity<PensionEntity>(oPensionRepository.getOne(id), HttpStatus.OK);
            } else {
                return new ResponseEntity<PensionEntity>(oPensionRepository.getOne(id), HttpStatus.NOT_FOUND);
            }
        }  
        
        
        @GetMapping("/all")
        public ResponseEntity<?> all() {
            if (oPensionRepository.count() <= 1000) {
                return new ResponseEntity<List<PensionEntity>>(oPensionRepository.findAll(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.PAYLOAD_TOO_LARGE);
            }
    }
        
        /**
         * POST pension
         * @return 
         */
        @PostMapping("/")
        public ResponseEntity<?> create(@RequestBody PensionEntity oPensionEntity) {
            
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
            if (oPensionEntity.getId() == null) {
                        return new ResponseEntity<PensionEntity>(oPensionRepository.save(oPensionEntity), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                    }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
        }
        
        /**
         * Update pension
         * Solo tiene permiso el admin
         * @param id
         * @param oPensionEntity
         * @return 
         */
        @PutMapping("/{id}")
        public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PensionEntity oPensionEntity) {

        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
            if (oPensionRepository.existsById(id)) {
                        return new ResponseEntity<PensionEntity>(oPensionRepository.save(oPensionEntity), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                    }
            } else {
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
        
        oPensionRepository.deleteById(id);

                if (oPensionRepository.existsById(id)) {
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
         * getPage reserva
         * todos pueden
         * @param oPageable
         * @return 
         */
        @GetMapping("/page")
        public ResponseEntity<Page<PensionEntity>> getPage(
         @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String order,
            @RequestParam(defaultValue = "true") boolean asc) {
            
            
            Page<PensionEntity> pensiones = oPensionesService.paginas(
        
                PageRequest.of(page, size, Sort.by(order))
        );
        
            if(!asc){
                pensiones = oPensionesService.paginas(

                        PageRequest.of(page, size, Sort.by(order).descending())
                );
            
            }

            return  new ResponseEntity< Page<PensionEntity>>(pensiones, HttpStatus.OK);
          }
        
    }
