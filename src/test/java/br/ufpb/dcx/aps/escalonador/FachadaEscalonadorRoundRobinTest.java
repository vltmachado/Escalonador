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
	public void statusAposCriacao() {
		assertEquals("Escalonador RoundRobin\n"
				+ "Processos: []\n"
				+ "Quantum: 3\n"
				+ "Tick: 0", 
				fachada.getStatus());
	}

}
