package com.beeva.banco.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.dao.CuentaDAO;
import com.beeva.banco.model.Cuenta;
@Repository
public class CuentaDAOImp extends CuentaDAO {
	@PersistenceContext
    EntityManager em;
    @Transactional
	@Override
	public void save(Cuenta cu) {
		em.persist(cu);
		
	}

	@Override
	public Cuenta getCuenta(int id) {
		Cuenta c = new Cuenta();
		c = em.find(Cuenta.class, id);
		return null;
	}

}
