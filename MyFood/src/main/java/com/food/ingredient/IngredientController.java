package com.food.ingredient;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.config.BasicResponse;
import com.food.jwt.JwtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@Controller
@CrossOrigin
@RequestMapping(value = "/api/ingredient")
@Api
public class IngredientController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	IngredientService service;
	
	@ResponseBody
	@RequestMapping(value = "/ingredients", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Object ingredients() throws SQLException {
		List<IngredientVO> ingredients = service.ingredients();
		BasicResponse result = new BasicResponse();
		result.status = true;
		if(ingredients != null) {
			result.data = "success";
			result.object = ingredients;
		} else {
			result.data = "false";
			result.object = "관련 재료 정보가 없거나, 불러오는데 실패했습니다";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public Object getIngredient(@PathVariable(required = true) String name) throws SQLException{
		IngredientVO ingredientVO = service.getIngredient(name);
		
		
	}

}
