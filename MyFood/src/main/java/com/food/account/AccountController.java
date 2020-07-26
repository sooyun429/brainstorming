package com.food.account;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.config.BasicResponse;
import com.food.jwt.JwtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@Controller
@CrossOrigin
@RequestMapping(value = "/api")
@Api
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@Autowired
	private JwtService jwtService;
	
	@ResponseBody
	@RequestMapping(value = "/account/list", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
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
	@RequestMapping(value="/user/signup", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "회원가입")
	public Object signup(@RequestBody AccountVO accountVO, HttpServletResponse res) throws SQLException {
		// 회원 검증은 아직 없음.
		BasicResponse result = new BasicResponse();
		result.status = true;
		
		try {
				if(service.signup(accountVO)) {
					result.data = "success";
					result.object = "회원가입에 성공했습니다";
					
					String token = jwtService.create(accountVO);
					res.setHeader("jwt-auth-token", token);
				
				} else {
					result.data = "false";
					result.object = "회원가입에 실패했습니다.";
				}
			
		} catch (Exception e) {
			result.data = "false";
			result.object = "회원가입에 실패했습니다. (이미 존재하는 닉네임, 이메일, 핸드폰 번호인지 확인해주십시오)";
		}
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/account/{user_nickname}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
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
	@RequestMapping(value = "/account/update", method = RequestMethod.PUT)
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
	@RequestMapping(value = "/account/{user_nickname}", method = RequestMethod.DELETE)
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
	@RequestMapping(value = "/user/signin", method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
	@ApiOperation("로그인")
	private Object signin(@RequestParam String user_nickname, @RequestParam String user_password, HttpServletResponse res) throws SQLException {
		BasicResponse result = new BasicResponse();
		result.status = true;

		Object accountVO = service.signin(user_nickname, user_password);
		System.out.println(accountVO);
		
		if (accountVO.equals("DIFFPW") ) {
			result.data = "fail";
			result.object = "아이디와 비밀번호가 일치하지 않습니다.";
		} else if (accountVO.equals("DIFFID") ){
			result.data = "fail";
			result.object = "존재하지 않는 아이디입니다";
		}
		else  {
			String token = jwtService.create((AccountVO)accountVO);
			
			if (token != null || token.length() > 0) {
				res.setHeader("jwt-auth-token", token);
				result.data = "success";
				result.object = accountVO;
			} else {
				result.data = "fail";
				result.object = "토큰 생성에 실패했습니다.";
			}
			
		} 
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/email/{user_email}", method = RequestMethod.GET)
	@ApiOperation("이메일 중복 찾기")
	private Object getUserEmail(@PathVariable String user_email) throws SQLException{
		BasicResponse result = new BasicResponse();
		
		boolean haveEmail = service.getUserEmail(user_email);
		
		System.out.println(haveEmail);
		if (haveEmail == true) {
			result.status = true;
			result.data = "false";
			result.object = "이미 존재하는 이메일이 있습니다";
			
		} else {
			result.status = true;
			result.data = "false";
			result.object = "사용하실 수 있는 이메일입니다.";
			
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/phone/{user_phone}", method = RequestMethod.GET)
	@ApiOperation("이메일 중복 찾기")
	private Object getUserPhone(@PathVariable String user_phone) throws SQLException{
		BasicResponse result = new BasicResponse();
		System.out.println(result);
		boolean haveEmail = service.getUserPhone(user_phone);
		if (haveEmail == true) {
			result.status = true;
			result.data = "false";
			result.object = "이미 존재하는 번호가 있습니다";
			
		} else {
			result.status = true;
			result.data = "false";
			result.object = "사용하실 수 있는 번호입니다.";
			
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/nickname/{user_nickname}", method=RequestMethod.GET)
	@ApiOperation("닉네임 중복 찾기")
	private Object getUserNickname(@PathVariable String user_nickname) {
		BasicResponse result = new BasicResponse();
		
		boolean haveNickname = service.findByUsername(user_nickname);
		if (haveNickname == true) {
			result.status = true;
			result.data = "false";
			result.object = "이미 존재하는 닉네임 있습니다";
			
		} else {
			result.status = true;
			result.data = "false";
			result.object = "사용하실 수 있는 닉네임입니다.";
			
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
