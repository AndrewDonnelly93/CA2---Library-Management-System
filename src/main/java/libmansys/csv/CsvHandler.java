package libmansys.csv;

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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvHandler {
    private String csvFilePath;
    private StringWriter csvHeader;
    private ArrayList<String> csvRecords;

    public CsvHandler(
            String csvFilePath,
            StringWriter csvHeader,
            ArrayList<String> csvRecords
    ) {
        this.csvFilePath = csvFilePath;
        this.csvHeader = csvHeader;
        this.csvRecords = csvRecords;
    }

    public String getCsvHeader() {
        return csvHeader.toString();
    }

    public void setCsvHeader(StringWriter csvHeader) {
        this.csvHeader = csvHeader;
    }

    public ArrayList<String> getCsvRecords() {
        return csvRecords;
    }

    public void setCsvRecords(ArrayList<String> csvRecords) {
        this.csvRecords = csvRecords;
    }

    public void writeToFile() {
        try {
            FileWriter write = new FileWriter(csvFilePath);
            CSVPrinter csvPrinter = new CSVPrinter(write, CSVFormat.DEFAULT.withHeader(this.getCsvHeader().split(",")));
            for (var csvRecord: this.getCsvRecords()) {
                csvPrinter.printRecord(csvRecord.toString().split("¬"));
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

    public void printBooks(List<CSVRecord> csvRecords) {
        System.out.println("\n---------------------------------------------");
        System.out.println("Books");
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

    public void printMedia(List<CSVRecord> csvRecords) {
        System.out.println("\n---------------------------------------------");
        System.out.println("Media");
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

    public void printTheses(List<CSVRecord> csvRecords) {
        System.out.println("\n---------------------------------------------");
        System.out.println("Theses");
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
