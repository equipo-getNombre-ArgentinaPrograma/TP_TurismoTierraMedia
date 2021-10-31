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
import java.util.Random;

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
		System.out.print("Bienvenido a la Tierra Media! ");
		welcomeMessage();
		showUsers();
		chooseUser();
		while (user != null) {
			suggest(user);
			itinerary = new Itinerary(user);
			itinerary.print();
			System.out.println("--Usted tuvo " + user.getAcquiredSuggestions().size()
					+ " adquisicion/es, debera gastar " + itinerary.getSpentCoins() + " moneda/s y requerira de "
					+ itinerary.getSpentTime() + " hora/s para completar su recorrido.\n");
			welcomeMessage();
			showUsers();
			chooseUser();
		}
		System.out.println("Fin del programa.");
		AttractionDAO.restoreQuota();
	}

// Muestra en pantalla un mensaje al azar de cada grupo para mostrar al usuario
	public static void welcomeMessage() {
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("Elija su usuario para continuar (0 para salir)");
		list1.add("Seleccione su usuario (escriba 0 para salir del sistema)");
		list1.add("Por favor seleccione su usuario para continuar (0 para salir)");

		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("Puede elegir por ID o por nombre");
		list2.add("ID y nombre se consideran opciones validas");
		list2.add("Puede seleccionar por nombre o numero de usuario");

		ArrayList<String> list3 = new ArrayList<String>();
		list3.add("Le recordamos que escribiendo 'crear' puede crear un nuevo usuario");
		list3.add("Puede crear un usuario escribiendo 'crear'");
		list3.add("Tambien puede crear un usuario escribiendo 'Crear'");

		Random random = new Random();
		System.out.printf(list1.get(random.nextInt(list1.size())) + "\n");
		System.out.printf(list2.get(random.nextInt(list2.size())) + "\n");
		System.out.printf(list3.get(random.nextInt(list3.size())) + "\n");
	}

// Muestra una lista de todos los usuarios
	public static void showUsers() {
		for (User user : users)
			System.out.println(user.getId() + ". " + user.getName());
		System.out.print("$ ");
	}

// Elige un usuario valido
	public static void chooseUser() {
		String selection = selector(ExpectedAns.users(users));
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
		System.out.println("No hay sugerencias que cumplan con sus requisitos.\n");
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

// Toma el input del usuario
	private static String userInput() {
		String ans;
		ans = scanner.nextLine();
		return ans;
	}

// Toma un input del usuario determinado
	private static String userInput(String type) {
		if (type.equals("Double"))
			return Double.toString(askForDouble());
		String ans = scanner.nextLine();
		return ans;
	}

// Pide al usuario un numero real positivo
	private static Double askForDouble() {
		Double ans;
		while (true) {
			if (!scanner.hasNextDouble()) {
				System.out.println("La respuesta debe ser un numero real positivo: $ ");
				scanner.next();
			}
			ans = Double.parseDouble(scanner.nextLine());
			if (ans >= 0)
				return ans;
			else
				System.out.println("El numero debe ser igual o mayor a cero: $ ");
		}
	}

// Se ocupa de la seleccion del usuario, crear uno nuevo o salir del sistema
	private static String selector(String[] expectedAns) {
		String ans;
		do {
			ans = userInput();
			// Si el input coincide con el nombre de un usuario, devuelve el id
			for (User user : users)
				if (ans.equals(user.getName().toLowerCase()))
					return Integer.toString(user.getId());
			// Si es 0 da por terminado el programa
			if (ans.equals("0"))
				return ans;
			// Si es 'crear', crea un usuario y devuelve el id del usuario creado
			if (ans.equals("crear")) {
				return Integer.toString(createUser().getId());
			}
			if (!Arrays.asList(expectedAns).contains(ans))
				System.out.printf("El usuario " + ans + " no existe, por favor seleccione una opcion correcta: $ ");
		} while (!Arrays.asList(expectedAns).contains(ans));
		return ans;
	}

// Crea un usuario, lo guarda en memoria y en db
	private static User createUser() {
		System.out.print("Bienvenido al creador de usuario, indique su nombre: $ ");
		String name = userInput();
		System.out.print("Indique sus monedas disponibles: $ ");
		Double coins = Double.parseDouble(userInput("Double"));
		System.out.print("Indique su tiempo disponible: $ ");
		Double time = Double.parseDouble(userInput("Double"));
		System.out.print("Indique su tipo de atraccion preferida: $ ");
		String type = userInput().toLowerCase();
		User newUser = new User(users.size() + 1, name, coins, time, type);
		UserDAO.newUser(newUser);
		users.add(newUser);
		System.out.println("Felicitaciones " + newUser.getName() + "! Ya puede usar el sistema.\n");
		return newUser;
	}

// Solo para test de sistema, devuelve las tablas a su valor original
	public static void resetTables() {
		AttractionDAO.restoreQuota();
		UserDAO.restoreTime();
		UserDAO.restoreCoins();
		UserDAO.deleteCompras();
		ItineraryDAO.deleteAll();
	}
}