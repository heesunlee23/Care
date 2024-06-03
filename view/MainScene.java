package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import model.Person;
import model.ReservationList;
import model.User;
import model.UserDataSet;


public class MainScene extends JFrame{
	// 마이페이지로 넘어가기 위해 owner 추가 
	private ReservationList reservationList;
	private UserDataSet dataSet;
	private LoginPage owner;
	
	public MainScene(LoginPage owner,UserDataSet dataSet,Person user) {
		super("서비스 검색");
		 
		this.dataSet = dataSet;
		this.owner = owner;
		
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setSize(1280,1280/16*9);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		
		reservationList = ReservationList.getInstance();
		ContentsField content = new ContentsField(reservationList,dataSet,(User) user);
		
		SearchField search = new SearchField(content);
		
		add(content.getContentsField());
		add(search.getSearchField());
		add(content.getIndexField());
		add(content.getFields());

		setVisible(true);
		
		// 마이페이지로 이동
		JButton myPageBtn = new JButton("마이페이지");
		myPageBtn.setBounds(1280/2 - 200, 8, 150, 33);
		myPageBtn.setBackground(new Color(0x1E90FF));
		myPageBtn.setForeground(Color.WHITE);
		myPageBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		myPageBtn.addActionListener(new ActionListener() {											// ActionListner 추가
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyPage myPage = new MyPage(MainScene.this, reservationList,user);
				setVisible(false);
				myPage.setVisible(true);
			}
		});
		add(myPageBtn);
		
		JButton goToMapBtn = new JButton("가까운 병원");
		goToMapBtn.setBounds(1280/2 + 50, 8, 150, 30);
		goToMapBtn.setBackground(new Color(0x1E90FF));
		goToMapBtn.setForeground(Color.WHITE);
		goToMapBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		goToMapBtn.addActionListener(new ActionListener() { // ActionListner 추가
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MapPage mapPage = new MapPage(MainScene.this);
				setVisible(false);
				mapPage.setVisible(true);
			}
		});
		add(goToMapBtn);
	}

	public UserDataSet getDataSet() {
		return dataSet;
	}

	public LoginPage getOwner() {
		return owner;
	}
}
