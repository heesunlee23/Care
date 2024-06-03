package model;

public class Reservation {
	private Caregiver caregiver;
	private User user;
	private String desiredDay;
	private String resevStartTime;
	private String resevEndTime;
	private long timeCalculate;//시간계산 -문태일
	
	public Reservation(Caregiver caregiver, String desiredDay, String resevStartTime, String resevEndTime, long timeCalculate, User user) {
		this.caregiver = caregiver;
		this.desiredDay = desiredDay;
		this.resevStartTime = resevStartTime;
		this.resevEndTime = resevEndTime;
		this.timeCalculate = timeCalculate;//추가 문태일
		this.user = user;//추가- 문태일
	}

	public Caregiver getCaregiver() {
		return caregiver;
	}

	public void setCaregiver(Caregiver caregiver) {
		this.caregiver = caregiver;
	}
	
	public String getClientId() {
		return user.getId();
	}

	public String getResevStartTime() {
		return resevStartTime;
	}

	public void setResevStartTime(String resevStartTime) {
		this.resevStartTime = resevStartTime;
	}

	public String getResevEndTime() {
		return resevEndTime;
	}

	public void setResevEndTime(String resevEndTime) {
		this.resevEndTime = resevEndTime;
	}

	public long getTimeCalculate() {
		return timeCalculate;
	}

	public void setTimeCalculate(long timeCalculate) {
		this.timeCalculate = timeCalculate;
	}

	public String getDesiredDay() {
		return desiredDay;
	}

	public void setDesiredDay(String desiredTime) {
		this.desiredDay = desiredTime;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		System.out.println("남은잔액 : " + user.getSaving());
		return "돌보미 이름: " + caregiver.getName() + ", "
				+ "돌보미 성별: " + caregiver.getGender() + ", "
				+ "서비스 종류: " + caregiver.getProvidingServiceTypes() + ", "
				+ "지역: " + caregiver.getRegion() + ", "
				+ "총 액수: " + timeCalculate + "원, "//caregiver.getRatesPerServiceType()*
				+ "평점: " + caregiver.getReputation() + ", "
				+ "서비스 일시: " + desiredDay + ", "
				+ "서비스 시작시간: " + resevStartTime + ", "
				+ "서비스 종료시간: " + resevEndTime + ", "
//				+ "/////시간을 분으로:"+timeCalculate +"/////"+currentBalance+"(잔액///////////"
				+ "남은 잔액 : " + user.getSaving() + "원"; //추가-문태일 //현재 문제점
	}	
}
