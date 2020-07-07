package com.food.account;

import java.sql.SQLException;
import java.util.List;

import com.food.account.AccountVO;

public interface AccountMapper {
	public List<AccountVO> accounts() throws SQLException;
	public boolean signup(AccountVO accountVO) throws SQLException;
	public AccountVO getUser(String user_nickname) throws SQLException;
	public boolean accountUpdate(AccountVO accountVO) throws SQLException;
	public boolean accountDelete(String user_nickname) throws SQLException;
	public String getDual() throws SQLException;
}
