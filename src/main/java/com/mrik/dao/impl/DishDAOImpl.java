package com.mrik.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mrik.dao.DishDAO;
import com.mrik.model.Dish;


public class DishDAOImpl implements DishDAO {

	
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public void insert(Dish dish) {
		// TODO Auto-generated method stub\
		String sql = "INSERT INTO dish " +
				"(NAME, PRICE, RES_ID) VALUES (?, ?, ?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, dish.getName());
			ps.setDouble(2, dish.getPrice());
			ps.setInt(3, dish.getRestaurant());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	@Override
	public Dish findByDishName(String name, int resId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM dish WHERE NAME = ? AND RES_ID = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, resId);
			Dish dish = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dish = new Dish();
				dish.setName(rs.getString("NAME"));
				dish.setPrice(rs.getDouble("PRICE"));
				dish.setRestaurant(rs.getInt("RES_ID"));
				
			}
			rs.close();
			ps.close();
			return dish;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}

	}
	@Override
	public void update(Dish dish) {
		// TODO Auto-generated method stub
		String sql = "UPDATE dish " +
				"SET NAME = ?, PRICE = ? , RES_ID = ? WHERE NAME = ? AND RES_ID = ?";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, dish.getName());
			ps.setDouble(2, dish.getPrice());
			ps.setInt(3, dish.getRestaurant());
			ps.setString(4, dish.getName());
			ps.setInt(5, dish.getRestaurant());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public void delete(String name, int resId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM dish WHERE NAME = ? AND RES_ID = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, resId);

			int rs = ps.executeUpdate();
			

			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
