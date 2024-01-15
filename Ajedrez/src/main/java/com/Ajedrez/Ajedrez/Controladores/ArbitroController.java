package com.Ajedrez.Ajedrez.Controladores;

import com.Ajedrez.Ajedrez.Entidades.Arbitro;
import com.Ajedrez.Ajedrez.Repositorios.ArbitroRepository;
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
@RequestMapping("/arbitros")
public class ArbitroController {
   
    @Autowired
    private InformePdfService informePdfService;


    @Autowired
    private ArbitroRepository arbitroRepository;

    @GetMapping
    public List<Arbitro> obtenerTodosLosArbitros() {
        return arbitroRepository.findAll();
    }

    @GetMapping("/{numero}")
    public Optional<Arbitro> obtenerArbitroPorNumero(@PathVariable int numero) {
        return arbitroRepository.findById(numero);
    }

    @PostMapping
    public Arbitro crearArbitro(@RequestBody Arbitro arbitro) {
        return arbitroRepository.save(arbitro);
    }

    @PutMapping("/{numero}")
    public Arbitro actualizarArbitro(@PathVariable int numero, @RequestBody Arbitro arbitroActualizado) {
        return arbitroRepository.findById(numero)
                .map(arbitro -> {
                    arbitro.setNombre(arbitroActualizado.getNombre());
                    arbitro.setTelefono(arbitroActualizado.getTelefono());
                    arbitro.setPais(arbitroActualizado.getPais());
                    arbitro.setHotel(arbitroActualizado.getHotel());
                    return arbitroRepository.save(arbitro);
                })
                .orElseGet(() -> {
                    arbitroActualizado.setNumero(numero);
                    return arbitroRepository.save(arbitroActualizado);
                });
    }

    @DeleteMapping("/{numero}")
    public void eliminarArbitro(@PathVariable int numero) {
        arbitroRepository.deleteById(numero);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarInformePdf() throws DecodeException {
        try {
            byte[] informePdfBytes = informePdfService.generarInformeArbitros();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=informe_ARBITRO.pdf");

            return new ResponseEntity<>(informePdfBytes, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            e.printStackTrace(); // Manejo adecuado de errores en producción
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
