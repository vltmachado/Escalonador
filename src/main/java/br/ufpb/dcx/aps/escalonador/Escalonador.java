package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Escalonador {

	private StatusEscalonador st = new StatusEscalonador();
	private TipoEscalonador tipoEscalonador;
	private int quantum = 3;
	private int tick = 0;
	private int valorAternar = 1;
	private int prioridadeRodando = 0;
	private String rodando;
	private boolean prioridadesIguais, filaAtualizada = false;

	private Queue<String> filaAternado = new LinkedList<>();
	private List<String> bloqueados = new ArrayList<String>();

	private List<String> processosFinalizar = new ArrayList<String>();
	private List<String> processosBloquear = new ArrayList<String>();
	private List<String> processosRetornar = new ArrayList<String>();

	private List<Integer> prioridades = new ArrayList<>();

	public Escalonador() {
	}

	public Escalonador(TipoEscalonador tipo) {
		this.tipoEscalonador = tipo;
	}

	public Escalonador(TipoEscalonador escalonador, int quantum) {
		this.tipoEscalonador = escalonador;
		this.quantum = quantum;
		if (quantum <= 0) {
			throw new EscalonadorException();
		}
	}

	public String getStatus() {

		if (rodando == null && filaAternado.size() == 0) {
			if (bloqueados.size() == 0) {
				return st.statusInicial(tipoEscalonador, quantum, tick);
			} else {
				return st.statusBloqueados(tipoEscalonador, bloqueados, quantum, tick);
			}
		}
		if (rodando == null && filaAternado.size() > 0) {
			return st.statusFila(tipoEscalonador, filaAternado, quantum, tick);
		}
		if (tick > 0 && filaAternado.size() == 0) {
			if (bloqueados.size() == 0) {
				return st.statusRodando(tipoEscalonador, rodando, quantum, tick);
			} else {
				return st.statusRodandoBloqueados(tipoEscalonador, rodando, bloqueados, quantum, tick);
			}
		}
		if (bloqueados.size() > 0) {
			return st.statusRodandoFilaBloqueados(tipoEscalonador, rodando, filaAternado, bloqueados, quantum, tick);
		}
		return st.statusProcessoRodandoFila(tipoEscalonador, rodando, filaAternado, quantum, tick);
	}

	public void tick() {

		tick++;

		verficarPrioridadesIguais();

		escalonarProcessos();

	}

	protected void escalonarProcessos() {
		if (prioridadesIguais || prioridades.size() == 0) {
			if (rodando == null) {
				rodando = filaAternado.poll();
			}

			finalizandoProcesso();

			verificarAlternancia();

			bloqueandoProcesso();

			retomandoProcesso();

		} else {

			rodarPrimeiroProcesso();

			rodarMenorPrioridade();

			atualizarFilaAlternando();

			finalizandoProcesso();

			bloqueandoProcesso();

		}
	}

	protected void verficarPrioridadesIguais() {
		for (int i = 0; i < prioridades.size(); i++) {
			if (prioridades.get(i) != 1 || prioridadeRodando != 0) {
				break;
			} else {
				prioridadesIguais = true;
			}
		}
	}

	protected void rodarPrimeiroProcesso() {
		if (rodando == null) {
			int menorPrioridade = Integer.MAX_VALUE;
			int posicao = 0;
			for (int i = 0; i < prioridades.size(); i++) {
				if (prioridades.get(i) < menorPrioridade) {
					posicao = i;
				}
			}
			prioridadeRodando = prioridades.remove(posicao);
			filaAternado.add(filaAternado.poll());
			rodando = filaAternado.poll();
		}
	}

	protected void rodarMenorPrioridade() {
		for (int i = 0; i < prioridades.size(); i++) {
			if (prioridades.get(i) < prioridadeRodando) {
				prioridades.add(prioridadeRodando);
				prioridadeRodando = prioridades.remove(i);
				if (rodando == null) {
					rodando = filaAternado.poll();
				} else {
					filaAternado.add(rodando);
					rodando = filaAternado.poll();

				}

			}

		}
	}

	private void atualizarFilaAlternando() {
		if (filaAternado.size() > 1 && !filaAtualizada) {
			int menorPrioridade = Integer.MAX_VALUE;
			int posicao = 0;
			for (int i = 0; i < prioridades.size(); i++) {
				if (prioridades.get(i) < menorPrioridade) {
					posicao = i;
				}
			}
			int valor = prioridades.remove(posicao);
			prioridades.add(0, valor);
			filaAternado.add(filaAternado.poll());
			filaAtualizada = true;
		}

	}
}	