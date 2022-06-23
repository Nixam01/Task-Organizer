package aproproject.Tasks;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskDatabaseTest {

    TaskDatabase database;

    @BeforeEach
    void setUp() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(new Date(123123123123L), "taskOne", "TaskOne", 10, 8,
                12, false));
        tasks.add(new Task(new Date(123123653123L), "taskTwo", "TaskTwo", 1, 1,
                34, true));
        tasks.add(new Task(new Date(123123523123L), "taskThree", "TaskThree", 7, 7,
                56, true));
        tasks.add(new Task(new Date(123123993123L), "taskFour", "TaskFour", 2, 4,
                78, false));

        database = TaskDatabase.getInstance(tasks, "data.json");
    }

    @Test
    void addTask() {
        List<Task> tasks = new ArrayList<>();
        database.addTask(new Task(new Date(123123321321L), "taskFive", "TaskFive", 6, 9,
                12354321, false));

        assertEquals(5, database.getTaskList().size());
        assertEquals("taskFive", database.findTask(12354321).getDescription());
        assertNull(database.findTask(123454321));
        assertEquals(Task.class, database.findTask(12354321).getClass());
        database.removeTask(12354321);
    }

    @Test
    void sort() {
        database.update();
        assertEquals("TaskOne", database.getTaskList().get(0).getName());
        assertEquals("TaskThree", database.getTaskList().get(1).getName());
        assertEquals("TaskFour", database.getTaskList().get(2).getName());
        assertEquals("TaskTwo", database.getTaskList().get(3).getName());
    }

    @Test
    void saveToFile() {
    }

    @Test
    void findTask() {
        assertEquals("TaskThree", database.findTask(56).getName());
        assertEquals(2, database.findTask(78).getUserPriority());
        assertEquals(Task.class, database.findTask(78).getClass());
        assertNull(database.findTask(2137));

    }

    @Test
    void modifyTask() {
        database.modifyTask(34, new Task(new Date(123321321L), "taskSix", "TaskSix", 10, 10,
                910, true));
        assertEquals(34, database.findTask(34).getId());
        assertEquals(10, database.findTask(34).getInternalPriority());
        assertEquals(new Date(123321321L), database.findTask(34).getDeadline());
        assertEquals("TaskSix", database.findTask(34).getName());
        assertNull(database.findTask(910));
    }

    @Test
    void removeTask() {
        database.removeTask(34);
        assertEquals(3, database.getTaskList().size());
        assertNull(database.findTask(34));
        assertDoesNotThrow(() -> database.removeTask(555));
    }

    @Test
    void readFromFile() {
    }
}