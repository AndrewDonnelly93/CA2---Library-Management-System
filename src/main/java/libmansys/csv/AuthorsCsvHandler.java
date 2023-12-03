package libmansys.csv;

import libmansys.author.Author;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorsCsvHandler {
    private String authorsCsvFile;
    private List<Author> authorsList;

    public AuthorsCsvHandler(String authorsCsvFile, List<Author> authorsList) {
        this.authorsCsvFile = authorsCsvFile;
        this.authorsList = authorsList;
    }

    public String getAuthorsCsvFile() {
        return authorsCsvFile;
    }

    public void setAuthorsCsvFile(String authorsCsvFile) {
        this.authorsCsvFile = authorsCsvFile;
    }

    public List<Author> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<Author> authorsList) {
        this.authorsList = authorsList;
    }

    public void writeAuthorsList() {
        try {
            FileWriter write = new FileWriter(this.getAuthorsCsvFile());
            CSVPrinter csvPrinter = new CSVPrinter(write, CSVFormat.DEFAULT.withHeader("Author"));
            this.getAuthorsList().forEach(author -> {
                try {
                    // Printing author name
                    csvPrinter.printRecord(author.getAuthorName());
                    csvPrinter.printRecord("List of books and theses written by this author");
                    if (author.getAuthoredItems().isEmpty()) {
                        csvPrinter.printRecord("No books or theses of this author is available");
                    } else {
                        for (var book: author.getAuthoredItems()) {
                            csvPrinter.printRecord(book.getTitle());
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            csvPrinter.flush();
            System.out.println("Authors list has been generated\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
