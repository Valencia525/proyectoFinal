package com.Ajedrez.Ajedrez.Controladores;

import com.Ajedrez.Ajedrez.Entidades.Sala;
import com.Ajedrez.Ajedrez.Repositorios.SalaRepository;
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
@RequestMapping("/salas")
public class SalaController {
    @Autowired
    private InformePdfService informePdfService;


    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public List<Sala> obtenerTodasLasSalas() {
        return salaRepository.findAll();
    }

    @GetMapping("/{numero}")
    public Optional<Sala> obtenerSalaPorNumero(@PathVariable int numero) {
        return salaRepository.findById(numero);
    }

    @PostMapping
    public Sala crearSala(@RequestBody Sala sala) {
        return salaRepository.save(sala);
    }

    @PutMapping("/{numero}")
    public Sala actualizarSala(@PathVariable int numero, @RequestBody Sala salaActualizada) {
        return salaRepository.findById(numero)
                .map(sala -> {
                    sala.setCapacidad(salaActualizada.getCapacidad());
                    sala.setHotel(salaActualizada.getHotel());
                    sala.setPartidas(salaActualizada.getPartidas());
                    return salaRepository.save(sala);
                })
                .orElseGet(() -> {
                    salaActualizada.setNumero(numero);
                    return salaRepository.save(salaActualizada);
                });
    }

    @DeleteMapping("/{numero}")
    public void eliminarSala(@PathVariable int numero) {
        salaRepository.deleteById(numero);
    }
     @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarInformePdf() throws DecodeException {
        try {
            byte[] informePdfBytes = informePdfService.generarInformeSalas();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=informe_sala.pdf");

            return new ResponseEntity<>(informePdfBytes, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            e.printStackTrace(); // Manejo adecuado de errores en producción
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
