package inObject;

import java.util.ArrayList;

public class AxBProm extends Promotion {

	public AxBProm(ArrayList<Attraction> attractions, String type, String attraction1, String attraction2,
			String attraction3) {
		super(attractions, type, attraction1, attraction2);
		this.includedAttractions.add(setFreeAttraction(attraction3));
	}

	// Getter
	public Attraction getFreeAttraction() {
		return includedAttractions.get(2);
	}

	// Setter
	private Attraction setFreeAttraction(String atraccion) {
		return attractionsData.get(AttractionIndex(atraccion));
	}

	@Override
	public boolean useQuota() {
		if (!isFull()) {
			super.useQuota();
			getFreeAttraction().useQuota();
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		return super.isFull() && !getFreeAttraction().isFull();
	}

	// Agrega al metodo heredado el tiempo de su atributo propio
	@Override
	protected double calculateCompletionTime() {
		return super.calculateCompletionTime() + getFreeAttraction().getCompletionTime();
	}

	// Agrega los campos propios al toString heredado
	@Override
	public String toString() {
		return super.toString() + ";Promocion de regalo: " + getFreeAttraction().getName();
	}

	@Override
	public void printToScreen() {
		super.printToScreen();
		System.out.println("Promocion de regalo: " + getFreeAttraction().getName());
	}
}
