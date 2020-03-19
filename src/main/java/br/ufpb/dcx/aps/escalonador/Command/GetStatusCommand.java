package br.ufpb.dcx.aps.escalonador.Command;
import br.ufpb.dcx.aps.escalonador.Escalonador;

public class GetStatusCommand implements Command{
	
	private Escalonador escalonador;
	
	public GetStatusCommand() {}

	@Override
	public String executar() {
		return getEscalonador().getStatus();
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
