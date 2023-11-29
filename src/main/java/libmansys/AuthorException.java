package libmansys;

public class AuthorException extends Exception
{
    private String message;
    public AuthorException() {
        message = "\n\tThere is an error in Student Class";
    }

    public AuthorException (String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "\n\t Exception: " + message;
    }

}
