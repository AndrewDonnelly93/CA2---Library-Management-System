package libmansys;

import libmansys.libItem.Book;
import libmansys.libItem.LibItem;
import libmansys.libItem.LibItemException;
import libmansys.user.LibUser;
import libmansys.user.LibUserException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibUserTest {
    LibUser libUser;
    List<LibItem> borrowedItems;

    @Test
    void testBorrowItem() throws LibUserException, NoSuchFieldException, IllegalAccessException, LibItemException {
        givenUserValidArguments();
        Book book = new Book("Title1", true, "Author", "1111111111111", "11111111-1111-1111-1111-111111111111");
        libUser.borrowItem(book);
        assertTrue(libUser.getListOfBorrowedAssets().contains(book));
    }

    @Test
    void testReturnItem() throws LibUserException, NoSuchFieldException, IllegalAccessException, LibItemException {
        givenUserValidArguments();
        Book book = new Book("Title1", true, "Author", "1111111111111", "11111111-1111-1111-1111-111111111111");
        borrowedItems.add(book);
        assertTrue(libUser.getListOfBorrowedAssets().contains(book));
        libUser.returnItem(book);
        assertFalse(libUser.getListOfBorrowedAssets().contains(book));
    }

    private void givenUserValidArguments() throws LibUserException, LibItemException {
        borrowedItems = new ArrayList<>();
        borrowedItems.add(new Book("Title", true, "Author", "0000000000000", "00000000-0000-0000-0000-000000000000"));
        libUser = new LibUser("User", "00000", (LinkedList<LibItem>) borrowedItems);
    }
}