package com.Ajedrez.Ajedrez.Controladores;

import com.Ajedrez.Ajedrez.Entidades.Pais;
import com.Ajedrez.Ajedrez.Repositorios.PaisRepository;
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
@RequestMapping("/paises")
public class PaisController {
    @Autowired
    private InformePdfService informePdfService;


    @Autowired
    private PaisRepository paisRepository;

    @GetMapping
    public List<Pais> obtenerTodosLosPaises() {
        return paisRepository.findAll();
    }

    @GetMapping("/{numero}")
    public Optional<Pais> obtenerPaisPorNumero(@PathVariable int numero) {
        return paisRepository.findById(numero);
    }

    @PostMapping
    public Pais crearPais(@RequestBody Pais pais) {
        return paisRepository.save(pais);
    }

    @PutMapping("/{numero}")
    public Pais actualizarPais(@PathVariable int numero, @RequestBody Pais paisActualizado) {
        return paisRepository.findById(numero)
                .map(pais -> {
                    pais.setNombre(paisActualizado.getNombre());
                    pais.setJugadores(paisActualizado.getJugadores());
                    return paisRepository.save(pais);
                })
                .orElseGet(() -> {
                    paisActualizado.setNumero(numero);
                    return paisRepository.save(paisActualizado);
                });
    }

    @DeleteMapping("/{numero}")
    public void eliminarPais(@PathVariable int numero) {
        paisRepository.deleteById(numero);
    }

     /**
     * @return
     * @throws DecodeException
     */
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarInformePdf() throws DecodeException {
        try {
            byte[] informePdfBytes = informePdfService.generarInformePaises();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=informe_pais.pdf");

            return new ResponseEntity<>(informePdfBytes, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            e.printStackTrace(); // Manejo adecuado de errores en producción
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
