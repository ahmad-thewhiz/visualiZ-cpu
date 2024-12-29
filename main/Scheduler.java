public class Scheduler {
    public boolean isEmpty();
    public Node peek();
    public void enqueue(Job job);
    public Job dequeue();
    public String getRemainingProcesses(); 
    public void allocateCPU(Job job);
}
