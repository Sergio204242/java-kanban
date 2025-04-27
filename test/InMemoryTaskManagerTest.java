import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryTaskManagerTest {
    TaskManager inMemoryTaskManager = new InMemoryTaskManager();

    @Test
    public void addTasks() {
        Task task = new Task(2, "fwf", "hojr", Status.NEW);
        inMemoryTaskManager.addTask(task);
        final int taskId = task.getId();
        final Task savedTask = inMemoryTaskManager.getTask(taskId);
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        List<Task> tasks = inMemoryTaskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
    }

    @Test
    public void addEpic() {
        Epic epic = new Epic(67, "gef", "gwege");
        inMemoryTaskManager.addEpic(epic);
        final int epicId = epic.getId();
        final Epic savedEpic = inMemoryTaskManager.getEpic(epicId);
        assertNotNull(savedEpic, "Задача не найдена");
        assertEquals(epic, savedEpic, "Задачи не совпадают");

        List<Epic> epics = inMemoryTaskManager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.getFirst(), "Задачи не совпадают.");
    }

    @Test
    public void addSubtask() {
        Epic epic = new Epic(124, "fwq", "gewg");
        Subtask subtask = new Subtask(12,"rwq", "ge", Status.NEW, 1);
        inMemoryTaskManager.addEpic(epic);
        inMemoryTaskManager.addSubTask(subtask);
        final int subtaskId = subtask.getId();
        final Subtask savedSubtask = inMemoryTaskManager.getSubtasks(subtaskId);
        assertNotNull(savedSubtask, "Задача не найдена");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают");

        List<Subtask> subtasks = inMemoryTaskManager.getSubtasks();

        assertNotNull(subtasks, "Задачи не возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество задач.");
        assertEquals(subtask, subtasks.getFirst(), "Задачи не совпадают.");
    }
}