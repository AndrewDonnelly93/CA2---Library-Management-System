package libmansys.libItem;

public enum LibItemType {
    LIB_ITEM("LIB_ITEM"),
    BOOK("BOOK"),
    MEDIA("MEDIA"),
    THESIS("THESIS");

    private final String text;

    LibItemType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
