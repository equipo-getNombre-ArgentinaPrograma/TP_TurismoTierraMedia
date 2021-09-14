package objetosDeSalida;

import java.util.ArrayList;

import objetosDeEntrada.Adquirible;
import objetosDeEntrada.Usuario;

public class Itinerario {
	private Usuario user;

	public Itinerario(Usuario user) {
		this.user = user;
	}

	// Getters
	public Usuario getUser() {
		return user;
	}
	
	public ArrayList<Adquirible> getSugerenciasAdquiridas(){
		return user.getSugerenciasAdquiridas();
	}

	public double getDineroGastado() {
		return calcularDineroGastado();
	}

	public double getTiempoNecesario() {
		return calcularTiempoNecesario();
	}

	// Calculan los atributos
	private double calcularDineroGastado() {
		double sum = 0;
		for (Adquirible s : getUser().getSugerenciasAdquiridas())
			sum += s.getPrecio();
		return sum;
	}

	private double calcularTiempoNecesario() {
		double sum = 0;
		for (Adquirible s : getUser().getSugerenciasAdquiridas())
			sum += s.getTiempoDeRealizacion();
		return sum;
	}
}
