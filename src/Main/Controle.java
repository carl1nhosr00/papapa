package Main;

import Itens.*;
import Usuarios.*;
import java.util.*;

public class Controle {
	
	private ArrayList<Livro> livros;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Usuario> usuariosExt;
	private ArrayList<UsuarioProfessor> professores;
	private ArrayList<UsuarioAluno> alunos;
	private ArrayList<MapaTematico> mapasTematicos;
	private ArrayList<Livro> periodicos;

	public Controle() {
		this.livros = new ArrayList<Livro>();
		this.usuarios = new ArrayList<Usuario>();
		this.usuariosExt = new ArrayList<Usuario>();
		this.professores = new ArrayList<UsuarioProfessor>();
		this.alunos = new ArrayList<UsuarioAluno>();
		this.mapasTematicos = new ArrayList<MapaTematico>();
		this.periodicos = new ArrayList<Livro>();
	}



	public void adicionarLivro(Livro livro) {
		System.out.println("Este livro é um periódico? 1(SIM) 2(NÃO)");
		int isPer = new Scanner(System.in).nextInt();
		if (isPer == 1) {
			livro.setPer();
			this.periodicos.add(livro);
		}
		livros.add(livro);

	}

	public void removerLivro(Livro livro) {
		livros.remove(livro);
	}

	public void adicionarAluno(UsuarioAluno usuario) {
		alunos.add(usuario);
		usuarios.add(usuario);
	}

	public void removerAluno(UsuarioAluno usuario) {
		alunos.remove(usuario);
		usuarios.remove(usuario);
	}

	public void adicionarProfessor(UsuarioProfessor usuario) {
		professores.add(usuario);
		usuarios.add(usuario);
	}

	public void removerProfessor(UsuarioProfessor usuario) {
		professores.remove(usuario);
		usuarios.remove(usuario);
	}

	public void adicionarUsuarioExt(Usuario usuario) {
		usuariosExt.add(usuario);
		usuarios.add(usuario);

	}

	public void removerUsuarioExt(Usuario usuario) {
		usuariosExt.remove(usuario);
		usuarios.remove(usuario);

	}

	public void adicionarMapaTematico(MapaTematico mapa) {
		mapasTematicos.add(mapa);
	}

	public void removerMapaTematico(MapaTematico mapa) {
		mapasTematicos.remove(mapa);
	}

	public boolean emprestarLivro(String titulo, Usuario usuario) {
		for (Livro livro : livros) {
			if (livro.getTitulo().equals(titulo)) {
				if (this.getLivro(titulo).isBloqueado() == false && !this.periodicos.contains(this.getLivro(titulo))) {
					System.out.println("Livro emprestado para: " + usuario.getNome());
					return livro.empresta(usuario, usuario.getPrazoMaximo());
				} else {
					System.out.println(this.getLivro(titulo).toString());
					System.out.println("Livro não retirado!");
				}

			} else {
				System.out.println("Não foi possível encontrar um livro com este título");
			}
		}

		return false;
	}

	public boolean emprestarPer(String titulo, UsuarioProfessor usuario) {
		for (Livro livro : this.periodicos) {
			if (livro.getTitulo().equals(titulo)) {
				System.out.println("Periódico emprestado para Professor(a): " + usuario.getNome());
				return livro.empresta(usuario, usuario.getPrazoMaximo());

			}
		}
		System.out.println("Não foi possível encontrar um periódico com este título");
		return false;
	}

	public boolean devolverLivro(String titulo, Usuario usuario) {
		for (Livro livro : livros) {
			if (livro.getTitulo().equals(titulo)) {
				System.out.println("Livro devolvido com sucesso!");
				return livro.retorna(usuario);
				
			}
		}
		System.out.println("Livro não devolvido!");
		return false;
	}
	
	public boolean devolverPeriodico(String titulo, UsuarioProfessor usuario) {
		for (Livro livro : livros) {
			if (livro.getTitulo().equals(titulo) && this.periodicos.contains(livro)) {
				System.out.println("Periodico devolvido com sucesso!");
				return livro.retorna(usuario);
				
			}
		}
		System.out.println("Periodico não devolvido!");
		return false;
	}

	public boolean emprestarMapa(String titulo, Usuario usuario) {
		for (MapaTematico mapa : mapasTematicos) {
			if (mapa.getTitulo().equals(titulo)) {
				System.out.println("Mapa emprestado para: " + usuario.getNome());
				return mapa.empresta(usuario, 2);
			}
		}
		System.out.println("Não foi possível encontrar um mapa com este título");
		return false;
	}

	public boolean devolverMapaTematico(String titulo, Usuario usuario) {
		for (MapaTematico mapa : mapasTematicos) {
			if (mapa.getTitulo().equals(titulo)) {
				System.out.println("Mapa devolvido com sucesso!");
				return mapa.retorna(usuario);
			}
		}
		System.out.println("Mapa não devolvido!");
		return false;
	}

	public Usuario getUsuario(String nome) {
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equals(nome)) {
				return usuario;
			}
		}
		return null;
	}

	public UsuarioAluno getAluno(String nome) {
		for (UsuarioAluno usuario : alunos) {
			if (usuario.getNome().equals(nome)) {
				return usuario;
			}
		}
		return null;
	}

	public UsuarioProfessor getProfessor(String nome) {
		for (UsuarioProfessor usuario : professores) {
			if (usuario.getNome().equals(nome)) {
				return usuario;
			}
		}
		return null;
	}

	public Livro getLivro(String titulo) {
		for (Livro livro : livros) {
			if (livro.getTitulo().equals(titulo)) {
				return livro;
			}
		}
		return null;
	}

	public MapaTematico getMapaTematico(String titulo) {
		for (MapaTematico mapa : mapasTematicos) {
			if (mapa.getTitulo().equals(titulo)) {
				return mapa;
			}
		}
		return null;
	}

	public void bloqueiaLivro(UsuarioProfessor us, String titulo, int prazo) {

		us.bloqueiaLivro(this.getLivro(titulo), prazo);

	}

	public void desbloqueiaLivro(UsuarioProfessor us, String titulo) {
		if (this.getLivro(titulo).isBloqueado() == true) {
			us.desbloqueiaLivro(this.getLivro(titulo));
			System.out.println("Livro desbloqueado!");
		} else {
			System.out.println("O livro já está desbloqueado!");
		}
	}

	public ArrayList<Livro> getLivros() {
		return this.livros;
	}

	public ArrayList<MapaTematico> getMapas() {
		return this.mapasTematicos;
	}

}
