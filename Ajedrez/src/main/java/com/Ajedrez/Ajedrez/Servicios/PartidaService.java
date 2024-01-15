package com.Ajedrez.Ajedrez.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.Ajedrez.Ajedrez.Entidades.Partida;
import com.Ajedrez.Ajedrez.Repositorios.PartidaRepository;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    public List<Partida> obtenerTodasLasPartidas() {
        return partidaRepository.findAll();
    }



    // Otros métodos del servicio según sea necesario
}
