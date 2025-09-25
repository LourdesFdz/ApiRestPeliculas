package com.mx.ApiRestPeliculas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// http://localhost:8081/PeliculasWebService/editar
		@PutMapping(path = "editar")
		public ResponseEntity<?> editar(@RequestBody Peliculas pelicula){
			try {
				boolean response = peliculaSevImp.editar(pelicula);
				if (response == false)
					return new ResponseEntity<String>("No existe ese idPelicula, no se puede editar", HttpStatus.OK);	
				else 
					return new ResponseEntity<Peliculas>(pelicula, HttpStatus.CREATED);
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<String>("Error al editar " + e.getMessage(), HttpStatus.OK);
			}
		}
		
		//http://localhost:8081/PeliculasWebService/eliminar
		@PostMapping(path = "eliminar")
		public ResponseEntity<String> eliminar(@RequestBody Peliculas pelicula) {
			try {
				boolean response = peliculaSevImp.eliminar(pelicula.getIdPeli());
				if (response == false) 
					return new ResponseEntity<String>("No existe ese registro", HttpStatus.OK);	
				else 
					return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<String>("Error al eliminar " + e.getMessage(), HttpStatus.OK);
			}
		}

		
		//http://localhost:8081/PeliculasWebService/buscarPorFecha
		@PostMapping(path = "buscarPorFecha")
		public ResponseEntity<List<Peliculas>> buscarPorFecha(@RequestBody Peliculas pelicula) {
		    List<Peliculas> response = peliculaSevImp.buscarPorFecha(pelicula.getFechaLanz());
		    
		    if (response.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		}	
		
		
		// http://localhost:8081/PeliculasWebService/buscarPorStock/{stock}
		// http://localhost:8081/PeliculasWebService/buscarPorStock/2
		@GetMapping(path = "buscarPorStock/{stock}")
		public ResponseEntity<List<Peliculas>> buscarStock(@PathVariable("stock") Integer stock) {
		    List<Peliculas> response = peliculaSevImp.buscarXstock(stock);
		    if (response.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		
		//http://localhost:8081/PeliculasWebService/eliminarPorNombre/{nombre}
		//http://localhost:8081/PeliculasWebService/eliminarPorNombre/El Conjuro 2
		@DeleteMapping(path = "eliminarPorNombre/{nombre}")
		public ResponseEntity<String> eliminarPorNombre(@PathVariable("nombre") String nombre) {
		    boolean response = peliculaSevImp.eliminarXnombre(nombre);

		    if (!response) {
		        return new ResponseEntity<>("No se encontró ninguna película con ese nombre", HttpStatus.NOT_FOUND);
		    }

		    return new ResponseEntity<>("Película eliminada correctamente", HttpStatus.OK);
		}
	
}
