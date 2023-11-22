package libmansys.libItem;
// Represents books and audiobooks
public class Book extends LibItem {
    private String author;
    private String ISBN;

    public Book(String title, boolean availabilityStatus, String author, String ISBN, String id) {
        super(title, availabilityStatus, id);
        this.itemType = LibItemType.BOOK;
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
    public void borrowItem() {

    }

    @Override
    public void returnItem() {

    }

    @Override
    public void printItemDetails() {
        System.out.println("-".repeat(50));
        System.out.println("Item type: Book Title: " + this.getTitle()
                + " Author: " + this.getAuthor() + " ISBN: " + this.getISBN());
    }
}
