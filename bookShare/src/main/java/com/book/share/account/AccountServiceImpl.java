package com.book.share.account;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDao dao;

	@Override
	public List<AccountVO> accounts() throws SQLException {
		return dao.accounts();
	}
	
	@Override
	public boolean signup(AccountVO accountVO) throws SQLException {
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
