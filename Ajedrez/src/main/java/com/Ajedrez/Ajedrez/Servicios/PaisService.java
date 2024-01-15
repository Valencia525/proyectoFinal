package com.Ajedrez.Ajedrez.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.Ajedrez.Ajedrez.Entidades.Pais;
import com.Ajedrez.Ajedrez.Repositorios.PaisRepository;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> obtenerTodosLosPaises() {
        return paisRepository.findAll();
    }

    

    // Otros métodos del servicio según sea necesario
}
