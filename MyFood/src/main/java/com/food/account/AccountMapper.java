package com.food.account;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.food.account.AccountVO;

public interface AccountMapper {
	public List<AccountVO> accounts() throws SQLException;
	public boolean signup(AccountVO accountVO) throws SQLException;
	public AccountVO getUser(String user_nickname) throws SQLException;
	public boolean accountUpdate(AccountVO accountVO) throws SQLException;
	public boolean accountDelete(String user_nickname) throws SQLException;
	public Object signin(String user_nickname,String user_password) throws SQLException;
	public String getDual() throws SQLException;
	
	//signup 검증
	public Object getUserEmail(String user_email) throws SQLException;
	public Object getUserPhone(String user_phone) throws SQLException;
	//spring security
	public Object findByUsername(String user_nickname);
}
