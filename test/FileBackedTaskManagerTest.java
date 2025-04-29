import exceptions.ManagerSaveException;
import manager.FileBackedTaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.io.File;
import java.io.IOException;

class FileBackedTaskManagerTest {
    private final File test = File.createTempFile("test", "txt");

    private final FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(test);

    FileBackedTaskManagerTest() throws IOException {
    }

    @Test
    public void addTasks() throws IOException, ManagerSaveException {
        Task task = new Task(1, "fwef", "fewf", Status.NEW);
        Epic epic = new Epic(1, "fef", "fwf");
        Epic epic1 = new Epic(1, "ffwaef", "fwf");
        Subtask subtask = new Subtask(1, "fwfbf", "vxdvs", Status.NEW, 2);
        fileBackedTaskManager.addTask(task);
        fileBackedTaskManager.addEpic(epic);
        fileBackedTaskManager.addEpic(epic1);
        fileBackedTaskManager.addSubTask(subtask);
        Assertions.assertEquals(1, fileBackedTaskManager.getTasks().size());
        Assertions.assertEquals(2, fileBackedTaskManager.getEpics().size());
        Assertions.assertEquals(1, fileBackedTaskManager.getSubtasks().size());
    }

    @Test
    public void getTasks() throws IOException, ManagerSaveException {
        Task task = new Task(0, "fwef", "fewf", Status.NEW);
        Epic epic = new Epic(1, "fef", "fwf");
        Subtask subtask = new Subtask(1, "fs", "fgwefwae", Status.DONE, 2);
        fileBackedTaskManager.addTask(task);
        fileBackedTaskManager.addEpic(epic);
        Assertions.assertEquals(task, FileBackedTaskManager.loadFromFile(test).getTask(1));
        Assertions.assertEquals(epic, FileBackedTaskManager.loadFromFile(test).getEpic(2));
        fileBackedTaskManager.addSubTask(subtask);
        Assertions.assertEquals(subtask, FileBackedTaskManager.loadFromFile(test).getSubtasks(3));

    }

}
