package br.ufpb.dcx.aps.escalonador;

import static org.junit.Assert.*;
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
		assertEquals("Escalonador RoundRobin;"
				+ "Processos: {};"
				+ "Quantum: 3;"
				+ "Tick: 0", 
				fachada.getStatus());
	}

	@Test
	public void t02_avancarTempo() {
		fachada.tick();
		assertEquals("Escalonador RoundRobin;"
				+ "Processos: {};"
				+ "Quantum: 3;"
				+ "Tick: 1", 
				fachada.getStatus());
	}

	@Test
	public void t03_processoSemConcorrencia() {
		fachada.adicionarProcesso("P1");
		assertEquals("Escalonador RoundRobin;"
				+ "Processos: {Fila: [P1]};"
				+ "Quantum: 3;"
				+ "Tick: 0", 
				fachada.getStatus());
		
		fachada.tick();
		assertEquals("Escalonador RoundRobin;"
				+ "Processos: {Rodando: P1};"
				+ "Quantum: 3;"
				+ "Tick: 1", 
				fachada.getStatus());
		
		//Estoura o quantum mas não tira o processo P1 da CPU, pois não há concorrência
		ticks(fachada, 3);
		assertEquals("Escalonador RoundRobin;"
				+ "Processos: {Rodando: P1};"
				+ "Quantum: 3;"
				+ "Tick: 4", 
				fachada.getStatus());		
	}
	
	@Test
	public void t04_finalizarProcessoEmExecução() {
		fachada.adicionarProcesso("P1");
		ticks(fachada, 4);

		fachada.finalizarProcesso("P1");
		assertEquals("Escalonador RoundRobin;"
				+ "Processos: {Rodando: P1};"
				+ "Quantum: 3;"
				+ "Tick: 4", 
				fachada.getStatus());

		fachada.tick();//Só efetua a ação no próximo tick
		assertEquals("Escalonador RoundRobin;"
				+ "Processos: {};"
				+ "Quantum: 3;"
				+ "Tick: 5", 
				fachada.getStatus());
	}

}
