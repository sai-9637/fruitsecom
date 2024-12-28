package com.fruitsecommerce.controller;


import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fruitsecommerce.service.CustomerService;


import com.fruitsecommerce.model.Customer;

public class CustomerController {
	//static CustomerService  customerService = new CustomerService();
	public static final Logger logger = Logger.getLogger("CartController");
	Scanner sc = new Scanner(System.in);
	Customer c = null;
	public void createCustomer() {
		logger.info("Enter name:");
		String name = sc.next();
		logger.info("Enter Contact:");
		String contact = sc.next();
		logger.info("Enter address:");
		String address = sc.next();

		logger.info("Enter email:");
		String email = sc.next();
		logger.info("Enter Password:");
		String pass = sc.next();

		String type = "user";

		c = new Customer(name, contact, address, email, pass, type);

		int res = CustomerService.getInstance().createCustomer(c);
		if (res == 1) {
			logger.info("SuccessFully sign up");
		} else {
			logger.info("sign up failed");
		}
	}
	public void getAllCustomers() {
	    List<Customer> customers = CustomerService.getInstance().getAllCustomers();
	    if (customers.isEmpty()) {
	        logger.info("No customers found.");
	    } else {
	        logger.info("List of customers:");
	        for (Customer customer : customers) {
	           
	            if(customer != null){
	              

		        	logger.log(Level.INFO, "{0}",customer );
	            }
	        }
	    }
	}


}