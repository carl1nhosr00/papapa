package Usuarios;

import Itens.Item;
import Itens.Livro;

import java.util.*;


public class UsuarioAluno extends Usuario {
	
	private Date dataLimite;

	public UsuarioAluno(String nome, Date dt) {
	    super(nome);
	    this.dataLimite = dt;
	  }

	public void renovaCartao(Date dt) {
		this.dataLimite = dt;
	}

	public boolean isRegular() {
		Date hoje = new Date();
		return dataLimite.after(hoje);
	}

	public boolean isARenovar() {
		return !isRegular();
	}
	
	@Override
	public String toString() {
		return "Aluno:" + this.getNome();
	}

	@Override
	public int getCotaMaxima() { 
		if (isRegular()) {
			return 3;
		}

		return super.getCotaMaxima();
	}

	@Override
	public int getPrazoMaximo() { 
		if (isRegular()) {
			return 7;
		}

		return super.getPrazoMaximo();
	}

}
