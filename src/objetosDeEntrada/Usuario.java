package objetosDeEntrada;

import java.util.ArrayList;

public class Usuario {
	private ArrayList<String[]> archivo;
	private ArrayList<Promocion> promosAdquiridas = new ArrayList<Promocion>();
	private ArrayList<Promocion> promosNoAdquiridas = new ArrayList<Promocion>();
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoPreferido;
	private int indice = 0;

	public Usuario(String nombre, double presupuesto, double tiempo, String tipo) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempo;
		this.tipoPreferido = tipo;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public String getTipoPreferido() {
		return tipoPreferido;
	}

	public int getIndice() {
		return this.indice;
	}

	public int getTamanio() {
		return this.archivo.size();
	}

	public ArrayList<Promocion> getPromosAdquiridas() {
		return promosAdquiridas;
	}

	public ArrayList<Promocion> getPromosNoAdquiridas() {
		return promosNoAdquiridas;
	}

	// Adquiere la promocion sumandola a la lista de promos adquiridas y resta
	// tiempo y presupuesto
	public void adquirir(Promocion promocionSugerida) {
		if (puedoComprar(promocionSugerida)) {
			this.tiempoDisponible -= promocionSugerida.getTiempoNecesario();
			this.presupuesto -= promocionSugerida.getPrecio();
			this.promosAdquiridas.add(promocionSugerida);
		}
	}

	// Rechaza la promocion sumandola a la lista de promos NO adquiridas para que no
	// vuelva a ser sugerida
	public void rechazar(Promocion promocionSugerida) {
		this.promosNoAdquiridas.add(promocionSugerida);
	}

	// Chequea si una promocion entra en su presupuesto y tiempo disponible
	public boolean puedoComprar(Promocion promocionSugerida) {
		return (promocionSugerida.getTiempoNecesario() <= this.tiempoDisponible
				&& promocionSugerida.getPrecio() <= this.presupuesto);
	}

	@Override
	public String toString() {
		String usuarios;
		usuarios = ("[" + this.nombre + ", " + Double.toString(this.presupuesto) + ", "
				+ Double.toString(this.tiempoDisponible) + ", " + this.tipoPreferido + "]");
		return usuarios;
	}
}
