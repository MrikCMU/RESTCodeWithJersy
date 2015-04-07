package com.mrik.model;

public class Dish {


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}
	String name;
	double price;
	int restaurant;
	
	@Override
	public String toString() {
		return new StringBuffer("name:").append(this.getName()).append("price:").append(this.getPrice()).append("restaurant:").append(this.getRestaurant()).toString();
	}
	
}
