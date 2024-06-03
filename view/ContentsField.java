package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.ListCreator;
import model.Person;
import model.ReservationList;
import model.User;
import model.UserDataSet;




public class ContentsField {
	private JPanel fields;			// ContentsField상단의 각 필드의 이름을 출력해주기 위한 공간
	private JPanel ContentsField;	// Caregiver객체를 10개씩 출력하기 위한 공간
	private JPanel indexField;		// 하단 페이지 이동 버튼을 넣어두기 위한 공간
	private JButton[] indexBtn;		// 페이지 이동 버튼을 모아둔 배열
	private ElementField element;	// ElementField에 접근하기 위한 참조변수
	private ListCreator list;		// CaregiverDataSet에서 검색조건에 맞는 List를 추출하기위한 ListCreator클래스의 참조변수
	private int cnt = 0;			// 버튼 카운트    예) 1..2..3...버튼
	private int pageCnt = 0;		// 버튼 카운트가 10을 넘어갔을 경우 11번 버튼부터 다시 10개씩 추가해주기 위한 페이지 카운트 
	
	private JButton upBtn;			// 페이지를 10개씩 증가시기키 위한 버튼
	private JButton downBtn;		// 페이지를 10개씩 감소시기키 위한 버튼
	
	private ReservationList reservationList;
	private UserDataSet userDataSet;
	
	private User user;
	
	public ContentsField(ReservationList reservationList,UserDataSet userDataSet,User user){	//생성자
		this.reservationList = reservationList;
		this.userDataSet = userDataSet;
		this.user = user;
		
		fields = new JPanel();							//ContentsField상단의 각 필드의 이름을 출력해주기 위한 공간 좋은 이름 있으면 제보 바람
		getFields().setBorder(new LineBorder(Color.LIGHT_GRAY, 1));	//외곽선 표시
		getFields().setBounds(240, 160, 800, 30);				//절대값으로 위치 지정
		getFields().setLayout(null);						//내부 요소를 절댓값으로 넣기위해 layout은 null로 지정
		getFields().setBackground(new Color(0xF0F8FF));  //내부 요소 색상 지정
		getFields().setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		JLabel name = new JLabel("|  이름  |");		// 이름 필드
		name.setBounds(25, 3, 45, 25);				// 크기 위치 지정
		getFields().add(name);							// asdf에 만들어진 JLabel 추가
		name.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		JLabel gender = new JLabel("|  성별  |");		// 성별 필드
		gender.setBounds(90, 3, 45, 25);
		getFields().add(gender);
		gender.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		JLabel price = new JLabel("|  시급  |");		// 시급 필드
		price.setBounds(145, 3, 45, 25);
		getFields().add(price);
		price.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		JLabel service = new JLabel("| 서비스 종류 |");//서비스 필드
		service.setBounds(210, 3, 80, 25);
		getFields().add(service);
		service.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		JLabel score = new JLabel("  평점 |");		//평점 필드
		score.setBounds(285, 3, 45, 25);
		getFields().add(score);
		score.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		JLabel region = new JLabel("|  가능지역 |");	//지역 필드
		region.setBounds(330, 3, 80, 25);
		getFields().add(region);
		region.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		JLabel trainingComp = new JLabel("| 안전교육 |");
		trainingComp.setBounds(420, 3, 80, 25);
		getFields().add(trainingComp);
		trainingComp.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		ContentsField = new JPanel();							// Caregiver객체를 담은 element배열의 각 원소를 10개씩 출력하기 위한 공간
		list = new ListCreator(getCnt());							// 현재 인덱스 번호 예> 4번이면 40번째부터 list2를 만들기 위해 현재 cnt값을 매개변수로 List생성자 실행
		getContentsField().setBorder(new LineBorder(Color.LIGHT_GRAY, 1));	// 외곽선 생성
		getContentsField().setBounds(240, 200, 800, 380);			// 크기 및 위치 지정
		getContentsField().setLayout(null);							// 내부 요소의 위치를 절대값으로 배치하기 위해 layout은 null로 지정
		getContentsField().setBackground(Color.WHITE);
		getContentsField().setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		element = new ElementField(getCnt(), reservationList,userDataSet,user); // ElementField객체를 생성후 element 참조변수에 저장 cnt은 해당 클래스에서 elememtList를 만들때 인덱스의 초기값으로 서 사용됨
		
		for(int i = 0; i < element.getElementList().length; i++) {	// 만들어진 elementList를 ContentsField에 추가해주는 기능
			getContentsField().add(element.getElementList()[i]);
		}
		
		indexField = new JPanel();								// 페이지 버튼을 배치하기 위한 공간
		getIndexField().setBounds(290, 600, 700, 50);				// 크기와 위치 지정
		getIndexField().setLayout(new FlowLayout());					// 버튼의 자동 정렬을 위해 FlowLayout사용
		getIndexField().setBackground(Color.WHITE);
		getIndexField().setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		downBtn = new JButton("이전");							// 이전버튼 생성
		downBtn.addActionListener(pageBtn);						// 버튼에 ActionListner 추가
		downBtn.setBackground(new Color(0x1E90FF));
		downBtn.setForeground(Color.WHITE);
		downBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		upBtn = new JButton("다음");								// 이전버튼 생성
		upBtn.addActionListener(pageBtn);						// 버튼에 ActionListner 추가
		upBtn.setBackground(new Color(0x1E90FF));
		upBtn.setForeground(Color.WHITE);
		upBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		if(list.getCtnMax() != 0) {									// 생성해야할 버튼의 갯수가 0개가 아닐경우 버튼 생성 시작
			indexBtn = new JButton[list.getCtnMax()+1];				// 여러개의 버튼을 저장하기 위한 배열 선언 배열 크기를 최대값+1로 한 이유는 그렇게 안하면 버그남
			for(int i = 0; i <= list.getCtnMax(); i++) {				// 버튼 생성
				JButton temp = new JButton(""+(i+1));			// 각 버튼을 만들면서 내용을 인덱스값의 1을 더한값으로 설정
				temp.addActionListener(pageBtn);				// 만들어진 버튼에 ActionListner 추가
				indexBtn[i] = temp;								// 만들어진 버튼을 배열에 저장
				indexBtn[i].setBackground(Color.WHITE);
				indexBtn[i].setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			}
		}
		
		getIndexField().add(downBtn);								// 인덱스필드 화면에 이전버튼(10개 단위로 cnt--) add
		
		if(list.getCtnMax() != 0) {									// 버튼의 최대값이 0이 아닐경우(만들어진 버튼이 존재하는 경우) 만들어진 버튼을 10개씩 필드에 추가
			pageCnt = getCnt()/10;									// 현재 page를 확인하기 위해 현재 cnt에 10을 나눠서 값을 구함 예> cnt가 12면 10으로 나눠서 나온 값 1이 pagecnt가 됨
			int start = pageCnt*10;								// 추가할 버튼의 시작값 설정 pagecnt이 1이면 10부터 버튼 생성
			for(int i = start; i <= list.getCtnMax() && i != start+10; i++) {	// 시작값 부터 10개를 생성하거나 마지막 버튼을 배치할 때 까지 반복
				getIndexField().add(indexBtn[i]);					// 버튼 배치
				if(getCnt() == i) {									// 만약 현재 생성하고 있는 버튼의 배열 인덱스가 현재 cnt와 같다면 배경색을 회색으로 변경
					indexBtn[i].setBackground(Color.WHITE);		//
					if(i == cnt) {
						indexBtn[i].setBackground(Color.ORANGE);
					}
					indexBtn[i].setFont(new Font("나눔스퀘어", Font.BOLD, 12));
				}
			}
		}
		
		getIndexField().add(upBtn);
	}
	
	public ActionListener pageBtn = new ActionListener() {		//인덱스 버튼의 ActionListner 구현
		
		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getActionCommand().equals("이전")) {				// 이전버튼을 눌렀을 경우
				pageCnt--;										// 페이지cnt를 1 내림
				setCnt(pageCnt*10);								// 현재 cnt를 page에 10을 곱한 값을 넣음 예> page가 0이면 0부터 1이면 10부터
				if(getCnt() < 0) {									// 만약 바꾼 cnt값이 0 미만이 되면 cnt값을 0으로 초기화
					setCnt(0);
				}
				reLoad();										// 화면을 다시 그려주는 메서드
			}else if(e.getActionCommand().equals("다음")) {		// 이전 버튼의 반대 버전
				pageCnt++;
				setCnt(pageCnt*10);
				if(getCnt() > list.getCtnMax()) {
					setCnt(list.getCtnMax());
				}
				reLoad();
			}else {												// 숫자 버튼을 눌렀을 경우 cnt의 값을 누른 버튼의 값에서 1을 뺀 값을 넣음
				setCnt(Integer.parseInt(e.getActionCommand()) - 1);
				reLoad();
			}
			System.out.println("현재 페이지 : " + getCnt());			// 현재 인덱스 확인
		}
	};
	
	public void reLoad() {										// 화면 재출력
		list = new ListCreator(getCnt());							// 최신 조건으로 list1 생성
		getContentsField().removeAll();								// ContentsField의 내용을 전부 삭제함
		getIndexField().removeAll();									// indexField의 내용을 전부 삭제
		element = new ElementField(getCnt(), reservationList,userDataSet,user); // 새로 만들어진 list를 토대로 ElementField객체를 재생성함
		element = new ElementField(getCnt(), reservationList,userDataSet,user);
		for(int i = 0; i < element.getElementList().length; i++) {	// ContentsField의 내용을 다시 추가해줌
			getContentsField().add(element.getElementList()[i]);			//
			getContentsField().setBackground(Color.WHITE);
			getContentsField().setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		}
		
		getIndexField().add(downBtn);								// indexField 재생성
		
		if(list.getCtnMax() != 0) {									//
			pageCnt = getCnt()/10;
			int start = pageCnt*10;
			for(int i = start; i <= list.getCtnMax() && i != start+10; i++) {
				getIndexField().add(indexBtn[i]);
				if(getCnt() == i) {
					indexBtn[i].setBackground(new Color(0xFFD700));		//
					indexBtn[i].setFont(new Font("나눔스퀘어", Font.BOLD, 12));
				}else {											//기존에 선택되어있던 버튼(색이 달랐던 버튼)의 색을 초기화 하고 새로 선택된 버튼의 색을 회색으로 바꿔줌
					indexBtn[i].setBackground(Color.WHITE);		//
					indexBtn[i].setFont(new Font("나눔스퀘어", Font.BOLD, 12));
				}
			}
		}
		
		getIndexField().add(upBtn);									// indexField 재생성 끝
		
		getContentsField().revalidate();								// 이유는 모르지만
		getContentsField().repaint();								// 재생성에 필요함
	}

	public JPanel getContentsField() {
		return ContentsField;
	}

	public JPanel getIndexField() {
		return indexField;
	}

	public JPanel getFields() {
		return fields;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}