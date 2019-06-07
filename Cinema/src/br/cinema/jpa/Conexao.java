package br.cinema.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	EntityManagerFactory emf = null;
	
	public Conexao() {
		if (emf == null) {
			emf = createEntity();
		} else {
			getEntity();
		}
	}

	private EntityManagerFactory getEntity() {
		return emf;
	}

	private EntityManagerFactory createEntity() {
		emf = Persistence.createEntityManagerFactory("Cinema");
		return emf;
	}
	
	private void closeEMF() {
		emf.close();
	}
}
