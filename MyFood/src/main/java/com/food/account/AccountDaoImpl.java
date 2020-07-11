package com.food.account;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
	

	@Autowired
	private SqlSession sqlSession;

	
	@Override
	public List<AccountVO> accounts() throws SQLException {
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		return mapper.accounts();
	}
	
	@Override
	public boolean signup(AccountVO accountVO) throws SQLException{
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		return mapper.signup(accountVO);
	}
	
	@Override
	public AccountVO getUser(String user_nickname) throws SQLException {
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		return mapper.getUser(user_nickname);
	}
	

	@Override
	public boolean accountUpdate(AccountVO accountVO) throws SQLException {
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		return mapper.accountUpdate(accountVO);
	}
	
	@Override
	public boolean accountDelete(String user_nickname) throws SQLException {
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		return mapper.accountDelete(user_nickname);
	}
	
	@Override
	public AccountVO findByUsername(String user_nickname) {
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		return mapper.findByUsername(user_nickname);
	}
	
	@Override
	public AccountVO signin(String user_nickname, String user_password) throws SQLException {
		
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		
		return mapper.signin(user_nickname, user_password);
	}
	
	@Override
	public String getDual() throws SQLException {
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		return mapper.getDual();
	}
}

