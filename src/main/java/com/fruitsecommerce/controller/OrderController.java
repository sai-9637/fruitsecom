package com.fruitsecommerce.controller;



import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.fruitsecommerce.model.Cart;
import com.fruitsecommerce.model.Order;
import com.fruitsecommerce.service.CartService;
import com.fruitsecommerce.service.FruitService;
import com.fruitsecommerce.service.OrderService;




public class OrderController {
	//static OrderService orderService = new OrderService();
	//static FruitService fruitService = new FruitService();
	public static final Logger logger = Logger.getLogger("OrderController");
	Scanner sc = new Scanner(System.in);
//	public void createOrder(int id){
//	double totalBill = 0; 
//	List<Cart> cartList = CartService.getInstance().getCartItems(id);
//	for (Cart cart : cartList) {
//	 logger.log(Level.INFO, "{0}", cart);
//		totalBill += cart.getBill();
//	}
//
//		 logger.log(Level.INFO, "Total Bill: {0}", totalBill);
//
//		 logger.info("Do you have a voucher code? Yes(y) or No(n)");
//		 String applyVoucher = sc.next();
//		 if (applyVoucher.equals("y")) {
//		   logger.info("Enter voucher code:");
//		   String voucherCode = sc.next();
//		   if (voucherCode.equals("DISCOUNT10")) {
//		       double discountAmount = 0.1 * totalBill;
//		       totalBill -= discountAmount;
//		       logger.log(Level.INFO, "Discount Applied: {0}", discountAmount);
//		 } else {
//		   logger.info("Invalid voucher code. No discount applied.");}
//		}
//
//	     logger.log(Level.INFO, "Final Bill: {0}", totalBill);
//
//		 logger.info("Do you want to proceed? Yes(y) or No(n)");
//		 String proceed = sc.next();
//
//		 if (proceed.equals("y")) {
//		    logger.info("Select Payment Mode?\n1) UPI\n2) COD");
//		    int k = sc.nextInt();
//		    String paymode = null;
//		    String status = null;
//
//		if (k == 1) {
//		paymode = "UPI";
//		 status = "Paid";
//		 } else {
//		 paymode = "COD";
//		status = "pending";
//	}
//
//	 cartList = CartService.getInstance().getCartItems(id);
//	 Order order = null;
//
//	 for (Cart cart : cartList) {
//	     order = new Order(cart.getCustomerId(), cart.getBookid(), cart.getQuantity(),
//		 cart.getBill(), paymode, status);
//		 OrderService.getInstance().createOrder(order);
//		 FruitService.getInstance().updateFruit(cart.getBookid(), cart.getQuantity());
//		 CartService.getInstance().removeFromCart(cart.getId());
//		}
//
//		 logger.info("Order Placed");
//	 } else {
//	 logger.info("Please come again!");
//	 }
//	}
 public void getOrder(int id) {
		 logger.info("Order History \n");
			List<Order> orderList = OrderService.getInstance().getOrder(id);
			logger.info("Fruit List:");
			if (!orderList.isEmpty()) {
				for (Order o : orderList) {
					logger.log(Level.INFO, "{0}", o);
				}
			} else {
				logger.info("No orders yet");
			}

			logger.info("\n\n");
		 
	 }
	public void createOrder(int id) {
	    double totalBill = 0;
	    boolean isFirstOrder = true; // Track if it's the customer's first order
	    List<Cart> cartList = CartService.getInstance().getCartItems(id);
	    for (Cart cart : cartList) {
	        logger.log(Level.INFO, "{0}", cart);
	        totalBill += cart.getBill();
	    }

	    logger.log(Level.INFO, "Total Bill: {0}", totalBill);

	    logger.info("Do you have a voucher code? Yes(y) or No(n)");
	    String applyVoucher = sc.next();
	    if (applyVoucher.equals("y")) {
	        logger.info("Enter voucher code:");
	        String voucherCode = sc.next();
	        if (voucherCode.equals("DISCOUNT10")) {
	            double discountAmount = 0.1 * totalBill;
	            totalBill -= discountAmount;
	            logger.log(Level.INFO, "Discount Applied: {0}", discountAmount);
	        } else {
	            logger.info("Invalid voucher code. No discount applied.");
	        }
	    }

	    if (isFirstOrder) { // Apply 50% discount for the first order
	        totalBill *= 0.5; // Apply 50% discount
	        logger.log(Level.INFO, "50% Discount Applied to First Order. New Total Bill: {0}", totalBill);
	        isFirstOrder = false; // Set isFirstOrder to false after applying the discount
	    }

	    logger.log(Level.INFO, "Final Bill: {0}", totalBill);

	    logger.info("Do you want to proceed? Yes(y) or No(n)");
	    String proceed = sc.next();

	    if (proceed.equals("y")) {
	        logger.info("Select Payment Mode?\n1) UPI\n2) COD");
	        int k = sc.nextInt();
	        String paymode = null;
	        String status = null;

	        if (k == 1) {
	            paymode = "UPI";
	            status = "Paid";
	        } else {
	            paymode = "COD";
	            status = "pending";
	        }
	        cartList = CartService.getInstance().getCartItems(id);
	   	 Order order = null;

	   	 for (Cart cart : cartList) {
	   	     order = new Order(cart.getCustomerId(), cart.getBookid(), cart.getQuantity(),
	   		 cart.getBill(), paymode, status);
	   		 OrderService.getInstance().createOrder(order);
	   		 FruitService.getInstance().updateFruit(cart.getBookid(), cart.getQuantity());
	   		 CartService.getInstance().removeFromCart(cart.getId());
	   		}

	   		 logger.info("Order Placed");
	   	 } else {
	   	 logger.info("Please come again!");
	   	 }
	    }
	

	 
	 public void getAllOrders() {
		    logger.info("All Orders History\n");
		    List<Order> orderList = OrderService.getInstance().getAllOrders();
		    logger.info("Order List:");
		    if (!orderList.isEmpty()) {
		        for (Order o : orderList) {
		            logger.log(Level.INFO, "{0}", o);
		        }
		    } else {
		        logger.info("No orders yet");
		    }

		    logger.info("\n\n");
		}
	 public void getOrdersByCustomerId(int customerId) {
		    List<Order> orderList = OrderService.getInstance().getOrdersByCustomerId(customerId);
		    if (orderList.isEmpty()) {
		     
		    	logger.log(Level.INFO, "no orders for customerid {0}", customerId);
		    } else {
		      
		    	
		    	logger.log(Level.INFO,"orders for customerid {0}", customerId);
		    
		        
		        for (Order order : orderList) {
		          
		        	logger.log(Level.INFO, "{0}", order);
		        }
}	 }
}
