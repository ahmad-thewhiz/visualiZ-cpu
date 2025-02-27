public class MaxPriorityQueue {
    private Job[] arr;
    private int current=-1;

    MaxPriorityQueue() {
        arr=new Job[10];
    }

    public int capacity() {
        return arr.length;
    }

    public int size() {
        return current+1;
    }

    public boolean isEmpty() {
        return (current+1)==0;
    }

    public void insert(Job job) {
        if(size()==capacity()){
            Job newArr[]=new Job[capacity()*2];

            for(int i=0;i<size();i++)
                newArr[i]=arr[i];

            arr=newArr;
        }
        arr[++current]=job;
    }

    public Job getMax() {
        if(isEmpty())
            throw new RuntimeException("error: queue is empty.");
        
        Job max=arr[0];
        for(int i=1;i<size();i++)
            if(arr[i].priority>max.priority)
                max=arr[i];

        return max;
    }

    public boolean contains(Job job){
        if(isEmpty())
            throw new RuntimeException("error: queue is empty.");
        
        for(Job j:arr)
            if(j==job)
                return true;
            
        return false;
    }

    public int getIndex(Job job) {
        if(!contains(job))
            throw new RuntimeException("error: {"+job.processID+"} not found in queue.");
    
        for(int i=0;i<size();i++)
            if(arr[i]==job)
                return i;
                    
        return -1;
        }

    public void removeAt(int idx){
        if(isEmpty())
            throw new RuntimeException("error: queue is empty.");
        
        for(int i=idx;i<size();i++)
            if(i!=size()-1)
                arr[i]=arr[i+1];
            
        --current;
    }

    public Job extractMax() {
        Job max=getMax();
        removeAt(getIndex(max));
        return max;
    }

    public int getPriority(int idx) {
        if(idx>=size() || idx<0)
            throw new RuntimeException("error: invalid index.");

        return arr[idx].priority;
    }


}
