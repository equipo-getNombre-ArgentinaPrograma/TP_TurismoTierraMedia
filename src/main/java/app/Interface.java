package app;

import java.util.Scanner;
import dao.AttractionDAO;
import dao.ItineraryDAO;
import dao.UserDAO;
import generator.*;
import inObject.*;
import outObject.Itinerary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Interface {
	private static SuggestionGenerator generator;
	private static Scanner scanner;
	private static Acquirable suggestion;
	private static ArrayList<User> users;
	private static User user;

	public static void main(String[] args) throws IOException {
		generator = new SuggestionGenerator();
		scanner = new Scanner(System.in);
		users = UserDAO.getAll();
		resetTables();
		Itinerary itinerary;
		System.out.println("Bienvenido a la Tierra Media! Elija su usuario para continuar (0 para salir):");
		showUsers();
		chooseUser();
		while (user != null) {
			suggest(user);
			itinerary = new Itinerary(user);
			ItineraryDAO.print(itinerary);
			System.out.println("--Usted tuvo " + user.getAcquiredSuggestions().size()
					+ " adquisicion/es, debera gastar " + itinerary.getSpentMoney() + " moneda/s y requerira de "
					+ itinerary.getSpentTime() + " hora/s para completar su recorrido.\n");
			System.out.println("Elija su usuario para continuar (0 para salir):");
			showUsers();
			chooseUser();
		}
		System.out.println("Fin del programa.");
		AttractionDAO.restoreQuota();
	}

	public static void showUsers() {
		for (User user : users)
			System.out.println(user.getId() + ". " + user.getName());
		System.out.print("$");
	}

	public static void chooseUser() {
		String selection = userSelector(ExpectedAns.users(users.size()));
		if (!selection.equals("0"))
			changeUser(Integer.parseInt(selection) - 1);
		else
			user = null;

	}

	public static void changeUser(int selection) {
		user = users.get(selection);
	}

	// Mientras haya promociones y el usuario quiera, se sugeriran promociones
	private static void suggest(User user) {
		generator.to(user);
		System.out.println("Bienvenido " + user.getName() + ", usted posee " + user.getAvailableCoins() + " moneda/s y "
				+ user.getAvailableTime() + " horas disponible/s, estas son nuestras sugerencias:");
		suggestion = generator.suggest();
		while (suggestion != null) {
			System.out.println("\n--Le sugerimos la siguiente " + Suggestions.type(suggestion) + ":");
			suggestion.printToScreen();
			System.out.printf("Puede aceptar o rechazar(a/r): $ ");
			if (userInput(ExpectedAns.accept(), ExpectedAns.reject()).equals("aceptar")) {
				generator.acceptPromotion();
				System.out.println("Le quedan " + user.getAvailableCoins() + " moneda(s) y " + user.getAvailableTime()
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

	private static String userSelector(String[] expectedAns) {
		String ans;
		do {
			ans = scanner.nextLine().toLowerCase();
			if (ans.equals("0"))
				return ans;
			if (!Arrays.asList(expectedAns).contains(ans))
				System.out.printf("El usuario " + ans + " no existe, por favor seleccione una opcion correcta: $");
		} while (!Arrays.asList(expectedAns).contains(ans));
		return ans;
	}

	private static void resetTables() {
		AttractionDAO.restoreQuota();
		UserDAO.restoreTime();
		UserDAO.restoreCoins();
		UserDAO.deleteCompras();
		ItineraryDAO.deleteAll();
	}
}
