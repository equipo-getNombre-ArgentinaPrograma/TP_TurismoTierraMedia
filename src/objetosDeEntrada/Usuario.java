package objetosDeEntrada;

import java.util.ArrayList;

public class Usuario {
	private ArrayList<Adquirible> sugerenciasAdquiridas = new ArrayList<Adquirible>();
	private ArrayList<Adquirible> sugerenciasNoAdquiridas = new ArrayList<Adquirible>();

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

	public ArrayList<Adquirible> getSugerenciasAdquiridas() {
		return sugerenciasAdquiridas;
	}

	public ArrayList<Adquirible> getSugerenciasNoAdquiridas() {
		return sugerenciasNoAdquiridas;
	}

	// Adquiere la promocion sumandola a la lista de promos adquiridas y resta
	// tiempo y presupuesto
	public boolean adquirir(Adquirible promocionSugerida) {
		if (puedeComprar(promocionSugerida)) {
			this.tiempoDisponible -= promocionSugerida.getTiempoDeRealizacion();
			this.presupuesto -= promocionSugerida.getPrecio();
			this.sugerenciasAdquiridas.add(promocionSugerida);
			return true;
		}
		return false;
	}

	// Chequea si una promocion entra en su presupuesto y tiempo disponible
	public boolean puedeComprar(Adquirible sugerencia) {
		return (sugerencia.getTiempoDeRealizacion() <= this.tiempoDisponible
				&& sugerencia.getPrecio() <= this.presupuesto 
				&& sugerencia.hayCupos());
	}

	@Override
	public String toString() {
		return "[" + getNombre() + ", " + getPresupuesto() + ", " + getTiempoDisponible() + ", " + getTipoPreferido()
				+ "]";
	}
}
