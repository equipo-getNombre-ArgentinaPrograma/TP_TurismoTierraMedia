package objetosDeEntrada;

import java.io.IOException;
import java.util.ArrayList;

import lecturaYescritura.LectorDeArchivos;

public class Atraccion {
	private ArrayList<String[]> archivo;
	private String nombre;
	private double costoXvisita;
	private double tiempoDeRealizacion;
	private int cuposXdia;
	private String tipoDeAtraccion;
	private int indice = 0;

	public Atraccion() {
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
	// usarCupo() devuelve T si el cupo es usado y F si no hay mas cupos para usarse, ademas resta un cupo a la atraccion
	public boolean usarCupo() {
		boolean hayCupos = true;
		if (this.cuposXdia == 0) 
			hayCupos = false;
		else 
			this.cuposXdia--;
		return hayCupos;
	}

	public void leerArchivo() {
		try {
			this.archivo = LectorDeArchivos.leer("atracciones.txt");
		} catch (IOException e) {
			System.err.println("No se pudo leer el archivo");
		}
	}

	public Atraccion siguienteAtraccion() {
		Atraccion tmp = new Atraccion();
		String[] campos = new String[5];
		campos = this.archivo.get(indice);
		tmp.nombre = campos[0];
		tmp.costoXvisita = Double.parseDouble(campos[1]);
		tmp.tiempoDeRealizacion = Double.parseDouble(campos[2]);
		tmp.cuposXdia = Integer.parseInt(campos[3]);
		tmp.tipoDeAtraccion = campos[4];
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
		String atraccion;
		atraccion = ("[" + this.nombre + ", "
						 + Double.toString(this.costoXvisita) + ", "
						 + Double.toString(this.tiempoDeRealizacion) + ", "
						 + Integer.toString(this.cuposXdia) + ", "
						 + this.tipoDeAtraccion + "]");
		return atraccion;
	}
}
