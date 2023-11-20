package libmansys;

import java.util.ArrayList;
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
    private List<Item> listOfBorrowedAssets;


    //Constructor
    public LibUser(String name, String id, List<Item> listOfBorrowedAssets) {
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

    public List<Item> getListOfBorrowedAssets() {
        return listOfBorrowedAssets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setListOfBorrowedAssets(List<Item> listOfBorrowedAssets) {
        this.listOfBorrowedAssets = listOfBorrowedAssets;
    }

    //Borrowing functionality
    public void borrowItem(Item item) {
        // Implementation
    }

    //Return an item
    public void returnItem(Item item) {
        // Implementation
    }

    //Display borrowed items
    public void displayBorrowedItems() {
        // Implementation
    }

    //Check if an item is borrowed
    public boolean hasBorrowed(Item item) {
        // Implementation
        return false;
    }

    @Override
    public String toString() {
        return "libmansys.LibUser{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", listOfBorrowedAssets=" + listOfBorrowedAssets +
                '}';
    }
}

class Item {
    String itemName;
    String itemType;

    public Item(String itemName, String itemType) {
        this.itemName = itemName;
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                '}';
    }
}

