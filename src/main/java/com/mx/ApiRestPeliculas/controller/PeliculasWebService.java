package com.mx.ApiRestPeliculas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestPeliculas.model.Peliculas;
import com.mx.ApiRestPeliculas.service.PeliculaSevImp;

@RestController
@RequestMapping(path = "PeliculasWebService")
@CrossOrigin

public class PeliculasWebService {
	
	@Autowired
	PeliculaSevImp peliculaSevImp;
	
	//URL : urlServidorLocal/pathClase/pathMetod
	
	//http://localhost:8081/PeliculasWebService/mostrarRegistros
	@GetMapping(path = "mostrarRegistros")
	public List<Peliculas> mostrar(){
		return peliculaSevImp.mostrar();
	}

	
	//http://localhost:8081/PeliculasWebService/guardarRegistros
	@PostMapping(path = "guardarRegistros")
	public ResponseEntity<?> guardarRegistros(@RequestBody Peliculas pelicula){
		try {
			boolean response = peliculaSevImp.guardar(pelicula);
			if (response == true) {
				return new ResponseEntity<String>("Ese nombre ya existe", HttpStatus.OK);
			} else {
				return new ResponseEntity<Peliculas>(pelicula, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al guardar" + e.getMessage(), HttpStatus.OK);
		}
	}
	
	//http://localhost:8081/PeliculasWebService/buscarXid
	@PostMapping(path = "buscarXid")
	public Peliculas buscarId(@RequestBody Peliculas pelicula) {
		return peliculaSevImp.buscarXid(pelicula.getIdPeli());
	}
	
}
