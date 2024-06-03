package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.CaregiverDataset;
import model.User;
import model.UserDataSet;

public class JoinPage extends JDialog {
	private LoginPage owner;
	private UserDataSet users;
	private CaregiverDataset members;

	private JLabel lblTitle;
	private JLabel lblId;
	private JLabel lblPw;
	private JLabel lblRe; // 비밀번호 일치 확인용
	private JLabel lblName;
	private JLabel lblNName;

	private JRadioButton rbtnMale; // 성별 버튼
	private JRadioButton rbtnFemale;
	private JTextField tfId;
	private JPasswordField tfPw;
	private JPasswordField tfRe;
	private JTextField tfName;
	private JTextField tfNName;
	private JButton btnJoin;
	private JButton btnCancel;

	public JoinPage(LoginPage owner) {
		super(owner, "회원가입", true);
		this.owner = owner;
		users = owner.getUsers();
		members = CaregiverDataset.getInstance();

		init();
		setDisplay();
		addListener();
		showFrame();

	}// JoinPage()

	public void init() { // 내용 수정: 김민기, 김희진
		int tfSize = 10; // 필드 사이즈 10

		Dimension lblSize = new Dimension(100, 35);
		Dimension btnSize = new Dimension(100, 25);

		// 라벨
		lblTitle = new JLabel("[회원가입 정보입력]");
		lblTitle.setFont(new Font("나눔스퀘어", Font.BOLD, 14)); // 폰트 지정

		lblId = new JLabel("아이디*", JLabel.LEFT);
		lblId.setPreferredSize(lblSize);
		lblId.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		lblPw = new JLabel("비밀번호*", JLabel.LEFT);
		lblPw.setPreferredSize(lblSize);
		lblPw.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		lblRe = new JLabel("비밀번호 확인*", JLabel.LEFT);
		lblRe.setPreferredSize(lblSize);
		lblRe.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		lblName = new JLabel("성 함*", JLabel.LEFT);
		lblName.setPreferredSize(lblSize);
		lblName.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		lblNName = new JLabel("닉네임*", JLabel.LEFT);
		lblNName.setPreferredSize(lblSize);
		lblNName.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		// 텍스트필드
		tfId = new JTextField(tfSize);
		tfId.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		tfPw = new JPasswordField(tfSize);
		tfPw.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		tfRe = new JPasswordField(tfSize);
		tfRe.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		tfName = new JTextField(tfSize);
		tfName.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		tfNName = new JTextField(tfSize);
		tfNName.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		// 라디오버튼(성별)
		rbtnMale = new JRadioButton("남성", true);
		rbtnMale.setBackground(Color.WHITE);
		rbtnMale.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		rbtnFemale = new JRadioButton("여성");
		rbtnFemale.setBackground(Color.WHITE);
		rbtnFemale.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);

		// 회원가입, 돌아가기 버튼
		btnJoin = new JButton("회원가입"); //Join
		btnJoin.setPreferredSize(btnSize);
		btnJoin.setBorder(new LineBorder(Color.ORANGE, 2));
		btnJoin.setBackground(new Color(0xFFFACD));
		btnJoin.setForeground(new Color(0x8B5A00));
		btnJoin.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		btnCancel = new JButton("돌아가기"); //Cancle
		btnCancel.setPreferredSize(btnSize);
		btnCancel.setBorder(new LineBorder(Color.ORANGE, 2));
		btnCancel.setBackground(new Color(0xFFFACD));
		btnCancel.setForeground(new Color(0x8B5A00));
		btnCancel.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

	}// init()

	public void setDisplay() {
		// FlowLayout 왼쪽 정렬
FlowLayout flowLeft = new FlowLayout(FlowLayout.LEFT);
		
		//pnlMain(pnlMNorth / pnlMCenter / pnlMSouth)
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.setBackground(new Color(0xFFD700));
		pnlMain.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		
		//pnlMNorth(lblTitle)
		JPanel pnlMNorth = new JPanel(flowLeft);
		pnlMNorth.setBackground(Color.WHITE);
		pnlMNorth.add(lblTitle);
		pnlMNorth.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		
		//pnlMCenter(pnlId / pnlPw / pnlRe / pnlName / pnlNName)
		JPanel pnlMCenter = new JPanel(new GridLayout(0, 1)); //0행 1열
		pnlMCenter.setBackground(Color.WHITE);
		pnlMCenter.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		
		JPanel pnlId = new JPanel(flowLeft);
		pnlId.setBackground(Color.WHITE);
		pnlId.add(lblId);
		pnlId.add(tfId);
		pnlId.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		
		JPanel pnlPw = new JPanel(flowLeft);
		pnlPw.setBackground(Color.WHITE);
		pnlPw.add(lblPw);
		pnlPw.add(tfPw);
		pnlPw.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		
		JPanel pnlRe = new JPanel(flowLeft);
		pnlRe.setBackground(Color.WHITE);
		pnlRe.add(lblRe);
		pnlRe.add(tfRe);
		pnlRe.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		
		JPanel pnlName = new JPanel(flowLeft);
		pnlName.setBackground(Color.WHITE);
		pnlName.add(lblName);
		pnlName.add(tfName);
		pnlName.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		
		JPanel pnlNName = new JPanel(flowLeft);
		pnlNName.setBackground(Color.WHITE);
		pnlNName.add(lblNName);
		pnlNName.add(tfNName);
		pnlNName.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		pnlMCenter.add(pnlId);
		pnlMCenter.add(pnlPw);
		pnlMCenter.add(pnlRe);
		pnlMCenter.add(pnlName);
		pnlMCenter.add(pnlNName);

		// pnlMSouth(rbtnMale / rbtnFemale)
		JPanel pnlMSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlMSouth.setBackground(Color.WHITE);
		pnlMSouth.add(rbtnMale);
		pnlMSouth.add(rbtnFemale);
		pnlMSouth.setBorder(new TitledBorder("성별")); //Gender
		pnlMSouth.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		// pnlMain
		pnlMain.add(pnlMNorth, BorderLayout.NORTH);
		pnlMain.add(pnlMCenter, BorderLayout.CENTER);
		pnlMain.add(pnlMSouth, BorderLayout.SOUTH);

		// pnlSouth(btnJoin / btnCancel)
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.WHITE);
		pnlSouth.add(btnJoin);
		pnlSouth.add(btnCancel);
		pnlSouth.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		// pnlMain, pnlSouth: EmptyBorder(); 메서드로 화면 테두리의 간격 부여
		pnlMain.setBorder(new EmptyBorder(0, 20, 0, 20));
		pnlSouth.setBorder(new EmptyBorder(0, 0, 10, 0));

		add(pnlMain, BorderLayout.NORTH);
		add(pnlSouth, BorderLayout.SOUTH);

	}// setDisplay()

	private void addListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose(); // 해당 메서드로 프레임에 붙은 내용 삭제
				owner.setVisible(true);
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose(); // 해당 메서드로 프레임에 붙은 내용 삭제
				owner.setVisible(true);
			}
		});
		// 가입하기 버튼 이벤트
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (isBlank()) { // 정보 하나라도 비어있으면
					JOptionPane.showMessageDialog(JoinPage.this, "* 표시는 필수 입력항목입니다.");
				} // if
				else { // 모두 입력했을 때
						// ID 중복일 때
					if (members.isIdOverlap(tfId.getText()) || users.isIdOverlap(tfId.getText())) {
						JOptionPane.showMessageDialog(JoinPage.this, "사용중인 ID입니다. 다시 입력해주세요.");
						tfId.requestFocus();
					} // if
						// Pw != Re
					else if (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()))) {
						JOptionPane.showMessageDialog(JoinPage.this, "입력한 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
						tfPw.requestFocus();
					} // else if
					else {
						User temp = new User(tfId.getText(), String.valueOf(tfPw.getPassword()), tfName.getText(),
								tfNName.getText(), getGender());

//						temp.setSaving(100000);		//삭제예정

						users.addUser(temp);
						JOptionPane.showMessageDialog(JoinPage.this, "회원가입이 완료되었습니다.");
						dispose(); // 해당 메서드로 프레임에 붙은 내용 삭제
						owner.setVisible(true);
					} // else
				} // else
			}
		});
	}

	public boolean isBlank() {
		boolean result = false;

		if (tfId.getText().isEmpty()) {
			tfId.requestFocus();
			return true;
		}
		if (String.valueOf(tfPw.getPassword()).isEmpty()) {
			tfPw.requestFocus();
			return true;
		}
		if (String.valueOf(tfRe.getPassword()).isEmpty()) {
			tfRe.requestFocus();
			return true;
		}
		if (tfName.getText().isEmpty()) {
			tfName.requestFocus();
			return true;
		}
		if (tfNName.getText().isEmpty()) {
			tfNName.requestFocus();
			return true;
		}
		return result;

	}// isBlank

	public String getGender() {
		if (rbtnMale.isSelected()) { // isSelected(): 체크박스 선택
			return rbtnMale.getText();
		} // if
		return rbtnFemale.getText();
	}// getGender

	public void showFrame() {
		pack();
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}// showFrame()

}// class
