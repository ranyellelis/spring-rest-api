package br.com.ranyel.enums;

public enum PeriodoEnum {
	M("Manhã"),
	T("Tarde"),
	N("Noite");
	
	private PeriodoEnum(String nome) {
		this.nome = nome;
	}
	
	private String nome;

	public String getNome() {
		return nome;
	}
}
