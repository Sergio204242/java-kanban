import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static final HashMap<Integer, Task> tasks = new HashMap<>();
    private static final HashMap<Integer, Epic> epics = new HashMap<>();
    private static final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private static int idTask = 0;
    private static int idEpic = 0;
    private static int idSubtask = 0;


    public static HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public static HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public static HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public static void clearTasks() {
        tasks.clear();
    }

    public static void clearEpics() {
        epics.clear();
        subtasks.clear();
    }

    public static void clearSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            epic.setStatus(Status.NEW);
        }
    }

    public static Task getTask(int id) {
        return tasks.get(id);
    }

    public static Epic getEpic(int id) {
        return epics.get(id);
    }

    public static Subtask getSubtasks(int id) {
        return subtasks.get(id);
    }

    public static void addTask(Task task) {
        for (Task m : tasks.values()) {
            if (m.equals(task)) {
                tasks.put(m.getId(), task);
                task.setId(m.getId());
            }
        }
        if (!tasks.containsValue(task)) {
            tasks.put(idTask, task);
            task.setId(idTask);
            idTask += 1;
        }
    }

    public static void addEpic(Epic epic) {
        for (Epic m : epics.values()) {
            if (m.equals(epic)) {
                epics.put(m.getId(), epic);
                epic.setId(m.getId());
            }
        }
        if (!epics.containsValue(epic)) {
            epics.put(idEpic, epic);
            epic.setId(idEpic);
            idEpic += 1;
        }
    }

    public static void addSubTask(Subtask subtask) {
        for (Epic epic : epics.values()) {
            if (subtask.getNameOfEpic().equals(epic.getName())) {
                if (!epic.getSubtasks().contains(subtask)) {
                    epic.addSubTask(subtask);
                }
                changeStatusOfEpic(subtask, epic);
            }
        }
        for (Subtask m : subtasks.values()) {
            if (m.equals(subtask)) {
                subtasks.put(m.getId(), subtask);
                subtask.setId(m.getId());
            }
        }
        if (!subtasks.containsValue(subtask)) {
            subtasks.put(idSubtask, subtask);
            subtask.setId(idSubtask);
            idSubtask += 1;
        }
    }

    private static void changeStatusOfEpic(Subtask subtask, Epic epic) {
        if ((subtask.getStatus() == Status.NEW && epic.statusNew) || epic.getSubtasks().isEmpty()) {
            epic.setStatus(Status.NEW);
            epic.statusDone = false;
        } else if (subtask.getStatus() == Status.DONE && epic.statusDone) {
            epic.setStatus(Status.DONE);
            epic.statusNew = false;
        } else {
            epic.setStatus(Status.IN_PROGRESS);
            epic.statusDone = false;
            epic.statusNew = false;
        }
    }

    public static void updateTask(Task task, int id) {
        tasks.put(id, task);
    }

    public static void updateEpic(Epic epic, int id) {
        for (Subtask subtask : epic.getSubtasks()) {
            for (Subtask subtask1 : subtasks.values()) {
                if (subtask.equals(subtask1)) {
                    subtasks.remove(subtask1);
                }
            }
        }
        epics.put(id, epic);
    }

    public static void updateSubtask(Subtask subtask, int id) {
        subtasks.put(id, subtask);
        for (Epic epic : epics.values()) {
            if (subtask.getNameOfEpic().equals(epic.getName())) {
                epic.getSubtasks().remove(id);
                epic.getSubtasks().add(id, subtask);
                epic.statusNew = true;
                epic.statusDone = true;
                for (Subtask subtask1 : epic.getSubtasks()) {
                    changeStatusOfEpic(subtask1, epic);
                }
            }
        }
    }

    public static ArrayList<Subtask> getSubtasksByEpic(Epic epic) {
        return epic.getSubtasks();
    }

    public static void removeTaskById(int id) {
        tasks.remove(id);
    }

    public static void removeEpicById(int id) {
        for (Subtask subtask : epics.get(id).getSubtasks()) {
            for (Subtask subtask1 : subtasks.values()) {
                if (subtask.equals(subtask1)) {
                    subtasks.remove(subtask1);
                }
            }
        }
        epics.remove(id);
    }

    public static void removeSubtaskById(int id) {
        for (Epic epic : epics.values()) {
            if (subtasks.get(id).getNameOfEpic().equals(epic.getName())) {
                epic.deleteSubtask(id);
                changeStatusOfEpic(subtasks.get(id), epic);
            }
        }
        subtasks.remove(id);
    }
}