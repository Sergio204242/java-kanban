package Tests;

import Manager.InMemoryTaskManager;
import Manager.TaskManager;
import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    public void compareSubtasks() {
        Subtask subtask = new Subtask("gew", "ojog", 5, Status.NEW, 1);
        Subtask subtask1 = new Subtask("gew", "ojog", 5, Status.NEW, 1);
        assertTrue(subtask.equals(subtask1));
    }

    @Test
    public void checkTaskbyFields() {
        TaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic("fewf", "gfewge", 1);
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("gew", "ojog", 1, Status.NEW, 1);
        final int subtaskId = subtask.getId();
        taskManager.addSubTask(subtask);
        assertEquals(subtask.getName(), taskManager.getSubtasks(2).getName(), "name не совпадает");
        assertEquals(subtask.getDescription(), taskManager.getSubtasks(2).getDescription(), "description не совпадает");
        assertEquals(subtask.getStatus(), taskManager.getSubtasks(2).getStatus(), "status не совпадает");
        assertNotEquals(subtaskId, taskManager.getSubtasks(2).getId(), "id совпадают");
    }
}