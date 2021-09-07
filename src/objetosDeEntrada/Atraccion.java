package objetosDeEntrada;

public class Atraccion implements PuedeSerComprada {
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
	public void usarCupos() {
		if (hayCupos())
			cuposXdia--;
	}

	public boolean hayCupos() {
		return cuposXdia > 0;
	}

	@Override
	public String toString() {
		return "\n*Atraccion " + getTipoDeAtraccion() + ":\nNombre: " + getNombre() + "\nPrecio: " + getPrecio()
				+ " monedas.\nDuracion: " + getTiempoDeRealizacion() + " horas.";

	}

	@Override
	public int compareTo(PuedeSerComprada otro) {
		int comparacionPorPrecio = Double.compare(otro.getPrecio(), this.getPrecio());
		if (comparacionPorPrecio != 0)
			return comparacionPorPrecio;
		return Double.compare(otro.getTiempoDeRealizacion(), this.getTiempoDeRealizacion());
	}
}
