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
	private void escalonarNovoProcesso() {
		if (duracaoFixa == tick && proRodando != null) {
			if (fila.size() > 0) {
				proRodando = fila.remove(0);
				duracaoRodando = duracaoProcesso.remove(0);
			} else {
				proRodando = null;
				duracaoRodando = 0;
			}
			if (duracaoRodando > 0) {
				duracaoFixa = tick + duracaoRodando;
			}
		}
	}

	private void escalonarPrimeiroProcesso() {
		if (fila.size() > 0) {
			if (proRodando == null) {

				proRodando = fila.remove(0);
				duracaoRodando = duracaoProcesso.remove(0);
				duracaoFixa = tick + duracaoRodando;
			}
		}
	}

	public void adicionarProcesso(String nomeProcesso) {
		throw new EscalonadorException();

	}
	
	public void adicionarProcesso(String nomeProcesso, int prioridade) {
		throw new EscalonadorException();
	}

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {
		if (fila.contains(nomeProcesso) || nomeProcesso == null) {
			throw new EscalonadorException();
		}
		if(duracao < 1) {
			throw new EscalonadorException();
		}
		fila.add(nomeProcesso);
		duracaoProcesso.add(duracao);
	}

}