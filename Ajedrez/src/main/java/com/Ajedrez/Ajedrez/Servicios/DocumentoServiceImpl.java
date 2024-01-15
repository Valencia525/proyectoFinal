
package com.Ajedrez.Ajedrez.Servicios;

import java.io.FileNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Ajedrez.Ajedrez.Entidades.Documento;
import com.Ajedrez.Ajedrez.Repositorios.DocumentoRepository;

import org.springframework.util.StringUtils;


@Service
public class DocumentoServiceImpl implements DocumentoService {
    @Autowired
    DocumentoRepository documentoRepository;
    @Override
    public Documento guardarArchivo(MultipartFile archivo) throws Exception {
        final String nombreDelArchivo= 
            StringUtils.cleanPath(archivo.getOriginalFilename());
        Documento documento = Documento.builder()
            .nombreDocumento(nombreDelArchivo)
            .tipoDocumento(archivo.getContentType())
            .datosDocumento(archivo.getBytes())
            .build();
        return documentoRepository.save(documento);
    }

    @Override
    public Optional<Documento> descargarArchivo(Long id) throws FileNotFoundException {
            Optional<Documento> archivo = documentoRepository.findById(id);
            if(archivo.isPresent()){
                return archivo;
            }
            throw new FileNotFoundException("No se encontro el archivo con el id: "+id);
        }
        
    }

