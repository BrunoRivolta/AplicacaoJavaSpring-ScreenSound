package br.com.alura.screensound.model;

public enum Estilo {
	ROCK("1"),
	SERTANEJO("2"),
	PAGODE("3"),
	SAMBA("4"),
	MPB("5"),
	POP("6");

	private String estiloMusical;

	Estilo(String estiloMusical) {
		this.estiloMusical = estiloMusical;
	}

	public static Estilo estiloDaMusica(String text) {
		for ( Estilo estilo :  Estilo.values()) {
			if (estilo.estiloMusical.equalsIgnoreCase(text)) {
				return estilo;
			}
		}
		throw new IllegalArgumentException("Nenhuma estilo encontrado para a string fornecida: " + text);
	}
}
