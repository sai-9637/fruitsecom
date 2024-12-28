package com.fruitsecommerce.model;

public class Order {
	private int id;
	private int customerId;
	private int fruitId;
	private int quantity;
	private double bill;
	private String paymentmode;
	private String status;
	
	public Order() {
		super();
	}
	public Order(int customerId, int fruitId, int quantity, double bill, String paymentmode, String status) {
		super();
		this.customerId = customerId;
		this.fruitId = fruitId;
		this.quantity = quantity;
		this.bill = bill;
		this.paymentmode = paymentmode;
		this.status = status;
	}
	public Order(int id, int customerId, int fruitId, int quantity, double bill, String paymentmode, String status) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.fruitId = fruitId;
		this.quantity = quantity;
		this.bill = bill;
		this.paymentmode = paymentmode;
		this.status = status;
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
	public int getFruitId() {
		return fruitId;
	}
	public void setFruitId(int fruitId) {
		this.fruitId = fruitId;
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
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", fruitId=" + fruitId + ", quantity=" + quantity
				+ ", bill=" + bill + ", paymentmode=" + paymentmode + ", status=" + status + "]";
	}


}
