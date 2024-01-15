package com.Ajedrez.Ajedrez.Controladores;

import com.Ajedrez.Ajedrez.Entidades.Hotel;
import com.Ajedrez.Ajedrez.Repositorios.HotelRepository;
import com.Ajedrez.Ajedrez.Servicios.InformePdfService;
import com.itextpdf.text.DocumentException;

import jakarta.websocket.DecodeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/hoteles")
public class HotelController {
    @Autowired
    private InformePdfService informePdfService;


    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> obtenerTodosLosHoteles() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{numero}")
    public Optional<Hotel> obtenerHotelPorNumero(@PathVariable int numero) {
        return hotelRepository.findById(numero);
    }

    @PostMapping
    public Hotel crearHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @PutMapping("/{numero}")
    public Hotel actualizarHotel(@PathVariable int numero, @RequestBody Hotel hotelActualizado) {
        return hotelRepository.findById(numero)
                .map(hotel -> {
                    hotel.setNombre(hotelActualizado.getNombre());
                    hotel.setTelefono(hotelActualizado.getTelefono());
                    hotel.setDireccion(hotelActualizado.getDireccion());
                    hotel.setJugadores(hotelActualizado.getJugadores());
                    hotel.setArbitros(hotelActualizado.getArbitros());
                    hotel.setSalas(hotelActualizado.getSalas());
                    return hotelRepository.save(hotel);
                })
                .orElseGet(() -> {
                    hotelActualizado.setNumero(numero);
                    return hotelRepository.save(hotelActualizado);
                });
    }

    @DeleteMapping("/{numero}")
    public void eliminarHotel(@PathVariable int numero) {
        hotelRepository.deleteById(numero);
    }

     @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarInformePdf() throws DecodeException {
        try {
            byte[] informePdfBytes = informePdfService.generarInformeHoteles();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=informe_hotel.pdf");

            return new ResponseEntity<>(informePdfBytes, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            e.printStackTrace(); // Manejo adecuado de errores en producción
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
