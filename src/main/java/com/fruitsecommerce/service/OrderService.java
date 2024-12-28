package com.fruitsecommerce.service;


import java.util.List;

import com.fruitsecommerce.dao.OrderDao;
import com.fruitsecommerce.model.Order;



public class OrderService {
	private static OrderService orderservice=null;
	private OrderService () {}
	public static OrderService getInstance() {
		if(orderservice==null) {
			orderservice=new OrderService();
		}
		return orderservice;
	}
	//private OrderDao dao=new OrderDao();
	public void createOrder(Order order) {
		OrderDao.getInstance().createOrder(order);
	}
	public List<Order> getOrder(int customerId){
		return OrderDao.getInstance().getOrder(customerId);
	}
	public List<Order> getAllOrders(){
		return OrderDao.getInstance().getAllOrders();
	}
	 public List<Order> getOrdersByCustomerId(int customerId){
		 return OrderDao.getInstance().getOrdersByCustomerId(customerId);
		 
	 }
	 

		 


}
