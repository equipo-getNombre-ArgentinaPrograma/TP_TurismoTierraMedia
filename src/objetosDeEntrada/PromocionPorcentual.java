package objetosDeEntrada;

public class PromocionPorcentual extends Promocion {
	private double descuento;

	public PromocionPorcentual(Atraccion[] atracciones) {
		this.atracciones = atracciones;
		this.nombreArchivo = "promocionesPorcentuales.txt";
		this.leerArchivo();
	}

	@Override
	public double calcularPrecio() {
		// no se pq funciona, para calcular el precio la clase necesita
		// un array de atracciones y no se cuando lo obtiene
		this.precio = super.calcularPrecio() * (1 - (this.descuento/100)); 
		return this.precio = (double)Math.round(this.precio * 10d) / 10d;
	}

	@Override
	public PromocionPorcentual siguientePromocion() {
		PromocionPorcentual tmp = new PromocionPorcentual(this.atracciones);
		//fijarse longitud de campos
		String[] campos = new String[4];
		campos = this.archivoPromos.get(indice);
		tmp.tipoDeAtraccion = campos[0];
		tmp.atraccion1 = campos[1];
		tmp.atraccion2 = campos[2];
		tmp.descuento = Double.parseDouble(campos[3]);
		this.aumentarIndice();

		return tmp;
	}

	@Override
	public String toString() {
		String atraccion;
		atraccion = ("[" + this.atraccion1 + ", "
						 + this.atraccion2 + ", "
						 + Double.toString(this.descuento) + "]");
		return atraccion;
	}
	
	public double getDescuento() {
		return descuento;
	}
}
