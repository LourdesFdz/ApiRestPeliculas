package com.mx.ApiRestPeliculas.service;

import java.util.Iterator;
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
		Peliculas peliculaEncontr = peliculasDao.findById(idPeli).orElseGet(null);
		
		return peliculaEncontr;
	}
}
