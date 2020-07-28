package com.food.ingredient;

import java.sql.SQLException;
import java.util.List;

public interface IngredientMapper {
	public List<IngredientVO> ingredients() throws SQLException;
	public IngredientVO getIngredient(String name) throws SQLException;
	public boolean insertIngredient(IngredientVO ingredientVO) throws SQLException;
	public boolean deleteIngredient(String name) throws SQLException;
	public boolean updateIngredient(IngredientVO ingredientVO) throws SQLException;
}
