package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Caregiver;
import model.TrainingProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class TrainingProgramListPage extends JFrame {
    private JList<TrainingProgram> trainingProgramJList;
    private DefaultListModel<TrainingProgram> trainingProgramListModel;
    private JTextArea trainingDescriptionArea;
    private JTextArea additionalTextArea;
    private JButton completeButton;
    private JButton videoButton;
    private JButton goBackButton;
    
    private Caregiver caregiver;

    public TrainingProgramListPage(JFrame parent, Caregiver caregiver) {
    	this.caregiver = caregiver;
    	
        setTitle("교육이수");
        setSize(1280, 1280/16*9);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        // DefaultListModel은 리스트 데이터를 저장하는 데 사용됩니다. 리스트 항목을 추가, 제거 및 수정할 수 있습니다.
        trainingProgramListModel = new DefaultListModel<>();
        // JList는 리스트 항목을 시각적으로 표시하는 컴포넌트입니다. 
        // 여기서는 DefaultListModel 객체를 사용하여 데이터를 제공합니다.
        trainingProgramJList = new JList<>(trainingProgramListModel);
        // 단일 선택 모드 설정 
        trainingProgramJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        /*
         * 리스트 항목 선택이 변경될 때마다 showTrainingDetails 메서드가 호출되도록 설정
         * 람다 표현식을 사용하여 선택 이벤트가 발생할 때 showTrainingDetails 메서드가 실행되도록 합니다.
         */
        trainingProgramJList.addListSelectionListener(e -> showTrainingDetails());
        trainingProgramJList.setFont(new Font("나눔스퀘어", Font.BOLD, 17));

        trainingDescriptionArea = new JTextArea();
        trainingDescriptionArea.setEditable(false);

        additionalTextArea = new JTextArea();
        additionalTextArea.setEditable(false);

        // 이수완료 버튼 
        completeButton = new JButton("이수완료");
        completeButton.setBackground(Color.white);
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeTraining();
            }
        });

        // 비디오 보기 버튼 
        videoButton = new JButton("비디오 보기");
        videoButton.setBackground(Color.white);
        videoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                watchVideo();
            }
        });
        
        // 뒤로 가기 버튼. 누르면 돌보미 마이페이지로 이동
        goBackButton = new JButton("뒤로가기");
        goBackButton.setBackground(new Color(0x1E90FF));
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parent.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(0xFFFFE0));
        buttonPanel.add(completeButton);
        buttonPanel.add(videoButton);
        buttonPanel.add(goBackButton);

        add(new JScrollPane(trainingProgramJList), BorderLayout.WEST);
        add(createDetailsPanel(), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadTrainingPrograms();
        
        setVisible(true);
    }

    /* 
     * 오른쪽에 교육프로그램(내용) 부분
     */
    private JPanel createDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(2, 1));
        detailsPanel.add(new JScrollPane(trainingDescriptionArea));
        detailsPanel.add(new JScrollPane(additionalTextArea));
        return detailsPanel;
    }

    private void loadTrainingPrograms() {
        // 교육 프로그램을 위한 더미 데이터 
        trainingProgramListModel.addElement(new TrainingProgram(
                "응급 처치법", 
                "기초 응급 처치는 누구나 쉽게 배워서 실행할 수 있는 간단한 절차를 중심으로 합니다. \n"
                + "우선, 출혈이 있을 때는 깨끗한 천이나 붕대로 상처를 덮고 손으로 압박하여 출혈을 멈춥니다. \n"
                + "심각한 출혈이 지속되면 상처를 심장보다 높게 들어 올려 출혈을 줄입니다. \n"
                + "화상을 입은 경우, 화상 부위를 차가운 물로 10-20분 동안 식혀주고, 깨끗한 천이나 붕대로 화상 부위를 덮어줍니다. \n"
                + "뼈가 부러진 경우에는 부목이나 딱딱한 물건으로 부러진 부위를 고정하고, 가능한 한 빨리 의료기관에 연락합니다. \n"
                + "심정지 환자를 발견하면, 반응을 확인하고 즉시 119에 연락한 뒤 가슴 압박을 시작합니다. \n"
                + "가슴 중앙에 손을 대고 분당 100-120회의 속도로 약 5cm 깊이로 눌러줍니다. \n"
                + "기도에 이물질이 걸린 경우, 환자가 기침을 할 수 없으면 하임리히법을 사용해 배꼽 바로 위를 강하게 밀어올려 이물질을 제거합니다. \n"
                + "경미한 상처는 깨끗한 물로 세척한 후 소독약을 발라주고 붕대로 감싸며, 정기적인 건강 체크를 통해 혈압, 혈당, 체온 등을 모니터링합니다. \n"
                + "또한, 어르신이나 어린이와 같은 취약한 그룹의 경우에는 특별히 주의하여 돌봄을 제공해야 합니다. \n"
                + "이러한 기본적인 응급 처치 기술은 위급 상황에서 생명을 구하거나 부상의 정도를 줄이는 데 큰 도움이 됩니다.",
                "https://www.youtube.com/watch?v=opUZX8f0wgA", 
                "심화된 응급 처치는 보다 전문적인 지식과 기술을 포함하며, 의료 전문가나 교육을 받은 사람들이 주로 시행합니다. \n"
                + "심각한 출혈이 있는 경우 지혈대를 사용하여 출혈을 멈추고, 지혈대는 상처 부위 위쪽에 단단히 묶어줍니다. \n"
                + "화학 화상을 입은 경우 해당 부위를 물로 충분히 씻어내고, 전기 화상의 경우 전원이 차단된 후에만 환자에게 접근하며, 즉시 의료기관에 연락합니다. \n"
                + "심정지 환자에게는 AED를 사용하여 심전도를 모니터링하고, 심실세동(VF)이나 무맥성 심실빈맥(VT)이 발견되면 제세동을 실시합니다. \n"
                + "기도 확보를 위해 에어웨이 기구(OP 또는 NPA)를 사용하거나, 백밸브마스크(BVM)를 사용해 더 효과적으로 인공호흡을 제공합니다. \n"
                + "필요시 기계적 환기를 통해 일관된 호흡을 제공하며, 기관내삽관을 통해 고급 기도를 확보합니다. \n"
                + "약물 투여는 심정지 환자에게 정맥 주사로 에피네프린, 아미오다론, 리도카인 등을 투여하여 심장 활동을 재개하도록 돕습니다. \n"
                + "경련이 발생하면 주변의 위험 물체를 제거해 안전을 확보하고, 기도를 확보한 후 호흡을 확인합니다. \n"
                + "뇌졸중 환자의 경우 FAST 검사를 통해 얼굴, 팔, 말, 시간을 확인하고, 즉시 119에 연락하여 신속하게 대응합니다. \n"
                + "응급 상황에서 다양한 의료 장비와 기술을 활용하여 환자의 상태를 안정시키고, 전문적인 의료 지원이 도착할 때까지 적절한 처치를 계속 시행합니다. \n"
                + "이러한 심화된 응급 처치 방법은 위급한 상황에서 환자의 생존 가능성을 크게 높이고, 부상의 심각성을 최소화하는 데 중요한 역할을 합니다."
                
        ));
        trainingProgramListModel.addElement(new TrainingProgram(
                "심폐소생술", 
                "심폐소생술(CPR)의 기초 버전은 비의료인도 쉽게 배울 수 있도록 단순화된 절차를 따릅니다. \n"
                + "먼저, 환자가 반응이 없는지 확인하고, 즉시 주변 사람에게 도움을 요청하며 119에 연락합니다. \n"
                + "환자를 평평한 바닥에 눕히고, 머리를 뒤로 젖혀 기도를 확보한 후, 호흡이 정상적으로 이루어지는지 5-10초 동안 관찰합니다. \n"
                + "호흡이 없거나 비정상적일 경우, 가슴 압박을 시작합니다. 한 손의 손바닥을 환자의 가슴 중앙에 놓고, 다른 손을 그 위에 포갭니다. \n"
                + "팔꿈치를 곧게 펴고 체중을 실어 가슴을 약 5cm 깊이로 분당 100-120회의 속도로 압박합니다. \n"
                + "30회의 가슴 압박 후에는 인공호흡을 2회 실시하는데, 코를 막고 입을 완전히 덮어 1초 동안 공기를 불어넣습니다. \n"
                + "가슴이 올라가는지 확인한 후 다시 30회의 가슴 압박을 반복합니다. 인공호흡을 하지 않는 경우, 지속적으로 가슴 압박만을 수행합니다. \n"
                + "구급대가 도착하거나 환자가 스스로 호흡을 시작할 때까지 이 과정을 반복합니다. \n"
                + "자동제세동기(AED)가 있는 경우, 즉시 사용하여 기기의 지시에 따라 충격을 가하고, 필요 시 CPR을 계속합니다. \n"
                + "이 기초적인 CPR 방법은 심정지 환자의 생명을 구하는 데 있어 중요한 초기 대응입니다.",
                "https://www.youtube.com/watch?v=RNWi4tF9uOA", 
                "심폐소생술(CPR)의 심화 버전은 의료 전문인이나 심폐소생술 교육을 받은 사람들이 시행하는 보다 정교한 절차를 포함합니다. \n"
                + "심정지 환자가 발생하면 즉시 반응을 확인하고, 도움을 요청하며 119에 연락합니다. \n"
                + "AED를 사용하기 위해 준비하며, 기도 확보를 위해 머리를 뒤로 젖히고 턱을 들어올립니다. \n"
                + "환자의 호흡과 심장 박동을 확인한 후, 호흡이 없거나 비정상적인 경우 가슴 압박을 시작합니다. \n"
                + "손의 위치를 가슴 중앙에 정확히 놓고, 팔꿈치를 곧게 펴서 체중을 실어 약 5cm 깊이로 분당 100-120회의 속도로 압박합니다. \n"
                + "30회의 압박 후에는 인공호흡을 2회 실시하며, 기도 확보를 위해 에어웨이 기구(OP 또는 NPA)를 사용할 수 있습니다. \n"
                + "백밸브마스크(BVM)를 사용하여 인공호흡을 보다 효과적으로 제공하며, 필요 시 기계적 환기를 통해 일관된 호흡을 제공합니다. \n"
                + "AED의 지시에 따라 패드를 부착하고, 심전도를 모니터링하며, 심실세동(VF)이나 무맥성 심실빈맥(VT)이 발견되면 제세동을 실시합니다. \n"
                + "의료진은 에피네프린, 아미오다론, 리도카인 등의 약물을 정맥 주사로 투여하여 심장 활동을 재개하도록 돕습니다. \n"
                + "기관내삽관을 통해 고급 기도를 확보하고, 지속적인 모니터링과 피드백을 통해 CPR의 효과를 평가하고 조정합니다. \n"
                + "심전도 모니터링 장치를 사용해 심장 리듬을 지속적으로 확인하고, 필요 시 추가적인 제세동이나 약물 투여를 시행합니다. \n"
                + "이러한 심화된 CPR 방법은 심정지 환자의 생존율을 높이기 위해 보다 전문적이고 체계적인 접근을 제공합니다." 
        ));
        trainingProgramListModel.addElement(new TrainingProgram(
                "어르신 돌봄", 
                "어르신 돌봄의 기초 버전은 일상적인 활동을 지원하고, 기본적인 건강과 안전을 유지하는 데 중점을 둡니다. \n"
                + "우선, 정기적인 건강 체크가 중요합니다. \n"
                + "혈압, 혈당, 체온 등을 주기적으로 측정하여 건강 상태를 모니터링하고, 이상이 발견되면 신속하게 의료진과 상의합니다. \n"
                + "약물 관리는 특히 중요한데, 어르신이 복용해야 할 약을 정해진 시간에 정확히 복용할 수 있도록 돕고, 복용 일정을 기록해 둡니다. \n"
                + "기본 위생 관리도 빼놓을 수 없습니다. 어르신이 정기적으로 샤워나 목욕을 할 수 있도록 돕고, 손과 발의 청결을 유지해 감염을 예방합니다. \n"
                + "균형 잡힌 식단을 제공해 영양을 충분히 공급하는 것도 중요합니다. \n"
                + "신선한 과일과 채소, 단백질이 풍부한 음식을 포함한 식단을 제공하며, 수분 섭취도 충분히 하도록 유도합니다. \n"
                + "어르신의 안전을 위해 집안 환경을 정돈하고, 넘어지지 않도록 주의합니다. \n"
                + "예를 들어, 미끄러운 바닥에는 미끄럼 방지 매트를 깔고, 욕실과 계단에는 손잡이를 설치합니다. \n"
                + "마지막으로, 정서적 지원을 통해 어르신이 고립감을 느끼지 않도록 자주 대화하고, 함께 시간을 보내며 사회적 활동에 참여할 수 있도록 돕습니다. \n", 
                "https://www.youtube.com/watch?v=60AjDw492RU", 
                "어르신 돌봄의 심화 버전은 보다 전문적이고 세심한 접근을 필요로 합니다. \n"
                + "먼저, 복잡한 건강 상태를 관리하기 위해 정기적인 의료 검진과 전문 의료진의 상담을 받도록 합니다. \n"
                + "만성 질환을 가진 어르신의 경우, 치료 계획을 세우고 지속적으로 관리해야 합니다. \n"
                + "재활 치료가 필요한 경우, 물리 치료사나 작업 치료사의 도움을 받아 일상 생활을 보다 독립적으로 할 수 있도록 지원합니다. \n"
                + "영양 관리에 있어서도 영양사의 도움을 받아 개인의 건강 상태에 맞춘 식단을 제공하며, 필요시 영양 보충제를 사용합니다. \n"
                + "심리적 안정과 정서적 지원을 위해 심리 상담이나 지원 그룹에 참여하게 하여 정서적 안정을 도모합니다. \n"
                + "신체 활동을 유지하기 위해 적절한 운동 프로그램을 제공하며, 스트레칭, 걷기, 가벼운 요가 등을 통해 신체 기능을 유지하고 개선할 수 있도록 돕습니다. \n"
                + "또한, 인지 기능을 유지하고 향상시키기 위해 퍼즐, 독서, 게임 등 다양한 인지 활동을 장려합니다. \n"
                + "안전한 생활 환경을 조성하기 위해 집 안의 위험 요소를 점검하고, 필요시 주거 환경을 개조해 어르신의 이동과 활동이 편리하고 안전하게 이루어질 수 있도록 합니다. \n"
                + "가정 내에서의 안전 외에도 사회적 안전망을 구축하는 것이 중요합니다. \n"
                + "지역 사회의 지원 서비스와 연계하여 돌봄 서비스를 받도록 하고, 응급 상황 시 신속히 대응할 수 있는 체계를 마련합니다. \n"
                + "어르신이 외출 시에는 안전하게 이동할 수 있도록 보행기나 휠체어를 제공하고, 동반자가 함께 동행하여 사고를 예방합니다. \n"
                + "또한, 디지털 기기를 활용하여 어르신이 가족 및 친구와 쉽게 연락할 수 있도록 돕고, 필요시 원격 의료 서비스를 통해 건강 상태를 관리합니다. \n"
                + "끝으로, 어르신의 삶의 질을 향상시키기 위해 문화적, 사회적 활동에 적극 참여하게 하여 사회적 연결을 유지하고, 활기찬 생활을 할 수 있도록 지원합니다. \n"
        ));
        trainingDescriptionArea.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
        additionalTextArea.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
    }

    /*
     * 교육콘텐츠 (내용)를 전시하는 메서드
     */
    private void showTrainingDetails() {
        TrainingProgram selectedProgram = trainingProgramJList.getSelectedValue();
        if (selectedProgram != null) {
            trainingDescriptionArea.setText(selectedProgram.getDescription());
            additionalTextArea.setText(selectedProgram.getAdditionalText());
        }
    }

    /*
     * URI에 해당하는 비디오 보여주는 메서드 
     */
    private void watchVideo() {
        TrainingProgram selectedProgram = trainingProgramJList.getSelectedValue();
        if (selectedProgram != null) {
            try {
                Desktop.getDesktop().browse(new URI(selectedProgram.getVideoUrl()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
     * '개별' 교육 이수 여부 체크하는 메서드 
     */
    private void completeTraining() {
        TrainingProgram selectedProgram = trainingProgramJList.getSelectedValue();
        if (selectedProgram != null) {
            selectedProgram.setCompleted(true);
            JOptionPane.showMessageDialog(this, selectedProgram.getTitle() + "교육을 이수했습니다.");
            checkAllTrainingsCompleted();
        }
    }

    /*
     * '모든' 교육 이수 여부 체크하는 메서드 
     */
    private void checkAllTrainingsCompleted() {
    	// 기본값은 true 
    	boolean allCompleted = true;
    	// 반복문 순회하면서 '모든' 교육 이수 여부 체크
        for (int i = 0; i < trainingProgramListModel.getSize(); i++) {
            if (!trainingProgramListModel.getElementAt(i).isCompleted()) {
                allCompleted = false;
                break;
            }
        }

        // 모든 교육 이수 했으면 수료증 출력하는 메서드 호출 
        if (allCompleted) {
            try {
            	// 검색 화면에 교육 이수 여부 전시하기 위한 용도로 설정 
            	caregiver.setTrainingCompleted(allCompleted);
                JOptionPane.showMessageDialog(this, "축하합니다! 모든 교육을 이수하셨습니다. 수료증을 저장합니다.");
                // 수료증 출력하는 메서드 호출
                generateCertificate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void generateCertificate() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("수료증 저장하기");
        
        // 파일 저장 다이얼로그를 표시하여 사용자가 저장할 파일을 선택하게 합니다.
        // showSaveDialog(this)는 저장 다이얼로그를 표시하고, 사용자의 선택을 기다립니다.
        // 사용자가 파일을 선택하면 JFileChooser.APPROVE_OPTION을 반환합니다.
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
        	// 사용자가 파일을 선택한 경우, 사용자가 선택한 파일을 가져옵니다.
        	File fileToSave = fileChooser.getSelectedFile();
            int width = 800;
            int height = 600;
            /*
             * BufferedImage는 자바에서 이미지 데이터를 다루기 위해 제공되는 클래스 중 하나입니다. 
             * 이 클래스는 이미지의 픽셀 데이터를 메모리에 저장하고, 이미지의 내용을 읽고 쓰는 데 사용됩니다. 
             * BufferedImage는 특히 이미지의 개별 픽셀을 직접 조작하거나 그래픽을 그릴 때 유용합니다. 
             */
            BufferedImage certificateImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // Graphics2D 객체를 생성하여 이미지에 그래픽을 그릴 수 있도록 합니다.
            Graphics2D g2d = certificateImage.createGraphics();

            // 배경 색상 설정
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);

            // 텍스트 색상 및 폰트 설정
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            g2d.drawString("Certificate of Completion", 100, 100);
            
            g2d.setFont(new Font("Arial", Font.PLAIN, 24));
            g2d.drawString("This certifies that you have successfully", 100, 200);
            g2d.drawString("completed all training programs.", 100, 250);

            // Graphics2D 객체를 해제하여 그래픽 리소스를 반환
            g2d.dispose();

            ImageIO.write(certificateImage, "png", fileToSave);
        }
    }
}