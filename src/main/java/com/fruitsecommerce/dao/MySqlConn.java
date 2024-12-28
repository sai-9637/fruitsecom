
package com.fruitsecommerce.dao;


import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
public class MySqlConn {
	private MySqlConn() {
	}
	 public static final Logger logger = LogManager.getLogger(MySqlConn.class);
	  static String password="root";
	 
	 
    public static Connection getCon() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitsecom", "root", password);
            
            
        }
        catch (Exception e) {
            logger.info(e);
        }
        return c;
    }
}
