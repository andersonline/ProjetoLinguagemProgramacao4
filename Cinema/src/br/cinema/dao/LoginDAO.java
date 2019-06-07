package br.cinema.dao;

import java.util.List;

import br.cinema.model.Login;

public interface LoginDAO {
	public int save(Login login);
	
	public boolean delete(int id);
	
	public List<Login> getAll();
	
	public int update(Login login);
}
