package br.cinema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.cinema.jpa.Conexao;
import br.cinema.model.Cliente;

public class DaoGenerico<T> {
	public static Logger log;
	protected EntityManager entityManager;
	
	public DaoGenerico() {
		log = Logger.getLogger(this.getClass());
		Conexao conexao = new Conexao();
		entityManager = conexao.getEntity();
	}
	
	public boolean save(T classType) {
		log.warn("Salvando " + classType.getClass().getSimpleName());
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(classType);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			log.error("Erro ao tentar salvar o " + classType.getClass().getSimpleName() + " " + e.getMessage());
		}
		return false;
	}
	
	public void update(T classType) {
		log.info("Atualizando " + classType.getClass().getSimpleName());
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(classType);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			log.error("Erro ao tentar atualizar o " + classType.getClass().getSimpleName() + " " + e.getMessage());
		}
	}
	
	public T getById(final Class<T> classType, final int id) {
		log.info("Buscando " + classType.getClass().getSimpleName() + " pelo id " + id);
		return entityManager.find(classType, id);
	}
	
	public void delete(Class<T> classType, final int id) {
		log.info("Deletando " + classType.getClass().getSimpleName());
		try {
			entityManager.getTransaction().begin();
			T removed = getById(classType, id);
			entityManager.remove(removed);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			log.error("Erro ao tentar atualizar o " + classType.getClass().getSimpleName() + " " + e.getMessage());
		}
	}
	
//	public Cliente getClienteByName(String c) {
//		TypedQuery<Cliente> queryCli = entityManager.createNamedQuery("Cliente.buscaNome", Cliente.class);
//		queryCli.setParameter("nome", c);
//		
//	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(final Class<T> classType) {
		log.info("Listando " + classType.getClass().getSimpleName());
		return entityManager.createQuery("FROM " + classType.getName()).getResultList();
	}
	
	public void close() {
		entityManager.close();
	}
}
