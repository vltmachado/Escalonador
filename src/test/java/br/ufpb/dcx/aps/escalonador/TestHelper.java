package br.ufpb.dcx.aps.escalonador;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class TestHelper {
	
	public static void ticks(FachadaEscalonador fachada, int vezes) {
		for (int i = 0; i < vezes; i++) {
			fachada.tick();
		}
	}

	public static void checaStatusRodandoFila(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks, String rodando, String... fila) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Rodando: " + rodando + ", Fila: " + Arrays.toString(fila) + "};"
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.getStatus());
	}
	
	public static void checaStatusRodando(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks, String rodando) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Rodando: " + rodando + "};"
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.getStatus());
	}

	public static void checaStatusFila(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks, String... fila) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {Fila: " + Arrays.toString(fila) + "};"  
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.getStatus());
	}
	
	public static void checaStatus(FachadaEscalonador fachada, TipoEscalonador escalonador, int quantum, 
			int ticks) {
		assertEquals("Escalonador " + escalonador + ";"
				+ "Processos: {};"
				+ "Quantum: " + quantum + ";"
				+ "Tick: " + ticks, 
				fachada.getStatus());
	}
	
}
