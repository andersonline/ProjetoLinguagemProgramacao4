package br.cinema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_login")
public class Login {
	@Id
	@GeneratedValue
	@Column(name = "id_login")
	private int id;
	private String email;
	private String senha;
	private String tipo;
	
	public Login() {
	}
	
	public Login(int id, String email, String senha, String tipo) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}
	
	public Login(String email, String senha, String tipo) {
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
