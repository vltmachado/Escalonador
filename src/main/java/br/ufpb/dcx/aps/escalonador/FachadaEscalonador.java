package br.ufpb.dcx.aps.escalonador;

public class FachadaEscalonador {
	
	private int tick;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
	}

	public String getStatus() {
		return "Escalonador RoundRobin;Processos: {};Quantum: 3;Tick: " + tick;
	}

	public void tick() {
		tick++;
	}

	public void adicionarProcesso(String nomeProcesso) {
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
	}

	public void finalizarProcesso(String nomeProcesso) {
	}

	public void bloquearProcesso(String nomeProcesso) {
	}

	public void retomarProcesso(String nomeProcesso) {
		
	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {
		
	}
}
