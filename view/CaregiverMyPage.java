package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Caregiver;
import model.CaregiverDataset;
import model.ReservationList;

public class CaregiverMyPage extends JFrame {
    
    public CaregiverMyPage(LoginPage owner, Caregiver caregiver, ReservationList reservationList, CaregiverDataset dataSet) {
        super("돌보미 마이페이지");
        
        getContentPane().setBackground(new Color(0xFFFFE0));
        super.setBackground(new Color(0xFFFFE0));
        
        setSize(1280, 720);
        setFont(new Font("나눔스퀘어", Font.PLAIN, 13));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("서비스 정보");
        title.setFont(new Font("나눔스퀘어", Font.BOLD, 24));
        title.setBounds(110, 50, 200, 30);
        add(title);
        
        JLabel t2 = new JLabel("회원 정보");
        t2.setFont(new Font("나눔스퀘어", Font.BOLD, 24));
        t2.setBounds(110,270,200,30);
        add(t2);
        
        ImageIcon image = new ImageIcon("src\\MyPageLogo.png");
        JLabel lbImage = new JLabel(image);
        lbImage.setBounds(710, 220, 450, 410);
        add(lbImage);

        JButton changePasswordButton = new JButton("회원정보 변경");
        changePasswordButton.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        changePasswordButton.setBounds(110, 330, 240, 75);
        changePasswordButton.setBackground(Color.white);
        add(changePasswordButton);

        JButton viewReservationsButton = new JButton("예약내역 조회");
        viewReservationsButton.setBounds(110, 115, 240, 75);
        viewReservationsButton.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        viewReservationsButton.setBackground(Color.white);
        add(viewReservationsButton);

        JButton deleteAccountButton = new JButton("회원탈퇴");
        deleteAccountButton.setBounds(400, 330, 240, 75);
        deleteAccountButton.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        deleteAccountButton.setBackground(Color.white);
        add(deleteAccountButton);
        
        JButton chatButton = new JButton("상담");
        chatButton.setBounds(400, 115, 240, 75);
        chatButton.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        chatButton.setBackground(Color.white);
        add(chatButton);
       
        JButton trainingButton = new JButton("교육이수");
        trainingButton.setBounds(690, 115, 240, 75);
        trainingButton.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        trainingButton.setBackground(Color.white);
        add(trainingButton);

        // 뒤로가기 버튼
        JButton backButton = new JButton("로그아웃");
        backButton.setBounds(445, 475, 150, 55);
        backButton.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        backButton.setBackground(new Color(0x1E90FF));
        backButton.setForeground(Color.WHITE);
        add(backButton);

        // 회원정보(비밀번호 변경)
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordPage(CaregiverMyPage.this, caregiver).setVisible(true);
                setVisible(false);
            }
        });
        
        // 예약 내역 조회(해당 돌보미를 예약한 고객 내역 조회)
        viewReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewReservationsPage(CaregiverMyPage.this, caregiver, reservationList).setVisible(true);
                setVisible(false);
            }
        });

        //회원탈퇴
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteAccountPage(CaregiverMyPage.this, caregiver, dataSet).setVisible(true);
                setVisible(false);
            }
        });
        
        // 채팅 버튼 클릭 시 채팅 서버와 클라이언트를 독립된 창으로 실행
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new ChatServer();
                new ChatClient();
            
            }
        });
        
        // 교육이수 버튼 
        trainingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new TrainingProgramListPage(CaregiverMyPage.this, caregiver);
                setVisible(false);
            }
        });
        
        // 뒤로 가기 버튼 - 로그인화면으로 돌아감
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setVisible(true);
                setVisible(false);
            }
        });
        
        setVisible(true);
    }
}