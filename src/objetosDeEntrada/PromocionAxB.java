package objetosDeEntrada;

import java.util.ArrayList;

public class PromocionAxB extends Promocion {

	public PromocionAxB(ArrayList<Atraccion> atracciones, String tipo, String atraccion1, String atraccion2,
			String atraccion3) {
		super(atracciones, tipo, atraccion1, atraccion2);
		this.atraccionesIncluidas.add(setAtraccionGratis(atraccion3));
	}

	// Getter
	public Atraccion getAtraccionGratis() {
		return atraccionesIncluidas.get(2);
	}

	// Setter
	private Atraccion setAtraccionGratis(String atraccion) {
		return atracciones.get(buscarIndiceAtraccion(atraccion));
	}

	@Override
	public boolean usarCupo() {
		if (hayCupos()) {
			super.usarCupo();
			getAtraccionGratis().usarCupo();
			return true;
		}
		return false;
	}

	@Override
	public boolean hayCupos() {
		return super.hayCupos() && getAtraccionGratis().getHayCupos();
	}

	// Agrega al metodo heredado el tiempo de su atributo propio
	@Override
	protected double calcularTiempoNecesario() {
		return super.calcularTiempoNecesario() + getAtraccionGratis().getTiempoDeRealizacion();
	}

	// Agrega los campos propios al toString heredado
	@Override
	public String toString() {
		return super.toString() + ";Promocion de regalo: " + getAtraccionGratis().getNombre();
	}

	@Override
	public void imprimirEnPantalla() {
		super.imprimirEnPantalla();
		System.out.println("Promocion de regalo: " + getAtraccionGratis().getNombre());
	}
}
