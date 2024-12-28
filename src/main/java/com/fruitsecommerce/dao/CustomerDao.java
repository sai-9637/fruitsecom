package com.fruitsecommerce.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fruitsecommerce.model.Customer;

public class CustomerDao {
	public static final Logger logger = LogManager.getLogger(CustomerDao.class);
	private static CustomerDao customerdao=null;
	private CustomerDao() {}
	public static CustomerDao getInstance() {
		if(customerdao==null) {
			customerdao=new CustomerDao();
		}
		return customerdao;
	}
	Connection conn=MySqlConn.getCon();
	PreparedStatement pss = null;
	public int createCustomer(Customer c) {
	    int status = 0;
	    try 
	        ( PreparedStatement ps= conn.prepareStatement("SELECT * FROM customer WHERE email = ?")){
	        ps.setString(1, c.getEmail());
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            
	            logger.info("Email already exists just try to login");
	            return status;
	        } else {
	            
	            pss = conn.prepareStatement("INSERT INTO customer (name, contact, address, email, password) VALUES (?, ?, ?, ?, ?)");
	            pss.setString(1, c.getName());
	            pss.setString(2, c.getContact());
	            pss.setString(3, c.getAddress());
	            pss.setString(4, c.getEmail());
	            pss.setString(5, c.getPassword());
	            status = pss.executeUpdate();
	        }
	    } catch (Exception e) {
	        logger.info(e);
	    }
	    
	    
	    return status;
	}

	 public Customer getCustomer(int id) {
		    try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer WHERE customerid = ?")) {
		      
		      stmt.setInt(1, id);
		      ResultSet rs = stmt.executeQuery();
		      if (rs.next()) {
		        return new Customer(rs.getInt("customerid"), rs.getString("name"), rs.getString("contact"),rs.getString("address"), rs.getString("email"),rs.getString("password"),"user");
		      }
		    } catch (SQLException e) {
		      logger.info("Error getting customer: " + e.getMessage());
		    }
		    return null;
		  }
	 
	 public List<Customer> getAllCustomers() {
			List<Customer> customers = new ArrayList<>();
			try (Statement stmt = conn.createStatement()) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
				while (rs.next()) {
					customers.add(new Customer(rs.getInt("customerid"), rs.getString("name"), rs.getString("contact"),
							rs.getString("address"), rs.getString("email"), rs.getString("password"), "user"));
				}
			} catch (SQLException e) {
				logger.info("Error getting all customers: " + e.getMessage());
			}
			return customers;
		}

}
