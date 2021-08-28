package objetosDeEntrada;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(Atraccion[] atracciones, String tipo, String atraccion1, String atraccion2,
			double precio) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.precio = precio;
	}

	@Override
	public String toString() {
		return super.toString() + Double.toString(this.precio) + "]";
	}

	@Override
	public double getPrecio() {
		return precio;
	}
}
