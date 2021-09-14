package interfaz;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import generadores.*;
import lectorYescritor.EscritorDeArchivos;
import objetosDeEntrada.*;
import objetosDeSalida.Itinerario;

public class Interfaz {
	private static GeneradorSugerencias generador;
	private static Scanner scanner;
	private static Adquirible sugerencia;

	// Mensajes validos como input del usuario
	private static String[] expectedYes = new String[] { "si", "s", "yes", "y", "1" };
	private static String[] expectedNo = new String[] { "no", "n", "2" };
	private static String[] expectedAccept = new String[] { "aceptar", "a", "1", "si", "s" };
	private static String[] expectedReject = new String[] { "rechazar", "r", "2", "no", "n" };

	public static void main(String[] args) throws IOException {
		generador = new GeneradorSugerencias();
		scanner = new Scanner(System.in);
		ArrayList<Itinerario> itinerarios = new ArrayList<Itinerario>();

		Iterator<Usuario> iterador = GenerarLista.deUsuarios().iterator();
		Itinerario it;
		Usuario user;
		// Se llama a la funcion para que comience la interaccion
		while (iterador.hasNext()) {
			user = iterador.next();
			sugerir(user);
			it = new Itinerario(user);
			itinerarios.add(it);
			System.out.println("--Usted tuvo " + user.getSugerenciasAdquiridas().size()
					+ " adquisicion/es, debera gastar " + it.getDineroGastado() + " moneda/s y requerira de "
					+ it.getTiempoNecesario() + " hora/s para completar su recorrido.\n");
		}
		System.out.println("No hay mas usuarios, quiere imprimir el itinerario de cada uno?(y/n): $ ");
		if (entradaUsuario(expectedYes, expectedNo).equals("si")) {
			EscritorDeArchivos.setItinerarios(itinerarios);
			System.out.println("Archivos creados.");
		} else
			System.out.println("Archivos no creados.");
		System.out.println("Fin del programa.");
	}

	// Mientras haya promociones y el usuario quiera, se sugeriran promociones
	private static void sugerir(Usuario user) {
		generador.para(user);
		System.out.println("Bienvenido " + user.getNombre() + ", usted posee " + user.getPresupuesto() + " moneda/s y "
				+ user.getTiempoDisponible() + " horas disponible/s, estas son nuestras sugerencias:");
		sugerencia = generador.sugerir();
		while (sugerencia != null) {
			System.out.println("\n--Le sugerimos la siguiente " + Sugerencias.tipo(sugerencia) + ":");
			sugerencia.imprimirEnPantalla();
			System.out.printf("Puede aceptar o rechazar(a/r): $ ");
			if (entradaUsuario(expectedAccept, expectedReject).equals("aceptar")) {
				generador.aceptarPromocion();
				System.out.println("Le quedan " + user.getPresupuesto() + " moneda(s) y " + user.getTiempoDisponible()
						+ " horas disponible(s)");
			} else
				generador.rechazarPromocion();
			sugerencia = generador.sugerir();
		}
		System.out.println("No hay mas sugerencias que cumplan con sus requisitos.\n");
	}

	// A partir de dos series de resultados esperados, determina si la entrada es
	// correcta y devuelve el primer resultado del grupo correcto
	private static String entradaUsuario(String[] expectedA, String[] expectedB) {
		String ans;
		do {
			ans = scanner.nextLine().toLowerCase();
			if (Arrays.asList(expectedA).contains(ans))
				ans = expectedA[0];
			else if (Arrays.asList(expectedB).contains(ans))
				ans = expectedB[0];
			else
				System.out.printf(
						"La respuesta no es correcta, pruebe con '" + expectedA[0] + "' o '" + expectedB[0] + "': $");
		} while (!(Arrays.asList(expectedB).contains(ans) || Arrays.asList(expectedA).contains(ans)));
		return ans;
	}
}
