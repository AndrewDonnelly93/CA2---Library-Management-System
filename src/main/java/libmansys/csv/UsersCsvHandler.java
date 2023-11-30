package libmansys.csv;

import libmansys.LibUser;
import libmansys.libItem.Book;
import libmansys.libItem.Media;
import libmansys.libItem.Thesis;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UsersCsvHandler {
    private String usersCsvFile;
    private ArrayList<LibUser> usersList;

    public UsersCsvHandler(String usersCsvFile, ArrayList<LibUser> usersList) {
        this.usersCsvFile = usersCsvFile;
        this.usersList = usersList;
    }

    public String getUsersCsvFile() {
        return usersCsvFile;
    }

    public void setUsersCsvFile(String usersCsvFile) {
        this.usersCsvFile = usersCsvFile;
    }

    public ArrayList<LibUser> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<LibUser> usersList) {
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
                        ArrayList<String> booksCsvRecords = new ArrayList<>();
                        ArrayList<String> mediaCsvRecords = new ArrayList<>();
                        ArrayList<String> thesesCsvRecords = new ArrayList<>();

                        for (var item : user.getListOfBorrowedAssets()) {
                            if (item instanceof Book) {
                                booksCsvRecords.add(item.printItemToCSV().toString());
                            } else if (item instanceof Media) {
                                mediaCsvRecords.add(item.printItemToCSV().toString());
                            } else if (item instanceof Thesis) {
                                thesesCsvRecords.add(item.printItemToCSV().toString());
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

                    csvPrinter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printLibItemToCsv(CSVPrinter csvPrinter, String libItemsName, String emptyMessage, ArrayList<String> csvRecords) throws IOException {
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
                csvRecords.forEach(record -> {
                    try {
                      csvPrinter.printRecord(record.toString().split("¬"));
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
