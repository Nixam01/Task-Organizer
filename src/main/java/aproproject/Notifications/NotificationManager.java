package aproproject.Notifications;

import aproproject.Tasks.Task;

import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class NotificationManager {
    private Queue<Notification> notificationQueue;
    private static NotificationManager instance;

    /**
     * Method for accessing instance of a singleton NotificationManager
     * @return an instance of NotificationManager
     */
    public static NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }


    private NotificationManager() {
        notificationQueue = new PriorityQueue<>();
    }

    /**
     * Method for sending all notification to the user
     */
    public void sendAllNotifications() {
        while (!notificationQueue.isEmpty()) {
            sendNotification();
        }
    }

    /**
     * Method which removes the notification from top of the queue
     */
    public Notification sendNotification() {
        return notificationQueue.poll();
    }

    /**
     * Method for building notifications queue of casual type
     * @param list list of tasks from which queue will be built
     */
    public void buildNotifications(List<Task> list, int DEADLINENotificationBeforeDeadline) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        for (Task task : list) {
            if (task.countHoursToDeadline() <= 0 && !task.wasDeadlineSent()) {
                notificationQueue.add(new Notification(task.getName(), task.getDescription(), task, NotificationType.WARNING));
                task.setWasDEADLINESent(true);
            } else if (task.countHoursToDeadline() <= DEADLINENotificationBeforeDeadline) {
                notificationQueue.add(new Notification(task.getName(), task.getDescription(), task, NotificationType.DEADLINE));
            }
        }
    }
    public Queue<Notification> getNotificationQueue() {
        return notificationQueue;
    }

    public void setNotificationQueue(Queue<Notification> notificationQueue) {
        this.notificationQueue = notificationQueue;
    }
}
