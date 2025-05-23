package customExceptions;

public class InvalidId extends IllegalArgumentException {

    public InvalidId() {
        super("enter a valid id");
    }

}
