package com.book.share.account;


public class AccountVO {
	
	
	private int user_id;
	private String user_nickname;
	private String user_name;
	private String user_pw;
	private String user_email;
	private String user_phone;
	private String user_photo;
	private String user_regdate; // 가입날짜
	private String user_pwchange; // 비밀번호 변경이력
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}
	public String getUser_regdate() {
		return user_regdate;
	}
	public void setUser_regdate(String user_regdate) {
		this.user_regdate = user_regdate;
	}
	public String getUser_pwchange() {
		return user_pwchange;
	}
	public void setUser_pwchange(String user_pwchange) {
		this.user_pwchange = user_pwchange;
	}
	
	@Override
	public String toString() {
		return "AccountVO [user_id=" + user_id + ", user_nickname=" + user_nickname + ", user_name=" + user_name
				+ ", user_pw=" + user_pw + ", user_email=" + user_email + ", user_phone=" + user_phone + ", user_photo="
				+ user_photo + ", user_regdate=" + user_regdate + ", user_pwchange=" + user_pwchange + "]";
	}
}
