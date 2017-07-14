package com.beeva.banco.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.beeva.banco.dao.BancoClientesDAO;
import com.beeva.banco.model.BancoClientes;


@Repository
public class BancoClientesDAOImp extends BancoClientesDAO{
	 @PersistenceContext
	    EntityManager em;
	    
		@Override
		@Transactional
		public void save(BancoClientes bc) {
			em.persist(bc);
			
		}

		@Override
		public BancoClientes getBancoClientes(int id) {
			BancoClientes bcl = new BancoClientes();
			bcl = em.find(BancoClientes.class, id);
			return bcl;
		}

}
