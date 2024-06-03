package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.ListCreator;
import model.SearchInfo;

public class SearchField {
	private JPanel searchField;					// 검색 필드
	
	private JTextField searchName;				// 검색할 이름을 입력할 필드
	private JButton searchBtn;					// 검색을 실시할 버튼
	private JComboBox<String> service;			// 서비스목록을 가진 콤보박스
	private JTextField priceField;				// 검색할 시급을 입력할 필드
	private JComboBox<String> gender;			// 성별목록을 가진 콤보박스
	private JComboBox<String> region;			// 지역목록을 가진 콤보박스
	
	public SearchField(ContentsField content) {
		searchField = new JPanel();		// JPanel 생성
		
		searchField.setBorder(new LineBorder(Color.LIGHT_GRAY, 1)); // 외곽선 설정
		searchField.setBounds(240, 50, 800, 100);
		searchField.setLayout(null);
		searchField.setBackground(new Color(0xF0F8FF));
		searchField.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		
		searchName = new JTextField();	//이름 검색
		searchName.setBounds(10, 10, 700, 40);
		searchName.setFont(new Font(null, 0, 20));
		searchName.setBackground(Color.WHITE); //검색창 색깔
		searchName.setFont(new Font("나눔스퀘어", Font.PLAIN, 13));
		
		searchBtn = new JButton("검색");					//검색 버튼
		searchBtn.setBounds(720, 10, 70, 40);
		searchBtn.setFont(new Font(null, 0, 18));
		searchBtn.setBackground(Color.BLUE);
		searchBtn.setForeground(Color.WHITE); 			//폰트 색상 지정
		searchBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		searchBtn.addActionListener(new ActionListener() {	// 검색 버튼 ActionListner
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
				content.setCnt(0);
				content.reLoad();
			}
		});
		
		JLabel serviceTag = new JLabel("서비스 종류");  // 서비스 검색
        serviceTag.setBounds(5, 60, 80, 30);  // 크기 설정
        serviceTag.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

        String[] serviceList = {"선택", "콜 택시", "베이비시터", "요양보호사", "간병인", "가사도우미"};
        service = new JComboBox<>(serviceList);
        service.setBounds(90, 60, 140, 30);  // 크기 설정
        service.setBackground(Color.WHITE);
        service.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

        JLabel priceTag = new JLabel("시급");  // 가격 검색
        priceTag.setBounds(235, 60, 40, 30);
        priceTag.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

        priceField = new JTextField();
        priceField.setBounds(280, 60, 80, 30);  // 크기 설정
        priceField.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

        JLabel genderTag = new JLabel("성별");  // 성별 검색
        genderTag.setBounds(375, 60, 40, 30);  // 위치 조정
        genderTag.setBackground(Color.WHITE);
        genderTag.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

        String[] genderList = {"선택", "남", "여"};
        gender = new JComboBox<>(genderList);
        gender.setBounds(420, 60, 80, 30);  // 크기 설정
        gender.setBackground(Color.WHITE);
        gender.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

        JLabel regionTag = new JLabel("지역");
        regionTag.setBounds(505, 60, 40, 30);  // 위치 조정
        regionTag.setBackground(Color.WHITE);
        regionTag.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

        String[] regionList = {"선택", "서울", "인천", "대구", "광주", "부산", "천안"};
        region = new JComboBox<>(regionList);
        region.setBounds(550, 60, 80, 30);  // 크기 설정
        region.setBackground(Color.WHITE);
        region.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
	
        // 만들어진 컴포넌트 searchField에 추가
		searchField.add(searchName);
		searchField.add(searchBtn);
		searchField.add(serviceTag);
		searchField.add(service);
		searchField.add(priceTag);
		searchField.add(priceField);
		searchField.add(genderTag);
		searchField.add(gender);
		searchField.add(regionTag);
		searchField.add(region);
	}
	
	public void search() {		// info객체 생성을 위한 메서드
		String nameInfo = null;	// 이름 검색조건 선언 및 초기화
		int priceInfo = -1;		// 시급 검색조건 선언 및 초기화
		if(!(searchName.getText().equals(""))) {	//입력받은 값이 있으면 검색조건에 저장
			nameInfo = searchName.getText();
		}
		String serviceInfo = service.getItemAt(service.getSelectedIndex());	//서비스 검색조건 선언 및 저장
		if(priceField.getText().matches("^[\\d]*$")) {						// 시급 검색조건이 문자값이 아니라면 내부코드 실행
			if(!(priceField.getText().equals(""))) {						// 시급 검색조건이 있으면 검색조건에 저장
				priceInfo = Integer.parseInt(priceField.getText());
			}
		}
		String genderInfo = gender.getItemAt(gender.getSelectedIndex());	// 성별 검색조건 선언 및 저장
		
		String regionInfo = region.getItemAt(region.getSelectedIndex());	// 지역 검색조건 선언 및 저장
		
		ListCreator.info = new SearchInfo(nameInfo, serviceInfo, priceInfo, genderInfo,regionInfo); //저장한 값들을 매개변수로 info객체 업데이트
	}

	public Component getSearchField() {
		// TODO Auto-generated method stub
		return searchField;
	}
}