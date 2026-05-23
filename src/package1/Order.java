package package1;

import java.util.HashMap;
import java.util.Map;

public class Order {

	enum OrderType {
	    PURCHASE,
	    SALES
	}
	
	private int orderID;
	private String date;
	private String status;
	private double totalAmount;
	private OrderType orderType;
	private Map<Product,Integer> products = new HashMap<Product,Integer>();
	
	
	Order(int orderID, String date, OrderType orderType, Map<Product,Integer> products){
		this.orderID = orderID;
		this.date = date;
		this.orderType = orderType;
		this.products = products;
		for(Product product : products.keySet()) {

	        int quantity = products.get(product);

	        this.totalAmount += product.getPrice() * quantity;
	    }
		if(orderType==OrderType.PURCHASE) {
			this.status = "Αναμονή";
		}else {
			this.status = "Επεξεργασία";
		}
	}
	
	Order(int orderID, String date, OrderType orderType){
		this.orderID = orderID;
		this.date = date;
		this.orderType = orderType;
		if(orderType==OrderType.PURCHASE) {
			this.status = "Αναμονή";
		}else {
			this.status = "Επεξεργασία";
		}
	}
	
	Order(){
		
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

	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
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
	
	
	//METHODS
	
	void addProduct(Product product, int quantity) {
		products.put(product, quantity);
		
	    this.totalAmount += product.getPrice() * quantity;
	    
	}
	
	
}
