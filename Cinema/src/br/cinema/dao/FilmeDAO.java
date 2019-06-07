package br.cinema.dao;

import java.util.List;

import br.cinema.model.Filme;

public interface FilmeDAO {
	public void save(Filme filme);
	
	public boolean delete(int id);
	
	public List<Filme> getAll();
	
	public void update(Filme filme);
}
