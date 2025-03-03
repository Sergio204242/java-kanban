import java.util.ArrayList;

public interface TaskManager {
    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<Subtask> getSubtasks();

    void clearTasks();

    void clearEpics();

    void clearSubtasks();

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtasks(int id);

    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubTask(Subtask subtask);

    void changeStatusOfEpic(Epic epic);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    ArrayList<Subtask> getSubtasksByEpic(int id);

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubtaskById(int id);

    ArrayList<Task> getHistory();
}
