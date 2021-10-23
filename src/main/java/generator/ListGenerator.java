package generator;

import java.util.ArrayList;

import fileManagement.Reader;
import inObject.*;

public class ListGenerator {
	// El lector devuelve una lista de array de strings, que son los atributos para
	// construir los objetos
	
	// Si el archivo de atracciones presenta un error en la lectura, se dara por
	// terminada la ejecucion del programa ya que las promociones no van a poder
	// armarse.

/*
	public static ArrayList<Attraction> ofAttractions() {
		ArrayList<Attraction> attractions = new ArrayList<Attraction>();
		for (String[] tmp : Reader.get("entrada", "atracciones")) {
			try {
				attractions.add(new Attraction(tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]),
						Integer.parseInt(tmp[3]), tmp[4]));
			} catch (NumberFormatException e) {
				System.err.println("No se pudo leer la linea " + attractions.size()
						+ " del archivo de atracciones, se abortara la ejecucion.");
				System.exit(1);
			}
		}
		return attractions;
	}
*/
	
	// En cambio, si un usuario no puede leerse simplemente no se agregara a la
	// lista
	public static ArrayList<User> ofUsers() {
		ArrayList<User> users = new ArrayList<User>();
		for (String[] tmp : Reader.get("entrada", "usuarios")) {
			try {
				users.add(new User(1, tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), (tmp[3])));
			} catch (NumberFormatException e) {
				System.err.println("No se pudo leer la linea " + users.size() + " del archivo de usuarios.");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("No se pudo leer la linea " + users.size() + " del archivo de usuarios.");
			}
		}
		return users;
	}
/*
	// Lo mismo sucede con las promociones
	public static ArrayList<Promotion> ofPromotions(ArrayList<Attraction> atracciones) {
		ArrayList<Promotion> promos = new ArrayList<Promotion>();
		Promotion promo = null;
		for (String[] tmp : Reader.get("entrada", "promociones")) {
			try {
				if (tmp[0].equals("Porcentual"))
					promo = new PorcentualProm(atracciones, tmp[1], tmp[2], tmp[3], Double.parseDouble(tmp[4]));
				else if (tmp[0].equals("Absoluta"))
					promo = new AbsoluteProm(atracciones, tmp[1], tmp[2], tmp[3], Double.parseDouble(tmp[4]));
				else if (tmp[0].equals("AxB"))
					promo = new AxBProm(atracciones, tmp[1], tmp[2], tmp[3], tmp[4]);
			} catch (NumberFormatException e) {
				System.err.println("No se pudo leer la linea " + promos.size() + " del archivo de promos.");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("No se pudo leer la linea " + promos.size() + " del archivo de promos.");
			}
			if (promo != null)
				promos.add(promo);
		}
		return promos;
	}
*/
}
