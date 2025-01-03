import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.File;

public class SchedulingGUI extends JFrame {

    private JButton browseButton;
    private JComboBox<String> comboBox;
    private JLabel processLabel;
    private JLabel algoLabel;
    private JLabel mainTitle;
    private JLabel subTitle;
    private JLabel sourceLabel;
    private JLabel maxProcessesLabel;
    private JLabel iconLabel;
    private JPanel mainPanel;
    private JTextField numSelectedField;
    private JButton beginButton;
    private JButton generateButton;

    private JPanel inputPanel; // Panel to hold dynamic input fields
    private JScrollPane scrollPane;

    private String[] algorithms = {"FCFS", "Round Robin", "Shortest Job First", "Priority Scheduling"};
    private String sourcePath = "";
    public static int NUM_OF_PROCESSES = 0;
    static String selectedAlgo = "";

    // Arrays to hold references to dynamically created text fields
    private JTextField[] arrivalFields;
    private JTextField[] burstFields;

    public SchedulingGUI() {
        initComponents();
        iconLabel.setIcon(new ImageIcon("src\\background\\dark_pattern.jpg")); 
        setResizable(false);
        setTitle("CPU Scheduling Simulator");
        comboBox.setModel(new DefaultComboBoxModel<>(algorithms));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new JPanel();
        processLabel = new JLabel();
        comboBox = new JComboBox<>();
        algoLabel = new JLabel();
        browseButton = new JButton();
        mainTitle = new JLabel();
        subTitle = new JLabel();
        sourceLabel = new JLabel();
        numSelectedField = new JTextField();
        beginButton = new JButton();
        generateButton = new JButton();
        maxProcessesLabel = new JLabel();
        iconLabel = new JLabel();
        inputPanel = new JPanel();
        scrollPane = new JScrollPane();
    
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
        mainPanel.setBackground(new Color(0, 0, 0));
        mainPanel.setLayout(null); // Using null layout for absolute positioning
        mainPanel.setPreferredSize(new Dimension(960, 600));
    
        mainTitle.setFont(new Font("Bookman Old Style", 1, 48)); 
        mainTitle.setForeground(new Color(255, 255, 255));
        mainTitle.setText("CPU Scheduling");
        mainTitle.setBounds(280, 10, 420, 80);
        mainPanel.add(mainTitle);
    
        subTitle.setFont(new Font("Tahoma", 1, 18));
        subTitle.setForeground(new Color(255, 255, 255));
        subTitle.setText("Simulator");
        subTitle.setBounds(420, 70, 130, 40);
        mainPanel.add(subTitle);
    
        algoLabel.setFont(new Font("Tahoma", 1, 18)); 
        algoLabel.setForeground(new Color(255, 255, 255));
        algoLabel.setText("Algorithm:");
        algoLabel.setBounds(320, 190, 120, 30);
        mainPanel.add(algoLabel);
    
        comboBox.setModel(new DefaultComboBoxModel<>(algorithms));
        comboBox.setBounds(450, 190, 140, 30);
        mainPanel.add(comboBox);
    
        sourceLabel.setFont(new Font("Tahoma", 1, 18)); 
        sourceLabel.setForeground(new Color(255, 255, 255));
        sourceLabel.setText("Source:");
        sourceLabel.setBounds(320, 240, 100, 30);
        mainPanel.add(sourceLabel);
    
        browseButton.setText("Browse");
        browseButton.setBounds(450, 240, 140, 30);
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        mainPanel.add(browseButton);
    
        processLabel.setFont(new Font("Tahoma", 1, 18)); 
        processLabel.setForeground(new Color(255, 255, 255));
        processLabel.setText("# of Processes:");
        processLabel.setBounds(320, 290, 160, 30);
        mainPanel.add(processLabel);
    
        numSelectedField.setText("0");
        numSelectedField.setBounds(490, 290, 100, 30);
        mainPanel.add(numSelectedField);
    
        maxProcessesLabel.setFont(new Font("Tahoma", 0, 12)); 
        maxProcessesLabel.setForeground(new Color(255, 255, 255));
        maxProcessesLabel.setText("(Max. 10)");
        maxProcessesLabel.setBounds(600, 290, 80, 30);
        mainPanel.add(maxProcessesLabel);
    
        generateButton.setText("Generate Input Fields");
        generateButton.setBounds(395, 330, 170, 30);
        generateButton.setFont(new Font("Tahoma", 0, 14));
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generateInputFields(evt);
            }
        });
        mainPanel.add(generateButton);
    
        beginButton.setFont(new Font("Tahoma", 1, 14)); 
        beginButton.setText("Begin");
        beginButton.setBounds(410, 520, 130, 40); // Lowered position to avoid overlap
        beginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                beginActionPerformed(evt);
            }
        });
        mainPanel.add(beginButton);
    
        // Initialize inputPanel for dynamic fields
        Font borderFont = new Font("Tahoma", Font.BOLD, 14);
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(0, 0, 0));
        // inputPanel.setBorder(new TitledBorder(null, "Process Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
        inputPanel.setBorder(new TitledBorder(
        BorderFactory.createLineBorder(Color.WHITE),
        "Process Details", 
        TitledBorder.LEADING, 
        TitledBorder.TOP, 
        borderFont, 
        Color.WHITE 
        ));

        scrollPane.setBounds(50, 370, 800, 140);
        scrollPane.setViewportView(inputPanel);
        scrollPane.setVisible(false); // Initially hidden
        mainPanel.add(scrollPane);
    
        iconLabel.setIcon(new ImageIcon("D:\\java\\proj\\visualiZ-cpu\\main\\src\\background\\dark_half.jpg")); 
        iconLabel.setBounds(0, 0, 960, 600);
        mainPanel.add(iconLabel);
    
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    
        pack();
        setLocationRelativeTo(null);
    }
    

    private void browseActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            sourcePath = file.getAbsolutePath();
        }
    }

    private void generateInputFields(ActionEvent evt) {
        String numStr = numSelectedField.getText();
        try {
            int num = Integer.parseInt(numStr);
            if(num <= 0 || num > 10){
                JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 10 for processes.");
                return;
            }
            NUM_OF_PROCESSES = num;
            createProcessInputFields(num);
            scrollPane.setVisible(true);
            mainPanel.revalidate();
            mainPanel.repaint();
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Please enter a valid number for processes.");
        }
    }

    private void createProcessInputFields(int num) {
        // Initialize arrays
        arrivalFields = new JTextField[num];
        burstFields = new JTextField[num];
    
        inputPanel.removeAll();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
    
        // Headers
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel processLabel = new JLabel("Process ID");
        processLabel.setForeground(Color.WHITE);
        processLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        inputPanel.add(processLabel, gbc);
    
        gbc.gridx = 1;
        JLabel arrivalLabel = new JLabel("Arrival Time");
        arrivalLabel.setForeground(Color.WHITE);
        arrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        inputPanel.add(arrivalLabel, gbc);
    
        gbc.gridx = 2;
        JLabel burstLabel = new JLabel("Burst Time");
        burstLabel.setForeground(Color.WHITE);
        burstLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        inputPanel.add(burstLabel, gbc);
    
        // Dynamic fields for each process
        for (int i = 0; i < num; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            JLabel processIDLabel = new JLabel("P" + (i + 1));
            processIDLabel.setForeground(Color.WHITE);
            processIDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            inputPanel.add(processIDLabel, gbc);
    
            gbc.gridx = 1;
            arrivalFields[i] = new JTextField(10);
            inputPanel.add(arrivalFields[i], gbc);
    
            gbc.gridx = 2;
            burstFields[i] = new JTextField(10);
            inputPanel.add(burstFields[i], gbc);
        }
    
        inputPanel.revalidate();
        inputPanel.repaint();
    }
    

    private void beginActionPerformed(ActionEvent evt) {
        if(sourcePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ERROR: Please input the source of computation");
            return;
        }

        if(NUM_OF_PROCESSES <= 0) {
            JOptionPane.showMessageDialog(this, "ERROR: Please input the appropriate number of processes");
            return;
        }

        // Validate and collect arrival and burst times
        Job[] jobs = new Job[NUM_OF_PROCESSES];
        for(int i = 0; i < NUM_OF_PROCESSES; i++) {
            String processID = "P" + (i + 1);
            String arrivalStr = arrivalFields[i].getText();
            String burstStr = burstFields[i].getText();

            try {
                long arrivalTime = Long.parseLong(arrivalStr);
                long burstTime = Long.parseLong(burstStr);

                if(arrivalTime < 0 || burstTime <= 0){
                    JOptionPane.showMessageDialog(this, "Arrival time must be >= 0 and Burst time must be > 0 for " + processID);
                    return;
                }

                long STRTTIME = System.nanoTime();
                jobs[i] = new Job(processID, arrivalTime, burstTime, STRTTIME);

            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Please enter valid numerical values for arrival and burst times for " + processID);
                return;
            }
        }

        selectedAlgo = comboBox.getSelectedItem().toString();

        CalcSimulation sim = new CalcSimulation(jobs, NUM_OF_PROCESSES, sourcePath, selectedAlgo);

        sim.start();
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SchedulingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchedulingGUI().setVisible(true);
            }
        });
    }
}
