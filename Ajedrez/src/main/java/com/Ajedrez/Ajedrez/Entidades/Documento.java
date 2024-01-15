

package com.Ajedrez.Ajedrez.Entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Documento", schema = "public")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;
    @Column(name = "nombre_documento" , length = 100 , nullable = false)
    private String nombreDocumento;
    @Column(name = "tipo_documento", length = 100, nullable = false)
    private String tipoDocumento;
    @Lob
    @Column(name = "datos_documento", length = 16777215)
    private byte[] datosDocumento;
}
