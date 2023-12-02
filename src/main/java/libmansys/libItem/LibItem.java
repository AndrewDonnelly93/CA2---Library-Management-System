package libmansys.libItem;

public abstract class LibItem {
    protected String title;
    protected boolean availabilityStatus;

    protected LibItemType itemType;
    protected String id;

    public LibItem(String title, boolean availabilityStatus, String id) throws LibItemException {
        if (id.length() != 36)
            throw new LibItemException("\n\tItem ID should have 36 characters");
        else
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

    public void setId(String id) throws LibItemException {
        if (id.length() != 36)
            throw new LibItemException("\n\tItem ID should have 36 characters");
        else
            this.id = id;
    }

    public abstract void borrowItem();

    public abstract void returnItem();

    public abstract void printItemDetails();
}
