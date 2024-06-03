package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.User;

public class ChargeBalancePage extends Dialog{
	
	private JTextField changeBalanceField;
	private JButton changeBalanceBtn;

	public ChargeBalancePage(MyPage owner,User user) {
		super(owner);
		setTitle("잔액 충전");
		setSize(300,150);
		setResizable(false);
		setLocationRelativeTo(owner);
		setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				owner.setEnabled(true);
				dispose();
			}
		});
		
		changeBalanceField = new JTextField("충전하려는 금액을 입력해주세요.");
		changeBalanceField.setBounds(30, 60, 180, 30);
		
		
		changeBalanceBtn = new JButton("충전");		
		changeBalanceBtn.setBounds(220, 60, 60, 30);
		changeBalanceBtn.setBackground(new Color(0x1E90FF));
		changeBalanceBtn.setForeground(Color.WHITE);
		changeBalanceBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		
		JLabel showCurrentBalance = new JLabel("현재 잔액: " + user.getSaving() + "원");
		showCurrentBalance.setBounds(80, 100, 150, 30);
		showCurrentBalance.setFont(new Font("나눔스퀘어", Font.BOLD, 13));
		showCurrentBalance.setVisible(true);
		
		add(new JTextField());
		add(changeBalanceBtn);
		add(changeBalanceField);
		add(showCurrentBalance);
		
		changeBalanceField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {

				if(changeBalanceField.getText().equals("")) {
					changeBalanceField.setText("충전하려는 금액을 입력해주세요.");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {

				if(changeBalanceField.getText().equals("충전하려는 금액을 입력해주세요.") || changeBalanceField.getText().equals("올바른 입력이 아닙니다.")
						|| changeBalanceField.getText().equals("충전이 완료 되었습니다.")) {
					changeBalanceField.setText("");
				}
			}
		});
		
		changeBalanceBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(changeBalanceField.getText().matches("^[\\d]*$")) {
					User temp = (User) user;
					temp.addSaving(Integer.parseInt(changeBalanceField.getText()));
					showCurrentBalance.setText("현재 잔액 : "+user.getSaving());
					changeBalanceField.setText("충전이 완료 되었습니다.");
				}else{
					changeBalanceField.setText("올바른 입력이 아닙니다.");
				}
			}
		});
		
		setVisible(true);

	}
	
}
