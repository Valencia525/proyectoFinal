
package com.Ajedrez.Ajedrez.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import com.Ajedrez.Ajedrez.Entidades.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    

}
