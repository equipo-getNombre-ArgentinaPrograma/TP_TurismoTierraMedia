package generadores;

import java.util.ArrayList;

import objetosDeEntrada.*;

public class GenerarPromocion {
	ArrayList<Promocion> promosApt;
	String nombreUsuario;
	int indiceUsuario;
	Promocion promocionSugerida;

	Atraccion[] atracciones;
	Usuario[] usuarios;
	Promocion[] promosPor;
	Promocion[] promosAbs;
	Promocion[] promosAxB;
	Promocion[] promosAptas;

	GenerarArray generador;

	public GenerarPromocion(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.generador = new GenerarArray();
		this.usuarios = generador.deUsuarios();
		this.atracciones = generador.deAtracciones();
		this.promosPor = generador.dePromocionesPorcentuales();
		this.promosAbs = generador.dePromocionesAbsolutas();
		this.promosAxB = generador.dePromocionesAxB();

		this.indiceUsuario = this.buscarIndice();

		for (int i = 0; i < this.buscarPromosAptas().size(); i++) {
			System.out.println(this.buscarPromosAptas().get(i).toString());
		}

	}

	public int buscarIndice() {
		for (int i = 0; i < this.usuarios.length; i++) {
			if (this.usuarios[i].getNombre().equals(this.nombreUsuario))
				return i;
		}
		return 0;
	}

	public ArrayList<Promocion> buscarPromosAptas() {
		this.promosApt = new ArrayList<Promocion>();
		addToList(promosPor);
		addToList(promosAbs);
		addToList(promosAxB);
		return promosApt;
	}

	public void addToList(Promocion[] promos) {
		for (int i = 0; i < promos.length; i++) {
			if (usuarios[this.indiceUsuario].getPresupuesto() >= promos[i].getPrecio()
					&& usuarios[this.indiceUsuario].getTiempoDisponible() >= promos[i].getTiempoNecesario()) {
				promosApt.add(promos[i]);
			}
		}
	}
}
