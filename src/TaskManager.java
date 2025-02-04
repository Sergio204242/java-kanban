import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int id = 1;

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void clearTasks() {
        tasks.clear();
    }

    public void clearEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void clearSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            changeStatusOfEpic(epic);
        }
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public Subtask getSubtasks(int id) {
        return subtasks.get(id);
    }

    public void addTask(Task task) {
        task.setId(id);
        tasks.put(id, task);
        id += 1;
    }

    public void addEpic(Epic epic) {
        epics.put(id, epic);
        epic.setId(id);
        id += 1;
    }

    public void addSubTask(Subtask subtask) {
        if (epics.get(subtask.getIdOfEpic()) != null) {
            subtask.setId(id);
            subtasks.put(id, subtask);
            epics.get(subtask.getIdOfEpic()).addSubTask(subtask);
            changeStatusOfEpic(epics.get(subtask.getIdOfEpic()));
            id += 1;
        }
    }

    private void changeStatusOfEpic(Epic epic) {
        boolean statusNew = true;
        boolean statusDone = true;
        for (Subtask subtask : epic.getSubtasks()) {
            if ((subtask.getStatus() == Status.NEW && statusNew) || epic.getSubtasks().isEmpty()) {
                epic.setStatus(Status.NEW);
                statusDone = false;
            } else if (subtask.getStatus() == Status.DONE && statusDone) {
                epic.setStatus(Status.DONE);
                statusNew = false;
            } else {
                epic.setStatus(Status.IN_PROGRESS);
                statusNew = false;
                statusDone = false;
            }
        }
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.get(epic.getId()).setName(epic.getName());
            epics.get(epic.getId()).setDescription(epic.getDescription());
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId()) && (subtask.getIdOfEpic() == subtasks.get(subtask.getId()).getIdOfEpic())) {
            epics.get(subtask.getIdOfEpic()).deleteSubtask(subtasks.get(subtask.getId()));
            epics.get(subtask.getIdOfEpic()).addSubTask(subtask);
            changeStatusOfEpic(epics.get(subtask.getIdOfEpic()));
            subtasks.put(subtask.getId(), subtask);
        }
    }

    public ArrayList<Subtask> getSubtasksByEpic(int id) {
        if (epics.get(id) != null) {
            return epics.get(id).getSubtasks();
        }
        return new ArrayList<>();
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeEpicById(int id) {
        if (epics.get(id) != null) {
            for (Subtask subtask : epics.get(id).getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }
    }

    public void removeSubtaskById(int id) {
        if (subtasks.get(id) != null) {
            epics.get(subtasks.get(id).getIdOfEpic()).deleteSubtask(subtasks.get(id));
            changeStatusOfEpic(epics.get(subtasks.get(id).getIdOfEpic()));
            subtasks.remove(id);
        }
    }
}