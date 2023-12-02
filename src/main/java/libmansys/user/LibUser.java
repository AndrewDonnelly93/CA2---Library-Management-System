package libmansys.user;

import libmansys.libItem.LibItem;

import java.util.List;

public class LibUser {
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
        if (id.length() != 5)
            throw new LibUserException("\n\tUser ID should have 5 characters");
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
        if (id.length() != 5)
            throw new LibUserException("\n\tUser ID should have 5 characters");
        else
            this.id = id;
    }

    public void setListOfBorrowedAssets(List<LibItem> listOfBorrowedAssets) {
        this.listOfBorrowedAssets = listOfBorrowedAssets;
    }

    //Borrowing functionality
    public void borrowItem(LibItem item) {
        listOfBorrowedAssets.add(item);
    }

    //Return an item
    public void returnItem(LibItem item) {
        if (hasBorrowed(item))
            listOfBorrowedAssets.remove(item);
        else
            System.out.println(item.getTitle() + " was not borrowed by this user");
    }

    //Check if an item is borrowed
    public boolean hasBorrowed(LibItem item) {
        return listOfBorrowedAssets.contains(item);
    }

    public void printUserDetails() {
        System.out.println("libmansys.user.LibUser{" +
                "name='" + this.getName() + '\'' +
                ", id='" + this.getId()+"}");
        System.out.println("\nList of borrowed assets\n");
        listOfBorrowedAssets.forEach(LibItem::printItemDetails);
    }
}
