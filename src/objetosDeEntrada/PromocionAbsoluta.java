package objetosDeEntrada;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(ArrayList<Atraccion> atracciones, String tipo, String atraccion1, String atraccion2,
			double precio) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.precio = precio;
	}

	// Geters
	@Override
	public double getPrecio() {
		return precio;
	}

	// Aniade al toString heredado mostrando los campos propios
	@Override
	public String toString() {
		return super.toString() + "por " + this.getPrecio() + " monedas.";
	}

}
