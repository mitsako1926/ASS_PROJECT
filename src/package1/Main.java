package package1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner scanner = new Scanner(System.in);

	static User loggedUser;
	
	static ArrayList<User> users = new ArrayList<>();
	
	static ArrayList<Pharmacy> pharmacies = new ArrayList<>(); 
	
	static ArrayList<Product> products = new ArrayList<>(); 

	static double total = 0;
	
	public static void main(String[] args) {
		
		users.add(new User("panos","dimis", "Ταμείο"));
		
		pharmacies.add(new Pharmacy(123, "69696969", "poytsou street"));
		
		products.add(new Product(12345,"dildo",39.99,10,5));
		
		logIn();
		
		boolean flag = false;
		
		switch(loggedUser.getRole()) {
			case "Αποθήκη":
				
				break;
			case "Ταμείο":
				while(true) {
					if(!flag) {
						clearConsole();
					}

					int action = menuForCashier();
					
					clearConsole();
					
					switch(action) {
						case 1: 
							total = 0;
							flag = false;
							
							while(true) {
								String keyword = newWholesaleSale().trim();
								
								if(!keyword.equals("0")) {
									Pharmacy searchedPharmacy = searchPharmacy(keyword);
									
									clearConsole();
									printPharmacyDetails(searchedPharmacy);
										
									if(searchedPharmacy!=null) {
										while(true) {
											
											printForProductInput();
											int productCode = scanner.nextInt();
											scanner.nextLine();
											
											if(productCode==0) {
												if(total<=0) {
													clearConsole();
													System.out.println("Παρακαλώ επιλέξτε προίοντα\n");
													continue;
												}else {
													clearConsole();
													System.out.println("Επιτυχής Έκδοση Τιμολογίου\n");
													break;
												}
											}
											
											Product product = searchProductCode(productCode);
											
											if(product!=null) {
												clearConsole();
												printProductDetails(product);
												System.out.println("Ποσότητα: ");
												int quantity = scanner.nextInt();
												scanner.nextLine();
												
												clearConsole();
												calculateAndPrintTotalWholesale(quantity, product);
											}else {
												clearConsole();
												System.out.println("Δεν βρέθηκε προϊόν με κωδικό: "+productCode+"\n");
											}
										}
									}
								}else {
									break;
								}
								
							}
							
							break;
						case 2: 
							total = 0;
							flag = false;
							while(true) {
								System.out.println("Έκδοση Απόδειξης Λιανικής → 0 ");
								System.out.println("\nEισάγετε τον κωδικό του προϊόντος προς προσθήκη");
								System.out.print("Κωδικός προϊόντος: ");
								int productCode = scanner.nextInt();
								scanner.nextLine();
								
								if(productCode==0) {
									if(total<=0) {
										clearConsole();
										System.out.println("Παρακαλώ επιλέξτε προίοντα\n");
										continue;
									}else {
										clearConsole();
										flag =true;
										System.out.println("Επιτυχής Έκδοση Απόδειξης Λιανικής\n");
										break;
									}
								}
								
								Product product = searchProductCode(productCode);
								
								if(product!=null) {
									clearConsole();
									printProductDetails(product);
									System.out.println("Ποσότητα: ");
									int quantity = scanner.nextInt();
									scanner.nextLine();
									
									clearConsole();
									calculateAndPrintTotalRetail(quantity, product);
								}else {
									clearConsole();
									System.out.println("Δεν βρέθηκε προϊόν με κωδικό: "+productCode+"\n");
								}
							}
							
							break;
						case 3: System.out.println("Έξοδος από το σύστημα...");
								System.exit(0);;
						default:
							System.out.println("Μη έγκυρη επιλογή!\n");
							menuForCashier();
					}
				}
				
			case "Λογιστήριο":
				
				break;					
		}
		
		
		scanner.close();
	}
	
	
	
	private static void logIn() {
		
		while(true) {
			System.out.println("════════════════════════════════════");
			System.out.println("        ΣΥΝΔΕΣΗ ΧΡΗΣΤΗ");
			System.out.println("════════════════════════════════════");

			System.out.print("Όνομα Χρήστη: ");
			String username = scanner.nextLine();

			System.out.print("Κωδικός Πρόσβασης: ");
			String password = scanner.nextLine();

			for(User u : users) {

			    if(u.equals(new User(username, password))) {
			        loggedUser = u;
			        break;
			    }
			}
			
			if(loggedUser!=null) {
				break;
			}
			
			clearConsole();
			System.out.println("\nΔεν βρέθηκε χρήστης\n");
		}
	}
	
	
	
	private static void menuForWarehouse() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("         ΚΕΝΤΡΙΚΟ ΜΕΝΟΥ");
		System.out.println("════════════════════════════════════");
		System.out.println("1. Διαχείριση Ορίων Αποθέματος");
		System.out.println("2. Εκκρεμείς Παραγγελίες");
		System.out.println("3. Αναζήτηση Προϊόντων");
		System.out.println("4. Έξοδος");
	}
	
	
	
	private static void menuForAccounting() {
		
	}
	
	
	
	private static int menuForCashier() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("         ΚΕΝΤΡΙΚΟ ΜΕΝΟΥ");
		System.out.println("════════════════════════════════════");
		System.out.println("1. Νέα Πώληση Χονδρικής");
		System.out.println("2. Νέα Πώληση Λιανικής");
		System.out.println("3. Έξοδος");
		System.out.println("════════════════════════════════════");
		System.out.print("Επιλέξτε ενέργεια: ");
		
		int action = scanner.nextInt();
		scanner.nextLine();
		
		return action;
	}
	
	
	
	private static String newWholesaleSale() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("      ΝΕΑ ΧΟΝΔΡΙΚΗ ΠΩΛΗΣΗ");
		System.out.println("════════════════════════════════════");
		System.out.println("Αναζήτηση Πελάτη - Φαρμακείου");
		System.out.println("------------------------------------");
		System.out.print("Εισάγετε Όνομα ή ΑΦΜ Φαρμακείου (Κεντρικό μενού → 0): ");
		
		return scanner.nextLine();
	}
	
	
	
	private static Pharmacy searchPharmacy(String keyword) {
		for(Pharmacy ph : pharmacies) {

		    if(ph.getPharmacyID()==Integer.valueOf(keyword)) {
		        return ph;
		    }
		}
		return null;
	}
	
	
	
	private static void printPharmacyDetails(Pharmacy pharmacy) {
		
		if(pharmacy!=null) {
			System.out.println("\n════════════════════════════════════");
			System.out.println("     ΣΤΟΙΧΕΙΑ ΦΑΡΜΑΚΕΙΟΥ");
			System.out.println("════════════════════════════════════");
			System.out.println("ΑΦΜ          : " + pharmacy.getPharmacyID());
			System.out.println("Τοποθεσία    : " + pharmacy.getLocation());
			System.out.println("Τηλέφωνο     : " + pharmacy.getPhone());
			System.out.println("════════════════════════════════════");
		
		}else {
			clearConsole();
			System.out.println("Δεν βρεθηκε φαρμακείο");
		}
		
	}
	
	
	
	private static void printForProductInput(){
		System.out.println("Οριστικοποίηση και Έκδοση Τιμολογίου → 0");
		System.out.println("\nEισάγετε τον κωδικό του προϊόντος προς προσθήκη");
		System.out.print("Κωδικός προϊόντος: ");
	}
	
	
	
	private static Product searchProductCode(int code) {
		
		for(Product p : products) {
			if(code==p.getProductID()) {
				return p;
			}
		}
		return null;
		
	}
	
	
	
	private static void printProductDetails(Product p) {
		System.out.println("\nΑπόθεμα: " + p.getStockLevel());
		System.out.println("Τιμή ανά μονάδα: " + p.getPrice());
	}
	
	
	
	private static void calculateAndPrintTotalWholesale(int quantity, Product product) {
		total+= product.getPrice()*quantity;
		System.out.println("Σύνολο παραγγελίας (καθαρή αξία + ΦΠΑ): ");
		System.out.printf("%.2f + %.2f \n", total, total * 0.24);
	}
	
	
	
	private static void calculateAndPrintTotalRetail(int quantity, Product product) {
		total+= product.getPrice()*quantity + product.getPrice()*quantity*0.24;
		System.out.println("Σύνολο παραγγελίας: ");
		System.out.printf("%.2f \n", total);
	}
	
	
	
	private static void clearConsole() {
	    for(int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}
	
	

}
