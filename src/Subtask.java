public class Subtask extends Task {
    private final int idOfEpic;

    public Subtask(String name, String description, int id, Status status, int idOfEpic) {
        super(name, description, id, status);
        this.idOfEpic = idOfEpic;
    }

    public Integer getIdOfEpic() {
        return idOfEpic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "idOfEpic=" + getIdOfEpic() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", id=" + getId() +
                '}';
    }
}
