package aproproject.Utilities;

import aproproject.Notifications.TestDataContainer;
import aproproject.Tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestDataContainerTest {

    TestDataContainer container = new TestDataContainer(10);

    @BeforeEach
    void setUp() {
        container.add(new Task(new Date(1_000_000_000L), "taskOne", "TaskOne", 10, 8,
                12, false));
        container.add(new Task(new Date(2_000_000_000L), "taskTwo", "TaskTwo", 1, 1,
                34, true));
        container.add(new Task(new Date(500_000_000L), "taskThree", "TaskThree", 7, 7,
                56, true));
        container.add(new Task(new Date(200_000_000L), "taskFour", "TaskFour", 2, 4,
                78, false));
        container.add(new Task(new Date(200_000_000L), "taskFive", "TaskFive", 2, 4,
                78, false));
    }

    @Test
    public void equalityTest(){
        assertEquals("TaskTwo", container.pq.poll().getName());
        assertEquals("TaskThree", container.pq.poll().getName());
        assertEquals("TaskFour", container.pq.poll().getName());
        assertEquals("TaskOne", container.pq.poll().getName());
        assertEquals("TaskFive", container.pq.poll().getName());
    }
}