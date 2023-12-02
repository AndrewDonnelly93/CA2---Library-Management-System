package libmansys.libItem;

import java.text.SimpleDateFormat;
import java.util.Date;

// Represents theses and dissertations
public class Thesis extends LibItem {
    private String author;
    private String topic;
    private String abstractSummary;
    private Date datePublished;

    public Thesis(
            String title,
            boolean availabilityStatus,
            String author,
            String topic,
            String abstractSummary,
            Date datePublished,
            String id
    ) throws LibItemException {
        super(title, availabilityStatus, id);
        this.author = author;
        this.topic = topic;
        this.abstractSummary = abstractSummary;
        this.datePublished = datePublished;
        this.itemType = LibItemType.THESIS;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAbstractSummary() {
        return abstractSummary;
    }

    public void setAbstractSummary(String abstractSummary) {
        this.abstractSummary = abstractSummary;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public void borrowItem() {

    }

    @Override
    public void returnItem() {

    }

    @Override
    public void printItemDetails() {
        System.out.println("-".repeat(50));
        System.out.println("Item type: Thesis Title: " + this.getTitle()
                + " Author: " + this.getAuthor() + " Topic: " + this.getTopic() +
                " Date published: " + new SimpleDateFormat("dd-MM-yyyy").format(this.getDatePublished()));
        System.out.println("\nAbstract:");
        System.out.println(this.getAbstractSummary());
        if (this.getAvailabilityStatus())
            System.out.println("AVAILABLE");
        else
            System.out.println("NOT AVAILABLE");
        System.out.println();

    }
}
