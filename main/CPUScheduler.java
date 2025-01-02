import java.awt.Color;
import java.util.concurrent.TimeUnit;
import javax.swing.*;


public class CPUScheduler extends Thread {
    Job[] jobBatch;
    Scheduler policy;
    JTextArea textArea;
    JTextField textField;
    JProgressBar[] pbars;
    JLabel[] burstTimes;
    ComputationThread[] myThreads = new ComputationThread[SchedulingGUI.NUM_OF_PROCESSES];
    JLabel[] waitingTimes, priorities;
    static int statusSum = 0;
    
    
    public CPUScheduler(Job[] jobBatch, Scheduler policy, JTextArea textArea, JTextField textField, JProgressBar[] pbars,
            JLabel[] burstTimes, JLabel[] waitingTimes, JLabel[] priorities) {
        this.jobBatch = jobBatch;
        this.policy = policy;
        this.textArea = textArea;
        this.textField = textField;
        this.pbars = pbars;
        this.burstTimes = burstTimes;
        this.waitingTimes = waitingTimes;
        this.priorities = priorities;
    }


    public void run() {
        if(CalcSimulation.algo.equals("FCFS")) {
            Job arrivedJob;

            // Enqueue all jobs in the jobBatch. jobBatch is an array of Job objects.
            for(int i=0;i<jobBatch.length;i++) {
                arrivedJob = jobBatch[i];
                policy.enqueue(arrivedJob);
            }

            int i=0;

            // While the policy is not empty, dequeue the job and start the computation thread.
            while(!policy.isEmpty()) {
                JProgressBar pbar = pbars[i];
                JLabel burstTime = burstTimes[i];
                long arrivalTime = policy.peek().job.arrivalTime;

                // Sleep for the arrival time of the job.
                try {
                    Thread.sleep(arrivalTime);
                } catch(Exception e) {}

                Job newJob = policy.dequeue();
                int durationInS = (int) TimeUnit.NANOSECONDS.toSeconds(newJob.waitTime);
                
                waitingTimes[i].setText(String.valueOf(durationInS) + "s");
                // creates a new computation thread for the job and starts it.
                myThreads[i] = new ComputationThread(newJob, policy, textArea, textField, pbar, burstTime);
                myThreads[i].t.start();

                i++;
            }
        }
        else if(CalcSimulation.algo.equals("Round Robin")) {
            // Enqueue all jobs in the jobBatch. jobBatch is an array of Job objects.

            for(int i = 0; i < jobBatch.length; i++) {
                jobBatch[i].progressBar = pbars[i];
                jobBatch[i].burstTimeLabel = burstTimes[i];
                jobBatch[i].waitTimeLabel = waitingTimes[i];
                policy.enqueue(jobBatch[i]); 
            }

            while(!policy.isEmpty()) {
                long arrivalTime = policy.peek().job.arrivalTime;
                try {
                    Thread.sleep(arrivalTime);
                } catch(Exception e) {}
                
                Job newJob = policy.dequeue();
                int durationInS = (int) TimeUnit.NANOSECONDS.toSeconds(newJob.waitTime);
                
                // Set the wait time label of the job.
                newJob.waitTimeLabel.setText(String.valueOf(durationInS) + "s");
                // creates a new computation thread for the job and starts it.
                myThreads[0] = new ComputationThread(newJob, policy, textArea, textField, 
                        newJob.progressBar, newJob.burstTimeLabel);
                myThreads[0].t.start();

                try {
                    myThreads[0].t.join();
                } catch(Exception e) {}

            }

            // Reset the computation threads.
            for(int j=1;j<myThreads.length;j++) {
                myThreads[j] = new ComputationThread(null, null, null, null, null, null);
            }
        }
        else if (CalcSimulation.algo.equals("Priority Scheduling")) {
            MaxPriorityQueue mp = new MaxPriorityQueue();
            MaxPriorityQueue tempmp = new MaxPriorityQueue();

            // Enqueue all jobs in the jobBatch with random priorities.
            for(int i = 0; i < jobBatch.length; i++) {
                int priority = (new java.util.Random().nextInt(10) + 1);
                JProgressBar pbar = pbars[i];
                JLabel burstTime = burstTimes[i];
                jobBatch[i].progressBar = pbar;
                jobBatch[i].burstTimeLabel = burstTime;
                jobBatch[i].priority = priority;
                jobBatch[i].waitTimeLabel = waitingTimes[i];
                priorities[i].setText(String.valueOf(jobBatch[i].priority));
                mp.insert(jobBatch[i]);
                tempmp.insert(jobBatch[i]);
            }

            // While the priority queue is not empty, dequeue the job and start the computation thread.
            while(!tempmp.isEmpty()) {
                policy.enqueue(tempmp.extractMax());
            }

            // While the priority queue is not empty, dequeue the job and start the computation thread.
            while(!mp.isEmpty()) {
                // fetch the arrival time of the job with the maximum priority.
                long arrivalTime = mp.getMax().arrivalTime;
                try {
                    Thread.sleep(arrivalTime);
                } catch(Exception e) {}

                Job newJob=mp.extractMax();
                policy.dequeue();
                newJob.waitTime=System.nanoTime()-newJob.startTime;
                int durationInS = (int) TimeUnit.NANOSECONDS.toSeconds(newJob.waitTime);
                newJob.waitTimeLabel.setText(String.valueOf(durationInS) + "s");

                // creates a new computation thread for the job and starts it.
                ComputationThread cpu=new ComputationThread(newJob, policy, textArea, 
                textField, newJob.progressBar, newJob.burstTimeLabel);
                cpu.t.start();

                try {
                    // waits for the computation thread to finish.
                    cpu.t.join();
                } catch(InterruptedException e) {}
            }
        }
        else {
            MaxPriorityQueue tempmp=new MaxPriorityQueue();

            for(int i = 0; i < jobBatch.length; i++) {
                int priority = (int) jobBatch[i].burstTime;
                JProgressBar pbar = pbars[i];
                JLabel burstTime = burstTimes[i];
                jobBatch[i].progressBar = pbar;
                jobBatch[i].burstTimeLabel = burstTime;
                jobBatch[i].priority = priority;
                jobBatch[i].waitTimeLabel = waitingTimes[i];
                priorities[i].setText(String.valueOf(jobBatch[i].priority));
                tempmp.insert(jobBatch[i]);
            }

            while(!tempmp.isEmpty()) {
                policy.enqueue(tempmp.extractMax());
            }

            while(!policy.isEmpty()) {
                long arrivalTime = policy.peek().job.arrivalTime;
                try {
                    Thread.sleep(arrivalTime);
                } catch(Exception e) {}

                Job newJob = policy.dequeue();
                newJob.waitTime = System.nanoTime() - newJob.startTime;
                int durationInS = (int) TimeUnit.NANOSECONDS.toSeconds(newJob.waitTime);
                newJob.waitTimeLabel.setText(String.valueOf(durationInS) + "s");
            
                ComputationThread cpu = new ComputationThread(newJob, policy, textArea, 
                        textField, newJob.progressBar, newJob.burstTimeLabel);
                cpu.t.start();

                try {
                    cpu.t.join();
                } catch(InterruptedException ex) {}
            }
        }

        System.out.println("GOT OUT");

        // if the algorithm is not Priority Scheduling or Shortest Job First, wait for all threads to finish. this is because the threads are not joined in the loop.
        if(!CalcSimulation.algo.equals("Priority Scheduling") && 
                !CalcSimulation.algo.equals("Shortest Job First")) {
            try {
                for(int j = 0; j < myThreads.length; j++)
                    myThreads[j].t.join();
            } catch(InterruptedException ex) {}
        }

        textField.setText("Idle");
        textField.setForeground(Color.green);

        // calculate the average wait time, average turnaround time, and total execution time.
        long avgWaitTime = 0;
        long avgTurnaroundTime = 0;
        long totalExecutionTime = 0;

        for(int i = 0; i < jobBatch.length; i++) {
            avgWaitTime += jobBatch[i].waitTime;
            avgTurnaroundTime += jobBatch[i].waitTime + jobBatch[i].burstTime;
            totalExecutionTime += jobBatch[i].burstTime;
        }

        avgWaitTime /= jobBatch.length;
        avgWaitTime = TimeUnit.NANOSECONDS.toSeconds(avgWaitTime);
        CalcSimulation.avgWaitField.setText(String.valueOf(avgWaitTime) + "s");

        avgTurnaroundTime = TimeUnit.NANOSECONDS.toSeconds(avgTurnaroundTime/jobBatch.length);
        CalcSimulation.avgServeField.setText(String.valueOf(avgTurnaroundTime) + "s");
        
        totalExecutionTime = System.nanoTime() - CalcSimulation.STRTTIME;
        totalExecutionTime = TimeUnit.NANOSECONDS.toSeconds(totalExecutionTime);
        CalcSimulation.totalExecField.setText(String.valueOf(totalExecutionTime) + "s");
    }
}
