package model;

import javax.swing.JOptionPane;

public class User extends Person{
	private String id;
	private String pw;
	private String name;
	private String nName;
	private String gender;
	
	private int saving;

	
	public User(String id, String pw, String name, String nName, String gender) {
		setId(id);
		setPw(pw);
		setName(name);
		setnName(nName);
		setGender(gender);
	}//생성자
	
	public User(String id) {
		setId(id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof User)) {
			return false;
		}//if
		User temp = (User)obj;
		return id.equals(temp.getId());
	}
	@Override
	public String toString() {
		String info = "아이디: " + id + "\n";
		info += "비밀번호: " + pw + "\n";
		info += "성 함: " + name + "\n";
		info += "닉네임: " + nName + "\n";
		info += "성별: " + gender  + "\n";
		return info;
	}

	public int getSaving() {
		// TODO Auto-generated method stub
		return saving;
	}
	public void setSaving(int saving) {
		this.saving = saving;
	}
	
	public void addSaving(int saving) {
		if(saving > 0)
			this.saving += saving;
		else
			JOptionPane.showMessageDialog(null,"0 보다 작은 금액은 충전할 수 없습니다.");
	}

}//User

