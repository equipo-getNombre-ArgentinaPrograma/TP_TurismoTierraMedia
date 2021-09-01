package tierraMedia;

import generadores.*;

public class App {
	public static void main(String[] args) {
		GenerarPromocion generador = new GenerarPromocion();

		System.out.printf("********* GENERADOR *********\n\n");
		
		generador.para("Sam");
		generador.sugerirPromocion();
		
		// interactuar con el usuario mediante consola
		// generar el itinerario a partir de las promociones adquiridas y exportarlo a un archivo
	}
}
