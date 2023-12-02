package libmansys;

import libmansys.author.Author;
import libmansys.author.AuthorException;
import libmansys.libItem.Book;
import libmansys.libItem.LibItem;
import libmansys.libItem.LibItemException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import java.util.List;

class AuthorTest
{
    private Author author;
    private List<LibItem> authoredItems;

    @Test
    void testAddBook() throws AuthorException, LibItemException {
        givenAuthorValidArguments();
        Book book = new Book("Title1", true, "Author", "1111111111111", "11111111-1111-1111-1111-111111111111");
        author.addItem(book);
        assertTrue(author.getAuthoredItems().contains(book));
    }
    @Test
    void testRemoveBook() throws AuthorException, LibItemException {
        givenAuthorValidArguments();
        Book book = new Book("Title1", true, "Author", "1111111111111", "11111111-1111-1111-1111-111111111111");
        author.addItem(book);
        assertTrue(author.getAuthoredItems().contains(book));
    }

    private void givenAuthorValidArguments() throws AuthorException, LibItemException {
        authoredItems = new ArrayList<>();
        authoredItems.add(new Book("Title", true, "Author", "0000000000000", "00000000-0000-0000-0000-000000000000"));
        author = new Author("Author", authoredItems);
    }
}