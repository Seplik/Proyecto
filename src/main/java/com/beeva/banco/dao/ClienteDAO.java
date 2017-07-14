package com.beeva.banco.dao;

import java.util.List;

import com.beeva.banco.model.Cliente;

public abstract class ClienteDAO {
	public abstract void save(Cliente c);
	public abstract Cliente getCliente(int id);
	public abstract List<Cliente> traeClientes();

}
