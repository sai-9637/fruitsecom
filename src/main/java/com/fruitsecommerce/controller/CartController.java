package com.fruitsecommerce.controller;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fruitsecommerce.service.CartService;
import com.fruitsecommerce.model.Cart;



public class CartController {
	//static CartService cartService = new CartService();
	public static final Logger logger = Logger.getLogger("CartController");
	Scanner sc = new Scanner(System.in);
	public void addToCart(int id) {
		logger.info("Enter Fruit Id");
		int fid = sc.nextInt();
		logger.info("Enter Quantiy");
		int quant = sc.nextInt();

		int r = CartService.getInstance().addToCart(id, fid, quant);
		if (r == 1) {
			logger.info("Added Successfully");
		} else {
			logger.info("Failed to add to cart");
		}

	}
	public void getCartItems(int id) {
		List<Cart> cartList = CartService.getInstance().getCartItems(id);
		for (Cart cart : cartList) {
			logger.log(Level.INFO, "{0}", cart);
		}
	}

}
