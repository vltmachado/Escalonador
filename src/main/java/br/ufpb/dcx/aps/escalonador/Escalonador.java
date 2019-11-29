package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class Escalonador {

	private StatusEscalonador status = new StatusEscalonador();
	private TipoEscalonador tipo;
	

	private int tick;
	private int quantum = 3;
	private String processoAtual;
	
	// Listas
	private List<String> listaProcessos = new ArrayList<String>();
	private List<String> processosRodando = new ArrayList<String>();
	
	public Escalonador() {
		
	}
	
	public Escalonador(TipoEscalonador tipo) {
		this.tipo = tipo;
	}

	public Escalonador(TipoEscalonador escalonador, int quantum) {
		this.tipo = escalonador;
		this.quantum = quantum;
	}



	public String getStatus() {
		if (listaProcessos.size() != 0 && tick == 0) {
			return status.checaStatusFila(tipo, listaProcessos, quantum, tick);
		}
		//Teste 4
		if(this.tick == 4) {
			processosRodando.remove(0);
			listaProcessos.remove(0);
			status.checaStatusFila(tipo, listaProcessos, quantum, tick);
			return status.checaStatusRodando(tipo, processoAtual, quantum, tick);
			
		}
		// teste 3
		if (processosRodando.size() != 0 ) {
			return status.checaStatusRodando(tipo, processoAtual, quantum, tick);
		}
		
		
		//teste 1
		return status.statusInicial(tipo, quantum, tick);
		
	}

	public void tick() {
	//teste 2
		tick++;
	}
 
	public void adicionarProcesso(String nomeProcesso) {
		listaProcessos.add(nomeProcesso);
		processosRodando.add(nomeProcesso);
		processoAtual = processosRodando.get(0);
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


