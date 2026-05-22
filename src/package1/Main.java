package package1;

import java.util.ArrayList;
import java.util.Scanner;

import package1.Order.OrderType;

public class Main {

	static Scanner scanner = new Scanner(System.in);

	static User loggedUser;
	
	static ArrayList<User> users = new ArrayList<>();
	
	static ArrayList<Pharmacy> pharmacies = new ArrayList<>(); 
	
	static ArrayList<Product> products = new ArrayList<>();
	
	static ArrayList<Order> orders = new ArrayList<>();

	static double total = 0;
	
	public static void main(String[] args) {
		
		users.add(new User("panos","dimis", "Αποθήκη"));
		
		pharmacies.add(new Pharmacy(123, "69696969", "poytsou street"));
		
		products.add(new Product(1,"dildo",39.99,10,5));
		products.add(new Product(12,"poutsos",9.99,33,5));
		products.add(new Product(123,"mouni",12,12,5));
		products.add(new Product(1234,"dawn",58,9,5));
		
		orders.add(new Order(1,"21/06/2008",900.3,OrderType.PURCHASE));
		orders.add(new Order(2,"11/09/2008",203,OrderType.SALES));
		orders.add(new Order(3,"1/06/2008",123,OrderType.SALES));
		orders.add(new Order(4,"29/05/2008",334,OrderType.PURCHASE));
		
		
		logIn();
		
		boolean flag = false;
		
		switch(loggedUser.getRole()) {
			case "Αποθήκη":
				
				while(true) {
					clearConsole();
					
					automaticStockCheck();
					
					int action = menuForWarehouse();
					
					switch(action) {
						case 1:
							clearConsole();
							while(true) {
								System.out.println("════════════════════════════════════");
								System.out.println("            ΠΡΟΪΟΝΤΑ");
								System.out.println("════════════════════════════════════");
								
								for(Product p : products) {
									printProductDetails(p);
									
									System.out.println("\nEισάγετε το κατώτερο όριο άσφαλείας ");
									
									int limit = scanner.nextInt();
									scanner.nextLine();
									
									p.setSafetyLimit(limit);
									
									clearConsole();
								}
								
								while(true) {
									System.out.println("\nΤα όρια αποθέματος ενημερώθηκαν επιτυχώς.");
									System.out.println("Εισάγετε 0 για επιστροφή στο κεντρικό μενού");
									
									int exit = scanner.nextInt();
									scanner.nextLine();
									
									if(exit==0) {
										break;
									}
									clearConsole();
								}
								
								break;

							}
							break;
						case 2:
							while(true) {
								clearConsole();
								System.out.println("════════════════════════════════════");
								System.out.println("            ΠΑΡΑΓΓΕΛΙΕΣ");
								System.out.println("════════════════════════════════════");
								int numberOfOrders = printOrdersFromSupplier();
								
								if(numberOfOrders==0) {
									
									while(true) {
										
										clearConsole();
										System.out.println("Δεν υπάρχουν εκκρεμής παραγγελίες προμηθευτή");
										System.out.println("Εισάγετε 0 για επιστροφή στο κεντρικό μενού");
										
										int exit = scanner.nextInt();
										scanner.nextLine();
										
										if(exit==0) {
											break;
										}
										
									}
									
									break;
								}
								
								System.out.println("Επιλογή εκκρεμής παραγγελίας προμηθευτή");
								System.out.println("Εισάγετε το Id της παραγγελίας για ολοκλήρωση παραλαβής");
								
								int orderId = scanner.nextInt();
								scanner.nextLine();
								
								Order order = searchOrderById(orderId);
								clearConsole();
								
								if(order!=null && order.getOrderType()==OrderType.PURCHASE) {
									System.out.println("Η παραλαβή ολοκληρώθηκε");
									
									
									//EDW GINETE UPDATE STOCK
									
									
								}else {
									System.out.println("Η παραλαβή με Id "+orderId +" δεν βρέθηκε");
								}
								
								while(true) {
									System.out.println("Εισάγετε 0 για επιστροφή στο κεντρικό μενού");
									
									int exit = scanner.nextInt();
									scanner.nextLine();
									
									if(exit==0) {
										break;
									}
								}
								
								break;
							}
							break;
						case 3:
							System.out.println("Διαχείριση Εκκρεμών Παραγγελιών");
							break;
						case 4:
							System.out.println("Έξοδος από το σύστημα...");
							System.exit(0);
					}
				}

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
													
													
													//EDW GINETE UPDATE TO STOCK
													
													
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
										
										
										//EDW GINETE UPDATE TO STOCK
										
										
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
								System.exit(0);
						default:
							System.out.println("Μη έγκυρη επιλογή!\n");
							menuForCashier();
					}
				}
				
			case "Εξυπηρέτηση":
				
				while(true) {
					clearConsole();
					
					int action = menuForSearch();
					
					if(action==2) {
						break;
					}
					clearConsole();

					while(true){
						System.out.println("Eισάγετε για έξοδο → 0");
						System.out.println("\nEισάγετε τον κωδικό του προϊόντος προς αναζήτηση");
						System.out.print("Κωδικός προϊόντος: ");
						int productCode = scanner.nextInt();
						scanner.nextLine();
						
						if(productCode==0) {
							break;
						}
						
						Product product = searchProductCode(productCode);
						
						if(product!=null) {
							clearConsole();
							printProductDetails(product);
						}else {
							clearConsole();
							System.out.println("Δεν βρέθηκε προϊόν με κωδικό: "+productCode+"\n");
						}
					}
					
					
				}
				
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
			String username = scanner.nextLine().trim();

			System.out.print("Κωδικός Πρόσβασης: ");
			String password = scanner.nextLine().trim();

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
	
	
	
	private static int menuForWarehouse() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("         ΚΕΝΤΡΙΚΟ ΜΕΝΟΥ");
		System.out.println("════════════════════════════════════");
		System.out.println("1. Διαχείριση Ορίων Αποθέματος");
		System.out.println("2. Παραλαβή Παραγγελίας Προμηθευτή");
		System.out.println("3. Διαχείριση Εκκρεμών Παραγγελιών");
		System.out.println("4. Έξοδος");
		
		int action = scanner.nextInt();
		scanner.nextLine();
		
		return action;
	}
	
	
	
	private static void menuForAccounting() {
		
	}
	
	
	private static int menuForSearch() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("         ΚΕΝΤΡΙΚΟ ΜΕΝΟΥ");
		System.out.println("════════════════════════════════════");
		System.out.println("1. Αναζήτηση Προϊόντων");
		System.out.println("2. Έξοδος");
		
		int action = scanner.nextInt();
		scanner.nextLine();
		
		return action;
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
	
	
	
	private static void automaticStockCheck() {
		for(Product p : products) {
			if(p.getStockLevel()<p.getSafetyLimit()) {
				//paraggelia
			}
		}
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
	
	
	
	private static int printOrdersFromSupplier() {
		int i = 0;
		for(Order o : orders) {
			if(o.getOrderType()==OrderType.PURCHASE) {
				i++;
				System.out.println(i+ ") Id: " + o.getOrderID());
				System.out.println("   Total: "+ o.getTotalAmount());
				System.out.println("   Date:" + o.getDate());
				System.out.println();
			}
		}
		
		return i;
	}
	
	
	
	private static Order searchOrderById(int orderId) {
		for(Order o :orders) {
			if(orderId == o.getOrderID()) {
				return o;
			}
		}
		return null;
	}
	
	
	
	private static void printProductDetails(Product p) {
		System.out.println("\nΌνομα: "+ p.getName());
		System.out.println("Απόθεμα: " + p.getStockLevel());
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
