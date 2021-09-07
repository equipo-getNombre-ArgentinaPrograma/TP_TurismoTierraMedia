package generadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import objetosDeEntrada.*;

public class GenerarPromocion {
	private ArrayList<Usuario> usuarios;
	private ArrayList<Promocion> promos;

	private ArrayList<Promocion> promosApt;
	private ArrayList<Promocion> promosNoApt;

	private int numeroUsuario;
	private boolean hayPromoSugerida = false;
	private Promocion promocionSugerida;

	// Genera las listas con los datos leidos del archivo
	public GenerarPromocion() {
		this.usuarios = GeneradorListas.deUsuarios();
		this.promos = GeneradorListas.dePromos();
	}

	// Constructor con parametro
	public GenerarPromocion(String nombreUsuario) throws IOException {
		this();
		para(nombreUsuario);
	}

	// Devuelve el objeto usuario al cual esta ligado el sistema
	public Usuario getUsuario() {
		return usuarios.get(this.numeroUsuario);
	}

	// WIP
	public String getItinerario() {
		return "";
	}

	// Guarda las promociones aptas para el usuario en una lista
	public boolean para(String nombreUsuario) {
		boolean validUser = false;
		this.numeroUsuario = this.buscarIndiceDeUsuario(nombreUsuario);
		if (numeroUsuario != -1) {
			this.inicializarListas();
			validUser = true;
		}
		return validUser;
	}

	// Inicializa las listas y las ordena por tipo preferido en aptas y no aptas
	public void inicializarListas() {
		promosApt = new ArrayList<Promocion>();
		promosNoApt = new ArrayList<Promocion>();
		for (Promocion promo : promos)
			if (getUsuario().getTipoPreferido().equals(promo.getTipoDeAtraccion()))
				promosApt.add(promo);
			else
				promosNoApt.add(promo);
	}

	// Sugiere la mejor promocion ligada a sus preferencias
	public void sugerirPromocion() {
		this.filtrarPromos(promosApt);
		if (promosApt.size() > 0) {
			promocionSugerida = promosApt.get(0);
			System.out.println("--Estimado " + getUsuario().getNombre() + " le sugerimos la promocion "
					+ promocionSugerida.toString());
			this.hayPromoSugerida = true;
		} else
			this.sugerirPromocionNoApta();
		System.out.println();
	}

	// Sugiere la mejor promocion la cual no esta ligada a sus preferencias
	public void sugerirPromocionNoApta() {
		this.filtrarPromos(promosNoApt);
		if (promosNoApt.size() > 0) {
			promocionSugerida = promosNoApt.get(0);
			System.out.println("--Estimado " + getUsuario().getNombre()
					+ " no encontramos mas promociones que cumplan con sus requisitos, le podemos sugerir "
					+ promocionSugerida.toString());
			this.hayPromoSugerida = true;
		} else {
			System.out.println("No hay mas promociones disponibles para sugerir");
			this.hayPromoSugerida = false;
		}
		System.out.println();
	}

	// Se acepta la promo sugerida y se agrega a la lista ligada al usuario
	public void aceptarPromocion() {
		if (hayPromoSugerida) {
			System.out.println("Adquiriste la promocion, la agregaremos a tu itinerario.");
			getUsuario().adquirir(promocionSugerida);
			promocionSugerida.usarCupos();
			this.hayPromoSugerida = false;
		} else
			System.out.println("La promocion no pudo ser adquirida o todavia no fue sugerida.");
		System.out.println();
	}

	// Se rechaza la promo sugerida y se agrega a la lista ligada al usuario
	public void rechazarPromocion() {
		if (hayPromoSugerida) {
			System.out.println("Rechazaste la promocion, no volveremos a sugerirla.");
			getUsuario().rechazar(promocionSugerida);
			this.hayPromoSugerida = false;
		} else
			System.out.println("No hay ninguna promocion que rechazar.");
		System.out.println();
	}

	// Devuelve la posicion del usuario en la lista de usuarios
	public int buscarIndiceDeUsuario(String nombreUsuario) {
		for (int i = 0; i < this.usuarios.size(); i++)
			if (this.usuarios.get(i).getNombre().toLowerCase().equals(nombreUsuario.toLowerCase()))
				return i;
		return -1;
	}

	// Remueve los elementos de la lista que no pueden ser comprados
	public void filtrarPromos(ArrayList<Promocion> promosAfiltrar) {
		Iterator<Promocion> iteradorPromo = promosAfiltrar.iterator();
		Promocion promo;
		while (iteradorPromo.hasNext()) {
			promo = iteradorPromo.next();
			if (!(getUsuario().puedeComprar(promo) && !this.fueSugerida(promo) && promo.hayCupos()))
				iteradorPromo.remove();
		}
		Collections.sort(promosAfiltrar);
	}

	// Si el usuario rechazo o acepto una promocion devolvera true
	public boolean fueSugerida(Promocion promocion) {
		return getUsuario().getPromosAdquiridas().contains(promocion)
				|| getUsuario().getPromosNoAdquiridas().contains(promocion);
	}

	public boolean hayPromosXsugerir() {
		return promosNoApt.size() > 0;
	}

	// Devuelven strings de las distintas listas
	public String promosAdquiridasToString() {
		return promosToString(getUsuario().getPromosAdquiridas());
	}

	public String promosRechazadasToString() {
		return promosToString(getUsuario().getPromosNoAdquiridas());
	}

	public String promosAptasToString() {
		return promosToString(promosApt);
	}

	public String promosNoAptasToString() {
		return promosToString(promosNoApt);
	}

	public String promosToString(ArrayList<Promocion> promociones) {
		String promos = "";
		if (promociones.size() > 0) {
			filtrarPromos(promociones);
			for (Promocion promo : promociones)
				promos += promo.toString() + "\n";
		}
		return promos;
	}

	public boolean hayPromosAdquiridas() {
		return getUsuario().getPromosAdquiridas().size() > 0;
	}
}
