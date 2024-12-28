package com.fruitsecommerce.service;

import com.fruitsecommerce.model.Customer;
import com.fruitsecommerce.dao.LoginDao;

public class LoginService {
	private LoginDao dao=new LoginDao();
	public Customer login(String email, String pswd) {
		return dao.login(email, pswd);
	}
	public void signUp(String name, String contact,String address,String email, String password) {
		 dao.signUp(name, contact, address, email, password);
	}


}
