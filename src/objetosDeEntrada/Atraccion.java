package objetosDeEntrada;

public class Atraccion {
	private String nombre;
	private double costoXvisita;
	private double tiempoDeRealizacion;
	private int cuposXdia;
	private String tipoDeAtraccion;

	public Atraccion(String nombre, double costo, double tiempo, int cupos, String tipo) {
		this.nombre = nombre;
		this.costoXvisita = costo;
		this.tiempoDeRealizacion = tiempo;
		this.cuposXdia = cupos;
		this.tipoDeAtraccion = tipo;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}

	public double getCostoXvisita() {
		return costoXvisita;
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
	public void usarCupo() {
		if (hayCupos())
			cuposXdia--;
	}
	
	public boolean hayCupos() {
		return cuposXdia > 0;
	}

	@Override
	public String toString() {
		String atraccion;
		atraccion = ("[" + this.nombre + ", " + Double.toString(this.costoXvisita) + ", "
				+ Double.toString(this.tiempoDeRealizacion) + ", " + Integer.toString(this.cuposXdia) + ", "
				+ this.tipoDeAtraccion + "]");
		return atraccion;
	}
}
