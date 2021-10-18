package inObject;

import java.util.ArrayList;

public abstract class Promotion implements Acquirable {
	protected ArrayList<Attraction> attractionsData;
	protected ArrayList<Attraction> includedAttractions;
	protected double price;
	protected String AttractionType;
	protected double CompletionTime;

	public Promotion(ArrayList<Attraction> attractions, String type, String attraction1, String attraction2) {
		this.attractionsData = attractions;
		this.AttractionType = type;
		includedAttractions = new ArrayList<Attraction>();
		this.includedAttractions.add(setAttraction(attraction1));
		this.includedAttractions.add(setAttraction(attraction2));
	}

	// Getters
	public Attraction getAttraction1() {
		return includedAttractions.get(0);
	}

	public Attraction getAttraction2() {
		return includedAttractions.get(1);
	}

	public ArrayList<Attraction> getAtraccionesIncluidas() {
		return includedAttractions;
	}

	public double getPrice() {
		return calculatePrice();
	}

	public String getAttractionType() {
		return AttractionType;
	}

	public double getCompletionTime() {
		return calculateCompletionTime();
	}

	// Setter
	private Attraction setAttraction(String attraction) {
		return attractionsData.get(AttractionIndex(attraction));
	}

	// Suma los precios de las dos atracciones
	protected double calculatePrice() {
		this.price = 0;
		this.price += getAttraction1().getPrice();
		this.price += getAttraction2().getPrice();
		return price;
	}

	// Suma los tiempos de las dos atracciones
	protected double calculateCompletionTime() {
		this.CompletionTime = 0;
		this.CompletionTime += getAttraction1().getCompletionTime();
		this.CompletionTime += getAttraction2().getCompletionTime();
		return CompletionTime;
	}

	public int AttractionIndex(String attraction) {
		for (int i = 0; i < attractionsData.size(); i++) {
			if (attractionsData.get(i).getName().equals(attraction))
				return i;
		}
		return -1;
	}

	public boolean useQuota() {
		if (!isFull()) {
			System.out.println("USO CUOTA\n\n ");
			getAttraction1().useQuota();
			getAttraction2().useQuota();
			return true;
		}
		return false;
	}

	public boolean isFull() {
		return getAttraction1().isFull() || getAttraction2().isFull();
	}

	public boolean isPromotion() {
		return true;
	}

	// Si se comparan atracciones devolvera true si tienen el mismo nombre

	// Atraccion y promocion devolvera true si alguna atraccion
	// dentro de la promocion tiene el mismo nombre que la atraccion
	@Override
	public boolean equals(Object object) {
		if (object instanceof Attraction)
			for (Attraction atraccion : includedAttractions)
				if (object.equals(atraccion))
					return true;
		if (object instanceof Promotion)
			for (Attraction atraccion : includedAttractions)
				if (((Promotion) object).getAtraccionesIncluidas().contains(atraccion))
					return true;
		return false;
	}

	@Override
	public String toString() {
		return "Promocion " + getAttractionType() + ";Atracciones: " + getAttraction1().getName() + ", "
				+ getAttraction2().getName() + ";Duracion: " + getCompletionTime() + " hora/s;Precio: "
				+ getPrice() + " moneda/s";
	}

	public void printToScreen() {
		System.out.println("Promocion " + getAttractionType() + ".\nAtracciones: " + getAttraction1().getName() + ", "
				+ getAttraction2().getName() + ".\nDuracion: " + getCompletionTime() + " hora/s.\nPrecio: "
				+ getPrice() + " moneda/s.");
	}
}
