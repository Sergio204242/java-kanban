package tasks;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    protected Type type = Type.EPIC;
    private final ArrayList<Integer> subtasks = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
    }

    public void addSubTask(Subtask subtask) {
        subtasks.add(subtask.getId());
    }

    public ArrayList<Integer> getSubtasks() {
        return new ArrayList<>(subtasks);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + description + "," + status + "," + type;
    }

    public void deleteSubtask(Subtask subtask) {
        subtasks.remove(subtask);
    }

    public void clearSubtasks() {
        subtasks.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epic epic = (Epic) o;
        return Objects.equals(id, epic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}