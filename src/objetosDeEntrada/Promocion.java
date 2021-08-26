package objetosDeEntrada;

import lecturaYescritura.LectorDeArchivos;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Promocion {
	protected ArrayList<String[]> archivoPromos;
	protected Atraccion[] atracciones;
	protected String atraccion1;
	protected String atraccion2;
	protected double precio;
	protected String tipoDeAtraccion;
	protected int indice = 0;
	protected String nombreArchivo;
	protected double tiempoNecesario;

	public abstract Promocion siguientePromocion();

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
			for (int i = indice; i < this.atracciones.length; i++) {
				if (this.atracciones[i].getNombre().equals(atraccion))
					return this.atracciones[i].getTiempoDeRealizacion();
			}
		} else if (datoAbuscar == "costoXvisita") {
			for (int i = indice; i < this.atracciones.length; i++) {
				if (this.atracciones[i].getNombre().equals(atraccion))
					return this.atracciones[i].getCostoXvisita();
			}
		}
		return 0;
	}

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
		return calcularPrecio();
	}

	public String getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double getTiempoNecesario() {
		return calcularTiempoNecesario();
	}
}
