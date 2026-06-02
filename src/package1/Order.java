package package1;

import java.util.HashMap;
import java.util.Map;

public class Order {

	enum OrderType {
	    PURCHASE,
	    SALES,
	    RETAIL
	}
	

	public enum OrderStatus {
		PENDING,
		PROCESSING,
		COMPLETED,
		READY_TO_SHIP
	}
	
	static int ID = 0;
	
	private int orderID;
	private Customer customer;
	private Employee issuedBy;
	private String date;
	private OrderStatus status;
	private double totalAmount;
	private OrderType orderType;
	private Map<Product,Integer> products = new HashMap<Product,Integer>();
	
	
	Order(String date, OrderType orderType, Map<Product,Integer> products, Customer customer, Employee issuedBy){
		ID++;
		this.orderID = ID;
		this.date = date;
		this.orderType = orderType;
		this.products = products;
		for(Product product : products.keySet()) {

	        int quantity = products.get(product);

	        this.totalAmount += product.getPrice() * quantity;
	    }
		if(orderType==OrderType.PURCHASE) {
			this.status = OrderStatus.PENDING;
		}else {
			this.status = OrderStatus.PROCESSING;
		}
		this.customer = customer;
		this.issuedBy = issuedBy;
	}
	
	Order(String date, OrderType orderType){
		ID++;
		this.orderID = ID;
		this.date = date;
		this.orderType = orderType;
		if(orderType==OrderType.PURCHASE) {
			this.status = OrderStatus.PENDING;
		}else {
			this.status = OrderStatus.PROCESSING;
		}
	}
	
	
	//GETTERS AND SETTERS

	public int getOrderID() {
	    return orderID;
	}

	public void setOrderID(int orderID) {
	    this.orderID = orderID;
	}

	public String getDate() {
	    return date;
	}

	public void setDate(String date) {
	    this.date = date;
	}

	public OrderStatus getStatus() {
	    return status;
	}

	public void setStatus(OrderStatus status) {
	    this.status = status;
	}

	public double getTotalAmount() {
	    return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
	    this.totalAmount = totalAmount;
	}
	
	public OrderType getOrderType() {
		return this.orderType;
	}
	
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	public Map<Product, Integer> getProducts(){
		return this.products;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Employee getIssuedBy() {
		return issuedBy;
	}
	
	public void setIssuedBy(Employee employee) {
		this.issuedBy = employee;
	}
	
	//METHODS
	
	void addProduct(Product product, int quantity) {
		products.put(product, quantity);
		
	    this.totalAmount += product.getPrice() * quantity;
	    
	}
	
	private String getDocumentType() {
	    if(orderType == OrderType.RETAIL) {
	        return "Απόδειξη";
	    }

	    return "Τιμολόγιο";
	}
	
	public void printData() {
		System.out.println();
		

	    if(orderType == OrderType.PURCHASE) {

	        System.out.println("════════════════════════════════════");
	        System.out.println("ΠΑΡΑΓΓΕΛΙΑ ΠΡΟΜΗΘΕΥΤΗ");
	        System.out.println("Λόγος: Απόθεμα κάτω από το όριο ασφαλείας");
	        System.out.println("------------------------------------");

	        for(Product product : products.keySet()) {

	            int quantity = products.get(product);

	            System.out.println("Προϊόν   : " + product.getName());
	            System.out.println("Ποσότητα : " + quantity);
	            System.out.println("------------------------------------");
	        }

	    } else {

	        System.out.println("════════════════════════════════════");
	        System.out.println("Κωδικός Παραγγελίας : " + orderID);
	        System.out.println("ΑΦΜ Πελάτη          : " + customer.getAFM());
	        System.out.println("Τύπος Παραστατικού  : " + getDocumentType());
	        System.out.println("Υπάλληλος           : " + issuedBy.getName());
	        System.out.println("Κατάσταση           : " + status);

	        for(Product product : products.keySet()) {

	            int quantity = products.get(product);

	            System.out.println(product.getName()
	                    + " | Ποσότητα: " + quantity);
	        }

	        if(orderType==OrderType.RETAIL) {
	        	System.out.printf("Σύνολο: %.2f€\n", totalAmount);
	        }else {
	        	System.out.printf("Σύνολο: %.2f€ + %.2f€ \n", totalAmount, totalAmount*0.24);
	        }
	        
	    }
	}
	
	
}
