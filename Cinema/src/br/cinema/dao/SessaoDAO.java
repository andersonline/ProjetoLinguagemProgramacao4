package br.cinema.dao;

import java.util.List;

import br.cinema.model.Sessao;

public interface SessaoDAO {
	public int save(Sessao sessao);
	
	public boolean delete(int id);
	
	public List<Sessao> getAll();
	
	public int update(Sessao sessao);
}
