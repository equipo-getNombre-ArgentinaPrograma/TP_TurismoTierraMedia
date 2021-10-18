package inObject;

public interface Acquirable {

	public double getPrice();

	public double getCompletionTime();

	public String getAttractionType();

	public boolean useQuota();

	public boolean isFull();

	public boolean isPromotion();
	
	public boolean equals(Object object);

	public void printToScreen();
	
}