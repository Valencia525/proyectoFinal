package com.Ajedrez.Ajedrez.Entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Arbitro")
@Table(name = "Arbitro")
public class Arbitro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numero;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "pais")
    private String pais;

    // Default constructor
    public Arbitro() {
    }

    // Constructor with all fields
    public Arbitro(int numero, String nombre, String telefono, String pais) {
        this.numero = numero;
        this.nombre = nombre;
        this.telefono = telefono;
        this.pais = pais;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "HotelID", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Hotel hotel;

    @OneToMany(mappedBy = "arbitro")
    private List<Partida> partidas = new ArrayList<>();
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public List<Partida> getPartidas() {
        return partidas;
    }

    
}
