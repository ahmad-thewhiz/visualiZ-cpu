import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;


class Stack implements Scheduler {
    private Node top;
    File sourceFile;

    Stack() {
        top=null;
    }


    Stack(File sourceFile) {
        this.sourceFile=sourceFile;
        top=null;
    }


    public boolean isEmpty() {
        return top==null;
    }


    public Node peek() {
        if(isEmpty())
            throw new RuntimeException("stack underflow.");
        return top;
    }


    public void enqueue(Job job) {
        Node newest=new Node(job);
        
        if(isEmpty())
            top=newest;
        else {
            newest.next=top;
            top=newest;
        }
    }


    public Job dequeue() {
        if(isEmpty())
            throw new RuntimeException("stack underflow.");
        
            Job data=top.job;
            top=top.next;
            return data;
    }


    public String getRemainingProcesses() {
        Node ptr=top;
        String remaining=new String();

        if(isEmpty())
            return "";
        else {
            while(ptr!=null) {
                remaining += ptr.job.processID + " ";
                ptr=ptr.next;
            }
        }

        return remaining;
    }


    public void allocateCPU(Job job) {
        try {
            BufferedReader read=new BufferedReader(new FileReader(sourceFile));
            String str=new String();

            while((str=read.readLine())!=null);

            String remProcesses=getRemainingProcesses();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "oops! seems we have an io error.");
            return;
        }
    }

}
