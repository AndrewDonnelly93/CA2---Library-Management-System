package libmansys;

public class LibUserException extends Exception
{
    private String message;
    public LibUserException() {
        message = "\n\tThere is an error in LibUser Class";
    }

    public LibUserException (String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "\n\t Exception: " + message;
    }
}
