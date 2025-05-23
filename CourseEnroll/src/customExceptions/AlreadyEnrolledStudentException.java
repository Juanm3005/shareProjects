package customExceptions;

public class AlreadyEnrolledStudentException extends Exception {

    public AlreadyEnrolledStudentException() {
        super("The student is already enrolled");
    }
}
