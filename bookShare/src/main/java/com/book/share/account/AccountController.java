package com.book.share.account;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParser;

@Controller
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@ResponseBody
	@RequestMapping(value = "/accounts", method=RequestMethod.GET)
	public List<AccountVO> accounts() throws SQLException {
		System.out.println(service.accounts());
		return service.accounts();
	}
	
	@ResponseBody
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public boolean signup(@RequestBody AccountVO accountVO) throws SQLException {
		System.out.println(accountVO.toString());
		System.out.println(service.signup(accountVO));
		return service.signup(accountVO);
	}
	
	@ResponseBody
	@RequestMapping(value = "/now", method=RequestMethod.GET) 
	public String now() throws SQLException {
		System.out.println(service.getDual());
		return service.getDual();
	}
	

}
