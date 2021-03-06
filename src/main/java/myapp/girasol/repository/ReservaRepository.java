/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.repository;

import myapp.girasol.entity.ReservaEntity;
import myapp.girasol.entity.PensionEntity;
import myapp.girasol.entity.HabitacionEntity;
import myapp.girasol.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Sara
 */
@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long>{

    @Query(value = "SELECT * FROM reserva WHERE id_usuario = :id_usuario", nativeQuery = true)
    public Page<ReservaEntity> findByReservaXIdUsuario(Long id_usuario, Pageable oPageable);
    
}
