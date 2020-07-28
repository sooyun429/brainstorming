package com.food.jwt;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptImpl implements EncryptHelper {
	
	@Override
	public String encrypt(String user_password) {
		return BCrypt.hashpw(user_password, BCrypt.gensalt());
	}
	
	@Override
	public boolean isMatch(String user_password, String hashed) {
		return BCrypt.checkpw(user_password, hashed);
	}
	
}
