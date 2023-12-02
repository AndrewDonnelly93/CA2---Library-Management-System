package libmansys;

import libmansys.libItem.*;
import libmansys.search.Search;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    private ArrayList<LibItem> itemsList;
    private Book book;
    private Media media;
    private Thesis thesis;

    @Test
    void testLinearSearchByAttribute() throws ParseException, LibItemException {
        addingItems();
        assertEquals(media, Search.linearSearchByAttribute(itemsList, LibItem::getTitle, "Media Title"));
    }

    private void addingItems() throws LibItemException {
        itemsList = new ArrayList<>();
        book = new Book("Book Title", true, "Author", "0000000000000",
                "00000000-0000-0000-0000-000000000000");
        media = new Media("Media Title", true, "Producer", "Director",
                Duration.ofHours(1).plusMinutes(30), "00000000-0000-0000-0000-000000000000");
        thesis = new Thesis("Thesis Title", true, "Author", "Topic",
                "Abstract Summary",  LocalDate.parse("14/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "00000000-0000-0000-0000-000000000000");
        itemsList.add(book);
        itemsList.add(media);
        itemsList.add(thesis);
    }
}