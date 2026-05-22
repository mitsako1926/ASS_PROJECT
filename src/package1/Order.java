package package1;

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
	
	
	Order(int orderID, String date, double totalAmount, OrderType orderType){
		this.orderID = orderID;
		this.date = date;
		this.totalAmount = totalAmount;
		this.orderType = orderType;
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
	
	
	//METHODS
	
	void createOrder() {
		
	}
	
	void addProduct() {
		
	}
	
	void updateStatus() {
		
	}
	
}
