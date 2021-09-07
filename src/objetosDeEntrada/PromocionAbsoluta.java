package objetosDeEntrada;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(ArrayList<Atraccion> atracciones, String tipo, String atraccion1, String atraccion2,
			double precio) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.precio = precio;
	}

	// Getters
	@Override
	public double getPrecio() {
		return precio;
	}

}
