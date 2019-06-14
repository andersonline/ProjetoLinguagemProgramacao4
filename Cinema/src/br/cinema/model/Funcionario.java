package br.cinema.model;

import java.time.LocalDate;

public class Funcionario extends Pessoa {
	private double salario;
	private String funcao;
	private LocalDate dataAdmissao;
	private String turno;
	
	public Funcionario(String nome, String cpf, String fone, String email, String senha, 
			LocalDate dataNascimento, Endereco endereco, Login login, 
			double salario, String funcao, LocalDate dataAdmissao, String turno) {
		super(nome, cpf, fone, email, senha, dataNascimento, endereco);
		this.salario = salario;
		this.funcao = funcao;
		this.dataAdmissao = dataAdmissao;
		this.turno = turno;
	}

	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public String getFuncao() {
		return funcao;
	}
	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public String getTurno() {
		return turno;
	}
	
	public void setTurno(String turno) {
		this.turno = turno;
	}
}
