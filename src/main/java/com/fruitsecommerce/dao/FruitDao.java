package com.fruitsecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.fruitsecommerce.model.Fruit;

public class FruitDao {
	private static FruitDao fruitdao=null;
	private FruitDao() {}
	public static FruitDao getInstance() {
		if(fruitdao==null) {
			fruitdao=new FruitDao();
		}
		return fruitdao;
	}

  private Connection conn = MySqlConn.getCon();
  public static final Logger logger = LogManager.getLogger(FruitDao.class);
  PreparedStatement insertStmt  = null;

  public int addFruit(Fruit fruit) {
		int res = 0;
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fruit WHERE name = ?")) {
			stmt.setString(1, fruit.getName());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				logger.info("Fruit already exists in DB");
				res = 0;
			} else {
				    insertStmt = conn.prepareStatement("INSERT INTO fruit (name, price, quantity) VALUES (?, ?, ?)") ;
					insertStmt.setString(1, fruit.getName());
					insertStmt.setDouble(2, fruit.getPrice());
					insertStmt.setInt(3, fruit.getQuantity());
					res = insertStmt.executeUpdate();
			}
				} catch (SQLException e) {
					logger.info("Error adding fruit: " + e.getMessage());
				}
		return res;
	}
 


  public Fruit getFruit(int id) {
    try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fruit WHERE fruitid = ?")) {
      
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new Fruit(rs.getInt("fruitid"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"));
      }
    } catch (SQLException e) {
      logger.info("Error getting fruit: " + e.getMessage());
    }
    return null;
  }

  public int updateFruit(Fruit fruit) {
    try(PreparedStatement stmt = conn.prepareStatement("UPDATE fruit SET name = ?, price = ?, quantity = ? WHERE fruitid = ?")) {
      
      stmt.setString(1, fruit.getName());
      stmt.setDouble(2, fruit.getPrice());
      stmt.setInt(3, fruit.getQuantity());
      stmt.setInt(4, fruit.getId());
      return stmt.executeUpdate();
    } catch (SQLException e) {
      logger.info("Error updating fruit: " + e.getMessage());
    }
    return 0;
  }
  public int updateFruit(int fruitid,int quantity) {
	    try(PreparedStatement stmt = conn.prepareStatement("UPDATE fruit SET quantity = (quantity - ?) WHERE fruitid = ?")) {
	      
	      
	      stmt.setInt(1, quantity);
	      stmt.setInt(2, fruitid);
	      return stmt.executeUpdate();
	    } catch (SQLException e) {
	      logger.info("Error updating fruit: " + e.getMessage());
	    }
	    return 0;
	  }

  public int deletefruit(int id) {
	  try( PreparedStatement stmt = conn.prepareStatement("update fruit set status = 0 where fruitid = ?")) {
	  stmt.setInt(1, id);
	  return stmt.executeUpdate();
	  } catch (SQLException e) {
	 logger.info("Error deleting fruit: " + e.getMessage());
	  }
	  return 0;
	  }
  public List<Fruit> getMenu(){
	  ArrayList<Fruit> al = new ArrayList<>();
	  try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM  fruit where status = 1 ")) {
		  
		  ResultSet rs = stmt.executeQuery();
		  while(rs.next()) {
			  Fruit fruit = new Fruit();
			  fruit.setId(rs.getInt("fruitid"));
			  fruit.setName(rs.getString("name"));
			  fruit.setPrice(rs.getDouble("price"));
			  fruit.setQuantity(rs.getInt("quantity"));
			  al.add(fruit);
		  }
	  }
	  catch (SQLException e) {
	      logger.info("Error getting customer: " + e.getMessage());
	    }
	  return al;
  }
}
