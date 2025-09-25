package com.mx.ApiRestPeliculas.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestPeliculas.model.Peliculas;


public interface PeliculasDao extends JpaRepository<Peliculas, Integer>{
	
	//buscar por FECHA_LANZ
	List<Peliculas> findByFechaLanz(Date fechaLanz);
	
	//buscar por stock
	List<Peliculas> findByStock(Integer stock);
	
	//eliminar por nombre de pelicula
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM PELICULAS WHERE NOMBRE = :NOMBRE", nativeQuery = true)
	public void eliminaXnombre(@Param("NOMBRE") String nombre);	
	//valida antes de eliminar
	List<Peliculas> findByNombre(String nombre);

}
