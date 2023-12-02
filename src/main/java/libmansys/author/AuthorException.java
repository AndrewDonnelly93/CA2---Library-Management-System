package libmansys.author;

public class AuthorException extends Exception
{
    private final String message;
    public AuthorException() {
        message = "\n\tThere is an error in Author Class";
    }

    public AuthorException (String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "\n\t Exception: " + message;
    }

}
