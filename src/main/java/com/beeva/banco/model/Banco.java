package com.beeva.banco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="banco")
public class Banco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idbanco;
	private String nombre;

	public int getIdbanco() {
		return idbanco;
	}
	public void setIdbanco(int idbanco) {
		this.idbanco = idbanco;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
