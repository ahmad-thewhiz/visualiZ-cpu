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
        this.job=job;
    }


    public void run() {
        policy.allocateCPU(job);
        boolean status=true;

        // Check the algorithm and set the progress bar for the job if the algorithm is FCFS, Priority Scheduling, or Shortest Job First
        if(CalcSimulation.algo.equals("FCFS") || CalcSimulation.algo.equals("Priority Scheduling")
            || CalcSimulation.algo.equals("Shortest Job First")) {
                int min=0;
                int max=(int)job.burstTime;

                pbar.setMinimum(min);
                pbar.setMaximum(max);
                pbar.setValue(0);

                String remProcesses=policy.getRemainingProcesses();
                readyQueue.setText(remProcesses);

                int remainingBurstTime=(int)job.burstTime;

                for(int i=0;i<max;i++) {
                    pbar.setValue(i);
                    
                    try {
                        // simulates the process by sleeping for 50ms and updating the text field
                        Thread.sleep(50);
                        synchronized(this) {
                            textField.setText("");
                            textField.setText(job.processID);
                        }
                    } catch(InterruptedException ex) {}

                    // decrement the remaining burst time and update the burst time label
                    remainingBurstTime--;
                    burstTime.setText(String.valueOf(remainingBurstTime)+"ms");
                }

                // increment the status sum if the process is completed
                if(status) {
                    CPUScheduler.statusSum++;
                    System.out.println(CPUScheduler.statusSum);
                }
            }
        
        else if(CalcSimulation.algo.equals("Round Robin")) {
            int min=job.pBarValue;
            int max=(int)job.burstTime;

            pbar.setMinimum(0);
            pbar.setMaximum(max);

            // set the remaining burst time and exceeded time
            int remainingBurstTime=(int)job.lastRemainingBurst;
            int exceedTime=0;

            // get the remaining processes and update the ready queue
            String remProcesses = policy.getRemainingProcesses();
            readyQueue.setText(remProcesses);

            // loop through the burst time and update the progress bar
            for(int i=min; i<max; i++) {
                pbar.setValue(i);

                // sleep for 50ms to simulate the process execution and update the text field
                try {
                    Thread.sleep(50);

                    // if the exceeded time is equal to the time quantum and there is remaining burst time then enqueue the job back with the updated remaining burst time
                    if(exceedTime==RoundRobinPolicy.TIME_QUANTUM-1 && remainingBurstTime>0) {
                        job.lastRemainingBurst=remainingBurstTime;
                        job.pBarValue=i;
                        policy.enqueue(job);
                        break;
                    }
                    // this is to update the text field which shows the current process
                    synchronized(this) {
                        textField.setText("");
                        textField.setText(job.processID);
                    }
                } catch(Exception ex) {}

                // decrement the remaining burst time and update the burst time label moreover increment the exceeded time
                remainingBurstTime--;
                burstTime.setText(String.valueOf(remainingBurstTime)+"ms");
                exceedTime++;
            }

            readyQueue.setText(policy.getRemainingProcesses());
        }

        // update the end time of the job. nanoTime() returns the current value of the running Java Virtual Machine's high-resolution time source, in nanoseconds
        job.endTime=System.nanoTime()-job.startTime;
    }
}   
