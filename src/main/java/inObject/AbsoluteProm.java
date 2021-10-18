package inObject;

import java.util.ArrayList;

public class AbsoluteProm extends Promotion {

	public AbsoluteProm(ArrayList<Attraction> attractions, String type, String attraccion1, String attraccion2,
			double price) {
		super(attractions, type, attraccion1, attraccion2);
		this.price = price;
	}

	// Getters
	@Override
	public double getPrice() {
		return price;
	}

}
