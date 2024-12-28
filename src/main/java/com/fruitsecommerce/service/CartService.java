package com.fruitsecommerce.service;


import java.util.List;

import com.fruitsecommerce.dao.CartDao;
import com.fruitsecommerce.model.Cart;



public class CartService {
	private static CartService cartservice=null;
	private CartService () {}
	public static CartService getInstance() {
		if(cartservice==null) {
			cartservice=new CartService();
		}
		return cartservice;
	}

	 public int addToCart(int customerId, int fruitId, int quantity) {
		 return CartDao.getInstance().addToCart(customerId, fruitId, quantity);
		 }
	 public List<Cart> getCartItems(int customerId) {
		 return CartDao.getInstance().getCartItems(customerId);
	 }
	 public int removeFromCart(int cartId) {
		 return CartDao.getInstance().removeFromCart(cartId);
	 }
	 public int removeFromCart(int fruitId,int customerId) {
		 return CartDao.getInstance().removeFromCart(fruitId, customerId);
	 }

}
