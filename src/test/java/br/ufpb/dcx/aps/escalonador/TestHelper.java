package br.ufpb.dcx.aps.escalonador;

public class TestHelper {
	
	public static void ticks(FachadaEscalonador fachada, int vezes) {
		for (int i = 0; i < vezes; i++) {
			fachada.tick();
		}
	}

}
