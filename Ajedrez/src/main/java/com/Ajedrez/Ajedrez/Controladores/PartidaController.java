package com.Ajedrez.Ajedrez.Controladores;

import com.Ajedrez.Ajedrez.Entidades.Partida;
import com.Ajedrez.Ajedrez.Repositorios.PartidaRepository;
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
@RequestMapping("/partidas")
public class PartidaController {
    @Autowired
    private InformePdfService informePdfService;


    @Autowired
    private PartidaRepository partidaRepository;

    @GetMapping
    public List<Partida> obtenerTodasLasPartidas() {
        return partidaRepository.findAll();
    }

    @GetMapping("/{numero}")
    public Optional<Partida> obtenerPartidaPorNumero(@PathVariable int numero) {
        return partidaRepository.findById(numero);
    }

    @PostMapping
    public Partida crearPartida(@RequestBody Partida partida) {
        return partidaRepository.save(partida);
    }

    @PutMapping("/{numero}")
    public Partida actualizarPartida(@PathVariable int numero, @RequestBody Partida partidaActualizada) {
        return partidaRepository.findById(numero)
                .map(partida -> {
                    partida.setFecha(partidaActualizada.getFecha());
                    partida.setNumeroEntradas(partidaActualizada.getNumeroEntradas());
                    partida.setArbitro(partidaActualizada.getArbitro());
                    partida.setSala(partidaActualizada.getSala());
                    partida.setJugadores(partidaActualizada.getJugadores());
                    return partidaRepository.save(partida);
                })
                .orElseGet(() -> {
                    partidaActualizada.setNumero(numero);
                    return partidaRepository.save(partidaActualizada);
                });
    }

    @DeleteMapping("/{numero}")
    public void eliminarPartida(@PathVariable int numero) {
        partidaRepository.deleteById(numero);
    }
     @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarInformePdf() throws DecodeException {
        try {
            byte[] informePdfBytes = informePdfService.generarInformePartidas();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=informe_partida.pdf");

            return new ResponseEntity<>(informePdfBytes, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            e.printStackTrace(); // Manejo adecuado de errores en producción
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
