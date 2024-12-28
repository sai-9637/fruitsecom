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


import com.fruitsecommerce.model.Order;


public class OrderDao {
  public static final Logger logger = LogManager.getLogger(OrderDao.class);
  private Connection conn=MySqlConn.getCon();
  private static OrderDao orderdao=null;
	private OrderDao() {}
	public static OrderDao getInstance() {
		if(orderdao==null) {
			orderdao=new OrderDao();
		}
		return orderdao;
	}
  //private CustomerDao customerDao=new CustomerDao();
  

  
  private static final String CUSTOMERID = "customerId";
  private static final String FRUITID = "fruitId";
  private static final String QUANTITY = "quantity";
  private static final String BILL = "bill";
  private static final String PAYMENTMODE = "paymentmode";
  private static final String STATUS = "status";
  
  
  
  
  
 
  public void createOrder(Order order) {
	  
    String query = "INSERT INTO orders (customerId, fruitId, quantity,bill,paymentmode,status) VALUES (?, ?, ?, ?, ?, ?)";
    
    try(PreparedStatement statement = conn.prepareStatement(query)) {
      
      statement.setInt(1, order.getCustomerId());
      statement.setInt(2, order.getFruitId());
      statement.setInt(3, order.getQuantity());
      statement.setDouble(4, order.getBill());
      statement.setString(5, order.getPaymentmode());
      statement.setString(6, order.getStatus());

      statement.executeUpdate();
 
    
     
      
      
  } catch (SQLException e) {
    logger.info("Error creating order: " + e.getMessage());
  }
  }

  public List<Order> getOrder(int customerId) {
	  ArrayList<Order> orderItems = new ArrayList<>();
    String query = "SELECT * FROM orders WHERE customerId = ? order by id desc";
    
    if(CustomerDao.getInstance().getCustomer(customerId)!=null) {
    try(PreparedStatement statement = conn.prepareStatement(query)) {
      
      statement.setInt(1, customerId);

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
    	  Order orderItem=new Order();
    	 orderItem.setId(resultSet.getInt("id"));
        orderItem.setCustomerId(resultSet.getInt(CUSTOMERID));
        orderItem.setFruitId(resultSet.getInt(FRUITID));
        orderItem.setQuantity(resultSet.getInt(QUANTITY));
        orderItem.setBill(resultSet.getDouble(BILL));
        orderItem.setPaymentmode(resultSet.getString(PAYMENTMODE));
        orderItem.setStatus(resultSet.getString(STATUS));
        
        orderItems.add(orderItem);
    
      }
    } catch (SQLException e) {
      logger.info("Error getting order: " + e.getMessage());
    }
    }
    
    return orderItems;
  }
  public List<Order> getAllOrders() {
      List<Order> orderList = new ArrayList<>();
      String sql = "SELECT * FROM orders";
      try 
         ( Statement stmt = conn.createStatement()){
          ResultSet rs = stmt.executeQuery(sql);
          while (rs.next()) {
              int id = rs.getInt("id");
              int customerId = rs.getInt(CUSTOMERID);
              int fruitId = rs.getInt(FRUITID);
              int quantity = rs.getInt(QUANTITY);
              double bill = rs.getDouble(BILL);
              String paymode = rs.getString(PAYMENTMODE);
              String status = rs.getString(STATUS);
              Order order = new Order(id, customerId, fruitId, quantity, bill, paymode, status);
              orderList.add(order);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return orderList;
  }
  public List<Order> getOrdersByCustomerId(int customerId) {
	    List<Order> orders = new ArrayList<>();
	    String query = "SELECT * FROM orders WHERE customerId = ?";
	    
	    try (PreparedStatement statement = conn.prepareStatement(query)) {
	        statement.setInt(1, customerId);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            Order order = new Order();
	            order.setId(resultSet.getInt("id"));
	            order.setCustomerId(resultSet.getInt(CUSTOMERID));
	            order.setFruitId(resultSet.getInt(FRUITID));
	            order.setQuantity(resultSet.getInt(QUANTITY));
	            order.setBill(resultSet.getDouble(BILL));
	            order.setPaymentmode(resultSet.getString(PAYMENTMODE));
	            order.setStatus(resultSet.getString(STATUS));
	            orders.add(order);
	        }
	    } catch (SQLException e) {
	        logger.error("Error getting orders by customer ID: " + e.getMessage());
	    }
	    
	    return orders;
	}


}
