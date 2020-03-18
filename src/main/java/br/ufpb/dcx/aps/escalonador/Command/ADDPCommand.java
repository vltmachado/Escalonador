package br.ufpb.dcx.aps.escalonador.Command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class ADDPCommand implements Command{
	
	private Escalonador escalonador;
	private String processo;
	private int prioridade;
	
	public ADDPCommand(String nomeProcesso) {
		this.processo = nomeProcesso;
	}
	
	public ADDPCommand(String nomeProcesso, int prioridade) {
		this.processo = nomeProcesso;
		this.prioridade = prioridade;
	}

	@Override
	public String executar() {
		
		if(this.prioridade == 0) {
			getEscalonador().adicionarProcesso(processo);
		}else {
			getEscalonador().adicionarProcessoTempoFixo(processo, prioridade);
		}
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