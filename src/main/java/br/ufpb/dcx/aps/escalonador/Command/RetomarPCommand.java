package br.ufpb.dcx.aps.escalonador.Command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class RetomarPCommand implements Command {
	private Escalonador escalonador;
	private String processo;
	
	public RetomarPCommand(String processo) {
		this.processo = processo;
	}

	@Override
	public String executar() {
		getEscalonador().retomarProcesso(processo);
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
