package br.ufpb.dcx.aps.escalonador;

import static org.junit.Assert.*;

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
				+ "Processos: [];"
				+ "Quantum: 3;"
				+ "Tick: 0", 
				fachada.getStatus());
	}

	@Test
	public void t02_avancarTempo() {
		fachada.tick();
		assertEquals("Escalonador RoundRobin;"
				+ "Processos: [];"
				+ "Quantum: 3;"
				+ "Tick: 1", 
				fachada.getStatus());
	}

}
