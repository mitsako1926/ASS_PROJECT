package package1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import package1.Order.OrderType;

public class Main {

	static Scanner scanner = new Scanner(System.in);

	static User loggedUser;
	
	static ArrayList<User> users = new ArrayList<User>();
	
	static ArrayList<Pharmacy> pharmacies = new ArrayList<Pharmacy>(); 
	
	static ArrayList<Product> products = new ArrayList<Product>();
	
	static ArrayList<Order> orders = new ArrayList<Order>();
	
	static ArrayList<Invoice> invoices = new ArrayList<Invoice>();

	static double total = 0;
	
	public static void main(String[] args) {
		
		//DATA
		
		
		users.add(new User("panos","dimis","Πώληση"));
		
		pharmacies.add(new Pharmacy(123, "69696969", "poytsou street"));
		
		products.add(new Product(11,"dildo",39.99,10,5));
		products.add(new Product(12,"poutsos",9.99,33,5));
		products.add(new Product(123,"mouni",12,12,5));
		products.add(new Product(1234,"dawn",58,9,5));
	
		Order order1 = new Order(1, "21/06/2008", OrderType.PURCHASE, Map.ofEntries(
                   Map.entry(products.get(0), 2),
                   Map.entry(products.get(1), 12),
                   Map.entry(products.get(2), 21))             );
		Order order2 = new Order(2, "11/09/2008", OrderType.SALES, Map.ofEntries(
				   Map.entry(products.get(3), 12),
				   Map.entry(products.get(2), 2),
				   Map.entry(products.get(1), 20))             );
		Order order3 = new Order(3, "1/06/2008", OrderType.SALES, Map.ofEntries(
				   Map.entry(products.get(2), 8),
			       Map.entry(products.get(3), 14),
				   Map.entry(products.get(1), 3))              );
		Order order4 = new Order(4, "29/05/2008", OrderType.PURCHASE, Map.ofEntries(
				   Map.entry(products.get(0), 12),
			       Map.entry(products.get(1), 2),
				   Map.entry(products.get(3), 22))             );
		
		orders.add(order1);  orders.add(order2);  orders.add(order3);  orders.add(order4);
	
		Invoice invoice1 = new Invoice(order1.getOrderID(), order1.getDate().toString(), order1.getTotalAmount());
		Invoice invoice2 = new Invoice(order2.getOrderID(), order2.getDate().toString(), order2.getTotalAmount());
		Invoice invoice3 = new Invoice(order3.getOrderID(), order3.getDate().toString(), order3.getTotalAmount());
		Invoice invoice4 = new Invoice(order4.getOrderID(), order4.getDate().toString(), order4.getTotalAmount());
		
		invoices.add(invoice1);  invoices.add(invoice2);  invoices.add(invoice3);  invoices.add(invoice4);
		
		
		//PROGRAM
		
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
									printProductDetailsWholesale(p);
									
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
								
								if(order!=null && order.getOrderType()==OrderType.PURCHASE && order.getStatus().equals("Αναμονή")) {
									int input = printOrderList(order);
									
									if(input==1) {
										break;
									}
									
									clearConsole();
									System.out.println("Η παραλαβή ολοκληρώθηκε");
									order.setStatus("Ολοκληρώθηκε");
																		
									for(Product product : order.getProducts().keySet()) {

								        int quantity = order.getProducts().get(product);

								        for(Product p : products) {
								        	if(p.getProductID()==product.getProductID()) {
								        		p.setStockLevel(p.getStockLevel()+quantity);
								        		break;
								        	}
								        	
								        }
								        
								    }
									
								}else {
									System.out.println("Η παραγγελία με Id "+orderId +" δεν βρέθηκε");
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
							
							while(true) {
								clearConsole();
								
								System.out.println("════════════════════════════════════");
								System.out.println("            ΠΑΡΑΓΓΕΛΙΕΣ");
								System.out.println("════════════════════════════════════");
								
								int numOfOrders = printOrdersForSale();
								
								if(numOfOrders==0) {
									while(true) {
										
										clearConsole();
										System.out.println("Δεν υπάρχουν εκκρεμής παραγγελίες");
										System.out.println("Εισάγετε 0 για επιστροφή στο κεντρικό μενού");
										
										int exit = scanner.nextInt();
										scanner.nextLine();
										
										if(exit==0) {
											break;
										}
										
									}
									
									break;
								}
								
								System.out.println("Επιλογή εκκρεμής παραγγελίας");
								System.out.println("Εισάγετε το Id της παραγγελίας για προετοιμασία");
								
								int orderId = scanner.nextInt();
								scanner.nextLine();
								
								Order order = searchOrderById(orderId);
								clearConsole();
								
								if(order!=null && order.getOrderType()==OrderType.SALES && order.getStatus().equals("Επεξεργασία")) {
									int input = printOrderList(order);
									
									if(input==1) {
										break;
									}
									clearConsole();
									System.out.println("Η παραγγελία με Id "+ orderId +" είναι έτοιμη προς άποστολή");
									order.setStatus("προς άποστολή");
								}else {
									System.out.println("Η παραγγελία με Id "+ orderId +" δεν βρέθηκε");
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
						case 4:
							System.out.println("Έξοδος από το σύστημα...");
							System.exit(0);
					}
				}

			case "Πώληση":
				
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
							boolean isNewOrder = true;
							
							while(true) {
								clearConsole();
								int afm = newWholesaleSale();
								
								if(afm!=0) {
									Pharmacy searchedPharmacy = searchPharmacy(afm);
									
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
											}else if(productCode==1) {
												break;
											}
											
											Product product = searchProductCode(productCode);
											Order orderNew = null;
											
											if(product!=null) {
												clearConsole();
												printProductDetailsWholesale(product);
												
												System.out.println("Ποσότητα: ");
												int quantity = scanner.nextInt();
												scanner.nextLine();
												
												if(isNewOrder) {
													int randomId = (int)(Math.random()*100000);
													orderNew = new Order(randomId,LocalDate.now().toString(),OrderType.SALES);
													isNewOrder = false;
													orders.add(orderNew);
													invoices.add(new Invoice(orderNew.getOrderID(), orderNew.getDate().toString(), orderNew.getTotalAmount()));													
												}
												
												orderNew.addProduct(product, quantity);
												
												if(product.getStockLevel()<=quantity) {
													product.setStockLevel(0);
												}else {
													product.setStockLevel(product.getStockLevel()-quantity);
												}
												
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
								System.out.println("Eισάγετε 1 για έξοδο");
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
								}else if(productCode==1) {
									break;
								}
								
								Product product = searchProductCode(productCode);
								
								if(product!=null) {
									clearConsole();
									printProductDetailsRetail(product);
									System.out.println("Ποσότητα: ");
									
									int quantity = scanner.nextInt();
									scanner.nextLine();
									
									if(product.getStockLevel()<=quantity) {
										product.setStockLevel(0);
									}else {
										product.setStockLevel(product.getStockLevel()-quantity);
									}
									
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
							printProductDetailsWholesale(product);
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
	
	
	
	private static int newWholesaleSale() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("      ΝΕΑ ΧΟΝΔΡΙΚΗ ΠΩΛΗΣΗ");
		System.out.println("════════════════════════════════════");
		System.out.println("Αναζήτηση Πελάτη - Φαρμακείου");
		System.out.println("------------------------------------");
		System.out.print("Εισάγετε ΑΦΜ Φαρμακείου (Κεντρικό μενού → 0): ");
		
		int action = scanner.nextInt();
		scanner.nextLine();
		
		return action;
	}
	
	
	
	private static Pharmacy searchPharmacy(int afm) {
		for(Pharmacy ph : pharmacies) {

		    if(ph.getPharmacyID()==afm) {
		        return ph;
		    }
		}
		return null;
	}
	
	
	
	private static Product searchProductCode(int code) {
		
		for(Product p : products) {
			if(code==p.getProductID()) {
				return p;
			}
		}
		return null;
		
	}
	
	
	
	private static Order searchOrderById(int orderId) {
		for(Order o :orders) {
			if(orderId == o.getOrderID()) {
				return o;
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
	
	
	
	private static int printOrderList(Order order) {
		System.out.println("Προϊόντα παραγγελίας με Id: "+ order.getOrderID());
        System.out.println("------------------------------------");

		for(Product product : order.getProducts().keySet()) {

	        int quantity = order.getProducts().get(product);

	        System.out.println("Κωδικός   : "+ product.getProductID());

	        System.out.println("Προϊόν    : "+ product.getName());

	        System.out.printf("Τιμή      : %.2f€\n",product.getPrice());

	        System.out.println("Ποσότητα  : "+ quantity);

	        System.out.println("------------------------------------");
	    }

	    System.out.printf("Συνολικό Ποσό: %.2f€\n",order.getTotalAmount());
	    
	    System.out.println("Εισάγετε 0 για ολοκλήρωση");
	    System.out.println("Εισάγετε 1 για έξοδο");
	    
	    int action = scanner.nextInt();
	    scanner.nextLine();
	    
	    return action;
	}
	
	
	
	private static void printForProductInput(){
		System.out.println("Οριστικοποίηση και Έκδοση Τιμολογίου → 0");
		System.out.println("Εισάγετε 1 για έξοδο");
		System.out.println("\nEισάγετε τον κωδικό του προϊόντος προς προσθήκη");
		System.out.print("Κωδικός προϊόντος: ");
	}
	
	
	
	private static int printOrdersFromSupplier() {
		int i = 0;
		for(Order o : orders) {
			if(o.getOrderType()==OrderType.PURCHASE && o.getStatus().equals("Αναμονή")) {
				i++;
				System.out.println(i+ ") Id: " + o.getOrderID());
				System.out.printf("   Total: %.2f€\n", o.getTotalAmount());
				System.out.println("   Date:" + o.getDate());
				System.out.println();
			}
		}
		
		return i;
	}
	
	
	
	private static int printOrdersForSale(){
		int i = 0;
		for(Order o : orders) {
			if(o.getOrderType()==OrderType.SALES && o.getStatus().equals("Επεξεργασία")) {
				i++;
				System.out.println(i+ ") Id: " + o.getOrderID());
				System.out.printf("   Total: %.2f€\n", o.getTotalAmount());
				System.out.println("   Date:" + o.getDate());
				System.out.println();
			}
		}
		
		return i;
	}
	
	
	
	private static void printProductDetailsWholesale(Product p) {
		System.out.println("\nΌνομα: "+ p.getName());
		System.out.println("Απόθεμα: " + p.getStockLevel());
		System.out.printf("Τιμή ανά μονάδα (Χονδρική): %.2f€\n", p.getPrice());
	}
	
	
	
	private static void printProductDetailsRetail(Product p) {
		System.out.println("\nΌνομα: "+ p.getName());
		System.out.println("Απόθεμα: " + p.getStockLevel());
		System.out.printf("Τιμή ανά μονάδα (Λιανική): %.2f€\n" , p.getPrice()+p.getPrice()*0.24);
	}
	
	
	
	private static void automaticStockCheck() {
		boolean flag = true;
		Order orderNew = null;
		
		for(Product p : products) {
		
			if(p.getStockLevel()<p.getSafetyLimit()) {
				
				if(flag) {
					int randomId = (int)(Math.random()*100000);
					orderNew = new Order(randomId,LocalDate.now().toString(),OrderType.PURCHASE);
					orders.add(orderNew);
					flag = false;
					
					invoices.add(new Invoice(orderNew.getOrderID(), orderNew.getDate().toString(), orderNew.getTotalAmount()));
				}
				orderNew.addProduct(p, 10);
			}
		}
		
	}
	
	
	
	private static void calculateAndPrintTotalWholesale(int quantity, Product product) {
		total+= product.getPrice()*quantity;
		System.out.println("Σύνολο παραγγελίας (καθαρή αξία + ΦΠΑ): ");
		System.out.printf("%.2f€ + %.2f€ \n", total, total * 0.24);
	}
	
	
	
	private static void calculateAndPrintTotalRetail(int quantity, Product product) {
		total+= product.getPrice()*quantity + product.getPrice()*quantity*0.24;
		System.out.println("Σύνολο παραγγελίας: ");
		System.out.printf("%.2f€ \n", total);
	}
	
	
	
	private static void clearConsole() {
	    for(int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}

	

}
