package view;

import java.io.*;
import java.net.*;
import javax.swing.*;

import model.PortNumber;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class ChatClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JTextArea chatArea;
    private JTextField inputField;

    //서버에서 할당된 포트를 자동으로 연결
    public ChatClient() {
        JFrame frame = new JFrame("채팅 클라이언트");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(10, 10, 360, 300);
        frame.add(scrollPane);

        inputField = new JTextField();
        inputField.setBounds(10, 320, 260, 30);
        frame.add(inputField);

        JButton sendButton = new JButton("보내기");
        sendButton.setBackground(new Color(0x1E90FF));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
        sendButton.setBounds(280, 320, 80, 30);
        frame.add(sendButton);

        frame.setVisible(true);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        
        frame.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		frame.dispose();
        		try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					
				}
        	}
		});

        new Thread(() -> {
            try {
                socket = new Socket("localhost", PortNumber.PORT);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String message;
                
                while ((message = in.readLine()) != null) {
                    chatArea.append("고객센터 담당자: " + message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (socket != null) socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (message != null && !message.trim().isEmpty()) {
            chatArea.append("고객님: " + message + "\n");
            out.println(message);
            inputField.setText("");
        }
    }
}