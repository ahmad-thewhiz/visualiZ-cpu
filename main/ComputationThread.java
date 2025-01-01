import javax.swing.*;

public class ComputationThread implements Runnable {
    Scheduler policy;
    Thread t;
    Job job;
    JTextField textField;
    JTextArea readyQueue;
    JProgressBar pbar;
    JLabel burstTime;

    ComputationThread(Job job, Scheduler policy, JTextArea readyQueue, JTextField textField, 
                        JProgressBar pbar, JLabel burstTime) {
        this.policy=policy;
        t=new Thread(this);
        this.job=job;
        this.readyQueue=readyQueue;
        this.textField=textField;
        this.pbar=pbar;
        this.burstTime=burstTime;
    }


    public void assignJob(Job job) {

    }


    public void run() {

    }
}   
