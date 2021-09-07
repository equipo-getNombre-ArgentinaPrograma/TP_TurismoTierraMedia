package objetosDeEntrada;

import java.util.ArrayList;

import generadores.GeneradorListas;

public class PromocionPorcentual extends Promocion {
	private double descuento;

	public PromocionPorcentual(ArrayList<Atraccion> atracciones, String tipo, String atraccion1, String atraccion2,
			double descuento) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.descuento = descuento;
	}

	// Getters
	public double getDescuento() {
		return descuento;
	}

	@Override
	public double calcularPrecio() {
		// no se pq funciona, para calcular el precio la clase necesita
		// un array de atracciones y no se cuando lo obtiene
		this.precio = super.calcularPrecio() * (1 - (getDescuento() / 100));
		return this.precio = (double) Math.round(this.precio * 10d) / 10d;
	}

	@Override
	public String toString() {
		return super.toString() + " Gracias al descuento del " + getDescuento() + "%.";
	}
}
