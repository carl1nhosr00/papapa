package Usuarios;

import Itens.Item;
import Itens.Livro;
import Itens.MapaTematico;

public class UsuarioProfessor extends Usuario {
	
	public UsuarioProfessor(String nome) {
		super(nome);
		this.nivelDePrivilegio = 3;
	}
	
	@Override
	public boolean retiraItem(Item it) {

		if (it.isDisponivel()) {
			it.empresta(this, getPrazoMaximo());
			this.itensRetirados.add(it);
			return true;
		}
		return false;
	}
	@Override
	public boolean devolveItem(Item it) {
		this.itensRetirados.remove(it);
		return true;
	}

	
	public boolean bloqueiaLivro(Livro livro, int prazo) {
		return livro.bloqueia(this, prazo);
	}

	
	public boolean desbloqueiaLivro(Livro livro) {
		return livro.desbloqueia(this); 
	}

	@Override
	public int getCotaMaxima() {
		return 5; 
	}
	
	@Override
	public int getPrazoMaximo() {
		return 14; 
	}

	@Override
	public String toString() {
		return "Prof. " + super.getNome();
	}
	@Override
	public boolean isProfessor() {
		return true;
	}

}
