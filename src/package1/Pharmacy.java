package package1;

public class Pharmacy {

	
	private int pharmacyID;
	private String phone;
	private String location;

	
	Pharmacy(int pharmacyID, String phone, String location){
		this.pharmacyID = pharmacyID;
		this.phone = phone;
		this.location = location;
	}
	
	
	//GETTERS AND SETTERS

	public int getPharmacyID() {
	    return pharmacyID;
	}

	public void setPharmacyID(int pharmacyID) {
	    this.pharmacyID = pharmacyID;
	}

	public String getLocation() {
	    return location;
	}

	public void setLocation(String location) {
	    this.location = location;
	}

	public String getPhone() {
	    return phone;
	}

	public void setPhone(String phone) {
	    this.phone = phone;
	}
	

	
	
}
