package com.Ajedrez.Ajedrez.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.Ajedrez.Ajedrez.Entidades.Arbitro;
import com.Ajedrez.Ajedrez.Repositorios.ArbitroRepository;

@Service
public class ArbitroService {

    @Autowired
    private ArbitroRepository arbitroRepository;

    public List<Arbitro> obtenerTodosLosArbitros() {
        return arbitroRepository.findAll();
    }

 
}
