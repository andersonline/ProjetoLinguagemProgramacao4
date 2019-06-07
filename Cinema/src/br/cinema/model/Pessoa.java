package br.cinema.model;

import java.time.LocalDate;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String fone;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private Login login;

	public Pessoa(int id, String nome, String cpf, String email, String fone, LocalDate dataNascimento, Endereco endereco, Login login) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.fone = fone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.login= login;
	}
	
	public Pessoa(String nome, String cpf, String email, String fone, LocalDate dataNascimento, Endereco endereco, Login login) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.fone = fone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.login= login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		// Retornar CPF com mask
		return cpf;
	}
	
	public void setCpf(String cpf) {
		// Validar CPF
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		// Retornar fone com mask
		return fone;
	}
	
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
