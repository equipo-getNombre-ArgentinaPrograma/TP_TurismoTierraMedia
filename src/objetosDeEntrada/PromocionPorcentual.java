package objetosDeEntrada;

public class PromocionPorcentual extends Promocion {
	private double descuento;

	public PromocionPorcentual(Atraccion[] atracciones, String tipo, String atraccion1, String atraccion2,
			double descuento) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.descuento = descuento;
	}

	@Override
	public double calcularPrecio() {
		// no se pq funciona, para calcular el precio la clase necesita
		// un array de atracciones y no se cuando lo obtiene
		this.precio = super.calcularPrecio() * (1 - (this.descuento / 100));
		return this.precio = (double) Math.round(this.precio * 10d) / 10d;
	}

	@Override
	public String toString() {
		return super.toString() + Double.toString(this.descuento) + "]";
	}

	public double getDescuento() {
		return descuento;
	}
}
