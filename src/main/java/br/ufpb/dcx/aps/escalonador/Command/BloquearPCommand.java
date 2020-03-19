package br.ufpb.dcx.aps.escalonador.Command;
import br.ufpb.dcx.aps.escalonador.Escalonador;

public class BloquearPCommand implements Command{
	
	private Escalonador escalonador;
	private String processo;
	
	public BloquearPCommand(String processo) {
		this.processo = processo;
	}

	@Override
	public String executar() {
		getEscalonador().bloquearProcesso(processo);
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
