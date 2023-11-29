package libmansys.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

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
            CSVPrinter csvPrinter = new CSVPrinter(write, CSVFormat.DEFAULT.withHeader(this.getCsvHeader().toString().split(",")));
            for (var csvRecord: this.getCsvRecords()) {
                csvPrinter.printRecord(csvRecord.toString().split("¬"));
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
