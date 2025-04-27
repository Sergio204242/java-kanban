package tasks;

import java.util.Objects;

public class Subtask extends Task {
    private final int idOfEpic;
    Type type = Type.SUBTASK;

    public Subtask(int id, String name, String description, Status status, int idOfEpic) {
        super(id, name, description, status);
        this.idOfEpic = idOfEpic;
    }

    public Integer getIdOfEpic() {
        return idOfEpic;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + description + "," + status + "," + type + "," + idOfEpic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtask subtask = (Subtask) o;
        return Objects.equals(id, subtask.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
