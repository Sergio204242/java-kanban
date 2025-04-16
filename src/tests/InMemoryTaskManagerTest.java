package tests;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryTaskManagerTest {
    TaskManager inMemoryTaskManager = new InMemoryTaskManager();

    @Test
    public void addTasks() {
        Task task = new Task("fwf", "hojr", 2, Status.NEW);
        inMemoryTaskManager.addTask(task);
        final int taskId = task.getId();
        final Task savedTask = inMemoryTaskManager.getTask(taskId);
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        List<Task> tasks = inMemoryTaskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    public void addEpic() {
        Epic epic = new Epic("gef", "gwege", 67);
        inMemoryTaskManager.addEpic(epic);
        final int epicId = epic.getId();
        final Epic savedEpic = inMemoryTaskManager.getEpic(epicId);
        assertNotNull(savedEpic, "Задача не найдена");
        assertEquals(epic, savedEpic, "Задачи не совпадают");

        List<Epic> epics = inMemoryTaskManager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");
    }

    @Test
    public void addSubtask() {
        Epic epic = new Epic("fwq", "gewg", 124);
        Subtask subtask = new Subtask("rwq", "ge", 12, Status.NEW, 1);
        inMemoryTaskManager.addEpic(epic);
        inMemoryTaskManager.addSubTask(subtask);
        final int subtaskId = subtask.getId();
        final Subtask savedSubtask = inMemoryTaskManager.getSubtasks(subtaskId);
        assertNotNull(savedSubtask, "Задача не найдена");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают");

        List<Subtask> subtasks = inMemoryTaskManager.getSubtasks();

        assertNotNull(subtasks, "Задачи не возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество задач.");
        assertEquals(subtask, subtasks.get(0), "Задачи не совпадают.");
    }
}