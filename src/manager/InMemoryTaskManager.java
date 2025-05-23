package manager;

import exceptions.ManagerSaveException;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private int id = 1;
    HistoryManager historyManager = new InMemoryHistoryManager();

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void clearTasks() throws IOException, ManagerSaveException {
        tasks.clear();
    }

    @Override
    public void clearEpics() throws IOException, ManagerSaveException {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void clearSubtasks() throws IOException, ManagerSaveException {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            changeStatusOfEpic(epic);
        }
    }

    @Override
    public Task getTask(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtasks(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public void addTask(Task task) throws IOException, ManagerSaveException {
        task.setId(id);
        tasks.put(id, task);
        id += 1;
    }

    @Override
    public void addEpic(Epic epic) throws IOException, ManagerSaveException {
        epics.put(id, epic);
        epic.setId(id);
        id += 1;
    }

    private void changeStatusOfEpic(Epic epic) {
        boolean statusNew = true;
        boolean statusDone = true;
        for (Integer id : epic.getSubtasks()) {
            if ((subtasks.get(id).getStatus() == Status.NEW && statusNew) || epic.getSubtasks().isEmpty()) {
                epic.setStatus(Status.NEW);
                statusDone = false;
            } else if (subtasks.get(id).getStatus() == Status.DONE && statusDone) {
                epic.setStatus(Status.DONE);
                statusNew = false;
            } else {
                epic.setStatus(Status.IN_PROGRESS);
                statusNew = false;
                statusDone = false;
            }
        }
    }

    @Override
    public void addSubTask(Subtask subtask) throws IOException, ManagerSaveException {
        if (epics.get(subtask.getIdOfEpic()) != null) {
            subtask.setId(id);
            subtasks.put(id, subtask);
            epics.get(subtask.getIdOfEpic()).addSubTask(subtask);
            changeStatusOfEpic(epics.get(subtask.getIdOfEpic()));
            id += 1;
        }
    }

    @Override
    public void updateTask(Task task) throws IOException, ManagerSaveException {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void updateEpic(Epic epic) throws IOException, ManagerSaveException {
        if (epics.containsKey(epic.getId())) {
            epics.get(epic.getId()).setName(epic.getName());
            epics.get(epic.getId()).setDescription(epic.getDescription());
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) throws IOException, ManagerSaveException {
        if (subtasks.containsKey(subtask.getId()) && (subtask.getIdOfEpic() == subtasks.get(subtask.getId()).getIdOfEpic())) {
            epics.get(subtask.getIdOfEpic()).deleteSubtask(subtasks.get(subtask.getId()));
            epics.get(subtask.getIdOfEpic()).addSubTask(subtask);
            changeStatusOfEpic(epics.get(subtask.getIdOfEpic()));
            subtasks.put(subtask.getId(), subtask);
        }
    }

    @Override
    public ArrayList<Subtask> getSubtasksByEpic(int id) {
        if (epics.get(id) != null) {
            ArrayList<Subtask> sub = new ArrayList<>();
            for (Subtask subtask : subtasks.values()) {
                for (Integer idOfSubtask : epics.get(id).getSubtasks()) {
                    if (subtask.getId() == idOfSubtask) {
                        sub.add(subtask);
                    }
                }
            }
            return sub;
        }
        return new ArrayList<>();
    }

    @Override
    public void removeTaskById(int id) throws IOException, ManagerSaveException {
        tasks.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void removeEpicById(int id) throws IOException, ManagerSaveException {
        if (epics.get(id) != null) {
            for (Integer idOfSubtask : epics.get(id).getSubtasks()) {
                subtasks.remove(idOfSubtask);
            }
            epics.remove(id);
        }
        historyManager.remove(id);
    }

    @Override
    public void removeSubtaskById(int id) throws IOException, ManagerSaveException {
        if (subtasks.get(id) != null) {
            epics.get(subtasks.get(id).getIdOfEpic()).deleteSubtask(subtasks.get(id));
            changeStatusOfEpic(epics.get(subtasks.get(id).getIdOfEpic()));
            subtasks.remove(id);
        }
        historyManager.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

}