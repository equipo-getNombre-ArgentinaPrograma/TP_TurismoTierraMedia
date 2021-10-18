package outObject;

import java.util.ArrayList;

import inObject.Acquirable;
import inObject.User;

public class Itinerary {
	private User user;

	public Itinerary(User user) {
		this.user = user;
	}

	// Getters
	public User getUser() {
		return user;
	}
	
	public ArrayList<Acquirable> getSugerenciasAdquiridas(){
		return user.getAdquiredSuggestions();
	}

	public double getSpentMoney() {
		return calcularDineroGastado();
	}

	public double getTiempoNecesario() {
		return calcularTiempoNecesario();
	}

	// Calculan los atributos
	private double calcularDineroGastado() {
		double sum = 0;
		for (Acquirable s : getUser().getAdquiredSuggestions())
			sum += s.getPrice();
		return sum;
	}

	private double calcularTiempoNecesario() {
		double sum = 0;
		for (Acquirable s : getUser().getAdquiredSuggestions())
			sum += s.getCompletionTime();
		return sum;
	}
}
