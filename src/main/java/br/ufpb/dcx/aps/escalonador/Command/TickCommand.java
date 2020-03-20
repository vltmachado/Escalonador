
package br.ufpb.dcx.aps.escalonador.Command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class TickCommand implements Command {

	private Escalonador escalonador;

	public TickCommand() {
	}

	@Override
	public String executar() {
		getEscalonador().tick();
		return null;
	}

	@Override
	public Escalonador getEscalonador() {
		return this.escalonador;
	}

	@Override
	public void setEscalonador(Escalonador escalonador) {
		this.escalonador = escalonador;
	}
}