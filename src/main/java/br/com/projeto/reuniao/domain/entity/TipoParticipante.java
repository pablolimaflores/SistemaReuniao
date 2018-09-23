package br.com.projeto.reuniao.domain.entity;

public enum TipoParticipante {

	SOLICITANTE("Solicitante"),
	MEDIADOR("Mediador"),
	SECRETARIO("Secretario"),
	INTEGRANTE("Integrante");
	
	private final String nome;
	
	private TipoParticipante(String nome) {
		this.nome = nome;
	}
		
	public String getNome() {
		return nome;
	}
	
}
