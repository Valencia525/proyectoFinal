package com.Ajedrez.Ajedrez.Entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Pais")
@Table(name = "Pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numero;

    @Column(name = "Nombre")
    private String nombre;

    // Constructor sin argumentos
    public Pais() {
    }

    // Constructor con argumentos
    public Pais(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jugador> jugadores = new ArrayList<>();

    // Getters y setters para jugadores
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    // Método para agregar un jugador
    public void addJugador(Jugador jugador) {
        jugadores.add(jugador);
        jugador.setPais(this);
    }

    // Método para remover un jugador
    public void removeJugador(Jugador jugador) {
        jugadores.remove(jugador);
        jugador.setPais(null);
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

    
}
