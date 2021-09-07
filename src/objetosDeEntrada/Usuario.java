package objetosDeEntrada;

import java.util.ArrayList;

public class Usuario {
	private ArrayList<PuedeSerComprada> sugerenciasAdquiridas = new ArrayList<PuedeSerComprada>();
	private ArrayList<PuedeSerComprada> sugerenciasNoAdquiridas = new ArrayList<PuedeSerComprada>();

	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoPreferido;

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

	public ArrayList<PuedeSerComprada> getSugerenciasAdquiridas() {
		return sugerenciasAdquiridas;
	}

	public ArrayList<PuedeSerComprada> getSugerenciasNoAdquiridas() {
		return sugerenciasNoAdquiridas;
	}

	// Adquiere la promocion sumandola a la lista de promos adquiridas y resta
	// tiempo y presupuesto
	public boolean adquirir(PuedeSerComprada promocionSugerida) {
		if (puedeComprar(promocionSugerida)) {
			this.tiempoDisponible -= promocionSugerida.getTiempoDeRealizacion();
			this.presupuesto -= promocionSugerida.getPrecio();
			this.sugerenciasAdquiridas.add(promocionSugerida);
			return true;
		}
		return false;
	}

	// Rechaza la promocion sumandola a la lista de promos NO adquiridas para que no
	// vuelva a ser sugerida
	public boolean rechazar(PuedeSerComprada sugerencia) {
		this.sugerenciasNoAdquiridas.add(sugerencia);
		return true;
	}

	// Chequea si una promocion entra en su presupuesto y tiempo disponible
	public boolean puedeComprar(PuedeSerComprada sugerencia) {
		return (sugerencia.getTiempoDeRealizacion() <= this.tiempoDisponible
				&& sugerencia.getPrecio() <= this.presupuesto);
	}

	@Override
	public String toString() {
		return "[" + getNombre() + ", " + getPresupuesto() + ", " + getTiempoDisponible() + ", " + getTipoPreferido()
				+ "]";
	}
}
