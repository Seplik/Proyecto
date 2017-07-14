package com.beeva.banco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipocuenta")
public class TipoCuenta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtipo_cuenta;
	private String nombre;
	
	public int getIdtipo_cuenta() {
		return idtipo_cuenta;
	}
	public void setIdtipo_cuenta(int idtipo_cuenta) {
		this.idtipo_cuenta = idtipo_cuenta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
