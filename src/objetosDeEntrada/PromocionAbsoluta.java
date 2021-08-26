package objetosDeEntrada;

public class PromocionAbsoluta extends Promocion{

	public PromocionAbsoluta(Atraccion[] atracciones) {
		this.atracciones = atracciones;
		this.nombreArchivo = "promocionesAbsolutas.txt";
		this.leerArchivo();
	}
	
	@Override
	public PromocionAbsoluta siguientePromocion() {
		PromocionAbsoluta tmp = new PromocionAbsoluta(this.atracciones);
		String[] campos = new String[5];
		campos = this.archivoPromos.get(indice);
		tmp.tipoDeAtraccion = campos[0];
		tmp.atraccion1 = campos[1];
		tmp.atraccion2 = campos[2];
		tmp.precio = Double.parseDouble(campos[3]);

		this.aumentarIndice();

		return tmp;
	}
	
	@Override
	public String toString() {
		String atraccion;
		atraccion = ("[" + this.atraccion1 + ", "
						 + this.atraccion2 + ", "
						 + Double.toString(this.precio) + "]");
		return atraccion;
	}
	
	@Override
	public double getPrecio() {
		return precio;
	}
}
