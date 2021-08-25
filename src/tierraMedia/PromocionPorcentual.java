package tierraMedia;

public class PromocionPorcentual extends Promocion {
	private double descuento;

	public PromocionPorcentual() {
		this.nombreArchivo = "promocionesPorcentuales.txt";
		this.leerArchivo();
	}

	@Override
	public double getPrecio(Atraccion[] atracciones) {
		this.precio=0;
		for (int i = indice; i < atracciones.length; i++) {
			if (atracciones[i].getNombre().equals(this.atraccion1))
				this.precio+=atracciones[i].getCostoXvisita();
			else if (atracciones[i].getNombre().equals(this.atraccion2))
				this.precio+=atracciones[i].getCostoXvisita();
		}
		this.precio *= 1 - (this.descuento/100); 
		return this.precio = (double)Math.round(this.precio * 10d) / 10d;
	}

	@Override
	public Promocion siguientePromocion() {
		PromocionPorcentual tmp = new PromocionPorcentual();
		String[] campos = new String[5];
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
