package libmansys;

import libmansys.libItem.Book;
import libmansys.libItem.LibItem;
import libmansys.libItem.Media;
import libmansys.libItem.Thesis;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    private ArrayList<LibItem> itemsList;
    private Book book;
    private Media media;
    private Thesis thesis;

    @Test
    void testLinearSearchByAttribute() throws ParseException {
        addingItems();
        // Replace linearSearchByStringAttribute with linearSearchByAttribute and return title with lamda method reference instead
        assertEquals(media, Search.linearSearchByAttribute(itemsList, LibItem::getTitle, "Media Title"));
    }

    private void addingItems() throws ParseException {
        itemsList = new ArrayList<>();
        book = new Book("Book Title", true, "Author", "0000000000000",
                "00000000-0000-0000-0000-000000000000");
        media = new Media("Media Title", true, "Producer", "Director",
                Duration.ofHours(1).plusMinutes(30), "00000000-0000-0000-0000-000000000000");
        thesis = new Thesis("Thesis Title", true, "Author", "Topic",
                "Abstract Summary", new SimpleDateFormat("dd/MM/yyyy").parse("14/05/2023"),
                "00000000-0000-0000-0000-000000000000");
        itemsList.add(book);
        itemsList.add(media);
        itemsList.add(thesis);
    }
}
