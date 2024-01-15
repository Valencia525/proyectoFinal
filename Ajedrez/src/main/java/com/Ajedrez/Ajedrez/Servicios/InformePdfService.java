package com.Ajedrez.Ajedrez.Servicios;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.Ajedrez.Ajedrez.Entidades.Arbitro;
import com.Ajedrez.Ajedrez.Entidades.Hotel;
import com.Ajedrez.Ajedrez.Entidades.Jugador;
import com.Ajedrez.Ajedrez.Entidades.Pais;
import com.Ajedrez.Ajedrez.Entidades.Partida;
import com.Ajedrez.Ajedrez.Entidades.Sala;

@Service
public class InformePdfService {

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private ArbitroService arbitroService;

    public byte[] generarInformeJugadores() throws DocumentException, IOException {
        List<Jugador> jugadores = jugadorService.obtenerTodosLosJugadores();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            for (Jugador jugador : jugadores) {
                document.add(new Paragraph("Número: " + jugador.getNumero()));
                document.add(new Paragraph("Nombre: " + jugador.getNombre()));
                document.add(new Paragraph("País: " + jugador.getPais().getNombre()));
                document.add(new Paragraph("-----"));
            }

            document.close();
        } catch (Exception e) {
            throw new DocumentException("Error al generar el informe PDF de jugadores", e);
        }

        return baos.toByteArray();
    }

    public byte[] generarInformePartidas() throws DocumentException, IOException {
        List<Partida> partidas = partidaService.obtenerTodasLasPartidas();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            for (Partida partida : partidas) {
                document.add(new Paragraph("Número: " + partida.getNumero()));
                document.add(new Paragraph("Fecha: " + partida.getFecha()));
                document.add(new Paragraph("Jugador Blanco: " + ((Arbitro) partida.getJugadores()).getNombre()));
                document.add(new Paragraph("Jugador Negro: " + ((Arbitro) partida.getJugadores()).getNombre()));
                document.add(new Paragraph("-----"));
            }

            document.close();
        } catch (Exception e) {
            throw new DocumentException("Error al generar el informe PDF de partidas", e);
        }

        return baos.toByteArray();
    }

    public byte[] generarInformeHoteles() throws DocumentException, IOException {
        List<Hotel> hoteles = hotelService.obtenerTodosLosHoteles();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            for (Hotel hotel : hoteles) {
                // Agrega los datos del hotel al PDF
                document.add(new Paragraph("Número: " + hotel.getNumero()));
                document.add(new Paragraph("Nombre: " + hotel.getNombre()));
                document.add(new Paragraph("Teléfono: " + hotel.getTelefono()));
                document.add(new Paragraph("Dirección: " + hotel.getDireccion()));

                // Agrega los datos de los jugadores asociados al hotel
                for (Jugador jugador : hotel.getJugadores()) {
                    document.add(new Paragraph("   Jugador: " + jugador.getNombre()));
                }

                document.add(new Paragraph("-----"));
            }

            document.close();
        } catch (Exception e) {
            throw new DocumentException("Error al generar el informe PDF de hoteles", e);
        }

        return baos.toByteArray();
    }

    public byte[] generarInformePaises() throws DocumentException, IOException {
        List<Pais> paises = paisService.obtenerTodosLosPaises();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            for (Pais pais : paises) {
                // Agrega los datos del país al PDF
                document.add(new Paragraph("Número: " + pais.getNumero()));
                document.add(new Paragraph("Nombre: " + pais.getNombre()));



                document.add(new Paragraph("-----"));
            }

            document.close();
            } catch (Exception e) {
                throw new DocumentException("Error al generar el informe PDF de países", e);
            }
        return null;
        
        }

    public byte[] generarInformeSalas() throws DocumentException, IOException {
        List<Sala> salas = salaService.obtenerTodasLasSalas();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            for (Sala sala : salas) {
                document.add(new Paragraph("Número: " + sala.getNumero()));
                document.add(new Paragraph("Capacidad: " + sala.getCapacidad()));

                for (Partida partida : sala.getPartidas()) {
                    document.add(new Paragraph("   Partida: " + partida.getNumero()));
                }

                document.add(new Paragraph("-----"));
            }

            document.close();
        } catch (Exception e) {
            throw new DocumentException("Error al generar el informe PDF de salas", e);
        }

        return baos.toByteArray();
    }

    public byte[] generarInformeArbitros() throws DocumentException, IOException {
        List<Arbitro> arbitros = arbitroService.obtenerTodosLosArbitros();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            for (Arbitro arbitro : arbitros) {
                document.add(new Paragraph("Número: " + arbitro.getNumero()));
                document.add(new Paragraph("Nombre: " + arbitro.getNombre()));
                document.add(new Paragraph("Teléfono: " + arbitro.getTelefono()));
                document.add(new Paragraph("País: " + arbitro.getPais()));

                for (Partida partida : arbitro.getPartidas()) {
                    document.add(new Paragraph("   Partida: " + partida.getNumero()));
                }

                document.add(new Paragraph("-----"));
            }

            document.close();
        } catch (Exception e) {
            throw new DocumentException("Error al generar el informe PDF de arbitros", e);
        }

        return baos.toByteArray();
    }

    
}
