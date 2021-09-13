package objetosDeEntrada;

import java.util.ArrayList;

public abstract class Promocion implements Adquirible {
	protected ArrayList<Atraccion> atracciones;
	protected ArrayList<Atraccion> atraccionesIncluidas;
	protected double precio;
	protected String tipoDeAtraccion;
	protected double tiempoNecesario;

	public Promocion(ArrayList<Atraccion> atracciones, String tipo, String atraccion1, String atraccion2) {
		this.atracciones = atracciones;
		this.tipoDeAtraccion = tipo;
		this.atraccionesIncluidas.add(setAtraccion(atraccion1));
		this.atraccionesIncluidas.add(setAtraccion(atraccion2));
		;
	}

	// Getters
	public Atraccion getAtraccion1() {
		return atraccionesIncluidas.get(0);
	}

	public Atraccion getAtraccion2() {
		return atraccionesIncluidas.get(1);
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

	// Setter
	private Atraccion setAtraccion(String atraccion) {
		return atracciones.get(buscarIndiceAtraccion(atraccion));
	}

	// Suma los precios de las dos atracciones
	protected double calcularPrecio() {
		this.precio = 0;
		this.precio += getAtraccion1().getPrecio();
		this.precio += getAtraccion2().getPrecio();
		return precio;
	}

	// Suma los tiempos de las dos atracciones
	protected double calcularTiempoNecesario() {
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

	public boolean esPromocion() {
		return true;
	}

	@Override
	public String toString() {
		return "Promocion " + getTipoDeAtraccion() + ";Atracciones: " + getAtraccion1().getNombre() + ", "
				+ getAtraccion2().getNombre() + ";Duracion: " + getTiempoDeRealizacion() + " hora/s;Precio: " + getPrecio()
				+ " moneda/s";
	}

	public void imprimirEnPantalla() {
		System.out.println("Promocion " + getTipoDeAtraccion() + ".\nAtracciones: " + getAtraccion1().getNombre() + ", "
				+ getAtraccion2().getNombre() + ".\nDuracion: " + getTiempoDeRealizacion() + " hora/s.\nPrecio: "
				+ getPrecio() + " moneda/s.");
	}
}
