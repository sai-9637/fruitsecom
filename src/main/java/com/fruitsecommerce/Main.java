
package com.fruitsecommerce;

import com.fruitsecommerce.controller.CartController;
import com.fruitsecommerce.controller.CustomerController;
import com.fruitsecommerce.controller.FruitController;
import com.fruitsecommerce.controller.LoginController;
import com.fruitsecommerce.controller.OrderController;
import com.fruitsecommerce.dao.LoginDao;
import com.fruitsecommerce.model.Customer;
import com.fruitsecommerce.model.Fruit;
import com.fruitsecommerce.service.FruitService;
import com.fruitsecommerce.service.LoginService;

import java.io.InputStream;

import java.util.List;
import java.util.Scanner;

import java.util.logging.LogManager;
import java.util.logging.Logger;



public class Main {
	private static Logger logger = null;
	static LoginDao loginDao = new LoginDao();
	static LoginService loginService=new LoginService();
	static FruitController fcnt=new FruitController();
	static CartController ccnt=new CartController();
	static OrderController ocnt=new OrderController();
	static CustomerController cunt=new CustomerController();
	static LoginController lcnt=new LoginController();
	static Main k=new Main();

	static {
		InputStream stream = Main.class.getClassLoader().getResourceAsStream("logging.properties");
		try {
			LogManager.getLogManager().readConfiguration(stream);
			logger = Logger.getLogger(Main.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void printLoginMenu() {
		logger.info("WELCOME TO FlowersEcommerce");
		logger.info("============================================================= ");
		logger.info("1) Login \n2) SignUp \n3) exit");
		logger.info("============================================================= ");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isLoggedin =false;
		boolean exit = false;

		printLoginMenu();

		do {
			if (!isLoggedin) {
				int opt = sc.nextInt();
				switch (opt) {
				case 1:

					lcnt.login();
					break;
				case 2:
					cunt.createCustomer();	
					printLoginMenu();
					break;
				case 3:
					exit = true;
					break;
				default:
					break;
				}
			}
		}
		while (!exit);
		sc.close();
	}
	public void  user( Customer c) {
		Scanner sc = new Scanner(System.in);

			List<Fruit> al = FruitService.getInstance().getMenu();
			logger.info("Fruit List : ");
			if (!al.isEmpty()) {


		String format = "%-5s %-15s %-10s %-8s%n";
			logger.info(String.format(format, "ID", "Fruit Name", "Price", "Quantity"));
		 al.forEach(fruit -> logger.info(String.format(format,fruit.getId(), fruit.getName(),fruit.getPrice(), fruit.getQuantity())));

				logger.info("\n\n");
				logger.info("1.AddToCart \n2.viewCart \n3.PlaceOrder \n4.Order History \n5.logout");
				logger.info("Enter your choice");
				int ch = sc.nextInt();
				switch (ch) {
				case 1:
					ccnt.addToCart(c.getId());
					k.user(c);
					break;

				case 2:
					ccnt.getCartItems(c.getId());
					k.user(c);
					break;
				case 3:
				    ocnt.createOrder(c.getId());
					k.user(c);
					break;
				case 4:
					ocnt.getOrder(c.getId());
					//ocnt.createOrder(ch);
					k.user(c);
					break;
				case 5:
					printLoginMenu();
					break;

				default:
					break;
				}

			} else {
				logger.info("Empty Fruit List");
			}
	}
	public void admin() {
		Scanner sc = new Scanner(System.in);

		List<Fruit> al = FruitService.getInstance().getMenu();
		logger.info("Fruit List:");
		if (!al.isEmpty()) {

			String format = "%-5s %-15s %-10s %-8s%n";
			logger.info(String.format(format, "ID", "Fruit Name", "Price", "Quantity"));
		 al.forEach(fruit -> logger.info(String.format(format,fruit.getId(), fruit.getName(),fruit.getPrice(), fruit.getQuantity())));
		} else {
			logger.info("Empty Fruit List");
		}
		logger.info("\n\n");
		logger.info(
				" 1.Add Fruit \n 2.Delete Fruit Item By Id \n 3.Update Fruit Item By Id \n 4.list of all customers \n 5.list of all orders \n 6.get orders by customerid \n 7.logout ");
		int opt = sc.nextInt();
		switch (opt) {
		case 1:
			fcnt.addfruit();;
			k.admin();
			break;

		case 2:
			fcnt.deletefruit();
			k.admin();
			break;

		case 3:
			fcnt.updatefruit();
			k.admin();
			break;
		case 4:
			cunt.getAllCustomers();
			logger.info("\n\n");
			k.admin();
			break;
		case 5:
			ocnt.getAllOrders();
			logger.info("\n\n");
			k.admin();
			break;

		case 7:
			printLoginMenu();
			break;
		case 6:
			logger.info("Enter customer ID: ");
		    int customerId = sc.nextInt();
		    ocnt.getOrdersByCustomerId(customerId);
		    logger.info("\n\n");
		    k.admin();
			break;
		default:
			break;
		}
	}
}

