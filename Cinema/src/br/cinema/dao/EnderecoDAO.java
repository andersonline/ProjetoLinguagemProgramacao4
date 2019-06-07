package br.cinema.dao;

import java.util.List;

import br.cinema.model.Endereco;

public interface EnderecoDAO {
	public int save(Endereco endereco);
	
	public boolean delete(int id);
	
	public List<Endereco> getAll();
	
	public int update(Endereco endereco);
}
