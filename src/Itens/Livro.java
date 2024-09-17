package Itens;

import Usuarios.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class Livro extends Item {
	
	private UsuarioProfessor bloqueadoPor;
	private Date dtBloqueio;
	private Date dtDesbloqueio;
	private boolean isPeriodico;

	public Livro(String tit) {
		super(tit);
		this.titulo = tit;
	}
	
	public void setPer() {
		this.isPeriodico = true;
	}
	public boolean getPer() {
		return isPeriodico;
	}
	
	public UsuarioProfessor getBloqueador() {
		return this.bloqueadoPor;
	}

	@Override
	public boolean isDisponivel() {
		Date hoje = new Date();
		return this.retiradoPor == null && (this.bloqueadoPor == null || this.dtDesbloqueio.before(hoje));

		
	}

	public boolean isBloqueado() {
		Date hoje = new Date();
		return this.retiradoPor == null && !(this.bloqueadoPor == null) && !(this.dtDesbloqueio.before(hoje));

		
	}

	public boolean bloqueia(UsuarioProfessor u, int prazo) {
		GregorianCalendar cal = new GregorianCalendar();
		if (this.isDisponivel() && u.isProfessor()) {
			this.bloqueadoPor = u;
			this.dtBloqueio = cal.getTime();
			cal.add(Calendar.DATE, (prazo > 20 ? 20 : prazo));
			this.dtDesbloqueio = cal.getTime();
		}
		return false;
	}

	public boolean desbloqueia(UsuarioProfessor u) {
		if (u == this.bloqueadoPor) {
			this.bloqueadoPor = null;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		if (this.isDisponivel()) {
			if (this.isPeriodico == true) {
				return this.titulo + " (disponivel APENAS PARA PROFESSORES)";
			} else {
				return this.titulo + " (disponivel)";
			}
			
		}
		if (this.isEmprestado()) {
			this.state = this.titulo + " retirado por " + this.retiradoPor + " em " + dma(this.dtEmprestimo) + " ate "
					+ dma(this.dtDevolucao);
		} else {
			this.state = this.titulo + " bloqueado por " + this.bloqueadoPor + " em " + dma(this.dtBloqueio) + " ate "
					+ dma(this.dtDesbloqueio);
		}
		return super.state;
	}

}
