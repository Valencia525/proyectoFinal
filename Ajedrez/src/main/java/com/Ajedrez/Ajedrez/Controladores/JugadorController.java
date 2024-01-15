package com.Ajedrez.Ajedrez.Controladores;

import com.Ajedrez.Ajedrez.Entidades.Jugador;
import com.Ajedrez.Ajedrez.Repositorios.JugadorRepository;
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
@RequestMapping("/jugadores")
public class JugadorController {
    @Autowired
    private InformePdfService informePdfService;


    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping
    public List<Jugador> obtenerTodosLosJugadores() {
        return jugadorRepository.findAll();
    }

    @GetMapping("/{numero}")
    public Optional<Jugador> obtenerJugadorPorNumero(@PathVariable int numero) {
        return jugadorRepository.findById(numero);
    }

    @PostMapping
    public Jugador crearJugador(@RequestBody Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @PutMapping("/{numero}")
    public Jugador actualizarJugador(@PathVariable int numero, @RequestBody Jugador jugadorActualizado) {
        return jugadorRepository.findById(numero)
                .map(jugador -> {
                    jugador.setNombre(jugadorActualizado.getNombre());
                    jugador.setTelefono(jugadorActualizado.getTelefono());
                    jugador.setNombrepais(jugadorActualizado.getNombrepais());
                    jugador.setPais(jugadorActualizado.getPais());
                    jugador.setHotel(jugadorActualizado.getHotel());
                    jugador.setPartidas(jugadorActualizado.getPartidas());
                    return jugadorRepository.save(jugador);
                })
                .orElseGet(() -> {
                    jugadorActualizado.setNumero(numero);
                    return jugadorRepository.save(jugadorActualizado);
                });
    }

    @DeleteMapping("/{numero}")
    public void eliminarJugador(@PathVariable int numero) {
        jugadorRepository.deleteById(numero);
    }

     @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarInformePdf() throws DecodeException {
        try {
            byte[] informePdfBytes = informePdfService.generarInformeJugadores();

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
