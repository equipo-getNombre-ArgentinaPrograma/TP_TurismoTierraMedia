package objetosDeEntrada;

public class PromocionAxB extends Promocion {
	private String atraccionGratis;

	public PromocionAxB(Atraccion[] atracciones, String tipo, String atraccion1, String atraccion2, String atraccion3) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.atraccionGratis = atraccion3;
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
		return super.toString() + this.atraccionGratis + "]";
	}
}
