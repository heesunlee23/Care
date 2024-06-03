package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Caregiver;
import model.Person;
import model.Reservation;
import model.ReservationList;
import model.User;
import model.UserDataSet;

public class ReservationConfirmationPage extends JFrame {
    private JTextField desiredDay;
    private ReservationList reservationList;
    private Caregiver caregiver;
    private User user;
    private JComboBox<String> startTime, endTime;
    private TextArea page;
    private long timeCalculator;
    private long nextBalance;
    private int currentBalance;
    private String startText  = String.format("%02d:%02d", 0, 0);
    private String endText  = String.format("%02d:%02d", 0, 0);

    public ReservationConfirmationPage(Caregiver caregiver, ReservationList reservationList, User user) {
        this.caregiver = caregiver;
        this.reservationList = reservationList;
        this.user = user;
        
        setTitle("예약페이지");
        getContentPane().setBackground(new Color(0xF0F8FF));
        setBounds(100, 100, 800, 600);
        setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setVisible(true);

        // Initialize UI components
        initializeComponents();

        // Update the TextArea with caregiver info
        updateTextArea();
    }

    private void initializeComponents() {
    	JLabel desiredTextDay = new JLabel("예약날짜");
        desiredTextDay.setBounds(400, 0, 200, 20);
        desiredTextDay.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
        add(desiredTextDay);

        startTime = createComboBox();
        startTime.setBounds(400, 70, 270, 30);
        startTime.setFont(new Font("나눔스퀘어", Font.BOLD, 17));
        startTime.setBackground(Color.WHITE);
        startTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculationAndTextArea();
            }
        });
        add(startTime);

        endTime = createComboBox();
        endTime.setBounds(400, 110, 270, 30);
        endTime.setFont(new Font("나눔스퀘어", Font.BOLD, 17));
        endTime.setBackground(Color.WHITE);
        endTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculationAndTextArea();
            }
        });
        add(endTime);

        desiredDay = new JTextField("ex)240627");
        desiredDay.setBounds(400, 30, 270, 30);
        desiredDay.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
        add(desiredDay);

        JPanel bottomSet = new JPanel();
        bottomSet.setBounds(0, 480, 800, 600);
        bottomSet.setLayout(null);
        bottomSet.setBackground(new Color(0xF0F8FF));

        JButton btnBack = new JButton("뒤로가기");
        btnBack.setBackground(new Color(0x1E90FF));
        btnBack.setForeground(Color.WHITE);
        btnBack.setSize(200, 60);
        btnBack.setLocation(5, 0);
        btnBack.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        bottomSet.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButton btnRes = new JButton("예약하기");
        btnRes.setBackground(new Color(0x1E90FF));
        btnRes.setForeground(Color.WHITE);
        btnRes.setSize(200, 60);
        btnRes.setLocation(210, 0);
        btnRes.setFont(new Font("나눔스퀘어", Font.BOLD, 22));
        bottomSet.add(btnRes);

        btnRes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleReservation();
            }
        });

        add(bottomSet);

        desiredDay.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (desiredDay.getText().equals("")) {
                    desiredDay.setText("ex)240627");
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (desiredDay.getText().equals("ex)240627")) {
                    desiredDay.setText("");
                }
            }
        });

        JLabel rInfo = new JLabel("도우미 정보");
        rInfo.setBounds(5, 5, 100, 20);
        rInfo.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
        add(rInfo);

        page = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
        page.setBounds(0, 0, 400, 300);
        page.setLocation(0, 30);
        page.setBackground(new Color(250, 250, 250));
        page.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
        page.setEditable(false);
        add(page);
    }

    private JComboBox<String> createComboBox() {
        String[] times = new String[24 * 2];
        int index = 0;
        for (int hour = 0; hour < 24; hour++) {
            for (int minute = 0; minute < 2; minute++) {
                times[index++] = String.format("%02d:%02d", hour, minute * 30);
            }
        }
        return new JComboBox<>(times);
    }

    private void calculateDifference() {
        startText = (String) startTime.getSelectedItem();
        
        if(startText == null) {
        	startText = String.format("%02d:%02d", 0, 0);
        }
        endText = (String) endTime.getSelectedItem();
        
        if(endText == null) {
        	endText = String.format("%02d:%02d", 0, 0);
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime sTime = LocalTime.parse(startText, formatter);
        LocalTime eTime = LocalTime.parse(endText, formatter);

        Duration duration = Duration.between(sTime, eTime);
        timeCalculator = Math.abs(duration.toMinutes() * caregiver.getRatesPerServiceType() / 60);
    }

    private void updateCalculationAndTextArea() {
        if (startTime.getSelectedItem() != null && endTime.getSelectedItem() != null) {
            calculateDifference();
            updateTextArea();
        }
    }

    private void handleReservation() {
    	if (startText == null || endText == null || startText.isEmpty() || endText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the desired time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(desiredDay.getText().equals("ex)240627")) {
        	JOptionPane.showMessageDialog(this, "날짜 입력이 초기값입니다.","Error",JOptionPane.ERROR_MESSAGE);
        	return;
        }
        else if(!(desiredDay.getText().matches("\\d+"))) {
        	JOptionPane.showMessageDialog(this, "숫자만 입력해주세요.","Error",JOptionPane.ERROR_MESSAGE);
        	return;
        }
        else if(desiredDay.getText().length() != 6) {
        	JOptionPane.showMessageDialog(this, "입력 형식이 다릅니다.","Error",JOptionPane.ERROR_MESSAGE);
        	return;
        }

        calculateDifference();
        String desiredDayText = desiredDay.getText();
        if (desiredDayText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the desired day.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        currentBalance = user.getSaving();
        nextBalance = currentBalance - timeCalculator;

        if (nextBalance < 0) {
            JOptionPane.showMessageDialog(this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user.setSaving((int) nextBalance);

        Reservation reservation = new Reservation(caregiver, desiredDayText, startText, endText, timeCalculator, user);
        reservationList.addReservation(reservation);

        JOptionPane.showMessageDialog(this, "예약이 완료되었습니다");
        dispose();
    }

    private void updateTextArea() {
        currentBalance = user.getSaving();
        nextBalance = currentBalance - timeCalculator;

        page.setText("\n◎ 이     름 : " + caregiver.getName() +
                "\n\n◎ 성     별 : " + caregiver.getGender() +
                "\n\n◎ 아  이  디 : " + caregiver.getId() +
                "\n\n◎ 제공 서비스 : " + caregiver.getProvidingServiceTypes() +
                "\n\n◎ 이용 금액(시간당) : " + caregiver.getRatesPerServiceType() + "원" +
                "\n\n◎ 이용 시간 : " + startText + " - " + endText +
                "\n\n◎ 이용 전 잔액 : " + currentBalance + "원" +
                "\n\n◎ 이용 후 잔액 : " + nextBalance + "원");
    }
}
