package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FachadaEscalonador {

	private final int quantum;
	private int tick;
	protected int duracaoRodando;
	protected int duracaoFixa;
	private TipoEscalonador tipoEscalonador;
	private final Queue<String> listaProcesso;
	private String rodando;
	protected List<String> fila = new ArrayList<String>();
	protected List<Integer> filaDuracao = new ArrayList<Integer>();
	protected List<String> processoRetomado = new ArrayList<String>();
	private final ArrayList<String> processoBloqueado;
	private int controlador;
	private String aFinalizar;
	protected String processoParaSerBloqueado;
	protected String processoParaSerRetomado;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		
		if(tipoEscalonador == null) {
			throw new EscalonadorException();
		}
		this.quantum = 3;
		this.tick = 0;
		this.tipoEscalonador = tipoEscalonador;
		this.listaProcesso = new LinkedList<String>();
		this.processoBloqueado = new ArrayList<String>();
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
		if(quantum <= 0) {
			throw new EscalonadorException();
		}

		this.quantum = quantum;
		this.tick = 0;
		this.tipoEscalonador = roundrobin;
		this.listaProcesso = new LinkedList<String>();
		this.processoBloqueado = new ArrayList<String>();
	}

	public String getStatus() {

		String resultado = "";

		resultado += "Escalonador " + this.tipoEscalonador + ";";

		resultado += "Processos: {";

		if (this.rodando != null) {
			resultado += "Rodando: " + this.rodando;

		}
		if (this.listaProcesso.size() > 0) {
			if (this.rodando != null) {
				resultado += ", ";
			}
			resultado += "Fila: " + this.listaProcesso.toString();
			if (this.processoBloqueado.size() > 0) {
				resultado += ", Bloqueados: " + this.processoBloqueado.toString();
			}

		}
		resultado += "};Quantum: " + this.quantum + ";";

		resultado += "Tick: " + this.tick;

		return resultado;
	}

	public void tick() {
		this.tick++;
		
		if (this.controlador > 0 && (this.controlador + this.quantum) == this.tick) {
			this.listaProcesso.add(this.rodando);
			this.rodando = this.listaProcesso.poll();
			this.controlador = this.tick;
		}

		if (this.aFinalizar != null) {
			if (this.rodando == this.aFinalizar) {
				this.rodando = null;

			} else {
				this.listaProcesso.remove(this.aFinalizar);
			}
		}

		if (this.rodando == null) {
			if (this.listaProcesso.size() != 0) {
				this.rodando = this.listaProcesso.poll();
				if (this.listaProcesso.size() > 0) {
					this.controlador = this.tick;
				}

			}
		}

		if (this.controlador == 0 && this.rodando != null && this.listaProcesso.size() > 0) {
			this.controlador = this.tick;
		}
		if (processoParaSerBloqueado != null) {
			if (this.rodando == this.processoParaSerBloqueado) {
				this.rodando = null;
				this.processoBloqueado.add(processoParaSerBloqueado);
				if (listaProcesso.size() > 0) {
					rodando = listaProcesso.poll();
				} else {
					rodando = null;
				}
			} else {
				this.listaProcesso.remove(processoParaSerBloqueado);
				this.processoBloqueado.add(processoParaSerBloqueado);
			}
			processoParaSerBloqueado = null;
		}

		if (processoRetomado.size() > 0) {
			for (int k = 0; k < processoRetomado.size(); k++) {
				String retomar = processoRetomado.get(k);
				if (processoBloqueado.contains(retomar)) {
					if (rodando == null) {
						rodando = retomar;
					} else {
						
						this.listaProcesso.add(retomar);
					}
					this.processoBloqueado.remove(retomar);
				}
			
			}
		}
		if (fila.size() > 0) {
			if (rodando == null) {
				rodando = fila.remove(0);
				duracaoRodando = filaDuracao.remove(0);
				duracaoFixa = this.tick + duracaoRodando;
			}
			if (duracaoFixa == this.tick && rodando != null) {
				if (fila.size() > 0) {
					rodando = fila.remove(0);
					duracaoRodando = filaDuracao.remove(0);
				} else {
					rodando = null;
					duracaoRodando = 0;
				}
				if (duracaoRodando > 0) {
					duracaoFixa = tick + duracaoRodando;

				}
			}
	
		}

	}

	public void adicionarProcesso(String nomeProcesso) {
		if(nomeProcesso == null) {
			throw new EscalonadorException();
		}
		if (listaProcesso.contains(nomeProcesso)) {
			throw new EscalonadorException();

		}
		this.listaProcesso.add(nomeProcesso);

	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
	}

	public void finalizarProcesso(String nomeProcesso) {
		if(!listaProcesso.contains(nomeProcesso) && rodando == null) {
			throw new EscalonadorException();
		}

		this.aFinalizar = nomeProcesso;
	}

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {
	}

	public void bloquearProcesso(String nomeProcesso) {
		if(!listaProcesso.contains(nomeProcesso) && rodando == null) {
			throw new EscalonadorException();
		}
		if(rodando != nomeProcesso) {
			throw new EscalonadorException();
		}
		this.processoParaSerBloqueado = nomeProcesso;
		//for (int k = 0; k < this.listaProcesso.size(); k++) {
			//if (this.listaProcesso.size() > 0) {
				//this.listaProcesso.remove(nomeProcesso);
				//this.processoBloqueado.add(nomeProcesso);
		//precisou remover esse for que está no comentário
			}
		
	

	public void retomarProcesso(String nomeProcesso) {
		if(!processoBloqueado.contains(nomeProcesso)) {
			throw new EscalonadorException();
		}
		processoRetomado.add(nomeProcesso);

	}
	
}
