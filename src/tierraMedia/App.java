package tierraMedia;

import java.io.IOException;

import generadores.*;
import objetosDeEntrada.*;

public class App {
	public static void main(String[] args) throws IOException {
		Atraccion[] atracciones = GenerarArray.deAtracciones();
		System.out.println();
		Usuario[] usuarios = GenerarArray.deUsuarios();
		System.out.println();
		Promocion[] promosPor = GenerarArray.dePromocionesPorcentuales(atracciones);
		System.out.println();
		Promocion[] promosAbs = GenerarArray.dePromocionesAbsolutas(atracciones);
		System.out.println();
		Promocion[] promosAxB = GenerarArray.dePromocionesAxB(atracciones);
		System.out.println();
	}
}
