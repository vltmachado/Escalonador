package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class EscalonadorFifo extends Escalonador{
	
	private StatusFifo status = new StatusFifo();
	private int quantum = 0;
	private int tick = 0;
	private String proRodando;
	
	private List<String> fila = new ArrayList<>();
	private List<Integer> duracaoProcesso = new ArrayList<>();
	
	private int duracaoRodando = 0;
	private int duracaoFixa = 0;
	
	
	public EscalonadorFifo(TipoEscalonador tipoEscalonador) {
		super(TipoEscalonador.Fifo);
	}
	
	public EscalonadorFifo(int quantum) {
		super(TipoEscalonador.Fifo, quantum);
	}
	
	public String getStatus() {
		if(proRodando == null && fila.size() == 0) {
			return status.statusInicialFifo(TipoEscalonador.Fifo, quantum, tick);
		}
		if(proRodando == null && fila.size() > 0) {
			return status.statusFilaFifo(TipoEscalonador.Fifo, fila, quantum, tick);
		}
		if (tick > 0 && fila.size() == 0) {
			return status.statusRodandoFifo(TipoEscalonador.Fifo, proRodando, quantum, tick);
		}
		return status.statusProcessoRodandoFilaFifo(TipoEscalonador.Fifo, proRodando, fila, quantum, tick);
	}

	public void tick() {
		tick++;
		
		escalonarPrimeiroProcesso();
		
		escalonarNovoProcesso();
		
	}