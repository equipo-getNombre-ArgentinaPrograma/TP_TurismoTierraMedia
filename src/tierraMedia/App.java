package tierraMedia;

import java.io.IOException;

import generadores.*;

public class App {
	public static void main(String[] args) throws IOException {
		GenerarPromocion generador = new GenerarPromocion();
		System.out.println();
		System.out.println("********* GENERADOR *********");
		System.out.println();
		
		generador.para("Eowyn");
		
		generador.mostrarPromosAptas();
		generador.sugerirPromocion();
		generador.aceptarPromocion();
		
		generador.mostrarPromosAdquiridas();
	}
}
