package inObject;

import java.util.ArrayList;
import dao.UserDAO;

public class User {
	private ArrayList<Acquirable> acquiredSuggestions = new ArrayList<Acquirable>();
	private int id;
	private String name;
	private double availableCoins;
	private double availableTime;
	private String preferredType;

	public User(Integer id, String name, double coins, double time, String type) {
		this.id = id;
		this.name = name;
		this.availableCoins = coins;
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

	public double getAvailableCoins() {
		return Math.round(availableCoins*100.0)/100.0;
	}

	public double getAvailableTime() {
		return Math.round(availableTime*100.0)/100.0;
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
			getAcquiredSuggestions().add(suggestion);
			this.availableTime -= suggestion.getCompletionTime();
			this.availableCoins -= suggestion.getPrice();
			updateDB(suggestion);
			suggestion.useQuota();
			return true;
		}
		return false;
	}
	public boolean updateDB(Acquirable suggestion) {
		UserDAO.acquire(suggestion, id);
		UserDAO.useCoins(suggestion.getPrice(), id);
		UserDAO.useTime(suggestion.getCompletionTime(), id);
		return true;
	}

// Chequea si una promocion entra en su presupuesto y tiempo disponible
	public boolean canBuy(Acquirable suggestion) {
		boolean buy = true;
		// Si la atraccion ya se encuentra adquirida por el usuario no podra ser adquirida otra vez
		if (getAcquiredSuggestions().contains(suggestion))
			buy = false;
		// Tampoco si no hay dinero, tiempo o cupos
		if (suggestion.getCompletionTime() > this.availableTime 
				  || suggestion.getPrice() > this.availableCoins
				  || suggestion.isFull())
			buy = false;
		return buy;
	}

	@Override
	public String toString() {
		return "[" + getName() + ", " + getAvailableCoins() + ", " + getAvailableTime() + ", " + getPreferredType()
				+ "]";
	}
/*

public class User {
	private int id;
	private String name;

	public User(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

// Getters
	public int getId() {
		return id;
	}
	
	public String getName() {
		return UserDAO.getName(id);
	}

	public double getAvailableCoins() {
		return UserDAO.getCoins(id);
	}

	public double getAvailableTime() {
		return UserDAO.getTime(id);
	}

	public String getPreferredType() {
		return UserDAO.getPreferredType(id);
	}

	public ArrayList<Acquirable> getAcquiredSuggestions() {
		return User.DAO(getAcquiredSuggestions(id));
	}

// Adquiere la sugerencia sumandola a las listas y resta tiempo y presupuesto
	public boolean acquire(Acquirable suggestion) {
		if (canBuy(suggestion)) {
			UserDAO.acquire(suggestion, id);
			UserDAO.useCoins(suggestion.getPrice(), id);
			UserDAO.useTime(suggestion.getCompletionTime(), id);
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
		if (suggestion.getCompletionTime() > getAvailableTime()
				  || suggestion.getPrice() > getAvailableCoins()
				  || suggestion.isFull())
			buy = false;
		return buy;
	}

	@Override
	public String toString() {
		return "[" + getName() + ", " + getAvailableCoins() + ", " + getAvailableTime() + ", " + getPreferredType()
				+ "]";
	}
}
*/
}
