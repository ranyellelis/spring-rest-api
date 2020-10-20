package br.com.ranyel.enums;

public enum EntradaSaidaEnum {

	E("Entrada"),
	S("Saída");
	
	private EntradaSaidaEnum(String nome) {
		this.nome = nome;
	}
	
	private String nome;

	public String getNome() {
		return nome;
	}
}

