package libmansys.libItem;
// Represents books and audiobooks
public class Book extends LibItem {
    private String author;
    private String ISBN;

    public Book(String title, boolean availabilityStatus, String author, String ISBN, String id) throws LibItemException {
        super(title, availabilityStatus, id);
        this.itemType = LibItemType.BOOK;
        this.author = author;
        if (ISBN.length() != 13)
            throw new LibItemException("\n\tBook ISBN should have 13 digits");
        else
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

    public void setISBN(String ISBN) throws LibItemException {
        if (ISBN.length() != 13)
            throw new LibItemException("\n\tBook ISBN should have 13 digits");
        else
            this.ISBN = ISBN;
    }

    @Override
    public void printItemDetails() {
        System.out.println("-".repeat(50));
        System.out.println("Item type: Book Title: " + this.getTitle()
                + " Author: " + this.getAuthor() + " ISBN: " + this.getISBN());
        if (this.getAvailabilityStatus())
            System.out.println("AVAILABLE");
        else
            System.out.println("NOT AVAILABLE");
        System.out.println();
    }
}
