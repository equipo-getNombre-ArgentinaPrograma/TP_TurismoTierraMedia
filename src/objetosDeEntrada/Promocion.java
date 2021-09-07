package objetosDeEntrada;

import java.util.ArrayList;

public abstract class Promocion implements PuedeSerComprada{
	protected ArrayList<Atraccion> atracciones;
	protected Atraccion atraccion1;
	protected Atraccion atraccion2;
	protected double precio;
	protected String tipoDeAtraccion;
	protected double tiempoNecesario;

	public Promocion(ArrayList<Atraccion> atracciones, String tipo, String atraccion1, String atraccion2) {
		this.atracciones = atracciones;
		this.tipoDeAtraccion = tipo;
		this.atraccion1 = atracciones.get(buscarIndiceAtraccion(atraccion1));
		this.atraccion2 = atracciones.get(buscarIndiceAtraccion(atraccion2));
	}

	// Getters
	public Atraccion getAtraccion1() {
		return atraccion1;
	}

	public Atraccion getAtraccion2() {
		return atraccion2;
	}

	public double getPrecio() {
		return calcularPrecio();
	}

	public String getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double getTiempoDeRealizacion() {
		return calcularTiempoNecesario();
	}

	// Suma los precios de las dos atracciones
	public double calcularPrecio() {
		this.precio = 0;
		this.precio += getAtraccion1().getPrecio();
		this.precio += getAtraccion2().getPrecio();
		return precio;
	}

	// Suma los tiempos de las dos atracciones
	public double calcularTiempoNecesario() {
		this.tiempoNecesario = 0;
		this.tiempoNecesario += getAtraccion1().getTiempoDeRealizacion();
		this.tiempoNecesario += getAtraccion2().getTiempoDeRealizacion();
		return tiempoNecesario;
	}

	public int buscarIndiceAtraccion(String atraccion) {
		for (int i = 0; i < atracciones.size(); i++) {
			if (atracciones.get(i).getNombre().equals(atraccion))
				return i;
		}
		return -1;
	}

	public void usarCupos() {
		if (hayCupos()) {
			getAtraccion1().usarCupos();
			getAtraccion2().usarCupos();
		}
	}

	public boolean hayCupos() {
		return getAtraccion1().getHayCupos() && getAtraccion2().getHayCupos();
	}

	@Override
	public String toString() {
		return "\n*Promo " + getTipoDeAtraccion() + ":\nAtracciones incluidas: " + atraccion1.getNombre() + " y " + atraccion2.getNombre() + ".\nPrecio: "
				+ getPrecio() + " monedas.\nDuracion: " + getTiempoDeRealizacion() + " horas.";
	}

	// Compara por precio y por tiempo
	@Override
	public int compareTo(PuedeSerComprada otro) {
		int comparacionPorPrecio = Double.compare(otro.getPrecio(), this.getPrecio());
		if (comparacionPorPrecio != 0)
			return comparacionPorPrecio;
		return Double.compare(otro.getTiempoDeRealizacion(), this.getTiempoDeRealizacion());
	}
}
