package libmansys.libItem;
// Represents books and audiobooks
public class Book extends LibItem {
    private String author;
    private String ISBN;

    public Book(String title, boolean availabilityStatus, String author, String ISBN, String id) throws LibItemException {
        super(title, availabilityStatus, id, new StringBuilder().append("Title,Availability Status,Author,ISBN,ID\n"));
        this.itemType = LibItemType.BOOK;
        this.author = author;
        if (ISBN.length() != 13)
            throw new LibItemException("\n\tISBN should have 13 digits");
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
            throw new LibItemException("\n\tISBN should have 13 digits");
        else
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

    @Override
    public StringBuilder printItemToCSV() {
        StringBuilder book = new StringBuilder();
        book.append(this.getTitle()).append(",");
        book.append(this.getAvailabilityStatus()).append(",");
        book.append(this.getAuthor()).append(",");
        book.append(this.getISBN()).append(",");
        book.append(this.getId()).append("\n");
        return book;
    }

}
