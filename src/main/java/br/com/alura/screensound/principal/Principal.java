package br.com.alura.screensound.principal;

import br.com.alura.screensound.model.Artista;
import br.com.alura.screensound.model.Estilo;
import br.com.alura.screensound.model.Musica;
import br.com.alura.screensound.model.Tipo;
import br.com.alura.screensound.repository.ArtistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Principal {
	Scanner leituraString = new Scanner(System.in);
	Scanner leituraInt = new Scanner(System.in);
	List<Artista> listaArtistas;
	List<Musica> listaMusicas = new ArrayList<>();
	Artista artistaSelecionado;
	boolean ficarNaAplicacao = true;
	private ArtistaRepository repositorio;

	public Principal(ArtistaRepository repo) {
		this.repositorio = repo;
	}

	public void exibeMenu() {
		while (ficarNaAplicacao) {
			System.out.printf("%nSeja bem vindo ao Screen Sound%n%n");
			System.out.println("""
				Menu:
				1 = Cadastro de Musicas
				2 = Listar Musicas
				3 = Buscar Musica por Artista
				4 = Busca Musica por Titulo
				5 = Busca Musica por Estilo Musical
				6 = Sair
			""");

			System.out.println("Digite a opção desejada: ");
			String opcaoMenu = leituraString.nextLine();

			switch (opcaoMenu) {
				case "1":
					cadastrarOuSelecionarArtista();
					break;
				case "2":
					listarMusicas();
					break;
				case "3":
					listaPorArtista();
					break;
				case "4":
					listaPorTitulo();
					break;
				case "5":
					listaPorEstiloMusical();
					break;
				case "6":
					ficarNaAplicacao = false;
					break;
				default:
			}
		}
	}

	private void cadastrarOuSelecionarArtista() {
		listaArtistas = repositorio.findAll();
		String selecaoArtista = "";
		if(listaArtistas.size() > 0) {
			System.out.printf("%n Artistas cadastrados: %n%n");
			for (int i = 0; i < listaArtistas.size(); i++) {
				System.out.println(i + " - " + listaArtistas.get(i).getNome());
			}

			System.out.printf("%nSelecione um artista cadastrado pelo numero%n");
			System.out.printf("ou digite 'c' para Cadastrar um Artista%n%n");
			selecaoArtista = leituraString.nextLine();
		} else {
			System.out.printf("%n Não existem artistas cadastrados! %n%n");
			selecaoArtista = "c";
		}

		if(Objects.equals(selecaoArtista, "c") || Objects.equals(selecaoArtista, "C")) {
			System.out.println("Cadastro do Artista");
			System.out.println("""
			Digite o tipo:
			1 = Solo
			2 = Dupla
			3 = Banda
		""");

			System.out.printf("%nDigite a opção desejada: %n");
			String tipoOpcao = leituraString.nextLine();

			var tipo = Tipo.tipoDoArtista(tipoOpcao);

			System.out.println("Digite o nome do artista/banda/dupla: ");
			String nome = leituraString.nextLine();

			Artista artista = new Artista(nome, tipo);
			repositorio.save(artista);
			System.out.printf("%n%s, cadastrado com sucesso!%n", nome);

		} else {
			artistaSelecionado = listaArtistas.get(Integer.parseInt(selecaoArtista));
			System.out.printf("%n%s, selecionado com sucesso!%n", artistaSelecionado.getNome());
			cadastrarOuSelecionarMusica();
		}
	}

	private void cadastrarOuSelecionarMusica() {
		boolean continuarCadastando = true;
		String opcaoEstilo;
		while (continuarCadastando){
			System.out.printf("%nCadastro da Musica do Artista %s%n", artistaSelecionado.getNome());
			System.out.println("""
				Digite o estilo:
				1 = Rock
				2 = Sertanejo
				3 = Pagode
				4 = Samba
				5 = MPB
				6 = POP
				N = NÃO Cadastrar mais Musicas
			""");
			System.out.printf("%nDigite a opção desejada: %n");
			opcaoEstilo = leituraString.nextLine();

			if (Objects.equals(opcaoEstilo, "N")  || Objects.equals(opcaoEstilo, "n")) {
				continuarCadastando = false;
			} else {
				var estilo = Estilo.estiloDaMusica(opcaoEstilo);

				System.out.println("Digite o Titulo da Musica: ");
				String titulo = leituraString.nextLine();

				System.out.println("Digite o tempo da musica em segundos: ");
				String tempoSegundos = leituraString.nextLine();
				int segundos = Integer.valueOf(tempoSegundos);

				Musica musica = new Musica(1L, titulo, segundos, artistaSelecionado, estilo);
				listaMusicas.add(musica);
			}
		}
		artistaSelecionado.setMusicas(listaMusicas);
		repositorio.save(artistaSelecionado);
	}

	private void listarMusicas() {
		listaArtistas = repositorio.findAll();
		System.out.printf("%nListando Musicas%n%n");
		listaArtistas.forEach(a -> a.getMusicas().forEach(m ->  System.out.println(m.getArtista().getNome() + " - " + m.getTitulo())));
	}

	private void listaPorArtista() {
		System.out.printf("%n%nDigite o nome do artista: %n");
		String nome = leituraString.nextLine();
		var artistaBusca = repositorio.findByNomeContainingIgnoreCase(nome);
		System.out.println("");
		System.out.println("Cantor/Banda: " + artistaBusca.get().getNome() + " Tipo: " + artistaBusca.get().getTipo());
	}

	private void listaPorTitulo() {
		System.out.printf("%n%nDigite o titulo da musica: %n");
		String trecho = leituraString.nextLine();
		var musicas = repositorio.musicaPorTitulo(trecho);
		System.out.println("");
		if(musicas.size() > 0) {
			musicas.forEach(m ->  System.out.println(m.getArtista().getNome() + " - " + m.getTitulo()));
		} else {
			System.out.println("Não foi encontrada nehuma musica com: " + trecho);
		}
	}

	private void listaPorEstiloMusical() {
		System.out.printf("%nEscolha o estilo: %n");
		System.out.println("""
				Digite o estilo:
				1 = Rock
				2 = Sertanejo
				3 = Pagode
				4 = Samba
				5 = MPB
				6 = POP
			""");
		System.out.printf("%nDigite a opção desejada: %n");
		String opcaoEstilo = leituraString.nextLine();
		var estilo = Estilo.estiloDaMusica(opcaoEstilo);
		var musicas = repositorio.musicaPorEstilo(estilo);
		System.out.println("");

		if(musicas.size() > 0) {
			musicas.forEach(m ->  System.out.println(m.getArtista().getNome() + " - " + m.getTitulo()));
		} else {
			System.out.println("Não foi encontrada nehuma musica com estilo: " + estilo.toString());
		}


	}
}
