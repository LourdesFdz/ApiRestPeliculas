package com.mx.ApiRestPeliculas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestPeliculas.model.Peliculas;

public interface PeliculasDao extends JpaRepository<Peliculas, Integer>{

}
