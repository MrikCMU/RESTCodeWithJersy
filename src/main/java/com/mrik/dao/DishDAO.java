package com.mrik.dao;

import com.mrik.model.Dish;

public interface DishDAO {
	public void insert(Dish dish);
	public Dish findByDishName(String name, int resId);
	public void update(Dish dish);
	public void delete(String name, int resId);
	
}
