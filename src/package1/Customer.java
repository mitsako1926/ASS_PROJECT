package package1;

public class Customer {

    private String name, type, afm, DOY, status;

    public Customer(String name, String type, String afm, String DOY, String status) {
        this.name = name;
        this.type = type;
        this.afm = afm;
        this.DOY = DOY;
        this.status = status;
    }

    // GETTERS & SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAFM() {
        return afm;
    }

    public void setAFM(String afm) {
        this.afm = afm;
    }

    public String getDOY() {
        return DOY;
    }

    public void setDOY(String DOY) {
        this.DOY = DOY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public void printData() {
    	System.out.println();
        System.out.println("════════════════════════════════════");
        System.out.println("Πελάτης      : " + name);
        System.out.println("Τύπος        : " + type);
        System.out.println("ΑΦΜ          : " + afm);
        System.out.println("ΔΟΥ          : " + DOY);
        System.out.println("Κατάσταση    : " + status);
        System.out.println("════════════════════════════════════");
    }
    
    @Override
    public String toString() {
        return name;
    }
}
