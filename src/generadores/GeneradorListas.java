package generadores;

import java.util.ArrayList;

import lectorYescritor.LectorDeArchivos;
import objetosDeEntrada.*;

public class GeneradorListas {
	// El lector devuelve un array de strings, que son los atributos para construir
	// el objeto que se agrega a la lista

	// Si el archivo de atracciones presenta un error en la lectura, se dara por
	// terminada la ejecucion del programa ya que las promociones no van a poder
	// armarse.
	public static ArrayList<Atraccion> deAtracciones() {
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		for (String[] tmp : LectorDeArchivos.get("atracciones")) {
			try {
				atracciones.add(new Atraccion(tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]),
						Integer.parseInt(tmp[3]), tmp[4]));
			} catch (NumberFormatException e) {
				System.err.println("No se pudo leer la linea " + atracciones.size()
						+ " del archivo de atracciones, se abortara la ejecucion.");
				System.exit(1);
			}
		}
		return atracciones;
	}

	// En cambio, si un usuario no puede leerse simplemente no se agregara a la
	// lista
	public static ArrayList<Usuario> deUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		for (String[] tmp : LectorDeArchivos.get("usuarios")) {
			try {
				usuarios.add(new Usuario(tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), (tmp[3])));
			} catch (NumberFormatException e) {
				System.err.println("No se pudo leer la linea " + usuarios.size() + " del archivo de usuarios.");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("No se pudo leer la linea " + usuarios.size() + " del archivo de usuarios.");
			}
		}
		return usuarios;
	}

	// Lo mismo sucede con las promociones
	public static ArrayList<Promocion> dePromos() {
		ArrayList<Promocion> promos = new ArrayList<Promocion>();
		ArrayList<Atraccion> atracciones = GeneradorListas.deAtracciones();
		Promocion promo = null;
		for (String[] tmp : LectorDeArchivos.get("promociones")) {
			try {
				if (tmp[0].equals("Porcentual"))
					promo = new PromocionPorcentual(atracciones, tmp[1], tmp[2], tmp[3], Double.parseDouble(tmp[4]));
				else if (tmp[0].equals("Absoluta"))
					promo = new PromocionAbsoluta(atracciones, tmp[1], tmp[2], tmp[3], Double.parseDouble(tmp[4]));
				else if (tmp[0].equals("AxB"))
					promo = new PromocionAxB(atracciones, tmp[1], tmp[2], tmp[3], tmp[4]);
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
}