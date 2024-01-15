package com.Ajedrez.Ajedrez.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.Ajedrez.Ajedrez.Entidades.Hotel;
import com.Ajedrez.Ajedrez.Repositorios.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> obtenerTodosLosHoteles() {
        return hotelRepository.findAll();
    }

   
}