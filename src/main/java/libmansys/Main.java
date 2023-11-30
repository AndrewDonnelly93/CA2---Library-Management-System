package libmansys;

import libmansys.csv.LibItemCsvHandler;
import libmansys.csv.UsersCsvHandler;
import libmansys.libItem.Book;
import libmansys.libItem.LibItem;
import libmansys.libItem.Media;
import libmansys.libItem.Thesis;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException, IOException {

        //Inits for testing
        Book book1 = new Book(
                "The Little Prince", true,
                "Antoine de Saint-Exupéry", "978-0156012195", "6c60ac1f-a82d-4c99-8590-8e19099d3b04"
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
                "This research developed a hybrid heating process which can sinter yttria" +
                        "zirconia ceramics to nearly 100% of their theoretical density in a short time." +
                        "Following optimisation of the process, a detailed comparison of the" +
                        "properties and microstructures of conventionally sintered and microwave" +
                        "sintered samples of 3 mol% and 8 mol% yttria zirconia was performed." +
                        "Identical thermal profiles were used for both types of heating. For both" +
                        "materials, microwave heating was found to enhance the densification" +
                        "processes which occur during constant rate heating.",
                new SimpleDateFormat("dd/MM/yyyy").parse("14/05/2023"),
                "17013fa6-1d7a-45f8-ae4c-292fbeb2b6db");


        List<LibItem> listOfBorrowedAssets = new ArrayList<>();

        listOfBorrowedAssets.add(book1);
        listOfBorrowedAssets.add(book2);
        listOfBorrowedAssets.add(cd1);
        listOfBorrowedAssets.add(dvd1);
        listOfBorrowedAssets.add(thesis1);

        LibUser libUser;
        LibUser libUser2;

        try {
            libUser = new LibUser("Alice John", "12345", listOfBorrowedAssets);
            libUser2 = new LibUser("James Barry", "32525", listOfBorrowedAssets);
            libUser.printUserDetails();
        } catch (LibUserException e) {
            throw new RuntimeException(e);
        }

        // Exporting the list of borrowed assets into CSV files
        ArrayList<String> booksCsvRecords = new ArrayList<>();
        ArrayList<String> mediaCsvRecords = new ArrayList<>();
        ArrayList<String> thesesCsvRecords = new ArrayList<>();

        for (var item : listOfBorrowedAssets) {
            if (item instanceof Book) {
                booksCsvRecords.add(item.printItemToCSV().toString());
            } else if (item instanceof Media) {
                mediaCsvRecords.add(item.printItemToCSV().toString());
            } else if (item instanceof Thesis) {
                thesesCsvRecords.add(item.printItemToCSV().toString());
            }
        }

        StringWriter booksCsvHeader = new StringWriter().append("Title,Availability,Author,ISBN,ID,\n");
        String booksCsvFile = "C:/Users/Leo/Desktop/ATU/Software Development/CA2---Library-Management-System/src/test/csv/books.csv";
        LibItemCsvHandler csvHandlerBooks = new LibItemCsvHandler(booksCsvFile, booksCsvHeader, booksCsvRecords);
        csvHandlerBooks.writeToFile();

        StringWriter mediaCsvHeader = new StringWriter().append("Title,Availability,Producer,Director,Duration,ID,\n");
        String mediaCsvFile = "C:/Users/Leo/Desktop/ATU/Software Development/CA2---Library-Management-System/src/test/csv/media.csv";
        LibItemCsvHandler csvHandlerMedia = new LibItemCsvHandler(mediaCsvFile, mediaCsvHeader, mediaCsvRecords);
        csvHandlerMedia.writeToFile();

        StringWriter thesesCsvHeader = new StringWriter().append("Title,Availability,Topic,DatePublished,ID");
        String thesesCsvFile = "C:/Users/Leo/Desktop/ATU/Software Development/CA2---Library-Management-System/src/test/csv/theses.csv";
        LibItemCsvHandler csvHandlerTheses = new LibItemCsvHandler(thesesCsvFile, thesesCsvHeader, thesesCsvRecords);
        csvHandlerTheses.writeToFile();

        // Reading newly generated CSV files
        System.out.println("\nFiles generated:");
        csvHandlerBooks.parseCsvFile(booksCsvFile,  "Books");
        csvHandlerMedia.parseCsvFile(mediaCsvFile, "Media");
        csvHandlerTheses.parseCsvFile(thesesCsvFile, "Theses");
        System.out.println("\n");

        // Printing the list of library users to a CSV file
        String usersCsvFile = "C:/Users/andre/IdeaProjects/CA2---Library-Management-System/src/test/csv/users.csv";
        ArrayList<LibUser> usersList = new ArrayList<>();
        usersList.add(libUser);
        usersList.add(libUser2);
        UsersCsvHandler usersCsvHandler = new UsersCsvHandler(usersCsvFile, usersList);
        usersCsvHandler.writeUsersList();

        //USER MENU

        //Main menu loop
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Library Management System! Please choose an option:");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Catalogue");
            System.out.println("3. Loan System");
            System.out.println("4. Exit");

            String input = scanner.nextLine();
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    handleUsers();
                    break;
                case 2:
                    handleCatalogue();
                    break;
                case 3:
                    handleLoans();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //Methods for handling different services (Exam, Student, Exam Results)
    //Each method contains a submenu for specific actions related to that service
    private static void handleUsers() {
        System.out.println("Manage Users:");
        System.out.println("1. Export list of all users");
        System.out.println("2. Search for user");
        System.out.println("3. Add user");
        System.out.println("4. Back to Main Menu");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                //TODO csvExport(libUserService);
                break;
            case "2":
                //TODO searchLibUser();
                break;
            case "3":
                //TODO addUser();
                break;
            case "4":
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void handleCatalogue () {
        System.out.println("Manage Catalogue:");
        System.out.println("1. Update Catalogue");
        System.out.println("2. View Catalogue");
        System.out.println("3. Back to Main Menu");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                updateCatalogue();
                break;
            case "2":
                viewCatalogue();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void updateCatalogue () {
        System.out.println("Update Catalogue:");
        System.out.println("1. Update Author");
        System.out.println("2. Update LibItem");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                updateAuthor();
                break;
            case "2":
                updateLibItem();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void viewCatalogue () {
        System.out.println("View Catalogue:");
        System.out.println("1. View Authors");
        System.out.println("2. View Library Items");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                viewAuthors();
                break;
            case "2":
                viewLibItems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void updateAuthor () {
        // TODO Code to update an author
    }


    private static void updateLibItem () {
        // TODO Code to update a library item
    }

    private static void viewAuthors () {
        System.out.println("Author Catalogue:");
        System.out.println("1. Export list of all Authors (Note: Valid for Books and Theses only)");
        System.out.println("2. Search for an Author");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                //TODO authorExport();
                break;
            case "2":
                //TODO searchAuthor();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void viewLibItems () {
        System.out.println("Library Catalogue:");
        System.out.println("1. Export list of all items (includes books, theses and media)");
        System.out.println("2. Export list of available items only");
        System.out.println("3. Search for an item");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                //TODO allItemsExport();
                break;
            case "2":
                //TODO availItemsExport();
                break;
            case "3":
                //TODO searchItems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void handleLoans () {
        System.out.println("Loan System:");
        System.out.println("a. Borrow Book");
        System.out.println("b. Return Book");
        System.out.println("c. Back to Main Menu");

        String choice = scanner.nextLine();

        switch (choice) {
            case "a":
                //TODO borrowBook();
                break;
            case "b":
                //TODO returnBook();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}


