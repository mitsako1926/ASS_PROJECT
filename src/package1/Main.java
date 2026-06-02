package package1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import package1.Order.OrderStatus;
import package1.Order.OrderType;

public class Main {

	private static Scanner scanner = new Scanner(System.in);

	private static Employee loggedUser;
	
	private static ArrayList<Employee> employees = new ArrayList<Employee>();
	
	private static ArrayList<Pharmacy> pharmacies = new ArrayList<Pharmacy>(); 
	
	private static ArrayList<Product> products = new ArrayList<Product>();
	
	private static ArrayList<Order> orders = new ArrayList<Order>();
	
	private static ArrayList<Invoice> invoices = new ArrayList<Invoice>();
	
	private static ArrayList<Customer> customers = new ArrayList<Customer>();

	private static double total = 0;
	
	public static void main(String[] args) {
		
		// NOTES
		// PREPEI NA MIOTHOUN TA STOCK META APO TA ORDERS
		// TI THA GINIE ME SUPPLIER
		// SXOLIA PANTOU KAI PIO CLEAN
		
		//DATA 

		Customer customer1 = new Customer("Farmakeio Papadopoulou", "Pharmacy", "099845210", "DOY Kalamarias", "OK");
		Customer customer2 = new Customer("Farmakeio Nikolaidis", "Pharmacy", "078632145", "DOY Evosmou", "OK");
		Customer customer3 = new Customer("Georgiou Maria", "Retail", "112233445", "DOY Thessalonikis", "OK");
		
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
		
		Employee employee1 = new Employee("Antoniou", "Warehouse", "warehouse_user" , "Antoniou1" , "pass");
		Employee employee2 = new Employee("Eleftheriou", "Customer Service", "service_user", "Eleftheriou1" , "pass");
		Employee employee3 = new Employee("Sotiriou", "Cashier", "cashier_user", "Sotiriou1" , "pass");
		Employee employee4 = new Employee("Seller1", "Sales", "sales_user", "Seller1", "pass");
		
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
		employees.add(employee4);
		
		Product product1 = new Product("Thermometer", "paramedical", 12.50, 80, 30);
		Product product2 = new Product("Shoes", "paramedical", 35.00, 45, 20);
		Product product3 = new Product("Cream", "cosmetic", 18.00, 25, 40);
		Product product4 = new Product("makeup", "cosmetic", 22.00, 60, 25);
		
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		
		
		Order order1 = new Order("21/05/2026", OrderType.SALES, Map.ofEntries(
                   Map.entry(products.get(0), 10),
                   Map.entry(products.get(2), 5)), customer1, employee4 );
				
		Order order2 = new Order("11/12/2025", OrderType.SALES, Map.ofEntries(
				   Map.entry(products.get(1), 8),
				   Map.entry(products.get(3), 12)), customer1, employee4 );
		Order order3 = new Order("1/12/2025", OrderType.SALES, Map.ofEntries(
				   Map.entry(products.get(2), 20),
			       Map.entry(products.get(3), 10),
				   Map.entry(products.get(0), 6)), customer2, employee4 );
		Order order4 = new Order("29/05/2026", OrderType.RETAIL, Map.ofEntries(
				   Map.entry(products.get(0), 1),
			       Map.entry(products.get(3), 2)), customer3, employee3 );
		
		orders.add(order1);  
		orders.add(order2);  
		orders.add(order3);  
		orders.add(order4);
	
		Invoice invoice1 = new Invoice(order1.getOrderID(), order1.getDate().toString(), order1.getTotalAmount());
		Invoice invoice2 = new Invoice(order2.getOrderID(), order2.getDate().toString(), order2.getTotalAmount());
		Invoice invoice3 = new Invoice(order3.getOrderID(), order3.getDate().toString(), order3.getTotalAmount());
		Invoice invoice4 = new Invoice(order4.getOrderID(), order4.getDate().toString(), order4.getTotalAmount());
		
		invoices.add(invoice1);  
		invoices.add(invoice2);  
		invoices.add(invoice3);  
		invoices.add(invoice4);
		
		pharmacies.add(new Pharmacy("Farmakeio Papadopoulou", "099845210", "DOY Kalamarias"));
		pharmacies.add(new Pharmacy("Farmakeio Nikolaidis", "078632145", "DOY Evosmou"));
		
		automaticStockCheck();
		
		//PROGRAM
		
		logIn();
		
		boolean flag = false;
		
		exitLabel : 
		switch(loggedUser.getUserType()) {
			case "warehouse_user":{
				
				while(true) {
					clearConsole();
					
					automaticStockCheck();
					
					int choice0 = menuForWarehouse();					
					
					switch(choice0) {
						case 1:
							clearConsole();
							while(true) {
								System.out.println("════════════════════════════════════");
								System.out.println("            ΠΡΟΪΟΝΤΑ");
								System.out.println("════════════════════════════════════");
								
								for(Product p : products) {
									printProductDetailsWholesale(p);
									
									System.out.println("\nEισάγετε το κατώτερο όριο άσφαλείας ");
									
									int limit = scannerForInt();
									
									p.setSafetyLimit(limit);
									
									clearConsole();
								}
								
								while(true) {
									System.out.println("\nΤα όρια αποθέματος ενημερώθηκαν επιτυχώς.");
									System.out.println("Εισάγετε 0 για επιστροφή στο κεντρικό μενού");
									
									int exit = scannerForInt();
									
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
										
										int exit = scannerForInt();
										
										if(exit==0) {
											break;
										}
										
									}
									
									break;
								}
								
								System.out.println("Επιλογή εκκρεμής παραγγελίας προμηθευτή");
								System.out.println("Εισάγετε το Id της παραγγελίας για ολοκλήρωση παραλαβής");
								
								int orderId = scannerForInt();
								
								Order order = searchOrderById(orderId);
								clearConsole();
								
								if(order!=null && order.getOrderType()==OrderType.PURCHASE && order.getStatus()==OrderStatus.PENDING) {
									int input = printOrderList(order);
									
									if(input==1) {
										break;
									}
									
									clearConsole();
									System.out.println("Η παραλαβή ολοκληρώθηκε");
									order.setStatus(OrderStatus.COMPLETED);
																		
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
									
									int exit = scannerForInt();
									
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
										
										int exit = scannerForInt();
										
										if(exit==0) {
											break;
										}
										
									}
									
									break;
								}
								
								System.out.println("Επιλογή εκκρεμής παραγγελίας");
								System.out.println("Εισάγετε το Id της παραγγελίας για προετοιμασία");
								
								int orderId = scannerForInt();
								
								Order order = searchOrderById(orderId);
								clearConsole();
								
								if(order!=null && order.getOrderType()==OrderType.SALES && order.getStatus()==OrderStatus.PROCESSING) {
									int input = printOrderList(order);
									
									if(input==1) {
										break;
									}
									clearConsole();
									System.out.println("Η παραγγελία με Id "+ orderId +" είναι έτοιμη προς άποστολή");
									order.setStatus(OrderStatus.READY_TO_SHIP);
								}else {
									System.out.println("Η παραγγελία με Id "+ orderId +" δεν βρέθηκε");
								}
								
								while(true) {
									System.out.println("Εισάγετε 0 για επιστροφή στο κεντρικό μενού");
									
									int exit = scannerForInt();
									
									if(exit==0) {
										break;
									}
								}
								
								break;
							}
							
							break;
						case 4:
							System.out.println("Έξοδος από το σύστημα...");
							break exitLabel;
					}
				}
			}
			case "cashier_user":{
				inner :
				while(true) {
					clearConsole();
					int choice1 = menuForCahier();
					
					if(choice1==1) {
						String afm = null;
						Customer customer = null;
		
						while(true) {
							clearConsole();
							
							afm = newRetailSale();
							
							if(afm.equals("0")) {
								continue inner;
							}
							
							customer = searchCustomerByAFM(afm);
							if(customer!=null) {
								break;
							}
						}
						
						clearConsole();
						
						boolean isNewOrder = true;
						total = 0;
						flag = false;
						Order orderNew = null;
						while(true) {
							System.out.println("Έκδοση Απόδειξης Λιανικής → 0 ");
							System.out.println("Eισάγετε -1 για έξοδο");
							System.out.println("\nEισάγετε τον κωδικό του προϊόντος προς προσθήκη");
							System.out.print("Κωδικός προϊόντος: ");
							int productCode = scannerForInt();
							
							if(productCode==0) {
								if(total<=0) {
									clearConsole();
									System.out.println("Παρακαλώ επιλέξτε προίοντα\n");
									continue;
								}else {
									clearConsole();
									flag = true;
									orderNew.setTotalAmount(total);
									System.out.println("Επιτυχής Έκδοση Απόδειξης Λιανικής\n");
									
									break;
								}
							}else if(productCode==-1) {
								break;
							}
							
							Product product = searchProductCode(productCode);
							
							if(product!=null) {
								clearConsole();
								printProductDetailsRetail(product);
								System.out.println("Ποσότητα: ");
								
								int quantity = scannerForInt();
								
								if(isNewOrder) {
									orderNew = new Order(LocalDate.now().toString(),OrderType.RETAIL);
									orderNew.setIssuedBy(loggedUser);
									orderNew.setCustomer(customer);
									isNewOrder = false;
									orders.add(orderNew);
								}
								
								orderNew.addProduct(product, quantity);
								
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
					}else if(choice1==2){
						break exitLabel;
					}
				}
			}	
			case "sales_user":{ 
				
				while(true) {
					
					if(!flag) {
						clearConsole();
					}

					int choice2 = menuForSales();
					
					clearConsole();
					
					switch(choice2) {
						case 1: 
							total = 0;
							flag = false;
							boolean isNewOrder = true;
							
							while(true) {
								clearConsole();
								String afm = newWholesaleSale();
								
								if(!afm.equals("0")) {
									Pharmacy searchedPharmacy = searchPharmacy(afm);
									
									clearConsole();
									printPharmacyDetails(searchedPharmacy,afm);
										
									if(searchedPharmacy!=null) {
										Order orderNew = null;
										while(true) {
											
											printForProductInput();
											int productCode = scannerForInt();
											
											if(productCode==0) {
												if(total<=0) {
													clearConsole();
													System.out.println("Παρακαλώ επιλέξτε προίοντα\n");
													continue;
												}else {
													clearConsole();
													System.out.println("Επιτυχής Έκδοση Τιμολογίου\n");	
													invoices.add(new Invoice(orderNew.getOrderID(), orderNew.getDate().toString(), orderNew.getTotalAmount()));													
													break;
												}
											}else if(productCode==-1) {
												break;
											}
											
											Product product = searchProductCode(productCode);
											
											if(product!=null) {
												clearConsole();
												printProductDetailsWholesale(product);
												
												System.out.println("Ποσότητα: ");
												int quantity = scannerForInt();
												
												if(isNewOrder) {
													orderNew = new Order(LocalDate.now().toString(),OrderType.SALES);
													orderNew.setIssuedBy(loggedUser);
													Customer customer = searchCustomerByAFM(afm);
													if(customer!=null) {
														orderNew.setCustomer(customer);
													}
													isNewOrder = false;
													orders.add(orderNew);
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
						
						case 2: System.out.println("Έξοδος από το σύστημα...");
							break exitLabel;
						default:
							System.out.println("Μη έγκυρη επιλογή!\n");
							menuForSales();
					}
				}
			}	
			case "service_user":{
				
				while(true) {
					clearConsole();
					
					int choice3 = menuForSearch();
					
					if(choice3==2) {
						break;
					}
					clearConsole();

					while(true){
						System.out.println("Eισάγετε για έξοδο → 0");
						System.out.println("\nEισάγετε τον κωδικό του προϊόντος προς αναζήτηση");
						System.out.print("Κωδικός προϊόντος: ");
						int productCode = scannerForInt();
						
						if(productCode==0) {
							break exitLabel;
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
				
			}	break;
		}
		
		scanner.close();
		
		System.out.println("════════════════════════════════════");
		System.out.println("            ΠΡΟΪΟΝΤΑ");
		System.out.println("════════════════════════════════════");
		
		for(Product p : products) {
			p.printData();
		}
		System.out.println();
		System.out.println();
		System.out.println("════════════════════════════════════");
		System.out.println("            ΠΕΛΑΤΕΣ");
		System.out.println("════════════════════════════════════");
		
		for(Customer c : customers) {
			c.printData();
		}
		
		System.out.println();
		System.out.println();
		System.out.println("════════════════════════════════════");
		System.out.println("            ΥΠΑΛΛΥΛΟΙ");
		System.out.println("════════════════════════════════════");
		
		for(Employee e : employees) {
			e.printData();
		}
		
		System.out.println();
		System.out.println();
		System.out.println("════════════════════════════════════");
		System.out.println("            ΠΑΡΑΓΓΕΛΙΕΣ");
		System.out.println("════════════════════════════════════");
		
		for(Order o : orders) {
			o.printData();
		}
		
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

			for(Employee u : employees) {

			    if(u.equals(new Employee(username, password))) {
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
		
		return scannerForInt(); 
	}
	
	
	
	private static int menuForSearch() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("         ΚΕΝΤΡΙΚΟ ΜΕΝΟΥ");
		System.out.println("════════════════════════════════════");
		System.out.println("1. Αναζήτηση Προϊόντων");
		System.out.println("2. Έξοδος");
		
		return scannerForInt();
	}
	
	
	
	private static int menuForSales() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("         ΚΕΝΤΡΙΚΟ ΜΕΝΟΥ");
		System.out.println("════════════════════════════════════");
		System.out.println("1. Νέα Πώληση Χονδρικής");
		System.out.println("2. Έξοδος");
		System.out.println("════════════════════════════════════");
		System.out.print("Επιλέξτε ενέργεια: ");
		
		return scannerForInt();
	}
	
	
	
	private static int menuForCahier() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("         ΚΕΝΤΡΙΚΟ ΜΕΝΟΥ");
		System.out.println("════════════════════════════════════");
		System.out.println("1. Νέα Πώληση Λιανικής");
		System.out.println("2. Έξοδος");
		System.out.println("════════════════════════════════════");
		System.out.print("Επιλέξτε ενέργεια: ");
		
		return scannerForInt();
	}
	
	
	
	private static String newWholesaleSale() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("      ΝΕΑ ΧΟΝΔΡΙΚΗ ΠΩΛΗΣΗ");
		System.out.println("════════════════════════════════════");
		System.out.println("Αναζήτηση Πελάτη - Φαρμακείου");
		System.out.println("------------------------------------");
		System.out.print("Εισάγετε ΑΦΜ Φαρμακείου (Κεντρικό μενού → 0): ");
		
		return scanner.nextLine();

	}
	
	
	
	private static String newRetailSale() {
		System.out.println("\n════════════════════════════════════");
		System.out.println("      ΝΕΑ ΛΙΑΝΙΚΗ ΠΩΛΗΣΗ");
		System.out.println("════════════════════════════════════");
		System.out.println("Αναζήτηση Πελάτη");
		System.out.println("------------------------------------");
		System.out.print("Εισάγετε ΑΦΜ Πελάτη (Κεντρικό μενού → 0): ");
		
		return scanner.nextLine();

	}
	
	
	
	private static Pharmacy searchPharmacy(String afm) {
		for(Pharmacy ph : pharmacies) {

	    if(ph.getAFM().equals(afm)) {
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
	
	
	
	private static Customer searchCustomerByAFM(String afm){
		for(Customer c : customers) {
			if(afm.equals(c.getAFM())) {
				return c;
			}
		}
		return null;
	}
	
	
	
	private static void printPharmacyDetails(Pharmacy pharmacy, String afm) {
		
		if(pharmacy!=null) {
			System.out.println("\n════════════════════════════════════");
			System.out.println("     ΣΤΟΙΧΕΙΑ ΦΑΡΜΑΚΕΙΟΥ");
			System.out.println("════════════════════════════════════");
			System.out.println("Όνομα        : " + pharmacy.getName());
			System.out.println("ΑΦΜ          : " + pharmacy.getAFM());
			System.out.println("ΔΟΥ          : " + pharmacy.getDOY());
			System.out.println("════════════════════════════════════");
		
		}else {
			clearConsole();
			System.out.println("Δεν βρεθηκε φαρμακείο με ΑΦΜ: "+afm);
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
	    
	    return scannerForInt();
	}
	
	
	
	private static void printForProductInput(){
		System.out.println("Οριστικοποίηση και Έκδοση Τιμολογίου → 0");
		System.out.println("Εισάγετε -1 για έξοδο");
		System.out.println("\nEισάγετε τον κωδικό του προϊόντος προς προσθήκη");
		System.out.print("Κωδικός προϊόντος: ");
	}

	
	
	private static int printOrdersFromSupplier() {
		int i = 0;
		for(Order o : orders) {
			if(o.getOrderType()==OrderType.PURCHASE && o.getStatus()==OrderStatus.PENDING) {
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
			if(o.getOrderType()==OrderType.SALES && o.getStatus()==OrderStatus.PROCESSING) {
				i++;
				System.out.println(i+ ") Id: " + o.getOrderID());
				System.out.printf("   Total: %.2f€\n", o.getTotalAmount());
				System.out.println("   Date: " + o.getDate());
				System.out.println("   Name: "+ o.getCustomer());
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
		boolean orderCreated = true;
		Order orderNew = null;
		
		for(Product p : products) {
		
			if(productAlreadyOrdered(p)) {
				continue;
			}
			
			if(p.getStockLevel()<p.getSafetyLimit()) {
				
				if(orderCreated) {
					
					orderNew = new Order(LocalDate.now().toString(),OrderType.PURCHASE);
					orders.add(orderNew);
					orderCreated = false;
				}
				int quantity = p.getSafetyLimit() - p.getStockLevel() + 10;
				orderNew.addProduct(p, quantity);
			}
		}
		
		if(!orderCreated) {
			invoices.add(new Invoice(orderNew.getOrderID(), orderNew.getDate().toString(), orderNew.getTotalAmount()));
		}
		
	}
	
	
	
	private static boolean productAlreadyOrdered(Product product) {

	    for(Order order : orders) {

	        if(order.getOrderType() == OrderType.PURCHASE &&
	           order.getStatus() == OrderStatus.PENDING) {

	            if(order.getProducts().containsKey(product)) {
	                return true;
	            }
	        }
	    }

	    return false;
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
	
	
	
	private static int scannerForInt() {

	    while(true) {

	        try {
	            int number = scanner.nextInt();
	            scanner.nextLine();

	            return number;

	        } catch(Exception e) {

	            scanner.nextLine();

	            System.out.println("Παρακαλώ εισάγετε έγκυρο ακέραιο αριθμό");
	            System.out.print("Εισαγωγή: ");
	        }
	    }
	}
	
	
	
	private static void clearConsole() {
	    for(int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}

	

}
