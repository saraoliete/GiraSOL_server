/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.repository;

import myapp.girasol.entity.HabitacionEntity;
import myapp.girasol.entity.TipoHabitacionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sara
 */
@Repository
public interface HabitacionRepository extends JpaRepository<HabitacionEntity, Long>{
    
}
