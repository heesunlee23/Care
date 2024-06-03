package model;

import java.util.ArrayList;

public class UserDataSet {
	private ArrayList<User> users;
	
	public UserDataSet() {
		users = new ArrayList<User>();
	}//UserDataSet
	
	public void addUser(User user) { //회원 추가
		users.add(user);
	}//addUser
	
	public boolean isIdOverlap(String id) { //아이디 중복 확인
		return users.contains(new User(id));
	}//isIdOverlap
	
	public void withdraw(Person user) { //회원 삭제
		users.remove(user); 
	}//withdraw
	
	public User getUser(String id) { //유저 정보 가져오기
		for(User user : users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}//getUser
	
	public boolean contains(User user) { //회원 여부 확인
		return users.contains(user);
	}//contains
	
}//class
