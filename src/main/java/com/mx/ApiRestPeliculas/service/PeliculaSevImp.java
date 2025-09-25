package com.mx.ApiRestPeliculas.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestPeliculas.dao.PeliculasDao;
import com.mx.ApiRestPeliculas.model.Peliculas;

@Service
public class PeliculaSevImp {
	
	@Autowired
	PeliculasDao peliculasDao;
	
	@Transactional(readOnly = true)
	public List<Peliculas> mostrar(){
		List<Peliculas> listaNueva = peliculasDao.findAll();
		return listaNueva;
	}
	
	@Transactional 
	public boolean guardar(Peliculas pelicula) {
		boolean bandera = false;
		
		for (Peliculas e : mostrar()) {
			if (e.getNombre().equals(pelicula.getNombre())) {
				bandera = true;
				break;
			}
		}
		if (bandera == false) {
			peliculasDao.save(pelicula);
		}
		return bandera;
	}
	
	@Transactional(readOnly = true)
	public Peliculas buscarXid(Integer idPeli) {
		Peliculas peliculaEncontr = peliculasDao.findById(idPeli).orElse(null);
		
		return peliculaEncontr;
	}
	
	//validar que el idPelicula exista
		@Transactional
		public boolean editar(Peliculas pelicula) {
			
			Peliculas peliculaEnco=buscarXid(pelicula.getIdPeli());
			
			if (peliculaEnco!=null) {
				peliculasDao.save(pelicula);
				return true;		
			} else 
				return false;
		}
		
		//
		@Transactional
		public boolean eliminar(Integer idPelicula) {
			Peliculas peliculaEncontr = buscarXid(idPelicula);
			
			if (peliculaEncontr != null) {
				peliculasDao.deleteById(idPelicula);
				return true;
			}else
				return false;
		}
		
		
		@Transactional(readOnly = true)
		public List<Peliculas> buscarPorFecha(Date fecha) {
		    return peliculasDao.findByFechaLanz(fecha);
		}
		
		
		@Transactional(readOnly = true)
		public List<Peliculas> buscarXstock(Integer stock) {
		    return peliculasDao.findByStock(stock);
		}
		
		
		@Transactional
		public boolean eliminarXnombre(String nombre) {
		    List<Peliculas> peliculas = peliculasDao.findByNombre(nombre);

		    if (peliculas.isEmpty()) {
		        return false; // No hay nada que eliminar
		    }

		    peliculasDao.eliminaXnombre(nombre); // Se hace la eliminación
		    return true;
		}
}
