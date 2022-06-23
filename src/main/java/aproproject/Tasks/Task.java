package aproproject.Tasks;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Task implements Comparable<Task> {
    private Date deadline;
    private String description;
    private String name;
    private int userPriority;
    private int internalPriority;
    private int id;
    private boolean wasDEADLINESent;

    /**
     * tworzy nowego taska
     * @param deadline
     * @param description
     * @param name
     * @param userPriority
     * @param internalPriority
     * @param id
     * @param wasDEADLINESent
     */
    public Task(Date deadline, String description, String name, int userPriority, int internalPriority, int id, boolean wasDEADLINESent) {
        this.deadline = deadline;
        this.description = description;
        this.name = name;
        this.userPriority = userPriority;
        this.internalPriority = internalPriority;
        this.id = id;
        this.wasDEADLINESent = wasDEADLINESent;
    }

    public Task(Date deadline, String name, String description,  int userPriority, int id) {
        this.deadline = deadline;
        this.description = description;
        this.name = name;
        this.userPriority = userPriority;
        this.id = id;
    }
    //constructor for application
    public Task(Date deadline, String description, String name, int userPriority) {
        this.deadline = deadline;
        this.description = description;
        this.name = name;
        this.userPriority = userPriority;
    }

    public Task() {

    }

    public long countHoursToDeadline() {
        long millis = System.currentTimeMillis();
        Date current = new Date(millis);
        long difference = deadline.getTime() - current.getTime();
        long hoursInMilli = 1000 * 60 * 60;
        return difference / hoursInMilli;
    }

    public void countInternalPriority() {
        long toDeadline = countHoursToDeadline();
        DeadlinePriority priority;
        if (toDeadline <= 24) priority = DeadlinePriority.HIGH;
        else if (toDeadline <= 48) priority = DeadlinePriority.MEDIUM_HIGH;
        else if (toDeadline <= 96) priority = DeadlinePriority.MEDIUM;
        else  priority = DeadlinePriority.LOW;
        internalPriority = userPriority + priority.getPriorityValue();
    }

    /**
     *
     * @return informacje o tasku w stringu
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TASK NR " + id + " \n");
        //sb.append("deadline: ").append(deadline).append("\n");
        //sb.append("description: '").append(description).append('\'').append("\n");
        sb.append(" ").append(name).append("\n");
        //sb.append("userPriority: ").append(userPriority).append("\n");
        //sb.append("internalPriority: ").append(internalPriority).append("\n");
        //sb.append("id: ").append(id).append("\n");
        //sb.append("wasDEADLINESent: ").append(wasDEADLINESent).append("\n");
        sb.append("\n");
        return sb.toString();
    }
    public String toStringForTaskManager(){
        final StringBuilder sb = new StringBuilder("TASK NR " + id + "\n");
        sb.append("Data wykonania: ").append(deadline).append('\'').append("\n");
        sb.append("Priorytet: ").append(internalPriority).append("\n");
        sb.append("Opis: ").append(description);
        return sb.toString();
    }
    public String toStringforGUI() {
        final StringBuilder sb = new StringBuilder("TASK NR " + id + "\n");
        sb.append("deadline: ").append(deadline).append("\n");
        sb.append("name: '").append(name).append('\'').append("\n");
        return sb.toString();
    }
    public String getTasksForTodaytoString(List<Task> tasks){
        StringBuilder sb = new StringBuilder();
        for(Task t: tasks){
            sb.append("ID").append(id).append("\n");
            sb.append("deadline: ").append(deadline).append("\n");
        }
        return sb.toString();


    }
    /**
     * zwraca deadline danego taska
     * @return data deadline'u wskazanego zadania
     */
    @JsonGetter
    public Date getDeadline() {
        return deadline;
    }

    /**
     * zwraca opis danego taska
     * @return opis wskazanego zadania
     */
    @JsonGetter
    public String getDescription() {
        return description;
    }

    /**
     * zwraca nazwę danego taska
     * @return nazwa wskazanego zadania
     */
    @JsonGetter
    public String getName() {
        return name;
    }

    /**
     * zwraca priorytetowość danego użytkownika
     * @return parametr userPriority wskazanego zadania
     */
    @JsonGetter
    public int getUserPriority() {
        return userPriority;
    }

    /**
     * zwraca wewnętrzną priorytetowość
     * @return parametr internalPriority wskazanego zadania
     */
    @JsonGetter
    public int getInternalPriority() {
        return internalPriority;
    }

    /**
     * zwraca numer id danego taska
     * @return id
     */
    @JsonGetter
    public int getId() {
        return id;
    }

    /**
     * zwraca informację, czy deadline został wysłany
     * @return wasDEADLINESent
     */
    @JsonGetter
    public boolean wasDeadlineSent() {
        return wasDEADLINESent;
    }

    /**
     * zwraca wynik porównania priorytetów
     * @param o instancja klasy Task, z którą porównujemy wskazany przez this obiekt
     * @return
     */


    @Override
    public int compareTo(Task o) {
        if (internalPriority > o.internalPriority) return -1;
        if (internalPriority == o.internalPriority) return 0;
        return 1;
    }

    /**
     * ustawia deadline danego taska
     * @param deadline
     */
    @JsonSetter
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * ustawia opis danego taska
     * @param description
     */
    @JsonSetter
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ustawia nazwę danego taska
     * @param name
     */
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ustawia priorytetowość danego użytkownika
     * @param userPriority
     */
    @JsonSetter
    public void setUserPriority(int userPriority) {
        this.userPriority = userPriority;
    }

    /**
     * ustawia wewnętrzną priorytetowość
     * @param internalPriority
     */
    @JsonSetter
    public void setInternalPriority(int internalPriority) {
        this.internalPriority = internalPriority;
    }

    /**
     * ustawia numer id danego taska
     * @param id
     */
    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }

    /**
     * ustawia informację, czy deadline został wysłany
     * @param wasDEADLINESent
     */
    @JsonSetter
    public void setWasDEADLINESent(boolean wasDEADLINESent) {
        this.wasDEADLINESent = wasDEADLINESent;
    }

    /**
     * sprawdza czy obiekty są równe
     * @param o porównywany obiekt
     * @return wartość logiczna opisująca równość obu obiektów
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    /**
     * tworzy hashcode wskazanej instancji klasy Task
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getDeadline(), getDescription(), getName(), getUserPriority(), getInternalPriority(), getId(), wasDeadlineSent());
    }

}
