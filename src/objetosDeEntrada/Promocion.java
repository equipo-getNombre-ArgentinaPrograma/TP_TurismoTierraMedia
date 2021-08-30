package objetosDeEntrada;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class Promocion implements Comparable<Promocion> {
	protected Atraccion[] atracciones;
	protected String atraccion1;
	protected String atraccion2;
	protected double precio;
	protected String tipoDeAtraccion;
	protected String nombreArchivo;
	protected double tiempoNecesario;

	NumberFormat nf = new DecimalFormat("##.###");

	public Promocion(Atraccion[] atracciones, String tipo, String atraccion1, String atraccion2) {
		this.atracciones = atracciones;
		this.tipoDeAtraccion = tipo;
		this.atraccion1 = atraccion1;
		this.atraccion2 = atraccion2;
	}

	// Getters
	public String getAtraccion1() {
		return atraccion1;
	}

	public String getAtraccion2() {
		return atraccion2;
	}

	public double getPrecio() {
		return calcularPrecio();
	}

	public String getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double getTiempoNecesario() {
		return calcularTiempoNecesario();
	}

	// Suma los precios de las dos atracciones
	public double calcularPrecio() {
		this.precio = 0;
		this.precio += this.buscarEnLaAtraccion(atraccion1, "costoXvisita");
		this.precio += this.buscarEnLaAtraccion(atraccion2, "costoXvisita");
		return precio;
	}

	// Suma los tiempos de las dos atracciones
	public double calcularTiempoNecesario() {
		this.tiempoNecesario = 0;
		this.tiempoNecesario += this.buscarEnLaAtraccion(atraccion1, "tiempoDeRealizacion");
		this.tiempoNecesario += this.buscarEnLaAtraccion(atraccion2, "tiempoDeRealizacion");
		return tiempoNecesario;
	}

	// Devuelve el dato a buscar de la atraccion deseada
	public double buscarEnLaAtraccion(String atraccion, String datoAbuscar) {
		for (int i = 0; i < this.atracciones.length; i++) {
			if (this.atracciones[i].getNombre().equals(atraccion))
				if (datoAbuscar == "tiempoDeRealizacion")
					return this.atracciones[i].getTiempoDeRealizacion();
				else if (datoAbuscar == "costoXvisita")
					return this.atracciones[i].getCostoXvisita();
		}
		return -1;
	}

	@Override
	public String toString() {
		String atraccion;
		atraccion = ("*Promo " + this.tipoDeAtraccion + ": " + this.atraccion1 + " y " + this.atraccion2 + " de "
				+ this.getTiempoNecesario() + " horas ");
		return atraccion;
	}

	// Compara por precio y por tiempo
	@Override
	public int compareTo(Promocion otro) {
		int comparacionPorPrecio = Double.compare(otro.getPrecio(), this.precio);
		if (comparacionPorPrecio != 0)
			return comparacionPorPrecio;
		return Double.compare(this.tiempoNecesario, otro.getTiempoNecesario());
	}
}
