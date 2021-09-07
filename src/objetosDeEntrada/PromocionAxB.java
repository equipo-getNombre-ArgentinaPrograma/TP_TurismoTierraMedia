package objetosDeEntrada;

import java.util.ArrayList;

public class PromocionAxB extends Promocion {
	private String atraccionGratis;

	public PromocionAxB(ArrayList<Atraccion> atracciones, String tipo, String atraccion1, String atraccion2,
			String atraccion3) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.atraccionGratis = atraccion3;
	}

	// Getters
	public Atraccion getAtraccionGratis() {
		return atracciones.get(buscarIndiceAtraccion(atraccionGratis));
	}

	@Override
	public void usarCupos() {
		if (hayCupos()) {
			super.usarCupos();
			getAtraccionGratis().usarCupo();
		}
	}

	@Override
	public boolean hayCupos() {
		return super.hayCupos() && getAtraccionGratis().getHayCupos();
	}

	// Aniade al metodo heredado el tiempo de su atributo propio
	@Override
	public double calcularTiempoNecesario() {
		return super.calcularTiempoNecesario() + atracciones.get(buscarIndiceAtraccion(atraccion1)).getTiempoDeRealizacion();
	}

	// Aniade al toString heredado mostrando los campos propios
	@Override
	public String toString() {
		return super.toString() + "por " + this.getPrecio() + " monedas, y ademas se lleva " + this.atraccionGratis
				+ " gratis.";
	}
}
