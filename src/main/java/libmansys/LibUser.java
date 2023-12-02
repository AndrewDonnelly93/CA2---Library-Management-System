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
    public LibUser(String name, String id, List<LibItem> listOfBorrowedAssets) throws LibUserException{
        if (name.length() < 2 || name.length() > 30)
            throw new LibUserException("\n\tUser name should be between 2 and 30 characters");
        else
            this.name = name;
        if (id.isEmpty() || id.length() > 10)
            throw new LibUserException("\n\tUser ID should be between 1 and 10 characters");
        else
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

    public void setName(String name) throws LibUserException {
        if (name.length() < 2 || name.length() > 30)
            throw new LibUserException("\n\tUser name should be between 2 and 30 characters");
        else
            this.name = name;
    }

    public void setId(String id) throws LibUserException{
        if (id.isEmpty() || id.length() > 10)
            throw new LibUserException("\n\tUser ID should be between 1 and 10 characters");
        else
            this.id = id;
    }

    public void setListOfBorrowedAssets(List<LibItem> listOfBorrowedAssets) {
        this.listOfBorrowedAssets = listOfBorrowedAssets;
    }

    //Borrowing functionality
    public void borrowItem(LibItem item) throws NoSuchFieldException, IllegalAccessException {
        listOfBorrowedAssets.add(item);
    }

    //Return an item
    public void returnItem(LibItem item) throws NoSuchFieldException, IllegalAccessException {
        if (hasBorrowed(item))
            listOfBorrowedAssets.remove(item);
        else
            System.out.println(item.getTitle() + " was not borrowed by this user");
    }

    //Display borrowed items
    public void displayBorrowedItems() {
        // Implementation
    }

    //Check if an item is borrowed
    public boolean hasBorrowed(LibItem item) {
        return listOfBorrowedAssets.contains(item);
    }

    public void printUserDetails() {
        System.out.println("libmansys.LibUser{" +
                "name='" + this.getName() + '\'' +
                ", id='" + this.getId()+"}");
        System.out.println("\nList of borrowed assets\n");
        listOfBorrowedAssets.forEach(LibItem::printItemDetails);
    }
}
