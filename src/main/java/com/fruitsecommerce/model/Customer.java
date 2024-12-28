package com.fruitsecommerce.model;

public class Customer {
	private int id;
	private String name;
	private String contact;
	private String address;
	private String email;
	private String password;
	private String type;
	public Customer(int id, String name, String contact, String address, String email, String password, String type) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	public Customer(String name, String contact, String address, String email, String password, String type) {
		super();
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	public Customer() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", contact=" + contact + ", address=" + address + ", email="
				+ email + ", password=" + password + ", type=" + type + "]";
	}
	


}
