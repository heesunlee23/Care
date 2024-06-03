package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ListCreator;
import model.Caregiver;
import model.Reservation;
import model.ReservationList;
import model.User;

public class ReviewPage extends JFrame {
	
	private JButton btnBack; //뒤로가기 버튼
	
	private JButton btnAddReview;
	private JTextField inputReviewField;
	
	private ButtonGroup scoreGroup;
	private JRadioButton[] scoreBtn;
	
	public ReviewPage(Caregiver member, User user) throws HeadlessException {
		getContentPane().setBackground(new Color(0xF0F8FF));
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		
		//상단 출력 문구
		setBounds(100, 100, 800, 600);
		setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		
		//상단 출력 문구
		JLabel rvPage = new JLabel("사용자 리뷰");
		rvPage.setBounds(5, 5, 100, 20);
		rvPage.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		add(rvPage);
		
		JLabel inputReview = new JLabel("리뷰 작성");
		inputReview.setBounds(420, 55, 100, 20);
		inputReview.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		add(inputReview);
		
		inputReviewField = new JTextField("리뷰를 입력해주세요.");
		inputReviewField.setBounds(420, 80, 250, 30);
		inputReviewField.setFont(new Font("나눔스퀘어", Font.PLAIN, 13));
		add(inputReviewField);
		
		btnAddReview = new JButton("입력");
		btnAddReview.setBounds(680, 80, 65, 30);
		btnAddReview.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		btnAddReview.setBackground(new Color(0x1E90FF));
		btnAddReview.setForeground(Color.WHITE);
		add(btnAddReview);
		
		JPanel scoreField = new JPanel();
		scoreField.setBounds(420, 120, 300, 30);
		scoreField.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		scoreField.setBackground(new Color(0xF0F8FF));
		
		scoreBtn = new JRadioButton[5];
		scoreGroup = new ButtonGroup();
		
		for(int i = 0; i < 5; i++) {
			scoreBtn[i] = new JRadioButton(i+1+"점");
			scoreGroup.add(scoreBtn[i]);
			scoreBtn[i].setFont(new Font("나눔스퀘어", Font.BOLD, 15));
			scoreBtn[i].setBackground(new Color(0xF0F8FF));
			scoreField.add(scoreBtn[i]);
		}
		
		add(scoreField);
		
		TextArea review = new TextArea(member.getReview(), 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		
		review.setBounds(0, 0, 400, 300);
		review.setLocation(0, 50);
		review.setBackground(new Color(250, 250, 250));
		review.setFont(new Font("나눔스퀘어", Font.PLAIN, 13));
		review.setEditable(false);
		add(review, FlowLayout.LEFT);
		
		btnBack = new JButton("뒤로가기");
		btnBack.setBackground(new Color(0x1E90FF));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBounds(70, 480, 800, 100);
		btnBack.setSize(140, 50);
		btnBack.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		add(btnBack, BorderLayout.SOUTH);
		
		//뒤로가기 버튼 이벤트
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		inputReviewField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(inputReviewField.getText().equals("")) {
					inputReviewField.setText("리뷰를 입력해주세요.");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {

				if(inputReviewField.getText().equals("리뷰를 입력해주세요.")) {
					inputReviewField.setText("");
				}
			}
		});
		
		btnAddReview.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	            int score = 0;
	            boolean token = false;

				for (Reservation reser : ReservationList.getInstance().getReservations()) {
					if (reser.getCaregiver().getId().equals(member.getId())) {
						if (reser.getClientId().equals(user.getId())) {
							token = true;
						}
					}
				}
	            
				if(token) {
					for (int i = 0; i < scoreBtn.length; i++) {
						if (scoreBtn[i].isSelected()) {
							score = i + 1;
						}
					}
					if (inputReviewField.getText().equals("리뷰를 입력해주세요.")) {
						JOptionPane.showMessageDialog(rootPane, "리뷰를 입력해 주세요");
					} else if (score == 0) {
						JOptionPane.showMessageDialog(rootPane, "평점을 선택해 주세요");
					} else {
						String scoreStar = "☆☆☆☆☆";
	
						for (int i = 0; i < score; i++) {
							StringBuilder sb = new StringBuilder(scoreStar);
							sb.replace(i, i + 1, "★");
	
							scoreStar = sb.toString();
						}
						String reviewMessege = " ◎ " + user.getnName() + "\t\t\t\t평점 |" + scoreStar + "\n" + "\n" + "\n"
								+ inputReviewField.getText() + "\n"
								+ "\n";
						member.setReview(reviewMessege);
						member.setReputationTotal(score);
						member.calcReputation();
						review.setText(member.getReview());
						JOptionPane.showMessageDialog(rootPane, "리뷰작성에 성공하였습니다.");
	
						inputReviewField.setText("리뷰를 입력해주세요.");
					}
				}else {
					JOptionPane.showMessageDialog(rootPane, "이용한 도우미만 리뷰를 남길 수 있습니다.");
				}
			}
		});
		
		setTitle("리뷰페이지");
		setResizable(false);
		setVisible(true);
		
	}//생성자
	
}//class
