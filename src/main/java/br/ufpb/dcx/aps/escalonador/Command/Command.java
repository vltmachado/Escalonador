package br.ufpb.dcx.aps.escalonador.Command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public interface Command{
	String executar();
	Escalonador getEscalonador();
	void setEscalonador(Escalonador escalonador);
	
		
	
}