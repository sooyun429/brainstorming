package com.food.ingredient;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<IngredientVO> ingredients() throws SQLException{
		IngredientMapper mapper = sqlSession.getMapper(IngredientMapper.class);
		return mapper.ingredients();
	}
	
	public IngredientVO getIngredient(String name) throws SQLException{
		IngredientMapper mapper = sqlSession.getMapper(IngredientMapper.class);
		return mapper.getIngredient(name);
	}
	
	public boolean insertIngredient(IngredientVO ingredientVO) throws SQLException{
		IngredientMapper mapper = sqlSession.getMapper(IngredientMapper.class);
		return mapper.insertIngredient(ingredientVO);
	}
	
	public boolean deleteIngredient(String name) throws SQLException{
		IngredientMapper mapper = sqlSession.getMapper(IngredientMapper.class);
		return mapper.deleteIngredient(name);
	}
	
	public boolean updateIngredient(IngredientVO ingredientVO) throws SQLException{
		IngredientMapper mapper = sqlSession.getMapper(IngredientMapper.class);
		return mapper.updateIngredient(ingredientVO);
	}
	
	
}
