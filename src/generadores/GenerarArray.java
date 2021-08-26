package generadores;

import objetosDeEntrada.*;

public class GenerarArray {
	Atraccion atraccion;
	Usuario usuario;
	Promocion promocionPorcentual;
	Promocion promocionAbsoluta;
	Promocion promocionAxB;

	Atraccion[] atracciones;
	int indice;

	public GenerarArray() {
		this.atraccion = new Atraccion();
		this.usuario = new Usuario();
	}

	public Atraccion[] deAtracciones() {
		atraccion.leerArchivo();
		Atraccion[] atracciones = new Atraccion[atraccion.getTamanio()];
		indice = atraccion.getIndice();
		do {
			atracciones[indice] = atraccion.siguienteAtraccion();
			indice = atraccion.getIndice();
		} while (indice != 0);
		this.atracciones = atracciones;
		return atracciones;
	}

	public Usuario[] deUsuarios() {
		usuario.leerArchivo();
		Usuario[] usuarios = new Usuario[usuario.getTamanio()];
		indice = usuario.getIndice();
		do {
			usuarios[indice] = usuario.siguienteUsuario();
			indice = usuario.getIndice();
		} while (indice != 0);
		return usuarios;
	}

	public Promocion[] dePromocionesPorcentuales() {
		this.promocionPorcentual = new PromocionPorcentual(atracciones);
		promocionPorcentual.leerArchivo();
		Promocion[] promocionesPorcentuales = new Promocion[promocionPorcentual.getTamanio()];
		indice = promocionPorcentual.getIndice();
		do {
			promocionesPorcentuales[indice] = (PromocionPorcentual) promocionPorcentual.siguientePromocion();
			promocionesPorcentuales[indice].getPrecio();
			indice = promocionPorcentual.getIndice();
		} while (indice != 0);
		return promocionesPorcentuales;
	}

	public Promocion[] dePromocionesAbsolutas() {
		this.promocionAbsoluta = new PromocionAbsoluta(atracciones);
		promocionAbsoluta.leerArchivo();
		Promocion[] promocionesAbsolutas = new Promocion[promocionAbsoluta.getTamanio()];
		indice = promocionAbsoluta.getIndice();
		do {
			promocionesAbsolutas[indice] = (PromocionAbsoluta) promocionAbsoluta.siguientePromocion();
			indice = promocionAbsoluta.getIndice();
		} while (indice != 0);
		return promocionesAbsolutas;
	}

	public Promocion[] dePromocionesAxB() {
		this.promocionAxB = new PromocionAxB(atracciones);
		promocionAxB.leerArchivo();
		Promocion[] promocionesAxB = new Promocion[promocionAxB.getTamanio()];
		indice = promocionAxB.getIndice();
		do {
			promocionesAxB[indice] = (PromocionAxB) promocionAxB.siguientePromocion();
			promocionesAxB[indice].getPrecio();
			indice = promocionAxB.getIndice();
		} while (indice != 0);
		return promocionesAxB;
	}
}
