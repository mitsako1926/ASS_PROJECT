package package1;

public class Invoice {

	//TO TIMOLOGIO EXEI IDIO invoiceNumber ME TO ID TOU ORDER POU SINDEETE
	private int invoiceNumber;
	private String issueDate;
	private double amount;

	
	Invoice(int invoiceNumber, String issueDate, double amount){
		this.invoiceNumber = invoiceNumber;
		this.issueDate = issueDate;
		this.amount = amount;
	}
	
	
	//GETTERS AND SETTERS

	public int getInvoiceNumber() {
	    return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
	    this.invoiceNumber = invoiceNumber;
	}

	public String getIssueDate() {
	    return issueDate;
	}

	public void setIssueDate(String issueDate) {
	    this.issueDate = issueDate;
	}

	public double getAmount() {
	    return amount;
	}

	public void setAmount(double amount) {
	    this.amount = amount;
	}
	

	
}
