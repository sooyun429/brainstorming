package com.food.account;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.account.AccountDao;
import com.food.account.AccountVO;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDao dao;
	
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<AccountVO> accounts() throws SQLException {
		return dao.accounts();
	}
	
	@Override
	public boolean signup(AccountVO accountVO) throws SQLException {
		accountVO.setUser_pw(passwordEncoder.encode(accountVO.getUser_pw()));
		return dao.signup(accountVO);
	}
	
	@Override
	public AccountVO getUser(String user_nickname) throws SQLException {
		return dao.getUser(user_nickname);
	}
	
	@Override
	public boolean accountUpdate(AccountVO accountVO) throws SQLException {
		return dao.accountUpdate(accountVO);
	}
	
	@Override
	public boolean accountDelete(String user_nickname) throws SQLException {
		return dao.accountDelete(user_nickname);
	}
	
	@Override
	public String getDual() throws SQLException {
		return dao.getDual();
	}

	
	
}
