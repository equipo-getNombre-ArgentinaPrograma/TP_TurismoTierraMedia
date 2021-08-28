package tierraMedia;

import java.io.IOException;

import generadores.*;

public class App {
	public static void main(String[] args) throws IOException {
		GenerarPromocion generador = new GenerarPromocion();
		generador.para("Sam");
		System.out.println();
		System.out.println("********* GENERADOR *********");
		System.out.println();
		
		generador.sugerirPromocion();
		generador.aceptarPromocion();
		
		generador.sugerirPromocion();
		generador.rechazarPromocion();
		
		generador.sugerirPromocion();
		generador.aceptarPromocion();
		
		generador.sugerirPromocion();
		generador.rechazarPromocion();
		
		generador.sugerirPromocion();
		generador.rechazarPromocion();

		generador.sugerirPromocion();
		generador.aceptarPromocion();

		generador.sugerirPromocion();
		generador.aceptarPromocion();

		generador.sugerirPromocion();
		generador.aceptarPromocion();

		generador.sugerirPromocion();
		generador.aceptarPromocion();

		generador.sugerirPromocion();
		generador.aceptarPromocion();
		
		generador.sugerirPromocion();
		generador.aceptarPromocion();
		generador.rechazarPromocion();
		System.out.println();
		generador.mostrarPromocionesAdquiridas();
		System.out.println();
		generador.mostrarPromocionesNoAdquiridas();
	}
}
