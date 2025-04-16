import Manager.InMemoryTaskManager;
import Manager.TaskManager;
import org.junit.jupiter.api.Assertions;
import Tasks.Epic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    @Test
    public void compareEpics() {
        Epic epic = new Epic("fs", "34hj", 2);
        Epic epic1 = new Epic("joj", "0i[", 2);
        Assertions.assertEquals(epic, epic1);
    }

    @Test
    public void checkTaskbyFields() {
        TaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic("fs", "34hj", 32);
        final int epicId = epic.getId();
        taskManager.addEpic(epic);
        assertEquals(epic.getName(), taskManager.getEpic(1).getName(), "name не совпадает");
        assertEquals(epic.getDescription(), taskManager.getEpic(1).getDescription(), "description не совпадает");
        assertNotEquals(epicId, taskManager.getEpic(1).getId(), "id совпадают");
    }
}