package libmansys;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Inits for testing
        Item item = new Item("The Little Prince", "Book");
        List<Item> listOfBorrowedAssets = new ArrayList<>();
        listOfBorrowedAssets.add(item);

        LibUser libUser = new LibUser("Alice John","12345", (List<Item>) listOfBorrowedAssets);

        System.out.println(libUser.toString());

    }
}
