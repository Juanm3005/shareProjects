package customExceptions;

public class LateEnrollmentExeption extends Exception {

    public LateEnrollmentExeption() {
        super("2 weeks of classes have passed, you cannot register");
    }

}
