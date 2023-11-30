package libmansys.libItem;

public class LibItemException extends Exception
{
    private String message;
    public LibItemException() {
        message = "\n\tThere is an error in LibItem Class";
    }

    public LibItemException (String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "\n\t Exception: " + message;
    }
}
