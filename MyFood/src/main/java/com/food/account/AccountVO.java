package com.food.account;

public class AccountVO {
	
	
	private String user_nickname;
	private String user_pw;
	private String user_email;
	private String user_phone;
	private String user_photo;
	private String user_regdate; // 가입날짜
	private String user_pwchange; // 비밀번호 변경이력
	private String user_logdate; // 로그인 기록
	private String user_age;
	private String user_height;
	private String user_weight;
	private String user_kcal; // 권장 칼로리
	private String user_state; // 식단 상태?
	
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
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
	
	public String getUser_logdate() {
		return user_logdate;
	}
	public void setUser_logdate(String user_logdate) {
		this.user_logdate = user_logdate;
	}
	public String getUser_age() {
		return user_age;
	}
	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}
	public String getUser_height() {
		return user_height;
	}
	public void setUser_height(String user_height) {
		this.user_height = user_height;
	}
	public String getUser_weight() {
		return user_weight;
	}
	public void setUser_weight(String user_weight) {
		this.user_weight = user_weight;
	}
	public String getUser_kcal() {
		return user_kcal;
	}
	public void setUser_kcal(String user_kcal) {
		this.user_kcal = user_kcal;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	
	@Override
	public String toString() {
		return "AccountVO [user_nickname=" + user_nickname 
				+ ", user_pw=" + user_pw + ", user_email=" + user_email + ", user_phone=" + user_phone + ", user_photo="
				+ user_photo + ", user_regdate=" + user_regdate + ", user_pwchange=" + user_pwchange + ", user_logdate="
				+ user_logdate + ", user_age=" + user_age + ", user_height=" + user_height + ", user_weight="
				+ user_weight + ", user_kcal=" + user_kcal + ", user_state=" + user_state + "]";
	}

}
