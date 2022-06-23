package aproproject.Tasks;

import aproproject.Tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.Date;

class TaskTest {
    static Task task = new Task(new Date(123123123123L), "taskOne", "TaskOne", 10, 8,
            1, false);

    @Test
    void countHoursToDeadline() {

    }

    @Test
    void countInternalPriority() {
    }
}