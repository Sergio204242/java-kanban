package manager;

import exceptions.ManagerSaveException;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;
import java.util.List;

public interface TaskManager {
    List<Task> getTasks();

    List<Epic> getEpics();

    List<Subtask> getSubtasks();

    void clearTasks() throws IOException, ManagerSaveException;

    void clearEpics() throws IOException, ManagerSaveException;

    void clearSubtasks() throws IOException, ManagerSaveException;

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtasks(int id);

    void addTask(Task task) throws IOException, ManagerSaveException;

    void addEpic(Epic epic) throws IOException, ManagerSaveException;

    void addSubTask(Subtask subtask) throws IOException, ManagerSaveException;

    void updateTask(Task task) throws IOException, ManagerSaveException;

    void updateEpic(Epic epic) throws IOException, ManagerSaveException;

    void updateSubtask(Subtask subtask) throws IOException, ManagerSaveException;

    List<Subtask> getSubtasksByEpic(int id);

    void removeTaskById(int id) throws IOException, ManagerSaveException;

    void removeEpicById(int id) throws IOException, ManagerSaveException;

    void removeSubtaskById(int id) throws IOException, ManagerSaveException;

    List<Task> getHistory();
}
