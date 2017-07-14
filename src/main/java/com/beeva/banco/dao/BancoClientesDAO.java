package com.beeva.banco.dao;

import com.beeva.banco.model.BancoClientes;

public abstract class BancoClientesDAO {
	public abstract void save(BancoClientes bc);
	public abstract BancoClientes getBancoClientes(int id);

}
