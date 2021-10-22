package app;

import java.util.Scanner;

import dao.AttractionDAO;
import fileManagement.Writer;
import generator.*;
import inObject.*;
import outObject.Itinerary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Interface {
	private static SuggestionGenerator generator;
	private static Scanner scanner;
	private static Acquirable suggestion;

	// Mensajes validos como input del usuario
	private static String[] expectedYes = new String[] { "si", "s", "yes", "y", "1" };
	private static String[] expectedNo = new String[] { "no", "n", "2" };
	private static String[] expectedAccept = new String[] { "aceptar", "a", "1", "si", "s" };
	private static String[] expectedReject = new String[] { "rechazar", "r", "2", "no", "n" };

	public static void main(String[] args) throws IOException {
		generator = new SuggestionGenerator();
		scanner = new Scanner(System.in);
		Iterator<User> iterator = ListGenerator.ofUsers().iterator();
		
		ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
		Itinerary itinerary;
		User user;
		// Se llama a la funcion para que comience la interaccion	
		while (iterator.hasNext()) {
			user = iterator.next();
			suggest(user);
			itinerary = new Itinerary(user);
			itineraries.add(itinerary);
			System.out.println("--Usted tuvo " + user.getAcquiredSuggestions().size()
					+ " adquisicion/es, debera gastar " + itinerary.getSpentMoney() + " moneda/s y requerira de "
					+ itinerary.getSpentTime() + " hora/s para completar su recorrido.\n");
		}
		System.out.println("No hay mas usuarios, quiere imprimir el itinerario de cada uno?(y/n): $ ");
		if (userInput(expectedYes, expectedNo).equals("si")) {
			Writer.setItinerary(itineraries);
			System.out.println("Archivos creados.");
		} else
			System.out.println("Archivos no creados.");
		System.out.println("Fin del programa.");
		AttractionDAO.restoreQuota();
	}

	// Mientras haya promociones y el usuario quiera, se sugeriran promociones
	private static void suggest(User user) {
		generator.to(user);
		System.out.println("Bienvenido " + user.getName() + ", usted posee " + user.getAvailableMoney() + " moneda/s y "
				+ user.getAvailableTime() + " horas disponible/s, estas son nuestras sugerencias:");
		suggestion = generator.suggest();
		while (suggestion != null) {
			System.out.println("\n--Le sugerimos la siguiente " + Suggestions.type(suggestion) + ":");
			suggestion.printToScreen();
			System.out.printf("Puede aceptar o rechazar(a/r): $ ");
			if (userInput(expectedAccept, expectedReject).equals("aceptar")) {
				generator.acceptPromotion();
				System.out.println("Le quedan " + user.getAvailableMoney() + " moneda(s) y " + user.getAvailableTime()
						+ " horas disponible(s)");
			} else
				generator.rejectPromotion();
			suggestion = generator.suggest();
		}
		System.out.println("No hay mas sugerencias que cumplan con sus requisitos.\n");
	}

	// A partir de dos series de resultados esperados, determina si la entrada es
	// correcta y devuelve el primer resultado del grupo correcto
	private static String userInput(String[] expectedA, String[] expectedB) {
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
