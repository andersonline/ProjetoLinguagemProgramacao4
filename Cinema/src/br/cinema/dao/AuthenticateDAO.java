package br.cinema.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.cinema.jpa.Conexao;

public class AuthenticateDAO {
	public static Logger log;
	protected EntityManager entityManager;
	
	public AuthenticateDAO() {
		log = Logger.getLogger(this.getClass());
		Conexao conexao = new Conexao();
		entityManager = conexao.getEntity();
	}
	
	public boolean validateLogin(String email, String password) {
		log.info("Validando login" + entityManager);
		Query stmt = entityManager.createQuery("FROM Pessoa WHERE email = :email AND senha = :senha");
		stmt.setParameter("email", email);
		stmt.setParameter("senha", password);
		return stmt.getResultList().size() > 0;
	}
}
