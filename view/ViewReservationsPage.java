package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.Caregiver;
import model.Reservation;
import model.ReservationList;

public class ViewReservationsPage extends JFrame {
    private Caregiver caregiver;
    private ReservationList reservationList;
    private JTextArea reservationListArea;

    public ViewReservationsPage(JFrame parent, Caregiver caregiver, ReservationList reservationList) {
        super("예약내역 조회");

        this.caregiver = caregiver;
        this.reservationList = reservationList;

        setSize(1280, 1280/16*9);
        getContentPane().setBackground(new Color(0xFAFAD2));
        setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel reservationListLabel = new JLabel("예약 내역");
        reservationListLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        reservationListLabel.setBounds(579, 50, 200, 25);
        add(reservationListLabel);
        Border line = BorderFactory.createLineBorder(Color.black,1);
        
        reservationListArea = new JTextArea();
        reservationListArea.setBounds(50, 100, 1150, 300);
        reservationListArea.setEditable(false);
        reservationListArea.setBorder(line);
        reservationListArea.setEditable(false);
        reservationListArea.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
        add(reservationListArea);

        displayReservationList();

        JButton backButton = new JButton("뒤로가기");
        backButton.setBounds(550, 450, 150, 50);
        backButton.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
        backButton.setBackground(new Color(0x1E90FF));
        backButton.setForeground(Color.white);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                setVisible(false);
            }
        });

        setVisible(true);
    }

    private void displayReservationList() {
        List<Reservation> reservations = reservationList.getReservations();
        StringBuilder reservationListText = new StringBuilder();
        
        for (Reservation reservation : reservations) {
        	if(reservation.getCaregiver().getId().equals(caregiver.getId())) {
                reservationListText.append("고객 ID: ").append(reservation.getClientId()).append("\n");
                reservationListText.append("예약 날짜: ").append(reservation.getDesiredDay()).append("\n");
                reservationListText.append("시작 시간: ").append(reservation.getResevStartTime()).append("\n");
                reservationListText.append("종료 시간: ").append(reservation.getResevEndTime()).append("\n\n");
            }
        }
        reservationListArea.setText(reservationListText.toString());
    }
}