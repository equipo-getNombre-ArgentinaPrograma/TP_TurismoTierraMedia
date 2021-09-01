package objetosDeEntrada;

import java.util.ArrayList;

public abstract class Promocion implements Comparable<Promocion> {
	protected ArrayList<Atraccion> atracciones;
	protected String atraccion1;
	protected String atraccion2;
	protected double precio;
	protected String tipoDeAtraccion;
	protected double tiempoNecesario;

	public Promocion(ArrayList<Atraccion> atracciones, String tipo, String atraccion1, String atraccion2) {
		this.atracciones = atracciones;
		this.tipoDeAtraccion = tipo;
		this.atraccion1 = atraccion1;
		this.atraccion2 = atraccion2;
	}

	// Getters
	public Atraccion getAtraccion1() {
		return atracciones.get(indiceAtraccion(atraccion1));
	}

	public Atraccion getAtraccion2() {
		return atracciones.get(indiceAtraccion(atraccion2));
	}

	public double getPrecio() {
		return calcularPrecio();
	}

	public String getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double getTiempoNecesario() {
		return calcularTiempoNecesario();
	}

	// Suma los precios de las dos atracciones
	public double calcularPrecio() {
		this.precio = 0;
		this.precio += getAtraccion1().getCostoXvisita();
		this.precio += getAtraccion2().getCostoXvisita();
		return precio;
	}

	// Suma los tiempos de las dos atracciones
	public double calcularTiempoNecesario() {
		this.tiempoNecesario = 0;
		this.tiempoNecesario += getAtraccion1().getTiempoDeRealizacion();
		this.tiempoNecesario += getAtraccion2().getTiempoDeRealizacion();
		return tiempoNecesario;
	}

	public int indiceAtraccion(String atraccion) {
		for (int i = 0; i < atracciones.size(); i++) {
			if (atracciones.get(i).getNombre().equals(atraccion))
				return i;
		}
		return -1;
	}

	public void usarCupos() {
		if (hayCupos()) {
			getAtraccion1().usarCupo();
			getAtraccion2().usarCupo();
		}
	}

	public boolean hayCupos() {
		return getAtraccion1().getHayCupos() && getAtraccion2().getHayCupos();
	}

	@Override
	public String toString() {
		String atraccion;
		atraccion = ("*Promo " + this.tipoDeAtraccion + ": " + this.atraccion1 + " y " + this.atraccion2 + " de "
				+ this.getTiempoNecesario() + " horas ");
		return atraccion;
	}

	// Compara por precio y por tiempo
	@Override
	public int compareTo(Promocion otro) {
		int comparacionPorPrecio = Double.compare(otro.getPrecio(), this.precio);
		if (comparacionPorPrecio != 0)
			return comparacionPorPrecio;
		return Double.compare(this.tiempoNecesario, otro.getTiempoNecesario());
	}
}
