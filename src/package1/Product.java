package package1;

public class Product {

	
	
	private int productID;
	private String name;
	private double price;
	private int stockLevel;
	private int safetyLimit;
	
	
	public Product(int productID, String name, double price, int stockLevel, int safetyLimit) {
		this.productID = productID;
		this.name = name;
		this.price = price;
		this.stockLevel = stockLevel;
		this.safetyLimit = safetyLimit;
	}
	
	//GETTERS AND SETTERS

	public int getProductID() {
	    return productID;
	}

	public void setProductID(int productID) {
	    this.productID = productID;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public double getPrice() {
	    return price;
	}

	public void setPrice(double price) {
	    this.price = price;
	}

	public int getStockLevel() {
	    return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
	    this.stockLevel = stockLevel;
	}

	public int getSafetyLimit() {
	    return safetyLimit;
	}

	public void setSafetyLimit(int safetyLimit) {
	    this.safetyLimit = safetyLimit;
	}
	
	
	//METHODS
	
	void checkStock() {
		
	}
	
	void updateStock() {
		
	}
	
}
