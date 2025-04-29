import exceptions.ManagerSaveException;
import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Task;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    public void compareTasks() {
        Task task1 = new Task(1, "fw", "fwef", Status.NEW);
        Task task2 = new Task(1, "fd", "8yhj", Status.DONE);
        assertTrue(task1.equals(task2));
    }

    @Test
    public void checkTaskbyFields() throws IOException, ManagerSaveException {
        TaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task(124, "fw", "fwef", Status.NEW);
        final int taskId = task1.getId();
        taskManager.addTask(task1);
        assertEquals(task1.getName(), taskManager.getTask(1).getName(), "name не совпадает");
        assertEquals(task1.getDescription(), taskManager.getTask(1).getDescription(), "description не совпадает");
        assertEquals(task1.getStatus(), taskManager.getTask(1).getStatus(), "status не совпадает");
        assertNotEquals(taskId, taskManager.getTask(1).getId(), "id совпадают");
    }
}