package com.beeva.banco.dao;

import com.beeva.banco.model.Banco;

public abstract class BancoDAO {
	public abstract void save(Banco ba);
	public abstract Banco getBanco(int id);

}
