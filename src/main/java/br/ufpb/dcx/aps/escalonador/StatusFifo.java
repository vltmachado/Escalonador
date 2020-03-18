package br.ufpb.dcx.aps.escalonador;

import java.util.List;

public class StatusFifo {
	
	public String statusInicialFifo(TipoEscalonador tipo, int quantum, int tick) {
		return "Escalonador " + tipo + ";Processos: {};Quantum: " + quantum + ";Tick: " + tick;
	}

	public String statusFilaFifo(TipoEscalonador tipo, List<String> fila, int quantum, int tick) {
		return "Escalonador " + tipo + ";Processos: {Fila: " + fila + "};Quantum: " + quantum + ";Tick: " + tick;
	}

	public String statusRodandoFifo(TipoEscalonador tipo, String rodando, int quantum, int tick) {
		return "Escalonador " + tipo + ";Processos: {Rodando: " + rodando + "};Quantum: " + quantum + ";Tick: " + tick;
	}
}