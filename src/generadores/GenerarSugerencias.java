package generadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import objetosDeEntrada.*;

public class GenerarSugerencias {
	Usuario usuario;
	private ArrayList<Promocion> promos;
	private ArrayList<Atraccion> atracciones;

	private ArrayList<PuedeSerComprada> sugerenciasApt;
	private ArrayList<PuedeSerComprada> sugerenciasNoApt;

	private boolean haySugerencia = false;
	private PuedeSerComprada sugerencia;

	// Genera las listas con los datos leidos del archivo
	public GenerarSugerencias() {
		this.promos = GeneradorListas.dePromos();
		this.atracciones = GeneradorListas.deAtracciones();

	}

	// Constructor con parametro
	public GenerarSugerencias(Usuario usuario) throws IOException {
		this();
		para(usuario);
		inicializarListas();
	}

	// Devuelve el objeto usuario al cual esta ligado el sistema
	public Usuario getUsuario() {
		return usuario;
	}

	// Guarda las promociones aptas para el usuario en una lista
	public void para(Usuario usuario) {
		this.usuario = usuario;
		inicializarListas();
	}

	// Inicializa las listas y las ordena por tipo preferido en aptas y no aptas
	public void inicializarListas() {
		sugerenciasApt = new ArrayList<PuedeSerComprada>();
		sugerenciasNoApt = new ArrayList<PuedeSerComprada>();
		aniadirAlistas(promos);
		aniadirAlistas(atracciones);
	}

	public void aniadirAlistas(ArrayList<? extends PuedeSerComprada> listaObjetos) {
		for (PuedeSerComprada o : listaObjetos)
			if (getUsuario().getTipoPreferido().equals(o.getTipoDeAtraccion()))
				sugerenciasApt.add(o);
			else
				sugerenciasNoApt.add(o);
	}

	// Sugiere la mejor promocion ligada a sus preferencias
	public void sugerirPromocion() {
		this.filtrarSugerencias(sugerenciasApt);
		if (sugerenciasApt.size() > 0) {
			sugerencia = sugerenciasApt.get(0);
			System.out.println("\n--Le sugerimos la siguiente " + Sugerencia.tipo(sugerencia.getClass().getSimpleName())
					+ ": " + sugerencia.toString() + "\n");
			this.haySugerencia = true;
		} else
			this.sugerirPromocionNoApta();
	}

	// Sugiere la mejor promocion la cual no esta ligada a sus preferencias
	public void sugerirPromocionNoApta() {
		this.filtrarSugerencias(sugerenciasNoApt);
		if (sugerenciasNoApt.size() > 0) {
			sugerencia = sugerenciasNoApt.get(0);
			System.out.println("\n--No encontramos mas sugerencias que cumplan con sus requisitos, podemos sugerirle la "
					+ Sugerencia.tipo(sugerencia.getClass().getSimpleName()) + ":" + sugerencia.toString());
			this.haySugerencia = true;
		} else {
			System.out.println("No hay mas promociones disponibles para sugerir.");
			this.haySugerencia = false;
		}
		System.out.println();
	}

	// Se acepta la promo sugerida y se agrega a la lista ligada al usuario
	public void aceptarPromocion() {
		if (haySugerencia && getUsuario().adquirir(sugerencia)) {
			System.out.println("Adquiriste la promocion con exito.");
			sugerencia.usarCupos();
			this.haySugerencia = false;
		}
	}

	// Se rechaza la promo sugerida y se agrega a la lista ligada al usuario
	public void rechazarPromocion() {
		if (haySugerencia && getUsuario().rechazar(sugerencia)) {
			System.out.println("Rechazaste la promocion, no volveremos a sugerirla.");
			this.haySugerencia = false;
		}
	}

	// Remueve los elementos de la lista que no pueden ser comprados
	public void filtrarSugerencias(ArrayList<? extends PuedeSerComprada> sugerenciasAfiltrar) {
		Iterator<? extends PuedeSerComprada> iterador = sugerenciasAfiltrar.iterator();
		PuedeSerComprada o;
		while (iterador.hasNext()) {
			o = iterador.next();
			if (!(getUsuario().puedeComprar(o) && !this.fueSugerida(o) && o.hayCupos()))
				iterador.remove();
		}
		Collections.sort(sugerenciasAfiltrar);
	}

	// Si el usuario rechazo o acepto una promocion devolvera true
	public boolean fueSugerida(PuedeSerComprada promocion) {
		return getUsuario().getSugerenciasAdquiridas().contains(promocion)
				|| getUsuario().getSugerenciasNoAdquiridas().contains(promocion);
	}

	public boolean hayPromosXsugerir() {
		return sugerenciasNoApt.size() > 0;
	}

	// Devuelven strings de las distintas listas
	public String promosAdquiridasToString() {
		return sugerenciasToString(getUsuario().getSugerenciasAdquiridas());
	}

	public String promosRechazadasToString() {
		return sugerenciasToString(getUsuario().getSugerenciasNoAdquiridas());
	}

	public String promosAptasToString() {
		return sugerenciasToString(sugerenciasApt);
	}

	public String promosNoAptasToString() {
		return sugerenciasToString(sugerenciasNoApt);
	}

	public String sugerenciasToString(ArrayList<? extends PuedeSerComprada> sugerencias) {
		String s = "";
		if (sugerencias.size() > 0) {
			filtrarSugerencias(sugerencias);
			for (PuedeSerComprada sugerencia : sugerencias)
				s += sugerencia.toString() + "\n";
		}
		return s;
	}

	public boolean hayPromosAdquiridas() {
		return getUsuario().getSugerenciasAdquiridas().size() > 0;
	}
}
