package br.ufpb.dcx.aps.escalonador;

import java.util.List;

public class StatusEscalonador { 
	
	public String statusInicial(TipoEscalonador tipo, int quantum, int tick) {
		return "Escalonador " + tipo + ";Processos: {};Quantum: " + quantum + ";Tick: " + tick;
	}
	
	public String checaStatusFila (TipoEscalonador tipo,List processos, int quantum, int tick) {
		return "Escalonador " + tipo + ";Processos: {Fila: " + processos + "};Quantum: " + quantum + ";Tick: " + tick;
	}
	
	public String checaStatusRodando (TipoEscalonador tipo,String processosRodando, int quantum, int tick) {
		return "Escalonador " + tipo + ";Processos: {Rodando: " + processosRodando+ "};Quantum: " + quantum + ";Tick: " + tick;
	}

}
 