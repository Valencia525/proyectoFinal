package com.Ajedrez.Ajedrez.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Hotel")
@Table(name = "Hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numero;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "Direccion")
    private String direccion;

    @OneToMany(mappedBy = "hotel")
    private java.util.List<Jugador> jugadores;

    @OneToMany(mappedBy = "hotel")
    private java.util.List<Arbitro> arbitros;

    @OneToMany(mappedBy = "hotel")
    private java.util.List<Sala> salas;

    // Default constructor
    public Hotel() {
    }

    // Constructor with all fields
    public Hotel(int numero, String nombre, String telefono, String direccion) {
        this.numero = numero;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public java.util.List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(java.util.List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public java.util.List<Arbitro> getArbitros() {
        return arbitros;
    }

    public void setArbitros(java.util.List<Arbitro> arbitros) {
        this.arbitros = arbitros;
    }

    public java.util.List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(java.util.List<Sala> salas) {
        this.salas = salas;
    }

}
