package com.beeva.banco.impl;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.dao.BancoDAO;
import com.beeva.banco.model.Banco;
@Repository

public class BancoDAOImp extends BancoDAO{
	 @PersistenceContext
	    EntityManager em;
		@Transactional
		@Override
		public void save(Banco ba) {
			em.persist(ba);
			
		}

		@Override
		public Banco getBanco(int id) {
			Banco b = new Banco();
			b = em.find(Banco.class, id);
			return b;
		}
		
		

}
