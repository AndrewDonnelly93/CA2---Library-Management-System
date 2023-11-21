package libmansys.libItem;

import java.util.Date;

// Represents theses and dissertations
public class Thesis extends LibItem {
    private String author;
    private String topic;
    private String abstractSummary;
    private Date datePublished;

    public Thesis(String title, boolean availabilityStatus, String author, String topic, String abstractSummary, Date datePublished) {
        super(title, availabilityStatus);
        this.author = author;
        this.topic = topic;
        this.abstractSummary = abstractSummary;
        this.datePublished = datePublished;
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
    void borrowItem() {

    }

    @Override
    void returnItem() {

    }
}
