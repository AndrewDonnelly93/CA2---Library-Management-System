package libmansys.csv;

import libmansys.user.LibUser;
import libmansys.libItem.Book;
import libmansys.libItem.LibItem;
import libmansys.libItem.Media;
import libmansys.libItem.Thesis;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class UsersCsvHandler {
    private String usersCsvFile;
    private LinkedList<LibUser> usersList;

    public UsersCsvHandler(String usersCsvFile, LinkedList<LibUser> usersList) {
        this.usersCsvFile = usersCsvFile;
        this.usersList = usersList;
    }

    public String getUsersCsvFile() {
        return usersCsvFile;
    }

    public void setUsersCsvFile(String usersCsvFile) {
        this.usersCsvFile = usersCsvFile;
    }

    public LinkedList<LibUser> getUsersList() {
        return usersList;
    }

    public void setUsersList(LinkedList<LibUser> usersList) {
        this.usersList = usersList;
    }

    // Writing a users list
    public void writeUsersList() {
        try {
            FileWriter write = new FileWriter(this.getUsersCsvFile());
            CSVPrinter csvPrinter = new CSVPrinter(write, CSVFormat.DEFAULT.withHeader("Name", "ID"));
            this.getUsersList().forEach(user -> {
                try {
                    // Printing username and ID
                    csvPrinter.printRecord(user.getName(), user.getId());
                    csvPrinter.printRecord("List of borrowed assets");
                    if (user.getListOfBorrowedAssets().isEmpty()) {
                        csvPrinter.printRecord("No items borrowed");
                    } else {
                        LinkedList<LibItem> booksCsvRecords = new LinkedList<>();
                        LinkedList<LibItem> mediaCsvRecords = new LinkedList<>();
                        LinkedList<LibItem> thesesCsvRecords = new LinkedList<>();

                        for (var item : user.getListOfBorrowedAssets()) {
                            if (item instanceof Book) {
                                booksCsvRecords.add(item);
                            } else if (item instanceof Media) {
                                mediaCsvRecords.add(item);
                            } else if (item instanceof Thesis) {
                                thesesCsvRecords.add(item);
                            }
                        }

                        // Printing borrowed books
                        printLibItemToCsv(
                                csvPrinter,
                                "Books",
                                "No books borrowed",
                                booksCsvRecords
                        );

                        // Printing borrowed media
                        printLibItemToCsv(
                                csvPrinter,
                                "Media",
                                "No media borrowed",
                                mediaCsvRecords
                        );

                        // Printing borrowed theses
                        printLibItemToCsv(
                                csvPrinter,
                                "Theses",
                                "No theses borrowed",
                                mediaCsvRecords
                        );
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            csvPrinter.flush();
            System.out.println("\nThe list of user file has been generated\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printLibItemToCsv(CSVPrinter csvPrinter, String libItemsName, String emptyMessage, LinkedList<LibItem> csvRecords) throws IOException {
        try {
            csvPrinter.printRecord(libItemsName);
            if (csvRecords.isEmpty()) {
                csvPrinter.printRecord(emptyMessage);
            } else {
                switch (libItemsName) {
                    case "Books":
                        csvPrinter.printRecord("Title","Availability","Author","ISBN","ID");
                        break;
                    case "Media":
                        csvPrinter.printRecord("Title","Availability","Producer","Director","Duration","ID");
                        break;
                    case "Theses":
                        csvPrinter.printRecord("Title","Availability","Topic","Date Published","ID");
                        break;
                }
                csvRecords.forEach(csvRecord -> {
                    try {
                        if (csvRecord instanceof Book) {
                            csvPrinter.printRecord(
                                    csvRecord.getTitle(),
                                    csvRecord.getAvailabilityStatus(),
                                    ((Book) csvRecord).getAuthor(),
                                    ((Book) csvRecord).getISBN(),
                                    csvRecord.getId()
                            );
                        } else if (csvRecord instanceof Media) {
                            csvPrinter.printRecord(
                                    csvRecord.getTitle(),
                                    csvRecord.getAvailabilityStatus(),
                                    ((Media) csvRecord).getProducer(),
                                    ((Media) csvRecord).getDirector(),
                                    ((Media) csvRecord).getPlaytime(),
                                    csvRecord.getId()
                            );
                        } else if (csvRecord instanceof Thesis) {
                            csvPrinter.printRecord(
                                    csvRecord.getTitle(),
                                    csvRecord.getAvailabilityStatus(),
                                    ((Thesis) csvRecord).getTopic(),
                                    ((Thesis) csvRecord).getDatePublished(),
                                    ((Thesis) csvRecord).getAbstractSummary(),
                                    csvRecord.getId()
                            );
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
