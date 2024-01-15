package com.Ajedrez.Ajedrez.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.Ajedrez.Ajedrez.Entidades.Sala;
import com.Ajedrez.Ajedrez.Repositorios.SalaRepository;


@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> obtenerTodasLasSalas() {
        return salaRepository.findAll();
    }

   

    // Otros métodos del servicio según sea necesario
}
