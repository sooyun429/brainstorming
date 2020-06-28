package com.book.share.account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
	public String getDual() throws SQLException;
	public List<AccountVO> accounts() throws SQLException;
	public boolean signup(AccountVO accountVO) throws SQLException;

}
