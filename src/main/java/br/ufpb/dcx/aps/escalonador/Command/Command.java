package br.ufpb.dcx.aps.escalonador.Command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class FinalizarPCommand implements Command{
	
	private Escalonador escalonador;
	private String processo;
	
	public FinalizarPCommand(String processo) {
		this.processo = processo;
	}

	@Override
	public String executar() {
		getEscalonador().finalizarProcesso(processo);
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