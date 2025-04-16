package test;

import manager.HistoryManager;
import manager.InMemoryHistoryManager;
import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryHistoryManagerTest {
    @Test
    public void addTaskWithSavingPrevious() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        Task task = new Task("fsf", "geg", 1, Status.NEW);
        historyManager.add(task);
        task.setStatus(Status.DONE);
        historyManager.add(task);
        assertEquals(1, historyManager.getHistory().size(), "Предыдущая версия не сохранилась");
    }

    @Test
    public void testAddTask() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        Task task = new Task("fw", "eg", 1, Status.NEW);
        Task task1 = new Task("ge", "hro", 2, Status.NEW);
        historyManager.add(task);
        historyManager.add(task);
        assertEquals(1, historyManager.getHistory().size());
        historyManager.add(task1);
        assertEquals(2, historyManager.getHistory().size());
    }

    @Test
    public void testRemoveTask() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        Task task = new Task("fw", "eg", 1, Status.NEW);
        historyManager.add(task);
        historyManager.remove(1);
        assertEquals(0, historyManager.getHistory().size());
    }
}