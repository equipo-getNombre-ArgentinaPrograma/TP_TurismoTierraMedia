package objetosDeEntrada;

public abstract class Promocion {
	protected Atraccion[] atracciones;
	protected String atraccion1;
	protected String atraccion2;
	protected double precio;
	protected String tipoDeAtraccion;
	protected String nombreArchivo;
	protected double tiempoNecesario;

	public Promocion(Atraccion[] atracciones, String tipo, String atraccion1, String atraccion2) {
		this.atracciones = atracciones;
		this.tipoDeAtraccion = tipo;
		this.atraccion1 = atraccion1;
		this.atraccion2 = atraccion2;
	}

	public double calcularPrecio() {
		this.precio = 0;
		this.precio += this.buscarEnLaAtraccion(atraccion1, "costoXvisita");
		this.precio += this.buscarEnLaAtraccion(atraccion2, "costoXvisita");
		return precio;
	}

	public double calcularTiempoNecesario() {
		this.tiempoNecesario = 0;
		this.tiempoNecesario += this.buscarEnLaAtraccion(atraccion1, "tiempoDeRealizacion");
		this.tiempoNecesario += this.buscarEnLaAtraccion(atraccion2, "tiempoDeRealizacion");
		return tiempoNecesario;
	}

	public double buscarEnLaAtraccion(String atraccion, String datoAbuscar) {
		if (datoAbuscar == "tiempoDeRealizacion") {
			for (int i = 0; i < this.atracciones.length; i++) {
				if (this.atracciones[i].getNombre().equals(atraccion))
					return this.atracciones[i].getTiempoDeRealizacion();
			}
		} else if (datoAbuscar == "costoXvisita") {
			for (int i = 0; i < this.atracciones.length; i++) {
				if (this.atracciones[i].getNombre().equals(atraccion))
					return this.atracciones[i].getCostoXvisita();
			}
		}
		return 0;
	}
	
	@Override
	public String toString() {
		String atraccion;
		atraccion = ("[" + this.atraccion1 + ", " + this.atraccion2 + ", ");
		return atraccion;
	}

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
}
