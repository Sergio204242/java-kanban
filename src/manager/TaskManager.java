package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getTasks();

    List<Epic> getEpics();

    List<Subtask> getSubtasks();

    void clearTasks();

    void clearEpics();

    void clearSubtasks();

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtasks(int id);

    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubTask(Subtask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    List<Subtask> getSubtasksByEpic(int id);

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubtaskById(int id);

    List<Task> getHistory();
}
