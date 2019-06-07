package br.cinema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import br.cinema.jpa.Conexao;
import br.cinema.model.Cliente;

public class ClienteDAO {
	public static Logger log = Logger.getLogger(ClienteDAO.class);
	protected EntityManager entityManager;

	public ClienteDAO() {
		Conexao conexao = new Conexao();
		entityManager = conexao.getEntity();
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
	
	public void update(Cliente cliente) {
		log.info("Atualizando cliente");
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Erro ao tentar atualizar o cliente " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}
	
	public Cliente getById(final int id) {
		log.info("Buscando cliente pelo id " + id);
		return entityManager.find(Cliente.class, id);
	}
	
	public void delete(Cliente cliente) {
		log.info("Deletando cliente");
		try {
			entityManager.getTransaction().begin();
			Cliente removed = getById(cliente.getId());
			entityManager.remove(removed);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Erro ao tentar atualizar o cliente " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getAll() {
		log.info("Listando todos os clientes");
		return entityManager.createQuery("FROM " + ClienteDAO.class.getName()).getResultList();
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
