package com.Ajedrez.Ajedrez.Servicios;
import com.Ajedrez.Ajedrez.Entidades.Jugador;
import com.Ajedrez.Ajedrez.Repositorios.JugadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> obtenerTodosLosJugadores() {
        return jugadorRepository.findAll();
    }


    // Otros métodos del servicio según sea necesario
}
