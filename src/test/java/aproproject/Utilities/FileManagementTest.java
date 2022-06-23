package aproproject.Utilities;

import aproproject.Tasks.Task;
import aproproject.Tasks.TaskDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagementTest {

    TaskDatabase database;
    List<Task> tasks = new ArrayList<>();

    @BeforeEach
    void setUp() {
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
    void writeToFile() {
    }

    @Test
    void readFromFile() {
        try {
            Task task = new Task(new Date(123123123123L), "taskOne", "TaskOne", 10, 8,
                    12354321, true);
            List<Task> tasksList = new ArrayList<>();
            tasksList.add(task);
            tasksList.add(task);
            File jsonFile = new File("data.json");
            FileManagement.writeToFile(tasksList, "data.json");
            List<Task> receivedTasks = FileManagement.readFromFile("data.json");
            assertEquals(Task.class, receivedTasks.get(0).getClass());
            assertEquals(ArrayList.class, receivedTasks.getClass());
            assertEquals("taskOne", receivedTasks.get(0).getDescription());
            assertEquals("TaskOne", receivedTasks.get(0).getName());
            assertEquals(new Date(123123123123L), receivedTasks.get(0).getDeadline());
            assertEquals(10, receivedTasks.get(0).getUserPriority());
            assertEquals(8, receivedTasks.get(1).getInternalPriority());
            assertFalse(receivedTasks.get(1).wasDeadlineSent());
            assertInstanceOf(ArrayList.class, receivedTasks);
            assertInstanceOf(Task.class, receivedTasks.get(0));
            assertInstanceOf(Task.class, receivedTasks.get(1));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void readFromIllegalFile() {
        assertDoesNotThrow(() -> FileManagement.readFromFile("file_that_not_exists.json"));
        assertDoesNotThrow(() -> FileManagement.readFromFile("readable_data.txt"));
    }

    @Test
    void writeToReadableFile(){
        FileManagement.writeToReadableFile(tasks, "readable_data.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader("readable_data.txt"))){
        StringBuilder message = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                message.append(line).append("\n");
            }

            String content = message.toString();
            assertTrue(content.contains("taskFour"));
            assertTrue(content.contains("Mon Nov 26 01:52:03 CET 1973"));
            assertTrue(content.contains("name: 'TaskFour'"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}