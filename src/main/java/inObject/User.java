package inObject;

import java.util.ArrayList;

import dao.UserDAO;

public class User {
	private ArrayList<Acquirable> acquiredSuggestions = new ArrayList<Acquirable>();
	private int id;
	private String name;
	private double availableMoney;
	private double availableTime;
	private String preferredType;

	public User(Integer id, String name, double money, double time, String type) {
		this.id = id;
		this.name = name;
		this.availableMoney = money;
		this.availableTime = time;
		this.preferredType = type;
	}

	// Getters
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public double getAvailableMoney() {
		return availableMoney;
	}

	public double getAvailableTime() {
		return availableTime;
	}

	public String getPreferredType() {
		return preferredType;
	}

	public ArrayList<Acquirable> getAcquiredSuggestions() {
		return acquiredSuggestions;
	}

	// Adquiere la sugerencia sumandola a las listas y resta tiempo y presupuesto
	public boolean acquire(Acquirable suggestion) {
		if (canBuy(suggestion)) {
			UserDAO.acquire(suggestion, id);
			getAcquiredSuggestions().add(suggestion);
			this.availableTime -= suggestion.getCompletionTime();
			this.availableMoney -= suggestion.getPrice();
			// falta restar plata y tiempo en la db
			suggestion.useQuota();
			return true;
		}
		return false;
	}

	// Chequea si una promocion entra en su presupuesto y tiempo disponible
	public boolean canBuy(Acquirable suggestion) {
		boolean buy = true;
		// Si la atraccion ya se encuentra adquirida por el usuario no podra ser adquirida otra vez
		if (getAcquiredSuggestions().contains(suggestion))
			buy = false;
		// Tampoco si no hay dinero, tiempo o cupos
		if (suggestion.getCompletionTime() > this.availableTime 
				  || suggestion.getPrice() > this.availableMoney
				  || suggestion.isFull())
			buy = false;
		return buy;
	}

	@Override
	public String toString() {
		return "[" + getName() + ", " + getAvailableMoney() + ", " + getAvailableTime() + ", " + getPreferredType()
				+ "]";
	}
}
