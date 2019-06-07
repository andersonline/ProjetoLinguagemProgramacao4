package br.cinema.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import br.cinema.model.Cliente;

public class ClienteDAO {
	public static Logger log = Logger.getLogger(ClienteDAO.class);
	protected EntityManager entityManager;

	public ClienteDAO() {
		entityManager = getEntityManager();
	}

	public void save(Cliente cliente) {
		log.info("Salvando cliente");
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Erro ao tentar salvar o cliente " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}

//	public boolean delete(int id);
//
//	public List<Cliente> getAll();
//
//	public int update(Cliente cliente);

	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Cinema");
		if (entityManager == null) {
			log.info("Criando conexão");
			return factory.createEntityManager();
		} else {
			return entityManager;
		}
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
