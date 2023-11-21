package libmansys.libItem;
// Represents books and audiobooks
public class Book extends LibItem {
    private String author;
    private String ISBN;

    public Book(String title, boolean availabilityStatus, String author, String ISBN) {
        super(title, availabilityStatus);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    void borrowItem() {

    }

    @Override
    void returnItem() {

    }
}
