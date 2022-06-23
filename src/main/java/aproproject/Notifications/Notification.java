package aproproject.Notifications;

import aproproject.Tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Notification implements Comparable<Notification> {
    private String title;
    private String description;
    private Task relatedTask;
    private NotificationType notificationType;

    public Notification(String title, String description, Task relatedTask, NotificationType notificationType) {
        this.title = title;
        this.description = description;
        this.relatedTask = relatedTask;
        this.notificationType = notificationType;
    }
    public String caption() {
        return "TASK " + relatedTask.getId() + ": " + relatedTask.getName();
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public String text(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return "Deadline: " + format.format(relatedTask.getDeadline());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Notification{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", relatedTask=").append(relatedTask);
        sb.append(", notificationType=").append(notificationType);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return title.equals(that.title) && description.equals(that.description) && relatedTask.equals(that.relatedTask) && notificationType == that.notificationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, relatedTask, notificationType);
    }

    @Override
    public int compareTo(Notification o) {
        return relatedTask.compareTo(o.relatedTask);
    }
}
