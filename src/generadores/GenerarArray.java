package generadores;

import java.io.IOException;

import lecturaYescritura.*;
import objetosDeEntrada.*;

public class GenerarArray {
	Atraccion atraccion;
	Usuario usuario;
	Promocion promocionPorcentual;
	Promocion promocionAbsoluta;
	Promocion promocionAxB;

	Atraccion[] atracciones;
	String[] tmp;
	int indice;

	public static Atraccion[] deAtracciones() throws IOException {
		LectorDeArchivos lector = new LectorDeArchivos("atracciones.txt");
		int cantidadDeDatos = lector.abrir();
		Atraccion[] atracciones = new Atraccion[cantidadDeDatos];
		for (int i = 0; i < cantidadDeDatos; i++) {
			String[] tmp = lector.leerLinea();
			atracciones[i] = new Atraccion(tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]),
			Integer.parseInt(tmp[3]), tmp[4]);
			//System.out.println(atracciones[i].toString());
		}
		return atracciones;
	}

	public static Usuario[] deUsuarios() throws IOException {
		LectorDeArchivos lector = new LectorDeArchivos("usuarios.txt");
		int cantidadDeDatos = lector.abrir();
		Usuario[] usuarios = new Usuario[cantidadDeDatos];
		for (int i = 0; i < cantidadDeDatos; i++) {
			String[] tmp = lector.leerLinea();
			usuarios[i] = new Usuario(tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), (tmp[3]));
			//System.out.println(usuarios[i].toString());
		}
		return usuarios;
	}

	public static Promocion[] dePromocionesPorcentuales(Atraccion[] atracciones) throws IOException {
		LectorDeArchivos lector = new LectorDeArchivos("promocionesPorcentuales.txt");
		int cantidadDeDatos = lector.abrir();
		Promocion[] promociones = new Promocion[cantidadDeDatos];
		for (int i = 0; i < cantidadDeDatos; i++) {
			String[] tmp = lector.leerLinea();
			promociones[i] = new PromocionPorcentual(atracciones, tmp[0], tmp[1], tmp[2], Double.parseDouble(tmp[3]));
			//System.out.println(promociones[i].toString());
		}
		return promociones;
	}

	public static Promocion[] dePromocionesAbsolutas(Atraccion[] atracciones) throws IOException {
		LectorDeArchivos lector = new LectorDeArchivos("promocionesAbsolutas.txt");
		int cantidadDeDatos = lector.abrir();
		Promocion[] promociones = new Promocion[cantidadDeDatos];
		for (int i = 0; i < cantidadDeDatos; i++) {
			String[] tmp = lector.leerLinea();
			promociones[i] = new PromocionAbsoluta(atracciones, tmp[0], tmp[1], tmp[2], Double.parseDouble(tmp[3]));
			//System.out.println(promociones[i].toString());
		}
		return promociones;
	}

	public static Promocion[] dePromocionesAxB(Atraccion[] atracciones) throws IOException {
		LectorDeArchivos lector = new LectorDeArchivos("promocionesAxB.txt");
		int cantidadDeDatos = lector.abrir();
		Promocion[] promociones = new Promocion[cantidadDeDatos];
		for (int i = 0; i < cantidadDeDatos; i++) {
			String[] tmp = lector.leerLinea();
			promociones[i] = new PromocionAxB(atracciones, tmp[0], tmp[1], tmp[2], tmp[3]);
			//System.out.println(promociones[i].toString());
		}
		return promociones;
	}
}
