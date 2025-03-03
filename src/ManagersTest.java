import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    @Test
    public void getDefault() {
        TaskManager taskManager = Managers.getDefault();

        assertNotNull(taskManager, "Дефолтного менеджера задач не существует");
        assertTrue(taskManager.getTasks().isEmpty(), "Менеджер задач не пустой");
        assertTrue(taskManager.getEpics().isEmpty(), "Менеджер задач не пустой");
        assertTrue(taskManager.getSubtasks().isEmpty(), "Менеджер задач не пустой");
        assertEquals(0, taskManager.getHistory().size(), "История просмотра не пустая");

    }

    @Test
    public void getHistoryDefault() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(historyManager);
        assertEquals(0, historyManager.getHistory().size(), "История просмотра не пустая");
    }
}