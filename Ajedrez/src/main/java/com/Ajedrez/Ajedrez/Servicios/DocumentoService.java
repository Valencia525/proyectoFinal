
package com.Ajedrez.Ajedrez.Servicios;


import java.io.FileNotFoundException;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.Ajedrez.Ajedrez.Entidades.Documento;

public interface DocumentoService {
    public Documento guardarArchivo(MultipartFile archivo)throws Exception;

    public Optional<Documento> descargarArchivo(Long id)throws FileNotFoundException;


}
