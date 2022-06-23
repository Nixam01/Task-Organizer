package aproproject.Tasks;

import aproproject.Utilities.FileManagement;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TaskDatabase {
    private List<Task> taskList;
    private String fileName;
    private static TaskDatabase instance;

    /**
     * Method for getting instance of singleton TaskDatabase
     * @param taskList list of tasks to generate in case instance field is null
     * @param fileName path to the file where tasks are written
     * @return an instance of
     */
    public static TaskDatabase getInstance(List<Task> taskList, String fileName){
        if (instance == null){
            instance = new TaskDatabase(taskList, fileName);
        }
        return instance;
    }
    public static TaskDatabase getInstance(String fileName){
        if (instance == null){
            instance = new TaskDatabase(fileName);
        }
        return instance;
    }

    /**
     * Method for accessing instance of a singleton TaskDatabase
     * @return an instance of TaskDatabase
     */
    public static TaskDatabase getInstance() {
        if (instance == null){
            instance = new TaskDatabase();
        }
        return instance;
    }

    private TaskDatabase(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Constructor of TaskDatabase instance
     * @param taskList list of tasks to generate in case instance field is null
     * @param fileName path to the file where tasks are written
     */
    private TaskDatabase(List<Task> taskList, String fileName) {
        this.taskList = taskList;
        this.fileName = fileName;
    }

    private TaskDatabase(){
    }

    /**
     * dodaje nowego taska do listy tasków
     * @param task instancja obiektu Task do dodania do listy tasków
     */
    public void addTask(Task task){
        for (Task t : taskList){
            if (t.equals(task)) return;
        }
        taskList.add(task);
    }

    /**
     * updates all tasks, count internal priority, hours to deadline and sorts list of tasks
     */
    public void update(){
        for (Task task : taskList) {
            task.countInternalPriority();
        }
        taskList.sort(Task::compareTo);
    }

    /**
     * zapisuje listę tasków do pliku
     */
    public void saveToFile() throws IOException {

            FileManagement.writeToFile(taskList, fileName);

    }
    /**
     * znajduje taska po jego numerze id, przeszukując listę tasków
     * @param id identyfikator taska, który wskazuje na poszukiwany obiekt
     * @return znaleziony task
     */
    public Task findTask(int id){
        for (Task t : taskList){
            if (t.getId() == id)
                return t;
        }
        return null;
    }

    /**
     * edytuje informacje o danym tasku
     * @param id identyfikator wskazujący na konkretny task
     * @param task instancja klasy Task mająca nadpisać instancję wskazaną parametrem id
     */
    public void modifyTask(int id, Task task){
        Task t = findTask(id);
        if (t != null) {
            task.setId(id);
            taskList.set(taskList.indexOf(t), task);
        }
    }

    /**
     * usuwa taska z danym numerem id
     * @param id identyfikator taska do usunięcia
     */
    public void removeTask(int id){
        Task t = findTask(id);
        if (t == null) return;
        taskList.remove(t);
    }

    public void readFromFile() throws IOException {
        taskList = FileManagement.readFromFile(fileName);
    }

    /**
     * zwraca listę tasków
     * @return taskList
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * porównuje obiekty
     * @param o obiekt do porównania z wskazywaną przez this instancją TaskDatabase
     * @return wynik porównywania
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDatabase)) return false;
        TaskDatabase that = (TaskDatabase) o;
        return getTaskList().equals(that.getTaskList()) && fileName.equals(that.fileName);
    }

    /**
     * tworzy hashcode singletonu TaskDatabase
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(taskList, fileName);
    }
}
