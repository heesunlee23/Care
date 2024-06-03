package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.CaregiverDataset;
import model.Person;
import model.Reservation;
import model.ReservationList;
import model.User;
import model.UserDataSet;

public class MyPage extends JFrame {
	private LoginPage owner;
	private UserDataSet users;
	private Person user;
	
	private JFrame frame;
	private JPasswordField textField;
	
	private JPanel memberInfoChangePage;
	private JPanel mainMyPage;
	private JPanel paymentHistoryPage;
	private ChargeBalancePage balanceChargePage;
	
	private JButton btnMemberInfoChange;
	private JButton btnPaymentHistory;
	private JButton btnDisbandment;
	private JButton btnGotoMainBoard;
	
	private JButton btnGoBack;
	private JButton btnChange;
	private JButton balanceButton;
	private JButton chatButton;
	
	// 비밀번호 변경 화면에서 마이페이지 화면으로 뒤로가기 버튼
	private JButton btnGoBackFromPasswordChangeToMyPage;
	
	private JLabel labelPaymentHistory;
	private JLabel labelMemberInfoChange;
	private JLabel labelPassword;
	
	private JLabel title1; //서비스 정보 추가_김희진
	private JLabel title2; //사용자 정보 추가_김희진
	private JLabel lbImage;
	
	// paymentHistory List 를 위한 것임
	DefaultListModel<String> listModel;
    JList<String> resultList;
    ReservationList reservationList; 
    
    private MainScene mainScene;
    private JButton btnLogout;
	
	public MyPage( MainScene mainScene, ReservationList reservationList, Person user){
		super();
		
		users = mainScene.getDataSet();
		this.user = user;
		
	    this.reservationList = reservationList;
	    this.mainScene = mainScene;
		init();
		addListeners();
	}

	private void addListeners() {
		btnMemberInfoChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberInfoChangePage.setVisible(true);
				mainMyPage.setVisible(false);
				title1.setVisible(false);
				title2.setVisible(false);
			}
		});
		
		btnPaymentHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentHistoryPage.setVisible(true);
				btnGoBack.setVisible(true); // 추가
				mainMyPage.setVisible(false);
				title1.setVisible(false);
				title2.setVisible(false);
				lbImage.setVisible(false);
				displayPaymentHistory();
			}
		});
		
		btnDisbandment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, 
						"모든 정보가 삭제되며 복구가 불가능합니다.", 
						"정말로 회원 탈퇴를 하시겠습니까?", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE,
						null);
				if(answer == 0) { // 'OK' is 0		        
					// UserDataSet리스트에서 삭제 
					users.withdraw(user);
					CaregiverDataset.getInstance().withdraw(user);
					 
					// 로그인 페이지로 돌아가기
					// 현재 창을 닫음
					dispose();
					
					// 로그인 창 열기 
					owner.setVisible(true);
				}
			}
		});
		
		// 이용내역 조회 페이지에서 마이페이지로 돌아가기
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMyPage.setVisible(true);
				paymentHistoryPage.setVisible(false);
				title1.setVisible(true);
				title2.setVisible(true);
				lbImage.setVisible(true);
			}
		});
		
		// 비밀번호 번경
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String changedPassword = String.valueOf(textField.getPassword());
				if(changedPassword.equals("")) {
					JOptionPane.showMessageDialog(rootPane, "바꾸려는 비밀번호를 입력하지 않았습니다.");
				}else {
					user.setPw(changedPassword);
					memberInfoChangePage.setVisible(false);
					mainMyPage.setVisible(true);
					textField.setText("");
				}
			}
		});
		
		// 비밀번호 변경 화면에서 마이페이지 화면으로 뒤로가기 버튼 누르면 돌아가기 
		btnGoBackFromPasswordChangeToMyPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				memberInfoChangePage.setVisible(false);
				mainMyPage.setVisible(true);
				title1.setVisible(true);
				title2.setVisible(true);
			}
		});
		
		// mainboard로 돌아가기 
		btnGotoMainBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mainScene.setVisible(true);
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(null,
                        "로그아웃 하시겠습니까?",
                        "로그아웃",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null);
                if (answer == 0) {
                    dispose();
                    mainScene.getOwner().setVisible(true);
                    
                }
            }
        });
		
		balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	balanceChargePage = new ChargeBalancePage(MyPage.this, (User) user);
            	setEnabled(false);
            	balanceChargePage.setVisible(true);
            }
        });
		
        // 채팅 기능
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new ChatServer();
                new ChatClient();
            }
        });
	}
	
	/*
	 * 사용자의 예약 내역을 검색하고, 그 결과를 JList에 업데이트하는 메서드
	 */
	private void displayPaymentHistory() {
		// 현재 사용자의 ID로 예약 내역을 검색
		ArrayList<Reservation> results = reservationList.searchReservationById(user.getId());

		// 기존의 리스트 항목을 모두 제거합니다. 이는 이전에 표시된 데이터를 지우기 위함입니다.
        listModel.clear();
        // results 리스트를 순회하면서 각 Reservation 객체의 문자열 표현을 listModel에 추가합니다.
        // reservation.toString() 메서드는 Reservation 객체를 문자열로 변환하여 리스트 항목으로 추가합니다.
        for (Reservation reservation : results) {
            listModel.addElement(reservation.toString());
        }

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "해당 아이디의 이용내역이 없습니다.");
        }
	}

	private void init() { 
		setTitle("마이페이지");
		setBounds(100, 100, 1280, 1280/16*9);
		setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//////////////////////// 페이지들 시작 /////////////////////////////
		title1 = new JLabel("서비스 정보");
		title1.setFont(new Font("나눔스퀘어", Font.BOLD, 18));
		title1.setBounds(130, 50, 200, 30);
		add(title1);
		
		title2 = new JLabel("사용자 정보");
		title2.setFont(new Font("나눔스퀘어", Font.BOLD, 18));
		title2.setBounds(130, 250, 200, 30);
		add(title2);
		
		ImageIcon image = new ImageIcon("src\\MyPageLogo.png");
		lbImage = new JLabel(image);
		lbImage.setBounds(790, 0, 450, 410);
		add(lbImage);
		
		mainMyPage = new JPanel();
		mainMyPage.setBounds(0, 0, 1280, 1280/16*9);
		mainMyPage.setBackground(new Color(0xF0F8FF));
		mainMyPage.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		getContentPane().add(mainMyPage);
		mainMyPage.setLayout(null);
	
		memberInfoChangePage = new JPanel();
		memberInfoChangePage.setBounds(0, 0, 1280, 1280/16*9);
		memberInfoChangePage.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		memberInfoChangePage.setBackground(new Color(0xF0F8FF));
		getContentPane().add(memberInfoChangePage);
		
		paymentHistoryPage = new JPanel();
		paymentHistoryPage.setBounds(0, 0, 1280, 1280/16*9);
		paymentHistoryPage.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		paymentHistoryPage.setBackground(new Color(0xF0F8FF));
		getContentPane().add(paymentHistoryPage);
		paymentHistoryPage.setLayout(null);
		
		/////////////////////////// 페이지들 끝 /////////////////////////
		
		//////////////////////////// 마이페이지에 있는 버튼들 //////////////
		btnPaymentHistory = new JButton("이용내역 조회");
		btnPaymentHistory.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		btnPaymentHistory.setBounds(120, 90, 200, 60);
		btnPaymentHistory.setBackground(Color.WHITE);
		mainMyPage.add(btnPaymentHistory);
		
        chatButton = new JButton("상담");
        chatButton.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
        chatButton.setBounds(350, 90, 200, 60);
        chatButton.setBackground(Color.WHITE);
        mainMyPage.add(chatButton);
		
		btnMemberInfoChange = new JButton("회원정보 변경");
		btnMemberInfoChange.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		btnMemberInfoChange.setBounds(120, 290, 200, 60);
		btnMemberInfoChange.setBackground(Color.WHITE);
		mainMyPage.add(btnMemberInfoChange);
		
        balanceButton = new JButton("잔액 충전");
        balanceButton.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
        balanceButton.setBounds(350, 290, 200, 60);
        balanceButton.setBackground(Color.WHITE);
        mainMyPage.add(balanceButton);
		
		btnDisbandment = new JButton("회원 탈퇴");
		btnDisbandment.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		btnDisbandment.setBounds(580, 290, 200, 60);
		btnDisbandment.setBackground(Color.WHITE);
		mainMyPage.add(btnDisbandment);
		//////////////////////////////////////////
		
		///////// '이용내역 조회' 버튼 누르면 뜨는 창에 있는 것 ////////////////
		labelPaymentHistory = new JLabel("이용내역 조회");
		labelPaymentHistory.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		labelPaymentHistory.setHorizontalAlignment(SwingConstants.CENTER);
		labelPaymentHistory.setBounds(550, 38, 144, 63);
		paymentHistoryPage.add(labelPaymentHistory);
		
		////////////////////////////// 이용내역 조회 화면 /////////
		// 예약 리스트가 담긴 리스트를 생성 
		// DefaultListModel은 리스트 데이터를 저장하는 데 사용됩니다. 리스트 항목을 추가, 제거 및 수정할 수 있습니다.
        listModel = new DefaultListModel<>();
        // JList는 리스트 항목을 시각적으로 표시하는 컴포넌트입니다. 
        // 여기서는 DefaultListModel 객체를 사용하여 데이터를 제공합니다.
        resultList = new JList<>(listModel);
        // JScrollPane은 스크롤 가능한 뷰를 제공하는 컴포넌트입니다. 
        // JList를 JScrollPane에 넣음으로써 리스트 항목이 많아져도 스크롤을 통해 모두 볼 수 있게 합니다.
	    JScrollPane scrollPane = new JScrollPane(resultList);
	    scrollPane.setBounds(50, 100, 1150, 300);
		paymentHistoryPage.add(scrollPane);

		////////// 이용내역 조회 페이지에서 마이페이지로 돌아가기 //////////////////
		btnGoBack = new JButton("뒤로가기");
		btnGoBack.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		btnGoBack.setBackground(new Color(0x1E90FF));
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setBounds(550, 450, 150, 50);
		btnGoBack.setVisible(false); // 추가 
		paymentHistoryPage.add(btnGoBack);
		
		// 비밀번호 변경 
		btnChange = new JButton("변경");
		btnChange.setBounds(264, 447, 271, 73);
		btnChange.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		btnChange.setBackground(Color.WHITE);
		memberInfoChangePage.setLayout(null);
		memberInfoChangePage.add(btnChange);
		
		btnGoBackFromPasswordChangeToMyPage = new JButton("뒤로가기");
		btnGoBackFromPasswordChangeToMyPage.setBounds(264 + 271 + 50, 447, 271, 73);
		btnGoBackFromPasswordChangeToMyPage.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		btnGoBackFromPasswordChangeToMyPage.setBackground(new Color(0x1E90FF));
		btnGoBackFromPasswordChangeToMyPage.setForeground(Color.WHITE);
		memberInfoChangePage.setLayout(null);
		memberInfoChangePage.add(btnGoBackFromPasswordChangeToMyPage);
		
		labelMemberInfoChange = new JLabel("회원정보 변경");
		labelMemberInfoChange.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		labelMemberInfoChange.setHorizontalAlignment(SwingConstants.CENTER);
		labelMemberInfoChange.setBounds(315, 41, 176, 73);
		memberInfoChangePage.add(labelMemberInfoChange);
		
		labelPassword = new JLabel("새 비밀번호");
		labelPassword.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		labelPassword.setBounds(220, 230, 100, 26);
		memberInfoChangePage.add(labelPassword);
		
		textField = new JPasswordField();
		textField.setBounds(350, 224, 230, 42);
		textField.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		memberInfoChangePage.add(textField);
		textField.setColumns(10);
		
		// mainMyPage 빼고 안보이게 설정  
		memberInfoChangePage.setVisible(false);
		
		// Mypage에서 MainBoard로 돌아가기 
		btnGotoMainBoard = new JButton("뒤로가기");
		btnGotoMainBoard.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		btnGotoMainBoard.setBounds(401, 475, 210, 55);
		btnGotoMainBoard.setBackground(new Color(0x1E90FF));
		btnGotoMainBoard.setForeground(Color.WHITE);
		mainMyPage.add(btnGotoMainBoard);
		
		btnLogout = new JButton("로그아웃");
        btnLogout.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        btnLogout.setBounds(680, 475, 210, 55);
        btnLogout.setBackground(new Color(0x1E90FF));
        btnLogout.setForeground(Color.WHITE);
        mainMyPage.add(btnLogout);
	}
}
