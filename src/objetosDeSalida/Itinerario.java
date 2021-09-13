package objetosDeSalida;

import objetosDeEntrada.Adquirible;
import objetosDeEntrada.Usuario;

public class Itinerario {
	private Usuario user;
	private double dineroGastado;
	private double horasNecesarias;

	public Itinerario(Usuario user) {
		this.user = user;
		this.dineroGastado = calcularDineroGastado();
		this.horasNecesarias = calcularHorasNecesarias();
	}

	// Getters
	public Usuario getUser() {
		return user;
	}

	public double getDineroGastado() {
		return dineroGastado;
	}

	public double getHorasNecesarias() {
		return horasNecesarias;
	}

	// Calculan los atributos
	private double calcularDineroGastado() {
		double sum = 0;
		for (Adquirible s : getUser().getSugerenciasAdquiridas())
			sum += s.getPrecio();
		return sum;
	}

	private double calcularHorasNecesarias() {
		double sum = 0;
		for (Adquirible s : getUser().getSugerenciasAdquiridas())
			sum += s.getTiempoDeRealizacion();
		return sum;
	}
}
