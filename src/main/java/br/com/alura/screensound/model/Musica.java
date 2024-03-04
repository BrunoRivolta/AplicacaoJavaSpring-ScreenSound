package br.com.alura.screensound.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String titulo;
	@Enumerated(EnumType.STRING)
	private Estilo estilo;
	private int tempoSegundos;
	@ManyToOne
	private Artista artista;

	public Musica() {
	}

	public Musica(Long id, String titulo, int tempoSegundos, Artista artista, Estilo estilo) {
		this.id = id;
		this.titulo = titulo;
		this.tempoSegundos = tempoSegundos;
		this.artista = artista;
		this.estilo = estilo;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getTempoSegundos() {
		return tempoSegundos;
	}

	public void setTempoSegundos(int tempoSegundos) {
		this.tempoSegundos = tempoSegundos;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}
}
