package customExceptions;

public class InvalidSerialNumberException extends IllegalArgumentException {

    public InvalidSerialNumberException() {
        super("enter a valid serial number (only numbers and positive)");

    }
}
