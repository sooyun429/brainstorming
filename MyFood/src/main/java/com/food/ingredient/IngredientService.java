package com.food.ingredient;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
	
	@Autowired
	IngredientDao dao;
	
	public List<IngredientVO> ingredients() throws SQLException{
		return dao.ingredients();
	}
	
	public IngredientVO getIngredient(String name) throws SQLException{
		return dao.getIngredient(name);
	}
	
	public boolean insertIngredient(IngredientVO ingredientVO) throws SQLException{
		return dao.insertIngredient(ingredientVO);
	}
	
	public boolean deleteIngredient(String name) throws SQLException {
		return dao.deleteIngredient(name);
	}
	
	public boolean updateIngredient(IngredientVO ingredientVO) throws SQLException {
		return dao.updateIngredient(ingredientVO);
	}
}
