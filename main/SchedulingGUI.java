import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.io.File;
import java.util.Random;

public class SchedulingGUI extends JFrame {

    private javax.swing.JButton browseButton;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JLabel processLabel;
    private javax.swing.JLabel algoLabel;
    private javax.swing.JLabel mainTitle;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel sourceLabel;
    private javax.swing.JLabel maxProcessesLabel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField numSelectedField;
    private javax.swing.JButton beginButtonLabel;


    private String[] algorithms = {"FCFS", "Round Robin", "Shortest Job First", "Priority Scheduling"};
    private String sourcePath = new String();
    public static int NUM_OF_PROCESSES = 0;
    static String selectedAlgo = new String();
    

    public SchedulingGUI() {
        initComponents();
        iconLabel.setIcon(new javax.swing.ImageIcon("src\\background\\dark_pattern.jpg")); 
        setResizable(false);
        setTitle("CPU Scheduling Simulator");
        comboBox.setModel(new DefaultComboBoxModel<>(algorithms));
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        processLabel = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        algoLabel = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        mainTitle = new javax.swing.JLabel();
        subTitle = new javax.swing.JLabel();
        sourceLabel = new javax.swing.JLabel();
        numSelectedField = new javax.swing.JTextField();
        beginButtonLabel = new javax.swing.JButton();
        maxProcessesLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));
        mainPanel.setLayout(new AbsoluteLayout());
        mainPanel.setPreferredSize(new java.awt.Dimension(450, 255));

        processLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        processLabel.setForeground(new java.awt.Color(255, 255, 255));
        processLabel.setText("# of Process:");
        mainPanel.add(processLabel, new AbsoluteConstraints(320, 290, 130, 30));

        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        mainPanel.add(comboBox, new AbsoluteConstraints(490, 190, 140, 30));

        algoLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        algoLabel.setForeground(new java.awt.Color(255, 255, 255));
        algoLabel.setText("Algorithm:");
        mainPanel.add(algoLabel, new AbsoluteConstraints(320, 190, 100, 30));

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        mainPanel.add(browseButton, new AbsoluteConstraints(490, 240, 140, 30));

        mainTitle.setFont(new java.awt.Font("Bookman Old Style", 1, 48)); 
        mainTitle.setForeground(new java.awt.Color(255, 255, 255));
        mainTitle.setText("CPU Scheduling");
        mainPanel.add(mainTitle, new AbsoluteConstraints(280, 10, 420, 80));

        subTitle.setFont(new java.awt.Font("Tahoma", 1, 18));
        subTitle.setForeground(new java.awt.Color(255, 255, 255));
        subTitle.setText("Simulator");
        mainPanel.add(subTitle, new AbsoluteConstraints(420, 70, 130, 40));

        sourceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        sourceLabel.setForeground(new java.awt.Color(255, 255, 255));
        sourceLabel.setText("Source:");
        mainPanel.add(sourceLabel, new AbsoluteConstraints(320, 240, 70, 30));

        numSelectedField.setText("0");
        mainPanel.add(numSelectedField, new AbsoluteConstraints(490, 290, 140, 30));

        beginButtonLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        beginButtonLabel.setText("Begin");
        beginButtonLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginActionPerformed(evt);
            }
        });
        mainPanel.add(beginButtonLabel, new AbsoluteConstraints(410, 370, 130, 40));

        maxProcessesLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        maxProcessesLabel.setForeground(new java.awt.Color(255, 255, 255));
        maxProcessesLabel.setText("(Max. 10)");
        mainPanel.add(maxProcessesLabel, new AbsoluteConstraints(650, 290, 120, 30));

        iconLabel.setIcon(new javax.swing.ImageIcon("D:\\java\\proj\\visualiZ-cpu\\main\\src\\background\\dark_half.jpg")); 
        mainPanel.add(iconLabel, new AbsoluteConstraints(0, 0, 960, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 957, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainPanel.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }


    private void browseActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        sourcePath = file.getAbsolutePath();
    }


    private void beginActionPerformed(java.awt.event.ActionEvent evt) {
        int numOfProcesses = Integer.parseInt(numSelectedField.getText());

        if(sourcePath.isEmpty())
            JOptionPane.showMessageDialog(null, "ERROR: Please input the source of computation");
        
        else if(numOfProcesses <= 0)
            JOptionPane.showMessageDialog(null, "ERROR: Please input the appropriate number of processes");
        
        else {
            selectedAlgo = comboBox.getSelectedItem().toString();
            NUM_OF_PROCESSES = Integer.parseInt(numSelectedField.getText());
            
            Job[] jobs = new Job[NUM_OF_PROCESSES];
            for(int i = 1; i <= NUM_OF_PROCESSES; i++) {
                String processID = "P"+i;
                long arrivalTime = (new java.util.Random().nextInt(9) + 1) * 300;
                long burstTime = (new java.util.Random().nextInt(3) + 1) * 100;
                long STRTTIME = System.nanoTime();
                
                Job newJob = new Job(processID, arrivalTime, burstTime, STRTTIME);
                jobs[(i-1)] = newJob;
            }
            
            CalcSimulation sim = new CalcSimulation(jobs, NUM_OF_PROCESSES, sourcePath, selectedAlgo);
            
            sim.start();
            this.dispose();
        }
    }


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SchedulingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchedulingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchedulingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchedulingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchedulingGUI().setVisible(true);
            }
        });
    }
}
