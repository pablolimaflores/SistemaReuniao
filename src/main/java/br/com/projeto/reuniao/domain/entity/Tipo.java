package br.com.projeto.reuniao.domain.entity;

public enum Tipo {
	
	BRAINSTORM("Brainstorm"),
	CONSULTIVA("Consultiva"),
	DELIBERATIVA("Deliberativa"),
	TRABALHO("Trabalho");
	
	private final String nome;
	
	Tipo(String nome) {
		this.nome = nome;
	}
		
	public String getNome() {
		return nome;
	}	
}
