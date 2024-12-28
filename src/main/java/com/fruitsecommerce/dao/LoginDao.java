package com.fruitsecommerce.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fruitsecommerce.model.Customer;

public class LoginDao {
	Connection conn=MySqlConn.getCon();
	String admin="admin";
	public static final Logger logger = LogManager.getLogger(LoginDao.class);
  public Customer login(String email, String pswd) {
	  if(email.equals(admin) && pswd.equals(admin)) {
		  return new Customer(0,admin,"7037394910","India",admin,admin,admin);
		  
	  }
	  
    String sql = "SELECT * FROM customer WHERE email = ? and password = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, email);
      statement.setString(2, pswd);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        return new Customer(rs.getInt("customerid"), rs.getString("name"), rs.getString("contact"),rs.getString("address"), rs.getString("email"),rs.getString("password"),"user");
      }
      else {
          // Credentials don't match
          logger.info("Login failed for email:Credintilas didn't match for " + email);
          return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();

    }
    return null;
  }
  
 
  public void signUp(String name, String contact, String address, String email, String password) {
	    String sql = "SELECT COUNT(*) FROM customer WHERE email = ?";
	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setString(1, email);
	        ResultSet resultSet = statement.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1);
	        if (count > 0) {
	            logger.info("User with email " + email + " already exists");
	            return;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    sql = "INSERT INTO customer (name, contact, address, email, password) values (?, ?, ?, ?, ?)";
	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setString(1, name);
	        statement.setString(2, contact);
	        statement.setString(3, address);
	        statement.setString(4, email);
	        statement.setString(5, password);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

  
}
