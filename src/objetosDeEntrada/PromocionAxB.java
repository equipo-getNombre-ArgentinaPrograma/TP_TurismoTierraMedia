package objetosDeEntrada;

public class PromocionAxB extends Promocion {
	private String atraccionGratis;

	public PromocionAxB(Atraccion[] atracciones, String tipo, String atraccion1, String atraccion2, String atraccion3) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.atraccionGratis = atraccion3;
	}

	// Getters
	public String getAtraccionGratis() {
		return atraccionGratis;
	}

	// Aniade al metodo heredado el tiempo de su atributo propio
	@Override
	public double calcularTiempoNecesario() {
		return super.calcularTiempoNecesario() + this.buscarEnLaAtraccion(atraccionGratis, "tiempoDeRealizacion");
	}

	// Aniade al toString heredado mostrando los campos propios
	@Override
	public String toString() {
		return super.toString() + "por " + this.getPrecio() + " monedas, y ademas se lleva " + this.atraccionGratis
				+ " gratis.";
	}
}
