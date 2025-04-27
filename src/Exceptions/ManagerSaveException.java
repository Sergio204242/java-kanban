package exceptions;

public class ManagerSaveException extends Throwable {
    private String message;

    public ManagerSaveException(String message) {
        this.message = message;
    }
}
