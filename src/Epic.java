import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Subtask> subtasks = new ArrayList<>();
    protected boolean statusNew = true;
    protected boolean statusDone = true;
    private Status status;

    public Epic(String name, String description) {
        super(name, description);
        status = Status.NEW;
    }

    public void addSubTask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public final void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + status +
                ", id=" + getId() +
                ", subtasks=" + getSubtasks() +
                '}';
    }

    public void deleteSubtask(int id){
        subtasks.remove(id);
    }

    public void clearSubtasks(){
        subtasks.clear();
    }
}