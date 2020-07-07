package com.food.account;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.config.BasicResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@Controller
@RequestMapping(value = "/account")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@ResponseBody
	@RequestMapping(value = "/accounts", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "회원 리스트")
	public Object accounts() throws SQLException {
		List<AccountVO> accounts = service.accounts();
		BasicResponse result = new BasicResponse();
		result.status = true;
		if(accounts != null) {
			result.data = "success";
			result.object = accounts;
		} else {
			result.data = "false";
			result.object = "회원정보가 없거나, 불러오는데 실패했습니다";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/signup", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "회원가입")
	public Object signup(@RequestBody AccountVO accountVO) throws SQLException {
		
		
		// 회원 검증은 아직 없음.
		
		BasicResponse result = new BasicResponse();
		result.status = true;
		if(service.signup(accountVO)) {
			result.data = "success";
			result.object = "회원가입에 성공했습니다";
		} else {
			result.data = "false";
			result.object = "회원가입에 실패했습니다.";
		}
		
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/{user_nickname}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "회원정보")
	public Object getUser(@PathVariable(required = true) String user_nickname) throws SQLException {
		
		BasicResponse result = new BasicResponse();
		System.out.println(result);
		AccountVO accountVO = service.getUser(user_nickname);
		if (accountVO != null) {
			result.status = true;
			result.data = "success";
			result.object = accountVO;
			
		} else {
			result.status = true;
			result.data = "false";
			result.object = "user가 존재하지 않습니다";
			
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ApiOperation(value = "회원 정보 수정")
	public Object accountUpdate(@RequestBody AccountVO accountVO) throws SQLException {
		BasicResponse result = new BasicResponse();
		result.status = true;
		
		if (service.getUser(accountVO.getUser_nickname()) != null) {
			boolean updateResult = service.accountUpdate(accountVO);
			if (updateResult == true) {
				result.data = "success";
				result.object = "회원 정보 수정에 성공했습니다.";
			} else {
				result.data = "false";
				result.object = "회원 정보 수정에 실패했습니다.";
			}
		} else {
			result.data = "false";
			result.object = "존재하지 않는 회원입니다.";
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{user_nickname}", method = RequestMethod.DELETE)
	@ApiOperation(value = "회원 탈퇴")
	public Object accountDelete(@PathVariable String user_nickname) throws SQLException{
		BasicResponse result = new BasicResponse();
		result.status = true;
		
		if (service.getUser(user_nickname) != null) {
			 boolean deleteResult = service.accountDelete(user_nickname);
			 
			 if(deleteResult == true) {
				 result.data = "success";
				 result.object = "회원 탈퇴에 성공했습니다.";
			 } else {
				 result.data = "false";
				 result.object = "회원 탈퇴에 실패했습니다.";
			 }
			 
		} else {
			result.data = "false";
			result.object = "존재하지 않는 회원입니다.";
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/now", method=RequestMethod.GET) 
	public String now() throws SQLException {
		System.out.println(service.getDual());
		return service.getDual();
	}
	

}
