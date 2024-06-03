package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Caregiver;
import model.CaregiverDataset;

public class DeleteAccountPage extends JFrame {
    private Caregiver caregiver;
    private CaregiverDataset dataSet;
    private JFrame parent;
    public DeleteAccountPage(JFrame parent, Caregiver caregiver, CaregiverDataset dataSet) {
        super("회원 탈퇴");

        this.parent = parent;
        this.caregiver = caregiver;
        this.dataSet = dataSet;

        setSize(400, 300);
        getContentPane().setBackground(new Color(0xFAFAD2));
        setFont(new Font("나눔스퀘어", Font.PLAIN, 13));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel deleteLabel = new JLabel("정말로 회원 탈퇴를 하시겠습니까?");
        deleteLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
        deleteLabel.setBounds(72, 50, 300, 25);
        add(deleteLabel);

        JButton confirmButton = new JButton("회원 탈퇴");
        confirmButton.setBounds(150, 100, 100, 25);
        confirmButton.setBackground(Color.white);
        confirmButton.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
        add(confirmButton);

        JButton backButton = new JButton("뒤로가기");
        backButton.setBounds(150, 150, 100, 25);
        backButton.setBackground(new Color(0x1E90FF));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
        add(backButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                setVisible(false);
            }
        });

        setVisible(true);
    }

    private void deleteAccount() {
        int confirmation = JOptionPane.showConfirmDialog(this, "정말로 회원 탈퇴를 하시겠습니까?", "회원 탈퇴", JOptionPane.YES_NO_OPTION);
        
        if (confirmation == JOptionPane.YES_OPTION) {
        	dataSet.withdraw(caregiver);
            
        	JOptionPane.showMessageDialog(this, "회원 탈퇴가 완료되었습니다.");

        	this.dispose();
        	parent.dispose();
        	
        	new LoginPage();
        }
    }
}