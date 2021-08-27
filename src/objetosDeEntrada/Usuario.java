package objetosDeEntrada;

import java.util.ArrayList;

public class Usuario {
	private ArrayList<String[]> archivo;
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoPreferido;
	private int indice = 0;

	public Usuario(String nombre, double presupuesto, double tiempo, String tipo) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempo;
		this.tipoPreferido = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public String getTipoPreferido() {
		return tipoPreferido;
	}

	public int getIndice() {
		return this.indice;
	}

	public int getTamanio() {
		return this.archivo.size();
	}

	public void aumentarIndice() {
		if (this.indice + 1 == this.getTamanio())
			this.indice = 0;
		else
			this.indice++;
	}

	@Override
	public String toString() {
		String usuarios;
		usuarios = ("[" + this.nombre + ", " + Double.toString(this.presupuesto) + ", "
				+ Double.toString(this.tiempoDisponible) + ", " + this.tipoPreferido + "]");
		return usuarios;
	}
}
