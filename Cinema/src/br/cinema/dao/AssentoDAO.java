package br.cinema.dao;

import java.util.List;

import br.cinema.model.Assento;

public interface AssentoDAO {
	public int save(Assento assento);
	
	public boolean delete(int id);
	
	public List<Assento> getAll();
	
	public int update(Assento assento);
}
