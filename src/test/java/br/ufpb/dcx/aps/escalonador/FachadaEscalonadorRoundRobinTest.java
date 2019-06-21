package br.ufpb.dcx.aps.escalonador;

import static br.ufpb.dcx.aps.escalonador.TestHelper.*;

import org.junit.Before;
import org.junit.Test;

public class FachadaEscalonadorRoundRobinTest {
	
	private FachadaEscalonador fachada;
	
	@Before
	public void inicializar() {
		fachada = new FachadaEscalonador(TipoEscalonador.RoundRobin);
	}

	@Test
	public void t01_statusAposCriacao() {
		checaStatus(fachada, TipoEscalonador.RoundRobin, 3, 0);
	}

	@Test
	public void t02_avancarTempo() {
		fachada.tick();
		checaStatus(fachada, TipoEscalonador.RoundRobin, 3, 1);
	}

	@Test
	public void t03_processoSemConcorrencia() {
		fachada.adicionarProcesso("P1");
		checaStatusFila(fachada, TipoEscalonador.RoundRobin, 3, 0, "P1");
		
		fachada.tick();
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1");
		
		//Estoura o quantum mas não tira o processo P1 da CPU, pois não há concorrência
		ticks(fachada, 3);
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 4, "P1");
	}
	
	@Test
	public void t04_finalizarProcessoEmExecução() {
		fachada.adicionarProcesso("P1");
		ticks(fachada, 4);

		fachada.finalizarProcesso("P1");
		checaStatusRodando(fachada, TipoEscalonador.RoundRobin, 3, 4, "P1");

		fachada.tick();//Só efetua a ação no próximo tick
		checaStatus(fachada, TipoEscalonador.RoundRobin, 3, 5);
	}

	@Test
	public void t05_alternarProcessosEmExecução() {
		fachada.adicionarProcesso("P1");
		fachada.adicionarProcesso("P2");

		fachada.tick();
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 1, "P1", "P2");

		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 3, "P1", "P2");

		fachada.tick();
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 4, "P2", "P1");
		
		ticks(fachada, 2);
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 6, "P2", "P1");

		fachada.tick();
		checaStatusRodandoFila(fachada, TipoEscalonador.RoundRobin, 3, 7, "P1", "P2");
	}

}
