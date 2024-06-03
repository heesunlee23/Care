package view;

import java.io.*;
import java.net.*;
import javax.swing.*;

import model.PortNumber;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class ChatServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private JTextArea chatArea;
    private JTextField inputField;

    public ChatServer() {
        JFrame frame = new JFrame("채팅 서버");
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
        			clientSocket.close();
        			serverSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					
				}
        	}
		});

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PortNumber.PORT);
                chatArea.append("서버에 접속되었습니다. 클라이언트를 기다리고 있습니다.\n");

                clientSocket = serverSocket.accept();
                chatArea.append("클라이언트가 접속했습니다.\n");

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    chatArea.append("고객님: " + message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (clientSocket != null) clientSocket.close();
                    if (serverSocket != null) serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (message != null && !message.trim().isEmpty()) {
            chatArea.append("고객센터 담당자: " + message + "\n");
            out.println(message);
            inputField.setText("");
        }
    }
}