package tierraMedia;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Promocion {
	protected ArrayList<String[]> archivoPromos;
	protected String atraccion1;
	protected String atraccion2;
	protected double precio;
	protected String tipoDeAtraccion;
	protected int indice = 0;
	protected String nombreArchivo;
	
	public abstract Promocion siguientePromocion();
	
	public abstract double getPrecio(Atraccion[] atracciones);
	
	public void aumentarIndice() {
		if (this.indice + 1 == this.getTamanio())
			this.indice = 0;
		else
			this.indice++;
	}
	
	public int getTamanio() {
		return this.archivoPromos.size();
	}
	
	public void leerArchivo() {
		try {
			this.archivoPromos = LectorDeArchivos.leerArchivo(this.nombreArchivo);
		} catch (IOException e) {
			System.err.println("No se pudo leer el archivo");
		}
	}
	
	public int getIndice() {
		return indice;
	}
	
	public String getAtraccion1() {
		return atraccion1;
	}
	
	public String getAtraccion2() {
		return atraccion2;
	}
	
	public double getPrecio() {
		return precio;
	}
}
