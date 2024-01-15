package com.Ajedrez.Ajedrez.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ajedrez.Ajedrez.Entidades.Jugador;


@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

}
