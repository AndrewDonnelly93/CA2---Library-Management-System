package libmansys.libItem;

public abstract class LibItem {
    protected String title;
    protected boolean availabilityStatus;

    public LibItem(String title, boolean availabilityStatus) {
        this.title = title;
        this.availabilityStatus = availabilityStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    abstract void borrowItem();
    abstract void returnItem();
}
