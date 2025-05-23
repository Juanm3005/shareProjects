package customExceptions;

public class InvalidFormatVersionException extends IllegalArgumentException {
    public InvalidFormatVersionException() {
        super("Invalid format version, use the format: X.X.X");
    }

}
