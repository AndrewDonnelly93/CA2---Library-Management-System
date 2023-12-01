package libmansys.csv;

import libmansys.libItem.Book;
import libmansys.libItem.LibItem;
import libmansys.libItem.Media;
import libmansys.libItem.Thesis;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibItemCsvHandler {
    private String csvFilePath;
    private StringWriter csvHeader;
    private ArrayList<LibItem> csvRecords;
    private String csvFileType;

    public LibItemCsvHandler(
            String csvFilePath,
            StringWriter csvHeader,
            ArrayList<LibItem> csvRecords,
            String csvFileType
    ) {
        this.csvFilePath = csvFilePath;
        this.csvHeader = csvHeader;
        this.csvRecords = csvRecords;
        this.csvFileType = csvFileType;
    }

    public String getCsvHeader() {
        return csvHeader.toString();
    }

    public void setCsvHeader(StringWriter csvHeader) {
        this.csvHeader = csvHeader;
    }

    public ArrayList<LibItem> getCsvRecords() {
        return csvRecords;
    }

    public void setCsvRecords(ArrayList<LibItem> csvRecords) {
        this.csvRecords = csvRecords;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public String getCsvFileType() {
        return csvFileType;
    }

    public void setCsvFileType(String csvFileType) {
        this.csvFileType = csvFileType;
    }

    // Write to a CSV file for books, media and theses
    public void writeToFile() {
        try {
            FileWriter write = new FileWriter(csvFilePath);
            CSVPrinter csvPrinter = new CSVPrinter(write, CSVFormat.DEFAULT);
            switch (this.getCsvFileType()) {
                case "Books":
                    csvPrinter.printRecord("Title","Availability","Author","ISBN","ID");
                    break;
                case "Media":
                    csvPrinter.printRecord("Title","Availability","Producer","Director","Duration","ID");
                    break;
                case "Theses":
                    csvPrinter.printRecord("Title","Availability","Topic","Date Published","Abstract","ID");
                    break;
                default:
                    break;
            }
            if (csvRecords.isEmpty()) {
                switch (this.getCsvFileType()) {
                    case "Books":
                        csvPrinter.printRecord("No available books");
                        break;
                    case "Media":
                        csvPrinter.printRecord("No available media");
                        break;
                    case "Theses":
                        csvPrinter.printRecord("No available theses");
                        break;
                    default:
                        break;
                }
            } else {
                for (var csvRecord : this.getCsvRecords()) {
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
                }
            }
            switch (this.getCsvFileType()) {
                case "Books":
                    System.out.println("The list of books has been generated");
                    break;
                case "Media":
                    System.out.println("The list of media has been generated");
                    break;
                case "Theses":
                    System.out.println("The list of theses has been generated");
                    break;
                default:
                    break;
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    // Read a CSV file and print it
    public void parseCsvFile(String docPath, String csvType) {
        try {
            CSVParser csvParser = CSVParser.parse(
                    new File(docPath),
                    Charset.defaultCharset(),
                    CSVFormat.DEFAULT.builder()
                            .setDelimiter(",")
                            .setSkipHeaderRecord(true)
                            .build()
            );
            // Records without header
           List<CSVRecord> parsedRecords = csvParser.getRecords().stream().skip(1).collect(Collectors.toList());
            switch (csvType) {
                case "Books":
                    printBooks(parsedRecords);
                    break;
                case "Media":
                    printMedia(parsedRecords);
                    break;
                case "Theses":
                    printTheses(parsedRecords);
                    break;
                default:
                    break;
            }

            csvParser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Printing book records
    public void printBooks(List<CSVRecord> csvRecords) {
        System.out.println("\n---------------------------------------------");
        System.out.println("Books");
        if (csvRecords.isEmpty()) {
            System.out.println("No available books");
        } else {
            csvRecords.forEach(csvRecord -> {
                long recordNumber = csvRecord.getRecordNumber() - 1;
                String title = csvRecord.get(0);
                String availability = csvRecord.get(1);
                String author = csvRecord.get(2);
                String isbn = csvRecord.get(3);
                String id = csvRecord.get(4);
                System.out.println("Record No - " + recordNumber);
                System.out.println("---------------");
                System.out.println("Title: " + title);
                System.out.println("Availability: " + availability);
                System.out.println("Author: " + author);
                System.out.println("ISBN: " + isbn);
                System.out.println("ID: " + id);
            });
        }
    }

    // Printing media records
    public void printMedia(List<CSVRecord> csvRecords) {
        System.out.println("\n---------------------------------------------");
        System.out.println("Media");
        if (csvRecords.isEmpty()) {
            System.out.println("No available media");
        } else {
            csvRecords.forEach(csvRecord -> {
                long recordNumber = csvRecord.getRecordNumber() - 1;
                String title = csvRecord.get(0);
                String availability = csvRecord.get(1);
                String producer = csvRecord.get(2);
                String director = csvRecord.get(3);
                String duration = csvRecord.get(4);
                String id = csvRecord.get(5);

                System.out.println("Record No - " + recordNumber);
                System.out.println("---------------");
                System.out.println("Title: " + title);
                System.out.println("Availability: " + availability);
                System.out.println("Producer: " + producer);
                System.out.println("Director: " + director);
                System.out.println("Duration: " + duration);
                System.out.println("ID: " + id);
            });
        }
    }

    // Printing theses records
    public void printTheses(List<CSVRecord> csvRecords) {
        System.out.println("\n---------------------------------------------");
        System.out.println("Theses");
        if (csvRecords.isEmpty()) {
            System.out.println("No available theses");
        } else {
            csvRecords.forEach(csvRecord -> {
                long recordNumber = csvRecord.getRecordNumber() - 1;
                String title = csvRecord.get(0);
                String availability = csvRecord.get(1);
                String topic = csvRecord.get(2);
                String datePublished = csvRecord.get(3);
                String id = csvRecord.get(4);
                System.out.println("Record No - " + recordNumber);
                System.out.println("---------------");
                System.out.println("Title: " + title);
                System.out.println("Availability: " + availability);
                System.out.println("Topic: " + topic);
                System.out.println("Date Published: " + datePublished);
                System.out.println("ID: " + id);
            });
        }
    }
}
