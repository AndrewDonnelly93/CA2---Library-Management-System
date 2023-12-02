package libmansys;

import libmansys.csv.AuthorsCsvHandler;
import libmansys.csv.LibItemCsvHandler;
import libmansys.csv.UsersCsvHandler;
import libmansys.libItem.*;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static AuthorsCsvHandler authorsCsvHandler;
    private static ArrayList<Author> authorsList;
    private static List<LibUser> libUserList;
    private static ArrayList<LibItem> library;
    private static final String booksCsvFile = getFullPathFromRelative("src/test/csv/books.csv");
    private static final String mediaCsvFile = getFullPathFromRelative("src/test/csv/media.csv");
    private static final String thesesCsvFile = getFullPathFromRelative("src/test/csv/theses.csv");
    private static final StringWriter booksCsvHeader = new StringWriter().append("Title,Availability,Author,ISBN,ID,\n");
    private static final StringWriter mediaCsvHeader = new StringWriter().append("Title,Availability,Producer,Director,Duration,ID,\n");
    private static final StringWriter thesesCsvHeader = new StringWriter().append("Title,Availability,Topic,DatePublished,ID");

    public static void main(String[] args) throws ParseException, IOException, AuthorException, NoSuchFieldException, IllegalAccessException, LibItemException {

        // Initialising the library system
        Book book1;
        Book book2;
        Media cd1;
        Media dvd1;
        Thesis thesis1;

        try {
            //Inits for testing
            book1 = new Book(
                    "The Little Prince", true,
                    "Antoine de Saint-Exupéry", "9780156012195", "6c60ac1f-a82d-4c99-8590-8e19099d3b04"
            );
            book2 = new Book(
                    "Harry Potter and the Chamber of Secrets", true,
                    "J. K. Rowling", "9788183221344",
                    "f5bc4b45-b297-44c9-a4e2-74c3d04d8cd4"
            );
            cd1 = new Media("Pale Green Ghosts", true, "Bella Union",
                    "Kate Le Bon", Duration.ofHours(1).plusMinutes(30),
                    "e37db276-a850-477f-9737-91e47ef83a84");
            dvd1 = new Media("Home Alone", true,
                    "John Hughes", "Chris Columbus",
                    Duration.ofHours(1).plusMinutes(43),
                    "83eb02f9-e1c4-4498-adce-cbe8911d4011");
            thesis1 = new Thesis("Zirconia ceramics", true, "Jack Russell",
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
        } catch (LibItemException e) {
            throw new RuntimeException(e);
        }
        library = new ArrayList<>();
        library.add(book1);
        library.add(book2);
        library.add(cd1);
        library.add(dvd1);
        library.add(thesis1);

        //Init list of users
        libUserList = new ArrayList<>();
        try {
            libUserList.add(new LibUser("Leonila Ortin", "00002", new ArrayList<>()));
            libUserList.add(new LibUser("Andrew Donnelly", "00003", new ArrayList<>()));
            libUserList.add(new LibUser("Grace Williams", "00001", new ArrayList<>()));
        } catch (LibUserException e) {
            System.out.println(e);
        }

        // Adding a list of authors
        ArrayList<LibItem> booksByAuthor1 = new ArrayList<>();
        booksByAuthor1.add(book2);
        ArrayList<LibItem> booksByAuthor2 = new ArrayList<>();
        booksByAuthor2.add(book1);
        ArrayList<LibItem> thesesByAuthor3 = new ArrayList<>();
        thesesByAuthor3.add(thesis1);
        authorsList = new ArrayList<>();
        try {
            authorsList.add(new Author("J. K. Rowling", booksByAuthor1));
            authorsList.add(new Author("Antoine de Saint-Exupéry", booksByAuthor2));
            authorsList.add(new Author("Jack Russell", thesesByAuthor3));
        } catch (AuthorException e) {
            System.out.println(e);
        }

        String authorsCsvFile = getFullPathFromRelative("src/test/csv/authors.csv");
        authorsCsvHandler = new AuthorsCsvHandler(authorsCsvFile, authorsList);

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
                    handleUsers(libUserList);
                    break;
                case 2:
                    handleCatalogue();
                    break;
                case 3:
                    handleLoans();
                    break;
                case 4:
                    exit = true;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        }
    }

    public static String getFullPathFromRelative(String relativePath) {
        Path fullPath = Paths.get(relativePath);
        return fullPath.toString();
    }

    //Methods for handling different services (Users, Catalogue, Loans
    //Each method contains a submenu for specific actions related to that service
    private static void handleUsers(List<LibUser> libUserList) {
        System.out.println("Manage Users:");
        System.out.println("1. Export list of all users");
        System.out.println("2. Search for user");
        System.out.println("3. Add user");
        System.out.println("4. Back to Main Menu");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                userSort(libUserList);
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

    //Sort method using Lambda function
    private static void userSort(List<LibUser> libUserList) {
        System.out.println("How would you like to sort the list of users?");
        System.out.println("1. By Name");
        System.out.println("2. By ID");
        String sortChoice = scanner.nextLine();
        Comparator<LibUser> comparator;

        switch (sortChoice) {
            case "1":
                comparator = Comparator.comparing(LibUser::getName);
                break;
            case "2":
                comparator = Comparator.comparing(LibUser::getId);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to sort by Name.");
                comparator = Comparator.comparing(LibUser::getName);
                break;
        }

        // Create an instance of MergeSort for LibUser
        MergeSort<LibUser> sorter = new MergeSort<>();

        // Output the list before sorting
        System.out.println("List Before Sorting:");
        libUserList.forEach(user -> System.out.println(user.getName() + " - ID: " + user.getId()));

        // Applying the merge sort
        sorter.sort(libUserList, comparator);

        // Output the list after sorting
        System.out.println("\nSorted List:");
        libUserList.forEach(user -> System.out.println(user.getName() + " - ID: " + user.getId()));

        exportUsersToCsv(libUserList);
    }


    private static void exportUsersToCsv(List<LibUser> libUserList) {
        String usersCsvFile = getFullPathFromRelative("src/test/csv/sorted_users.csv");
        UsersCsvHandler usersCsvHandler = new UsersCsvHandler(usersCsvFile, (ArrayList<LibUser>) libUserList);
        usersCsvHandler.writeUsersList();
        System.out.println("Sorted user list exported to CSV.");
    }


    private static void handleCatalogue() throws NoSuchFieldException, IllegalAccessException, LibItemException {
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
                break;
        }
    }

    private static void updateCatalogue() throws NoSuchFieldException, IllegalAccessException, LibItemException {
        System.out.println("Update Catalogue:");
        System.out.println("1. Add Author");
        System.out.println("2. Add LibItem");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addAuthor();
                break;
            case "2":
                addLibItem();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void viewCatalogue() throws NoSuchFieldException, IllegalAccessException {
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

    private static void addAuthor() throws NoSuchFieldException, IllegalAccessException {
        String authorName;
        do {
            System.out.println("Enter author's name: ");
            authorName = scanner.nextLine();
            if (authorName.length() < 5 || authorName.length() > 30) {
                System.out.println("Author's name should be between 5 and 30 characters");
            }
        } while (authorName.length() < 5 || authorName.length() > 30);
        List<Author> authorsListCasted = authorsList;
        Author authorSearch = Search.linearSearchByStringAttribute(authorsListCasted, authorName, "authorName");
        if (authorSearch == null) {
            // Adding new author to the library
            System.out.println("Would you like to add a book or a thesis for this author?");
            System.out.println("1. Add a book");
            System.out.println("2. Add a thesis");
            System.out.println("3. No, finish editing this author");
        } else {
            System.out.println("Sorry, this author already exists");
        }
    }


    private static void addLibItem() throws NoSuchFieldException, IllegalAccessException, LibItemException {
        System.out.println("Which item would you like to add?");
        System.out.println("1. Add a book");
        System.out.println("2. Add a media item");
        System.out.println("3. Add a thesis");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addBook();
                break;
            case "2":
                addMedia();
                break;
            case "3":
                addThesis();
                break;
            default:
                System.out.println("Invalid choice. Please try again");
                break;
        }
    }

    private static void addBook() throws NoSuchFieldException, IllegalAccessException, LibItemException {
        String authorName;
        String title;
        String isbnNumber;
        String id = generateRandomID();
        do {
            System.out.println("Enter author's name (it should exists in the author list or add an author first): ");
            // Entering authorName
            authorName = scanner.nextLine();
            if (authorName.length() < 2) {
                System.out.println("Author's name should have at least two characters");
            }
        } while (authorName.length() < 2);
        List<Author> authorsListCasted = authorsList;
        Author authorSearch = Search.linearSearchByStringAttribute(authorsListCasted, authorName, "authorName");
        if (authorSearch == null) {
            System.out.println("Please add this author to the authors list first");
        } else {
            // Entering title
            do {
                System.out.println("Enter title:");
                title = scanner.nextLine();
                if (title.length() < 2 || title.length() > 50) {
                    System.out.println("Sorry, title should be between 2 and 50 characters");
                }
            } while (title.length() < 2 || title.length() > 50);
            // Entering ISBN
            do {
                System.out.println("Enter ISBN (13 characters):");
                isbnNumber = scanner.nextLine();
                if (isbnNumber.length() != 13) {
                    System.out.println("Sorry, ISBN should have 13 characters");
                }
            } while (isbnNumber.length() != 13);

            // Adding book to the library
            Book newBook = new Book(title, true, authorName, isbnNumber, id);
            library.add(newBook);
            System.out.println("New book has been added to the library");
            newBook.printItemDetails();
        }
    }

    private static void addMedia() {

    }

    private static void addThesis() {

    }

    private static String generateRandomID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private static void viewAuthors() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Author Catalogue:");
        System.out.println("1. Export list of all Authors (Note: Valid for Books and Theses only)");
        System.out.println("2. Search for an Author");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                authorsCsvHandler.writeAuthorsList();
                break;
            case "2":
                searchAuthors();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

    }

    private static void viewLibItems() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Library Catalogue:");
        System.out.println("1. Export list of all items (includes books, theses and media)");
        System.out.println("2. Export list of available items only");
        System.out.println("3. Search for an item");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                allItemsExport();
                break;
            case "2":
                availItemsExport();
                break;
            case "3":
                searchItems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void handleLoans() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Loan System:");
        System.out.println("a. Borrow Book");
        System.out.println("b. Return Book");
        System.out.println("c. Back to Main Menu");

        String choice = scanner.nextLine();

        switch (choice) {
            case "a":
                borrowBook();
                break;
            case "b":
                returnBook();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void borrowBook() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Enter User's ID");
        String userID = scanner.nextLine();
        LibUser user = Search.linearSearchByStringAttribute(libUserList, userID, "id");
        if (user == null){
            System.out.println("User ID " + userID + " is not registered");
        }
        else {
            System.out.println("Enter item's title: ");
            String itemTitle = scanner.nextLine();
            LibItem item = Search.linearSearchByStringAttribute(library, itemTitle, "title");
            if (item == null){
                System.out.println("Item " + itemTitle + " does not exist");
            }
            else {
                if (!item.getAvailabilityStatus()){
                    System.out.println(itemTitle + " is not available");
                }
                else {
                    int userIndex = libUserList.indexOf(user);
                    libUserList.get(userIndex).borrowItem(item);
                    int itemIndex = library.indexOf(item);
                    library.get(itemIndex).setAvailabilityStatus(false);
                    System.out.println(item.getTitle() + " has been borrowed by user " + user.getName());
                }
            }
        }
    }

    private static void returnBook() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Enter User's ID");
        String userID = scanner.nextLine();
        LibUser user = Search.linearSearchByStringAttribute(libUserList, userID, "id");
        if (user == null){
            System.out.println("User ID " + userID + " is not registered");
        }
        else {
            System.out.println("Enter item's title: ");
            String itemTitle = scanner.nextLine();
            LibItem item = Search.linearSearchByStringAttribute(user.getListOfBorrowedAssets(), itemTitle, "title");
            if (item == null){
                System.out.println("Item " + itemTitle + " was not borrowed by user " + userID);
            }
            else {
                int userIndex = libUserList.indexOf(user);
                libUserList.get(userIndex).returnItem(item);
                int itemIndex = library.indexOf(item);
                library.get(itemIndex).setAvailabilityStatus(true);
                System.out.println(item.getTitle() + " has been returned by user " + user.getName());
            }
        }
    }

    private static void searchAuthors() throws NoSuchFieldException, IllegalAccessException {
        String authorName;
        do {
            System.out.println("Enter author's name: ");
            authorName = scanner.nextLine();
            if (authorName.length() < 2) {
                System.out.println("Author's name should have at least two characters");
            }
        } while (authorName.length() < 2);

        System.out.println("Searching by name: " + authorName);
        List<Author> authorsListCasted = authorsList;
        Author authorSearch = Search.linearSearchByStringAttribute(authorsListCasted, authorName, "authorName");
        if (authorSearch == null) {
            System.out.println("Sorry, this author has not been found");
        } else {
            System.out.println("This author(" + authorSearch.getAuthorName() + ") has been found");
            if (authorSearch.getAuthoredItems().isEmpty()) {
                System.out.println("No available books or items");
            } else {
                authorSearch.printAuthorDetails();
            }
        }
    }

    private static void searchItems() throws NoSuchFieldException, IllegalAccessException {
        String itemTitle;
        do {
            System.out.println("Enter item's name (between 5 and 50 characters): ");
            itemTitle = scanner.nextLine();
            if (itemTitle.length() < 5 || itemTitle.length() > 50) {
                System.out.println("Item's name should be between 5 and 50 characters");
            }
        } while (itemTitle.length() < 5 || itemTitle.length() > 50);
        List<LibItem> libraryItems = library;
        LibItem itemSearch = Search.linearSearchByStringAttribute(libraryItems, itemTitle, "title");
        if (itemSearch == null) {
            System.out.println("Sorry, this item has not been found");
        } else {
            System.out.println("This item has been found");
            itemSearch.printItemDetails();
        }
    }


    private static void allItemsExport() {
        ArrayList<LibItem> booksCsvRecords = new ArrayList<>();
        ArrayList<LibItem> mediaCsvRecords = new ArrayList<>();
        ArrayList<LibItem> thesesCsvRecords = new ArrayList<>();
        for (var item : library) {
            if (item instanceof Book) {
                booksCsvRecords.add(item);
            } else if (item instanceof Media) {
                mediaCsvRecords.add(item);
            } else if (item instanceof Thesis) {
                thesesCsvRecords.add(item);
            }
        }
        // Initiating CSV Handlers for books, media and theses
        LibItemCsvHandler csvHandlerBooks = new LibItemCsvHandler(booksCsvFile, booksCsvHeader, booksCsvRecords, "Books");
        LibItemCsvHandler csvHandlerMedia = new LibItemCsvHandler(mediaCsvFile, mediaCsvHeader, mediaCsvRecords, "Media");
        LibItemCsvHandler csvHandlerTheses = new LibItemCsvHandler(thesesCsvFile, thesesCsvHeader, thesesCsvRecords, "Theses");
        // Generating CSV files
        csvHandlerBooks.writeToFile();
        csvHandlerMedia.writeToFile();
        csvHandlerTheses.writeToFile();
        // Reading CSV files and showing them
        showGeneratedLibItemsFiles(csvHandlerBooks, csvHandlerMedia, csvHandlerTheses);
    }

    private static void showGeneratedLibItemsFiles(
            LibItemCsvHandler csvHandlerBooks,
            LibItemCsvHandler csvHandlerMedia,
            LibItemCsvHandler csvHandlerTheses
    ) {
        // Reading newly generated CSV files
        System.out.println("\nFiles generated:");
        csvHandlerBooks.parseCsvFile(booksCsvFile, "Books");
        csvHandlerMedia.parseCsvFile(mediaCsvFile, "Media");
        csvHandlerTheses.parseCsvFile(thesesCsvFile, "Theses");
        System.out.println("\n");
    }

    private static void availItemsExport() {
        ArrayList<LibItem> availBooksCsvRecords = new ArrayList<>();
        ArrayList<LibItem> availMediaCsvRecords = new ArrayList<>();
        ArrayList<LibItem> availThesesCsvRecords = new ArrayList<>();
        List<LibItem> availableLibItems = library.stream()
                .filter(LibItem::getAvailabilityStatus)
                .collect(Collectors.toList());
        for (var item : availableLibItems) {
            if (item instanceof Book) {
                availBooksCsvRecords.add(item);
            } else if (item instanceof Media) {
                availMediaCsvRecords.add(item);
            } else if (item instanceof Thesis) {
                availThesesCsvRecords.add(item);
            }
        }
        LibItemCsvHandler csvHandlerAvailBooks = new LibItemCsvHandler(booksCsvFile, booksCsvHeader, availBooksCsvRecords, "Books");
        LibItemCsvHandler csvHandlerAvailMedia = new LibItemCsvHandler(mediaCsvFile, mediaCsvHeader, availMediaCsvRecords, "Media");
        LibItemCsvHandler csvHandlerAvailTheses = new LibItemCsvHandler(thesesCsvFile, thesesCsvHeader, availThesesCsvRecords, "Theses");
        csvHandlerAvailBooks.writeToFile();
        csvHandlerAvailMedia.writeToFile();
        csvHandlerAvailTheses.writeToFile();
        System.out.println("Available items lists have been generated");
        showGeneratedLibItemsFiles(csvHandlerAvailBooks, csvHandlerAvailMedia, csvHandlerAvailTheses);
    }
}

/*TODO
add sort options - ID and number of loans
Export the list
 */


