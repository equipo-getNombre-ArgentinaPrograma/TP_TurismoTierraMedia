package app;

import java.util.Scanner;

import dao.AttractionDAO;
import dao.UserDAO;
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
	//private static ArrayList<User> users = UserDAO.getAll();
	private static ArrayList<User> users = ListGenerator.ofUsers();
	
	public static void main(String[] args) throws IOException {
		generator = new SuggestionGenerator();
		scanner = new Scanner(System.in);
		ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
		Itinerary itinerary;
		User user;
		String index = "0";
		System.out.println("Bienvenido a la Tierra Media! Elija su usuario para continuar:");
		showUsers();	
		System.out.print("$");
		index = userInput(ExpectedAns.users(users.size()));
		while (index != "0") {
			user = users.get(Integer.parseInt(index));
			suggest(user);
			itinerary = new Itinerary(user);
			itineraries.add(itinerary);
			System.out.println("--Usted tuvo " + user.getAcquiredSuggestions().size()
					+ " adquisicion/es, debera gastar " + itinerary.getSpentMoney() + " moneda/s y requerira de "
					+ itinerary.getSpentTime() + " hora/s para completar su recorrido.\n");
			showUsers();
			index = userInput(ExpectedAns.users(users.size()));
		}
		System.out.println("No hay mas usuarios, quiere imprimir el itinerario de cada uno?(y/n): $ ");
		if (userInput(ExpectedAns.yes(), ExpectedAns.no()).equals("si")) {
			Writer.setItinerary(itineraries);
			System.out.println("Archivos creados.");
		} else
			System.out.println("Archivos no creados.");
		System.out.println("Fin del programa.");
		AttractionDAO.restoreQuota();
	}
	
	public static void showUsers() {
		for(User user : users)
			System.out.println(user.getId() + ". " + user.getName());
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
			if (userInput(ExpectedAns.accept(), ExpectedAns.reject()).equals("aceptar")) {
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
	
	private static String userInput(String[] expectedAns) {
		return userInput(expectedAns, expectedAns);
	}
}
