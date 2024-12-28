package com.fruitsecommerce.controller;


import java.util.Scanner;
import java.util.logging.Logger;

import com.fruitsecommerce.Main;
import com.fruitsecommerce.service.LoginService;
import com.fruitsecommerce.model.Customer;

public class LoginController {

	public static final Logger logger = Logger.getLogger("LoginController");
	Scanner sc = new Scanner(System.in);

	static LoginService loginService = new LoginService();

	boolean isLoggedin = false;
	boolean exit = false;
	Customer c = null;
	  
	  

		public void  login() {
			Main m=new Main();
		    
			logger.info("Enter email:");
			String uname = sc.next();
			logger.info("Enter Password:");
			String pwd = sc.next();
			c=loginService.login(uname, pwd);
			if ( c != null && c.getType().equals("user")) {
				 m.user(c);
			}
			
				else if(c != null && c.getType().equals("admin")) {
			  m.admin();
				}
			else {
				logger.info("Invalid details");
				m.printLoginMenu();
			}
	 
		}}
