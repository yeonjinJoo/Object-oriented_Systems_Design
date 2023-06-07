package Week14.Practice2;
import java.util.ArrayList;

public class PriorityQueue<T> {
    ArrayList<Queue<T>> queueList;

    public class Queue<T>{
        T item;
        int priority;

        public Queue(T item, int priority){
            this.item = item;
            this.priority = priority;
        }
    }

    public PriorityQueue() {
        queueList = new ArrayList<Queue<T>>();
    }

    public void add(T item, int priority){
        int i;
        for(i = 0; i < queueList.size(); i++){
            if(queueList.get(i).priority < priority){
                break;
            }
        }
        queueList.add(i, new Queue<T>(item, priority));
    }

    public T remove(){
        if(queueList.size() == 0){
            return null;
        }
        return queueList.remove(0).item;
    }
}
