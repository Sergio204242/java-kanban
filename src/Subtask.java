public class Subtask extends Task {
    private final String nameOfEpic;

    public Subtask(String name, String description, Status status, Epic epic) {
        super(name, description, status);
        nameOfEpic = epic.getName();
    }

    public String getNameOfEpic() {
        return nameOfEpic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "nameOfEpic=" + getNameOfEpic() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", id=" + getId() +
                '}';
    }
}
