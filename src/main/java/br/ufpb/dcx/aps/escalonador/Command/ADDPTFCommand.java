package br.ufpb.dcx.aps.escalonador.Command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class ADDPTFCommand implements Command {

	private Escalonador escalonador;
	private String processo;
	private int duracao;

	public ADDPTFCommand(String nomeProcesso) {
		this.processo = nomeProcesso;
	}

	public ADDPTFCommand(String nomeProcesso, int duracaoProcesso) {
		this.processo = nomeProcesso;
		this.duracao = duracaoProcesso;
	}

	@Override
	public String executar() {
		getEscalonador().adicionarProcessoTempoFixo(processo, duracao);
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