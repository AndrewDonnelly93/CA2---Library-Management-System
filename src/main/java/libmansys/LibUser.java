package libmansys;

import libmansys.libItem.LibItem;

import java.util.List;

public class LibUser {

    /*LibraryUser Class
    Attributes:
    name (string)
    ID (string)
    listOfBorrowedAssets (List<Book/AudioBook/Theses/Dissertation/CD/DVD>)
    Methods:
    getName()
    getID()
    getListOfBorrowedAssets()*/

    //Item Placeholder


    //Attributes
    private String name;
    private String id;
    private List<LibItem> listOfBorrowedAssets;


    //Constructor
    public LibUser(String name, String id, List<LibItem> listOfBorrowedAssets) {
        this.name = name;
        this.id = id;
        this.listOfBorrowedAssets = listOfBorrowedAssets;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<LibItem> getListOfBorrowedAssets() {
        return listOfBorrowedAssets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setListOfBorrowedAssets(List<LibItem> listOfBorrowedAssets) {
        this.listOfBorrowedAssets = listOfBorrowedAssets;
    }

    //Borrowing functionality
    public void borrowItem(LibItem item) {
        // Implementation
    }

    //Return an item
    public void returnItem(LibItem item) {
        // Implementation
    }

    //Display borrowed items
    public void displayBorrowedItems() {
        // Implementation
    }

    //Check if an item is borrowed
    public boolean hasBorrowed(LibItem item) {
        // Implementation
        return false;
    }

    public void printUserDetails() {
        System.out.println("libmansys.LibUser{" +
                "name='" + this.getName() + '\'' +
                ", id='" + this.getId());
        System.out.println("\nList of borrowed assets\n");
        for (var item: this.getListOfBorrowedAssets()) {
            item.printItemDetails();
        }
    }
}
