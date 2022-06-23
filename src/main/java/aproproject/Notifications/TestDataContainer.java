package aproproject.Notifications;

import aproproject.Tasks.Task;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TestDataContainer {
    public PriorityQueue<Task> pq;
    public int capacity;

    public TestDataContainer(int capacity){
        this.pq = new PriorityQueue<>(capacity, (o1, o2) -> {
            if (o1.countHoursToDeadline() < o2.countHoursToDeadline()) return -1;
            else if (o1.countHoursToDeadline() > o2.countHoursToDeadline()) return 1;
            else return 0;
        });
    }

    public void add(Task task){
        pq.add(task);
    }
}
