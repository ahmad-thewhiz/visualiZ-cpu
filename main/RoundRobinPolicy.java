import java.io.*;
import javax.swing.*;

public class RoundRobinPolicy implements Scheduler{
    static final long TIME_QUANTUM=140;
    File sourceFile;
    Node head=null, tail=null;

    RoundRobinPolicy(File sourcFile) {
        this.sourceFile=sourcFile;
    }


    public boolean isEmpty() {
        return head == null;
    }


    public Node peek(){
        return head;
    }


    public void enqueue(Job job) {
        Node newNode=new Node(job);
        if(isEmpty()) 
            head=tail=newNode;
        else {
            tail.next=newNode;
            tail=tail.next;
        }
    }


    public Job dequeue() {
        if(isEmpty())
            return null;
        
        Job tempJob=head.job;
        head=head.next;

        tempJob.waitTime = System.nanoTime()-tempJob.startTime;
        return tempJob;
    }


    public void allocateCPU(Job job) {
        try {
            BufferedReader read=new BufferedReader(new FileReader(sourceFile));
            String str=new String();

            while((str=read.readLine())!=null);

            String remProcesses=getRemainingProcesses();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "seems like an io error.", 0);
            return ;
        }
    }


    public String getRemainingProcesses() {
        Node traverse=head;
        String remaining=new String();

        if(isEmpty())
            return "";
        
        while(traverse!=null) {
            remaining+=traverse.job.processID+" ";
            traverse=traverse.next;
        }

        return remaining;
    }
}
