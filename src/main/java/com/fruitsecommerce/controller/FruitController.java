package com.fruitsecommerce.controller;


import java.util.Scanner;

import java.util.logging.Logger;



import com.fruitsecommerce.model.Fruit;
import com.fruitsecommerce.service.FruitService;





public class FruitController {
	//static FruitService fruitService = new FruitService();
	public static final Logger logger = Logger.getLogger("FruitController");
	Scanner sc = new Scanner(System.in);
 public void addfruit() {
	 Fruit f = new Fruit();
		logger.info("Enter Fruit Details Below");
		logger.info("Enter FruitName");
		f.setName(sc.next());
		logger.info("Enter Fruit price");
		f.setPrice(sc.nextDouble());
		logger.info("Enter Quantity");
		f.setQuantity(sc.nextInt());

		int res = FruitService.getInstance().addFruit(f);
		if (res == 1)
			logger.info("Successfully Added");
		else
			logger.info("Failed to add in DB");
 }
 public void deletefruit() {
	 int ret = 0;
		logger.info("Enter FruitId To Delete");
		int fruitId = sc.nextInt();
		ret = FruitService.getInstance().deletefruit(fruitId);
		if (ret == 1)
			logger.info("Successfully Deleted");
		else
			logger.info("Failed to delete");
 }
 public void updatefruit() {
	 int ret=0;
	 logger.info("Enter Fruit details to Update below");

		logger.info("Enter Fruit Id");
		int fid = sc.nextInt();
		logger.info("Enter Fruit name");
		String fname = sc.next();
		logger.info("Enter Price of the Fruit");
		double fPrice = sc.nextDouble();
		logger.info("Enter Quantity of that Fruit");
		int fQuant = sc.nextInt();

		Fruit fruit = new Fruit(fid, fname, fPrice, fQuant);
		ret = FruitService.getInstance().updateFruit(fruit);
		if (ret == 1) {
			logger.info("Successfully Updated");
		} else {
			logger.info("Update Failed .. Please try again");
		}

 }

}
