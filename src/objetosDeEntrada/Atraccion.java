package objetosDeEntrada;

import java.util.ArrayList;

public class Atraccion {
	private ArrayList<String[]> archivo;
	private String nombre;
	private double costoXvisita;
	private double tiempoDeRealizacion;
	private int cuposXdia;
	private String tipoDeAtraccion;
	private int indice = 0;

	public Atraccion(String nombre, double costo, double tiempo, int cupos, String tipo) {
		this.nombre = nombre;
		this.costoXvisita = costo;
		this.tiempoDeRealizacion = tiempo;
		this.cuposXdia = cupos;
		this.tipoDeAtraccion = tipo;
	}

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

	public int getIndice() {
		return this.indice;
	}

	public int getTamanio() {
		return this.archivo.size();
	}

	// usarCupo() devuelve T si el cupo es usado y F si no hay mas cupos para
	// usarse, ademas resta un cupo a la atraccion
	public boolean usarCupo() {
		boolean hayCupos = true;
		if (this.cuposXdia == 0)
			hayCupos = false;
		else
			this.cuposXdia--;
		return hayCupos;
	}

	public void aumentarIndice() {
		if (this.indice + 1 == this.getTamanio())
			this.indice = 0;
		else
			this.indice++;
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
