package br.cinema.dao;

import java.util.List;

import br.cinema.model.Endereco;
import br.cinema.model.Login;
import br.cinema.model.Pessoa;

public interface PessoaDAO {
	public int save(Pessoa pessoa, Endereco endereco, Login login);
	
	public boolean delete(int id);
	
	public List<Pessoa> getAll();
	
	public int update(Pessoa pessoa, Endereco endereco, Login login);
}
