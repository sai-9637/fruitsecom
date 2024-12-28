package com.fruitsecommerce.model;

public class Cart {
	private int id;
	private int customerId;
	private int bookid;
	private int quantity;
	private double bill;
	public Cart(int id, int customerId, int bookid, int quantity, double bill) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.bookid = bookid;
		this.quantity = quantity;
		this.bill = bill;
	}
	public Cart(int customerId, int bookid, int quantity, double bill) {
		super();
		this.customerId = customerId;
		this.bookid = bookid;
		this.quantity = quantity;
		this.bill = bill;
	}
	public Cart() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", customerId=" + customerId + ", bookid=" + bookid + ", quantity=" + quantity
				+ ", bill=" + bill + "]";
	}
	

}
