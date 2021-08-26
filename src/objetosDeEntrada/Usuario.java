package objetosDeEntrada;

import java.io.IOException;
import java.util.ArrayList;

import lecturaYescritura.LectorDeArchivos;

public class Usuario {
	private ArrayList<String[]> archivo;
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoPreferido;
	private int indice = 0;

	public Usuario() {
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

	public void leerArchivo() {
		try {
			this.archivo = LectorDeArchivos.leerArchivo("usuarios.txt");
		} catch (IOException e) {
			System.err.println("No se pudo leer el archivo");
		}
	}

	public Usuario siguienteUsuario() {
		Usuario tmp = new Usuario();
		String[] campos = new String[5];
		campos = this.archivo.get(this.indice);
		tmp.nombre = campos[0];
		tmp.presupuesto = Double.parseDouble(campos[1]);
		tmp.tiempoDisponible = Double.parseDouble(campos[2]);
		tmp.tipoPreferido = campos[3];
		this.aumentarIndice();

		return tmp;
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
		usuarios = ("[" + this.nombre + ", "
						+ Double.toString(this.presupuesto) + ", "
						+ Double.toString(this.tiempoDisponible) + ", "
						+ this.tipoPreferido + "]");
		return usuarios;
	}
}
