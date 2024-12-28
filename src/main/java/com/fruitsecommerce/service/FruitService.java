package com.fruitsecommerce.service;


import java.util.List;

import com.fruitsecommerce.dao.FruitDao;
import com.fruitsecommerce.model.Fruit;



public class FruitService {
	private static FruitService fruitservice=null;
	private FruitService  () {}
	public static FruitService  getInstance() {
		if(fruitservice==null) {
			fruitservice=new FruitService();
		}
		return fruitservice;
	}
	//private FruitDao dao=new FruitDao();
	 public int addFruit(Fruit fruit) {
		 return FruitDao.getInstance().addFruit(fruit);
	 }
	 public Fruit getFruit(int id) {
		 return FruitDao.getInstance().getFruit(id);
	 }
	 public int updateFruit(Fruit fruit) {
		 return FruitDao.getInstance().updateFruit(fruit);
	 }
	 public int updateFruit(int fruitid,int quantity) {
		 return FruitDao.getInstance().updateFruit(fruitid, quantity);
	 }
	 public int deletefruit(int id) {
		 return FruitDao.getInstance().deletefruit(id);
	 }
	 public List<Fruit> getMenu(){
		 return FruitDao.getInstance().getMenu();
	 }

}
