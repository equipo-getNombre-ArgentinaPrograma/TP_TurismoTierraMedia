package userInterface;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Iterator;

import generadores.*;
import objetosDeEntrada.*;

public class Interfaz {
	public static GenerarSugerencias generador;
	public static Scanner scanner;
	public static String ans;

	public static boolean isFirsTime = true;
	public static boolean validUser = false;

	// Mensajes validos como input del usuario
	public static String[] expectedYes = new String[] { "si", "s", "yes", "y", "1" };
	public static String[] expectedNo = new String[] { "no", "n", "2" };
	public static String[] expectedAccept = new String[] { "aceptar", "a", "1", "si", "s" };
	public static String[] expectedReject = new String[] { "rechazar", "r", "2", "no", "n" };

	public static void main(String[] args) {
		// Se inicializa generador, el scanner y se llama a la funcion para que comience
		// la interaccion
		generador = new GenerarSugerencias();
		scanner = new Scanner(System.in);
		Iterator<Usuario> iterador = GeneradorListas.deUsuarios().iterator();
		Usuario user;
		while (iterador.hasNext()) {
			user = iterador.next();
			sugerir(user);
		}
	}

	// Mientras haya promociones y el usuario quiera, se sugeriran promociones
	public static void sugerir(Usuario user) {
		generador.para(user);
		System.out.printf(
				"Bienvenido al sistema " + user.getNombre() + ", usted posee " + user.getPresupuesto() + " moneda(s) y "
						+ user.getTiempoDisponible() + " horas disponible(s), estas son nuestras sugerencias:\n");
		do {
			generador.sugerirPromocion();
			if (generador.hayPromosXsugerir()) {
				System.out.printf("\nPuede aceptar o rechazar(a/r): $_");
				ans = entradaUsuario(expectedAccept, expectedReject);
				if (ans.equals("aceptar")) {
					generador.aceptarPromocion();
					System.out.println("Le quedan " + user.getPresupuesto() + " moneda(s) y "
							+ user.getTiempoDisponible() + " horas disponible(s)");
				} else
					generador.rechazarPromocion();
			}
		} while (generador.hayPromosXsugerir());
	}

	// A partir de dos series de resultados esperados, determina si la entrada es
	// correcta y devuelve el primer resultado del grupo correcto
	public static String entradaUsuario(String[] expectedA, String[] expectedB) {
		do {
			ans = scanner.nextLine().toLowerCase();
			if (Arrays.asList(expectedA).contains(ans))
				ans = expectedA[0];
			else if (Arrays.asList(expectedB).contains(ans))
				ans = expectedB[0];
			else
				System.out.println(
						"La respuesta no es correcta, pruebe con '" + expectedA[0] + "' o '" + expectedB[0] + "'");
		} while (!(Arrays.asList(expectedB).contains(ans) || Arrays.asList(expectedA).contains(ans)));
		return ans;
	}
}
