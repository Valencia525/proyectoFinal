// Version: 1.0
// Date: 02/05/2021
package com.Ajedrez.Ajedrez.Controladores;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.Ajedrez.Ajedrez.Entidades.Documento;
import com.Ajedrez.Ajedrez.Servicios.DocumentoService;
import com.Ajedrez.Ajedrez.dto.RespuestaDTO;

@RestController
@RequestMapping("/apiDocumentos/Documentos")
public class DocumentoController {

    @Autowired
    DocumentoService documentoService;

    @PostMapping("/SubirArchivo")
    public ResponseEntity<RespuestaDTO> subirArchivo(@RequestParam MultipartFile archivo) throws IOException {
        try {
            Documento documento = documentoService.guardarArchivo(archivo);
            
            if (documento == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new RespuestaDTO("No se pudo guardar el archivo"));
    }
        } catch (Exception e) {
            
           return ResponseEntity.status(HttpStatus.OK)
                .body(new RespuestaDTO("Se guardó el archivo correctamente"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RespuestaDTO("Se guardó el archivo correctamente"));
    }

    @GetMapping("/DescargarDocumento/{id}")
    public ResponseEntity<byte[]> descargarDocumento(@PathVariable Long id) throws FileNotFoundException {
        Documento documento = documentoService.descargarArchivo(id).orElseThrow(() -> new FileNotFoundException("Documento no encontrado"));
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, documento.getTipoDocumento())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + StringUtils.cleanPath(documento.getNombreDocumento()) + "\"")
                .body(documento.getDatosDocumento());
    }
}
