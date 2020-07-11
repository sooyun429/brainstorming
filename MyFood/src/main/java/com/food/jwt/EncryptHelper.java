package com.food.jwt;

public interface EncryptHelper {
	String encrypt(String user_password);
	
	boolean isMatch(String password, String hashed);
}
