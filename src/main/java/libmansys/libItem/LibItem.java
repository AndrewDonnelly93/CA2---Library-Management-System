package libmansys.libItem;

import java.io.StringWriter;

public abstract class LibItem {
    protected String title;
    protected boolean availabilityStatus;
    protected LibItemType itemType;
    protected String id;

    public LibItem(String title, boolean availabilityStatus, String id) {
        this.id = id;
        this.title = title;
        this.availabilityStatus = availabilityStatus;
        this.itemType = LibItemType.LIB_ITEM;
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

    public String getId() {
        return id;
    }
    public abstract void borrowItem();
    public abstract void returnItem();

    public abstract void printItemDetails();

    public abstract String printItemToCSV();

   // public abstract void printCsvRecord();
}
