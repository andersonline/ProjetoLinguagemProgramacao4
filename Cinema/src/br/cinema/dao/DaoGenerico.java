package br.cinema.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.cinema.jpa.Conexao;

public class DaoGenerico<T> {
	public static Logger log;
	protected EntityManager entityManager;
	
	public DaoGenerico() {
		log = Logger.getLogger(this.getClass());
		Conexao conexao = new Conexao();
		entityManager = conexao.getEntity();
	}
	
	public void save(T classType) {
		log.info("Salvando cliente");
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(classType);
			entityManager.getTransaction().commit();
		} catch (HibernateException e) {
			entityManager.getTransaction().rollback();
			log.error("Erro ao tentar salvar o " + classType.getClass().getSimpleName() + " " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}
	
	public void update(T classType) {
		log.info("Atualizando cliente");
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(classType);
			entityManager.getTransaction().commit();
		} catch (HibernateException e) {
			entityManager.getTransaction().rollback();
			log.error("Erro ao tentar atualizar o " + classType.getClass().getSimpleName() + " " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}
	
	public T getById(final Class<T> classType, final int id) {
		log.info("Buscando cliente pelo id " + id);
		return entityManager.find(classType, id);
	}
	
	public void delete(Class<T> classType, final int id) {
		log.info("Deletando cliente");
		try {
			entityManager.getTransaction().begin();
			T removed = getById(classType, id);
			entityManager.remove(removed);
			entityManager.getTransaction().commit();
		} catch (HibernateException e) {
			entityManager.getTransaction().rollback();
			log.error("Erro ao tentar atualizar o cliente " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(final Class<T> classType) {
		log.info("Listando todos os clientes");
		return entityManager.createQuery("FROM " + classType.getName()).getResultList();
	}
}
