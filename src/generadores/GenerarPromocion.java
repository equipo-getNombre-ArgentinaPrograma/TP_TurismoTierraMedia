package generadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import objetosDeEntrada.*;

public class GenerarPromocion {
	private ArrayList<Usuario> usuarios;
	public ArrayList<Promocion> promos;

	private ArrayList<Promocion> promosApt;
	private ArrayList<Promocion> promosNoApt;

	private String nombreUsuario;
	private int numeroUsuario;
	private boolean hayPromoSugerida = false;
	Promocion promocionSugerida;

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

	// Getters
	public Usuario getUsuario() {
		return usuarios.get(this.numeroUsuario);
	}

	public ArrayList<Promocion> getPromosAdquiridas() {
		return getUsuario().getPromosAdquiridas();
	}

	public ArrayList<Promocion> getPromosNoAdquiridas() {
		return getUsuario().getPromosNoAdquiridas();
	}

	public Promocion getPromo() {
		return promocionSugerida;
	}

	// Guarda las promociones aptas para el usuario en una lista
	public void para(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.numeroUsuario = this.buscarIndiceDeUsuario();
	}

	public void sugerirPromocion() {
		this.buscarPromosAptas();
		if (promosApt.size() > 0) {
			promocionSugerida = promosApt.get(0);
			System.out.println(
					"--Estimado " + this.nombreUsuario + " le sugerimos la promocion " + promocionSugerida.toString());
			this.hayPromoSugerida = true;
		} else
			this.sugerirPromocionNoApta();
		System.out.println();
	}

	public void sugerirPromocionNoApta() {
		this.buscarPromosAptas();
		if (promosNoApt.size() > 0) {
			promocionSugerida = promosNoApt.get(0);
			System.out.println("--Estimado " + this.nombreUsuario
					+ " no encontramos mas promociones que cumplan con sus requisitos, le podemos sugerir "
					+ promocionSugerida.toString());
			this.hayPromoSugerida = true;
		} else {
			System.out.println("No hay mas promociones disponibles para sugerir");
			this.hayPromoSugerida = false;
		}
		System.out.println();
	}

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

	public void rechazarPromocion() {
		if (hayPromoSugerida) {
			System.out.println("Rechazaste la promocion, no volveremos a sugerirla.");
			getUsuario().rechazar(promocionSugerida);
			this.hayPromoSugerida = false;
		} else
			System.out.println("No hay ninguna promocion que rechazar.");
		System.out.println();
	}

	public int buscarIndiceDeUsuario() {
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getNombre().equals(this.nombreUsuario))
				return i;
		}
		return 0;
	}

	public void buscarPromosAptas() {
		filtrarPromos(promos);
		this.ordenarPromos();
	}

	public void ordenarPromos() {
		Collections.sort(promosApt);
		Collections.sort(promosNoApt);
	}

	// Aniade a la lista de promos aptas y no aptas los objetos del array
	public void filtrarPromos(ArrayList<Promocion> promos) {
		promosApt = new ArrayList<Promocion>();
		promosNoApt = new ArrayList<Promocion>();
		for (Promocion promo : promos)
			if (getUsuario().puedeComprar(promo) && !this.fueSugerida(promo) && promo.hayCupos())
				if (getUsuario().getTipoPreferido().equals(promo.getTipoDeAtraccion()))
					promosApt.add(promo);
				else
					promosNoApt.add(promo);
	}

	public boolean fueSugerida(Promocion promocion) {
		return getPromosAdquiridas().contains(promocion) || getPromosNoAdquiridas().contains(promocion);
	}

	// Muestran el estado de los arrays en consola
	public void mostrarPromosAdquiridas() {
		mostrarPromos(getPromosAdquiridas());
		System.out.println();
	}

	public void mostrarPromosRechazadas() {
		mostrarPromos(getPromosNoAdquiridas());
		System.out.println();
	}

	public void mostrarPromosAptas() {
		mostrarPromos(promosApt);
		System.out.println();
	}

	public void mostrarPromosNoAptas() {
		mostrarPromos(promosNoApt);
		System.out.println();
	}

	private void mostrarPromos(ArrayList<Promocion> promociones) {
		if (promociones.size() > 0) {
			for (Promocion promo : promociones)
				System.out.println(promo.toString());
		} else
			System.out.println("No hay ninguna promocion para mostrar.");
	}

}
