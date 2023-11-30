package libmansys.libItem;

import java.io.StringWriter;

// Represents books and audiobooks
public class Book extends LibItem {
    private String author;
    private String ISBN;

    public Book(String title, boolean availabilityStatus, String author, String ISBN, String id) {
        super(title, availabilityStatus, id, new StringWriter().append("Title,Availability,Author,ISBN,ID,\n"));
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

    @Override
    public String printItemToCSV() {
        StringBuilder book = new StringBuilder();
        book.append(this.getTitle()).append("¬");
        book.append(this.getAvailabilityStatus()).append("¬");
        book.append(this.getAuthor()).append("¬");
        book.append(this.getISBN()).append("¬");
        book.append(this.getId()).append("\n");
        return book.toString();
    }

}
