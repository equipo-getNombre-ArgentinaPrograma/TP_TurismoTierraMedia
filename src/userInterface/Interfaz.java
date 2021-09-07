package userInterface;

import java.util.Scanner;
import java.util.Arrays;

import generadores.GenerarPromocion;

public class Interfaz {
	public static GenerarPromocion generador;
	public static Scanner scanner;
	public static String ans;

	public static boolean isFirsTime = true;
	public static boolean validUser = false;

	// Mensajes validos como input del usuario
	public static String[] expectedYes = new String[] { "si", "s", "yes", "y", "1" };
	public static String[] expectedNo = new String[] { "no", "n", "2" };
	public static String[] expectedAccept = new String[] { "aceptar", "a", "1" };
	public static String[] expectedReject = new String[] { "rechazar", "r", "2" };
	public static String[] expectedChoice = new String[] { "1", "2", "3", "4", "5", "6", "7" };

	public static void main(String[] args) {
		// Se inicializa generador, el scanner y se llama a la funcion para que comience
		// la interaccion
		generador = new GenerarPromocion();
		scanner = new Scanner(System.in);
		eleccion();

	}

	public static void eleccion() {
		if (!isFirsTime) {
			System.out.println("Que desea hacer?:");
			System.out.printf("1 * Sugerir promociones.\n" + "2 * Mostrar que promociones puedo comprar.\n"
					+ "3 * Mostrar mis promociones aceptadas.\n" + "4 * Mostrar itinerario.\n"
					+ "5 * Imprimir itinerario.\n" + "6 * Cambiar usuario.\n" + "7 * Salir.\n--> ");
		} else
			elegirUsuario();
		ans = entradaUsuario(expectedChoice);
		switch (ans) {
		case "1":
			sugerir();
			break;
		case "2":
			puedoComprar();
			break;
		case "3":
			// System.out.println("HOLAA");
			System.out.println(generador.promosAdquiridasToString());
			break;
		case "4":
			// System.out.println(generador.itinerarioToString());
			break;
		case "5":
			// imprimirItinerario();
			break;
		case "6":
			elegirUsuario();
			break;
		case "7":
			System.out.println("Hasta la proxima!");
			System.exit(0);
			break;
		}
	}

	// Mientras haya promociones y el usuario quiera, se sugeriran promociones
	public static void sugerir() {
		do {
			generador.sugerirPromocion();
			if (generador.hayPromosXsugerir()) {
				System.out.printf("Puede aceptar o rechazar: ");
				ans = entradaUsuario(expectedAccept, expectedReject);
				if (ans.equals("aceptar"))
					generador.aceptarPromocion();
				else
					generador.rechazarPromocion();
				System.out.printf("Desea que se le sugiera una promocion?(s/n): ");
				ans = entradaUsuario(expectedYes, expectedNo);
			}
		} while (ans.equals("yes") && generador.hayPromosXsugerir());
		eleccion();
	}

	// Se "Logea" con el usuario pedido
	public static void elegirUsuario() {
		System.out.printf("Bienvenido al sistema, cual es su nombre?: ");
		while (generador.para(entradaUsuario())) {
			System.out.println(
					"Hola " + generador.getUsuario().getNombre() + "! Quiere que se le sugiera una promocion?");
			System.out.printf("Escriba Si o No para continuar: ");
			isFirsTime = false;
			if (entradaUsuario(expectedYes, expectedNo).equals("yes"))
				sugerir();
			else
				eleccion();
		}
		System.out.printf("El nombre es incorrecto, ingrese nuevamente: ");
		elegirUsuario();
	}

	// Muestra que promociones que puede comprar a partir de su tiempo y dinero
	public static void puedoComprar() {
		System.out.println("Promociones acordes a tus gustos:");
		System.out.println(generador.promosAptasToString());
		System.out.println("Promociones NO acordes a tus gustos:");
		System.out.println(generador.promosNoAptasToString());
		System.out.printf("Presione ENTER para volver...\n");
		entradaUsuario();
		eleccion();
	}

	// A partir de resultados esperados, determina si el input pertenece a ese grupo
	public static String entradaUsuario(String[] expected) {
		do {
			ans = scanner.nextLine().toLowerCase();
			if (!Arrays.asList(expected).contains(ans)) {
				System.out.printf("La respuesta no es correcta, pruebe con ");
				for (String word : expected)
					System.out.printf("'" + word + "' ");
			}
		} while (!Arrays.asList(expected).contains(ans));
		return ans;
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

	// Pide input al usuario sin hacer ningun check
	public static String entradaUsuario() {
		return scanner.nextLine();
	}
}
