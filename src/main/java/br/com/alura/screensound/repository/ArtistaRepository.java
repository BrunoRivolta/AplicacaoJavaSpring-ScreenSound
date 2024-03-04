package br.com.alura.screensound.repository;

import br.com.alura.screensound.model.Artista;
import br.com.alura.screensound.model.Estilo;
import br.com.alura.screensound.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
	Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);

	@Query("SELECT m FROM Musica m WHERE m.titulo ILIKE %:trechoTitulo%")
	List<Musica> musicaPorTitulo(String trechoTitulo);

	@Query("SELECT m FROM Musica m WHERE m.estilo = :estiloMusica")
	List<Musica> musicaPorEstilo(Estilo estiloMusica);
}
