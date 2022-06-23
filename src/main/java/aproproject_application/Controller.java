package aproproject_application;

import aproproject.Notifications.Notification;
import aproproject.Notifications.NotificationManager;
import aproproject.Notifications.NotificationType;
import aproproject.Tasks.Task;
import aproproject.Tasks.TaskDatabase;
import aproproject.Utilities.FileManagement;
import aproproject_application.gui.StartClientView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class Controller {
    private TaskDatabase database;
    private NotificationManager notifications = NotificationManager.getInstance();
    private Settings settings;
    private TimeManagement timeManagement = new TimeManagement();
    private static Controller instance;
    private static String  fileNameWithoutExtension = "system";
    private SystemTray systemTray;
    private TrayIcon icon;
    private static int nextTaskId;
    //if file doesn't exist, instance will be null
    public static Controller getInstance(){
        if (instance == null && Files.exists(Path.of(fileNameWithoutExtension + ".json")) && Files.exists(Path.of(fileNameWithoutExtension + ".properties")) && Files.exists(Path.of(fileNameWithoutExtension + ".txt"))){
            try {
                instance = new Controller(fileNameWithoutExtension);
            } catch (IOException e) {
                instance = null;
            }
        }
        return instance;
    }

    private Controller() {
    }

    private Controller(String fileNameWithoutExtension) throws IOException{
        database = TaskDatabase.getInstance(fileNameWithoutExtension + ".json");
        settings = Settings.getInstance(fileNameWithoutExtension + ".properties");
        Scanner scanner = new Scanner(new File(fileNameWithoutExtension + ".txt"));
        nextTaskId = scanner.nextInt();
    }
    public void startLogic() throws IOException {
        database.readFromFile();
        database.update();
        settings.loadSettings();
        notificationsSetup();
        timeManagement.executeEveryFullHour(settings.getStartOfWorkingTime(),settings.getEndOfWorkingTime());
        timeManagement.executeEveryDay(settings.getPreferredDailyNotificationTime());
        getDeadlineWarningNotifications();
        if (LocalTime.now().isAfter(settings.getPreferredDailyNotificationTime())) getDailyNotifications();
    }
    public void addTask(Task task) throws IOException {
        task.setId(getNextTaskId());
        database.addTask(task);
        database.update();
        database.saveToFile();
    }

    public void modifyTask(int id, Task task) throws IOException {
        database.modifyTask(id,task);
        database.update();
        database.saveToFile();
    }
    public void removeTask(int id) throws IOException {
        database.removeTask(id);
        database.update();
        database.saveToFile();
    }
    public void getDailyNotifications(){
        database.update();
        icon.displayMessage("New tasks for today!","", TrayIcon.MessageType.INFO);
        // TODO: 09.06.2021 reload gui tasks
    }
    public void getDeadlineWarningNotifications(){
        database.update();
        notifications.buildNotifications(database.getTaskList(), settings.getDEADLINENotificationBeforeDeadline());
        Notification temp;
        while ((temp = notifications.sendNotification()) != null){
            icon.displayMessage(temp.caption(), temp.text(), (temp.getNotificationType() == NotificationType.WARNING) ? TrayIcon.MessageType.ERROR : TrayIcon.MessageType.WARNING);
        }
    }
    public Task getTask(int id){
        return database.findTask(id);
    }

    public static String getFileNameWithoutExtension() {
        return fileNameWithoutExtension;
    }

    public List<Task> getTasksForToday(){
        database.update();
        int n = settings.getNumberOfDailyNotifications();
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < Math.min(n,database.getTaskList().size()); i++) {
            temp.add(database.getTaskList().get(i));
        }
        return temp;
    }

    private List<Task> getTasksAtSpecifiedDeadline(Date from, Date to){
        database.update();
        List<Task> temp = new ArrayList<>();
        for (Task task : database.getTaskList()) {
            if (from.before(task.getDeadline()) && to.before(task.getDeadline())) temp.add(task);
        }
        return temp;
    }
    public void saveTasksToFile(List<Task> tasks, String fileName){
        FileManagement.writeToReadableFile(tasks, fileName);
    }
    public List<Task> getTasksAtDay(Date day) {
        database.update();
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.setTime(day);
        to.setTime(day);
        from.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        to.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return getTasksAtSpecifiedDeadline(from.getTime(),to.getTime());
    }
    public List<Task> getAllTasks(){
        database.update();
        return database.getTaskList();
    }
    private int getNextTaskId() throws IOException {
        FileWriter fw = new FileWriter(fileNameWithoutExtension + ".txt");
        nextTaskId++;
        fw.write(String.valueOf(nextTaskId));
        fw.close();
        return nextTaskId;
    }
    private void notificationsSetup(){
        systemTray = SystemTray.getSystemTray();
        icon = new TrayIcon(Toolkit.getDefaultToolkit().createImage("icon.png"));
        icon.setImageAutoSize(true);
        try {
            systemTray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        icon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartClientView();
            }
        });
    }
}
