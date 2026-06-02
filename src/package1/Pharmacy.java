package package1;

public class Pharmacy {

	private String name, afm, DOY;

	
	Pharmacy(String name, String afm, String DOY){
		this.name = name;
		this.afm = afm;
		this.DOY = DOY;
	}
	
	
	//GETTERS AND SETTERS

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getDOY() {
	    return DOY;
	}

	public void setDOY(String DOY) {
	    this.DOY = DOY;
	}

	public String getAFM() {
	    return afm;
	}

	public void setAFM(String afm) {
	    this.afm = afm;
	}
	
	
	
}
