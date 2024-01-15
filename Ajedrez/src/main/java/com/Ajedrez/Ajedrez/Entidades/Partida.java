package com.Ajedrez.Ajedrez.Entidades;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Partida")
@Table(name = "Partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "NumeroEntradas")
    private int numeroEntradas;

    // Default constructor
    public Partida() {
    }

    // Constructor with all fields
    public Partida(int numero, String fecha, int numeroEntradas) {
        this.numero = numero;
        this.fecha = fecha;
        this.numeroEntradas = numeroEntradas;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "numeroArbitro", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Arbitro arbitro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "numeroSala", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Sala sala;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "Jugador_Partida",
        joinColumns = @JoinColumn(name = "PartidaID", referencedColumnName = "numero"),
        inverseJoinColumns = @JoinColumn(name = "JugadorID", referencedColumnName = "numero")
    )
    private Set<Jugador> jugadores = new HashSet<>();

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumeroEntradas() {
        return numeroEntradas;
    }

    public void setNumeroEntradas(int numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }



}
