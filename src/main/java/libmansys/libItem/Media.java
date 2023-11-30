package libmansys.libItem;

import java.io.StringWriter;
import java.time.Duration;

// Represents CDs/DVDs
public class Media extends LibItem {
    private String producer;
    private String director;
    private Duration playtime;

    public Media(
            String title,
            boolean availabilityStatus,
            String producer,
            String director,
            Duration playtime,
            String id
    ) {
        super(title, availabilityStatus, id);
        this.producer = producer;
        this.director = director;
        this.playtime = playtime;
        this.itemType = LibItemType.MEDIA;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Duration getPlaytime() {
        return playtime;
    }

    public String formatDuration(Duration d) {
        long days = d.toDays();
        d = d.minusDays(days);
        long hours = d.toHours();
        d = d.minusHours(hours);
        long minutes = d.toMinutes();
        d = d.minusMinutes(minutes);
        long seconds = d.getSeconds() ;
        return
                (days ==  0?"":days+" days ")+
                        (hours == 0?"":hours+" hours ")+
                        (minutes ==  0?"":minutes+" minutes")+
                        (seconds == 0?"":seconds+", seconds");
    }

    public void setPlaytime(Duration playtime) {
        this.playtime = playtime;
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
        System.out.println("Item type: Media Title: " + this.getTitle()
                + " Producer: " + this.getProducer() + " Director: " + this.getDirector() +
                " Duration: " + this.formatDuration(this.getPlaytime()));
    }

    @Override
    public String printItemToCSV() {
        StringBuilder media = new StringBuilder();
        media.append(this.getTitle()).append("¬");
        media.append(this.getAvailabilityStatus()).append("¬");
        media.append(this.getProducer()).append("¬");
        media.append(this.getDirector()).append("¬");
        media.append(this.formatDuration(this.getPlaytime())).append("¬");
        media.append(this.getId()).append("\n");
        return media.toString();
    }
}
