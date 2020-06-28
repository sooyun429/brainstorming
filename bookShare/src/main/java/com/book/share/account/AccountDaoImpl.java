package com.book.share.account;

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
	public String getDual() throws SQLException {
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		return mapper.getDual();
	}
	
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
}
