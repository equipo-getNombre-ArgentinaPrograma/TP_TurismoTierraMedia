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
	
	public ArrayList<Acquirable> getAcquiredSuggestions(){
		return user.getAcquiredSuggestions();
	}

	public double getSpentMoney() {
		return calculateSpentMoney();
	}

	public double getSpentTime() {
		return calculateSpentTime();
	}

	// Calculan los atributos
	private double calculateSpentMoney() {
		double sum = 0;
		for (Acquirable s : getUser().getAcquiredSuggestions())
			sum += s.getPrice();
		return sum;
	}

	private double calculateSpentTime() {
		double sum = 0;
		for (Acquirable s : getUser().getAcquiredSuggestions())
			sum += s.getCompletionTime();
		return sum;
	}
}
