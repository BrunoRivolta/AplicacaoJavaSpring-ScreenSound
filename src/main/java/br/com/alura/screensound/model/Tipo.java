package br.com.alura.screensound.model;

public enum Tipo {
	SOLO("1"),
	DUPLA("2"),
	BANDA("3");

	private String tipoArtista;

	Tipo(String tipoArtista) {
		this.tipoArtista = tipoArtista;
	}

	public static Tipo tipoDoArtista(String text) {
		for (Tipo tipo : Tipo.values()) {
			if (tipo.tipoArtista.equalsIgnoreCase(text)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Nenhuma tipo encontrado para a string fornecida: " + text);
	}
}
