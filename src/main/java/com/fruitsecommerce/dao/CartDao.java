package com.fruitsecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fruitsecommerce.model.Cart;
import com.fruitsecommerce.model.Customer;
import com.fruitsecommerce.model.Fruit;

public class CartDao {
	private static CartDao cartdao=null;
	private CartDao () {}
	public static CartDao getInstance() {
		if(cartdao==null) {
			cartdao=new CartDao();
		}
		return cartdao;
	}
	private Connection conn = MySqlConn.getCon();
	//CustomerDao customerDao = new CustomerDao();
	//FruitDao fruitDao = new FruitDao();

    

    public int addToCart(int customerId, int fruitId, int quantity) {
        int result = 0;
        Customer c = CustomerDao.getInstance().getCustomer(customerId);
        Fruit f=FruitDao.getInstance().getFruit(fruitId);
        if(c != null && f != null && f.getQuantity() >= quantity) {
        try(PreparedStatement preparedStatement = conn
                .prepareStatement("INSERT INTO cart (customerId,fruitId, quantity,bill) VALUES (?,?,?,?)")) {
            
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, fruitId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, f.getPrice()*quantity);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;}
        else {
        	return 0;
        }
    }


    public int removeFromCart(int fruitId,int customerId) {
        int result = 0;
        try(PreparedStatement preparedStatement = conn
                .prepareStatement("DELETE FROM cart WHERE fruitId=? and customerId=?")) {
            
            preparedStatement.setInt(1, fruitId);
            preparedStatement.setInt(2, customerId);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int removeFromCart(int cartId) {
        int result = 0;
        try(PreparedStatement preparedStatement = conn
                .prepareStatement("DELETE FROM cart WHERE id = ?")) {
            
            preparedStatement.setInt(1, cartId);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Cart> getCartItems(int customerId) {
        ArrayList<Cart> cartItems = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cart WHERE customerid = ?")){
        	
		    stmt.setInt(1, customerId);
		    ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cart cartItem = new Cart();
                cartItem.setId(rs.getInt("id"));
                cartItem.setBookid(rs.getInt("fruitId"));
                cartItem.setCustomerId(rs.getInt("customerId"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setBill(rs.getDouble("bill"));
                
                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }
}
