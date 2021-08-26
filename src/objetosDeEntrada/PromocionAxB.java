package objetosDeEntrada;

public class PromocionAxB extends Promocion {
	private String atraccionGratis;

	public PromocionAxB(Atraccion[] atracciones) {
		this.atracciones = atracciones;
		this.nombreArchivo = "promocionesAxB.txt";
		this.leerArchivo();
	}
	
	@Override
	public PromocionAxB siguientePromocion() {
		PromocionAxB tmp = new PromocionAxB(this.atracciones);
		// fijarse longitud de campos
		String[] campos = new String[4];
		campos = this.archivoPromos.get(indice);
		tmp.tipoDeAtraccion = campos[0];
		tmp.atraccion1 = campos[1];
		tmp.atraccion2 = campos[2];
		tmp.atraccionGratis = campos[3];

		this.aumentarIndice();

		return tmp;
	}
	
	public String getAtraccionGratis() {
		return atraccionGratis;
	}
	
	@Override
	public double calcularTiempoNecesario() {
		return super.calcularTiempoNecesario() + this.buscarEnLaAtraccion(atraccionGratis, "tiempoDeRealizacion");
	}
	
	@Override
	public String toString() {
		String atraccion;
		atraccion = ("[" + this.atraccion1 + ", "
						 + this.atraccion2 + ", "
						 + this.atraccionGratis + "]");
		return atraccion;
	}
}
