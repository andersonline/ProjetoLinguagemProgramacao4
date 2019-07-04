package br.cinema.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tab_cliente")
@NamedQuery(name = "Cliente.buscaNome", query = "SELECT c FROM Cliente c Where c.nome = :nome")


public class Cliente extends Pessoa {
	
	private String tipoCliente; // Normal, VIP, Ouro e Platinum 
	private boolean estudante;
	private LocalDate validade;

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, String email, String senha, String fone, LocalDate dataNascimento,
			Endereco endereco, String tipoCliente, boolean estudante, LocalDate validade) {
		super(nome, cpf, email, senha, fone, dataNascimento, endereco);
		this.tipoCliente = tipoCliente;
		this.estudante = estudante;
		this.validade = validade;
	}
	
	public Cliente(int id, String nome, String cpf, String email, String senha, String fone, LocalDate dataNascimento,
			Endereco endereco, String tipoCliente, boolean estudante, LocalDate validade) {
		super(id, nome, cpf, email, senha, fone, dataNascimento, endereco);
		this.tipoCliente = tipoCliente;
		this.estudante = estudante;
		this.validade = validade;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public boolean isEstudante() {
		return estudante;
	}

	public void setEstudante(boolean estudante) {
		this.estudante = estudante;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
}
