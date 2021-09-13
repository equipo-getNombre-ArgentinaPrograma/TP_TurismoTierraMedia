package objetosDeEntrada;

import java.util.ArrayList;

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
	protected double calcularPrecio() {
		this.precio = super.calcularPrecio() * (1 - (getDescuento() / 100));
		return this.precio = (double) Math.round(this.precio * 10d) / 10d;
	}

	@Override
	public String toString() {
		return super.toString() + ";Descuento: " + getDescuento() + "%";
	}

	@Override
	public void imprimirEnPantalla() {
		super.imprimirEnPantalla();
		System.out.println("Descuento: " + getDescuento() + "%.");
	}
}
