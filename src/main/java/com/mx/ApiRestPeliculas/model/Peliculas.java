package com.mx.ApiRestPeliculas.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="PELICULAS")
@Data
public class Peliculas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="ID_PELI", columnDefinition = "NUMBER", nullable = false)
	private Integer idPeli;
	
	@Column(name = "NOMBRE", columnDefinition = "VARCHAR2(90)", nullable = false)
	private String nombre;
	
	@Column(name = "PRECIO", columnDefinition = "FLOAT", nullable = false)
	private Float precio;
	
	@Column(name = "FECHA_LANZ", columnDefinition = "DATE", nullable = true)
	private Date fechaLanz;
	
	@Column(name = "STOCK", columnDefinition = "INT", nullable = false)
	private Integer stock;
}
