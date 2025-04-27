import Exceptions.ManagerSaveException;
import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SubtaskTest {
    @Test
    public void compareSubtasks() {
        Subtask subtask = new Subtask(5, "gew", "ojog", Status.NEW, 1);
        Subtask subtask1 = new Subtask(5, "gew", "ojog", Status.NEW, 1);
        assertEquals(subtask, subtask1);
    }

    @Test
    public void checkTaskbyFields() throws IOException, ManagerSaveException {
        TaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic(1, "fewf", "gfewge");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask(1, "gew", "ojog", Status.NEW, 1);
        final int subtaskId = subtask.getId();
        taskManager.addSubTask(subtask);
        assertEquals(subtask.getName(), taskManager.getSubtasks(2).getName(), "name не совпадает");
        assertEquals(subtask.getDescription(), taskManager.getSubtasks(2).getDescription(), "description не совпадает");
        assertEquals(subtask.getStatus(), taskManager.getSubtasks(2).getStatus(), "status не совпадает");
        assertNotEquals(subtaskId, taskManager.getSubtasks(2).getId(), "id совпадают");
    }
}