package libmansys.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvHandler {
    private String csvFilePath;
    private boolean appendToFile = false;
    private String csvHeader;
    private ArrayList<StringBuilder> csvRecords;

    public CsvHandler(
            String csvFilePath,
            boolean appendToFile,
            String csvHeader,
            ArrayList<StringBuilder> csvRecords
    ) {
        this.csvFilePath = csvFilePath;
        this.appendToFile = appendToFile;
        this.csvHeader = csvHeader;
        this.csvRecords = csvRecords;
    }

    public String getCsvHeader() {
        return csvHeader.toString();
    }

    public void setCsvHeader(String csvHeader) {
        this.csvHeader = csvHeader;
    }

    public ArrayList<StringBuilder> getCsvRecords() {
        return csvRecords;
    }

    public void setCsvRecords(ArrayList<StringBuilder> csvRecords) {
        this.csvRecords = csvRecords;
    }

    public void writeToFile() {
        try {
            FileWriter write = new FileWriter(csvFilePath, appendToFile);
            CSVPrinter csvPrinter = new CSVPrinter(write, CSVFormat.DEFAULT.withHeader(this.getCsvHeader()));
            for (var csvRecord: this.getCsvRecords()) {
                csvPrinter.printRecord(csvRecord.toString());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
