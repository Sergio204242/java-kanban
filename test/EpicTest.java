import Exceptions.ManagerSaveException;
import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tasks.Epic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EpicTest {
    @Test
    public void compareEpics() {
        Epic epic = new Epic(1, "fs", "34hj");
        Epic epic1 = new Epic(1, "joj", "0i[");
        Assertions.assertEquals(epic, epic1);
    }

    @Test
    public void checkTaskbyFields() throws IOException, ManagerSaveException {
        TaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic(32, "fs", "34hj");
        final int epicId = epic.getId();
        taskManager.addEpic(epic);
        assertEquals(epic.getName(), taskManager.getEpic(1).getName(), "name не совпадает");
        assertEquals(epic.getDescription(), taskManager.getEpic(1).getDescription(), "description не совпадает");
        assertNotEquals(epicId, taskManager.getEpic(1).getId(), "id совпадают");
    }
}