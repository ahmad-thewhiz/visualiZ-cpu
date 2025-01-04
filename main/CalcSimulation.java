import java.awt.Color;
import java.io.File;
import javax.swing.*;

public class CalcSimulation extends javax.swing.JFrame {
    Job[] jobBatch;
    int numOfJobs;
    public FCFSPolicy fifo;
    String sourcePath;
    static String algo;
    JProgressBar[] pbars = new JProgressBar[10];
    JLabel[] burstTimes = new JLabel[10], waitingTimes = new JLabel[10];
    JLabel[] priorities = new JLabel[10];
    JLabel[] arrivalTimes = new JLabel[10];
    static long STRTTIME;


    private javax.swing.JLabel algoSelected;
    private javax.swing.JLabel avgServeLabel;
    private javax.swing.JLabel avgTurnLabel;

    public static javax.swing.JTextField avgServeField;
    public static javax.swing.JTextField avgWaitField;

    private javax.swing.JLabel rem_bt1;
    private javax.swing.JLabel rem_bt2;
    private javax.swing.JLabel rem_bt3;
    private javax.swing.JLabel rem_bt4;
    private javax.swing.JLabel rem_bt5;
    private javax.swing.JLabel rem_bt6;
    private javax.swing.JLabel rem_bt7;
    private javax.swing.JLabel rem_bt8;
    private javax.swing.JLabel rem_bt9;
    private javax.swing.JLabel rem_bt10;

    private javax.swing.JLabel wt1;
    private javax.swing.JLabel wt2;
    private javax.swing.JLabel wt3;
    private javax.swing.JLabel wt4;
    private javax.swing.JLabel wt5;
    private javax.swing.JLabel wt6;
    private javax.swing.JLabel wt7;
    private javax.swing.JLabel wt8;
    private javax.swing.JLabel wt9;
    private javax.swing.JLabel wt10;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel waitingTimeLabel;
    private javax.swing.JLabel priorityLabel;
    private javax.swing.JLabel remBurstTimeLabel;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel statusBarLabel;
    private javax.swing.JLabel arrivalTime;

    private javax.swing.JPanel mainPanel;

    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JProgressBar jProgressBar7;
    private javax.swing.JProgressBar jProgressBar8;
    private javax.swing.JProgressBar jProgressBar9;
    private javax.swing.JProgressBar jProgressBar10;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    
    private javax.swing.JLabel pt1;
    private javax.swing.JLabel pt2;
    private javax.swing.JLabel pt3;
    private javax.swing.JLabel pt4;
    private javax.swing.JLabel pt5;
    private javax.swing.JLabel pt6;
    private javax.swing.JLabel pt7;
    private javax.swing.JLabel pt8;
    private javax.swing.JLabel pt9;
    private javax.swing.JLabel pt10;

    private javax.swing.JTextField selectedAlgo;
    private javax.swing.JTextField selectedJob;
    
    public static javax.swing.JTextField totalExecField;

    private javax.swing.JLabel arr1;
    private javax.swing.JLabel arr2;
    private javax.swing.JLabel arr3;
    private javax.swing.JLabel arr4;
    private javax.swing.JLabel arr5;
    private javax.swing.JLabel arr6;
    private javax.swing.JLabel arr7;
    private javax.swing.JLabel arr8;
    private javax.swing.JLabel arr9;
    private javax.swing.JLabel arr10;
    

    public CalcSimulation(Job[] jobBatch, int numOfJobs, String sourcePath, String algo) {
        this.jobBatch = jobBatch;
        this.numOfJobs = numOfJobs;
        this.sourcePath = sourcePath;
        this.algo = algo;

        initComponents();
        
        jLabel1.setIcon(new javax.swing.ImageIcon("src\\background\\dark_pattern.jpg"));
        selectedAlgo.setText(algo);
        
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
        pbars[0] = jProgressBar1;
        pbars[1] = jProgressBar2;
        pbars[2] = jProgressBar3;
        pbars[3] = jProgressBar4;
        pbars[4] = jProgressBar5;
        pbars[5] = jProgressBar6;
        pbars[6] = jProgressBar7;
        pbars[7] = jProgressBar8;
        pbars[8] = jProgressBar9;
        pbars[9] = jProgressBar10;
        
        burstTimes[0] = rem_bt1;
        burstTimes[1] = rem_bt2;
        burstTimes[2] = rem_bt3;
        burstTimes[3] = rem_bt4;
        burstTimes[4] = rem_bt5;
        burstTimes[5] = rem_bt6;
        burstTimes[6] = rem_bt7;
        burstTimes[7] = rem_bt8;
        burstTimes[8] = rem_bt9;
        burstTimes[9] = rem_bt10;
        
        waitingTimes[0] = wt1;
        waitingTimes[1] = wt2;
        waitingTimes[2] = wt3;
        waitingTimes[3] = wt4;
        waitingTimes[4] = wt5;
        waitingTimes[5] = wt6;
        waitingTimes[6] = wt7;
        waitingTimes[7] = wt8;
        waitingTimes[8] = wt9;
        waitingTimes[9] = wt10;
        
        priorities[0] = pt1;
        priorities[1] = pt2;
        priorities[2] = pt3;
        priorities[3] = pt4;
        priorities[4] = pt5;
        priorities[5] = pt6;
        priorities[6] = pt7;
        priorities[7] = pt8;
        priorities[8] = pt9;
        priorities[9] = pt10;

        arrivalTimes[0] = arr1;
        arrivalTimes[1] = arr2;
        arrivalTimes[2] = arr3;
        arrivalTimes[3] = arr4;
        arrivalTimes[4] = arr5;
        arrivalTimes[5] = arr6;
        arrivalTimes[6] = arr7;
        arrivalTimes[7] = arr8;
        arrivalTimes[8] = arr9;
        arrivalTimes[9] = arr10;
        
        STRTTIME = System.nanoTime();
    }

    public void start() {
        for(int i=0; i<numOfJobs; i++) {
            arrivalTimes[i].setText(String.valueOf(jobBatch[i].arrivalTime) + "ms");
        }
        if(algo.equals("FCFS")) {
            fifo = new FCFSPolicy(new File(sourcePath));
            CPUScheduler scheduler = new CPUScheduler(jobBatch, fifo, textArea, selectedJob, 
                    pbars, burstTimes, waitingTimes, priorities);
            scheduler.start();
        } else if (algo.equals("Round Robin")) {
            RoundRobinPolicy rr = new RoundRobinPolicy(new File(sourcePath));
            CPUScheduler scheduler = new CPUScheduler(jobBatch, rr, textArea, selectedJob, 
                    pbars, burstTimes, waitingTimes, priorities);
            scheduler.start();
        } else if(algo.equals("Priority Scheduling")) {
            
            priorityLabel.setText("Priority");
            
            PriorityPolicy pp = new PriorityPolicy(new File(sourcePath));
            CPUScheduler scheduler = new CPUScheduler(jobBatch, pp, textArea, selectedJob, 
                    pbars, burstTimes, waitingTimes, priorities);
            scheduler.start();
        } else {
            priorityLabel.setText("Burst");
            Stack stack = new Stack(new File(sourcePath));
            CPUScheduler scheduler = new CPUScheduler(jobBatch, stack, textArea, selectedJob, 
                    pbars, burstTimes, waitingTimes, priorities);
            scheduler.start();
        }
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        algoSelected = new javax.swing.JLabel();
        selectedAlgo = new javax.swing.JTextField();
        pt1 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jProgressBar4 = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jProgressBar5 = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        jProgressBar6 = new javax.swing.JProgressBar();
        jLabel8 = new javax.swing.JLabel();
        jProgressBar7 = new javax.swing.JProgressBar();
        jLabel9 = new javax.swing.JLabel();
        jProgressBar8 = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        jProgressBar9 = new javax.swing.JProgressBar();
        jLabel11 = new javax.swing.JLabel();
        jProgressBar10 = new javax.swing.JProgressBar();
        jLabel12 = new javax.swing.JLabel();
        waitingTimeLabel = new javax.swing.JLabel();
        priorityLabel = new javax.swing.JLabel();
        remBurstTimeLabel = new javax.swing.JLabel();
        rem_bt3 = new javax.swing.JLabel();
        rem_bt1 = new javax.swing.JLabel();
        rem_bt2 = new javax.swing.JLabel();
        rem_bt6 = new javax.swing.JLabel();
        rem_bt5 = new javax.swing.JLabel();
        rem_bt4 = new javax.swing.JLabel();
        rem_bt10 = new javax.swing.JLabel();
        rem_bt8 = new javax.swing.JLabel();
        rem_bt7 = new javax.swing.JLabel();
        rem_bt9 = new javax.swing.JLabel();
        wt1 = new javax.swing.JLabel();
        wt2 = new javax.swing.JLabel();
        wt3 = new javax.swing.JLabel();
        wt4 = new javax.swing.JLabel();
        wt5 = new javax.swing.JLabel();
        wt6 = new javax.swing.JLabel();
        wt7 = new javax.swing.JLabel();
        wt8 = new javax.swing.JLabel();
        wt9 = new javax.swing.JLabel();
        wt10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        avgServeLabel = new javax.swing.JLabel();
        selectedJob = new javax.swing.JTextField();
        avgWaitField = new javax.swing.JTextField();
        avgServeField = new javax.swing.JTextField();
        totalExecField = new javax.swing.JTextField();
        avgTurnLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pt2 = new javax.swing.JLabel();
        pt3 = new javax.swing.JLabel();
        pt4 = new javax.swing.JLabel();
        statusBarLabel = new javax.swing.JLabel();
        pt5 = new javax.swing.JLabel();
        pt6 = new javax.swing.JLabel();
        pt7 = new javax.swing.JLabel();
        pt8 = new javax.swing.JLabel();
        pt9 = new javax.swing.JLabel();
        pt10 = new javax.swing.JLabel();
        arrivalTime = new javax.swing.JLabel();
        arr1 = new javax.swing.JLabel();
        arr2 = new javax.swing.JLabel();
        arr3 = new javax.swing.JLabel();
        arr4 = new javax.swing.JLabel();
        arr5 = new javax.swing.JLabel();
        arr6 = new javax.swing.JLabel();
        arr7 = new javax.swing.JLabel();
        arr8 = new javax.swing.JLabel();
        arr9 = new javax.swing.JLabel();
        arr10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(31, 34, 50));
        mainPanel.setLayout(new AbsoluteLayout());
        mainPanel.setPreferredSize(new java.awt.Dimension(1200, 615)); 

        textArea.setBackground(new java.awt.Color(102, 102, 102));
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Tahoma", 3, 18)); 
        textArea.setForeground(new java.awt.Color(255, 255, 255));
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        mainPanel.add(jScrollPane1, new AbsoluteConstraints(0, 570, 330, 50));

        priorityLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        priorityLabel.setForeground(new java.awt.Color(255, 255, 255));
        priorityLabel.setText("Priority");
        mainPanel.add(priorityLabel, new AbsoluteConstraints(410, 20, 70, 30));

        arrivalTime.setFont(new java.awt.Font("Tahoma", 1, 18));
        arrivalTime.setForeground(new java.awt.Color(255, 255, 255));
        arrivalTime.setText("Arrival Time");
        mainPanel.add(arrivalTime, new AbsoluteConstraints(510, 20, 120, 30));

        statusBarLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        statusBarLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusBarLabel.setText("Status Bar");
        mainPanel.add(statusBarLabel, new AbsoluteConstraints(740, 20, 100, 30));

        remBurstTimeLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        remBurstTimeLabel.setForeground(new java.awt.Color(255, 255, 255));
        remBurstTimeLabel.setText("Rem Burst Time");
        mainPanel.add(remBurstTimeLabel, new AbsoluteConstraints(950, 20, 210, 30));

        waitingTimeLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        waitingTimeLabel.setForeground(new java.awt.Color(255, 255, 255));
        waitingTimeLabel.setText("Waiting Time");
        mainPanel.add(waitingTimeLabel, new AbsoluteConstraints(1110, 20, 180, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel16, new AbsoluteConstraints(420, 70, 50, 30));
        
        pt1.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt1.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt1, new AbsoluteConstraints(440, 70, 100, 20));
        
        arr1.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr1.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr1, new AbsoluteConstraints(540, 70, 80, 20));
        
        jProgressBar1.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar1, new AbsoluteConstraints(650, 70, 270, 30));
        
        rem_bt1.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt1.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt1, new AbsoluteConstraints(1010, 70, 80, 20));
        
        wt1.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt1.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt1, new AbsoluteConstraints(1150, 70, 80, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel4, new AbsoluteConstraints(430, 120, 30, 30));
        
        pt2.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt2.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt2, new AbsoluteConstraints(440, 120, 100, 20));
        
        arr2.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr2.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr2, new AbsoluteConstraints(540, 120, 80, 20));
        
        jProgressBar2.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar2, new AbsoluteConstraints(650, 120, 270, 30));
        
        rem_bt2.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt2.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt2, new AbsoluteConstraints(1010, 120, 80, 20));
        
        wt2.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt2.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt2, new AbsoluteConstraints(1150, 120, 80, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel5, new AbsoluteConstraints(430, 170, 30, 30));
        
        pt3.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt3.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt3, new AbsoluteConstraints(440, 170, 100, 20));
        
        arr3.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr3.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr3, new AbsoluteConstraints(540, 170, 80, 20));
        
        jProgressBar3.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar3, new AbsoluteConstraints(650, 170, 270, 30));
        
        rem_bt3.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt3.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt3, new AbsoluteConstraints(1010, 170, 80, 20));
        
        wt3.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt3.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt3, new AbsoluteConstraints(1150, 170, 80, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel6, new AbsoluteConstraints(430, 220, 30, 30));
        
        pt4.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt4.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt4, new AbsoluteConstraints(440, 220, 100, 20));
        
        arr4.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr4.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr4, new AbsoluteConstraints(540, 220, 80, 20));
        
        jProgressBar4.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar4, new AbsoluteConstraints(650, 220, 270, 30));
        
        rem_bt4.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt4.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt4, new AbsoluteConstraints(1010, 220, 80, 20));
        
        wt4.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt4.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt4, new AbsoluteConstraints(1150, 220, 80, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel7, new AbsoluteConstraints(430, 270, 30, 30));
        
        pt5.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt5.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt5, new AbsoluteConstraints(440, 270, 100, 20));
        
        arr5.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr5.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr5, new AbsoluteConstraints(540, 270, 80, 20));
        
        jProgressBar5.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar5, new AbsoluteConstraints(650, 270, 270, 30));
        
        rem_bt5.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt5.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt5, new AbsoluteConstraints(1010, 270, 80, 20));
        
        wt5.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt5.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt5, new AbsoluteConstraints(1150, 270, 80, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel8, new AbsoluteConstraints(430, 320, 30, 30));
        
        pt6.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt6.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt6, new AbsoluteConstraints(440, 320, 100, 20));
        
        arr6.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr6.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr6, new AbsoluteConstraints(540, 320, 80, 20));
        
        jProgressBar6.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar6, new AbsoluteConstraints(650, 320, 270, 30));
        
        rem_bt6.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt6.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt6, new AbsoluteConstraints(1010, 320, 80, 20));
        
        wt6.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt6.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt6, new AbsoluteConstraints(1150, 320, 80, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel9, new AbsoluteConstraints(430, 370, 30, 30));
        
        pt7.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt7.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt7, new AbsoluteConstraints(440, 370, 100, 20));
        
        arr7.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr7.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr7, new AbsoluteConstraints(540, 370, 80, 20));
        
        jProgressBar7.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar7, new AbsoluteConstraints(650, 370, 270, 30));
        
        rem_bt7.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt7.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt7, new AbsoluteConstraints(1010, 370, 80, 20));
        
        wt7.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt7.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt7, new AbsoluteConstraints(1150, 370, 80, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel10, new AbsoluteConstraints(430, 420, 30, 30));
        
        pt8.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt8.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt8, new AbsoluteConstraints(440, 420, 100, 20));
        
        arr8.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr8.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr8, new AbsoluteConstraints(540, 420, 80, 20));
        
        jProgressBar8.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar8, new AbsoluteConstraints(650, 420, 270, 30));
        
        rem_bt8.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt8.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt8, new AbsoluteConstraints(1010, 420, 80, 20));
        
        wt8.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt8.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt8, new AbsoluteConstraints(1150, 420, 80, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel11, new AbsoluteConstraints(430, 470, 30, 30));
        
        pt9.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt9.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt9, new AbsoluteConstraints(440, 470, 100, 20));
        
        arr9.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr9.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr9, new AbsoluteConstraints(540, 470, 80, 20));
        
        jProgressBar9.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar9, new AbsoluteConstraints(650, 470, 270, 30));
        
        rem_bt9.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt9.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt9, new AbsoluteConstraints(1010, 470, 80, 20));
        
        wt9.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt9.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt9, new AbsoluteConstraints(1150, 470, 80, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14));
        mainPanel.add(jLabel12, new AbsoluteConstraints(430, 520, 30, 30));
        
        pt10.setFont(new java.awt.Font("Tahoma", 1, 14));
        pt10.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(pt10, new AbsoluteConstraints(440, 520, 100, 20));
        
        arr10.setFont(new java.awt.Font("Tahoma", 1, 14));
        arr10.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(arr10, new AbsoluteConstraints(540, 520, 80, 20));
        
        jProgressBar10.setBackground(new java.awt.Color(0, 153, 255));
        mainPanel.add(jProgressBar10, new AbsoluteConstraints(650, 520, 270, 30));
        
        rem_bt10.setFont(new java.awt.Font("Tahoma", 1, 14));
        rem_bt10.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(rem_bt10, new AbsoluteConstraints(1010, 520, 80, 20));
        
        wt10.setFont(new java.awt.Font("Tahoma", 1, 14));
        wt10.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(wt10, new AbsoluteConstraints(1150, 520, 80, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); 
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ready Queue");
        mainPanel.add(jLabel2, new AbsoluteConstraints(110, 550, 120, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Algorithm: ");
        mainPanel.add(jLabel3, new AbsoluteConstraints(10, 10, 110, 30));

        algoSelected.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        algoSelected.setForeground(new java.awt.Color(255, 255, 255));
        algoSelected.setText("CPU:");
        mainPanel.add(algoSelected, new AbsoluteConstraints(10, 50, 70, 30));

        selectedAlgo.setBackground(new java.awt.Color(102, 102, 102));
        selectedAlgo.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        selectedAlgo.setForeground(new java.awt.Color(255, 255, 255));
        selectedAlgo.setText("jTextField1");
        mainPanel.add(selectedAlgo, new AbsoluteConstraints(120, 10, 250, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Average Waiting Time:");
        mainPanel.add(jLabel17, new AbsoluteConstraints(10, 200, 220, 50));

        avgServeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        avgServeLabel.setForeground(new java.awt.Color(255, 255, 255));
        avgServeLabel.setText("Average Turnaround Time:");
        mainPanel.add(avgServeLabel, new AbsoluteConstraints(10, 260, 250, 50));

        selectedJob.setBackground(new java.awt.Color(102, 102, 102));
        selectedJob.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        selectedJob.setForeground(new java.awt.Color(255, 255, 255));
        selectedJob.setText("P1");
        mainPanel.add(selectedJob, new AbsoluteConstraints(120, 50, 110, 30));

        avgWaitField.setBackground(new java.awt.Color(102, 102, 102));
        avgWaitField.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        avgWaitField.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(avgWaitField, new AbsoluteConstraints(280, 210, 80, 30));

        avgServeField.setBackground(new java.awt.Color(102, 102, 102));
        avgServeField.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        avgServeField.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(avgServeField, new AbsoluteConstraints(280, 270, 80, 30));

        totalExecField.setBackground(new java.awt.Color(102, 102, 102));
        totalExecField.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        totalExecField.setForeground(new java.awt.Color(255, 255, 255));
        mainPanel.add(totalExecField, new AbsoluteConstraints(280, 330, 80, 30));

        avgTurnLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        avgTurnLabel.setForeground(new java.awt.Color(255, 255, 255));
        avgTurnLabel.setText("Total Execution Time:");
        mainPanel.add(avgTurnLabel, new AbsoluteConstraints(10, 320, 250, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon("src\\background\\dark_half.jpg")); 
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

}
