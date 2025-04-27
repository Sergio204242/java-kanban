package manager;

import exceptions.ManagerSaveException;
import tasks.*;

import java.io.*;
import java.util.ArrayList;


public class FileBackedTaskManager extends InMemoryTaskManager implements TaskManager {
    private final File file;
    private final InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    private void save() throws ManagerSaveException {
        try (Writer writer = new FileWriter(file)) {
            ArrayList<String> allTasks = new ArrayList<>();
            ArrayList<Task> tasks = super.getTasks();
            for (Task task : tasks) {
                allTasks.add(task.toString());
            }
            ArrayList<Epic> epics = super.getEpics();
            for (Epic epic : epics) {
                allTasks.add(epic.toString());
            }
            ArrayList<Subtask> subtasks = super.getSubtasks();
            for (Subtask subtask : subtasks) {
                allTasks.add(subtask.toString());
            }
            writer.write("id,name,description,status,type,epic\n");
            for (String task : allTasks) {
                writer.write(task + "\n");
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка во время записи файла");
        }
    }

    private static Task fromString(String value) {
        String[] fields = value.split(",");
        if (Type.valueOf(fields[4]) == Type.TASK) {
            return new Task(Integer.parseInt(fields[0]), fields[1], fields[2], Status.valueOf(fields[3]));
        } else if (Type.valueOf(fields[4]) == Type.EPIC) {
            return new Epic(Integer.parseInt(fields[0]), fields[1], fields[2]);
        } else {
            return new Subtask(Integer.parseInt(fields[0]), fields[1], fields[2], Status.valueOf(fields[3]), Integer.parseInt(fields[5]));
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) throws ManagerSaveException, IOException {
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(file);
        Reader reader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(reader)) {
            String firstLine = br.readLine();
            while (br.ready()) {
                String str = br.readLine();
                String[] fields = str.split(",");
                if (Type.valueOf(fields[4]) == Type.TASK) {
                    fileBackedTaskManager.addTask(fromString(str));
                } else if (Type.valueOf(fields[4]) == Type.EPIC) {
                    fileBackedTaskManager.addEpic((Epic) fromString(str));
                } else {
                    fileBackedTaskManager.addSubTask((Subtask) fromString(str));
                }
            }
        } catch (IOException exception) {
            throw new ManagerSaveException("Ошибка во время чтения файла");
        }
        reader.close();
        return fileBackedTaskManager;
    }

    @Override
    public void clearTasks() throws IOException, ManagerSaveException {
        super.clearTasks();
        save();
    }

    @Override
    public void clearEpics() throws IOException, ManagerSaveException {
        super.clearEpics();
        save();
    }

    @Override
    public void clearSubtasks() throws IOException, ManagerSaveException {
        super.clearSubtasks();
        save();
    }

    @Override
    public void addTask(Task task) throws IOException, ManagerSaveException {
        super.addTask(task);
        save();
    }

    @Override
    public void addEpic(Epic epic) throws IOException, ManagerSaveException {
        super.addEpic(epic);
        save();
    }

    @Override
    public void addSubTask(Subtask subtask) throws IOException, ManagerSaveException {
        super.addSubTask(subtask);
        save();
    }

    @Override
    public void updateTask(Task task) throws IOException, ManagerSaveException {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) throws IOException, ManagerSaveException {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) throws IOException, ManagerSaveException {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void removeTaskById(int id) throws IOException, ManagerSaveException {
        super.removeTaskById(id);
        save();
    }

    @Override
    public void removeEpicById(int id) throws IOException, ManagerSaveException {
        super.removeEpicById(id);
        save();
    }

    @Override
    public void removeSubtaskById(int id) throws IOException, ManagerSaveException {
        super.removeSubtaskById(id);
        save();
    }

}


