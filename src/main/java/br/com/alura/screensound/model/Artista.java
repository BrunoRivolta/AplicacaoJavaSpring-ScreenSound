package br.com.alura.screensound.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nome;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	@OneToMany(
		mappedBy = "artista",
		cascade = {CascadeType.ALL},
		fetch = FetchType.EAGER
	)
	private List<Musica> musicas = new ArrayList<>();
	public Artista() {
	}
	public Artista( String nome, Tipo tipoArtista) {
		this.nome = nome;
		this.tipo = tipoArtista;
	}

	public void setMusicas(List<Musica> musicas) {
		musicas.forEach(m -> m.setArtista(this));
		this.musicas = musicas;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTip() {
		return tipo;
	}

	public void setTip(Tipo tip) {
		this.tipo = tip;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	@Override
	public String toString() {
		return "Artista " +
			"id=" + id +
			", nome='" + nome + '\'' +
			", tipo=" + tipo;
	}
}
