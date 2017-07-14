package com.beeva.banco.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.dao.TipoCuentaDAO;
import com.beeva.banco.model.TipoCuenta;

@Repository
public class TipoCuentaDAOImp extends TipoCuentaDAO {
	@PersistenceContext
    EntityManager em;
    @Transactional
	@Override
	public void save(TipoCuenta tc) {
	
		em.persist(tc);
	}

	@Override
	public TipoCuenta getTipoCuenta(int id) {
		TipoCuenta tip = new TipoCuenta();
		tip = em.find(TipoCuenta.class, id);
		return tip;
	}

}
