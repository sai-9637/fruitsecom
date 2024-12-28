package com.fruitsecommerce.service;


import java.util.List;

import com.fruitsecommerce.dao.CustomerDao;
import com.fruitsecommerce.model.Customer;



public class CustomerService {
	private static CustomerService customerservice=null;
	private CustomerService  () {}
	public static CustomerService  getInstance() {
		if(customerservice==null) {
			customerservice=new CustomerService();
		}
		return customerservice;
	}
	//private CustomerDao dao=new CustomerDao();
	public int createCustomer(Customer c) {
		return CustomerDao.getInstance().createCustomer(c);
	}
	 public Customer getCustomer(int id) {
		 return CustomerDao.getInstance().getCustomer(id);
		 
	 }
	 public List<Customer> getAllCustomers(){
		 return CustomerDao.getInstance().getAllCustomers();
				 
	 }

}
