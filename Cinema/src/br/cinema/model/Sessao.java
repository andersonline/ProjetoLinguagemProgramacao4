package br.cinema.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sessao {
	private int id;
	private LocalDate data;
	private LocalTime hora;
	private double valor;
	private String tipo;
	
	public Sessao(int id, LocalDate data, LocalTime hora, double valor, String tipo) {
		this.id = id;
		this.data = data;
		this.hora = hora;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public Sessao(LocalDate data, LocalTime hora, double valor, String tipo) {
		this.data = data;
		this.hora = hora;
		this.valor = valor;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
