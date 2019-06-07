package br.cinema.model;


public class Filme {
	private int id;
	private String titulo;
	private String duracao;
	private String classificacao;
	private String genero;
	private String sinopse;
	
	public Filme(int id, String titulo, String duracao, String classificacao, String genero, String sinopse) {
		this.id = id;
		this.titulo = titulo;
		this.duracao = duracao;
		this.classificacao = classificacao;
		this.genero = genero;
		this.sinopse = sinopse;
	}
	
	public Filme(String titulo, String duracao, String classificacao, String genero, String sinopse) {
		this.titulo = titulo;
		this.duracao = duracao;
		this.classificacao = classificacao;
		this.genero = genero;
		this.sinopse = sinopse;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
}
