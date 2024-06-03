package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import model.Caregiver;
import model.CaregiverDataset;
import model.UserDataSet;

public class CaregiverJoinPage extends JDialog {

	private LoginPage owner;
	private UserDataSet users;
	private CaregiverDataset members;

	private JLabel clblTitle;
	private JLabel clblId;
	private JLabel clblPw;
	private JLabel clblRe; // 비밀번호 일치 확인용
	private JLabel clblName;
	private JLabel clblNName;
	private JLabel clblService;
	// 김현성 추가====
	private JLabel clblPrice;
	private JLabel clblRegion;

	private JTextField tfPrice;
	private CheckboxGroup regionGrop;
	private Checkbox r1, r2, r3, r4, r5, r6;

	private JPanel tfRegion;
	private String region = "";

	private JRadioButton rbtnMale; // 성별 버튼
	private JRadioButton rbtnFemale;
	private JTextField tfId;
	private JPasswordField tfPw;
	private JPasswordField tfRe;
	private JTextField tfName;
	private JTextField tfNName;
	private JPanel tfService;
	private JButton btnJoin;
	private JButton btnCancel;
	private CheckboxGroup serviceChoice;
	private Checkbox s1, s2, s3, s4, s5;
	private String service = "";

	public CaregiverJoinPage(LoginPage loginPage) {
		super(loginPage, "회원가입", true);
		owner = loginPage;
		users = loginPage.getUsers();
		members = CaregiverDataset.getInstance();

		init();
		setDisplay();
		addListener();
		showFrame();
	}// CaregiverJoinPage

	public void init() { // 내용 수정: 김민기, 김희진
		int tfSize = 10;
		Dimension lblSize = new Dimension(100, 35);
		Dimension btnSize = new Dimension(100, 25);

		clblTitle = new JLabel("[돌보미 회원정보]");
		clblTitle.setFont(new Font("나눔스퀘어", Font.BOLD, 14)); // 폰트 지정

		clblId = new JLabel("아이디*", JLabel.LEFT);
		clblId.setBackground(Color.WHITE);
		clblId.setPreferredSize(lblSize);
		clblId.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		clblPw = new JLabel("비밀번호*", JLabel.LEFT);
		clblPw.setBackground(Color.WHITE);
		clblPw.setPreferredSize(lblSize);
		clblPw.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		clblRe = new JLabel("비밀번호 확인*", JLabel.LEFT);
		clblRe.setBackground(Color.WHITE);
		clblRe.setPreferredSize(lblSize);
		clblRe.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		clblName = new JLabel("성 함*", JLabel.LEFT);
		clblName.setBackground(Color.WHITE);
		clblName.setPreferredSize(lblSize);
		clblName.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		clblNName = new JLabel("닉네임*", JLabel.LEFT);
		clblNName.setBackground(Color.WHITE);
		clblNName.setPreferredSize(lblSize);
		clblNName.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		clblService = new JLabel("서비스*", JLabel.LEFT);
		clblService.setBackground(Color.WHITE);
		clblService.setPreferredSize(lblSize);
		clblService.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		clblPrice = new JLabel("시 급", JLabel.LEFT);
		clblPrice.setBackground(Color.WHITE);
		clblPrice.setPreferredSize(lblSize);
		clblPrice.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		clblRegion = new JLabel("지 역*", JLabel.LEFT);
		clblRegion.setBackground(Color.WHITE);
		clblRegion.setPreferredSize(lblSize);
		clblRegion.setFont(new Font("나눔스퀘어", Font.BOLD, 13));

		serviceChoice = new CheckboxGroup();

		s1 = new Checkbox("콜 택시", serviceChoice, false);
		s2 = new Checkbox("베이비시터", serviceChoice, false);
		s3 = new Checkbox("요양보호사", serviceChoice, false);
		s4 = new Checkbox("간병인", serviceChoice, false);
		s5 = new Checkbox("가사도우미", serviceChoice, false);
		// 김현성 추가
		regionGrop = new CheckboxGroup();

		r1 = new Checkbox("서울", regionGrop, false);
		r1.setBackground(Color.WHITE);
		r2 = new Checkbox("인천", regionGrop, false);
		r2.setBackground(Color.WHITE);
		r3 = new Checkbox("대구", regionGrop, false);
		r3.setBackground(Color.WHITE);
		r4 = new Checkbox("광주", regionGrop, false);
		r4.setBackground(Color.WHITE);
		r5 = new Checkbox("부산", regionGrop, false);
		r5.setBackground(Color.WHITE);
		r6 = new Checkbox("천안", regionGrop, false);
		r6.setBackground(Color.WHITE);
		//

		tfId = new JTextField(tfSize);
		tfPw = new JPasswordField(tfSize);
		tfRe = new JPasswordField(tfSize);
		tfName = new JTextField(tfSize);
		tfNName = new JTextField(tfSize);
		tfPrice = new JTextField(tfSize); // 김현성 추가

		tfService = new JPanel();
		tfService.add(s1);
		tfService.add(s2);
		tfService.add(s3);
		tfService.add(s4);
		tfService.add(s5);
		tfService.setBackground(Color.WHITE);
		tfService.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		// 김현성 추가
		tfRegion = new JPanel();
		tfRegion.add(r1);
		tfRegion.add(r2);
		tfRegion.add(r3);
		tfRegion.add(r4);
		tfRegion.add(r5);
		tfRegion.add(r6);
		tfRegion.setBackground(Color.WHITE);

		rbtnMale = new JRadioButton("남성", true);
		rbtnMale.setBackground(Color.WHITE);
		rbtnMale.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

		rbtnFemale = new JRadioButton("여성");
		rbtnFemale.setBackground(Color.WHITE);
		rbtnFemale.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);

		btnJoin = new JButton("회원가입"); // Join
		btnJoin.setPreferredSize(btnSize);
		btnJoin.setBorder(new LineBorder(Color.ORANGE, 2));
		btnJoin.setBackground(new Color(0xFFFACD));
		btnJoin.setForeground(new Color(0xCD8500));
		btnJoin.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

		btnCancel = new JButton("돌아가기"); // Cancle
		btnCancel.setPreferredSize(btnSize);
		btnCancel.setBorder(new LineBorder(Color.ORANGE, 2));
		btnCancel.setBackground(new Color(0xFFFACD));
		btnCancel.setForeground(new Color(0xCD8500));
		btnCancel.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

	}// init

	public void setDisplay() {
		// FlowLayout 왼쪽 정렬
		FlowLayout flowLeft = new FlowLayout(FlowLayout.LEFT);

		// pnlMain(pnlMNorth / pnlMCenter / pnlMSouth)
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.setBackground(new Color(0xFFD700));
		pnlMain.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		// pnlMNorth(lblTitle)
		JPanel pnlMNorth = new JPanel(flowLeft);
		pnlMNorth.setBackground(Color.WHITE);
		pnlMNorth.add(clblTitle);
		pnlMNorth.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		// pnlMCenter(pnlId / pnlPw / pnlRe / pnlName / pnlNName)
		JPanel pnlMCenter = new JPanel(new GridLayout(0, 1));
		pnlMCenter.setBackground(Color.WHITE);
		pnlMCenter.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		JPanel pnlId = new JPanel(flowLeft);
		pnlId.setBackground(Color.WHITE);
		pnlId.add(clblId);
		pnlId.add(tfId);
		pnlId.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		JPanel pnlPw = new JPanel(flowLeft);
		pnlPw.setBackground(Color.WHITE);
		pnlPw.add(clblPw);
		pnlPw.add(tfPw);
		pnlPw.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		JPanel pnlRe = new JPanel(flowLeft);
		pnlRe.setBackground(Color.WHITE);
		pnlRe.add(clblRe);
		pnlRe.add(tfRe);
		pnlRe.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		JPanel pnlName = new JPanel(flowLeft);
		pnlName.setBackground(Color.WHITE);
		pnlName.add(clblName);
		pnlName.add(tfName);

		JPanel pnlNName = new JPanel(flowLeft);
		pnlNName.setBackground(Color.WHITE);
		pnlNName.add(clblNName);
		pnlNName.add(tfNName);
		pnlNName.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		// 김현성 추가
		JPanel pnlPrice = new JPanel(flowLeft);
		pnlPrice.setBackground(Color.WHITE);
		pnlPrice.add(clblPrice);
		pnlPrice.add(tfPrice);
		pnlPrice.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		JPanel pnlService = new JPanel(flowLeft);
		pnlService.setBackground(Color.WHITE);
		pnlService.add(clblService);
		pnlService.add(tfService);
		pnlService.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		// 김현성 추가
		JPanel pnlRegion = new JPanel(flowLeft);
		pnlRegion.setBackground(Color.WHITE);
		pnlRegion.add(clblRegion);
		pnlRegion.add(tfRegion);
		pnlRegion.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));

		pnlMCenter.add(pnlId);
		pnlMCenter.add(pnlPw);
		pnlMCenter.add(pnlRe);
		pnlMCenter.add(pnlName);
		pnlMCenter.add(pnlNName);
		pnlMCenter.add(pnlService);

		// 김현성 추가
		pnlMCenter.add(pnlPrice);
		pnlMCenter.add(pnlRegion);

		// pnlMSouth(rbtnMale / rbtnFemale)
		JPanel pnlMSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlMSouth.setBackground(Color.WHITE);
		pnlMSouth.add(rbtnMale);
		pnlMSouth.add(rbtnFemale);
		pnlMSouth.setBorder(new TitledBorder("성 별")); // Gender
		pnlMSouth.setFont(new Font("나눔스퀘어", Font.BOLD, 14));

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

		pnlMain.setBorder(new EmptyBorder(0, 20, 0, 20));
		pnlSouth.setBorder(new EmptyBorder(0, 0, 10, 0));

		add(pnlMain, BorderLayout.NORTH);
		add(pnlSouth, BorderLayout.SOUTH);
	}// setDisplay

	private void addListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
				owner.setVisible(true);
			}
		});

		s1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					service = "콜 택시";
				}

			}
		});
		s1.setBackground(Color.WHITE);

		s2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					service = "베이비시터 ";
				}
			}
		});
		s2.setBackground(Color.WHITE);
		
		s3.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					service = "요양보호사";
				}
			}
		});
		s3.setBackground(Color.WHITE);
		
		s4.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					service = "간병인";
				}
			}
		});
		s4.setBackground(Color.WHITE);
		
		s5.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					service = "가사도우미";
				}

			}
		});
		s5.setBackground(Color.WHITE);

		
		//김현성 추가==================================================================================
		r1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					region = "서울";
				}
			}
		}); r1.setBackground(Color.WHITE);

		r2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					region = "인천";
				}
			}
		}); r2.setBackground(Color.WHITE);

		r3.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					region = "대구";
				}
			}
		}); r3.setBackground(Color.WHITE);
		
		r4.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					region = "광주";
				}
			}
		}); r4.setBackground(Color.WHITE);
		
		r5.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					region = "부산";
				}
			}
		}); r5.setBackground(Color.WHITE);
		
		r6.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					region = "천안";
				}
			}
		}); r6.setBackground(Color.WHITE);
		
		//==================================================================================

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
				owner.setVisible(true);
			}
		});

		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (isBlank()) { // 정보 하나라도 비어있으면
					JOptionPane.showMessageDialog(CaregiverJoinPage.this, "* 표시는 필수 입력항목입니다.");
				} // if
				else { // 모두 입력했을 때
						// ID 중복일 때
					if (users.isIdOverlap(tfId.getText())) {
						JOptionPane.showMessageDialog(CaregiverJoinPage.this, "사용중인 ID입니다. 다시 입력해주세요.");
						tfId.requestFocus();
					} // if
						// Pw != Re
					else if (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()))) {
						JOptionPane.showMessageDialog(CaregiverJoinPage.this, "입력한 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
						tfPw.requestFocus();
					} // else if
					else if (service == "") {
						JOptionPane.showMessageDialog(CaregiverJoinPage.this, "서비스는 필수 체크 사항입니다");
					} // else if

					else {
						int price;												//김현성 추가
						if(tfPrice.getText().isEmpty()) {						//
							price = 9860;										//
						}else {													//
							price = Integer.parseInt(tfPrice.getText());		//
						}
						
						String pw = new String(tfPw.getPassword());
						members.addMember(new Caregiver(tfId.getText(), pw, tfName.getText(), getGender(),
								service, region, price, 0));

						JOptionPane.showMessageDialog(CaregiverJoinPage.this, "회원가입이 완료되었습니다.");
						dispose();
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
		} // if
		if (String.valueOf(tfPw.getPassword()).isEmpty()) {
			tfPw.requestFocus();
			return true;
		} // if
		if (String.valueOf(tfRe.getPassword()).isEmpty()) {
			tfRe.requestFocus();
			return true;
		} // if
		if (tfName.getText().isEmpty()) {
			tfName.requestFocus();
			return true;
		}
		if (tfNName.getText().isEmpty()) {
			tfNName.requestFocus();
			return true;
		} // if
		if(region.equals("")) {
			tfRegion.requestFocus();
			return true;
		}
		return result;

	}// isBlank

	public String getGender() {
		if (rbtnMale.isSelected()) {
			return rbtnMale.getText();
		} // if
		return rbtnFemale.getText();
	}// getGender

	public void showFrame() {
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}// showFrame

}// class
