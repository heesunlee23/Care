package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Caregiver;
import model.CaregiverDataset;
import model.Person;
import model.ReservationList;
import model.User;
import model.UserDataSet;

public class LoginPage extends JFrame {

	private UserDataSet users;
	private CaregiverDataset careUsers;
	private JLabel lblId;
	private JLabel lblPw;
	private JTextField tfId;
	private JPasswordField tfPw;
	private JButton btnLogin;
	private JButton btnJoin;
	private JButton btnCaregiver;
	
	private ReservationList reservationList;

	public LoginPage() {
		init();
		setDisplay();
		addListeners();
		showFrame();
		
	}//생성자
	
	public void init() { //내용 수정: 김민기, 김희진
		reservationList = ReservationList.getInstance();
		users = new UserDataSet();
		careUsers = CaregiverDataset.getInstance();
		Dimension lblSize = new Dimension(70, 30);
		int tfSize = 13; //값 수정_김희진
		
		setSize(210, 100); //로그인 창 설정_김희진

		lblId = new JLabel("아 이 디"); //ID
		lblId.setPreferredSize(lblSize);
		lblId.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		lblPw = new JLabel("비밀번호"); //Password
		lblPw.setPreferredSize(lblSize);
		lblPw.setFont(new Font("나눔스퀘어", Font.BOLD, 13));
		
		tfId = new JTextField(tfSize);
		tfPw = new JPasswordField(tfSize);
		
		btnLogin = new JButton("로그인"); //Login
		btnLogin.setPreferredSize(new Dimension(80, 60));
		btnLogin.setBorder(new LineBorder(Color.ORANGE, 3));
		btnLogin.setBackground(Color.ORANGE);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("나눔스퀘어", Font.BOLD, 13));
		
		btnJoin = new JButton("일반 회원가입"); //Join
		btnJoin.setPreferredSize(new Dimension(130, 27));
		btnJoin.setBackground(new Color(0xFFFACD));
		btnJoin.setForeground(new Color(0x8B5A00));
		btnJoin.setBorder(new LineBorder(Color.ORANGE, 2));
		btnJoin.setFont(new Font("나눔스퀘어", Font.BOLD, 13));
		
		btnCaregiver = new JButton("돌보미 회원가입");//Caregiver
		btnCaregiver.setPreferredSize(new Dimension(130, 27));
		btnCaregiver.setBackground(new Color(0xFFFACD));
		btnCaregiver.setForeground(new Color(0x8B5A00));
		btnCaregiver.setBorder(new LineBorder(Color.ORANGE, 2));
		btnCaregiver.setFont(new Font("나눔스퀘어", Font.BOLD, 13));
		
	}//init
	
	public UserDataSet getUsers() {
		return users;
	}
	
	public void setDisplay() {
		
		//pnlNorth(pnlId, pnlPw)
		JPanel pnlNorth = new JPanel(new GridLayout(2, 6));
		pnlNorth.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		pnlNorth.setBounds(15, 15, -10, 10);
		
		JPanel pnlId = new JPanel();
		pnlId.setBackground(Color.WHITE); //배경색 추가
		pnlId.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		pnlId.setBounds(0, 0, 10, 20);
		pnlId.add(lblId);
		pnlId.add(tfId);
		
		JPanel pnlPw = new JPanel();
		pnlPw.setBackground(Color.WHITE);
		pnlPw.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		pnlPw.setBounds(0, 0, 10, 20);
		pnlPw.add(lblPw);
		pnlPw.add(tfPw);
		
		pnlNorth.add(pnlId);
		pnlNorth.add(pnlPw);
		
		//pnlEast(loginBtn) 내용 추가_김희진
		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(Color.WHITE);
		pnlEast.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		pnlEast.setBorder(new EmptyBorder(5, 10, -5, 10));
		pnlEast.add(btnLogin);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.WHITE); 
		pnlSouth.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		pnlSouth.setBorder(new EmptyBorder(5, 100, 10, 10));
		pnlSouth.add(btnJoin);
		pnlSouth.add(btnCaregiver); // 추가
		
		add(pnlNorth); //North 고정 값 삭제_김희진
		add(pnlEast, BorderLayout.EAST);
		add(pnlSouth, BorderLayout.SOUTH);
		
	}//setDisplay
	
	public void addListeners() {
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new JoinPage(LoginPage.this);
				 tfId.setText("");
				 tfPw.setText("");
			}
		}); 
		
		btnCaregiver.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CaregiverJoinPage(LoginPage.this);
				 tfId.setText("");
				 tfPw.setText("");
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person currentUser = login();// 로그인 메서드 수행 후 로그인 결과에 따라 현재유저변수에 객체 대입
				if(currentUser != null) {	//객체가 null이 아닐경우(로그인 성공)
					if(currentUser instanceof User) {// 로그인한 사용자가 일반 사용자일 경우 조회화면으로 이동
						MainScene searchPage = new MainScene(LoginPage.this,users,currentUser);					
						searchPage.setVisible(true);
					}
					else if(currentUser instanceof Caregiver) {	// 로그인 한 사용자가 서비스 제공자(도우미)일 경우 도우미 마이페이지로 이동 
						CaregiverMyPage caregiverMyPage = new CaregiverMyPage(LoginPage.this, (Caregiver)currentUser, reservationList, careUsers);
						caregiverMyPage.setVisible(true);
					}
					setVisible(false);		//로그인 화면 숨김
					tfId.setText("");		//아이디 입력 필드 초기화
					tfPw.setText("");		//패스워드 입력 필드 초기화
				} 	
			} 
		}); 

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int choice = JOptionPane.showConfirmDialog(LoginPage.this,
						"프로그램을 종료합니다.", "메세지", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if(choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}//if
			}
		}); 
	}
	
	public Person login() {
		Person currentUser;		//객체 반환을 위한 Person객체 추가
		if(tfId.getText().isEmpty()) {		//아이디 입력 필드가 비었을 경우(아이디 입력필요)
			JOptionPane.showMessageDialog(LoginPage.this,
					"아이디를 입력하세요.", "메세지", JOptionPane.WARNING_MESSAGE);
		}else {								//아이디 입력 필드에 값이 있는 경우
			if(users.getUser(tfId.getText()) != null) {	// 입력된 아이디와 일치하는 유저 아이디가 존재하는 경우
				currentUser = users.getUser(tfId.getText());	// 해당 아이디로 찾아낸 유저객체 저장
				if(currentUser.getPw().equals(tfPw.getText())) {	//입력된 pw가 해당 유저의 pw와 일치하는 경우
					return currentUser;		// 유저 객체 반환
				}else {						//pw가 다를 경우 로그인 실패메세지 출력 후 null 반환
					JOptionPane.showMessageDialog(LoginPage.this,
							"비밀번호가 일치하지 않습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					return null;
				}
			}else if (CaregiverDataset.getInstance().getCaregiver(tfId.getText()) != null){		// 서비스 제공자 로그인
				currentUser = CaregiverDataset.getInstance().getCaregiver(tfId.getText());
				if(currentUser.getPw().equals(tfPw.getText())) {
					return currentUser;
				}else {
					JOptionPane.showMessageDialog(LoginPage.this,
							"비밀번호가 일치하지 않습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					return null;
				}
			}else {
				JOptionPane.showMessageDialog(LoginPage.this,
						"아이디가 존재하지 않습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
				return null;
			}
		}
		return null;
	}
	
	public void showFrame() {
		setTitle("케어");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
	}//showFrame
	
}//class
