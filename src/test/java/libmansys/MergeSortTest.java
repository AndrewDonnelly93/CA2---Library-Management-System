package libmansys;

import libmansys.libItem.*;
import libmansys.sort.MergeSort;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    private ArrayList<LibItem> itemsList;
    private Book book;
    private Book book1;
    private Media media;
    private Thesis thesis;

    @Test
    void testMergeSort() throws LibItemException, ParseException {
        addingItemsToList();
        MergeSort<LibItem> sorter = new MergeSort<>();
        Comparator<LibItem> comparator = Comparator.comparing(LibItem::getTitle);

        sorter.sort(itemsList, comparator);

        ArrayList<LibItem> expectedList = new ArrayList<>();
        expectedList.add(book1);
        expectedList.add(book);
        expectedList.add(media);
        expectedList.add(thesis);

        assertEquals(expectedList, itemsList);
    }

    private void addingItemsToList() throws LibItemException {
        itemsList = new ArrayList<>();
        book = new Book("Book Title", true, "Author", "0000000000000",
                "00000000-0000-0000-0000-000000000000");
        book1 = new Book("Book Title", true, "Author", "0000000000000",
                "00000000-0000-0000-0000-000000000000");
        media = new Media("Media Title", true, "Producer", "Director",
                Duration.ofHours(1).plusMinutes(30), "00000000-0000-0000-0000-000000000000");
        thesis = new Thesis("Thesis Title", true, "Author", "Topic",
                "Abstract Summary", LocalDate.parse("14/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "00000000-0000-0000-0000-000000000000");
        itemsList.add(media);
        itemsList.add(book1);
        itemsList.add(thesis);
        itemsList.add(book);
    }

}