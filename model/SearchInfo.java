package model;

public class SearchInfo {	//검색 조건을 객체로 관리하기 위한 클래스
	private String nameInfo;		//이름
	private String serviceInfo;		//서비스 종류
	private int priceInfo;			//비용(시급)
	private String genderInfo;		//성별
	private String regionInfo;		//서비스 지역
	
	public SearchInfo(String name, String service,int price,String gender,String region) {	//객체 생성자
		if(name == null) {	// 이름 검색 필드에 아무것도 입력하지 않았을 경우 기본값 "" 대입
			name = "";	
		}
		if(price == 0) {	// 가격 검색 필드에 아무것도 입력하지 않았을 경우 기본값 -1 대입
			price = -1;
		}
		nameInfo = name;			//생성자 초기화
		serviceInfo = service;		//생성자 초기화
		priceInfo = price;			//생성자 초기화
		genderInfo = gender;		//생성자 초기화
		regionInfo = region;		//생성자 초기화
	}

	public String getNameInfo() {
		return nameInfo;
	}

	public String getGenderInfo() {
		return genderInfo;
	}

	public String getServiceInfo() {
		return serviceInfo;
	}

	public int getPriceInfo() {
		return priceInfo;
	}

	public String getRegionInfo() {
		return regionInfo;
	}
}
