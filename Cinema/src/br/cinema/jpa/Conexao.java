package br.cinema.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	private final EntityManagerFactory emf;
	private EntityManager em = null;

	public Conexao() {
		emf = Persistence.createEntityManagerFactory("Cinema");
		if (emf != null) {
			em = emf.createEntityManager();
		}
	}

	public EntityManager getEntity() {
		return em;
	}

	public void closeEMF() {
		emf.close();
	}
}
