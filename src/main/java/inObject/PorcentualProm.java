package inObject;

import java.util.ArrayList;

public class PorcentualProm extends Promotion {
	private double discount;

	public PorcentualProm(Integer id, ArrayList<Attraction> attractions, String type, Double discount) {
		super(id, attractions, type);
		this.discount = discount;
	}

	// Getters
	public double getDiscount() {
		return discount;
	}

	@Override
	protected double calculatePrice() {
		this.price = super.calculatePrice() * (1 - (getDiscount() / 100));
		return this.price = (double) Math.round(this.price * 10d) / 10d;
	}

	@Override
	public String toString() {
		return super.toString() + ";Descuento: " + getDiscount() + "%";
	}

	@Override
	public void printToScreen() {
		super.printToScreen();
		System.out.println("Descuento: " + getDiscount() + "%.");
	}
}
