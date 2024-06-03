package model;

public class Caregiver extends Person{
	private String id;
	private String password;
	private String name;
	private String gender;
	
	// 제공하고 있는 서비스 종류 - 한 명의 돌보미가 여러 개의 서비스를 제공할 수 있다고 가정 
	// 예: new String[]{"간병인", "요양보호사", "베이비시터", "가사도우미", "콜택시"};
	private String providingServiceTypes;
	
	// 서비스 제공을 위해 이용 가능한 거리 
	private String region;
	
	// 요금을 돌보미가 선택할 수 있음 - 서비스를 여러가지 제공할 수 있으므로 요금도 여러가지 
	private int ratesPerServiceType;
	
	// 서비스 이용고객으로 부터 받은 평점 (5점만점)
	private double reputationAver;
	
	private double reputationTotal;
	
	private int count;
	private String review;
	
	private boolean isTrainingCompleted;
	
	// 디폴트 생성자 - 다른 생성자 만들어서 사용하셔도 됩니다.
	public Caregiver() {}
	
	public Caregiver(String id, String password, String name, String gender, String providingServiceTypes,
			String region, int ratesPerServiceType, double reputation) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.providingServiceTypes = providingServiceTypes;
		this.region = region;
		this.ratesPerServiceType = ratesPerServiceType;
		this.reputationAver = reputation;
		this.review = "현재 등록된 리뷰가 없습니다.";
		this.isTrainingCompleted = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProvidingServiceTypes() {
		return providingServiceTypes;
	}

	public void setProvidingServiceTypes(String providingServiceTypes) {
		this.providingServiceTypes = providingServiceTypes;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getRatesPerServiceType() {
		return ratesPerServiceType;
	}

	public void setRatesPerServiceType(int ratesPerServiceType) {
		this.ratesPerServiceType = ratesPerServiceType;
	}

	public double getReputation() {
		return reputationAver;
	}

	public void setReputation(double reputation) {
		this.reputationAver = reputation;
	}
	
	public void calcReputation() {
		double temp = this.reputationTotal / this.count;
		temp = Math.round(temp * 10.0) / 10.0;
		this.reputationAver = temp;
	}
	
	public void setReputationTotal(int score) {
		this.reputationTotal += score;
		this.count++;
	}

	@Override
	public void setPw(String pw) {
		this.password = pw;
	}
	
	public void setReview(String review) {
		if(this.review.equals("현재 등록된 리뷰가 없습니다.")) {
			this.review = review;
		}else {
			this.review += review;
		}
	}
	
	public String getReview() {
		return this.review;
	}
	
	public void setTrainingCompleted(boolean completed) {
		this.isTrainingCompleted = completed;
	}
	public boolean getTrainingCompleted() {
		return this.isTrainingCompleted;
	}
	
}
