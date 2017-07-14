package com.beeva.banco.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.dao.ClienteDAO;
import com.beeva.banco.model.Cliente;
@Repository
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class ClienteDAOImp extends ClienteDAO{
	@PersistenceContext
	EntityManager em;
	@Override
	@Transactional
	public void save(Cliente c) {
		em.persist(c);
		
	}

	@Override
	public Cliente getCliente(int id) {
		Cliente c = new Cliente();
		c = em.find(Cliente.class, id);
		return c;
	}
	public List<Cliente> getClientes(){
		String q = "select cliente.idcliente,cliente.nombre,cuenta.idcuenta,tipocuenta.nombre from cliente,cuenta,tipocuenta where cuenta.idcliente=cliente.idcliente and cuenta.idtipo_cuenta=tipocuenta.idtipo_cuenta";
		Query query = em.createQuery(q);
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}
	public List<Cliente> traeClientes(){
		//TypedQuery<Cliente> tq = (TypedQuery<Cliente>) em.createNativeQuery("select ¨* from cliente",Cliente.class);
		
		return em.createNativeQuery("select cliente.idcliente,cliente.nombre,cuenta.idcuenta,tipocuenta.nombre from cliente,cuenta,tipocuenta where cuenta.idcliente=cliente.idcliente and cuenta.idtipo_cuenta=tipocuenta.idtipo_cuenta", Cliente.class).getResultList();
		
	}

	
}
