package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.ListCreator;
import model.Caregiver;
import model.ReservationList;
import model.User;
import model.UserDataSet;

class ElementField {											// elementJpanel의 배열을 만들기 위해 나눠둔 클래스
	private JPanel[] elementList;										// elementJpanel를 담기 위한 배열
	private ListCreator list;													// list2에 접근하기 위한 List객체
	private int cnt = 0;												// count변수
	
	public ElementField(int ctn, ReservationList reservationList, UserDataSet userDataSet,User user) {								// 생성자
		
		list = new ListCreator(ctn);									// cnt를 초기값으로 하는 list1의 객체를 추출한 list2 생성
		elementList = new JPanel[list.getShowList().size()];			// 배열의 크기를 지정하기 위해 list2의 size를 활용(10으로 초기화 하지 않는 이유는 만들어야할 element가 10개 미만일 경우를 위함)
		
		Iterator<Caregiver> it = list.getShowList().iterator();			// Iterator 사용
		
		while(it.hasNext()) {									// list2에 다음 값이 존재하면 반복
			JPanel element = new JPanel();						// ContentsField에 추가하기 위한 Caregiver객체의 정보를 담을 JPanel
			element.setBounds(10,(cnt*37)+5,780,35);			// 크기 및 위치 지정 (y값은 반복할 수록 내려가도록 설정)
			element.setBorder(new LineBorder(Color.GRAY,1));	// 외곽선 생성
			element.setLayout(null);							// 내부 요소의 위치를 절대값으로 배치하기 위해 Layout을 null로 지정
			element.setBackground(Color.WHITE);
			element.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			
			Caregiver temp = it.next();							// list2의 Caregiver객체를 순차적으로 가져와서 temp에 임시 저장
			
			JTextField tempNameField = new JTextField(temp.getName());	// 이름 출력을 위한 JTextField 생성
			tempNameField.setBounds(10, 8, 60, 20);						// 위치 및 크기 지정
			tempNameField.setEditable(false);							// 값의 수정을 불가능하게 함으로서 출력만 되게끔 설정함
			tempNameField.setHorizontalAlignment(0);					// 글자의 위치를 중앙에 위치하도록 설정
			tempNameField.setBorder(null);	
			tempNameField.setBackground(Color.WHITE);
			tempNameField.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			
			JTextField tempGenderField = new JTextField(temp.getGender());	// 성별정보 출력
			tempGenderField.setBounds(90, 8, 30, 20);
			tempGenderField.setEditable(false);
			tempGenderField.setHorizontalAlignment(0);
			tempGenderField.setBorder(null);
			tempGenderField.setBackground(Color.WHITE);
			tempGenderField.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			
			JTextField tempPriceField = new JTextField(Integer.toString(temp.getRatesPerServiceType()) + "원");	// 시급 정보 출력
			tempPriceField.setBounds(130, 8, 60, 20);
			tempPriceField.setEditable(false);
			tempPriceField.setHorizontalAlignment(0);
			tempPriceField.setBorder(null);
			tempPriceField.setBackground(Color.WHITE);
			tempPriceField.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			
			JTextField tempServiceField = new JTextField(temp.getProvidingServiceTypes());				// 서비스 정보 출력
			tempServiceField.setBounds(200, 8, 80, 20);	
			tempServiceField.setEditable(false);
			tempServiceField.setHorizontalAlignment(0);
			tempServiceField.setBorder(null);
			tempServiceField.setBackground(Color.WHITE);
			tempServiceField.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			
			String score = Double.toString(temp.getReputation());										// 평점 정보 출력
			JTextField tempScoreField = new JTextField(score);
			tempScoreField.setBounds(270, 8, 50, 20);
			tempScoreField.setEditable(false);
			tempScoreField.setHorizontalAlignment(0);
			tempScoreField.setBorder(null);
			tempScoreField.setBackground(Color.WHITE);
			tempScoreField.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
				
			JTextField tempregion = new JTextField(temp.getRegion());									// 지역 정보 출력
			tempregion.setBounds(330, 8, 50, 20);
			tempregion.setEditable(false);
			tempregion.setHorizontalAlignment(0);
			tempregion.setBorder(null);
			tempregion.setBackground(Color.WHITE);
			tempregion.setFont(new Font("나눔스퀘어", Font.BOLD, 12));										//////////////
			
			JTextField tempTrainingComp = new JTextField();												// 교육 여부 출력
			tempTrainingComp.setBounds(410, 8, 50, 20);
			tempTrainingComp.setEditable(false);
			tempTrainingComp.setHorizontalAlignment(0);
			tempTrainingComp.setBorder(null);
			tempTrainingComp.setBackground(Color.WHITE);
			tempTrainingComp.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			
			if(temp.getTrainingCompleted()) {
				tempTrainingComp.setText("이수");
			}else {
				tempTrainingComp.setText("미이수");
			}																							//////////////
			
			JButton tempReviewBtn = new JButton("리뷰");													// 리뷰 버튼
			tempReviewBtn.setBounds(630, 8, 60, 20);
			tempReviewBtn.setBackground(Color.WHITE);
			tempReviewBtn.setBorder(new LineBorder(Color.GRAY, 1));
			tempReviewBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			tempReviewBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new ReviewPage(temp, user);
				}
			});
			
			JButton tempBtn = new JButton("예약");														// 예약 페이지로 넘어가기 위한 예약 버튼
			tempBtn.setBounds(710, 8, 60, 20);
			tempBtn.setBackground(Color.WHITE);
			tempBtn.setBorder(new LineBorder(Color.GRAY, 1));
			tempBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
			tempBtn.addActionListener(new ActionListener() {											// ActionListner 추가
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					// 예약 확인 페이지로 이동
					new ReservationConfirmationPage(temp, reservationList, user);
				}
			});
			
			element.add(tempNameField);					// 생성한 요소드를 Element JPanel에 추가
			element.add(tempGenderField);				// 생성한 요소드를 Element JPanel에 추가
			element.add(tempPriceField);				// 생성한 요소드를 Element JPanel에 추가
			element.add(tempServiceField);				// 생성한 요소드를 Element JPanel에 추가
			element.add(tempScoreField);				// 생성한 요소드를 Element JPanel에 추가
			element.add(tempregion);					// 생성한 요소드를 Element JPanel에 추가
			element.add(tempReviewBtn);					// 
			element.add(tempBtn);						// 생성한 요소드를 Element JPanel에 추가
			element.add(tempTrainingComp);				
			
			getElementList()[cnt] = element;			// 생성한 Element를 배열에 저장
			cnt++;										// count 증가
		}
	}

	public JPanel[] getElementList() {
		return elementList;
	}
}