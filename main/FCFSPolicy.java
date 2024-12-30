import java.io.*;
import javax.swing.JOptionPane;

public class FCFSPolicy {
    File sourceFile;
    Node head, tail;

    FCFSPolicy(File sourceFile) {
        this.sourceFile=sourceFile;
        head=tail=null;
    }

    public boolean isEmpty() { 
        return head == null; 
    }
    
    public void enqueue(Job job) {
        Node newNode = new Node(job);
        
        if(isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
    }
    
    public Job dequeue() {
        if(isEmpty()) {
            return null;
        } else {
            Job tempJob = head.job;
            head = head.next;
            
            tempJob.waitTime = System.nanoTime() - tempJob.startTime;
            return tempJob;
        }
    }
    
    public void allocateCPU(Job job) {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(sourceFile));
            String str = new String();
            
            while((str = bufReader.readLine()) != null);
            
            String remProcesses = getRemainingProcesses();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "seems like an io error.");
            return;
        }
    }
    
    public void enqueueAtHead(Job job) {
        Node newNode = new Node(job);
        
        if(isEmpty()) 
            head = newNode;
        else {
            Node temp = head;
            head = newNode;
            head.next = temp;
        }
    }
    
    public Node peek() {
        return head;
    }
    
    public String getRemainingProcesses() {
        Node ptr = head;
        String remaining = new String();
        
        if(isEmpty()) 
            return "";
        else {
            while(ptr != null) {
                remaining += ptr.job.processID + " ";
                ptr = ptr.next;
            }
        }
        return remaining;
    }
}
