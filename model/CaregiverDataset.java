package model;

import java.util.ArrayList;
import java.util.Random;

public class CaregiverDataset {											
	private ArrayList<Caregiver> list;									// 모든 Caregiver객체를 저장한 list
	private static final CaregiverDataset instance = new CaregiverDataset();	// 해당 클래스의 객체가 1번만 생성되도록 싱글톤패턴 적용
	private Random ran = new Random();										// 객체에 넣을 랜덤데이터를 생성할 Random클래스객체 생성
	
	private String[] familyName = { "김", "이", "전", "권", "박" };							//랜덤 데이터셋
	private String[] mainName = { "민기", "민수", "현지", "수영", "수지" };						//
	private String[] genderList = { "남", "여" };												//
	private String[] serviceList = { "콜 택시", "베이비시터", "요양보호사", "간병인","가사도우미" };	//
	private String[] regionList = {"서울", "인천", "대구", "광주", "부산", "천안"};				//
	private char[] alpha = {																//
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
	
	private CaregiverDataset() {	// 생성자
		list = new ArrayList<Caregiver>();
		if(list.isEmpty()) {														// list에 내용이 없으면 실행
			list.add(new Caregiver("admin","adminPass", "김현성","남","콜 택시","서울",11000,5.0));
			for (int i = 1; i <= 153; i++) {										// 생성하고픈 객체의 수를 지정해서 반복
				
				// id 생성 
				String id = "";															//id 랜덤 생성을 위한 변수 선언
				for(int j = 0; j < 6+ran.nextInt(4); j++) {								// 위의 알파벳 테이블 에서 랜덤으로 6에서 10자리의 id를 만들기 위해 반복문 사용
					id += alpha[ran.nextInt(alpha.length)];
				}
				
				// password 생성 
				String pw ="";															// pw 랜덤 생성
				for(int j = 0; j < 6+ran.nextInt(4); j++) {								
					pw += alpha[ran.nextInt(alpha.length)];
				}
				
				// name 생성
				String name = familyName[ran.nextInt(5)] + mainName[ran.nextInt(5)];	//생성된 객체에 이름 지정
				
				// gender 생성
				String gender = genderList[ran.nextInt(2)];								//생성된 객체에 성별 지정
				
				String serviceN = serviceList[ran.nextInt(serviceList.length)];			//Caregiver에 필요한 정보 저장
				
				String regionN = regionList[ran.nextInt(regionList.length)];			//
				
				int salary = 9800 + ran.nextInt(5200);		

				double min = 0.0;
		        double max = 5.0;
		        double randomValue = min + (Math.random() * (max - min));
				double score = Math.round(randomValue * 10.0) / 10.0;
				
				int a = ran.nextInt(100);
				boolean flag = false;
				if(a >=50) {
					flag = true;
				}
				
				Caregiver temp = new Caregiver(id, pw, name, gender, serviceN, regionN, salary, score);		//Caregiver객체 생성
				temp.setTrainingCompleted(flag);
				list.add(temp);																				// 생성한 객체 list에 추가	
			}	
		}
	}
	public static CaregiverDataset getInstance() {
		return instance;
	}
	public ArrayList<Caregiver> getList(){
		return this.list;
	}
	
	public void withdraw(Person user) { //회원 삭제
		list.remove(user); 
	}//withdraw
	
	public boolean isIdOverlap(String id) { //아이디 중복 확인
		for(Caregiver care : list) {
			if(care.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}//isIdOverlap
	
	public void addMember(Caregiver member) {
		list.add(member);
	}
	
	
	public Caregiver getCaregiver(String id) {
		for(Caregiver care : list) {
			if(care.getId().equals(id)) {
				return care;
			}
		}
		return null;
	}
}
