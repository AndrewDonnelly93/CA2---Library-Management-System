package libmansys;

import libmansys.libItem.Book;
import libmansys.libItem.LibItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibUserTest
{
    LibUser libUser;
    List<LibItem> borrowedItems;

    private void givenUserValidArguments() throws LibUserException {
        borrowedItems = new ArrayList<>();
        borrowedItems.add(new Book("Title", true, "Author", "0000000000000", "00000000-0000-0000-0000-000000000000"));
        libUser = new LibUser("User", "00000", borrowedItems);
    }
}