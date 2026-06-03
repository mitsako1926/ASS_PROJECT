package package1;

public class Product {

	//ETSI TO ID PANTA THA EINAI MONADIKO (SAN PRIMARY KEY ALA DE DOULEYOUME ME BASHS TORA)
	static int ID = 0;
	
	private int productID;
	private String name, category;
	private double price;
	private int stockLevel;
	private int safetyLimit;
	
	
	public Product(String name, String category ,double price, int stockLevel, int safetyLimit) {
		ID++;
		this.category = category;
		this.productID = ID;
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
	
	public String getCategory() {
	    return category;
	}

	public void setCategory(String category) {
	    this.category = category;
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
	
	
	//EQUALS KAI HASHCODE OSTE NA SIGGRINOUME PRODUCTS OPOS EMEIS THELUME (ME TO ID TOUS)
	@Override
	public boolean equals(Object o) {

	    if (this == o) {
	        return true;
	    }

	    if (!(o instanceof Product)) {
	        return false;
	    }

	    Product product = (Product) o;

	    return productID == product.productID;
	}
	
	
	
	@Override
	public int hashCode() {
	    return Integer.hashCode(productID);
	}
	
	
	
	public void printData() {
		System.out.println();
	    System.out.println("════════════════════════════════════");
	    System.out.println("Κωδικός Προϊόντος : " + productID);
	    System.out.println("Όνομα             : " + name);
	    System.out.println("Κατηγορία         : " + category);
	    System.out.printf("Τιμή              : %.2f€\n", price);
	    System.out.println("Διαθέσιμο Απόθεμα : " + stockLevel);
	    System.out.println("Όριο Ασφαλείας    : " + safetyLimit);
	    System.out.println("════════════════════════════════════");
	}
	
}
