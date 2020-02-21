package br.ufpb.dcx.aps.escalonador;

import java.util.*;

public class EscalonadorMaisCurtoPrimeiro extends Escalonador {

	StatusEscalonadorMCP status = new StatusEscalonadorMCP();

	private int tick;
	private List<String> fila = new ArrayList<String>();
	private List<Integer> duracoes = new ArrayList<Integer>();

	private String processoRodando;

	private int duracaoFixa = 0;
	private int duracaoRodando = 0;

	public EscalonadorMaisCurtoPrimeiro() {
	}

	public EscalonadorMaisCurtoPrimeiro(TipoEscalonador tipoEscalonador) {
		super(TipoEscalonador.MaisCurtoPrimeiro);
	}

	public EscalonadorMaisCurtoPrimeiro(int quantum) {
		super(TipoEscalonador.MaisCurtoPrimeiro, quantum);
	}

	public String getStatus() {
		if (processoRodando == null && fila.size() == 0) {
			return status.statusInicialMCP(TipoEscalonador.MaisCurtoPrimeiro, 0, tick);
		}
		if (processoRodando == null && fila.size() > 0) {
			return status.statusFilaMCP(TipoEscalonador.MaisCurtoPrimeiro, fila, 0, tick);
		}
		if (tick > 0 && fila.size() == 0) {
			return status.statusRodandoMCP(TipoEscalonador.MaisCurtoPrimeiro, processoRodando, 0, tick);
		}
		return status.statusProcessoRodandoFilaMCP(TipoEscalonador.MaisCurtoPrimeiro, processoRodando, fila, 0, tick);
	}

	public void tick() {

		tick++;
		
		rodarPrimeiroProcessoMCP();
		
		rodarNovoProcesso();

	}

	private void rodarPrimeiroProcessoMCP() {
		if (fila.size() > 0) {
			if (processoRodando == null) {

				processoRodando = fila.remove(0);
				duracaoRodando = duracoes.remove(0);
				duracaoFixa = tick + duracaoRodando;
			}
		}
	}

	private void rodarNovoProcesso() {
		if (duracaoFixa == tick && processoRodando != null) {
			if (fila.size() > 0) {
				processoRodando = fila.remove(0);
				duracaoRodando = duracoes.remove(0);
			} else {
				processoRodando = null;
				duracaoRodando = 0;
			}
			if (duracaoRodando > 0) {
				duracaoFixa = tick + duracaoRodando;
			}
		}
	}
	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {
		if (fila.contains(nomeProcesso) || nomeProcesso == null) {
			throw new EscalonadorException();
		}
		if (duracao < 1) {
			throw new EscalonadorException();
		}
		adicionarProcessoMCP(nomeProcesso, duracao);

	}

	private void adicionarProcessoMCP(String nomeProcesso, int duracao) {
		int maisCurto = Integer.MAX_VALUE;
		if (fila.size() == 0) {
			fila.add(nomeProcesso);
			duracoes.add(duracao);
		} else {
			int menorPosicao = guardarPosicaoMenor(nomeProcesso, duracao, maisCurto);

			ordenarFilaProcessos(menorPosicao);
		}
	}

	private void ordenarFilaProcessos(int menorPosicao) {
		if (menorPosicao > 0) {
			String filaTemp = fila.remove(menorPosicao);
			Integer duracaoTemp = duracoes.remove(menorPosicao);

			fila.add(0, filaTemp);
			duracoes.add(0, duracaoTemp);
		}
	}

	private int guardarPosicaoMenor(String nomeProcesso, int duracao, int maisCurto) {
		fila.add(nomeProcesso);
		duracoes.add(duracao);
		int menorPosicao = 0;
		for (int i = 0; i < duracoes.size(); i++) {
			if (duracoes.get(i) < maisCurto) {
				maisCurto = duracoes.get(i);
				menorPosicao = i;
			}
		}
		return menorPosicao;
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
		throw new EscalonadorException();
	}


}	
