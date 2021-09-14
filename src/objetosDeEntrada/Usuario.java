package objetosDeEntrada;

import java.util.ArrayList;

public class Usuario {
	private ArrayList<Adquirible> sugerenciasAdquiridas = new ArrayList<Adquirible>();
	private ArrayList<Atraccion> atraccionesAdquiridas = new ArrayList<Atraccion>();

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

	private ArrayList<Atraccion> getAtraccionesAdquiridas() {
		return atraccionesAdquiridas;
	}

	// Adquiere la promocion sumandola a la lista de promos adquiridas y resta
	// tiempo y presupuesto
	public boolean adquirir(Adquirible sugerencia) {
		if (puedeComprar(sugerencia)) {
			this.tiempoDisponible -= sugerencia.getTiempoDeRealizacion();
			this.presupuesto -= sugerencia.getPrecio();
			getSugerenciasAdquiridas().add(sugerencia);
			sugerencia.usarCupo();
			if (sugerencia.esPromocion()) {
				for (Atraccion ad : ((Promocion) sugerencia).getAtraccionesIncluidas())
					getAtraccionesAdquiridas().add(ad);
			} else
				getAtraccionesAdquiridas().add((Atraccion) sugerencia);
			return true;
		}
		return false;
	}

	// Chequea si una promocion entra en su presupuesto y tiempo disponible
	public boolean puedeComprar(Adquirible sugerencia) {
		boolean comprar = true;
		if (sugerencia.esPromocion()) {
			for (Atraccion ad : ((Promocion) sugerencia).getAtraccionesIncluidas())
				if (getAtraccionesAdquiridas().contains(ad))
					comprar = false;
		} else if (getAtraccionesAdquiridas().contains(sugerencia))
			comprar = false;

		if (!(sugerencia.getTiempoDeRealizacion() <= this.tiempoDisponible && sugerencia.getPrecio() <= this.presupuesto
				&& sugerencia.hayCupos()))
			comprar = false;
		return comprar;
	}

	@Override
	public String toString() {
		return "[" + getNombre() + ", " + getPresupuesto() + ", " + getTiempoDisponible() + ", " + getTipoPreferido()
				+ "]";
	}
}
