package libmansys.libItem;

import java.time.Duration;

// Represents CDs/DVDs
public class Media extends LibItem {
    private String producer;
    private String director;
    private Duration playtime;

    public Media(String title, boolean availabilityStatus, String producer, String director, Duration playtime) {
        super(title, availabilityStatus);
        this.producer = producer;
        this.director = director;
        this.playtime = playtime;
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

    public void setPlaytime(Duration playtime) {
        this.playtime = playtime;
    }

    @Override
    void borrowItem() {

    }

    @Override
    void returnItem() {

    }
}
