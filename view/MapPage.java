package view;

import javax.swing.*;

import model.Hospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MapPage extends JFrame {
    private List<Hospital> hospitals;
    private JPanel mapPanel;
    private JTextField xField;
    private JTextField yField;
    private JLabel resultLabel;
    private Image mapImage;

    public MapPage(MainScene searchPage) {
        hospitals = new ArrayList<>();
        // 하드코딩된 병원 위치 (단순 예시)
        hospitals.add(new Hospital("백병원", 200, 50));
        hospitals.add(new Hospital("서울대병원", 794, 250));
        hospitals.add(new Hospital("더좋은병원", 1100, 465));
        
        // 지도 이미지 로드
        mapImage = Toolkit.getDefaultToolkit().createImage("src\\map1.png");

        setTitle("가장 가까운 병원");
        setSize(1280, 1280/16*9);
        setFont(new Font("나눔스퀘어", Font.BOLD, 14));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        /* 지도 패널
         * 자바의 JPanel을 확장하여 커스텀 그래픽을 그림
         * mapPanel이라는 이름의 새로운 JPanel 인스턴스를 생성
         * 이 JPanel은 paintComponent 메서드를 재정의하여 커스텀 그래픽을 그립니다.
         */
        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                /* 
                 * paintComponent 메서드는 패널이 그려질 때 호출되는 메서드입니다.
                 * super.paintComponent(g);를 호출하여 패널의 기본 그래픽을 먼저 그립니다. 
                 * 이는 이전에 그려진 잔상을 지우고 새로 그리기 위해 필요합니다.
                 */
            	super.paintComponent(g);
                /*
                 * 지도 이미지 그리기
                 * 마지막 인자 this의 타입은 ImageObserver이고, 이미지 로딩 상태를 모니터링합니다.
                 * 
                 * ImageObserver 란?
                 * ImageObserver는 이미지가 비동기적으로 로드될 때 그 진행 상태를 통지받기 위한 인터페이스입니다. 
                 * 이미지 로드가 완료되지 않은 상태에서 이미지를 그리기 시작하면, 
                 * 로딩이 끝날 때까지 ImageObserver가 계속해서 업데이트를 받게 됩니다. 
                 * 이때 ImageObserver는 보통 로딩 상태를 모니터링하고, 
                 * 로딩이 끝났을 때 이미지를 다시 그리도록 트리거하는 역할을 합니다.
                 * -> 한마디로 이미지 로드와 그 위에 그림 그리는 것을 조화롭게 할 수 있도록 도와주는 역할
                 * 
                 * this가 ImageObserver로 사용되는 이유
                 * JPanel은 ImageObserver 인터페이스를 구현하지 않지만, 
                 * Component 클래스는 ImageObserver 인터페이스를 구현합니다. 
                 * JPanel은 Component의 하위 클래스이므로, 
                 * JPanel 객체 (this)는 유효한 ImageObserver로 사용될 수 있습니다.
                 * 
                 * 따라서, drawImage 메서드에서 this를 ImageObserver로 전달하는 것은 
                 * JPanel 자체가 이미지 로딩 상태를 모니터링하고 필요할 때 다시 그리기 요청을 받도록 합니다.
                 * 
                 * -> 요약하면, this가 JPanel 객체를 참조하고, JPanel은 Component를 상속받아 
                 * ImageObserver를 구현하기 때문에 유효한 ImageObserver로 작동합니다.
                 */
                g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);

                // 병원 위치 표시
                g.setColor(Color.RED);
                for(Hospital hospital : hospitals) {
                	// 병원의 좌표에 지름이 30인 원을 그립니다. 
                    g.fillOval(hospital.getX(), hospital.getY(), 30, 30);
                    // 병원의 이름을 원의 오른쪽에 표시합니다. 
                    g.drawString(hospital.getName(), hospital.getX() + 15, hospital.getY());
                }
            }
        };

        add(mapPanel, BorderLayout.CENTER);

        // 사용자 입력 패널
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setFont(new Font("나눔스퀘어", Font.PLAIN, 13));

        inputPanel.add(new JLabel("X좌표를 입력하세요:"));
        inputPanel.setBackground(Color.WHITE);
        xField = new JTextField(20);
        inputPanel.add(xField);

        inputPanel.add(new JLabel("Y좌표를 입력하세요:"));
        yField = new JTextField(20);
        inputPanel.add(yField);
        
        inputPanel.add(yField);
        JLabel j2 = new JLabel("     ");
        inputPanel.add(j2);

        JButton findButton = new JButton("가장 가까운 병원 찾기");
        findButton.setBackground(new Color(0x1E90FF));
        findButton.setForeground(Color.WHITE);
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findNearestHospital();
            }
        });
        inputPanel.add(findButton);
        
        JButton goBackButton = new JButton("뒤로가기");
        goBackButton.setBackground(new Color(0x1E90FF));
        goBackButton.setForeground(Color.WHITE);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                searchPage.setVisible(true);
            }
        });
        JLabel j1 = new JLabel("     ");
        inputPanel.add(j1);
        inputPanel.add(goBackButton);

        resultLabel = new JLabel("");
        inputPanel.add(resultLabel);

        add(inputPanel, BorderLayout.NORTH);
    }

    private void findNearestHospital() {
        try {
            int userX = Integer.parseInt(xField.getText());
            int userY = Integer.parseInt(yField.getText());

            Hospital nearestHospital = null;
            double minDistance = Double.MAX_VALUE;

            for (Hospital hospital : hospitals) {
                double distance = Math.sqrt(Math.pow(userX - hospital.getX(), 2) + Math.pow(userY - hospital.getY(), 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestHospital = hospital;
                }
            }

            if (nearestHospital != null) {
                resultLabel.setText("  가장 가까운 병원: " + nearestHospital.getName());
                mapPanel.scrollRectToVisible(new Rectangle(nearestHospital.getX() - 100, nearestHospital.getY() - 100, 200, 200));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "유효한 좌표를 입력해주세요.");
        }
    }
}