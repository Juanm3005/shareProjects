package customExceptions;

public class GradesCancelationException extends Exception {
    public GradesCancelationException(){
        super("course cannot be cancelled, student has more than halfk of their grades registered");
    }
    
}
