package br.cinema.model;

public class Assento {
	private int id;
	private String tipo;
	private String poltrona;
	
	public Assento(int id, String tipo, String poltrona) {
		this.id = id;
		this.tipo = tipo;
		this.poltrona = poltrona;
	}
	
	public Assento(String tipo, String poltrona) {
		this.tipo = tipo;
		this.poltrona = poltrona;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(String poltrona) {
		this.poltrona = poltrona;
	}
}
