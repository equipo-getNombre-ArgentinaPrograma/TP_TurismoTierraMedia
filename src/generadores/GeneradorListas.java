package generadores;

import java.util.ArrayList;
import lecturaYescritura.*;
import objetosDeEntrada.*;

public class GeneradorListas {
	public static ArrayList<Atraccion> deAtracciones() {
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		for (String[] tmp : LectorDeArchivos.get("atracciones"))
			atracciones.add(new Atraccion(tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]),
					Integer.parseInt(tmp[3]), tmp[4]));
		return atracciones;
	}

	public static ArrayList<Usuario> deUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		for (String[] tmp : LectorDeArchivos.get("usuarios"))
			usuarios.add(new Usuario(tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), (tmp[3])));
		return usuarios;
	}

	public static ArrayList<Promocion> dePromos() {
		ArrayList<Promocion> promos = new ArrayList<Promocion>();
		Promocion promo = null;
		for (String[] tmp : LectorDeArchivos.get("promociones")) {
			if (tmp[0].equals("Porcentual"))
				promo = new PromocionPorcentual(GeneradorListas.deAtracciones(), tmp[1], tmp[2], tmp[3],
						Double.parseDouble(tmp[4]));
			else if (tmp[0].equals("Absoluta"))
				promo = new PromocionAbsoluta(GeneradorListas.deAtracciones(), tmp[1], tmp[2], tmp[3],
						Double.parseDouble(tmp[4]));
			else if (tmp[0].equals("AxB"))
				promo = new PromocionAxB(GeneradorListas.deAtracciones(), tmp[1], tmp[2], tmp[3], tmp[4]);
			promos.add(promo);
		}
		return promos;
	}
}