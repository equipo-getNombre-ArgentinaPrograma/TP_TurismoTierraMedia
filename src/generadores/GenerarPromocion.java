package generadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import objetosDeEntrada.*;

public class GenerarPromocion {
	ArrayList<Promocion> promosApt;
	ArrayList<Promocion> promosNoApt;
	ArrayList<Promocion> promosAdquiridas = new ArrayList<Promocion>();
	ArrayList<Promocion> promosNoAdquiridas = new ArrayList<Promocion>();
	String nombreUsuario;
	int numeroUsuario;
	int indice = 0;
	int cantidadDePromos;
	int usuarioElige;
	boolean hayPromoSugerida = true;
	Promocion promocionSugerida;

	Atraccion[] atracciones;
	Usuario[] usuarios;
	Promocion[] promosPor;
	Promocion[] promosAbs;
	Promocion[] promosAxB;
	Promocion[] promosAptas;

	public GenerarPromocion() throws IOException {
		this.atracciones = GenerarArray.deAtracciones();
		this.usuarios = GenerarArray.deUsuarios();
		this.promosPor = GenerarArray.dePromocionesPorcentuales(atracciones);
		this.promosAbs = GenerarArray.dePromocionesAbsolutas(atracciones);
		this.promosAxB = GenerarArray.dePromocionesAxB(atracciones);
		this.usuarioElige = -1;
	}

	public void para(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.numeroUsuario = this.buscarIndice();

		this.buscarPromosAptas(); // busco las promos que cumplen con el criterio del usuario
		this.cantidadDePromos = this.promosApt.size();
		this.promosAptas = new Promocion[this.cantidadDePromos]; // inicializo el array de promos a generar
		// System.out.println();
		for (int i = 0; i < this.cantidadDePromos; i++) { // guardo el arrayList de promociones que son aptas en el
															// array
			this.promosAptas[i] = this.promosApt.get(i);
			// System.out.println(this.promosAptas[i].toString());
		}
	}

	public void sugerirPromocion() {
		// System.out.println("sugerir");
		this.buscarPromosAptas();
		if (promosApt.size() >= 1) {
			promocionSugerida = promosApt.get(0);
			if (this.usuarioElige == -1) {
				System.out.println("Estimado " + this.nombreUsuario + " le sugerimos la promocion "
						+ promocionSugerida.toString());
			} else {
				if (usuarioElige == 1) {
					this.promosAdquiridas.add(promocionSugerida);
					usuarioElige = -1;
					// System.out.println("acepto");
				}
				if (usuarioElige == 0) {
					this.promosNoAdquiridas.add(promocionSugerida);
					usuarioElige = -1;
					// System.out.println("rechazo");
				}
			}
		} else
			this.sugerirPromocionNoApta();
	}

	public void aceptarPromocion() {
		if (hayPromoSugerida) {
			this.usuarioElige = 1;
			System.out.println("Aceptaste la promocion, esta se agregara a tu itinerario");
			sugerirPromocion();
		} else
			System.out.println("No hay ninguna promocion que aceptar");
	}

	public void rechazarPromocion() {
		if (hayPromoSugerida) {
			this.usuarioElige = 0;
			System.out.println("Rechazaste la promocion, no volveremos a sugerirla");
			sugerirPromocion();
		} else
			System.out.println("No hay ninguna promocion que rechazar");
	}

	public void sugerirPromocionNoApta() {
		// System.out.println("sugerir");
		this.buscarPromosAptas();
		if (promosNoApt.size() >= 1) {
			promocionSugerida = promosNoApt.get(0);
			if (this.usuarioElige == -1) {
				System.out.println("Estimado " + this.nombreUsuario
						+ " no encontramos mas promociones que cumplan con sus requisitos, le podemos sugerir "
						+ promocionSugerida.toString());
			} else {
				if (usuarioElige == 1) {
					this.promosAdquiridas.add(promocionSugerida);
					usuarioElige = -1;
					// System.out.println("acepto");
				}
				if (usuarioElige == 0) {
					this.promosNoAdquiridas.add(promocionSugerida);
					usuarioElige = -1;
					// System.out.println("rechazo");
				}
			}
		} else {
			System.out.println("No hay mas promociones disponibles para sugerir");
			this.hayPromoSugerida = false;
		}
	}

	public int buscarIndice() {
		for (int i = 0; i < this.usuarios.length; i++) {
			if (this.usuarios[i].getNombre().equals(this.nombreUsuario))
				return i;
		}
		return 0;
	}

	public void buscarPromosAptas() {
		promosApt = new ArrayList<Promocion>();
		promosNoApt = new ArrayList<Promocion>();
		addToList(promosPor);
		addToList(promosAbs);
		addToList(promosAxB);

		this.ordenarPromos();
	}

	public void ordenarPromos() {
		try {
			Collections.sort(this.promosApt);
		} catch (NullPointerException npe) {
			System.err.println("La lista todavia no fue generada o hubo un error en su generaion");
		}
	}

	public void addToList(Promocion[] promos) {
		for (int i = 0; i < promos.length; i++) {
			if (usuarios[this.numeroUsuario].getPresupuesto() >= promos[i].getPrecio()
					&& usuarios[this.numeroUsuario].getTiempoDisponible() >= promos[i].getTiempoNecesario()
					&& !this.yaFueSugerida(promos[i])) {
				promosApt.add(promos[i]);
			} else if (!this.yaFueSugerida(promos[i]))
				promosNoApt.add(promos[i]);
		}
	}

	private boolean yaFueSugerida(Promocion promocion) {
		boolean fueSugerida = false;
		for (int i = 0; i < this.promosAdquiridas.size(); i++) {
			if (this.promosAdquiridas.get(i).equals(promocion))
				fueSugerida = true;
		}
		for (int i = 0; i < this.promosNoAdquiridas.size(); i++) {
			if (this.promosNoAdquiridas.get(i).equals(promocion))
				fueSugerida = true;
		}
		return fueSugerida;
	}
	
	public void mostrarPromocionesAdquiridas() {
		for(int i=0;i<promosAdquiridas.size();i++)
			System.out.println(promosAdquiridas.get(i).toString());
	}
	
	public void mostrarPromocionesNoAdquiridas() {
		for(int i=0;i<promosNoAdquiridas.size();i++)
			System.out.println(promosNoAdquiridas.get(i).toString());
	}
}
