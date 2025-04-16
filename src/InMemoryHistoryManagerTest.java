import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryHistoryManagerTest {
    @Test
    public void addTaskWithSavingPrevious() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        Task task = new Task("fsf", "geg", 1, Status.NEW);
        historyManager.add(task);
        task.setStatus(Status.DONE);
        historyManager.add(task);
        assertEquals(2, historyManager.getHistory().size(), "Предыдущая версия не сохранилась");
    }
}