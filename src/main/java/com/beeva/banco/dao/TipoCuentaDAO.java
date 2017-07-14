package com.beeva.banco.dao;

import com.beeva.banco.model.TipoCuenta;

public abstract class TipoCuentaDAO {
	public abstract void save(TipoCuenta tc);
	public abstract TipoCuenta getTipoCuenta(int id);

}
