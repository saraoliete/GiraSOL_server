/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.api;

import javax.servlet.http.HttpSession;
import myapp.girasol.repository.AdministradorRepository;
import myapp.girasol.repository.ClienteRepository;
import myapp.girasol.repository.HabitacionRepository;
import myapp.girasol.repository.PensionRepository;
import myapp.girasol.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sara
 */

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    HttpSession oHttpSession;

    @Autowired
    ReservaRepository oReservaRepository;
    
    @Autowired
    PensionRepository oPensionRepository;
    
    @Autowired
    HabitacionRepository oHabitacionRepository;
    
    @Autowired
    AdministradorRepository oAdministradorRepository;
    
    @Autowired
    ClienteRepository oClienteRepository;
    
    
    
}