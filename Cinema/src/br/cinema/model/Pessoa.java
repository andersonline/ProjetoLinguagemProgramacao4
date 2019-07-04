package br.cinema.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tab_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id = 0;
	private String nome;
	@Column(unique = true)
	private String cpf;
	@Column(unique = true)
	private String email;
	private String senha;
	private String fone;
	private LocalDate dataNascimento;
	@ManyToOne
	private Endereco endereco;

	public Pessoa() {
		super();
	}
	
	public Pessoa(String nome, String cpf, String email, String senha, String fone, LocalDate dataNascimento,
			Endereco endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.fone = fone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}

	public Pessoa(int id, String nome, String cpf, String email, String senha, String fone, LocalDate dataNascimento,
			Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.fone = fone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
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
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
}
