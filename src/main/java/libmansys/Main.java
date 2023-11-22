package libmansys;

import libmansys.libItem.Book;
import libmansys.libItem.LibItem;
import libmansys.libItem.Media;
import libmansys.libItem.Thesis;

import java.text.ParseException;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {

        //Inits for testing
        Book book1 = new Book(
                "The Little Prince", true,
                "Antoine de Saint-Exupéry", " 978-0156012195","6c60ac1f-a82d-4c99-8590-8e19099d3b04"
        );
        Book book2 = new Book(
                "Harry Potter and the Chamber of Secrets", false,
                "J. K. Rowling", "9788183221344",
                "f5bc4b45-b297-44c9-a4e2-74c3d04d8cd4"
        );
        Media cd1 = new Media("Pale Green Ghosts", true, "Bella Union",
                "Kate Le Bon", Duration.ofHours(1).plusMinutes(30),
                "e37db276-a850-477f-9737-91e47ef83a84");
        Media dvd1 = new Media("Home Alone", false,
        "John Hughes", "Chris Columbus",
        Duration.ofHours(1).plusMinutes(43),
        "83eb02f9-e1c4-4498-adce-cbe8911d4011");
        Thesis thesis1 = new Thesis("Zirconia ceramics", true, "Jack Russell",
                "Heating process which can sinter yttria zirconia ceramics",
                "This research developed a hybrid heating process which can sinter yttria\n" +
                        "zirconia ceramics to nearly 100% of their theoretical density in a short time.\n" +
                        "Following optimisation of the process, a detailed comparison of the\n" +
                        "properties and microstructures of conventionally sintered and microwave\n" +
                        "sintered samples of 3 mol% and 8 mol% yttria zirconia was performed.\n" +
                        "Identical thermal profiles were used for both types of heating. For both\n" +
                        "materials, microwave heating was found to enhance the densification\n" +
                        "processes which occur during constant rate heating.",
                new SimpleDateFormat("dd/MM/yyyy").parse("14/05/2023"),
                "17013fa6-1d7a-45f8-ae4c-292fbeb2b6db");


        List<LibItem> listOfBorrowedAssets = new ArrayList<>();

        listOfBorrowedAssets.add(book1);
        listOfBorrowedAssets.add(book2);
        listOfBorrowedAssets.add(cd1);
        listOfBorrowedAssets.add(dvd1);
        listOfBorrowedAssets.add(thesis1);

        LibUser libUser = new LibUser("Alice John","12345", listOfBorrowedAssets);

        libUser.printUserDetails();
    }
}
