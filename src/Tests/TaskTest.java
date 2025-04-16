package Tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    public void compareTasks() {
        Task task1 = new Task("fw", "fwef", 1, Status.NEW);
        Task task2 = new Task("fd", "8yhj", 1, Status.DONE);
        assertTrue(task1.equals(task2));
    }

    @Test
    public void checkTaskbyFields() {
        TaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task("fw", "fwef", 124, Status.NEW);
        final int taskId = task1.getId();
        taskManager.addTask(task1);
        assertEquals(task1.getName(), taskManager.getTask(1).getName(), "name не совпадает");
        assertEquals(task1.getDescription(), taskManager.getTask(1).getDescription(), "description не совпадает");
        assertEquals(task1.getStatus(), taskManager.getTask(1).getStatus(), "status не совпадает");
        assertNotEquals(taskId, taskManager.getTask(1).getId(), "id совпадают");
    }
}