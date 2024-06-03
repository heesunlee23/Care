package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import model.Caregiver;

public class ChangePasswordPage extends JFrame {
    private Caregiver caregiver;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;

    public ChangePasswordPage(JFrame parent, Caregiver caregiver) {
        super("회원정보 변경");
        this.caregiver = caregiver;

        setSize(400, 300);
        getContentPane().setBackground(new Color(0xFAFAD2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel oldPasswordLabel = new JLabel("현재 비밀번호");
        oldPasswordLabel.setFont(new Font("나눔고딕코딩", Font.BOLD, 15));
        oldPasswordLabel.setBounds(75, 50, 200, 25);
        add(oldPasswordLabel);

        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(225, 50, 100, 25);
        oldPasswordField.setFont(new Font("나눔스퀘어", Font.PLAIN, 15));
        add(oldPasswordField);

        JLabel newPasswordLabel = new JLabel("새 비밀번호");
        newPasswordLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
        newPasswordLabel.setBounds(75, 100, 200, 25);
        add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(225, 100, 100, 25);
        newPasswordField.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
        add(newPasswordField);

        JButton changeButton = new JButton("변경");
        changeButton.setFont(new Font("나눔스퀘어", Font.BOLD, 13));
        changeButton.setBackground(Color.white);
        changeButton.setBounds(150, 150, 100, 25);
        add(changeButton);

        JButton backButton = new JButton("뒤로가기");
        backButton.setFont(new Font("나눔스퀘어", Font.BOLD, 13));
        backButton.setBackground(new Color(0x1E90FF));
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(150, 190, 100, 25);
        add(backButton);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
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

    private void changePassword() {
        String oldPassword = new String(oldPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());

        if(newPassword.equals("")) {
        	JOptionPane.showMessageDialog(this, "변경하려는 비밀번호를 입력해 주세요");
        }
        else if (caregiver.getPw().equals(oldPassword)) {
            caregiver.setPassword(newPassword);
            System.out.println("Caregiver에서 바뀐 비밀번호 : " + caregiver.getPw());
            JOptionPane.showMessageDialog(this, "비밀번호가 성공적으로 변경되었습니다.");
        }else if(newPassword.equals(caregiver.getPw())) {
        	JOptionPane.showMessageDialog(this, "현재 사용중인 비밀번호입니다.");
        }
        else {
            JOptionPane.showMessageDialog(this, "현재 비밀번호가 일치하지 않습니다.");
        }
    }
}