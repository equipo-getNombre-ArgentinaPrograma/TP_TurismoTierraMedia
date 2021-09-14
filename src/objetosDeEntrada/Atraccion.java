package objetosDeEntrada;

public class Atraccion implements Adquirible {
	private String nombre;
	private double precio;
	private double tiempoDeRealizacion;
	private int cuposXdia;
	private String tipoDeAtraccion;

	public Atraccion(String nombre, double costo, double tiempo, int cupos, String tipo) {
		this.nombre = nombre;
		this.precio = costo;
		this.tiempoDeRealizacion = tiempo;
		this.cuposXdia = cupos;
		this.tipoDeAtraccion = tipo;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public double getTiempoDeRealizacion() {
		return tiempoDeRealizacion;
	}

	public int getCuposXdia() {
		return cuposXdia;
	}

	public String getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public boolean getHayCupos() {
		return hayCupos();
	}

	// UsarCupo() devuelve True si el cupo es usado y False si no hay mas cupos para
	// usarse, ademas resta un cupo a la atraccion
	public boolean usarCupo() {
		if (hayCupos()) {
			cuposXdia--;
			return true;
		}
		return false;
	}

	public boolean hayCupos() {
		return cuposXdia > 0;
	}

	public boolean esPromocion() {
		return false;
	}

	@Override
	public String toString() {
		return "Atraccion " + getTipoDeAtraccion() + ";Nombre: " + getNombre() + ";Duracion: "
				+ getTiempoDeRealizacion() + " hora/s;Precio: " + getPrecio() + " moneda/s";
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Atraccion) {
			String otroNombre = ((Atraccion) object).getNombre();
			if (this.getNombre().equals(otroNombre))
				return true;
		}
		return false;
	}

	public void imprimirEnPantalla() {
		System.out.println("Atraccion " + getTipoDeAtraccion() + ".\nNombre: " + getNombre() + ".\nDuracion: "
				+ getTiempoDeRealizacion() + " hora/s.\nPrecio: " + getPrecio() + " moneda/s.");
	}
}
