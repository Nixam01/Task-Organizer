package aproproject.Notifications;
import aproproject.Notifications.Notification;
import aproproject.Notifications.NotificationManager;
import aproproject.Notifications.NotificationType;
import aproproject.Tasks.Task;
import aproproject.Tasks.TaskDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class NotificationManagerTest {

    TaskDatabase database;
    NotificationManager notificationManager;
    Queue<Notification> notificationQueue = new ArrayDeque<>();
    List<Task> tasks = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tasks.add(new Task(new Date(1621512000000L), "taskOne", "TaskOne", 10, 8,
                12, false));
        tasks.add(new Task(new Date(1621425600000L), "taskTwo", "TaskTwo", 1, 1,
                34, true));
        tasks.add(new Task(new Date(1621598400000L), "taskThree", "TaskThree", 7, 7,
                56, true));
        tasks.add(new Task(new Date(1590062400000L), "taskFour", "TaskFour", 2, 4,
                78, false));
        Notification notification1 = new Notification("notificationOne", "NotificationOne", tasks.get(0), NotificationType.WARNING);
        Notification notification2 = new Notification("notificationTwo", "NotificationTwo", tasks.get(1), NotificationType.WARNING);
        Notification notification3 = new Notification("notificationThree", "NotificationThree", tasks.get(2), NotificationType.WARNING);
        Notification notification4 = new Notification("notificationFour", "NotificationFour", tasks.get(3), NotificationType.WARNING);
        database = TaskDatabase.getInstance(tasks, "data.json");
        notificationManager = NotificationManager.getInstance();
        notificationManager.setNotificationQueue(notificationQueue);
    }

    @Test
    void testSendAllNotifications() {
    }

    @Test
    void testSendNotification() {
    }

    @Test
    void testBuildNotifications(){
//        Queue<Notification> expectedQueue = new ArrayDeque<>();
//        expectedQueue.add()
//        notificationManager.buildNotifications(tasks);
//        System.out.println(notificationManager);
//
//        assertEquals(notificationManager.getNotificationQueue(), );
    }

    @Test
    void testBuildDailyNotificationsQueue() {
    }
}